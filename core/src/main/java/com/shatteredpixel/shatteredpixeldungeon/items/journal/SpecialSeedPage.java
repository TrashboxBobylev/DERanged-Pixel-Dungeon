package com.shatteredpixel.shatteredpixeldungeon.items.journal;

import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class SpecialSeedPage extends DocumentPage {

    {
        image = ItemSpriteSheet.SEED_PAGE;
    }

    @Override
    public Document document() {
        return Document.SPECIAL_SEEDS;
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", document().pageTitle(page()));
    }
}

