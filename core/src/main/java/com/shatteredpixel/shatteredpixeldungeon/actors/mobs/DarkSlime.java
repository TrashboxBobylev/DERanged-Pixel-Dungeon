package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CausticSlimeSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class DarkSlime extends Mob {

    {
        spriteClass = CausticSlimeSprite.class;

        HP = HT = 64;
        defenseSkill = 23;

        EXP = 12;
        maxLvl = 25;

        loot = new GooBlob();
        lootChance = 0.1f;
        properties.add(Property.ACIDIC);
        properties.add(Property.DEMONIC);
        properties.add(Property.INORGANIC);
    }

    private static final float SPLIT_DELAY	= 1f;

    int generation	= 0;

    private static final String GENERATION	= "generation";

    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle( bundle );
        bundle.put( GENERATION, generation );
    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );
        generation = bundle.getInt( GENERATION );
        if (generation > 0) EXP = 0;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(18, 23);
    }

    @Override
    public void damage(int dmg, Object src) {
        for (Class c : RingOfElements.RESISTS){
            if (c.isAssignableFrom(src.getClass()) && src instanceof Wand){
                dmg *= 3f;
            }
        }
        super.damage(dmg, src);
    }

    @Override
    public int defenseProc( Char enemy, int damage ) {

        if (HP >= damage + 2) {
            ArrayList<Integer> candidates = new ArrayList<>();
            boolean[] solid = Dungeon.level.passable;

            int[] neighbours = {pos + 1, pos - 1, pos + Dungeon.level.width(), pos - Dungeon.level.width()};
            for (int n : neighbours) {
                if (solid[n] && Actor.findChar( n ) == null) {
                    candidates.add( n );
                }
            }

            if (candidates.size() > 0) {

                DarkSlime clone = split();
                HP = HT;
                HT *= 0.8f;
                clone.HT = clone.HP = HT;
                clone.pos = Random.element( candidates );
                clone.state = clone.HUNTING;
                Dungeon.level.pressCell(clone.pos);

                if (Dungeon.level.map[clone.pos] == Terrain.DOOR) {
                    Door.enter( clone.pos );
                }

                GameScene.add( clone, SPLIT_DELAY );
                Actor.addDelayed( new Pushing( clone, pos, clone.pos ), -1 );

                HP = HT;
            }
        }

        return super.defenseProc(enemy, damage);
    }

    @Override
    public int attackSkill( Char target ) {
        return 38;
    }

    private DarkSlime split() {
        DarkSlime clone = new DarkSlime();
        clone.generation = generation + 1;
        clone.EXP = 0;
        if (buff( Burning.class ) != null) {
            Buff.affect( clone, Burning.class ).reignite( clone );
        }
        if (buff( Poison.class ) != null) {
            Buff.affect( clone, Poison.class ).set(2);
        }
        if (buff(Corruption.class ) != null) {
            Buff.affect( clone, Corruption.class);
        }
        return clone;
    }
}
