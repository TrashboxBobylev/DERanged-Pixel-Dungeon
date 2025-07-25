package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Miasma;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PhantomSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Phantom extends AbyssalMob {

    {
        spriteClass = PhantomSprite.class;

        HP = HT = 112;
        defenseSkill = 36;

        EXP = 13;

        loot = Random.oneOf(Generator.Category.WEAPON, Generator.Category.ARMOR);
        lootChance = 0.125f; //initially, see rollToDropLoot

        properties.add(Property.INORGANIC);
        properties.add(Property.LARGE);
        properties.add(Property.UNDEAD);

        HUNTING = new Hunting();
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 20 + abyssLevel()*9, 55 + abyssLevel()*13 );
    }

    @Override
    public int attackSkill( Char target ) {
        return 50 + abyssLevel()*3;
    }

    @Override
    public int drRoll() {
        return super.drRoll() + Random.NormalIntRange(5 + abyssLevel()*6, 16 + abyssLevel()*9);
    }

    @Override
    public void rollToDropLoot() {
        //each drop makes future drops 1/2 as likely
        // so loot chance looks like: 1/8, 1/16, 1/32, 1/64, etc.
        lootChance *= Math.pow(1/2f, Dungeon.LimitedDrops.PHANTOM_EQUIP.count);
        super.rollToDropLoot();
    }

    public Item createLoot() {
        Dungeon.LimitedDrops.PHANTOM_EQUIP.count++;
        //uses probability tables for dwarf city
        if (loot == Generator.Category.WEAPON){
            return Generator.randomWeapon(6);
        } else {
            return Generator.randomArmor(6);
        }
    }

    private int ventCooldown = 0;

    private static final String VENT_COOLDOWN = "vent_cooldown";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(VENT_COOLDOWN, ventCooldown);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        ventCooldown = bundle.getInt( VENT_COOLDOWN );
    }

    @Override
    protected boolean act() {
        ventCooldown--;
        return super.act();
    }

    public void onZapComplete(){
        zap();
        next();
    }

    private void zap( ){
        spend( TICK*2 );
        ventCooldown = 10;

        Ballistica trajectory = new Ballistica(pos, enemy.pos, Ballistica.STOP_TARGET);

        for (int i : trajectory.subPath(0, trajectory.dist)){
            GameScene.add(Blob.seed(i, 9, Miasma.class));
        }

    }

    {
        immunities.add(Miasma.class);
    }

    private class Hunting extends Mob.Hunting{

        @Override
        public boolean act(boolean enemyInFOV, boolean justAlerted) {
            if (!enemyInFOV || canAttack(enemy)) {
                return super.act(enemyInFOV, justAlerted);
            } else {
                enemySeen = true;
                target = enemy.pos;

                int oldPos = pos;

                if (ventCooldown <= 0 && distance(enemy) >= 1 && Random.Int(50/distance(enemy)) == 0){
                    if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
                        sprite.zap( enemy.pos );
                        return false;
                    } else {
                        zap();
                        return true;
                    }

                } else if (getCloser( target )) {
                    spend( 1 / speed() );
                    return moveSprite( oldPos,  pos );

                } else if (ventCooldown <= 0) {
                    if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
                        sprite.zap( enemy.pos );
                        return false;
                    } else {
                        zap();
                        return true;
                    }

                } else {
                    spend( TICK );
                    return true;
                }

            }
        }
    }

}
