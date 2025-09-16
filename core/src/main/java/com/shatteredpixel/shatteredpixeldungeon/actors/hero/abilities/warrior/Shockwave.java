/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2025 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Tackle;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ratking.OmniAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mimic;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ConeAOE;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent.*;

public class Shockwave extends ArmorAbility {

	{
		baseChargeUse = 35f;
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	public int targetedPos(Char user, int dst) {
		return new Ballistica( user.pos, dst, Ballistica.STOP_SOLID | Ballistica.STOP_TARGET ).collisionPos;
	}

	public static void activate(Hero hero, int target, int degrees, int maxDist, Callback next) {
		boolean endTurn = next == null;

		hero.busy();

		Ballistica aim = new Ballistica(hero.pos, target, Ballistica.WONT_STOP);

		int dist = Math.min(aim.dist, maxDist);

		ConeAOE cone = new ConeAOE(aim, dist, degrees,
				Ballistica.STOP_SOLID | Ballistica.STOP_TARGET);

		//cast to cells at the tip, rather than all cells, better performance.
		for (Ballistica ray : cone.outerRays){
			hero.sprite.parent.recycle( MagicMissile.class ).reset(
					MagicMissile.FORCE_CONE,
					hero.sprite,
					ray.path.get(ray.dist),
					null
			);
		}

		hero.sprite.zap(target);
		// TODO fix so that sounds can just play as soon as possible.
		Sample.INSTANCE.playDelayed(Assets.Sounds.BLAST, next == null ? 0f : 0.125f, next == null ? 1f : 3f, 0.5f);
		PixelScene.shake(2, 0.5f);
		//final zap at 2/3 distance, for timing of the actual effect
		MagicMissile.boltFromChar(hero.sprite.parent,
				MagicMissile.FORCE_CONE,
				hero.sprite,
				cone.coreRay.path.get(dist * 2 / 3),
				() -> {
					for (int cell : cone.cells){

						Char ch = Actor.findChar(cell);
						if (ch != null && ch.alignment != hero.alignment){
							int scalingStr = hero.STR()-10;
							int damage = Hero.heroDamageIntRange(5 + scalingStr, 10 + 2*scalingStr);
							float modifier = (1f + hero.byTalent(
									SHOCK_FORCE, .2f,
									AFTERSHOCK, .15f));
							damage = Math.round(damage * modifier);
							damage -= ch.drRoll();

							// note I'm not giving this to aftershock.
							if (hero.pointsInTalent(STRIKING_WAVE) == 4){
								Buff.affect(hero, Talent.StrikingWaveTracker.class, 0f);
							}

							if (Random.Int(10) < hero.byTalent(
									STRIKING_WAVE, 3,
									AFTERSHOCK, 2)){
								boolean wasEnemy = ch.alignment == Char.Alignment.ENEMY
										|| (ch instanceof Mimic && ch.alignment == Char.Alignment.NEUTRAL);
								damage = hero.attackProc(ch, damage);
								ch.damage(damage, hero);
								if(wasEnemy) {
									if (hero.subClass.is(HeroSubClass.GLADIATOR) && wasEnemy){
										Buff.affect( hero, Combo.class ).hit( ch );
									}
									if (hero.subClass.is(HeroSubClass.VETERAN)){
										if (Dungeon.level.adjacent(ch.pos, hero.pos) && hero.buff(Tackle.class) == null) {
											Buff.prolong(hero, Tackle.class, 1).set(ch.id());
										}
									}
								}
							} else {
								ch.damage(damage, hero);
							}
							if (ch.isAlive()){
								// fixme for wrath this should probably be delayed, though
								if (Random.Int(hero.hasTalent(SHOCK_FORCE) ? 4 : 5) < hero.pointsInTalent(SHOCK_FORCE,AFTERSHOCK)){
									Buff.affect(ch, Paralysis.class, 5f);
									Buff.affect(ch, ShockForceStunHold.class, 0f);} else {
									Buff.affect(ch, Cripple.class, 5f);
								}
							}

						}
					}

					if(endTurn) {
						Invisibility.dispel();
						hero.spendAndNext(Actor.TICK);
						OmniAbility.markAbilityUsed(new Shockwave());
					} else next.call();

				});
	}

