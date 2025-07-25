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

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Build;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Command;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HorseRiding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Pray;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Preparation;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SharpShooterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.DungeonSeed;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndChooseSubclass;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass.*;

public class TengusMask extends Item {
	
	private static final String AC_WEAR	= "WEAR";
	
	{
		stackable = false;
		image = ItemSpriteSheet.MASK;

		defaultAction = AC_WEAR;

		unique = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_WEAR );
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_WEAR )) {
			
			curUser = hero;
			ArrayList<HeroSubClass> subClasses;

			if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.RANDOM_HERO)){
				subClasses = new ArrayList<>();
                ArrayList<HeroSubClass> heroSubClasses = new ArrayList<>(Arrays.asList(HeroSubClass.values()));
                //remove unusable classes
                heroSubClasses.remove(NONE);
                if (!curUser.isClassedLoosely(HeroClass.MAGE))
                    heroSubClasses.remove(HeroSubClass.BATTLEMAGE);
                if (!curUser.isClassedLoosely(HeroClass.HUNTRESS))
                    heroSubClasses.remove(HeroSubClass.SNIPER);
                if (!curUser.isClassedLoosely(HeroClass.ROGUE))
                    heroSubClasses.remove(HeroSubClass.ASSASSIN);
				if (!curUser.isClassedLoosely(HeroClass.CLERIC)) {
					heroSubClasses.remove(PALADIN);
					heroSubClasses.remove(PRIEST);
					heroSubClasses.remove(ENCHANTER);
				}
				if (!curUser.isClassedLoosely(HeroClass.SAMURAI)){
					heroSubClasses.remove(MASTER);
					heroSubClasses.remove(SLAYER);
				}
				if (!curUser.isClassedLoosely(HeroClass.ADVENTURER)){
					heroSubClasses.remove(ENGINEER);
					heroSubClasses.remove(EXPLORER);
				}
				if (!curUser.isClassedLoosely(HeroClass.MEDIC)){
					heroSubClasses.remove(SAVIOR);
				}
                heroSubClasses.remove(hero.subClass);
                //remove rat king class
                heroSubClasses.remove(HeroSubClass.KING);
                while (subClasses.size() < 3) {
                    HeroSubClass chosenSub;
                    do {
                        chosenSub = Random.element(heroSubClasses);
                        if (!subClasses.contains(chosenSub)) {
                            subClasses.add(chosenSub);
                            break;
                        }
                    } while (true);
                }
			} else {
				subClasses = hero.heroClass.subClasses();
			}

			GameScene.show( new WndChooseSubclass( this, hero, subClasses ) );
			
		}
	}
	
	@Override
	public boolean doPickUp(Hero hero, int pos) {
		Badges.validateMastery();
		return super.doPickUp( hero, pos );
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	public void choose( HeroSubClass way ) {
		
		detach( curUser.belongings.backpack );
		Catalog.countUse( getClass() );
		
		curUser.spend( Actor.TICK );
		curUser.busy();
		
		curUser.subClass = way;
		Talent.initSubclassTalents(curUser);

		if (way == HeroSubClass.ASSASSIN && curUser.invisible > 0){
			Buff.affect(curUser, Preparation.class);
		}

		if (way == HeroSubClass.ENGINEER) {
			Buff.affect(curUser, Build.class);
		}

		if (way == HeroSubClass.HORSEMAN) {
			Buff.affect(curUser, HorseRiding.class).set();
		}

		if (way == HeroSubClass.CRUSADER) {
			Buff.affect(curUser, Pray.class);
		}

		if (way == HeroSubClass.MEDICALOFFICER) {
			Buff.affect(curUser, Command.class);
		}

		if (way == HeroSubClass.SHARPSHOOTER) {
			Buff.affect(curUser, SharpShooterBuff.class);
		}
		
		curUser.sprite.operate( curUser.pos );
		Sample.INSTANCE.play( Assets.Sounds.MASTERY );
		
		Emitter e = curUser.sprite.centerEmitter();
		e.pos(e.x-2, e.y-6, 4, 4);
		e.start(Speck.factory(Speck.MASK), 0.05f, 20);
		GLog.p( Messages.get(this, "used"));
		
	}
}
