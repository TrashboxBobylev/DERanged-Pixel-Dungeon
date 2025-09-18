package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.TimedShrink;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GhostChickenSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class GhostChicken extends AbyssalMob {

    {
        spriteClass = GhostChickenSprite.class;

        HP = HT = 3;
        defenseSkill = 40;
        baseSpeed = 3f;

        EXP = 13;

        loot = Generator.random();
        lootChance = 0.1f;

        properties.add(Property.DEMONIC);
        properties.add(Property.UNDEAD);
    }

    @Override
    protected boolean act() {
        boolean act = super.act();
        if (HT > 15){
            HP = HT = 15;
        }
        return act;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 1 + abyssLevel()*2, 3 + abyssLevel()*4 );
    }

    @Override
    public int attackProc(Char enemy, int damage) {
        Buff.prolong(enemy, TimedShrink.class, 2.5f + abyssLevel()*2.5f);
        return super.attackProc(enemy, damage);
    }

    @Override
    public int attackSkill( Char target ) {
        return 42;
    }

    @Override
    public void damage(int dmg, Object src) {
        dmg = 1;
        super.damage(dmg, src);
        aggro(Dungeon.hero);
    }

    @Override
    public void hitSound(float pitch) {
        Sample.INSTANCE.play(Assets.Sounds.PUFF, 1, pitch);
    }

    @Override
    public float attackDelay() {
        return super.attackDelay()*0.25f;
    }
}