	// this prevents stun from being broken during wrath.
	public static class ShockForceStunHold extends FlavourBuff {{ actPriority = VFX_PRIO; }}

	@Override
	protected void activate(ClassArmor armor, Hero hero, Integer target) {
		if (target == null){
			return;
		}
		if (target == hero.pos){
			GLog.w(Messages.get(this, "self_target"));
			return;
		}
		hero.busy();

        armor.useCharge(hero, this, false);

		Ballistica aim = new Ballistica(hero.pos, target, Ballistica.WONT_STOP);

		int maxDist = 5 + hero.pointsInTalent(Talent.EXPANDING_WAVE);
		int dist = Math.min(aim.dist, maxDist);

		ConeAOE cone = new ConeAOE(aim,
				dist,
				60 + 15*hero.pointsInTalent(Talent.EXPANDING_WAVE),
				Ballistica.STOP_SOLID | Ballistica.STOP_TARGET);

		//cast to cells at the tip, rather than all cells, better performance.
		for (Ballistica ray : cone.outerRays){
			((MagicMissile)hero.sprite.parent.recycle( MagicMissile.class )).reset(
					MagicMissile.FORCE_CONE,
					hero.sprite,
					ray.path.get(ray.dist),
					null
			);
		}

		hero.sprite.zap(target);
		Sample.INSTANCE.play(Assets.Sounds.BLAST, 1f, 0.5f);
		PixelScene.shake(2, 0.5f);
		//final zap at 2/3 distance, for timing of the actual effect
		MagicMissile.boltFromChar(hero.sprite.parent,
				MagicMissile.FORCE_CONE,
				hero.sprite,
				cone.coreRay.path.get(dist * 2 / 3),
				new Callback() {
					@Override
					public void call() {

						for (int cell : cone.cells){

							Char ch = Actor.findChar(cell);
							if (ch != null && ch.alignment != hero.alignment){
								int scalingStr = hero.STR()-10;
								int damage = Hero.heroDamageIntRange(5 + scalingStr, 10 + 2*scalingStr);
								damage = Math.round(damage * (1f + 0.2f*hero.pointsInTalent(SHOCK_FORCE)));
								damage -= ch.drRoll();

								if (hero.pointsInTalent(STRIKING_WAVE) == 4){
									Buff.affect(hero, Talent.StrikingWaveTracker.class, 0f);
								}

								if (Random.Int(10) < 3*hero.pointsInTalent(STRIKING_WAVE)){
									boolean wasEnemy = ch.alignment == Char.Alignment.ENEMY
											|| (ch instanceof Mimic && ch.alignment == Char.Alignment.NEUTRAL);
									damage = hero.attackProc(ch, damage);
									ch.damage(damage, hero);
									if (hero.subClass.is(HeroSubClass.GLADIATOR) && wasEnemy){
										Buff.affect( hero, Combo.class ).hit( ch );
									}
									if (hero.subClass.is(HeroSubClass.VETERAN)){
										if (Dungeon.level.adjacent(ch.pos, hero.pos) && hero.buff(Tackle.class) == null) {
											Buff.prolong(hero, Tackle.class, 1).set(ch.id());
										}
									}
								} else {
									ch.damage(damage, hero);
								}
								if (ch.isAlive()){
									if (Random.Int(4) < hero.pointsInTalent(SHOCK_FORCE)){
										Buff.affect(ch, Paralysis.class, 5f);
									} else {
										Buff.affect(ch, Cripple.class, 5f);
									}
								}

							}
						}

						Invisibility.dispel();
						hero.spendAndNext(Actor.TICK);
						OmniAbility.markAbilityUsed(Shockwave.this);

					}
				});
	}

	@Override
	public int icon() {
		return HeroIcon.SHOCKWAVE;
	}

	@Override
	public Talent[] talents() {
		return new Talent[]{Talent.EXPANDING_WAVE, STRIKING_WAVE, SHOCK_FORCE, Talent.HEROIC_ENERGY};
	}
}
