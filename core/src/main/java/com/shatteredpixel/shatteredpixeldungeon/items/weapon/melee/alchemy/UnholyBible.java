/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.alchemy;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Evolution;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.UpgradeDust;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Bible;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class UnholyBible extends MeleeWeapon implements AlchemyWeapon {

	{
		image = ItemSpriteSheet.UNHOLY_BIBLE;
		hitSound = Assets.Sounds.HIT;
		hitSoundPitch = 1.1f;

		tier = 4;
	}

	@Override
	public int proc(Char attacker, Char defender, int damage) {
		switch (Random.Int(15)) {
			case 0: case 1: default:
				Buff.affect( defender, Weakness.class, 3f );
				break;
			case 2: case 3:
				Buff.affect( defender, Vulnerable.class, 3f );
				break;
			case 4:
				Buff.affect( defender, Cripple.class, 3f );
				break;
			case 5:
				Buff.affect( defender, Blindness.class, 3f );
				break;
			case 6:
				Buff.affect( defender, Terror.class, 3f );
				break;
			case 7: case 8: case 9:
				Buff.affect( defender, Amok.class, 3f );
				break;
			case 10: case 11:
				Buff.affect( defender, Slow.class, 3f );
				break;
			case 12: case 13:
				Buff.affect( defender, Hex.class, 3f );
				break;
			case 14:
				Buff.affect( defender, Paralysis.class, 3f );
				break;
		}
		if (Random.Float() < Math.min(0.01f*(1+buffedLvl()), 0.1f)) { //1% base, +1% per lvl, max 10%
			Buff.affect( defender, com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Doom.class);
		}
		return damage;
	}

	@Override
	public int max(int lvl) {
		return  3*(tier) +    		//12 base
				lvl*(tier-1);     	//+3 per level
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		demonAbility(hero, 5+buffedLvl(), this);
	}

	public static void demonAbility(Hero hero, int duration, MeleeWeapon wep){
		wep.beforeAbilityUsed(hero, null);
		Buff.prolong(hero, Demon.class, duration);
		hero.next();
		((HeroSprite)hero.sprite).read();
		SpellSprite.show(hero, SpellSprite.BERSERK);
		hero.sprite.centerEmitter().start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );
		Sample.INSTANCE.play( Assets.Sounds.CHALLENGE );
		Sample.INSTANCE.play( Assets.Sounds.READ );
		wep.afterAbilityUsed(hero);
	}

	@Override
	public String abilityInfo() {
		int duration = levelKnown ? 6+buffedLvl() : 6;
		if (levelKnown){
			return Messages.get(this, "ability_desc", duration);
		} else {
			return Messages.get(this, "typical_ability_desc", duration);
		}
	}

	public static class Demon extends FlavourBuff {

		{
			announced = true;
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.DUEL_EVIL;
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (6 - visualcooldown()) / 6);
		}
	}

	@Override
	public ArrayList<Class<?extends Item>> weaponRecipe() {
		return new ArrayList<>(Arrays.asList(Bible.class, UpgradeDust.class, Evolution.class));
	}

	@Override
	public String discoverHint() {
		return AlchemyWeapon.hintString(weaponRecipe());
	}

	@Override
	public String desc() {
		String info = super.desc();

		info += "\n\n" + AlchemyWeapon.hintString(weaponRecipe());

		return info;
	}

}
