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

package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArcaneArmor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Berserk;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BowMasterSkill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ChampionEnemy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Daze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Doom;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FireImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FrostImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Fury;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Haste;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Juggling;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LifeLink;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PainKiller;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Pray;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Preparation;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.RadioactiveMutation;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SharpShooterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Shrink;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SnipersMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SoulMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Speed;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Stamina;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.StimPack;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ThunderImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.TimedShrink;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WarriorParry;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.DeathMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.Awake;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.ShadowBlade;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.AuraOfProtection;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.BeamingRay;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.GuidingLight;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HolyMantle;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.LifeLinkSpell;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.ShieldOfLight;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Brute;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.CrystalSpire;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DwarfKing;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Elemental;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollGeomancer;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Necromancer;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Tengu;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.YogDzewa;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.PrismaticImage;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.Rope;
import com.shatteredpixel.shatteredpixeldungeon.items.Sheath;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Bulk;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Brimstone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Flow;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Obfuscation;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Potential;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Swiftness;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Pickaxe;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfSirensSong;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.ElectricityImbue;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.FerretTuft;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.GrindStone;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFireblast;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blazing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Kinetic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sickle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.bow.BowWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.gun.Gun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.ShockingDart;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GeyserTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GnollRockfallTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrimTrap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Languages;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.TargetHealthIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.DungeonSeed;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

public abstract class Char extends Actor {
	
	public int pos = 0;
	
	public CharSprite sprite;
	
	public int HT;
	public int HP;
	
	protected float baseSpeed	= 1;
	protected PathFinder.Path path;

	public int paralysed	    = 0;
	public boolean rooted		= false;
	public boolean flying		= false;
	public int invisible		= 0;

	//these are relative to the hero
	public enum Alignment{
		ENEMY,
		NEUTRAL,
		ALLY
	}
	public Alignment alignment;
	
	public int viewDistance	= 8;
	
	public boolean[] fieldOfView = null;
	
	private LinkedHashSet<Buff> buffs = new LinkedHashSet<>();
	
	@Override
	protected boolean act() {
		if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()){
			fieldOfView = new boolean[Dungeon.level.length()];
		}
		Dungeon.level.updateFieldOfView( this, fieldOfView );

		//throw any items that are on top of an immovable char
		if (properties().contains(Property.IMMOVABLE)){
			throwItems();
		}

