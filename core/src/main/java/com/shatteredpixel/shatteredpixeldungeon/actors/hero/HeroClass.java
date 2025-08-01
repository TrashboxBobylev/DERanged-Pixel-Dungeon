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

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.QuickSlot;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WarriorParry;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.adventurer.Root;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.adventurer.Sprout;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.adventurer.TreasureMap;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.archer.DashAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.archer.Hunt;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.archer.Snipe;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.AscendedForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.Trinity;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Feint;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.FirstAidKit;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.ReinforcedArmor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.gunner.Riot;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpectralBlades;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.knight.HolyShield;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.knight.StimPack;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.knight.UnstableAnkh;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.ElementalBlast;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WarpBeacon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.medic.AngelWing;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.medic.GammaRayEmmit;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.medic.HealingGenerator;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ratking.OmniAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ratking.Wrath;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.DeathMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.SmokeBomb;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.Abil_Kunai;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.Awake;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.samurai.ShadowBlade;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.HeroicLeap;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Shockwave;
import com.shatteredpixel.shatteredpixeldungeon.items.ArrowBag;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.BulletBelt;
import com.shatteredpixel.shatteredpixeldungeon.items.GammaRayGun;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KingsCrown;
import com.shatteredpixel.shatteredpixeldungeon.items.KnightsShield;
import com.shatteredpixel.shatteredpixeldungeon.items.Sheath;
import com.shatteredpixel.shatteredpixeldungeon.items.Teleporter;
import com.shatteredpixel.shatteredpixeldungeon.items.TengusMask;
import com.shatteredpixel.shatteredpixeldungeon.items.Waterskin;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HolyTome;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MedicKit;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.CheesyCheest;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.items.changer.OldAmulet;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfPurity;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.bow.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Cudgel;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Dagger;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Gloves;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Machete;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Rapier;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Saber;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Scalpel;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornKatana;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornShortsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.alchemy.TacticalShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.bow.WornShortBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.gun.AR.AR_T1;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.quick.PocketKnife;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingSpike;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingStone;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.utils.DungeonSeed;
import com.watabou.utils.DeviceCompat;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Arrays;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

public enum HeroClass {

	WARRIOR( HeroSubClass.BERSERKER, HeroSubClass.GLADIATOR, HeroSubClass.VETERAN ),
	MAGE( HeroSubClass.BATTLEMAGE, HeroSubClass.WARLOCK, HeroSubClass.WIZARD ),
	ROGUE( HeroSubClass.ASSASSIN, HeroSubClass.FREERUNNER, HeroSubClass.CHASER ),
	HUNTRESS( HeroSubClass.SNIPER, HeroSubClass.WARDEN, HeroSubClass.FIGHTER ),
	DUELIST( HeroSubClass.CHAMPION, HeroSubClass.MONK, HeroSubClass.FENCER ),
	CLERIC( HeroSubClass.PRIEST, HeroSubClass.PALADIN, HeroSubClass.ENCHANTER ),
	GUNNER( HeroSubClass.OUTLAW, HeroSubClass.GUNSLINGER, HeroSubClass.SPECIALIST ),
	SAMURAI( HeroSubClass.SLASHER, HeroSubClass.MASTER, HeroSubClass.SLAYER ),
	ADVENTURER( HeroSubClass.ENGINEER, HeroSubClass.EXPLORER, HeroSubClass.RESEARCHER ),
	KNIGHT( HeroSubClass.DEATHKNIGHT, HeroSubClass.HORSEMAN, HeroSubClass.CRUSADER),
	MEDIC( HeroSubClass.SAVIOR, HeroSubClass.THERAPIST, HeroSubClass.MEDICALOFFICER ),
	ARCHER( HeroSubClass.BOWMASTER, HeroSubClass.JUGGLER, HeroSubClass.SHARPSHOOTER ),
	RAT_KING(HeroSubClass.KING);

	private HeroSubClass[] subClasses;

