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

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDAction;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Awakening;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Berserk;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BowMasterSkill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Build;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Command;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FirstAidBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HorseRiding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Juggling;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalCombo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Pray;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Preparation;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SharpShooterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SnipersMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SwordAura;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Tackle;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WarriorParry;
import com.shatteredpixel.shatteredpixeldungeon.items.Sheath;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HolyTome;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndActionList;
import com.watabou.input.GameAction;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Visual;

public class ActionIndicator extends Tag {

	Visual primaryVis;
	Visual secondVis;

	public static Action action;
	public static ActionIndicator instance;

	public ActionIndicator() {
		super( 0 );

		instance = this;

		setSize( SIZE, SIZE );
		visible = false;
	}
	
	@Override
	public GameAction keyAction() {
		return SPDAction.TAG_ACTION;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		instance = null;

		longClickListener.destroy();
	}
	
	@Override
	protected synchronized void layout() {
		super.layout();
		
		if (primaryVis != null){
			if (!flipped)   primaryVis.x = x + (SIZE - primaryVis.width()) / 2f + 1;
			else            primaryVis.x = x + width - (SIZE + primaryVis.width()) / 2f - 1;
			primaryVis.y = y + (height - primaryVis.height()) / 2f;
			PixelScene.align(primaryVis);
			if (secondVis != null){
				if (secondVis.width() > 16) secondVis.x = primaryVis.center().x - secondVis.width()/2f;
				else                        secondVis.x = primaryVis.center().x + 8 - secondVis.width();
				if (secondVis instanceof BitmapText){
					//need a special case here for text unfortunately
					secondVis.y = primaryVis.center().y + 8 - ((BitmapText) secondVis).baseLine();
				} else {
					secondVis.y = primaryVis.center().y + 8 - secondVis.height();
				}
				PixelScene.align(secondVis);
			}
		}
	}
	
	private boolean needsRefresh = false;
	
	@Override
	public void update() {
		super.update();

		synchronized (ActionIndicator.class) {
			// auto clear unusable actions
			if (action != null && !action.usable()) clearAction(action);

			if (!visible && action != null) {
				visible = true;
				needsRefresh = true;
				flash();
			} else {
				visible = action != null;
			}

			if (needsRefresh) {
				if (primaryVis != null) {
					primaryVis.destroy();
					primaryVis.killAndErase();
					primaryVis = null;
				}
				if (secondVis != null) {
					secondVis.destroy();
					secondVis.killAndErase();
					secondVis = null;
				}
				if (action != null) {
					primaryVis = action.primaryVisual();
					add(primaryVis);

					secondVis = action.secondaryVisual();
					if (secondVis != null) {
						add(secondVis);
					}

					setColor(action.indicatorColor());
				}

				layout();
				needsRefresh = false;
			}

			if (!Dungeon.hero.ready) {
				if (primaryVis != null) primaryVis.alpha(0.5f);
				if (secondVis != null) secondVis.alpha(0.5f);
			} else {
				if (primaryVis != null) primaryVis.alpha(1f);
				if (secondVis != null) secondVis.alpha(1f);
			}
		}

	}

	@Override
	protected void onClick() {
		super.onClick();
		if (action != null && Dungeon.hero.ready) {
			action.doAction();
		}
	}

	@Override
	protected String hoverText() {
		String text = (action == null ? null : action.actionName());
		if (text != null){
			return Messages.titleCase(text);
		} else {
			return null;
		}
	}

	@Override
	protected boolean onLongClick() {
		GameScene.show(new WndActionList());
		return true;
	}

	private final Button longClickListener = new Button() {
		@Override public GameAction keyAction() { return SPDAction.TAG_CYCLE; }
		@Override protected void onClick()      { GameScene.show(new WndActionList()); }
	};

	public static boolean setAction(Action action){
		if(action == null || !action.usable()) return false;
		synchronized (ActionIndicator.class) {
			ActionIndicator.action = action;
			refresh();
		}
		return true;
	}

	// list of action buffs that we should replace it with.
	public static final Class<?extends Buff>[] actionBuffClasses = new Class[]{
			Preparation.class,
			SnipersMark.class,
			Combo.class,
			Momentum.class,
			MonkEnergy.class,
			Berserk.class,
			MeleeWeapon.Charger.class,
			HolyTome.TomeRecharge.class,
			Tackle.class,
			MagicalCombo.class,
			Sheath.Sheathing.class,
			SwordAura.class,
			Awakening.class,
			Build.class,
			HorseRiding.class,
			Pray.class,
			FirstAidBuff.class,
			Command.class,
			BowMasterSkill.class,
			Juggling.class,
			SharpShooterBuff.class,
			WarriorParry.class
	};
	private static boolean findAction(boolean cycle) {
		if(Dungeon.hero == null) return false;
		if(action == null) cycle = false;
		int start = -1;
		if(cycle) while(++start < actionBuffClasses.length && !actionBuffClasses[start].isInstance(action));

		for(int i = (start+1)%actionBuffClasses.length; i != start && i < actionBuffClasses.length; i++) {
			for(Buff b : Dungeon.hero.buffs(actionBuffClasses[i])) {
				if( b != action && setAction( (Action) b ) ) return true;
			}
			if(cycle && i+1 == actionBuffClasses.length) i = -1;
		}
		return false;
	}

	public static void clearAction(){
		clearAction(null);
	}

	public static void clearAction(Action action){
		synchronized (ActionIndicator.class) {
			if (action == null || ActionIndicator.action == action) {
				ActionIndicator.action = null;
				findAction(false);
			}
		}
	}

	public static void refresh(){
		synchronized (ActionIndicator.class) {
			if (instance != null) {
				instance.needsRefresh = true;
			}
		}
	}

	public interface Action {

		default String actionName() { return Messages.get(this, "action_name"); };

		default int actionIcon(){
			return HeroIcon.NONE;
		}

		//usually just a static icon, unless overridden
		default Visual primaryVisual(){
			return new HeroIcon(this);
		}

		//a smaller visual on the bottom-right, usually a tiny icon or bitmap text
		default Visual secondaryVisual(){
			return null; //no second visual by default
		}

		int indicatorColor();

		void doAction();

		default boolean usable() { return true; }

	}

}
