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

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.items.KingsCrown;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

import java.util.Arrays;
import java.util.Collection;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

public class WndChooseAbility extends Window {

	private static final int WIDTH		= 130;
	private static final float GAP		= 2;

	protected KingsCrown crown;
	protected Armor armor;

	public WndChooseAbility(final KingsCrown crown, final Armor armor) {
		this(crown,armor, crown == null ? armor.name() : crown.name(), true);
	}

	public WndChooseAbility(final KingsCrown crown, final Armor armor, String title, boolean includeCancel){

		super();
		this.crown = crown;
		this.armor = armor;

		//crown can be null if hero is choosing from armor
		IconTitle titlebar = new IconTitle();
		titlebar.icon( new ItemSprite( crown == null ? armor.image() : crown.image(), null ) );
		titlebar.label( Messages.titleCase(title) );
		titlebar.setRect( 0, 0, WIDTH, 0 );
		add( titlebar );

		RenderedTextBlock body = PixelScene.renderTextBlock( 6 );
		if (crown != null) {
			body.text(Messages.get(this, "message"), WIDTH);
		} else {
			body.text(Messages.get(this, "message_no_crown"), WIDTH);
		}
		body.setPos( titlebar.left(), titlebar.bottom() + GAP );
		add( body );

		float pos = body.bottom() + 3*GAP;
		for (ArmorAbility ability : getArmorAbilities()) {

			RedButton abilityButton = new RedButton(ability.shortDesc(), 6){
				@Override
				protected void onClick() {
					selectAbility(ability);
				}
			};
			abilityButton.leftJustify = true;
			abilityButton.multiline = true;
			abilityButton.setSize(WIDTH-20, abilityButton.reqHeight()+2);
			abilityButton.setRect(0, pos, WIDTH-20, abilityButton.reqHeight()+2);
			add(abilityButton);

			IconButton abilityInfo = new IconButton(Icons.get(Icons.INFO)){
				@Override
				protected void onClick() {
					GameScene.show(getAbilityInfo(ability));
				}
			};
			abilityInfo.setRect(WIDTH-20, abilityButton.top() + (abilityButton.height()-20)/2, 20, 20);
			add(abilityInfo);

			pos = abilityButton.bottom() + GAP;
		}

		if(includeCancel) {
			RedButton cancelButton = new RedButton(Messages.get(this, "cancel")){
				@Override
				protected void onClick() {
					hide();
				}
			};
			cancelButton.setRect(0, pos, WIDTH, 18);
			add(cancelButton);
			pos = cancelButton.bottom() + GAP;
		}

		resize(WIDTH, (int)pos);

	}

	protected Collection<ArmorAbility> getArmorAbilities() {
		return Arrays.asList(hero.heroClass.armorAbilities());
	}

	protected WndInfoArmorAbility getAbilityInfo(ArmorAbility ability) {
		return new WndInfoArmorAbility(ability);
	}

	protected void selectAbility(ArmorAbility ability) {
		String warn = "";
		GameScene.show(new WndOptions( new HeroIcon( ability ),
				Messages.titleCase(ability.name()),
				warn + Messages.get(WndChooseAbility.this, "are_you_sure"),
				Messages.get(WndChooseAbility.this, "yes"),
				Messages.get(WndChooseAbility.this, "no")){

			@Override
			protected void onSelect(int index) {
				hide();
				if (index == 0 && WndChooseAbility.this.parent != null){
					WndChooseAbility.this.hide();
					if (crown != null) {
						crown.upgradeArmor(hero, armor, ability);
					} else {
						new KingsCrown().upgradeArmor(hero, null, ability);
					}
				}
			}
		});
	}

}