	HeroClass( HeroSubClass...subClasses ) {
		this.subClasses = subClasses;
	}

	/** useful for sharing attributes with Rat King **/
	public boolean is(HeroClass cls) {
		return hero.isClassedLoosely(cls);
	}

	public boolean is(HeroClass cls, Hero hero){
		return hero.isClassedLoosely(cls);
	}

	// TODO: this is used much more frequently than is(cls) due to heroes *not* sharing traits with rat king
	public boolean isExact(HeroClass cls) {
		return hero.isClassed(cls);
	}

	public boolean isExact(HeroClass cls, Hero hero){
		return hero.isClassed(cls);
	}

	public void initHero( Hero hero ) {

		hero.heroClass = this;
		Talent.initClassTalents(hero);
		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.RANDOM_TALENTS)){
			Talent.shuffleTalents(hero);
		}

		Item i = new ClothArmor().identify();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor)i;

		i = new Food();
		if (!Challenges.isItemBlocked(i)) i.collect();

		new VelvetPouch().collect();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();

		Waterskin waterskin = new Waterskin();
		waterskin.collect();

		new ScrollOfIdentify().identify();

		if (SPDSettings.customSeed().contains("test")) {
			new RingOfMight().identify().upgrade(10).collect();
			new RingOfEnergy().identify().upgrade(20).collect();
			new RingOfHaste().identify().upgrade(100).collect();
			new RingOfAccuracy().identify().upgrade(100).collect();
			new AlchemistsToolkit().identify().upgrade(10).collect();
			new ElixirOfHoneyedHealing().identify().quantity(500).collect();
			new PlateArmor().identify().upgrade(100).collect();
			new TacticalShield().identify().upgrade(100).collect();
			new Teleporter().collect();
			new TengusMask().collect();
			new KingsCrown().collect();
			new OldAmulet().collect();
//			new BrokenShield().collect();
//			new PinkGem().collect();
//			new Ankh().collect();
//			new PotionOfLiquidFlame().identify().quantity(100).collect();
//			new SheathFragment().quantity(20).collect();
//			new BulletBelt().collect();
//			new WornShortBow().identify().collect();
//			new ShortBow().identify().collect();
//			new Bow().identify().collect();
//			new LongBow().identify().collect();
//			new GreatBow().identify().collect();
		}

		switch (this) {
			case WARRIOR:
				initWarrior( hero );
				break;

			case MAGE:
				initMage( hero );
				break;

			case ROGUE:
				initRogue( hero );
				break;

			case HUNTRESS:
				initHuntress( hero );
				break;

			case DUELIST:
				initDuelist( hero );
				break;

			case CLERIC:
				initCleric( hero );
				break;

			case GUNNER:
				initGunner( hero );
				break;

			case SAMURAI:
				initSamurai( hero );
				break;

			case ADVENTURER:
				initAdventurer( hero );
				break;

			case KNIGHT:
				initKnight( hero );
				break;

			case MEDIC:
				initMedic( hero );
				break;

			case ARCHER:
				initArcher( hero );
				break;

			case RAT_KING:
				initRatKing( hero );
				break;
		}

		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.CORROSION)){
			WandOfCorrosion wand = new WandOfCorrosion();
			wand.identify();
			wand.upgrade(100);
			wand.collect();
			for (int s = 0; s < QuickSlot.SIZE; s++) {
				if (Dungeon.quickslot.getItem(s) == null) {
					Dungeon.quickslot.setSlot(s, wand);
					break;
				}
			}
		}

		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.DUELIST)){
			MeleeWeapon weapon = (MeleeWeapon) Generator.randomUsingDefaults(Random.oneOf(Generator.Category.WEP_T1, Generator.Category.WEP_T2, Generator.Category.WEP_T3, Generator.Category.WEP_T4, Generator.Category.WEP_T5));
			if (Random.Int(22) == 0){
				weapon = (MeleeWeapon) Generator.randomUsingDefaults(Generator.Category.WEP_AL_T6);
			}
			if (weapon != null){
				weapon.duelistStart = true;
				weapon.tier = 1;
				weapon.cursed = false;
				weapon.level(0);
				weapon.enchant(null);
				(hero.belongings.weapon = weapon).identify();
			}
		}

		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.ENCHANTED_WORLD)){
			for (Item item: hero.belongings){
				if (item instanceof Weapon){
					((Weapon) item).enchant();
				} else if (item instanceof Armor){
					((Armor) item).inscribe();
				}
			}
		}

		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.CLERIC)){
			HolyTome tome = new HolyTome();
			tome.collect();
			tome.identify();
			tome.activate(hero);
			for (int s = 0; s < QuickSlot.SIZE; s++) {
				if (Dungeon.quickslot.getItem(s) == null) {
					Dungeon.quickslot.setSlot(s, tome);
					break;
				}
			}
		}

		if (SPDSettings.quickslotWaterskin()) {
			for (int s = 0; s < QuickSlot.SIZE; s++) {
				if (Dungeon.quickslot.getItem(s) == null) {
					Dungeon.quickslot.setSlot(s, waterskin);
					break;
				}
			}
		}

		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.LEVELLING_DOWN)){
			hero.lvl = 24;
			hero.exp += hero.maxExp()/2;
			hero.updateStats();
		}

	}

	public Badges.Badge masteryBadge() {
		switch (this) {
			case WARRIOR:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case ROGUE:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
			case DUELIST:
				return Badges.Badge.MASTERY_DUELIST;
			case CLERIC:
				return Badges.Badge.MASTERY_CLERIC;
			case GUNNER:
				return Badges.Badge.MASTERY_GUNNER;
			case SAMURAI:
				return Badges.Badge.MASTERY_SAMURAI;
			case ADVENTURER:
				return Badges.Badge.MASTERY_ADVENTURER;
			case KNIGHT:
				return Badges.Badge.MASTERY_KNIGHT;
			case MEDIC:
				return Badges.Badge.MASTERY_MEDIC;
			case ARCHER:
				return Badges.Badge.MASTERY_ARCHER;
		}
		return null;
	}

	private static void initWarrior( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, stones);

		if (hero.belongings.armor != null){
			hero.belongings.armor.affixSeal(new BrokenSeal());
			Catalog.setSeen(BrokenSeal.class); //as it's not added to the inventory
		}

		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.WARRIOR)){
			Buff.affect(hero, WarriorParry.class);
		}

		new PotionOfHealing().identify();
		new ScrollOfRage().identify();
	}

	private static void initMage( Hero hero ) {
		MagesStaff staff;

		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.MAGE)){
			do {
				staff = new MagesStaff((Wand) Reflection.newInstance(Random.element(Generator.Category.WAND.classes)));
			} while (staff.wandClass() == WandOfMagicMissile.class);
		} else {
			staff = new MagesStaff(new WandOfMagicMissile());
		}

		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);

		Dungeon.quickslot.setSlot(0, staff);

		new ScrollOfUpgrade().identify();
		new PotionOfLiquidFlame().identify();
	}

	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();

		if (!Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.ROGUE)) {
			CloakOfShadows cloak = new CloakOfShadows();
			(hero.belongings.artifact = cloak).identify();
			hero.belongings.artifact.activate(hero);
			Dungeon.quickslot.setSlot(0, cloak);
		}
		 else {
			Random.pushGenerator();
			Artifact cloak = Generator.randomArtifact();
			(hero.belongings.artifact = cloak).identify();
			hero.belongings.artifact.activate(hero);
			Random.popGenerator();
			Dungeon.quickslot.setSlot(0, cloak);
		}

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		Dungeon.quickslot.setSlot(1, knives);

		new ScrollOfMagicMapping().identify();
		new PotionOfInvisibility().identify();
	}

	private static void initHuntress( Hero hero ) {

		(hero.belongings.weapon = new Gloves()).identify();
		SpiritBow bow = new SpiritBow();
		bow.identify().collect();

		Dungeon.quickslot.setSlot(0, bow);

		new PotionOfMindVision().identify();
		new ScrollOfLullaby().identify();
	}

	private static void initDuelist( Hero hero ) {

		(hero.belongings.weapon = new Rapier()).identify();
		hero.belongings.weapon.activate(hero);

		ThrowingSpike spikes = new ThrowingSpike();
		spikes.quantity(2).collect();

		Dungeon.quickslot.setSlot(0, hero.belongings.weapon);
		Dungeon.quickslot.setSlot(1, spikes);

		new PotionOfStrength().identify();
		new ScrollOfMirrorImage().identify();
	}

	private static void initCleric( Hero hero ) {

		(hero.belongings.weapon = new Cudgel()).identify();
		hero.belongings.weapon.activate(hero);

		HolyTome tome = new HolyTome();
		(hero.belongings.artifact = tome).identify();
		hero.belongings.artifact.activate( hero );

		Dungeon.quickslot.setSlot(0, tome);

		new PotionOfPurity().identify();
		new ScrollOfRemoveCurse().identify();
	}

	private static void initGunner( Hero hero ) {
		(hero.belongings.weapon = new AR_T1()).identify();
		hero.belongings.weapon.activate(hero);

		BulletBelt bulletBelt = new BulletBelt();
		bulletBelt.quantity(5).collect();

		PocketKnife pocketKnife = new PocketKnife();
		pocketKnife.identify().collect();

		Dungeon.quickslot.setSlot(0, hero.belongings.weapon);
		Dungeon.quickslot.setSlot(1, pocketKnife);
		Dungeon.quickslot.setSlot(2, bulletBelt);

		new PotionOfHaste().identify();
		new ScrollOfTeleportation().identify();
	}

	private static void initSamurai( Hero hero ) {

		WornKatana wornKatana = new WornKatana();
		(hero.belongings.weapon = wornKatana).identify();

		Sheath sheath = new Sheath();
		sheath.collect();

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, sheath);
		Dungeon.quickslot.setSlot(1, knives);

		new ScrollOfRetribution().identify();
		new PotionOfStrength().identify();
	}

	private static void initAdventurer( Hero hero ) {
		Shovel shovel = new Shovel();
		(hero.belongings.weapon = shovel).identify();
		hero.belongings.weapon.activate(hero);

		Machete machete = new Machete();
		machete.identify().collect();

		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, shovel);
		Dungeon.quickslot.setSlot(1, machete);
		Dungeon.quickslot.setSlot(2, stones);

		new ScrollOfMagicMapping().identify();
		new PotionOfPurity().identify();
	}

	private static void initKnight( Hero hero ) {
		Saber saber = new Saber();
		(hero.belongings.weapon = saber).identify();
		hero.belongings.weapon.activate(hero);
		KnightsShield shield = new KnightsShield();
		shield.collect();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, stones);

		new ScrollOfRemoveCurse().identify();
		new PotionOfParalyticGas().identify();
	}

	private static void initMedic( Hero hero ) {
		Scalpel scalpel = new Scalpel();
		(hero.belongings.weapon = scalpel).identify();
		hero.belongings.weapon.activate(hero);

		GammaRayGun gammaRayGun = new GammaRayGun();
		gammaRayGun.collect();
		Dungeon.quickslot.setSlot(0, gammaRayGun);

		MedicKit kit = new MedicKit();
		(hero.belongings.artifact = kit).identify();
		hero.belongings.artifact.activate( hero );
		Dungeon.quickslot.setSlot(1, kit);

		new ScrollOfMirrorImage().identify();
		new PotionOfHealing().identify();
	}

	private static void initArcher( Hero hero ) {
		WornShortBow bow = new WornShortBow();
		(hero.belongings.weapon = bow).identify();
		hero.belongings.weapon.activate(hero);

		PocketKnife pocketKnife = new PocketKnife();
		pocketKnife.identify().collect();

		BulletBelt bulletBelt = new BulletBelt();
		bulletBelt.quantity(3).collect();

		ArrowBag arrowBag = new ArrowBag();
		arrowBag.collect();

		Dungeon.quickslot.setSlot(0, hero.belongings.weapon);
		Dungeon.quickslot.setSlot(1, pocketKnife);
		Dungeon.quickslot.setSlot(2, arrowBag);
		Dungeon.quickslot.setSlot(3, bulletBelt);

		new ScrollOfMagicMapping().identify();
		new PotionOfHaste().identify();
	}

	private static void initRatKing( Hero hero ) {
		// warrior
		if (hero.belongings.armor != null){
			hero.belongings.armor.affixSeal(new BrokenSeal());
		}
		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.WARRIOR)){
			Buff.affect(hero, WarriorParry.class);
		}
		// mage
		MagesStaff staff;

		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.MAGE)){
			do {
				staff = new MagesStaff((Wand) Reflection.newInstance(Random.element(Generator.Category.WAND.classes)));
			} while (staff.wandClass() == WandOfMagicMissile.class);
		} else {
			staff = new MagesStaff(new WandOfMagicMissile());
		}
		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);
		// rogue
		Artifact cloak;
		if (!Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.ROGUE)) {
			cloak = new CloakOfShadows();
			(hero.belongings.artifact = cloak).identify();
			hero.belongings.artifact.activate(hero);
			Dungeon.quickslot.setSlot(0, cloak);
		} else {
			Random.pushGenerator();
			cloak = Generator.randomArtifact();
			(hero.belongings.artifact = cloak).identify();
			hero.belongings.artifact.activate(hero);
			Random.popGenerator();
			Dungeon.quickslot.setSlot(0, cloak);
		}
		// huntress
		SpiritBow bow = new SpiritBow();
		bow.identify().collect();
		// cleric
		HolyTome tome = new HolyTome();
		(hero.belongings.misc = tome).identify();
		hero.belongings.misc.activate( hero );
		// rearranged heroes
		CheesyCheest chest = new CheesyCheest();
		chest.collect();
		BulletBelt bulletBelt = new BulletBelt();
		bulletBelt.quantity(5).collect();
		new AR_T1().identify().collect();
		new Sheath().collect();
		new Machete().identify().collect();
		new Shovel().identify().collect();
		new KnightsShield().collect();
		new GammaRayGun().collect();
		new MedicKit().identify().collect();
		new WornShortBow().identify().collect();
        new ArrowBag().collect();
		// allocating slots
		Dungeon.quickslot.setSlot(0, bow);
		Dungeon.quickslot.setSlot(1, cloak);
		Dungeon.quickslot.setSlot(2, staff);
		Dungeon.quickslot.setSlot(3, tome);
		Dungeon.quickslot.setSlot(4, chest);
	}

	public String title() {
		return Messages.get(HeroClass.class, name());
	}

	public String desc(){
		return Messages.get(HeroClass.class, name()+"_desc");
	}

	public String shortDesc(){
		return Messages.get(HeroClass.class, name()+"_desc_short");
	}

	public ArrayList<HeroSubClass> subClasses() {
		return new ArrayList<>(Arrays.asList(subClasses));
	}

	public ArmorAbility[] armorAbilities(){
		if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.RANDOM_HERO) && hero != null && hero.armorAbility == null){
			return new ArmorAbility[]{new OmniAbility()};
		}
		switch (this) {
			case WARRIOR: default:
				return new ArmorAbility[]{new HeroicLeap(), new Shockwave(), new Endure()};
			case MAGE:
				return new ArmorAbility[]{new ElementalBlast(), new WildMagic(), new WarpBeacon()};
			case ROGUE:
				return new ArmorAbility[]{new SmokeBomb(), new DeathMark(), new ShadowClone()};
			case HUNTRESS:
				return new ArmorAbility[]{new SpectralBlades(), new NaturesPower(), new SpiritHawk()};
			case DUELIST:
				return new ArmorAbility[]{new Challenge(), new ElementalStrike(), new Feint()};
			case CLERIC:
				return new ArmorAbility[]{new AscendedForm(), new Trinity(), new PowerOfMany()};
			case GUNNER:
				return new ArmorAbility[]{new Riot(), new ReinforcedArmor(), new FirstAidKit()};
			case SAMURAI:
				return new ArmorAbility[]{new Awake(), new ShadowBlade(), new Abil_Kunai()};
			case ADVENTURER:
				return new ArmorAbility[]{new Sprout(), new TreasureMap(), new Root()};
			case KNIGHT:
				return new ArmorAbility[]{new HolyShield(), new StimPack(), new UnstableAnkh()};
			case MEDIC:
				return new ArmorAbility[]{new HealingGenerator(), new AngelWing(), new GammaRayEmmit()};
			case ARCHER:
				return new ArmorAbility[]{new DashAbility(), new Hunt(), new Snipe()};
			case RAT_KING:
				return new ArmorAbility[]{new Wrath(), new Ratmogrify(), new OmniAbility()};
		}
	}

	public String spritesheet() {
		switch (this) {
			case WARRIOR: default:
				return Assets.Sprites.WARRIOR;
			case MAGE:
				return Assets.Sprites.MAGE;
			case ROGUE:
				return Assets.Sprites.ROGUE;
			case HUNTRESS:
				return Assets.Sprites.HUNTRESS;
			case DUELIST:
				return Assets.Sprites.DUELIST;
			case CLERIC:
				return Assets.Sprites.CLERIC;
			case GUNNER:
				return Assets.Sprites.GUNNER;
			case SAMURAI:
				return Assets.Sprites.SAMURAI;
			case ADVENTURER:
				return Assets.Sprites.ADVENTURER;
			case KNIGHT:
				return Assets.Sprites.KNIGHT;
			case MEDIC:
				return Assets.Sprites.MEDIC;
			case ARCHER:
				return Assets.Sprites.ARCHER;
			case RAT_KING:
				return Assets.Sprites.RAT_KING_HERO;
		}
	}

	public String splashArt(){
		switch (this) {
			case WARRIOR: default:
				return Assets.Splashes.WARRIOR;
			case MAGE:
				return Assets.Splashes.MAGE;
			case ROGUE:
				return Assets.Splashes.ROGUE;
			case HUNTRESS:
				return Assets.Splashes.HUNTRESS;
			case DUELIST:
				return Assets.Splashes.DUELIST;
			case CLERIC:
				return Assets.Splashes.CLERIC;
			case GUNNER:
				return Assets.Splashes.GUNNER;
			case SAMURAI:
				return Assets.Splashes.SAMURAI;
			case ADVENTURER:
				return Assets.Splashes.ADVENTURER;
			case KNIGHT:
				return Assets.Splashes.KNIGHT;
			case MEDIC:
				return Assets.Splashes.MEDIC;
			case ARCHER:
				return Assets.Splashes.ARCHER;
			case RAT_KING:
				return Assets.Splashes.RAT_KING;
		}
	}

	public boolean isUnlocked(){
		Badges.Badge unlockBadge;
		try {
			unlockBadge = Badges.Badge.valueOf("UNLOCK_" + name());
		} catch (IllegalArgumentException e) { return true; }
		if (this != RAT_KING) Badges.unlock(unlockBadge);  // auto-unlock non-rat king
		else unlockBadge = Badges.Badge.HIGH_SCORE_4;
		//always unlock on debug builds
		return DeviceCompat.isDebug() || Badges.isUnlocked(unlockBadge);
	}

	
	public String unlockMsg() {
		return shortDesc() + "\n\n" + Messages.get(HeroClass.class, name()+"_unlock");
	}

}
