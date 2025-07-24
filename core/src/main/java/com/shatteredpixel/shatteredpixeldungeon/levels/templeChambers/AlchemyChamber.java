package com.shatteredpixel.shatteredpixeldungeon.levels.templeChambers;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.EnergyCrystal;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.utils.DungeonSeed;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

public class AlchemyChamber extends Chamber {

    {
        isBuildWithStructure = true;
    }

    @Override
    public int[] roomStructure() {
        int n = -1;
        int E = Terrain.EMPTY_SP;
        int B = Terrain.BOOKSHELF;
        int P = Terrain.PEDESTAL;
        int A = Terrain.ALCHEMY;
        int S = Terrain.STATUE_SP;
        return new int[] {
                E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                E, B, B, B, B, B, B, B, E, B, B, B, B, B, B, B, E,
                E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                E, B, B, B, B, B, B, B, E, B, B, B, B, B, B, B, E,
                E, E, E, E, E, E, E, E, E, E, P, E, E, E, P, E, E,
                E, B, B, B, B, B, B, B, E, E, P, E, A, E, P, E, E,
                E, E, E, E, E, E, E, E, E, E, P, E, E, E, P, E, E,
                E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                E, E, E, E, E, E, E, E, n, E, E, E, E, E, E, E, E,
                E, E, E, E, E, E, E, E, E, E, S, E, S, E, S, E, E,
                E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                E, B, B, B, B, B, B, B, E, B, B, B, B, B, B, B, E,
                E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                E, B, B, B, B, B, B, B, E, B, B, B, B, B, B, B, E,
                E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                E, B, B, B, B, B, B, B, E, B, B, B, B, B, B, B, E,
                E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
        };
    }

    @Override
    public void build() {
        super.build();

        Heap.Type type = Heap.Type.HEAP;
        if (Dungeon.isSpecialSeedEnabled(DungeonSeed.SpecialSeed.CHESTS))
            type = Heap.Type.CHEST;

        for (int i = 2; i < 5; i++) {
            Point pedestalPos = new Point(center.x+2, center.y-i);
            if (i == 2) {
                level.drop(Generator.randomUsingDefaults(Generator.Category.FOOD), level.pointToCell(pedestalPos)).type = type;
            } else {
                level.drop(new EnergyCrystal().quantity(Random.IntRange(6,10)), level.pointToCell(pedestalPos)).type = type;
            }
        }
        for (int i = 2; i < 5; i++) {
            Point pedestalPos = new Point(center.x+6, center.y-i);
            level.drop(Generator.randomUsingDefaults(Generator.Category.POTION), level.pointToCell(pedestalPos)).type = type;
        }
    }
}