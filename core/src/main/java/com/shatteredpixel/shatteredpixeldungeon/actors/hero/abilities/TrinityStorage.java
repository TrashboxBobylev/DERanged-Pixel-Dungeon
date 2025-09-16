package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities;

import com.watabou.utils.Bundlable;

public interface TrinityStorage {
    Bundlable bodyForm();
    void bodyForm(Bundlable form);
    Bundlable mindForm();
    void mindForm(Bundlable form);
    Bundlable spiritForm();
    void spiritForm(Bundlable form);
}
