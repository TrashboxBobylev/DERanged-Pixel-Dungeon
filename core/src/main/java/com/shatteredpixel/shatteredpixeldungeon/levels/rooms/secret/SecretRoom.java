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

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.secret;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SuspiciousKey;
import com.shatteredpixel.shatteredpixeldungeon.levels.AbyssLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.SpecialRoom;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Arrays;


public abstract class SecretRoom extends SpecialRoom {
	
	
	private static final ArrayList<Class<? extends SecretRoom>> ALL_SECRETS = new ArrayList<>( Arrays.asList(
			SecretGardenRoom.class, SecretLaboratoryRoom.class, SecretLibraryRoom.class,
			SecretLarderRoom.class, SecretWellRoom.class, SecretRunestoneRoom.class,
			SecretArtilleryRoom.class, SecretChestChasmRoom.class, SecretHoneypotRoom.class,
			SecretHoardRoom.class, SecretMazeRoom.class, SecretSummoningRoom.class));
	
	public static ArrayList<Class<? extends SecretRoom>> runSecrets = new ArrayList<>();

	//this is the number of secret rooms per region (whole value),
	// plus the chance for an extra secret room (fractional value)
	private static float[] baseRegionSecrets = new float[]{2f, 2.25f, 2.5f, 2.75f, 3.0f, 3.25f, 3.5f};
	private static int[] regionSecretsThisRun = new int[7];
	
	public static void initForRun(){
		
		float[] regionChances = baseRegionSecrets.clone();
		
		for (int i = 0; i < regionSecretsThisRun.length; i++){
			regionSecretsThisRun[i] = (int)regionChances[i];
			if (Random.Float() < regionChances[i] % 1f){
				regionSecretsThisRun[i]++;
			}
		}
		
		runSecrets = new ArrayList<>(ALL_SECRETS);
		Random.shuffle(runSecrets);
		
	}
	
	public static int secretsForFloor(int depth){
		if (depth == 1) return 0;
		
		int region = depth/5;
		int floor = depth%5;
		
		int floorsLeft = 5 - floor;
		
		float secrets;
		if (floorsLeft == 0) {
			secrets = regionSecretsThisRun[region];
		} else {
			if (Dungeon.branch == AbyssLevel.BRANCH){
				secrets = 1;
			} else secrets = regionSecretsThisRun[region] / (float)floorsLeft;
			if (Random.Float() < secrets % 1f){
				secrets = (float)Math.ceil(secrets);
			} else {
				secrets = (float)Math.floor(secrets);
			}
		}
		float additionalSecrets;
		if (floorsLeft == 0) {
			additionalSecrets = SuspiciousKey.additionalSecretRoom(depth);
		} else {
			additionalSecrets = SuspiciousKey.additionalSecretRoom(depth) / (float)floorsLeft;
			if (Random.Float() < additionalSecrets % 1f){
				additionalSecrets = (float)Math.ceil(additionalSecrets);
			} else {
				additionalSecrets = (float)Math.floor(additionalSecrets);
			}
		}

		if (Dungeon.branch != AbyssLevel.BRANCH) {
			regionSecretsThisRun[region] -= (int) secrets;
			SuspiciousKey.secretUse(region, (int) additionalSecrets);
		}
		return (int)secrets + (int)additionalSecrets;
	}
	
	public static SecretRoom createRoom(){

		//60% chance for front of queue, 30% chance for next, 10% for one after that
		int index = Random.chances(new float[]{6, 3, 1});
		while (index >= runSecrets.size()) index--;

		SecretRoom r = Reflection.newInstance(runSecrets.get( index ));
		
		runSecrets.add(runSecrets.remove(index));
		
		return r;
	}
	
	private static final String ROOMS	= "secret_rooms";
	private static final String REGIONS	= "region_secrets";
	
	public static void restoreRoomsFromBundle( Bundle bundle ) {
		runSecrets.clear();
		if (bundle.contains( ROOMS )) {
			for (Class<? extends SecretRoom> type : bundle.getClassArray(ROOMS)) {
				if (type != null) runSecrets.add(type);
			}
			regionSecretsThisRun = bundle.getIntArray(REGIONS);
		} else {
			initForRun();
			ShatteredPixelDungeon.reportException(new Exception("secrets array didn't exist!"));
		}
	}
	
	public static void storeRoomsInBundle( Bundle bundle ) {
		bundle.put( ROOMS, runSecrets.toArray(new Class[0]) );
		bundle.put( REGIONS, regionSecretsThisRun );
	}

}