		return false;
	}

	protected void throwItems(){
		Heap heap = Dungeon.level.heaps.get( pos );
		if (heap != null && heap.type == Heap.Type.HEAP
				&& !(heap.peek() instanceof Tengu.BombAbility.BombItem)
				&& !(heap.peek() instanceof Tengu.ShockerAbility.ShockerItem)) {
			ArrayList<Integer> candidates = new ArrayList<>();
			for (int n : PathFinder.NEIGHBOURS8){
				if (Dungeon.level.passable[pos+n]){
					candidates.add(pos+n);
				}
			}
			if (!candidates.isEmpty()){
				Dungeon.level.drop( heap.pickUp(), Random.element(candidates) ).sprite.drop( pos );
			}
		}
	}

	public String name(){
		String name = Messages.get(this, "name");
		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.RLETTER)) {
			name = ShatteredPixelDungeon.turnIntoRrrr(name);
		}
		return name;
	}

	public boolean canInteract(Char c){
		if (Dungeon.level.adjacent( pos, c.pos )){
			return true;
		} else if (c instanceof Hero
				&& alignment == Alignment.ALLY
				&& !hasProp(this, Property.IMMOVABLE)
				&& Dungeon.level.distance(pos, c.pos) <= 2*Dungeon.hero.pointsInTalent(Talent.ALLY_WARP, Talent.RK_WARLOCK)){
			return true;
		} else {
			return false;
		}
	}
	
	//swaps places by default
	public boolean interact(Char c){

		//don't allow char to swap onto hazard unless they're flying
		//you can swap onto a hazard though, as you're not the one instigating the swap
		if (!Dungeon.level.passable[pos] && !c.flying){
			return true;
		}

		//can't swap into a space without room
		if (properties().contains(Property.LARGE) && !Dungeon.level.openSpace[c.pos]
			|| c.properties().contains(Property.LARGE) && !Dungeon.level.openSpace[pos]){
			return true;
		}

		//we do a little raw position shuffling here so that the characters are never
		// on the same cell when logic such as occupyCell() is triggered
		int oldPos = pos;
		int newPos = c.pos;

		//can't swap or ally warp if either char is immovable
		if (hasProp(this, Property.IMMOVABLE) || hasProp(c, Property.IMMOVABLE)){
			return true;
		}

		//warp instantly with allies in this case
		if (c == Dungeon.hero && Dungeon.hero.hasTalent(Talent.ALLY_WARP, Talent.RK_WARLOCK)){
			PathFinder.buildDistanceMap(c.pos, BArray.or(Dungeon.level.passable, Dungeon.level.avoid, null));
			if (PathFinder.distance[pos] == Integer.MAX_VALUE){
				return true;
			}
			pos = newPos;
			c.pos = oldPos;
			ScrollOfTeleportation.appear(this, newPos);
			ScrollOfTeleportation.appear(c, oldPos);
			Dungeon.observe();
			GameScene.updateFog();
			return true;
		}

		//can't swap places if one char has restricted movement
		if (paralysed > 0 || c.paralysed > 0 || rooted || c.rooted
				|| buff(Vertigo.class) != null || c.buff(Vertigo.class) != null){
			return true;
		}

		c.pos = oldPos;
		moveSprite( oldPos, newPos );
		move( newPos );

		c.pos = newPos;
		c.sprite.move( newPos, oldPos );
		c.move( oldPos );
		
		c.spend( 1 / c.speed() );

		if (c == Dungeon.hero){
			if (Dungeon.hero.subClass.is(HeroSubClass.FREERUNNER)){
				Buff.affect(Dungeon.hero, Momentum.class).gainStack();
			}

			BowMasterSkill.move();

			Juggling.move();

			Dungeon.hero.justMoved = true;

			Dungeon.hero.busy();
		}
		
		return true;
	}
	
	protected boolean moveSprite( int from, int to ) {
		
		if (sprite.isVisible() && sprite.parent != null && (Dungeon.level.heroFOV[from] || Dungeon.level.heroFOV[to])) {
			sprite.move( from, to );
			return true;
		} else {
			sprite.turnTo(from, to);
			sprite.place( to );
			return true;
		}
	}

	public void hitSound( float pitch ){
		Sample.INSTANCE.play(Assets.Sounds.HIT, 1, pitch);
	}

	public boolean blockSound( float pitch ) {
		return false;
	}
	
	protected static final String POS       = "pos";
	protected static final String TAG_HP    = "HP";
	protected static final String TAG_HT    = "HT";
	protected static final String TAG_SHLD  = "SHLD";
	protected static final String BUFFS	    = "buffs";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		
		super.storeInBundle( bundle );
		
		bundle.put( POS, pos );
		bundle.put( TAG_HP, HP );
		bundle.put( TAG_HT, HT );
		bundle.put( BUFFS, buffs );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		
		super.restoreFromBundle( bundle );
		
		pos = bundle.getInt( POS );
		HP = bundle.getInt( TAG_HP );
		HT = bundle.getInt( TAG_HT );
		
		for (Bundlable b : bundle.getCollection( BUFFS )) {
			if (b != null) {
				((Buff)b).attachTo( this );
			}
		}
	}

	final public boolean attack( Char enemy ){
		if (hero.buff(Awake.awakeTracker.class) != null && (this instanceof Hero || enemy instanceof Hero)) {
			return attack(enemy, 1.2f+0.2f*hero.pointsInTalent(Talent.AWAKE_LIMIT), 0f, 1f);
		} else {
			return attack(enemy, 1f, 0f, 1f);
		}
	}
	
	public boolean attack( Char enemy, float dmgMulti, float dmgBonus, float accMulti ) {

		if (this instanceof Hero && hero.subClass.is(HeroSubClass.EXPLORER)) {
			Rope rope = hero.belongings.getItem(Rope.class);
			KindOfWeapon wep = hero.belongings.attackingWeapon();
			if (rope != null && wep instanceof MeleeWeapon) {
				int weaponReach = wep.reachFactor(hero) + KindOfWeapon.additionalReach();
				int attackReach = weaponReach + rope.quantity();

				boolean[] passable = BArray.not(Dungeon.level.solid, null);
				for (Char ch : Actor.chars()) {
					if (ch != hero) passable[ch.pos] = false;
				}
				//enemy.pos를 중심으로 attackReach거리까지 타일마다 거리를 계산하여 기록
				PathFinder.buildDistanceMap(enemy.pos, passable, attackReach);
				//위에서 만든 거리 목록 중 영웅이 있는 좌표를 주소로 가지는 거리가 무기 기본 사정거리를 넘으면 그만큼 로프를 사용함
				if (PathFinder.distance[hero.pos] > weaponReach) {
					//PathFinder.distance[hero.pos]가 Integer.MAX_VALUE가 될 수는 없기 때문에 이것에 대한 조건 처리는 따로 하지 않는다.
					//왜냐하면 [영웅이 공격을 했다 -> PathFinder.distance[hero.pos]가 공격 거리보다 짧거나 같다]는 의미이기 때문.
					int ropeUse = (PathFinder.distance[hero.pos] - weaponReach);
					if (hero.hasTalent(Talent.DURABLE_ROPE, Talent.RK_EXPLORER)) {
						ropeUse = (int)(ropeUse*(0.9f-0.1f*hero.pointsInTalent(Talent.DURABLE_ROPE, Talent.RK_EXPLORER)));
					}
					rope.quantity(rope.quantity() - ropeUse);
					if (rope.quantity() <= 0) {
						rope.detachAll(hero.belongings.backpack);
					}
					Item.updateQuickslot();
				}
			}
		}
		if (enemy == null) return false;
		
		boolean visibleFight = Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[enemy.pos];

		if (buff(StimPack.class) != null) {
			accMulti *= 1.6f;
			dmgMulti *= 1.3f;
		}

		if (enemy.isInvulnerable(getClass())) {

			if (visibleFight) {
				enemy.sprite.showStatus( CharSprite.POSITIVE, Messages.get(this, "invulnerable") );

				Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY, 1f, Random.Float(0.96f, 1.05f));
			}

			return false;

		} else if (hit( this, enemy, accMulti, false )) {
			boolean isDirectedToHero = false;
			if (enemy != hero && enemy.alignment == Alignment.ALLY && Dungeon.level.heroFOV[enemy.pos] && hero.hasTalent(Talent.CHIVALRY, Talent.THE_PROTECTOR)) {
				enemy = hero;
				isDirectedToHero = true;
			}
			
			int dr = Math.round(enemy.drRoll() * AscensionChallenge.statModifier(enemy));
			
			if (this instanceof Hero){
				Hero h = (Hero)this;
				if (h.belongings.attackingWeapon() instanceof MissileWeapon && !Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.HUNTRESS)
						&& h.subClass.is(HeroSubClass.SNIPER)
						&& !Dungeon.level.adjacent(h.pos, enemy.pos)){
					dr = 0;
				}

				if (h.belongings.attackingWeapon() instanceof BowWeapon.Arrow
						&& Dungeon.hero.buff(BowWeapon.PenetrationShotBuff.class) != null
						&& !Dungeon.level.adjacent(h.pos, enemy.pos)){
					dr = 0;
				}

				if (h.belongings.attackingWeapon() instanceof Gun.Bullet) {
					dr *= ((Gun.Bullet) h.belongings.attackingWeapon()).whatBullet().armorFactor();
				}

				if (hero.buff(ShadowBlade.shadowBladeTracker.class) != null && Random.Int(2) == 0) {
					if (hero.hasTalent(Talent.CRITICAL_SHADOW)) {
						dmgBonus += Random.NormalIntRange(0, 5*hero.pointsInTalent(Talent.CRITICAL_SHADOW));
					}
					if (hero.hasTalent(Talent.HERBAL_SHADOW)) {
						hero.heal(hero.pointsInTalent(Talent.HERBAL_SHADOW));
					}
					dr = 0;
				}

				if (h.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class) != null){
					dr = 0;
				}
			}

			//we use a float here briefly so that we don't have to constantly round while
			// potentially applying various multiplier effects
			float dmg;
			Preparation prep = buff(Preparation.class);
			if (prep != null){
				dmg = prep.damageRoll(this);
				if (this == Dungeon.hero && Dungeon.hero.hasTalent(Talent.BOUNTY_HUNTER, Talent.RK_ASSASSIN)) {
					Buff.affect(Dungeon.hero, Talent.BountyHunterTracker.class, 0.0f);
				}
			} else {
				dmg = damageRoll();
			}

			dmg = dmg*dmgMulti;

			//flat damage bonus is affected by multipliers
			dmg += dmgBonus;

			if (enemy.buff(GuidingLight.Illuminated.class) != null){
				enemy.buff(GuidingLight.Illuminated.class).detach();
				if (this == Dungeon.hero && Dungeon.hero.hasTalent(Talent.SEARING_LIGHT, Talent.KINGS_WISDOM)){
					dmg += 2 + 2*Dungeon.hero.pointsInTalent(Talent.SEARING_LIGHT, Talent.KINGS_WISDOM);
				}
				if (this == Dungeon.hero && Dungeon.hero.hasTalent(Talent.WARDING_LIGHT, Talent.EXTRA_BULK)){
					Buff.affect(Dungeon.hero, Barrier.class).setShield(1+2*Dungeon.hero.pointsInTalent(Talent.WARDING_LIGHT, Talent.EXTRA_BULK));
				}
				if (this != Dungeon.hero && Dungeon.hero.subClass.is(HeroSubClass.PRIEST)){
					enemy.damage(5+Dungeon.hero.lvl, GuidingLight.INSTANCE);
				}
			}

			Berserk berserk = buff(Berserk.class);
			if (berserk != null) dmg = berserk.damageFactor(dmg);

			if (buff( Fury.class ) != null) {
				dmg *= 1.5f;
			}

			if (buff( PowerOfMany.PowerBuff.class) != null){
				if (buff( BeamingRay.BeamingRayBoost.class) != null
					&& buff( BeamingRay.BeamingRayBoost.class).object == enemy.id()){
					dmg *= 1.3f + 0.05f*Dungeon.hero.pointsInTalent(Talent.BEAMING_RAY);
				} else {
					dmg *= 1.25f;
				}
			}

			for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
				dmg *= buff.meleeDamageFactor();
			}

			dmg *= AscensionChallenge.statModifier(this);

			//friendly endure
			Endure.EndureTracker endure = buff(Endure.EndureTracker.class);
			if (endure != null) dmg = endure.damageFactor(dmg);

			//enemy endure
			endure = enemy.buff(Endure.EndureTracker.class);
			if (endure != null){
				dmg = endure.adjustDamageTaken(dmg);
			}

			if (enemy.buff(ScrollOfChallenge.ChallengeArena.class) != null){
				dmg *= 0.67f;
			}

			if (enemy.buff(Shrink.class) != null || enemy.buff(TimedShrink.class) != null) dmg *= 1.4f;

			if (Dungeon.hero.alignment == enemy.alignment
					&& Dungeon.hero.buff(AuraOfProtection.AuraBuff.class) != null
					&& (Dungeon.level.distance(enemy.pos, Dungeon.hero.pos) <= 2 || enemy.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null)){
				dmg *= 0.9f - 0.1f*Dungeon.hero.pointsInTalent(Talent.AURA_OF_PROTECTION, Talent.RK_PALADIN);
			}

			if (enemy.buff(MonkEnergy.MonkAbility.Meditate.MeditateResistance.class) != null){
				dmg *= 0.2f;
			}

			if (enemy.buff(PainKiller.class) != null) {
				dmg *= 0.5f;
			}

			if ( buff(Weakness.class) != null ){
				dmg *= 0.67f;
			}

			if (isDirectedToHero && hero.pointsInTalent(Talent.CHIVALRY, Talent.THE_PROTECTOR) > 1) {
				dmg *= 0.5f;
			}

			if ( buff(SoulMark.class) != null && hero.hasTalent(Talent.MARK_OF_WEAKNESS, Talent.RK_WARLOCK)) {
				if (this.alignment != Alignment.ALLY) {
					dmg *= Math.pow(0.9f, hero.pointsInTalent(Talent.MARK_OF_WEAKNESS, Talent.RK_WARLOCK));
				}
			}

			//characters influenced by aggression deal 1/2 damage to bosses
			if ( enemy.buff(StoneOfAggression.Aggression.class) != null
					&& enemy.alignment == alignment
					&& (Char.hasProp(enemy, Property.BOSS) || Char.hasProp(enemy, Property.MINIBOSS))){
				dmg *= 0.5f;
				//yog-dzewa specifically takes 1/4 damage
				if (enemy instanceof YogDzewa){
					dmg *= 0.5f;
				}
			}
			
			int effectiveDamage = enemy.defenseProc( this, Math.round(dmg) );
			//do not trigger on-hit logic if defenseProc returned a negative value
			if (effectiveDamage >= 0) {
				effectiveDamage = Math.max(effectiveDamage - dr, 0);

				if (enemy.buff(Viscosity.ViscosityTracker.class) != null) {
					effectiveDamage = enemy.buff(Viscosity.ViscosityTracker.class).deferDamage(effectiveDamage);
					enemy.buff(Viscosity.ViscosityTracker.class).detach();
				}

				//vulnerable specifically applies after armor reductions
				if (enemy.buff(Vulnerable.class) != null) {
					effectiveDamage *= 1.33f;
				}

				effectiveDamage = attackProc(enemy, effectiveDamage);
			}
			if (visibleFight) {
				if (effectiveDamage > 0 && enemy.buff(WarriorParry.BlockTrock.class) == null || !enemy.blockSound(Random.Float(0.96f, 1.05f))) {
					hitSound(Random.Float(0.87f, 1.15f));
				}
			}

			// If the enemy is already dead, interrupt the attack.
			// This matters as defence procs can sometimes inflict self-damage, such as armor glyphs.
			if (!enemy.isAlive()){
				return true;
			}

			if (Dungeon.isChallenged(Challenges.NO_ACCURACY)){
				effectiveDamage *= GameMath.gate(0.75f,
						acuRoll(this, attackSkill( enemy ), accMulti)/
								defRoll(this, enemy, enemy.defenseSkill(this), accMulti), 1.25f);
			}

			if (enemy.buff(WarriorParry.BlockTrock.class) != null && effectiveDamage >= 0){
				enemy.sprite.emitter().burst( Speck.factory( Speck.FORGE ), 15 );
				SpellSprite.show(enemy, SpellSprite.BLOCK, 2f, 2f, 2f);
				Buff.affect(enemy, Barrier.class).setShield(Math.round(effectiveDamage*1.25f));
				hero.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString(Math.round(effectiveDamage*1.25f)), FloatingText.SHIELDING );
				enemy.buff(WarriorParry.BlockTrock.class).triggered = true;
			} else {
				enemy.damage(effectiveDamage, this);
			}

			if (buff(FireImbue.class) != null)  buff(FireImbue.class).proc(enemy);
			if (buff(FrostImbue.class) != null) buff(FrostImbue.class).proc(enemy);
			if (buff(ThunderImbue.class) != null) buff(ThunderImbue.class).proc(enemy, (int)dmg);
			if (buff(ElectricityImbue.ElectricityImbueBuff.class) != null) buff(ElectricityImbue.ElectricityImbueBuff.class).proc(enemy, (int)dmg);

			SharpShooterBuff.channel(this, enemy, effectiveDamage);

			if (enemy.isAlive() && enemy.alignment != alignment && prep != null && prep.canKO(enemy)){
				enemy.HP = 0;
				if (enemy.buff(Brute.BruteRage.class) != null){
					enemy.buff(Brute.BruteRage.class).detach();
				}
				if (!enemy.isAlive()) {
					enemy.die(this);
				} else {
					//helps with triggering any on-damage effects that need to activate
					enemy.damage(-1, this);
					DeathMark.processFearTheReaper(enemy);
				}
				if (enemy.sprite != null) {
					enemy.sprite.showStatus(CharSprite.NEGATIVE, Messages.get(Preparation.class, "assassinated"));
				}
				if (Random.Float() < hero.pointsInTalent(Talent.ENERGY_DRAW, Talent.RK_ASSASSIN)/3f) {
					CloakOfShadows cloak = hero.belongings.getItem(CloakOfShadows.class);
					if (cloak != null) {
						cloak.directCharge(1);
						ScrollOfRecharging.charge(Dungeon.hero);
						SpellSprite.show(hero, SpellSprite.CHARGE);
					}
				}
			}

			Talent.CombinedLethalityAbilityTracker combinedLethality = buff(Talent.CombinedLethalityAbilityTracker.class);
			if (combinedLethality != null && this instanceof Hero && ((Hero) this).belongings.attackingWeapon() instanceof MeleeWeapon && combinedLethality.weapon != ((Hero) this).belongings.attackingWeapon()){
				if ( enemy.isAlive() && enemy.alignment != alignment && !Char.hasProp(enemy, Property.BOSS)
						&& !Char.hasProp(enemy, Property.MINIBOSS) &&
						(enemy.HP/(float)enemy.HT) <= 0.4f*((Hero)this).pointsInTalent(Talent.COMBINED_LETHALITY, Talent.RK_CHAMPION)/3f) {
					enemy.HP = 0;
					if (enemy.buff(Brute.BruteRage.class) != null){
						enemy.buff(Brute.BruteRage.class).detach();
					}
					if (!enemy.isAlive()) {
						enemy.die(this);
					} else {
						//helps with triggering any on-damage effects that need to activate
						enemy.damage(-1, this);
						DeathMark.processFearTheReaper(enemy);
					}
					if (enemy.sprite != null) {
						enemy.sprite.showStatus(CharSprite.NEGATIVE, Messages.get(Talent.CombinedLethalityAbilityTracker.class, "executed"));
					}
				}
				combinedLethality.detach();
			}

			Talent.JudgementTracker judgement = enemy.buff(Talent.JudgementTracker.class);
			if (judgement != null) {
				if ( enemy.isAlive() && enemy.alignment != alignment && !Char.hasProp(enemy, Property.BOSS)
						&& !Char.hasProp(enemy, Property.MINIBOSS) && this instanceof Hero &&
						(enemy.HP/(float)enemy.HT) <= 0.4f*((Hero)this).pointsInTalent(Talent.JUDGEMENT)/3f) {
					enemy.HP = 0;
					if (!enemy.isAlive()) {
						enemy.die(this);
					} else {
						//helps with triggering any on-damage effects that need to activate
						enemy.damage(-1, this);
						DeathMark.processFearTheReaper(enemy);
					}
					if (enemy.sprite != null) {
						enemy.sprite.showStatus(CharSprite.NEGATIVE, Messages.get(Talent.JudgementTracker.class, "executed"));
						ThunderImbue.thunderEffect(enemy.sprite);
					}
				}
				judgement.detach();
			}

			if (enemy.sprite != null) {
				enemy.sprite.bloodBurstA(sprite.center(), effectiveDamage);
				enemy.sprite.flash();
			}

			if (!enemy.isAlive() && visibleFight) {
				if (enemy == Dungeon.hero) {
					
					if (this == Dungeon.hero) {
						return true;
					}

					if (this instanceof WandOfLivingEarth.EarthGuardian
							|| this instanceof MirrorImage || this instanceof PrismaticImage){
						Badges.validateDeathFromFriendlyMagic();
					}
					Dungeon.fail( this );
					GLog.n( Messages.capitalize(Messages.get(Char.class, "kill", name())) );
					
				} else if (this == Dungeon.hero) {
					GLog.i( Messages.capitalize(Messages.get(Char.class, "defeat", enemy.name())) );
				}
			}
			
			return true;
			
		} else {

			if (enemy instanceof Hero) {
				if (hero.pointsInTalent(Talent.SWIFT_MOVEMENT, Talent.RK_FIGHTER) == 3) {
					Buff.prolong(hero, Invisibility.class, 1.0001f);
				}
				if (Random.Int(5) < hero.pointsInTalent(Talent.COUNTER_ATTACK, Talent.RK_FIGHTER)) {
					Buff.affect(hero, Talent.CounterAttackTracker.class);
				}
				if (hero.hasTalent(Talent.QUICK_PREP, Talent.RK_FREERUNNER)) {
					Momentum momentum = hero.buff(Momentum.class);
					if (momentum != null) {
						momentum.quickPrep(hero.pointsInTalent(Talent.QUICK_PREP, Talent.RK_FREERUNNER));
					}
				}
			}

			if (enemy.sprite != null){
				if (tuftDodged){
					//dooking is a playful sound Ferrets can make, like low pitched chirping
					// I doubt this will translate, so it's only in English
					if (Messages.lang() == Languages.ENGLISH && Random.Int(10) == 0) {
						enemy.sprite.showStatusWithIcon(CharSprite.NEUTRAL, "dooked", FloatingText.TUFT);
					} else {
						enemy.sprite.showStatusWithIcon(CharSprite.NEUTRAL, enemy.defenseVerb(), FloatingText.TUFT);
					}
				} else {
					enemy.sprite.showStatus(CharSprite.NEUTRAL, enemy.defenseVerb());
				}
			}
			tuftDodged = false;
			if (visibleFight) {
				//TODO enemy.defenseSound? currently miss plays for monks/crab even when they parry
				Sample.INSTANCE.play(Assets.Sounds.MISS);
			}
			
			return false;
			
		}
	}

	public static int INFINITE_ACCURACY = 1_000_000;
	public static int INFINITE_EVASION = 1_000_000;

	public static float acuRoll(Char attacker, float acuStat, float accMulti){
		float acuRoll = Random.Float( acuStat );
		if (attacker.buff(Bless.class) != null) acuRoll *= 1.25f;
		if (attacker.buff(  Hex.class) != null) acuRoll *= 0.8f;
		if (attacker.buff( Daze.class) != null) acuRoll *= 0.5f;
		if (attacker.buff(Shrink.class)!= null || attacker.buff(TimedShrink.class)!= null) acuRoll *= 0.6f;
		for (ChampionEnemy buff : attacker.buffs(ChampionEnemy.class)){
			acuRoll *= buff.evasionAndAccuracyFactor();
		}
		return acuRoll*accMulti;
	}

	public static float defRoll(Char attacker, Char defender, float defStat, float accMulti) {
		float defRoll = Random.Float( defStat );
		if (defender.buff(Bless.class) != null) defRoll *= 1.25f;
		if (defender.buff(  Hex.class) != null) defRoll *= 0.8f;
		if (defender.buff( Daze.class) != null) defRoll *= 0.5f;
		if (defender.buff(Shrink.class)!= null || defender.buff(TimedShrink.class)!= null) defRoll *= 0.8f;
		for (ChampionEnemy buff : defender.buffs(ChampionEnemy.class)){
			defRoll *= buff.evasionAndAccuracyFactor();
		}
		return defRoll;
	}

	final public static boolean hit( Char attacker, Char defender, boolean magic ) {
		return hit(attacker, defender, magic ? 2f : 1f, magic);
	}

	public static boolean hit( Char attacker, Char defender, float accMulti, boolean magic ) {
		float acuStat = attacker.attackSkill( defender );
		float defStat = defender.defenseSkill( attacker );

		if (defender instanceof Hero && ((Hero) defender).damageInterrupt){
			((Hero) defender).interrupt();
		}

		//invisible chars always hit (for the hero this is surprise attacking)
		if (attacker.invisible > 0 && attacker.canSurpriseAttack()){
			acuStat = INFINITE_ACCURACY;
		}

		if (defender.buff(MonkEnergy.MonkAbility.Focus.FocusBuff.class) != null){
			defStat = INFINITE_EVASION;
		}

		//if accuracy or evasion are large enough, treat them as infinite.
		//note that infinite evasion beats infinite accuracy
		if (defStat >= INFINITE_EVASION){
			return false;
		} else if (acuStat >= INFINITE_ACCURACY){
			return true;
		}

		float acuRoll = Random.Float( acuStat );
		if (attacker.buff(Bless.class) != null) acuRoll *= 1.25f;
		if (attacker.buff(  Hex.class) != null) acuRoll *= 0.8f;
		if (attacker.buff( Daze.class) != null) acuRoll *= 0.5f;
		if (attacker.buff(Shrink.class)!= null || attacker.buff(TimedShrink.class)!= null) acuRoll *= 0.6f;
		for (ChampionEnemy buff : attacker.buffs(ChampionEnemy.class)){
			acuRoll *= buff.evasionAndAccuracyFactor();
		}
		acuRoll *= AscensionChallenge.statModifier(attacker);
		if (!Dungeon.hero.heroClass.is(HeroClass.CLERIC)
				&& Dungeon.hero.hasTalent(Talent.BLESS, Talent.POWER_WITHIN)
				&& attacker.alignment == Alignment.ALLY){
			// + 3%/5%
			acuRoll *= 1.01f + 0.02f*Dungeon.hero.pointsInTalent(Talent.BLESS, Talent.POWER_WITHIN);
		}
		acuRoll *= accMulti;

		float defRoll = Random.Float( defStat );
		if (defender.buff(Bless.class) != null) defRoll *= 1.25f;
		if (defender.buff(  Hex.class) != null) defRoll *= 0.8f;
		if (defender.buff( Daze.class) != null) defRoll *= 0.5f;
		if (defender.buff(Shrink.class)!= null || defender.buff(TimedShrink.class)!= null) defRoll *= 0.8f;
		for (ChampionEnemy buff : defender.buffs(ChampionEnemy.class)){
			defRoll *= buff.evasionAndAccuracyFactor();
		}

		if (Dungeon.isChallenged(Challenges.NO_ACCURACY)){
			return true;
		}

		defRoll *= AscensionChallenge.statModifier(defender);
		if (!Dungeon.hero.heroClass.is(HeroClass.CLERIC)
				&& Dungeon.hero.hasTalent(Talent.BLESS, Talent.POWER_WITHIN)
				&& defender.alignment == Alignment.ALLY){
			// + 3%/5%
			defRoll *= 1.01f + 0.02f*Dungeon.hero.pointsInTalent(Talent.BLESS, Talent.POWER_WITHIN);
		}

		if (defRoll < acuRoll && (defRoll*FerretTuft.evasionMultiplier()) >= acuRoll){
			tuftDodged = true;
		}
		defRoll *= FerretTuft.evasionMultiplier();

		return acuRoll >= defRoll;
	}

	//TODO this is messy and hacky atm, should consider standardizing this so we can have many 'dodge reasons'
	private static boolean tuftDodged = false;

	public int attackSkill( Char target ) {
		return 0;
	}
	
	public int defenseSkill( Char enemy ) {
		return 0;
	}
	
	public String defenseVerb() {
		return Messages.get(this, "def_verb");
	}
	
	public int drRoll() {
		int dr = 0;

		dr += Random.NormalIntRange( 0 , Barkskin.currentLevel(this) );

		dr -= GrindStone.drRoll();

		return dr;
	}
	
	public int damageRoll() {
		return 1;
	}
	
	//TODO it would be nice to have a pre-armor and post-armor proc.
	// atm attack is always post-armor and defence is already pre-armor
	
	public int attackProc( Char enemy, int damage ) {
		for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
			buff.onAttackProc( enemy );
		}
		return damage;
	}
	
	public int defenseProc( Char enemy, int damage ) {

		Earthroot.Armor armor = buff( Earthroot.Armor.class );
		if (armor != null) {
			damage = armor.absorb( damage );
		}

		ShieldOfLight.ShieldOfLightTracker shield = buff( ShieldOfLight.ShieldOfLightTracker.class);
		if (shield != null && shield.object == enemy.id()){
			int min = 1 + Dungeon.hero.pointsInTalent(Talent.SHIELD_OF_LIGHT, Talent.NOBLE_CAUSE);
			damage -= Random.NormalIntRange(min, 2*min);
			damage = Math.max(damage, 0);
		} else if (this == Dungeon.hero
				&& Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.hasTalent(Talent.SHIELD_OF_LIGHT, Talent.NOBLE_CAUSE)
				&& TargetHealthIndicator.instance.target() == enemy){
			//33/50%
			if (Random.Int(6) < 1+Dungeon.hero.pointsInTalent(Talent.SHIELD_OF_LIGHT, Talent.NOBLE_CAUSE)){
				damage -= 1;
			}
		}

		// hero and pris images skip this as they already benefit from hero's armor glyph proc
		if (!(this instanceof Hero || this instanceof PrismaticImage)) {
			if (Dungeon.hero.alignment == alignment && Dungeon.hero.belongings.armor() != null
					&& Dungeon.hero.buff(AuraOfProtection.AuraBuff.class) != null
					&& (Dungeon.level.distance(pos, Dungeon.hero.pos) <= 2 || buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null)) {
				damage = Dungeon.hero.belongings.armor().proc( enemy, this, damage );
			}
		}

		return damage;
	}

	//Returns the level a glyph is at for a char, or -1 if they are not benefitting from that glyph
	//This function is needed as (unlike enchantments) many glyphs trigger in a variety of cases
	public int glyphLevel(Class<? extends Armor.Glyph> cls){
		if (Dungeon.hero != null && Dungeon.level != null
				&& this != Dungeon.hero && Dungeon.hero.alignment == alignment
				&& Dungeon.hero.buff(AuraOfProtection.AuraBuff.class) != null
				&& (Dungeon.level.distance(pos, Dungeon.hero.pos) <= 2 || buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null)) {
			return Dungeon.hero.glyphLevel(cls);
		} else {
			return -1;
		}
	}
	
	public float speed() {
		float speed = baseSpeed;
		if ( buff( Cripple.class ) != null ) speed /= 2f;
		if ( buff( Stamina.class ) != null) speed *= 1.5f;
		if ( buff( Adrenaline.class ) != null) speed *= 2f;
		if ( buff( Haste.class ) != null) speed *= 3f;
		if ( buff( Dread.class ) != null) speed *= 2f;

		speed *= Swiftness.speedBoost(this, glyphLevel(Swiftness.class));
		speed *= Flow.speedBoost(this, glyphLevel(Flow.class));
		speed *= Bulk.speedBoost(this, glyphLevel(Bulk.class));

		if ( this.alignment != Alignment.ALLY
				&& buff( Ooze.class ) != null
				&& hero.hasTalent(Talent.STICKY_OOZE, Talent.RK_RESEARCHER)) speed *= 1-0.1f*hero.pointsInTalent(Talent.STICKY_OOZE, Talent.RK_RESEARCHER);
		return speed;
	}

	//currently only used by invisible chars, or by the hero
	public boolean canSurpriseAttack(){
		return true;
	}
	
	//used so that buffs(Shieldbuff.class) isn't called every time unnecessarily
	private int cachedShield = 0;
	public boolean needsShieldUpdate = true;
	
	public int shielding(){
		if (!needsShieldUpdate){
			return cachedShield;
		}
		
		cachedShield = 0;
		for (ShieldBuff s : buffs(ShieldBuff.class)){
			cachedShield += s.shielding();
		}
		needsShieldUpdate = false;
		return cachedShield;
	}
	
	public void damage( int dmg, Object src ) {
		
		if (!isAlive() || dmg < 0) {
			return;
		}

		if(isInvulnerable(src.getClass())){
			sprite.showStatus(CharSprite.POSITIVE, Messages.get(this, "invulnerable"));
			return;
		}

		if (!(src instanceof LifeLink || src instanceof Hunger) && buff(LifeLink.class) != null){
			HashSet<LifeLink> links = buffs(LifeLink.class);
			for (LifeLink link : links.toArray(new LifeLink[0])){
				if (Actor.findById(link.object) == null){
					links.remove(link);
					link.detach();
				}
			}
			dmg = (int)Math.ceil(dmg / (float)(links.size()+1));
			for (LifeLink link : links){
				Char ch = (Char)Actor.findById(link.object);
				if (ch != null) {
					ch.damage(dmg, link);
					if (!ch.isAlive()) {
						link.detach();
						if (ch == Dungeon.hero){
							Badges.validateDeathFromFriendlyMagic();
							Dungeon.fail(src);
							GLog.n( Messages.get(LifeLink.class, "ondeath") );
						}
					}
				}
			}
		}

		//temporarily assign to a float to avoid rounding a bunch
		float damage = dmg;

		//if dmg is from a character we already reduced it in Char.attack
		if (!(src instanceof Char)) {
			if (Dungeon.hero.alignment == alignment
					&& Dungeon.hero.buff(AuraOfProtection.AuraBuff.class) != null
					&& (Dungeon.level.distance(pos, Dungeon.hero.pos) <= 2 || buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null)) {
				damage *= 0.9f - 0.1f*Dungeon.hero.pointsInTalent(Talent.AURA_OF_PROTECTION, Talent.RK_PALADIN);
			}
		}

		if (buff(PowerOfMany.PowerBuff.class) != null){
			if (buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null){
				damage *= 0.70f - 0.05f*Dungeon.hero.pointsInTalent(Talent.LIFE_LINK);
			} else {
				damage *= 0.75f;
			}
		}

		Terror t = buff(Terror.class);
		if (t != null){
			t.recover();
		}
		Dread d = buff(Dread.class);
		if (d != null){
			d.recover();
		}
		Charm c = buff(Charm.class);
		if (c != null){
			c.recover(src);
		}
		if (this.buff(Frost.class) != null){
			Buff.detach( this, Frost.class );
		}
		if (this.buff(MagicalSleep.class) != null){
			Buff.detach(this, MagicalSleep.class);
		}
		if (this.buff(Doom.class) != null && !isImmune(Doom.class)){
			damage *= 1.67f;
		}
		if (alignment != Alignment.ALLY && this.buff(DeathMark.DeathMarkTracker.class) != null){
			damage *= 1.25f;
		}

		if (buff(Sickle.HarvestBleedTracker.class) != null){
			buff(Sickle.HarvestBleedTracker.class).detach();

			if (!isImmune(Bleeding.class)){
				Bleeding b = buff(Bleeding.class);
				if (b == null){
					b = new Bleeding();
				}
				b.announced = false;
				b.set(dmg, Sickle.HarvestBleedTracker.class);
				b.attachTo(this);
				sprite.showStatus(CharSprite.WARNING, Messages.titleCase(b.name()) + " " + (int)b.level());
				return;
			}
		}

		if (buff(HolyMantle.HolyMantleBuff.class) != null) {
			damage = HolyMantle.HolyMantleBuff.DamageReduction(damage);
			buff(HolyMantle.HolyMantleBuff.class).detach();
		}

		Class<?> srcClass = src.getClass();
		if (isImmune( srcClass )) {
			damage = 0;
		} else {
			damage *= resist( srcClass );
		}

		if (this instanceof Hero && Dungeon.isChallenged(Challenges.UNSTABLE_DAMAGE)){
			dmg *= Random.Float(0.5f, 2f);
		}

		dmg = Math.round(damage);

		//we ceil these specifically to favor the player vs. champ dmg reduction
		// most important vs. giant champions in the earlygame
		for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
			dmg = (int) Math.ceil(dmg * buff.damageTakenFactor());
		}
		
		//TODO improve this when I have proper damage source logic
		if (AntiMagic.RESISTS.contains(src.getClass())){
			dmg -= AntiMagic.drRoll(this, glyphLevel(AntiMagic.class));
			if (buff(ArcaneArmor.class) != null) {
				dmg -= Random.NormalIntRange(0, buff(ArcaneArmor.class).level());
			}
			if (dmg < 0) dmg = 0;
		}

		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.CAPITALISM)){
			Gold gold = new Gold();
			gold.quantity(Math.max(1, Random.Int(25, 500)*dmg/100));
			gold.doPickUp(hero, pos);
			hero.spendAndNext( -1F );
		}
		
		if (buff( Paralysis.class ) != null) {
			buff( Paralysis.class ).processDamage(dmg);
		}

		BrokenSeal.WarriorShield shield = buff(BrokenSeal.WarriorShield.class);
		if (!(src instanceof Hunger)
				&& dmg > 0
				//either HP is already half or below (ignoring shield)
				// or the hit will reduce it to half or below
				&& (HP <= HT/2 || HP + shielding() - dmg <= HT/2)
				&& shield != null && !shield.coolingDown()){
			sprite.showStatusWithIcon(CharSprite.POSITIVE, Integer.toString(buff(BrokenSeal.WarriorShield.class).maxShield()), FloatingText.SHIELDING);
			shield.activate();
		}

		int shielded = dmg;
		dmg = ShieldBuff.processDamage(this, dmg, src);
		shielded -= dmg;
		HP -= dmg;

		if (HP > 0 && buff(Grim.GrimTracker.class) != null){

			float finalChance = buff(Grim.GrimTracker.class).maxChance;
			finalChance *= (float)Math.pow( ((HT - HP) / (float)HT), 2);

			if (Random.Float() < finalChance) {
				int extraDmg = Math.round(HP*resist(Grim.class));
				dmg += extraDmg;
				HP -= extraDmg;

				sprite.emitter().burst( ShadowParticle.UP, 5 );
				if (!isAlive() && buff(Grim.GrimTracker.class).qualifiesForBadge){
					Badges.validateGrimWeapon();
				}
			}
		}

		if (HP < 0 && src instanceof Char && alignment == Alignment.ENEMY){
			if (((Char) src).buff(Kinetic.KineticTracker.class) != null){
				int dmgToAdd = -HP;
				dmgToAdd -= ((Char) src).buff(Kinetic.KineticTracker.class).conservedDamage;
				dmgToAdd = Math.round(dmgToAdd * Weapon.Enchantment.genericProcChanceMultiplier((Char) src));
				if (dmgToAdd > 0) {
					Buff.affect((Char) src, Kinetic.ConservedDamage.class).setBonus(dmgToAdd);
				}
				((Char) src).buff(Kinetic.KineticTracker.class).detach();
			}
		}
		
		if (sprite != null) {
			String dmgText = Integer.toString(dmg + shielded);
			if (src == hero && hero.buff(Sheath.CriticalAttack.class) != null) {
				dmgText += "!";
			}
			//defaults to normal damage icon if no other ones apply
			int                                                         icon = FloatingText.PHYS_DMG;
			if (NO_ARMOR_PHYSICAL_SOURCES.contains(src.getClass()))     icon = FloatingText.PHYS_DMG_NO_BLOCK;
			if (AntiMagic.RESISTS.contains(src.getClass()))             icon = FloatingText.MAGIC_DMG;
			if (src instanceof Pickaxe)                                 icon = FloatingText.PICK_DMG;

			//special case for sniper when using ranged attacks
			if (src == Dungeon.hero
					&& Dungeon.hero.subClass.is(HeroSubClass.SNIPER)
					&& !Dungeon.level.adjacent(Dungeon.hero.pos, pos)
					&& Dungeon.hero.belongings.attackingWeapon() instanceof MissileWeapon){
				icon = FloatingText.PHYS_DMG_NO_BLOCK;
			}

			if (src == Dungeon.hero
					&& Dungeon.hero.buff(BowWeapon.PenetrationShotBuff.class) != null
					&& !Dungeon.level.adjacent(Dungeon.hero.pos, pos)
					&& Dungeon.hero.belongings.attackingWeapon() instanceof BowWeapon.Arrow){
				icon = FloatingText.PHYS_DMG_NO_BLOCK;
			}

			//special case for monk using unarmed abilities
			if (src == Dungeon.hero
					&& Dungeon.hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class) != null){
				icon = FloatingText.PHYS_DMG_NO_BLOCK;
			}

			if (src instanceof Hunger)                                  icon = FloatingText.HUNGER;
			if (src instanceof Burning)                                 icon = FloatingText.BURNING;
			if (src instanceof Chill || src instanceof Frost)           icon = FloatingText.FROST;
			if (src instanceof GeyserTrap || src instanceof StormCloud) icon = FloatingText.WATER;
			if (src instanceof Burning)                                 icon = FloatingText.BURNING;
			if (src instanceof Electricity)                             icon = FloatingText.SHOCKING;
			if (src instanceof Bleeding)                                icon = FloatingText.BLEEDING;
			if (src instanceof ToxicGas)                                icon = FloatingText.TOXIC;
			if (src instanceof Corrosion)                               icon = FloatingText.CORROSION;
			if (src instanceof Poison)                                  icon = FloatingText.POISON;
			if (src instanceof Ooze)                                    icon = FloatingText.OOZE;
			if (src instanceof Viscosity.DeferedDamage)                 icon = FloatingText.DEFERRED;
			if (src instanceof Corruption)                              icon = FloatingText.CORRUPTION;
			if (src instanceof AscensionChallenge)                      icon = FloatingText.AMULET;
			if (src instanceof RadioactiveMutation)                     icon = FloatingText.RADIOACTIVE;

			sprite.showStatusWithIcon(CharSprite.NEGATIVE, dmgText, icon);
		}

		if (HP < 0) HP = 0;

		if (!isAlive()) {
			if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.ALLIES) && Random.Int(3) == 0 && src instanceof Hero){
				if (!isImmune(ScrollOfSirensSong.Enthralled.class)){
					HP = HT;
					AllyBuff.affectAndLoot((Mob) this, hero, ScrollOfSirensSong.Enthralled.class);
				} else {
					Buff.affect( this, Charm.class, Charm.DURATION ).object = hero.id();
					die(src);
				}
				sprite.centerEmitter().burst( Speck.factory( Speck.HEART ), 10 );
			} else {
				die( src );
			}
		} else if (HP == 0 && buff(DeathMark.DeathMarkTracker.class) != null){
			DeathMark.processFearTheReaper(this);
		}
	}

	//these are misc. sources of physical damage which do not apply armor, they get a different icon
	private static HashSet<Class> NO_ARMOR_PHYSICAL_SOURCES = new HashSet<>();
	{
		NO_ARMOR_PHYSICAL_SOURCES.add(CrystalSpire.SpireSpike.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(GnollGeomancer.Boulder.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(GnollGeomancer.GnollRockFall.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(GnollRockfallTrap.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(DwarfKing.KingDamager.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(DwarfKing.Summoning.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(LifeLink.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(Chasm.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(WandOfBlastWave.Knockback.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(Heap.class); //damage from wraiths attempting to spawn from heaps
		NO_ARMOR_PHYSICAL_SOURCES.add(Necromancer.SummoningBlockDamage.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(DriedRose.GhostHero.NoRoseDamage.class);
	}
	
	public void destroy() {
		HP = 0;
		Actor.remove( this );

		for (Char ch : Actor.chars().toArray(new Char[0])){
			if (ch.buff(Charm.class) != null && ch.buff(Charm.class).object == id()){
				ch.buff(Charm.class).detach();
			}
			if (ch.buff(Dread.class) != null && ch.buff(Dread.class).object == id()){
				ch.buff(Dread.class).detach();
			}
			if (ch.buff(Terror.class) != null && ch.buff(Terror.class).object == id()){
				ch.buff(Terror.class).detach();
			}
			if (ch.buff(SnipersMark.class) != null && ch.buff(SnipersMark.class).object == id()){
				ch.buff(SnipersMark.class).detach();
			}
			if (ch.buff(Talent.FollowupStrikeTracker.class) != null
					&& ch.buff(Talent.FollowupStrikeTracker.class).object == id()){
				ch.buff(Talent.FollowupStrikeTracker.class).detach();
			}
			if (ch.buff(Talent.DeadlyFollowupTracker.class) != null
					&& ch.buff(Talent.DeadlyFollowupTracker.class).object == id()){
				ch.buff(Talent.DeadlyFollowupTracker.class).detach();
			}
			if (ch.buff(Talent.FollowupShootTracker.class) != null
					&& ch.buff(Talent.FollowupShootTracker.class).object == id()){
				ch.buff(Talent.FollowupShootTracker.class).detach();
			}
		}
	}
	
	public void die( Object src ) {
		destroy();
		if (src != Chasm.class) {
			sprite.die();
			if (!flying && Dungeon.level != null && sprite instanceof MobSprite && Dungeon.level.map[pos] == Terrain.CHASM){
				((MobSprite) sprite).fall();
			}
		}
	}

	//we cache this info to prevent having to call buff(...) in isAlive.
	//This is relevant because we call isAlive during drawing, which has both performance
	//and thread coordination implications
	public boolean deathMarked = false;
	
	public boolean isAlive() {
		return HP > 0 || deathMarked;
	}

	public boolean isActive() {
		return isAlive();
	}

	@Override
	protected void spendConstant(float time) {
		TimekeepersHourglass.timeFreeze freeze = buff(TimekeepersHourglass.timeFreeze.class);
		if (freeze != null) {
			freeze.processTime(time);
			return;
		}

		Swiftthistle.TimeBubble bubble = buff(Swiftthistle.TimeBubble.class);
		if (bubble != null){
			bubble.processTime(time);
			return;
		}

		super.spendConstant(time);
	}

	@Override
	protected void spend( float time ) {

		float timeScale = 1f;
		if (buff( Slow.class ) != null) {
			timeScale *= 0.5f;
			//slowed and chilled do not stack
		} else if (buff( Chill.class ) != null) {
			timeScale *= buff( Chill.class ).speedFactor();
		}
		if (buff( Speed.class ) != null) {
			timeScale *= 2.0f;
		}
		
		super.spend( time / timeScale );
	}
	
	public synchronized LinkedHashSet<Buff> buffs() {
		return new LinkedHashSet<>(buffs);
	}
	
	@SuppressWarnings("unchecked")
	//returns all buffs assignable from the given buff class
	public synchronized <T extends Buff> HashSet<T> buffs( Class<T> c ) {
		HashSet<T> filtered = new HashSet<>();
		for (Buff b : buffs) {
			if (c.isInstance( b )) {
				filtered.add( (T)b );
			}
		}
		return filtered;
	}

	public synchronized final <T extends Object> T buff(Class<T> c) {
		return buff(c, true);
	}

	@SuppressWarnings("unchecked")
	//returns an instance of the specific buff class, if it exists. Not just assignable
	public synchronized <T extends Object> T buff( Class<T> c, boolean matchClass ) {
		for (Buff b : buffs) {
			if (matchClass ? b.getClass() == c: c.isAssignableFrom(b.getClass())) {
				return (T)b;
			}
			if (c.isInterface()){
				Class[] interfaces = b.getClass().getInterfaces();
				for (Class clazz : interfaces){
					if (clazz.isAssignableFrom(c)){
						return (T)b;
					}
				}
			}
		}
		return null;
	}
	public synchronized <T extends Buff> T virtualBuff( Class<T> c) {
		return buff(c, false);
	}

	public synchronized boolean isCharmedBy( Char ch ) {
		int chID = ch.id();
		for (Buff b : buffs) {
			if (b instanceof Charm && ((Charm)b).object == chID) {
				return true;
			}
		}
		return false;
	}

	public synchronized boolean add( Buff buff ) {

		if (buff(PotionOfCleansing.Cleanse.class) != null) { //cleansing buff
			if (buff.type == Buff.buffType.NEGATIVE
					&& !(buff instanceof AllyBuff)
					&& !(buff instanceof LostInventory)){
				return false;
			}
		}

		if (sprite != null && buff(Challenge.SpectatorFreeze.class) != null){
			return false; //can't add buffs while frozen and game is loaded
		}

		buffs.add( buff );
		if (Actor.chars().contains(this)) Actor.add( buff );

		if (sprite != null && buff.announced) {
			switch (buff.type) {
				case POSITIVE:
					sprite.showStatus(CharSprite.POSITIVE, Messages.titleCase(buff.name()));
					break;
				case NEGATIVE:
					sprite.showStatus(CharSprite.WARNING, Messages.titleCase(buff.name()));
					break;
				case NEUTRAL:
				default:
					sprite.showStatus(CharSprite.NEUTRAL, Messages.titleCase(buff.name()));
					break;
			}
		}

		return true;

	}
	
	public synchronized boolean remove( Buff buff ) {
		
		buffs.remove( buff );
		Actor.remove( buff );

		return true;
	}
	
	public synchronized void remove( Class<? extends Buff> buffClass ) {
		for (Buff buff : buffs( buffClass )) {
			remove( buff );
		}
	}
	
	@Override
	protected synchronized void onRemove() {
		for (Buff buff : buffs.toArray(new Buff[buffs.size()])) {
			buff.detach();
		}
	}
	
	public synchronized void updateSpriteState() {
		for (Buff buff:buffs) {
			buff.fx( true );
		}
	}
	
	public float stealth() {
		float stealth = 0;

		stealth += Obfuscation.stealthBoost(this, glyphLevel(Obfuscation.class));

		return stealth;
	}

	public final void move( int step ) {
		move( step, true );
	}

	//travelling may be false when a character is moving instantaneously, such as via teleportation
	public void move( int step, boolean travelling ) {

		if (travelling && Dungeon.level.adjacent( step, pos ) && buff( Vertigo.class ) != null) {
			sprite.interruptMotion();
			int newPos = pos + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
			if (!(Dungeon.level.passable[newPos] || Dungeon.level.avoid[newPos])
					|| (properties().contains(Property.LARGE) && !Dungeon.level.openSpace[newPos])
					|| Actor.findChar( newPos ) != null)
				return;
			else {
				sprite.move(pos, newPos);
				step = newPos;
			}
		}

		if (Dungeon.level.map[pos] == Terrain.OPEN_DOOR) {
			Door.leave( pos );
		}

		pos = step;
		
		if (this != Dungeon.hero) {
			sprite.visible = Dungeon.level.heroFOV[pos];
		}
		
		Dungeon.level.occupyCell(this );
	}
	
	public int distance( Char other ) {
		return Dungeon.level.distance( pos, other.pos );
	}

	public boolean[] modifyPassable( boolean[] passable){
		//do nothing by default, but some chars can pass over terrain that others can't
		return passable;
	}
	
	public void onMotionComplete() {
		//Does nothing by default
		//The main actor thread already accounts for motion,
		// so calling next() here isn't necessary (see Actor.process)
	}
	
	public void onAttackComplete() {
		next();
	}
	
	public void onOperateComplete() {
		next();
	}
	
	protected final HashSet<Class> resistances = new HashSet<>();

	public float resistanceValue(Class effect){
		return 0.5f;
	}
	
	//returns percent effectiveness after resistances
	//TODO currently resistances reduce effectiveness by a static 50%, and do not stack.
	public float resist( Class effect ){
		HashSet<Class> resists = new HashSet<>(resistances);
		for (Property p : properties()){
			resists.addAll(p.resistances());
		}
		for (Buff b : buffs()){
			resists.addAll(b.resistances());
		}
		
		float result = 1f;
		for (Class c : resists){
			if (c.isAssignableFrom(effect)){
				result *= resistanceValue(effect);
			}
		}

		if (buff(Pray.Praying.class) != null) {
			result *= Pray.Praying.resist(this, effect);
		}

		return result * RingOfElements.resist(this, effect);
	}
	
	protected final HashSet<Class> immunities = new HashSet<>();
	
	public boolean isImmune(Class effect ){
		HashSet<Class> immunes = new HashSet<>(immunities);
		for (Property p : properties()){
			immunes.addAll(p.immunities());
		}
		for (Buff b : buffs()){
			immunes.addAll(b.immunities());
		}
		if (glyphLevel(Brimstone.class) >= 0){
			immunes.add(Burning.class);
		}
		
		for (Class c : immunes){
			if (c.isAssignableFrom(effect)){
				return true;
			}
		}
		return false;
	}

	//similar to isImmune, but only factors in damage.
	//Is used in AI decision-making
	public boolean isInvulnerable( Class effect ){
		return buff(Challenge.SpectatorFreeze.class) != null || buff(Invulnerability.class) != null;
	}

	protected HashSet<Property> properties = new HashSet<>();

	public HashSet<Property> properties() {
		HashSet<Property> props = new HashSet<>(properties);
		//TODO any more of these and we should make it a property of the buff, like with resistances/immunities
		if (buff(ChampionEnemy.Giant.class) != null) {
			props.add(Property.LARGE);
		}
		return props;
	}

	public enum Property{
		BOSS ( new HashSet<Class>( Arrays.asList(Grim.class, GrimTrap.class, ScrollOfRetribution.class, ScrollOfPsionicBlast.class)),
				new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class) )),
		MINIBOSS ( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class) )),
		BOSS_MINION,
		UNDEAD,
		DEMONIC,
		INORGANIC ( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(Bleeding.class, ToxicGas.class, Poison.class) )),
		FIERY ( new HashSet<Class>( Arrays.asList(WandOfFireblast.class, Elemental.FireElemental.class)),
				new HashSet<Class>( Arrays.asList(Burning.class, Blazing.class))),
		ICY ( new HashSet<Class>( Arrays.asList(WandOfFrost.class, Elemental.FrostElemental.class)),
				new HashSet<Class>( Arrays.asList(Frost.class, Chill.class))),
		ACIDIC ( new HashSet<Class>( Arrays.asList(Corrosion.class)),
				new HashSet<Class>( Arrays.asList(Ooze.class))),
		ELECTRIC ( new HashSet<Class>( Arrays.asList(WandOfLightning.class, Shocking.class, Potential.class,
										Electricity.class, ShockingDart.class, Elemental.ShockElemental.class )),
				new HashSet<Class>()),
		LARGE,
		IMMOVABLE ( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(Vertigo.class) )),
		//A character that acts in an unchanging manner. immune to AI state debuffs or stuns/slows
		STATIC( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class, Terror.class, Amok.class, Charm.class, Sleep.class,
									Paralysis.class, Frost.class, Chill.class, Slow.class, Speed.class) ));

		private HashSet<Class> resistances;
		private HashSet<Class> immunities;
		
		Property(){
			this(new HashSet<Class>(), new HashSet<Class>());
		}
		
		Property( HashSet<Class> resistances, HashSet<Class> immunities){
			this.resistances = resistances;
			this.immunities = immunities;
		}
		
		public HashSet<Class> resistances(){
			return new HashSet<>(resistances);
		}
		
		public HashSet<Class> immunities(){
			return new HashSet<>(immunities);
		}

	}

	public static boolean hasProp( Char ch, Property p){
		return (ch != null && ch.properties().contains(p));
	}

	public void heal(int amount) {
		amount = Math.min( amount, this.HT - this.HP );
		if (amount > 0 && this.isAlive()) {
			this.HP += amount;
			this.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
			this.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString( amount ), FloatingText.HEALING);
		}
	}

	public void heal(int amount, boolean shouldAlive) {
		if (shouldAlive) {
			heal(amount);
			return;
		}
		amount = Math.min( amount, this.HT - this.HP );
		if (amount > 0) {
			this.HP += amount;
			this.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
			this.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString( amount ), FloatingText.HEALING);
		}
	}
}
