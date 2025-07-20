package com.shatteredpixel.shatteredpixeldungeon.items.bags;

import com.shatteredpixel.shatteredpixeldungeon.items.ArrowBag;
import com.shatteredpixel.shatteredpixeldungeon.items.GammaRayGun;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KnightsShield;
import com.shatteredpixel.shatteredpixeldungeon.items.Sheath;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MedicKit;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Machete;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.bow.WornShortBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.gun.AR.AR_T1;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class CheesyCheest extends Bag {
    {
        image = ItemSpriteSheet.CHEEST;
    }

    @Override
    public int capacity() {
        return 9;
    }

    @Override
    public boolean canHold( Item item ) {
        if (item instanceof Sheath || item instanceof Machete || item instanceof Shovel ||
                item instanceof KnightsShield || item instanceof MedicKit || item instanceof GammaRayGun
                || item instanceof ArrowBag || item instanceof AR_T1 || item instanceof WornShortBow){
            return super.canHold(item);
        } else {
            return false;
        }
    }
}
