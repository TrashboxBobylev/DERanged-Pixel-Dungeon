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

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class ItemSpriteSheet {

	private static final int WIDTH = 16;
	public static final int SIZE = 16;

	public static TextureFilm film = new TextureFilm( Assets.Sprites.ITEMS, SIZE, SIZE );

	private static int xy(int x, int y){
		x -= 1; y -= 1;
		return x + WIDTH*y;
	}

	private static void assignItemRect( int item, int width, int height ){
		int x = (item % WIDTH) * SIZE;
		int y = (item / WIDTH) * SIZE;
		film.add( item, x, y, x+width, y+height);
	}

	private static final int PLACEHOLDERS   =                               xy(1, 1);   //18 slots
	//SOMETHING is the default item sprite at position 0. May show up ingame if there are bugs.
	public static final int SOMETHING       = PLACEHOLDERS+0;
	public static final int WEAPON_HOLDER   = PLACEHOLDERS+1;
	public static final int ARMOR_HOLDER    = PLACEHOLDERS+2;
	public static final int MISSILE_HOLDER  = PLACEHOLDERS+3;
	public static final int WAND_HOLDER     = PLACEHOLDERS+4;
	public static final int RING_HOLDER     = PLACEHOLDERS+5;
	public static final int ARTIFACT_HOLDER = PLACEHOLDERS+6;
	public static final int TRINKET_HOLDER  = PLACEHOLDERS+7;
	public static final int FOOD_HOLDER     = PLACEHOLDERS+8;
	public static final int BOMB_HOLDER     = PLACEHOLDERS+9;
	public static final int POTION_HOLDER   = PLACEHOLDERS+10;
	public static final int SEED_HOLDER     = PLACEHOLDERS+11;
	public static final int SCROLL_HOLDER   = PLACEHOLDERS+12;
	public static final int STONE_HOLDER    = PLACEHOLDERS+13;
	public static final int ELIXIR_HOLDER   = PLACEHOLDERS+14;
	public static final int SPELL_HOLDER    = PLACEHOLDERS+15;
	public static final int MOB_HOLDER      = PLACEHOLDERS+16;
	public static final int DOCUMENT_HOLDER = PLACEHOLDERS+17;
	static{
		assignItemRect(SOMETHING,       8,  13);
		assignItemRect(WEAPON_HOLDER,   14, 14);
		assignItemRect(ARMOR_HOLDER,    14, 12);
		assignItemRect(MISSILE_HOLDER,  15, 15);
		assignItemRect(WAND_HOLDER,     14, 14);
		assignItemRect(RING_HOLDER,     8,  10);
		assignItemRect(ARTIFACT_HOLDER, 15, 15);
		assignItemRect(TRINKET_HOLDER,  16, 11);
		assignItemRect(FOOD_HOLDER,     15, 11);
		assignItemRect(BOMB_HOLDER,     10, 13);
		assignItemRect(POTION_HOLDER,   12, 14);
		assignItemRect(SEED_HOLDER,     10, 10);
		assignItemRect(SCROLL_HOLDER,   15, 14);
		assignItemRect(STONE_HOLDER,    14, 12);
		assignItemRect(ELIXIR_HOLDER,   12, 14);
		assignItemRect(SPELL_HOLDER,    8,  16);
		assignItemRect(MOB_HOLDER,      15, 14);
		assignItemRect(DOCUMENT_HOLDER, 10, 11);
	}

	private static final int UNCOLLECTIBLE  =                               xy(3, 2);   //14 slots
	public static final int GOLD            = UNCOLLECTIBLE+0;
	public static final int ENERGY          = UNCOLLECTIBLE+1;

	public static final int DEWDROP         = UNCOLLECTIBLE+3;
	public static final int PETAL           = UNCOLLECTIBLE+4;
	public static final int SANDBAG         = UNCOLLECTIBLE+5;
	public static final int SPIRIT_ARROW    = UNCOLLECTIBLE+6;
	
	public static final int TENGU_BOMB      = UNCOLLECTIBLE+8;
	public static final int TENGU_SHOCKER   = UNCOLLECTIBLE+9;
	public static final int GEO_BOULDER     = UNCOLLECTIBLE+10;
	static{
		assignItemRect(GOLD,        15, 13);
		assignItemRect(ENERGY,      16, 16);

		assignItemRect(DEWDROP,     10, 10);
		assignItemRect(PETAL,       8,  8);
		assignItemRect(SANDBAG,     10, 10);
		assignItemRect(SPIRIT_ARROW,11, 11);
		
		assignItemRect(TENGU_BOMB,      10, 10);
		assignItemRect(TENGU_SHOCKER,   10, 10);
		assignItemRect(GEO_BOULDER,     16, 14);
	}

	private static final int CONTAINERS     =                               xy(1, 3);   //16 slots
	public static final int BONES           = CONTAINERS+0;
	public static final int REMAINS         = CONTAINERS+1;
	public static final int TOMB            = CONTAINERS+2;
	public static final int GRAVE           = CONTAINERS+3;
	public static final int CHEST           = CONTAINERS+4;
	public static final int LOCKED_CHEST    = CONTAINERS+5;
	public static final int CRYSTAL_CHEST   = CONTAINERS+6;
	public static final int EBONY_CHEST     = CONTAINERS+7;
	static{
		assignItemRect(BONES,           14, 11);
		assignItemRect(REMAINS,         14, 11);
		assignItemRect(TOMB,            14, 15);
		assignItemRect(GRAVE,           14, 15);
		assignItemRect(CHEST,           16, 14);
		assignItemRect(LOCKED_CHEST,    16, 14);
		assignItemRect(CRYSTAL_CHEST,   16, 14);
		assignItemRect(EBONY_CHEST,     16, 14);
	}

	private static final int MISC_CONSUMABLE =                              xy(1, 4);   //32 slots
	public static final int ANKH            = MISC_CONSUMABLE +0;
	public static final int STYLUS          = MISC_CONSUMABLE +1;
	public static final int SEAL            = MISC_CONSUMABLE +2;
	public static final int TORCH           = MISC_CONSUMABLE +3;
	public static final int BEACON          = MISC_CONSUMABLE +4;
	public static final int HONEYPOT        = MISC_CONSUMABLE +5;
	public static final int SHATTPOT        = MISC_CONSUMABLE +6;
	public static final int IRON_KEY        = MISC_CONSUMABLE +7;
	public static final int GOLDEN_KEY      = MISC_CONSUMABLE +8;
	public static final int CRYSTAL_KEY     = MISC_CONSUMABLE +9;
	public static final int SKELETON_KEY    = MISC_CONSUMABLE +10;
	public static final int MASK            = MISC_CONSUMABLE +11;
	public static final int CROWN           = MISC_CONSUMABLE +12;
	public static final int AMULET          = MISC_CONSUMABLE +13;
	public static final int MASTERY         = MISC_CONSUMABLE +14;
	public static final int KIT             = MISC_CONSUMABLE +15;
	public static final int SEAL_SHARD      = MISC_CONSUMABLE +16;
	public static final int BROKEN_STAFF    = MISC_CONSUMABLE +17;
	public static final int CLOAK_SCRAP     = MISC_CONSUMABLE +18;
	public static final int BOW_FRAGMENT    = MISC_CONSUMABLE +19;
	public static final int BROKEN_HILT     = MISC_CONSUMABLE +20;
	public static final int TORN_PAGE       = MISC_CONSUMABLE +21;
	public static final int TRINKET_CATA    = MISC_CONSUMABLE +22;
	public static final int BROKEN_CROWN    = MISC_CONSUMABLE +23;



	static{
		assignItemRect(ANKH,            10, 16);
		assignItemRect(STYLUS,          12, 13);
		
		assignItemRect(SEAL,            13, 13);
		assignItemRect(TORCH,           12, 15);
		assignItemRect(BEACON,          16, 15);
		
		assignItemRect(HONEYPOT,        14, 12);
		assignItemRect(SHATTPOT,        14, 12);
		assignItemRect(IRON_KEY,        8,  14);
		assignItemRect(GOLDEN_KEY,      8,  14);
		assignItemRect(CRYSTAL_KEY,     8,  14);
		assignItemRect(SKELETON_KEY,    8,  14);
		assignItemRect(MASK,            11,  9);
		assignItemRect(CROWN,           13,  7);
		assignItemRect(AMULET,          16, 16);
		assignItemRect(MASTERY,         13, 16);
		assignItemRect(KIT,             16, 15);

		assignItemRect(SEAL_SHARD,      12, 12);
		assignItemRect(BROKEN_STAFF,    14, 10);
		assignItemRect(CLOAK_SCRAP,      9,  9);
		assignItemRect(BOW_FRAGMENT,    12,  9);
		assignItemRect(BROKEN_HILT,      9,  9);
		assignItemRect(TORN_PAGE,       11, 13);
		assignItemRect(BROKEN_CROWN,     8,  6);

		assignItemRect(TRINKET_CATA,    12, 11);
	}
	
	private static final int BOMBS          =                               xy(1, 6);   //16 slots
	public static final int BOMB            = BOMBS+0;
	public static final int DBL_BOMB        = BOMBS+1;
	public static final int FIRE_BOMB       = BOMBS+2;
	public static final int FROST_BOMB      = BOMBS+3;
	public static final int REGROWTH_BOMB   = BOMBS+4;
	public static final int SMOKE_BOMB      = BOMBS+5;
	public static final int FLASHBANG       = BOMBS+6;
	public static final int HOLY_BOMB       = BOMBS+7;
	public static final int WOOLY_BOMB      = BOMBS+8;
	public static final int NOISEMAKER      = BOMBS+9;
	public static final int ARCANE_BOMB     = BOMBS+10;
	public static final int SHRAPNEL_BOMB   = BOMBS+11;
	
	static{
		assignItemRect(BOMB,            10, 13);
		assignItemRect(DBL_BOMB,        14, 13);
		assignItemRect(FIRE_BOMB,       13, 12);
		assignItemRect(FROST_BOMB,      13, 12);
		assignItemRect(REGROWTH_BOMB,   13, 12);
		assignItemRect(SMOKE_BOMB,      13, 12);
		assignItemRect(FLASHBANG,       10, 13);
		assignItemRect(HOLY_BOMB,       10, 13);
		assignItemRect(WOOLY_BOMB,      10, 13);
		assignItemRect(NOISEMAKER,      10, 13);
		assignItemRect(ARCANE_BOMB,     10, 13);
		assignItemRect(SHRAPNEL_BOMB,   10, 13);
	}

	private static final int WEP_TIER1      =                               xy(1, 7);   //8 slots
	public static final int WORN_SHORTSWORD = WEP_TIER1+0;
	public static final int CUDGEL          = WEP_TIER1+1;
	public static final int GLOVES          = WEP_TIER1+2;
	public static final int RAPIER          = WEP_TIER1+3;
	public static final int DAGGER          = WEP_TIER1+4;
	public static final int MAGES_STAFF     = WEP_TIER1+5;
	static{
		assignItemRect(WORN_SHORTSWORD, 13, 13);
		assignItemRect(CUDGEL,          15, 15);
		assignItemRect(GLOVES,          12, 16);
		assignItemRect(RAPIER,          13, 14);
		assignItemRect(DAGGER,          12, 13);
		assignItemRect(MAGES_STAFF,     15, 16);
	}

	private static final int WEP_TIER2      =                               xy(9, 7);   //8 slots
	public static final int SHORTSWORD      = WEP_TIER2+0;
	public static final int HAND_AXE        = WEP_TIER2+1;
	public static final int SPEAR           = WEP_TIER2+2;
	public static final int QUARTERSTAFF    = WEP_TIER2+3;
	public static final int DIRK            = WEP_TIER2+4;
	public static final int SICKLE          = WEP_TIER2+5;
	static{
		assignItemRect(SHORTSWORD,      13, 13);
		assignItemRect(HAND_AXE,        12, 14);
		assignItemRect(SPEAR,           16, 16);
		assignItemRect(QUARTERSTAFF,    16, 16);
		assignItemRect(DIRK,            13, 14);
		assignItemRect(SICKLE,          15, 15);
	}

	private static final int WEP_TIER3      =                               xy(1, 8);   //8 slots
	public static final int SWORD           = WEP_TIER3+0;
	public static final int MACE            = WEP_TIER3+1;
	public static final int SCIMITAR        = WEP_TIER3+2;
	public static final int ROUND_SHIELD    = WEP_TIER3+3;
	public static final int SAI             = WEP_TIER3+4;
	public static final int WHIP            = WEP_TIER3+5;
	static{
		assignItemRect(SWORD,           14, 14);
		assignItemRect(MACE,            15, 15);
		assignItemRect(SCIMITAR,        13, 16);
		assignItemRect(ROUND_SHIELD,    16, 16);
		assignItemRect(SAI,             16, 16);
		assignItemRect(WHIP,            14, 14);
	}

	private static final int WEP_TIER4      =                               xy(9, 8);   //8 slots
	public static final int LONGSWORD       = WEP_TIER4+0;
	public static final int BATTLE_AXE      = WEP_TIER4+1;
	public static final int FLAIL           = WEP_TIER4+2;
	public static final int RUNIC_BLADE     = WEP_TIER4+3;
	public static final int ASSASSINS_BLADE = WEP_TIER4+4;
	public static final int CROSSBOW        = WEP_TIER4+5;
	public static final int KATANA          = WEP_TIER4+6;
	static{
		assignItemRect(LONGSWORD,       15, 15);
		assignItemRect(BATTLE_AXE,      16, 16);
		assignItemRect(FLAIL,           14, 14);
		assignItemRect(RUNIC_BLADE,     14, 14);
		assignItemRect(ASSASSINS_BLADE, 14, 15);
		assignItemRect(CROSSBOW,        15, 15);
		assignItemRect(KATANA,          15, 16);
	}

	private static final int WEP_TIER5      =                               xy(1, 9);   //8 slots
	public static final int GREATSWORD      = WEP_TIER5+0;
	public static final int WAR_HAMMER      = WEP_TIER5+1;
	public static final int GLAIVE          = WEP_TIER5+2;
	public static final int GREATAXE        = WEP_TIER5+3;
	public static final int GREATSHIELD     = WEP_TIER5+4;
	public static final int GAUNTLETS       = WEP_TIER5+5;
	public static final int WAR_SCYTHE      = WEP_TIER5+6;
	static{
		assignItemRect(GREATSWORD,  16, 16);
		assignItemRect(WAR_HAMMER,  16, 16);
		assignItemRect(GLAIVE,      16, 16);
		assignItemRect(GREATAXE,    12, 16);
		assignItemRect(GREATSHIELD, 12, 16);
		assignItemRect(GAUNTLETS,   13, 15);
		assignItemRect(WAR_SCYTHE,  14, 15);
	}

	                                                                                    //8 free slots

	private static final int MISSILE_WEP    =                               xy(1, 10);  //16 slots. 3 per tier + bow
	public static final int SPIRIT_BOW      = MISSILE_WEP+0;
	
	public static final int THROWING_SPIKE  = MISSILE_WEP+1;
	public static final int THROWING_KNIFE  = MISSILE_WEP+2;
	public static final int THROWING_STONE  = MISSILE_WEP+3;
	
	public static final int FISHING_SPEAR   = MISSILE_WEP+4;
	public static final int SHURIKEN        = MISSILE_WEP+5;
	public static final int THROWING_CLUB   = MISSILE_WEP+6;
	
	public static final int THROWING_SPEAR  = MISSILE_WEP+7;
	public static final int BOLAS           = MISSILE_WEP+8;
	public static final int KUNAI           = MISSILE_WEP+9;
	
	public static final int JAVELIN         = MISSILE_WEP+10;
	public static final int TOMAHAWK        = MISSILE_WEP+11;
	public static final int BOOMERANG       = MISSILE_WEP+12;
	
	public static final int TRIDENT         = MISSILE_WEP+13;
	public static final int THROWING_HAMMER = MISSILE_WEP+14;
	public static final int FORCE_CUBE      = MISSILE_WEP+15;
	
	static{
		assignItemRect(SPIRIT_BOW,      16, 16);
		
		assignItemRect(THROWING_SPIKE,  11, 10);
		assignItemRect(THROWING_KNIFE,  12, 13);
		assignItemRect(THROWING_STONE,  12, 10);
		
		assignItemRect(FISHING_SPEAR,   11, 11);
		assignItemRect(SHURIKEN,        12, 12);
		assignItemRect(THROWING_CLUB,   12, 12);
		
		assignItemRect(THROWING_SPEAR,  13, 13);
		assignItemRect(BOLAS,           15, 14);
		assignItemRect(KUNAI,           15, 15);
		
		assignItemRect(JAVELIN,         16, 16);
		assignItemRect(TOMAHAWK,        13, 13);
		assignItemRect(BOOMERANG,       14, 14);
		
		assignItemRect(TRIDENT,         16, 16);
		assignItemRect(THROWING_HAMMER, 12, 12);
		assignItemRect(FORCE_CUBE,      11, 12);
	}
	
	public static final int DARTS    =                                      xy(1, 11);  //16 slots
	public static final int DART            = DARTS+0;
	public static final int ROT_DART        = DARTS+1;
	public static final int INCENDIARY_DART = DARTS+2;
	public static final int ADRENALINE_DART = DARTS+3;
	public static final int HEALING_DART    = DARTS+4;
	public static final int CHILLING_DART   = DARTS+5;
	public static final int SHOCKING_DART   = DARTS+6;
	public static final int POISON_DART     = DARTS+7;
	public static final int CLEANSING_DART  = DARTS+8;
	public static final int PARALYTIC_DART  = DARTS+9;
	public static final int HOLY_DART       = DARTS+10;
	public static final int DISPLACING_DART = DARTS+11;
	public static final int BLINDING_DART   = DARTS+12;
	static {
		for (int i = DARTS; i < DARTS+16; i++)
			assignItemRect(i, 15, 15);
	}
	
	private static final int ARMOR          =                               xy(1, 12);  //16 slots
	public static final int ARMOR_CLOTH     = ARMOR+0;
	public static final int ARMOR_LEATHER   = ARMOR+1;
	public static final int ARMOR_MAIL      = ARMOR+2;
	public static final int ARMOR_SCALE     = ARMOR+3;
	public static final int ARMOR_PLATE     = ARMOR+4;
	public static final int ARMOR_WARRIOR   = ARMOR+5;
	public static final int ARMOR_MAGE      = ARMOR+6;
	public static final int ARMOR_ROGUE     = ARMOR+7;
	public static final int ARMOR_HUNTRESS  = ARMOR+8;
	public static final int ARMOR_DUELIST   = ARMOR+9;
	public static final int ARMOR_CLERIC    = ARMOR+10;
	public static final int ARMOR_RAT_KING  = ARMOR+11;
	static{
		assignItemRect(ARMOR_CLOTH,     15, 12);
		assignItemRect(ARMOR_LEATHER,   14, 13);
		assignItemRect(ARMOR_MAIL,      14, 12);
		assignItemRect(ARMOR_SCALE,     14, 11);
		assignItemRect(ARMOR_PLATE,     12, 12);
		assignItemRect(ARMOR_WARRIOR,   12, 12);
		assignItemRect(ARMOR_MAGE,      15, 15);
		assignItemRect(ARMOR_ROGUE,     14, 12);
		assignItemRect(ARMOR_HUNTRESS,  13, 15);
		assignItemRect(ARMOR_DUELIST,   12, 13);
		assignItemRect(ARMOR_CLERIC,    13, 14);
		assignItemRect(ARMOR_RAT_KING, 12, 6);
	}

	                                                                                    //16 free slots

	private static final int WANDS              =                           xy(1, 14);  //16 slots
	public static final int WAND_MAGIC_MISSILE  = WANDS+0;
	public static final int WAND_FIREBOLT       = WANDS+1;
	public static final int WAND_FROST          = WANDS+2;
	public static final int WAND_LIGHTNING      = WANDS+3;
	public static final int WAND_DISINTEGRATION = WANDS+4;
	public static final int WAND_PRISMATIC_LIGHT= WANDS+5;
	public static final int WAND_CORROSION      = WANDS+6;
	public static final int WAND_LIVING_EARTH   = WANDS+7;
	public static final int WAND_BLAST_WAVE     = WANDS+8;
	public static final int WAND_CORRUPTION     = WANDS+9;
	public static final int WAND_WARDING        = WANDS+10;
	public static final int WAND_REGROWTH       = WANDS+11;
	public static final int WAND_TRANSFUSION    = WANDS+12;
	static {
		for (int i = WANDS; i < WANDS+16; i++)
			assignItemRect(i, 14, 14);
	}

	private static final int RINGS          =                               xy(1, 15);  //16 slots
	public static final int RING_GARNET     = RINGS+0;
	public static final int RING_RUBY       = RINGS+1;
	public static final int RING_TOPAZ      = RINGS+2;
	public static final int RING_EMERALD    = RINGS+3;
	public static final int RING_ONYX       = RINGS+4;
	public static final int RING_OPAL       = RINGS+5;
	public static final int RING_TOURMALINE = RINGS+6;
	public static final int RING_SAPPHIRE   = RINGS+7;
	public static final int RING_AMETHYST   = RINGS+8;
	public static final int RING_QUARTZ     = RINGS+9;
	public static final int RING_AGATE      = RINGS+10;
	public static final int RING_DIAMOND    = RINGS+11;
	static {
		for (int i = RINGS; i < RINGS+16; i++)
			assignItemRect(i, 8, 10);
	}

	private static final int ARTIFACTS          =                            xy(1, 16);  //24 slots
	public static final int ARTIFACT_CLOAK      = ARTIFACTS+0;
	public static final int ARTIFACT_ARMBAND    = ARTIFACTS+1;
	public static final int ARTIFACT_CAPE       = ARTIFACTS+2;
	public static final int ARTIFACT_TALISMAN   = ARTIFACTS+3;
	public static final int ARTIFACT_HOURGLASS  = ARTIFACTS+4;
	public static final int ARTIFACT_TOOLKIT    = ARTIFACTS+5;
	public static final int ARTIFACT_SPELLBOOK  = ARTIFACTS+6;
	public static final int ARTIFACT_BEACON     = ARTIFACTS+7;
	public static final int ARTIFACT_CHAINS     = ARTIFACTS+8;
	public static final int ARTIFACT_HORN1      = ARTIFACTS+9;
	public static final int ARTIFACT_HORN2      = ARTIFACTS+10;
	public static final int ARTIFACT_HORN3      = ARTIFACTS+11;
	public static final int ARTIFACT_HORN4      = ARTIFACTS+12;
	public static final int ARTIFACT_CHALICE1   = ARTIFACTS+13;
	public static final int ARTIFACT_CHALICE2   = ARTIFACTS+14;
	public static final int ARTIFACT_CHALICE3   = ARTIFACTS+15;
	public static final int ARTIFACT_SANDALS    = ARTIFACTS+16;
	public static final int ARTIFACT_SHOES      = ARTIFACTS+17;
	public static final int ARTIFACT_BOOTS      = ARTIFACTS+18;
	public static final int ARTIFACT_GREAVES    = ARTIFACTS+19;
	public static final int ARTIFACT_ROSE1      = ARTIFACTS+20;
	public static final int ARTIFACT_ROSE2      = ARTIFACTS+21;
	public static final int ARTIFACT_ROSE3      = ARTIFACTS+22;
	public static final int ARTIFACT_TOME       = ARTIFACTS+23;
	static{
		assignItemRect(ARTIFACT_CLOAK,      9,  15);
		assignItemRect(ARTIFACT_ARMBAND,    16, 13);
		assignItemRect(ARTIFACT_CAPE,       16, 14);
		assignItemRect(ARTIFACT_TALISMAN,   15, 13);
		assignItemRect(ARTIFACT_HOURGLASS,  13, 16);
		assignItemRect(ARTIFACT_TOOLKIT,    15, 13);
		assignItemRect(ARTIFACT_SPELLBOOK,  13, 16);
		assignItemRect(ARTIFACT_BEACON,     16, 16);
		assignItemRect(ARTIFACT_CHAINS,     16, 16);
		assignItemRect(ARTIFACT_HORN1,      15, 15);
		assignItemRect(ARTIFACT_HORN2,      15, 15);
		assignItemRect(ARTIFACT_HORN3,      15, 15);
		assignItemRect(ARTIFACT_HORN4,      15, 15);
		assignItemRect(ARTIFACT_CHALICE1,   12, 15);
		assignItemRect(ARTIFACT_CHALICE2,   12, 15);
		assignItemRect(ARTIFACT_CHALICE3,   12, 15);
		assignItemRect(ARTIFACT_SANDALS,    16, 6 );
		assignItemRect(ARTIFACT_SHOES,      16, 6 );
		assignItemRect(ARTIFACT_BOOTS,      16, 9 );
		assignItemRect(ARTIFACT_GREAVES,    16, 14);
		assignItemRect(ARTIFACT_ROSE1,      14, 14);
		assignItemRect(ARTIFACT_ROSE2,      14, 14);
		assignItemRect(ARTIFACT_ROSE3,      14, 14);
		assignItemRect(ARTIFACT_TOME,       14, 16);
	}

	private static final int TRINKETS        =                               xy(9, 17);  //24 slots
	public static final int RAT_SKULL       = TRINKETS+0;
	public static final int PARCHMENT_SCRAP = TRINKETS+1;
	public static final int PETRIFIED_SEED  = TRINKETS+2;
	public static final int EXOTIC_CRYSTALS = TRINKETS+3;
	public static final int MOSSY_CLUMP     = TRINKETS+4;
	public static final int SUNDIAL         = TRINKETS+5;
	public static final int CLOVER          = TRINKETS+6;
	public static final int TRAP_MECHANISM  = TRINKETS+7;
	public static final int MIMIC_TOOTH     = TRINKETS+8;
	public static final int WONDROUS_RESIN  = TRINKETS+9;
	public static final int EYE_OF_NEWT     = TRINKETS+10;
	public static final int SALT_CUBE       = TRINKETS+11;
	public static final int BLOOD_VIAL      = TRINKETS+12;
	public static final int OBLIVION_SHARD  = TRINKETS+13;
	public static final int CHAOTIC_CENSER  = TRINKETS+14;
	public static final int FERRET_TUFT     = TRINKETS+15;
	static{
		assignItemRect(RAT_SKULL,       16, 11);
		assignItemRect(PARCHMENT_SCRAP, 10, 14);
		assignItemRect(PETRIFIED_SEED,  10, 10);
		assignItemRect(EXOTIC_CRYSTALS, 14, 13);
		assignItemRect(MOSSY_CLUMP,     12, 11);
		assignItemRect(SUNDIAL,         16, 12);
		assignItemRect(CLOVER,          11, 15);
		assignItemRect(TRAP_MECHANISM,  13, 15);
		assignItemRect(MIMIC_TOOTH,     8,  15);
		assignItemRect(WONDROUS_RESIN,  12, 11);
		assignItemRect(EYE_OF_NEWT,     12, 12);
		assignItemRect(SALT_CUBE,       12, 13);
		assignItemRect(BLOOD_VIAL,      6,  15);
		assignItemRect(OBLIVION_SHARD,  7,  14);
		assignItemRect(CHAOTIC_CENSER,  13, 15);
		assignItemRect(FERRET_TUFT,     16, 15);
	}

	private static final int SCROLLS        =                               xy(1, 19);  //16 slots
	public static final int SCROLL_KAUNAN   = SCROLLS+0;
	public static final int SCROLL_SOWILO   = SCROLLS+1;
	public static final int SCROLL_LAGUZ    = SCROLLS+2;
	public static final int SCROLL_YNGVI    = SCROLLS+3;
	public static final int SCROLL_GYFU     = SCROLLS+4;
	public static final int SCROLL_RAIDO    = SCROLLS+5;
	public static final int SCROLL_ISAZ     = SCROLLS+6;
	public static final int SCROLL_MANNAZ   = SCROLLS+7;
	public static final int SCROLL_NAUDIZ   = SCROLLS+8;
	public static final int SCROLL_BERKANAN = SCROLLS+9;
	public static final int SCROLL_ODAL     = SCROLLS+10;
	public static final int SCROLL_TIWAZ    = SCROLLS+11;

	public static final int ARCANE_RESIN    = SCROLLS+13;
	static {
		for (int i = SCROLLS; i < SCROLLS+16; i++)
			assignItemRect(i, 15, 14);
		assignItemRect(ARCANE_RESIN   , 12, 11);
	}
	
	private static final int EXOTIC_SCROLLS =                               xy(1, 20);  //16 slots
	public static final int EXOTIC_KAUNAN   = EXOTIC_SCROLLS+0;
	public static final int EXOTIC_SOWILO   = EXOTIC_SCROLLS+1;
	public static final int EXOTIC_LAGUZ    = EXOTIC_SCROLLS+2;
	public static final int EXOTIC_YNGVI    = EXOTIC_SCROLLS+3;
	public static final int EXOTIC_GYFU     = EXOTIC_SCROLLS+4;
	public static final int EXOTIC_RAIDO    = EXOTIC_SCROLLS+5;
	public static final int EXOTIC_ISAZ     = EXOTIC_SCROLLS+6;
	public static final int EXOTIC_MANNAZ   = EXOTIC_SCROLLS+7;
	public static final int EXOTIC_NAUDIZ   = EXOTIC_SCROLLS+8;
	public static final int EXOTIC_BERKANAN = EXOTIC_SCROLLS+9;
	public static final int EXOTIC_ODAL     = EXOTIC_SCROLLS+10;
	public static final int EXOTIC_TIWAZ    = EXOTIC_SCROLLS+11;
	static {
		for (int i = EXOTIC_SCROLLS; i < EXOTIC_SCROLLS+16; i++)
			assignItemRect(i, 15, 14);
	}
	
	private static final int STONES             =                           xy(1, 21);  //16 slots
	public static final int STONE_AGGRESSION    = STONES+0;
	public static final int STONE_AUGMENTATION  = STONES+1;
	public static final int STONE_FEAR          = STONES+2;
	public static final int STONE_BLAST         = STONES+3;
	public static final int STONE_BLINK         = STONES+4;
	public static final int STONE_CLAIRVOYANCE  = STONES+5;
	public static final int STONE_SLEEP         = STONES+6;
	public static final int STONE_DETECT        = STONES+7;
	public static final int STONE_ENCHANT       = STONES+8;
	public static final int STONE_FLOCK         = STONES+9;
	public static final int STONE_INTUITION     = STONES+10;
	public static final int STONE_SHOCK         = STONES+11;
	static {
		for (int i = STONES; i < STONES+16; i++)
			assignItemRect(i, 14, 12);
	}

	private static final int POTIONS        =                               xy(1, 22);  //16 slots
	public static final int POTION_CRIMSON  = POTIONS+0;
	public static final int POTION_AMBER    = POTIONS+1;
	public static final int POTION_GOLDEN   = POTIONS+2;
	public static final int POTION_JADE     = POTIONS+3;
	public static final int POTION_TURQUOISE= POTIONS+4;
	public static final int POTION_AZURE    = POTIONS+5;
	public static final int POTION_INDIGO   = POTIONS+6;
	public static final int POTION_MAGENTA  = POTIONS+7;
	public static final int POTION_BISTRE   = POTIONS+8;
	public static final int POTION_CHARCOAL = POTIONS+9;
	public static final int POTION_SILVER   = POTIONS+10;
	public static final int POTION_IVORY    = POTIONS+11;

	public static final int LIQUID_METAL    = POTIONS+13;
	static {
		for (int i = POTIONS; i < POTIONS+16; i++)
			assignItemRect(i, 12, 14);
		assignItemRect(LIQUID_METAL,    8, 15);
	}
	
	private static final int EXOTIC_POTIONS =                               xy(1, 23);  //16 slots
	public static final int EXOTIC_CRIMSON  = EXOTIC_POTIONS+0;
	public static final int EXOTIC_AMBER    = EXOTIC_POTIONS+1;
	public static final int EXOTIC_GOLDEN   = EXOTIC_POTIONS+2;
	public static final int EXOTIC_JADE     = EXOTIC_POTIONS+3;
	public static final int EXOTIC_TURQUOISE= EXOTIC_POTIONS+4;
	public static final int EXOTIC_AZURE    = EXOTIC_POTIONS+5;
	public static final int EXOTIC_INDIGO   = EXOTIC_POTIONS+6;
	public static final int EXOTIC_MAGENTA  = EXOTIC_POTIONS+7;
	public static final int EXOTIC_BISTRE   = EXOTIC_POTIONS+8;
	public static final int EXOTIC_CHARCOAL = EXOTIC_POTIONS+9;
	public static final int EXOTIC_SILVER   = EXOTIC_POTIONS+10;
	public static final int EXOTIC_IVORY    = EXOTIC_POTIONS+11;
	static {
		for (int i = EXOTIC_POTIONS; i < EXOTIC_POTIONS+16; i++)
			assignItemRect(i, 12, 13);
	}

	private static final int SEEDS              =                           xy(1, 24);  //16 slots
	public static final int SEED_ROTBERRY       = SEEDS+0;
	public static final int SEED_FIREBLOOM      = SEEDS+1;
	public static final int SEED_SWIFTTHISTLE   = SEEDS+2;
	public static final int SEED_SUNGRASS       = SEEDS+3;
	public static final int SEED_ICECAP         = SEEDS+4;
	public static final int SEED_STORMVINE      = SEEDS+5;
	public static final int SEED_SORROWMOSS     = SEEDS+6;
	public static final int SEED_MAGEROYAL = SEEDS+7;
	public static final int SEED_EARTHROOT      = SEEDS+8;
	public static final int SEED_STARFLOWER     = SEEDS+9;
	public static final int SEED_FADELEAF       = SEEDS+10;
	public static final int SEED_BLINDWEED      = SEEDS+11;
	static{
		for (int i = SEEDS; i < SEEDS+16; i++)
			assignItemRect(i, 10, 10);
	}
	
	private static final int BREWS          =                               xy(1, 25);  //8 slots
	public static final int BREW_INFERNAL   = BREWS+0;
	public static final int BREW_BLIZZARD   = BREWS+1;
	public static final int BREW_SHOCKING   = BREWS+2;
	public static final int BREW_CAUSTIC    = BREWS+3;
	public static final int BREW_AQUA       = BREWS+4;
	public static final int BREW_UNSTABLE   = BREWS+5;
	
	private static final int ELIXIRS        =                               xy(9, 25);  //8 slots
	public static final int ELIXIR_HONEY    = ELIXIRS+0;
	public static final int ELIXIR_AQUA     = ELIXIRS+1;
	public static final int ELIXIR_MIGHT    = ELIXIRS+2;
	public static final int ELIXIR_DRAGON   = ELIXIRS+3;
	public static final int ELIXIR_TOXIC    = ELIXIRS+4;
	public static final int ELIXIR_ICY      = ELIXIRS+5;
	public static final int ELIXIR_ARCANE   = ELIXIRS+6;
	public static final int ELIXIR_FEATHER  = ELIXIRS+7;
	static{
		for (int i = BREWS; i < BREWS+16; i++)
			assignItemRect(i, 12, 14);

		assignItemRect(BREW_AQUA, 9, 11);
	}
	
	                                                                                    //16 free slots
	
	private static final int SPELLS         =                               xy(1, 27);  //16 slots
	public static final int WILD_ENERGY     = SPELLS+0;
	public static final int PHASE_SHIFT     = SPELLS+1;
	public static final int TELE_GRAB       = SPELLS+2;
	public static final int UNSTABLE_SPELL  = SPELLS+3;

	public static final int CURSE_INFUSE    = SPELLS+5;
	public static final int MAGIC_INFUSE    = SPELLS+6;
	public static final int ALCHEMIZE       = SPELLS+7;
	public static final int RECYCLE         = SPELLS+8;

	public static final int RECLAIM_TRAP    = SPELLS+10;
	public static final int RETURN_BEACON   = SPELLS+11;
	public static final int SUMMON_ELE      = SPELLS+12;

	static{
		assignItemRect(WILD_ENERGY,     12, 11);
		assignItemRect(PHASE_SHIFT,     12, 11);
		assignItemRect(TELE_GRAB,       12, 11);
		assignItemRect(UNSTABLE_SPELL,  12, 13);

		assignItemRect(CURSE_INFUSE,    10, 15);
		assignItemRect(MAGIC_INFUSE,    10, 15);
		assignItemRect(ALCHEMIZE,       10, 15);
		assignItemRect(RECYCLE,         10, 15);

		assignItemRect(RECLAIM_TRAP,     8, 16);
		assignItemRect(RETURN_BEACON,    8, 16);
		assignItemRect(SUMMON_ELE,       8, 16);
	}
	
	private static final int FOOD       =                                   xy(1, 28);  //16 slots
	public static final int MEAT            = FOOD+0;
	public static final int STEAK           = FOOD+1;
	public static final int STEWED          = FOOD+2;
	public static final int OVERPRICED      = FOOD+3;
	public static final int CARPACCIO       = FOOD+4;
	public static final int RATION          = FOOD+5;
	public static final int PASTY           = FOOD+6;
	public static final int MEAT_PIE        = FOOD+7;
	public static final int BLANDFRUIT      = FOOD+8;
	public static final int BLAND_CHUNKS    = FOOD+9;
	public static final int BERRY           = FOOD+10;
	public static final int PHANTOM_MEAT    = FOOD+11;
	public static final int SUPPLY_RATION   = FOOD+12;
	static{
		assignItemRect(MEAT,            15, 11);
		assignItemRect(STEAK,           15, 11);
		assignItemRect(STEWED,          15, 11);
		assignItemRect(OVERPRICED,      14, 11);
		assignItemRect(CARPACCIO,       15, 11);
		assignItemRect(RATION,          16, 12);
		assignItemRect(PASTY,           16, 11);
		assignItemRect(MEAT_PIE,        16, 12);
		assignItemRect(BLANDFRUIT,      9,  12);
		assignItemRect(BLAND_CHUNKS,    14,  6);
		assignItemRect(BERRY,           9,  11);
		assignItemRect(PHANTOM_MEAT,    15, 11);
		assignItemRect(SUPPLY_RATION,   16, 12);
	}

	private static final int HOLIDAY_FOOD   =                               xy(1, 29);  //16 slots
	public static final int STEAMED_FISH    = HOLIDAY_FOOD+0;
	public static final int FISH_LEFTOVER   = HOLIDAY_FOOD+1;
	public static final int CHOC_AMULET     = HOLIDAY_FOOD+2;
	public static final int EASTER_EGG      = HOLIDAY_FOOD+3;
	public static final int RAINBOW_POTION  = HOLIDAY_FOOD+4;
	public static final int SHATTERED_CAKE  = HOLIDAY_FOOD+5;
	public static final int PUMPKIN_PIE     = HOLIDAY_FOOD+6;
	public static final int VANILLA_CAKE    = HOLIDAY_FOOD+7;
	public static final int CANDY_CANE      = HOLIDAY_FOOD+8;
	public static final int SPARKLING_POTION= HOLIDAY_FOOD+9;
	static{
		assignItemRect(STEAMED_FISH,    16, 12);
		assignItemRect(FISH_LEFTOVER,   16, 12);
		assignItemRect(CHOC_AMULET,     16, 16);
		assignItemRect(EASTER_EGG,      12, 14);
		assignItemRect(RAINBOW_POTION,  12, 14);
		assignItemRect(SHATTERED_CAKE,  14, 13);
		assignItemRect(PUMPKIN_PIE,     16, 12);
		assignItemRect(VANILLA_CAKE,    14, 13);
		assignItemRect(CANDY_CANE,      13, 16);
		assignItemRect(SPARKLING_POTION, 7, 16);
	}

	private static final int QUEST  =                                       xy(1, 30);  //16 slots
	public static final int DUST    = QUEST+1;
	public static final int CANDLE  = QUEST+2;
	public static final int EMBER   = QUEST+3;
	public static final int PICKAXE = QUEST+4;
	public static final int ORE     = QUEST+5;
	public static final int TOKEN   = QUEST+6;
	public static final int BLOB    = QUEST+7;
	public static final int SHARD   = QUEST+8;
	public static final int CHAOSSTONE = QUEST+9;
	public static final int RUNIC_CLUMP= QUEST+10;
	public static final int RED_CRYSTAL= QUEST+11;
	static{
		assignItemRect(DUST,    12, 11);
		assignItemRect(CANDLE,  12, 12);
		assignItemRect(EMBER,   12, 11);
		assignItemRect(PICKAXE, 14, 14);
		assignItemRect(ORE,     15, 15);
		assignItemRect(TOKEN,   12, 12);
		assignItemRect(BLOB,    10,  9);
		assignItemRect(SHARD,    8, 10);
		assignItemRect(CHAOSSTONE,  10, 15);
		assignItemRect(RUNIC_CLUMP, 12, 11);
		assignItemRect(RED_CRYSTAL, 10, 15);
	}

	private static final int BAGS       =                                   xy(1, 31);  //16 slots
	public static final int WATERSKIN   = BAGS+0;
	public static final int BACKPACK    = BAGS+1;
	public static final int POUCH       = BAGS+2;
	public static final int HOLDER      = BAGS+3;
	public static final int BANDOLIER   = BAGS+4;
	public static final int HOLSTER     = BAGS+5;
	public static final int VIAL        = BAGS+6;
	public static final int CHEEST      = BAGS+7;
	static{
		assignItemRect(WATERSKIN,   16, 14);
		assignItemRect(BACKPACK,    16, 16);
		assignItemRect(POUCH,       14, 15);
		assignItemRect(HOLDER,      16, 16);
		assignItemRect(BANDOLIER,   15, 16);
		assignItemRect(HOLSTER,     15, 16);
		assignItemRect(VIAL,        12, 12);
		assignItemRect(CHEEST,      16, 14);
	}

	private static final int DOCUMENTS  =                                   xy(1, 32);  //16 slots
	public static final int GUIDE_PAGE  = DOCUMENTS+0;
	public static final int ALCH_PAGE   = DOCUMENTS+1;
	public static final int SEWER_PAGE  = DOCUMENTS+2;
	public static final int PRISON_PAGE = DOCUMENTS+3;
	public static final int CAVES_PAGE  = DOCUMENTS+4;
	public static final int CITY_PAGE   = DOCUMENTS+5;
	public static final int HALLS_PAGE  = DOCUMENTS+6;
	public static final int LABS_PAGE   = DOCUMENTS+7;
	public static final int SEED_PAGE  = DOCUMENTS+8;
	static{
		assignItemRect(GUIDE_PAGE,  10, 11);
		assignItemRect(ALCH_PAGE,   10, 11);
		assignItemRect(SEWER_PAGE,  10, 11);
		assignItemRect(PRISON_PAGE, 10, 11);
		assignItemRect(CAVES_PAGE,  10, 11);
		assignItemRect(CITY_PAGE,   10, 11);
		assignItemRect(HALLS_PAGE,  10, 11);
		assignItemRect(LABS_PAGE,  	10, 11);
		assignItemRect(SEED_PAGE,   10, 11);
	}

	// ****** new sprites ******

//	private static final int 			  	=			xy(1, );  //16 slots
//	public static final int 				= +0;
//	public static final int 				= +1;
//	public static final int 				= +2;
//	public static final int 				= +3;
//	public static final int 				= +4;
//	public static final int 				= +5;
//	public static final int 				= +6;
//	static{
//		assignItemRect(, 16, 16);
//		assignItemRect(, 16, 16);
//		assignItemRect(, 16, 16);
//		assignItemRect(, 16, 16);
//		assignItemRect(, 16, 16);
//		assignItemRect(, 16, 16);
//		assignItemRect(, 16, 16);
//	}

	//free 16 slots

	//free 16 slots

	private static final int NEW_SPECIAL_ITEM	=		xy(1, 35);  //16 slots
	public static final int AMMO_BELT			= NEW_SPECIAL_ITEM+0;
	public static final int SHEATH				= NEW_SPECIAL_ITEM+1;
	public static final int KNIGHT_SHIELD		= NEW_SPECIAL_ITEM+2;
	public static final int ARROW_BAG			= NEW_SPECIAL_ITEM+3;

	public static final int TELEPORTER			= NEW_SPECIAL_ITEM+5;
	static{
		assignItemRect(AMMO_BELT	, 15, 15);
		assignItemRect(SHEATH		, 16, 16);
		assignItemRect(KNIGHT_SHIELD, 16, 15);
		assignItemRect(ARROW_BAG	, 14, 15);

		assignItemRect(TELEPORTER	, 14, 14);
	}

	private static final int NEW_ARMOR	 	=			xy(1, 36);  //16 slots
	public static final int ARMOR_GUNNER	= NEW_ARMOR+0;
	public static final int ARMOR_SAMURAI	= NEW_ARMOR+1;
	public static final int ARMOR_PLANTER	= NEW_ARMOR+2;
	public static final int ARMOR_KNIGHT	= NEW_ARMOR+3;
	public static final int ARMOR_MEDIC 	= NEW_ARMOR+4;
	public static final int ARMOR_ARCHER 	= NEW_ARMOR+5;
	static{
		assignItemRect(ARMOR_GUNNER	, 15, 13);
		assignItemRect(ARMOR_SAMURAI, 12, 16);
		assignItemRect(ARMOR_PLANTER, 15, 12);
		assignItemRect(ARMOR_KNIGHT	, 14, 12);
		assignItemRect(ARMOR_MEDIC	, 14, 12);
		assignItemRect(ARMOR_ARCHER	, 12, 11);
	}

	private static final int NEW_SCROLL 	=			xy(1, 37);  //16 slots
	public static final int SCROLL_PLUS		= NEW_SCROLL+0;
	public static final int BLUEPRINT		= NEW_SCROLL+1;
	static{
		assignItemRect(SCROLL_PLUS, 15, 14);
		assignItemRect(BLUEPRINT, 15, 14);
	}

	private static final int NEW_EXOTIC_SCROLL 	=		xy(1, 38);  //16 slots
	public static final int EXOTIC_SCROLL_PLUS	= NEW_EXOTIC_SCROLL+0;
	static{
		assignItemRect(EXOTIC_SCROLL_PLUS, 15, 14);
	}

	private static final int NEW_RINGS =			xy(1, 39);  //16 slots
	public static final int RING_OBSIDIAN   = NEW_RINGS +0;
	public static final int RING_PEARL   	= NEW_RINGS +1;
	public static final int RING_GOLD   	= NEW_RINGS +2;
	public static final int RING_EMBER   	= NEW_RINGS +3;
	public static final int RING_IOLITE		= NEW_RINGS +4;
	public static final int RING_AQUAMARINE = NEW_RINGS +5;
	public static final int RING_JADE		= NEW_RINGS +6;
	static{
		for (int i = NEW_RINGS; i < NEW_RINGS +6; i++)
			assignItemRect(i, 8, 10);
	}

	private static final int NEW_PLACEHOLDERS 	=			xy(1, 40);  //16 slots
	public static final int SPELLBOOK_HOLDER    = NEW_PLACEHOLDERS +0;
	public static final int PILLS_HOLDER   	    = NEW_PLACEHOLDERS +1;
	static{
		assignItemRect(SPELLBOOK_HOLDER	, 10, 14);
		assignItemRect(PILLS_HOLDER	, 11, 11);
	}


	private static final int BULLET_ITEM	=			xy(1, 41);  //16 slots
	public static final int BULLET		= BULLET_ITEM+0;
	public static final int HP_BULLET	= BULLET_ITEM+1;
	public static final int AP_BULLET	= BULLET_ITEM+2;
	public static final int CARTRIDGE	= BULLET_ITEM+3;
	public static final int BULLET_BELT	= BULLET_ITEM+4;
	public static final int OLD_AMULET	= BULLET_ITEM+5;
	public static final int GUNSMITHING_TOOL= BULLET_ITEM+6;
	static{

		assignItemRect(BULLET		, 13, 13);
		assignItemRect(HP_BULLET	, 13, 13);
		assignItemRect(AP_BULLET	, 13, 13);
		assignItemRect(CARTRIDGE	, 11, 11);
		assignItemRect(BULLET_BELT	, 15, 15);
		assignItemRect(OLD_AMULET	, 16, 16);
		assignItemRect(GUNSMITHING_TOOL	, 16, 13);
	}

	private static final int BULLETS		=			xy(1, 42);  //16 slots
	public static final int SINGLE_BULLET	= BULLETS+0;
	public static final int DOUBLE_BULLET	= BULLETS+1;
	public static final int TRIPLE_BULLET	= BULLETS+2;
	public static final int SNIPER_BULLET	= BULLETS+3;
	public static final int GHOST_BULLET	= BULLETS+4;
	public static final int NO_BULLET		= BULLETS+5;
	static{
		assignItemRect(SINGLE_BULLET	, 8, 8);
		assignItemRect(DOUBLE_BULLET	, 11, 10);
		assignItemRect(TRIPLE_BULLET	, 11, 11);
		assignItemRect(SNIPER_BULLET	, 8, 8);
		assignItemRect(GHOST_BULLET		, 8, 8);
		assignItemRect(NO_BULLET		, 0, 0);
	}

	private static final int SPECIAL_BULLETS=			xy(1, 43);  //16 slots
	public static final int GRENADE_GREEN	= SPECIAL_BULLETS+0;
	public static final int GRENADE_RED		= SPECIAL_BULLETS+1;
	public static final int GRENADE_WHITE	= SPECIAL_BULLETS+2;
	public static final int ROCKET			= SPECIAL_BULLETS+3;
	public static final int BATTERY_SINGLE	= SPECIAL_BULLETS+4;
	public static final int BATTERY_DOUBLE	= SPECIAL_BULLETS+5;
	public static final int BATTERY_POWER	= SPECIAL_BULLETS+6;
	public static final int SWORD_AURA		= SPECIAL_BULLETS+7;
	static{
		assignItemRect(GRENADE_GREEN	, 7, 7);
		assignItemRect(GRENADE_RED		, 7, 7);
		assignItemRect(GRENADE_WHITE	, 7, 7);
		assignItemRect(ROCKET			, 9, 9);
		assignItemRect(BATTERY_SINGLE	, 10, 10);
		assignItemRect(BATTERY_DOUBLE	, 12, 12);
		assignItemRect(BATTERY_POWER	, 12, 12);
		assignItemRect(SWORD_AURA		, 15, 15);
	}

	private static final int ARROWS			=			xy(1, 44);  //16 slots
	public static final int ARROW_WIND		= ARROWS+0;
	public static final int ARROW_NATURE	= ARROWS+1;
	public static final int ARROW_GOLD		= ARROWS+2;
	public static final int ARROW_CORROSION	= ARROWS+3;
	public static final int ARROW_TACTICAL	= ARROWS+4;
	public static final int ARROW_PHASE		= ARROWS+5;
	public static final int ARROW_ELECTRIC	= ARROWS+6;
	public static final int ARROW_MAGICAL	= ARROWS+7;
	static{
		assignItemRect(ARROW_WIND		, 11, 11);
		assignItemRect(ARROW_NATURE		, 11, 11);
		assignItemRect(ARROW_GOLD		, 11, 11);
		assignItemRect(ARROW_CORROSION	, 11, 11);
		assignItemRect(ARROW_TACTICAL	, 11, 11);
		assignItemRect(ARROW_PHASE		, 11, 11);
		assignItemRect(ARROW_ELECTRIC	, 11, 11);
		assignItemRect(ARROW_MAGICAL	, 11, 11);
	}

	private static final int NEW_WEP_TIER_1	=			xy(1, 45);  //8 slots
	public static final int WORN_KATANA		= NEW_WEP_TIER_1+0;
	public static final int SABER			= NEW_WEP_TIER_1+1;
	public static final int HEALING_BOOK	= NEW_WEP_TIER_1+2;
	static{
		assignItemRect(WORN_KATANA	, 13, 13);
		assignItemRect(SABER		, 13, 15);
		assignItemRect(HEALING_BOOK	, 13, 15);
	}

	private static final int NEW_WEP_TIER_2	=			xy(9, 45);  //8 slots
	public static final int SHORT_KATANA	= NEW_WEP_TIER_2+0;
	public static final int KNIFE			= NEW_WEP_TIER_2+1;
	public static final int NUNCHAKU		= NEW_WEP_TIER_2+2;
	public static final int DUAL_DAGGER		= NEW_WEP_TIER_2+3;
	static{
		assignItemRect(SHORT_KATANA	, 14, 14);
		assignItemRect(KNIFE		, 12, 13);
		assignItemRect(NUNCHAKU		, 16, 16);
		assignItemRect(DUAL_DAGGER	, 16, 16);
	}

	private static final int NEW_WEP_TIER_3	=			xy(1, 46);  //8 slots
	public static final int NORMAL_KATANA	= NEW_WEP_TIER_3+0;
	public static final int BIBLE			= NEW_WEP_TIER_3+1;
	public static final int RUNE_DAGGER		= NEW_WEP_TIER_3+2;
	static{
		assignItemRect(NORMAL_KATANA, 14, 15);
		assignItemRect(BIBLE		, 13, 16);
		assignItemRect(RUNE_DAGGER	, 13, 14);
	}

	private static final int NEW_WEP_TIER_4 =			xy(9, 46);  //8 slots
	public static final int LONG_KATANA		= NEW_WEP_TIER_4+0;
	static{
		assignItemRect(LONG_KATANA, 15, 16);
	}

	private static final int NEW_WEP_TIER_5	=			xy(1, 47);  //8 slots
	public static final int LARGE_KATANA= NEW_WEP_TIER_5+0;
	public static final int LARGE_SHORD	= NEW_WEP_TIER_5+1;
	static{
		assignItemRect(LARGE_KATANA	, 12, 16);
		assignItemRect(LARGE_SHORD	, 14, 16);
	}

//	private static final int NEW_WEP_TIER_6	=			xy(9, 47);  //8 slots
//	static{
//	}

	private static final int HG	  	=			xy(1, 48);  //8 slots
	public static final int HG_T1		= HG+0;
	public static final int HG_T2		= HG+1;
	public static final int HG_T3		= HG+2;
	public static final int HG_T4		= HG+3;
	public static final int HG_T5		= HG+4;
	public static final int HG_AL		= HG+5;
	public static final int HG_T6		= HG+6;
	static{
		assignItemRect(HG_T1, 10, 13);
		assignItemRect(HG_T2, 11, 15);
		assignItemRect(HG_T3, 12, 15);
		assignItemRect(HG_T4, 13, 16);
		assignItemRect(HG_T5, 12, 16);
		assignItemRect(HG_AL, 12, 15);
		assignItemRect(HG_T6, 16, 16);
	}

	private static final int SMG	=			xy(9, 48);  //8 slots
//	public static final int SMG_T1		= SMG+0;
	public static final int SMG_T2		= SMG+1;
//	public static final int SMG_T3		= SMG+2;
	public static final int SMG_T4		= SMG+3;
	public static final int SMG_T5		= SMG+4;
//	public static final int SMG_AL		= SMG+5;
//	public static final int SMG_T6		= SMG+6;
	static{
//		assignItemRect(SMG_T1, 16, 16);
		assignItemRect(SMG_T2, 15, 15);
//		assignItemRect(SMG_T3, 16, 16);
		assignItemRect(SMG_T4, 13, 14);
		assignItemRect(SMG_T5, 15, 15);
//		assignItemRect(SMG_AL, 16, 16);
//		assignItemRect(SMG_T6, 16, 16);
	}

	private static final int MG	  	=			xy(1, 49);  //8 slots
//	public static final int MG_T1		= MG+0;
//	public static final int MG_T2		= MG+1;
	public static final int MG_T3		= MG+2;
//	public static final int MG_T4		= MG+3;
	public static final int MG_T5		= MG+4;
//	public static final int MG_AL		= MG+5;
//	public static final int MG_T6		= MG+6;
	static{
//		assignItemRect(MG_T1, 16, 16);
//		assignItemRect(MG_T2, 16, 16);
		assignItemRect(MG_T3, 13, 15);
//		assignItemRect(MG_T4, 16, 16);
		assignItemRect(MG_T5, 16, 15);
//		assignItemRect(MG_AL, 16, 16);
//		assignItemRect(MG_T6, 16, 16);
	}



	private static final int SG	  	=			xy(9, 49);  //8 slots
//	public static final int SG_T1		= SG+0;
//	public static final int SG_T2		= SG+1;
	public static final int SG_T3		= SG+2;
//	public static final int SG_T4		= SG+3;
	public static final int SG_T5		= SG+4;
//	public static final int SG_AL		= SG+5;
//	public static final int SG_T6		= SG+6;
	static{
//		assignItemRect(SG_T1, 16, 16);
//		assignItemRect(SG_T2, 16, 16);
		assignItemRect(SG_T3, 14, 16);
//		assignItemRect(SG_T4, 16, 16);
		assignItemRect(SG_T5, 15, 16);
//		assignItemRect(SG_AL, 16, 16);
//		assignItemRect(SG_T6, 16, 16);
	}

	private static final int SR			  	=			xy(1, 50);  //8 slots
	public static final int SR_T1			= SR+0;
	public static final int SR_T2			= SR+1;
	public static final int SR_T3			= SR+2;
	public static final int SR_T4			= SR+3;
	public static final int SR_T5			= SR+4;
	public static final int SR_AL			= SR+5;
	public static final int SR_T6			= SR+6;
	static{
		assignItemRect(SR_T1, 11, 15);
		assignItemRect(SR_T2, 13, 16);
		assignItemRect(SR_T3, 13, 16);
		assignItemRect(SR_T4, 14, 16);
		assignItemRect(SR_T5, 15, 16);
		assignItemRect(SR_AL, 16, 16);
		assignItemRect(SR_T6, 15, 16);
	}

	private static final int AR			  	=			xy(9, 50);  //8 slots
	public static final int AR_T1			= AR+0;
	public static final int AR_T2			= AR+1;
	public static final int AR_T3			= AR+2;
	public static final int AR_T4			= AR+3;
	public static final int AR_T5			= AR+4;
	public static final int AR_AL			= AR+5;
	public static final int AR_T6			= AR+6;
	static{
		assignItemRect(AR_T1, 12, 13);
		assignItemRect(AR_T2, 13, 14);
		assignItemRect(AR_T3, 15, 16);
		assignItemRect(AR_T4, 16, 15);
		assignItemRect(AR_T5, 15, 16);
		assignItemRect(AR_AL, 16, 16);
		assignItemRect(AR_T6, 15, 16);
	}

	private static final int GL			  	=			xy(1, 51);  //8 slots
//	public static final int GL_T1			= GL+0;
//	public static final int GL_T2			= GL+1;
	public static final int GL_T3			= GL+2;
//	public static final int GL_T4			= GL+3;
	public static final int GL_T5			= GL+4;
//	public static final int GL_AL			= GL+5;
	public static final int GL_T6			= GL+6;
	static{
//		assignItemRect(GL_T1, 16, 16);
//		assignItemRect(GL_T2, 16, 16);
		assignItemRect(GL_T3, 11, 13);
//		assignItemRect(GL_T4, 16, 16);
		assignItemRect(GL_T5, 15, 15);
//		assignItemRect(GL_AL, 16, 16);
		assignItemRect(GL_T6, 15, 15);
	}

	private static final int RL			  	=			xy(9, 51);  //8 slots
//	public static final int RL_T1			= RL+0;
//	public static final int RL_T2			= RL+1;
//	public static final int RL_T3			= RL+2;
	public static final int RL_T4			= RL+3;
	public static final int RL_T5			= RL+4;
//	public static final int RL_AL			= RL+5;
	public static final int RL_T6			= RL+6;
	static{
//		assignItemRect(RL_T1, 16, 16);
//		assignItemRect(RL_T2, 16, 16);
//		assignItemRect(RL_T3, 16, 16);
		assignItemRect(RL_T4, 16, 16);
		assignItemRect(RL_T5, 16, 16);
//		assignItemRect(RL_AL, 16, 16);
		assignItemRect(RL_T6, 16, 16);
	}

	private static final int FT			  	=			xy(1, 52);  //8 slots
//	public static final int FT_T1			= FT+0;
//	public static final int FT_T2			= FT+1;
//	public static final int FT_T3			= FT+2;
//	public static final int FT_T4			= FT+3;
	public static final int FT_T5			= FT+4;
//	public static final int FT_AL			= FT+5;
//	public static final int FT_T6			= FT+6;
	static{
//		assignItemRect(FT_T1, 16, 16);
//		assignItemRect(FT_T2, 16, 16);
//		assignItemRect(FT_T3, 16, 16);
//		assignItemRect(FT_T4, 16, 16);
		assignItemRect(FT_T5, 14, 15);
//		assignItemRect(FT_AL, 16, 16);
//		assignItemRect(FT_T6, 16, 16);
	}

	private static final int LG			  	=			xy(9, 52);  //8 slots
//	public static final int LG_T1			= LG+0;
//	public static final int LG_T2			= LG+1;
//	public static final int LG_T3			= LG+2;
//	public static final int LG_T4			= LG+3;
	public static final int LG_T5			= LG+4;
//	public static final int LG_AL			= LG+5;
//	public static final int LG_T6			= LG+6;
	static{
//		assignItemRect(LG_T1, 16, 16);
//		assignItemRect(LG_T2, 16, 16);
//		assignItemRect(LG_T3, 16, 16);
//		assignItemRect(LG_T4, 16, 16);
		assignItemRect(LG_T5, 14, 16);
//		assignItemRect(LG_AL, 16, 16);
//		assignItemRect(LG_T6, 16, 16);
	}

	//GL, RL, 화염방사기, 레이저총 추후 추가

	private static final int BOWS			=			xy(1, 53);  //8 slots
	public static final int WIND_BOW		= BOWS+0;
	public static final int NATURE_BOW		= BOWS+1;
	public static final int GOLDEN_BOW		= BOWS+2;
	public static final int CORROSION_BOW	= BOWS+3;
	public static final int TACTICAL_BOW	= BOWS+4;
	public static final int PHASE_BOW		= BOWS+5;
	public static final int ELECTRIC_BOW	= BOWS+6;
	public static final int MAGICAL_BOW		= BOWS+7;
	static{
		assignItemRect(WIND_BOW		, 16, 16);
		assignItemRect(NATURE_BOW	, 16, 16);
		assignItemRect(GOLDEN_BOW	, 16, 16);
		assignItemRect(CORROSION_BOW, 16, 16);
		assignItemRect(TACTICAL_BOW	, 16, 16);
		assignItemRect(PHASE_BOW	, 16, 16);
		assignItemRect(ELECTRIC_BOW	, 16, 16);
		assignItemRect(MAGICAL_BOW	, 16, 16);
	}

	//보조무기 추후 추가

	//old amulet's items
	private static final int SPECIAL_ITEMS  =			xy(1, 55);  //8 slots
	public static final int COMPLETE_SEAL	= SPECIAL_ITEMS+0;
	static{
		assignItemRect(COMPLETE_SEAL	, 9, 15);
	}

	//Alchemy melee weapons

	private static final int AL_WEP_T3			=			xy(1, 56);  //16 slots
	public static final int SPEAR_N_SHIELD		= AL_WEP_T3+0;
	public static final int UNHOLY_SWORD		= AL_WEP_T3+1;
	public static final int CHAOS_SWORD			= AL_WEP_T3+2;
	public static final int FIRE_SCIMITAR		= AL_WEP_T3+3;
	public static final int FROST_SCIMITAR		= AL_WEP_T3+4;
	public static final int POISON_SCIMITAR		= AL_WEP_T3+5;
	public static final int ELECTRIC_SCIMITAR	= AL_WEP_T3+6;
	static{
		assignItemRect(SPEAR_N_SHIELD	, 16, 15);
		assignItemRect(UNHOLY_SWORD		, 14, 14);
		assignItemRect(CHAOS_SWORD		, 14, 14);
		assignItemRect(FIRE_SCIMITAR	, 13, 16);
		assignItemRect(FROST_SCIMITAR	, 13, 16);
		assignItemRect(POISON_SCIMITAR	, 13, 16);
		assignItemRect(ELECTRIC_SCIMITAR, 13, 16);
	}

	private static final int AL_WEP_T4		=			xy(1, 57);  //16 slots
	public static final int UNHOLY_BIBLE		= AL_WEP_T4+0;
	static{
		assignItemRect(UNHOLY_BIBLE	, 	13, 16);
	}

	private static final int AL_WEP_T5		=			xy(1, 58);  //16 slots
	public static final int TRUE_RUNIC_BLADE= AL_WEP_T5+0;
	public static final int CHAIN_WHIP		= AL_WEP_T5+1;
	static{
		assignItemRect(TRUE_RUNIC_BLADE, 14, 14);
		assignItemRect(CHAIN_WHIP, 		 14, 14);
	}

	private static final int AL_WEP_MIS		=			xy(9, 58);  //16 slots
	public static final int CROSS			= AL_WEP_MIS+0;
	public static final int THUNDERBOLT		= AL_WEP_MIS+1;
	static{
		assignItemRect(CROSS, 		14, 14);
		assignItemRect(THUNDERBOLT, 13, 13);
	}

	private static final int AL_WEP_T6	  	=			xy(1, 59);  //16 slots
	public static final int DUAL_GREATSWORD	= AL_WEP_T6+0;
	public static final int FORCE_GLOVE		= AL_WEP_T6+1;
	public static final int CHAIN_FLAIL		= AL_WEP_T6+2;
	public static final int UNFORMED_BLADE	= AL_WEP_T6+3;
	public static final int ASSASSINS_SPEAR	= AL_WEP_T6+4;
	public static final int SHARP_KATANA	= AL_WEP_T6+5;
	public static final int OBSIDIAN_SHIELD	= AL_WEP_T6+6;
	public static final int LANCE			= AL_WEP_T6+7;
	public static final int BEAM_SABER		= AL_WEP_T6+8;
	public static final int MEISTER_HAMMER	= AL_WEP_T6+9;
	public static final int HUGE_SWORD		= AL_WEP_T6+10;
	static{
		assignItemRect(DUAL_GREATSWORD	, 16, 16);
		assignItemRect(FORCE_GLOVE		, 13, 15);
		assignItemRect(CHAIN_FLAIL		, 16, 16);
		assignItemRect(UNFORMED_BLADE	, 14, 15);
		assignItemRect(ASSASSINS_SPEAR	, 16, 16);
		assignItemRect(SHARP_KATANA		, 12, 16);
		assignItemRect(OBSIDIAN_SHIELD	, 12, 16);
		assignItemRect(LANCE			, 15, 15);
		assignItemRect(BEAM_SABER		, 16, 15);
		assignItemRect(MEISTER_HAMMER	, 16, 16);
		assignItemRect(HUGE_SWORD		, 16, 16);
	}

	private static final int AL_WEP_T7		=			xy(1, 60);  //16 slots
	public static final int LANCE_N_SHIELD	= AL_WEP_T7+0;
	public static final int TACTICAL_SHIELD	= AL_WEP_T7+1;
	public static final int HOLYSWORD_TRUE	= AL_WEP_T7+2;
	public static final int HOLYSWORD		= AL_WEP_T7+3;
	static{
		assignItemRect(LANCE_N_SHIELD	, 16, 15);
		assignItemRect(TACTICAL_SHIELD	, 12, 16);
		assignItemRect(HOLYSWORD_TRUE	, 16, 16);
		assignItemRect(HOLYSWORD		, 16, 16);
	}

	private static final int ENERGY_WEP			  	=			xy(1, 61);  //16 slots
	public static final int WORN_SHORTSWORD_ENERGY	= ENERGY_WEP+0;
//	public static final int 						= ENERGY_WEP+1;
	public static final int DAGGER_ENERGY			= ENERGY_WEP+2;
	public static final int GLOVES_ENERGY			= ENERGY_WEP+3;
	public static final int RAPIER_ENERGY			= ENERGY_WEP+4;
	public static final int HG_T1_ENERGY			= ENERGY_WEP+5;
	public static final int WORN_KATANA_ENERGY		= ENERGY_WEP+6;
	public static final int SABER_ENERGY			= ENERGY_WEP+7;
	static{
		assignItemRect(WORN_SHORTSWORD_ENERGY	, 13, 13);
//		assignItemRect(							, 16, 16);
		assignItemRect(DAGGER_ENERGY			, 12, 13);
		assignItemRect(GLOVES_ENERGY			, 12, 16);
		assignItemRect(RAPIER_ENERGY			, 13, 14);
		assignItemRect(HG_T1_ENERGY				, 10, 13);
		assignItemRect(WORN_KATANA_ENERGY		, 13, 13);
		assignItemRect(SABER_ENERGY				, 13, 15);
	}

	private static final int SPELLBOOK			  		=		xy(1, 62);  //16 slots
	public static final int BOOK_OF_MAGIC  			= SPELLBOOK+0;
	public static final int BOOK_OF_FIRE      		= SPELLBOOK+1;
	public static final int BOOK_OF_FROST          	= SPELLBOOK+2;
	public static final int BOOK_OF_THUNDERBOLT    	= SPELLBOOK+3;
	public static final int BOOK_OF_DISINTEGRATION 	= SPELLBOOK+4;
	public static final int BOOK_OF_LIGHT			= SPELLBOOK+5;
	public static final int BOOK_OF_CORROSION      	= SPELLBOOK+6;
	public static final int BOOK_OF_EARTH   		= SPELLBOOK+7;
	public static final int BOOK_OF_BLAST     		= SPELLBOOK+8;
	public static final int BOOK_OF_CORRUPTION     	= SPELLBOOK+9;
	public static final int BOOK_OF_WARDING        	= SPELLBOOK+10;
	public static final int BOOK_OF_REGROWTH       	= SPELLBOOK+11;
	public static final int BOOK_OF_TRANSFUSION    	= SPELLBOOK+12;
	static{
		assignItemRect(BOOK_OF_MAGIC  			, 12, 16);
		assignItemRect(BOOK_OF_FIRE      		, 12, 16);
		assignItemRect(BOOK_OF_FROST          	, 12, 16);
		assignItemRect(BOOK_OF_THUNDERBOLT     	, 12, 16);
		assignItemRect(BOOK_OF_DISINTEGRATION 	, 12, 16);
		assignItemRect(BOOK_OF_LIGHT			, 12, 16);
		assignItemRect(BOOK_OF_CORROSION      	, 12, 16);
		assignItemRect(BOOK_OF_EARTH   			, 12, 16);
		assignItemRect(BOOK_OF_BLAST     		, 12, 16);
		assignItemRect(BOOK_OF_CORRUPTION     	, 12, 16);
		assignItemRect(BOOK_OF_WARDING        	, 12, 16);
		assignItemRect(BOOK_OF_REGROWTH       	, 12, 16);
		assignItemRect(BOOK_OF_TRANSFUSION    	, 12, 16);
	}

	private static final int ADVENTURE			=				xy(1, 63);  //8 slots
	public static final int ADVENTURER_SHOVEL	= ADVENTURE+0;
	public static final int GILDED_SHOVEL		= ADVENTURE+1;
	public static final int BATTLE_SHOVEL		= ADVENTURE+2;
	public static final int MINERS_TOOL			= ADVENTURE+3;
	public static final int MACHETE				= ADVENTURE+4;
	public static final int ENHANCED_MACHETE	= ADVENTURE+5;
	public static final int ROPE				= ADVENTURE+6;
	static{
		assignItemRect(ADVENTURER_SHOVEL, 16, 16);
		assignItemRect(GILDED_SHOVEL	, 16, 16);
		assignItemRect(BATTLE_SHOVEL	, 16, 16);
		assignItemRect(MINERS_TOOL		, 16, 16);
		assignItemRect(MACHETE			, 15, 16);
		assignItemRect(ENHANCED_MACHETE	, 15, 16);
		assignItemRect(ROPE				, 15, 16);
	}

	private static final int SPECIAL_ITEM			=		xy(9, 63);  //8 slots
	public static final int HERO_SWORD	= SPECIAL_ITEM+0;
	public static final int DEATHS_SWORD	= SPECIAL_ITEM+1;
	public static final int SADDLE			= SPECIAL_ITEM+2;
	public static final int ROSARY			= SPECIAL_ITEM+3;
	static{
		assignItemRect(HERO_SWORD	, 14, 14);
		assignItemRect(DEATHS_SWORD	, 14, 16);
		assignItemRect(SADDLE		, 14, 13);
		assignItemRect(ROSARY		, 14, 15);
	}

	private static final int NEW_POTIONS		=			xy(1, 64);  //16 slots
	public static final int POTION_FLUORESCENT	= NEW_POTIONS+0;
	public static final int POTION_ASH			= NEW_POTIONS+1;
	static{
		for (int i = NEW_POTIONS; i < NEW_POTIONS+1; i++)
			assignItemRect(i, 12, 14);
	}

	private static final int NEW_EXOTIC_POTIONS	=			xy(1, 65);  //16 slots
	public static final int EXOTIC_FLUORESCENT	= NEW_EXOTIC_POTIONS+0;
	public static final int EXOTIC_ASH			= NEW_EXOTIC_POTIONS+1;
	static{
		for (int i = NEW_EXOTIC_POTIONS; i < NEW_EXOTIC_POTIONS+1; i++)
			assignItemRect(i, 12, 13);
	}

	private static final int NEW_BREWS			=			xy(1, 66);  //16 slots
	public static final int BREW_SATISFACTION	= NEW_BREWS+0;
	public static final int BREW_TALENT			= NEW_BREWS+1;
	static{
		for (int i = NEW_BREWS; i < NEW_BREWS+2; i++)
			assignItemRect(i, 12, 14);
	}

	private static final int NEW_SPELLS			=			xy(1, 67);  //16 slots
	public static final int FIRE_IMBUE			= NEW_SPELLS+0;
	public static final int XRAY				= NEW_SPELLS+1;
	public static final int FIREMAKER			= NEW_SPELLS+2;
	public static final int ICEMAKER			= NEW_SPELLS+3;
	public static final int RAPID_GROWTH		= NEW_SPELLS+4;
	public static final int	HANDY_BARRICADE		= NEW_SPELLS+5;
	public static final int ADVANCED_EVOLUTION	= NEW_SPELLS+6;
	public static final int EVOLUTION			= NEW_SPELLS+7;
	public static final int UPGRADE_DUST		= NEW_SPELLS+8;
	public static final int ELECTRICITY_IMBUE	= NEW_SPELLS+9;
	public static final int UNSTABLE_IDENTIFY	= NEW_SPELLS+10;
	static{
		assignItemRect(FIRE_IMBUE			, 11, 11);
		assignItemRect(XRAY					, 11, 11);
		assignItemRect(FIREMAKER			, 12, 11);
		assignItemRect(ICEMAKER				, 12, 11);
		assignItemRect(RAPID_GROWTH			, 8, 16);
		assignItemRect(HANDY_BARRICADE		, 8, 16);
		assignItemRect(ADVANCED_EVOLUTION	, 10, 15);
		assignItemRect(EVOLUTION			, 10, 15);
		assignItemRect(UPGRADE_DUST			, 15, 11);
		assignItemRect(ELECTRICITY_IMBUE	, 12, 11);
		assignItemRect(UNSTABLE_IDENTIFY	, 12, 11);
	}

	private static final int NEW_TRINKETS			=			xy(1, 68);  //16 slots
	public static final int COMPASS			= NEW_TRINKETS+0;
	public static final int CRYSTAL_BALL	= NEW_TRINKETS+1;
	public static final int MAGNIFYING_GLASS= NEW_TRINKETS+2;
	public static final int SUSPICIOUS_KEY	= NEW_TRINKETS+3;
	public static final int PINK_GEM		= NEW_TRINKETS+4;
	public static final int NECKLACE		= NEW_TRINKETS+5;
	public static final int GRINDSTONE		= NEW_TRINKETS+6;
	static {
		assignItemRect(COMPASS			, 16, 11);
		assignItemRect(CRYSTAL_BALL		, 12, 14);
		assignItemRect(MAGNIFYING_GLASS	, 14, 14);
		assignItemRect(SUSPICIOUS_KEY	, 8, 14);
		assignItemRect(PINK_GEM			, 11, 9);
		assignItemRect(NECKLACE			, 14, 15);
		assignItemRect(GRINDSTONE		, 14, 11);
	}

	private static final int NECKLACE_GEMS			=			xy(1, 69);  //16 slots
	public static final int NECKLACE_GARNET    			= NECKLACE_GEMS+0;
	public static final int NECKLACE_RUBY      			= NECKLACE_GEMS+1;
	public static final int NECKLACE_TOPAZ     			= NECKLACE_GEMS+2;
	public static final int NECKLACE_EMERALD   			= NECKLACE_GEMS+3;
	public static final int NECKLACE_ONYX      			= NECKLACE_GEMS+4;
	public static final int NECKLACE_OPAL      			= NECKLACE_GEMS+5;
	public static final int NECKLACE_TOURMALINE			= NECKLACE_GEMS+6;
	public static final int NECKLACE_SAPPHIRE  			= NECKLACE_GEMS+7;
	public static final int NECKLACE_AMETHYST  			= NECKLACE_GEMS+8;
	public static final int NECKLACE_QUARTZ    			= NECKLACE_GEMS+9;
	public static final int NECKLACE_AGATE     			= NECKLACE_GEMS+10;
	public static final int NECKLACE_DIAMOND   			= NECKLACE_GEMS+11;
	static {
		for (int i = NECKLACE_GEMS; i < NECKLACE_GEMS+16; i++)
			assignItemRect(i, 14, 15);
	}

	private static final int PILL					=			xy(1, 70);
	public static final int PILL_STRENGTH =  PILL+0;
	public static final int PILL_HEALING  =  PILL+1;
	public static final int PILL_MINDVIS  =  PILL+2;
	public static final int PILL_FROST    =  PILL+3;
	public static final int PILL_LIQFLAME =  PILL+4;
	public static final int PILL_TOXICGAS =  PILL+5;
	public static final int PILL_HASTE    =  PILL+6;
	public static final int PILL_INVIS    =  PILL+7;
	public static final int PILL_LEVITATE =  PILL+8;
	public static final int PILL_PARAGAS  =  PILL+9;
	public static final int PILL_PURITY   =  PILL+10;
	public static final int PILL_EXP      =  PILL+11;
	static {
		for (int i = PILL; i < PILL+12; i++)
			assignItemRect(i, 11, 11);
	}

	private static final int MEDIC			=			xy(1, 71);  //16 slots
	public static final int SCALPEL			= MEDIC+0;
	public static final int GAMMA_RAY_GUN	= MEDIC+1;
	public static final int FIRST_AID_KIT	= MEDIC+2;
	static{
		assignItemRect(SCALPEL			, 13, 13);
		assignItemRect(GAMMA_RAY_GUN	, 12, 15);
		assignItemRect(FIRST_AID_KIT	, 16, 13);
	}

	private static final int NEW_MISC_CONSUMABLE	= 	xy(1, 72); //16 slots
	public static final int SPARE_MAG 				= NEW_MISC_CONSUMABLE+0;
	public static final int SHEATH_FRAGMENT 		= NEW_MISC_CONSUMABLE+1;
	public static final int HOLED_POUCH 			= NEW_MISC_CONSUMABLE+2;
	public static final int BROKEN_SHIELD 			= NEW_MISC_CONSUMABLE+3;
	public static final int BROKEN_PILLBOX 			= NEW_MISC_CONSUMABLE+4;
	static{
		assignItemRect(SPARE_MAG 		, 10, 13);
		assignItemRect(SHEATH_FRAGMENT 	, 13, 10);
		assignItemRect(HOLED_POUCH 		, 14, 13);
		assignItemRect(BROKEN_SHIELD 	, 13, 10);
		assignItemRect(BROKEN_PILLBOX	, 13, 15);
	}

	private static final int BOW_WEAPONS			=	xy(1, 73);
	public static final int WORN_SHORTBOW			= BOW_WEAPONS+0;
	public static final int SHORTBOW				= BOW_WEAPONS+1;
	public static final int BOW						= BOW_WEAPONS+2;
	public static final int LONGBOW					= BOW_WEAPONS+3;
	public static final int GREATBOW				= BOW_WEAPONS+4;
	static {
		assignItemRect(WORN_SHORTBOW, 12, 12);
		assignItemRect(SHORTBOW		, 13, 13);
		assignItemRect(BOW			, 15, 15);
		assignItemRect(LONGBOW		, 16, 16);
		assignItemRect(GREATBOW		, 16, 16);
	}

	private static final int ARROW_WEAPONS			=	xy(1, 74);
	public static final int NORMAL_ARROW			= ARROW_WEAPONS+0;
	static {
		assignItemRect(NORMAL_ARROW, 14, 14);
	}

	private static final int QUICK_WEAPONS			=	xy(1, 75);
	public static final int POCKET_KNIFE			= QUICK_WEAPONS+0;
	static {
		assignItemRect(POCKET_KNIFE, 11, 12);
	}


	// ****** new sprites end ******



	//for smaller 8x8 icons that often accompany an item sprite
	public static class Icons {

		private static final int WIDTH = 16;
		public static final int SIZE = 8;

		public static TextureFilm film = new TextureFilm( Assets.Sprites.ITEM_ICONS, SIZE, SIZE );

		private static int xy(int x, int y){
			x -= 1; y -= 1;
			return x + WIDTH*y;
		}

		private static void assignIconRect( int item, int width, int height ){
			int x = (item % WIDTH) * SIZE;
			int y = (item / WIDTH) * SIZE;
			film.add( item, x, y, x+width, y+height);
		}

		private static final int RINGS          =                            xy(1, 1);  //16 slots
		public static final int RING_ACCURACY   = RINGS+0;
		public static final int RING_ARCANA     = RINGS+1;
		public static final int RING_ELEMENTS   = RINGS+2;
		public static final int RING_ENERGY     = RINGS+3;
		public static final int RING_EVASION    = RINGS+4;
		public static final int RING_FORCE      = RINGS+5;
		public static final int RING_FUROR      = RINGS+6;
		public static final int RING_HASTE      = RINGS+7;
		public static final int RING_MIGHT      = RINGS+8;
		public static final int RING_SHARPSHOOT = RINGS+9;
		public static final int RING_TENACITY   = RINGS+10;
		public static final int RING_WEALTH     = RINGS+11;
		static {
			assignIconRect( RING_ACCURACY,      7, 7 );
			assignIconRect( RING_ARCANA,        7, 7 );
			assignIconRect( RING_ELEMENTS,      7, 7 );
			assignIconRect( RING_ENERGY,        7, 5 );
			assignIconRect( RING_EVASION,       7, 7 );
			assignIconRect( RING_FORCE,         5, 6 );
			assignIconRect( RING_FUROR,         7, 6 );
			assignIconRect( RING_HASTE,         6, 6 );
			assignIconRect( RING_MIGHT,         7, 7 );
			assignIconRect( RING_SHARPSHOOT,    7, 7 );
			assignIconRect( RING_TENACITY,      6, 6 );
			assignIconRect( RING_WEALTH,        7, 6 );
		}

		                                                                                //16 free slots

		private static final int SCROLLS        =                            xy(1, 3);  //16 slots
		public static final int SCROLL_UPGRADE  = SCROLLS+0;
		public static final int SCROLL_IDENTIFY = SCROLLS+1;
		public static final int SCROLL_REMCURSE = SCROLLS+2;
		public static final int SCROLL_MIRRORIMG= SCROLLS+3;
		public static final int SCROLL_RECHARGE = SCROLLS+4;
		public static final int SCROLL_TELEPORT = SCROLLS+5;
		public static final int SCROLL_LULLABY  = SCROLLS+6;
		public static final int SCROLL_MAGICMAP = SCROLLS+7;
		public static final int SCROLL_RAGE     = SCROLLS+8;
		public static final int SCROLL_RETRIB   = SCROLLS+9;
		public static final int SCROLL_TERROR   = SCROLLS+10;
		public static final int SCROLL_TRANSMUTE= SCROLLS+11;
		static {
			assignIconRect( SCROLL_UPGRADE,     7, 7 );
			assignIconRect( SCROLL_IDENTIFY,    4, 7 );
			assignIconRect( SCROLL_REMCURSE,    7, 7 );
			assignIconRect( SCROLL_MIRRORIMG,   7, 5 );
			assignIconRect( SCROLL_RECHARGE,    7, 5 );
			assignIconRect( SCROLL_TELEPORT,    7, 7 );
			assignIconRect( SCROLL_LULLABY,     7, 6 );
			assignIconRect( SCROLL_MAGICMAP,    7, 7 );
			assignIconRect( SCROLL_RAGE,        6, 6 );
			assignIconRect( SCROLL_RETRIB,      5, 6 );
			assignIconRect( SCROLL_TERROR,      5, 7 );
			assignIconRect( SCROLL_TRANSMUTE,   7, 7 );
		}

		private static final int EXOTIC_SCROLLS =                            xy(1, 4);  //16 slots
		public static final int SCROLL_ENCHANT  = EXOTIC_SCROLLS+0;
		public static final int SCROLL_DIVINATE = EXOTIC_SCROLLS+1;
		public static final int SCROLL_ANTIMAGIC= EXOTIC_SCROLLS+2;
		public static final int SCROLL_PRISIMG  = EXOTIC_SCROLLS+3;
		public static final int SCROLL_MYSTENRG = EXOTIC_SCROLLS+4;
		public static final int SCROLL_PASSAGE  = EXOTIC_SCROLLS+5;
		public static final int SCROLL_SIREN    = EXOTIC_SCROLLS+6;
		public static final int SCROLL_FORESIGHT= EXOTIC_SCROLLS+7;
		public static final int SCROLL_CHALLENGE= EXOTIC_SCROLLS+8;
		public static final int SCROLL_PSIBLAST = EXOTIC_SCROLLS+9;
		public static final int SCROLL_DREAD    = EXOTIC_SCROLLS+10;
		public static final int SCROLL_METAMORPH= EXOTIC_SCROLLS+11;
		static {
			assignIconRect( SCROLL_ENCHANT,     7, 7 );
			assignIconRect( SCROLL_DIVINATE,    7, 6 );
			assignIconRect( SCROLL_ANTIMAGIC,   7, 7 );
			assignIconRect( SCROLL_PRISIMG,     5, 7 );
			assignIconRect( SCROLL_MYSTENRG,    7, 5 );
			assignIconRect( SCROLL_PASSAGE,     5, 7 );
			assignIconRect( SCROLL_SIREN,       7, 6 );
			assignIconRect( SCROLL_FORESIGHT,   7, 5 );
			assignIconRect( SCROLL_CHALLENGE,   7, 7 );
			assignIconRect( SCROLL_PSIBLAST,    5, 6 );
			assignIconRect( SCROLL_DREAD,       5, 7 );
			assignIconRect( SCROLL_METAMORPH,   7, 7 );
		}

		                                                                                //16 free slots

		private static final int POTIONS        =                            xy(1, 6);  //16 slots
		public static final int POTION_STRENGTH = POTIONS+0;
		public static final int POTION_HEALING  = POTIONS+1;
		public static final int POTION_MINDVIS  = POTIONS+2;
		public static final int POTION_FROST    = POTIONS+3;
		public static final int POTION_LIQFLAME = POTIONS+4;
		public static final int POTION_TOXICGAS = POTIONS+5;
		public static final int POTION_HASTE    = POTIONS+6;
		public static final int POTION_INVIS    = POTIONS+7;
		public static final int POTION_LEVITATE = POTIONS+8;
		public static final int POTION_PARAGAS  = POTIONS+9;
		public static final int POTION_PURITY   = POTIONS+10;
		public static final int POTION_EXP      = POTIONS+11;
		static {
			assignIconRect( POTION_STRENGTH,    7, 7 );
			assignIconRect( POTION_HEALING,     6, 7 );
			assignIconRect( POTION_MINDVIS,     7, 5 );
			assignIconRect( POTION_FROST,       7, 7 );
			assignIconRect( POTION_LIQFLAME,    5, 7 );
			assignIconRect( POTION_TOXICGAS,    7, 7 );
			assignIconRect( POTION_HASTE,       6, 6 );
			assignIconRect( POTION_INVIS,       5, 7 );
			assignIconRect( POTION_LEVITATE,    6, 7 );
			assignIconRect( POTION_PARAGAS,     7, 7 );
			assignIconRect( POTION_PURITY,      5, 7 );
			assignIconRect( POTION_EXP,         7, 7 );
		}

		private static final int EXOTIC_POTIONS =                            xy(1, 7);  //16 slots
		public static final int POTION_MASTERY  = EXOTIC_POTIONS+0;
		public static final int POTION_SHIELDING= EXOTIC_POTIONS+1;
		public static final int POTION_MAGISIGHT= EXOTIC_POTIONS+2;
		public static final int POTION_SNAPFREEZ= EXOTIC_POTIONS+3;
		public static final int POTION_DRGBREATH= EXOTIC_POTIONS+4;
		public static final int POTION_CORROGAS = EXOTIC_POTIONS+5;
		public static final int POTION_STAMINA  = EXOTIC_POTIONS+6;
		public static final int POTION_SHROUDFOG= EXOTIC_POTIONS+7;
		public static final int POTION_STRMCLOUD= EXOTIC_POTIONS+8;
		public static final int POTION_EARTHARMR= EXOTIC_POTIONS+9;
		public static final int POTION_CLEANSE  = EXOTIC_POTIONS+10;
		public static final int POTION_DIVINE   = EXOTIC_POTIONS+11;
		static {
			assignIconRect( POTION_MASTERY,     7, 7 );
			assignIconRect( POTION_SHIELDING,   6, 6 );
			assignIconRect( POTION_MAGISIGHT,   7, 5 );
			assignIconRect( POTION_SNAPFREEZ,   7, 7 );
			assignIconRect( POTION_DRGBREATH,   7, 7 );
			assignIconRect( POTION_CORROGAS,    7, 7 );
			assignIconRect( POTION_STAMINA,     6, 6 );
			assignIconRect( POTION_SHROUDFOG,   7, 7 );
			assignIconRect( POTION_STRMCLOUD,   7, 7 );
			assignIconRect( POTION_EARTHARMR,   6, 6 );
			assignIconRect( POTION_CLEANSE,     7, 7 );
			assignIconRect( POTION_DIVINE,      7, 7 );
		}

		                                                                                //16 free slots

	}

}
