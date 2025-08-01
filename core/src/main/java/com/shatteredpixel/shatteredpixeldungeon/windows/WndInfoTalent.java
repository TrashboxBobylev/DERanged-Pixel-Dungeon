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

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentIcon;
import com.watabou.utils.Callback;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

public class WndInfoTalent extends WndTitledMessage {

	public WndInfoTalent(Talent talent, int points, TalentButtonCallback buttonCallback){
		super(
				new TalentIcon( talent ),
				Messages.titleCase(talent.title() + (points > 0 ? " +" + points: "")),
				talent.desc((buttonCallback != null && buttonCallback.metamorphDesc()) ||
						hero != null && (hero.metamorphedTalents.containsValue(talent)
								&& !hero.heroClass.is(talent.getHeroClass())
						)));

		if (buttonCallback != null) {
			addToBottom(new RedButton( buttonCallback.prompt() ) {
				@Override
				protected void onClick() {
					super.onClick();
					hide();
					buttonCallback.call();
				}
				{
					icon(Icons.get(Icons.TALENT));
					setHeight(18);
				}
			}, 2*GAP, 1);
		}

	}

	public static abstract class TalentButtonCallback implements Callback {

		public abstract String prompt();

		public boolean metamorphDesc(){
			return false;
		}

	}

}
