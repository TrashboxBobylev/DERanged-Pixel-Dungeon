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

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.adventurer;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Awareness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Foresight;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.effects.Identification;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;

public class TreasureMap extends ArmorAbility {

	{
		baseChargeUse = 50f;
	}

	@Override
	protected void activate(ClassArmor armor, Hero hero, Integer target) {
		Sample.INSTANCE.play( Assets.Sounds.READ );
		Buff.prolong(hero, Awareness.class, Awareness.DURATION);
		Buff.prolong(hero, LuckTracker.class, 20 * (1 + 0.2f * hero.pointsInTalent(Talent.LONG_LUCK)));
		if (hero.hasTalent(Talent.FORESIGHT)) {
			Buff.prolong(hero, Foresight.class, 5f * hero.pointsInTalent(Talent.FORESIGHT));
		}
		Dungeon.observe();
		hero.sprite.parent.add( new Identification( hero.sprite.center().offset( 0, -16 ) ) );
		hero.busy();
		((HeroSprite)hero.sprite).read();

		armor.useCharge(hero, this);
		Invisibility.dispel();
		hero.spendAndNext(Actor.TICK);
	}

	@Override
	public boolean isTracked(Hero hero) {
		return hero.buff(LuckTracker.class) != null;
	}

	@Override
	public int icon() {
		return HeroIcon.TREASUREMAP;
	}

	@Override
	public Talent[] talents() {
		return new Talent[]{Talent.LONG_LUCK, Talent.FORESIGHT, Talent.GOLD_HUNTER, Talent.HEROIC_ENERGY};
	}

	public static class LuckTracker extends FlavourBuff {

		public static final float DURATION = 20f;

		@Override
		public int icon() {
			return BuffIndicator.LUCK;
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

		@Override
		public String toString() {
			return Messages.get(this, "name");
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", dispTurns());
		}

	}

	public static class GoldTracker extends Buff {}
}
