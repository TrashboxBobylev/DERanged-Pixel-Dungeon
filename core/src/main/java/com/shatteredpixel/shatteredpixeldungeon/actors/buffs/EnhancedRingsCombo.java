/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
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

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

import static com.shatteredpixel.shatteredpixeldungeon.items.Item.updateQuickslot;

public class EnhancedRingsCombo extends FlavourBuff{

	{
		type = buffType.POSITIVE;
	}

	int combo = 0;

	public void hit() {
		if (combo < Dungeon.hero.pointsInTalent(Talent.RING_KNUCKLE, Talent.RK_FIGHTER)) combo ++;
		else combo = Dungeon.hero.pointsInTalent(Talent.RING_KNUCKLE, Talent.RK_FIGHTER);
		if (target instanceof Hero) ((Hero) target).updateHT(false);
	}

	public int getCombo() {
		return combo;
	}

	private static final String COMBO = "combo";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(COMBO, combo);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		combo = bundle.getInt(COMBO);
	}

	@Override
	public boolean attachTo(Char target) {
		if (super.attachTo(target)){
			return true;
		}
		return false;
	}

	@Override
	public void detach() {
		super.detach();
		if (target instanceof Hero) ((Hero) target).updateHT(false);
		updateQuickslot();
	}

	@Override
	public int icon() {
		return BuffIndicator.UPGRADE;
	}

	@Override
	public void tintIcon(Image icon) {
		icon.hardlight(0, 1, 0);
	}

	@Override
	public float iconFadePercent() {
		float max = (Dungeon.hero.pointsInTalent(Talent.RING_KNUCKLE, Talent.RK_FIGHTER) >= 2) ? 2 : 1;
		return Math.max(0, (max-visualcooldown()) / max);
	}

	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", (int)visualcooldown(), combo);
	}
}
