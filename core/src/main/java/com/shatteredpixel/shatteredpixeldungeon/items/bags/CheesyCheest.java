package com.shatteredpixel.shatteredpixeldungeon.items.bags;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class CheesyCheest extends Bag {
    {
        image = ItemSpriteSheet.CHEEST;
    }

    @Override
    public int capacity() {
        return 9;
    }


}
