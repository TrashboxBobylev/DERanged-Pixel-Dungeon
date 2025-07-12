package com.shatteredpixel.shatteredpixeldungeon.items.bags;

import com.shatteredpixel.shatteredpixeldungeon.items.GammaRayGun;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KnightsShield;
import com.shatteredpixel.shatteredpixeldungeon.items.Sheath;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MedicKit;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Machete;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class CheesyCheest extends Bag {
    {
        image = ItemSpriteSheet.CHEEST;
    }

    @Override
    public int capacity() {
        return 6;
    }

    @Override
    public boolean canHold( Item item ) {
        if (item instanceof Sheath || item instanceof Machete || item instanceof Shovel ||
                item instanceof KnightsShield || item instanceof MedicKit || item instanceof GammaRayGun){
            return super.canHold(item);
        } else {
            return false;
        }
    }
}
