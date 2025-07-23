package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Shrink;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.TimedShrink;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfPrismaticLight;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.FinalFroggitSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class FinalFroggit extends AbyssalMob implements Callback {

    private static final float TIME_TO_ZAP	= 1f;

    {
        spriteClass = FinalFroggitSprite.class;

        HP = HT = 180;
        defenseSkill = 20;

        EXP = 13;

        loot = Generator.random();
        lootChance = 1f;

        properties.add(Property.UNDEAD);
        properties.add(Property.DEMONIC);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 18, 25 );
    }

    @Override
    public int attackSkill( Char target ) {
        return 30 + abyssLevel()*10;
    }

    @Override
    public int drRoll() {
        return super.drRoll() + Random.NormalIntRange(0 + abyssLevel()*10, 8 + abyssLevel()*15);
    }

    @Override
    public boolean canAttack(Char enemy) {
        return (super.canAttack(enemy) || new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos);
    }

    protected boolean doAttack( Char enemy ) {

        boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
        if (visible) {
            sprite.zap( enemy.pos );
        } else {
            zap();
        }

        return !visible;
    }

    //used so resistances can differentiate between melee and magical attacks
    public static class Bolt{}

    protected void zap() {

        spend(TIME_TO_ZAP);

        Char enemy = this.enemy;

        if (hit( this, enemy, true )) {

            Eradication eradication = enemy.buff(Eradication.class);
            float multiplier = 1f;
            if (eradication != null){
                multiplier = (float) (Math.pow(1.2f, eradication.combo));
            }
            int damage = Random.Int( 4 + abyssLevel()*4, 10 + abyssLevel()*8 );
            if (buff(Shrink.class) != null|| enemy.buff(TimedShrink.class) != null) damage *= 0.6f;

            int dmg = Math.round(damage * multiplier);

            /*if (enemy.buff(WarriorParry.BlockTrock.class) != null){
                enemy.sprite.emitter().burst( Speck.factory( Speck.FORGE ), 15 );
                SpellSprite.show(enemy, SpellSprite.BLOCK, 2f, 2f, 2f);
                Buff.affect(enemy, Barrier.class).incShield(Math.round(dmg*1.25f));
                hero.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString(Math.round(dmg*1.25f)), FloatingText.SHIELDING );
                enemy.buff(WarriorParry.BlockTrock.class).triggered = true;
            } else {

                */Buff.prolong(enemy, Eradication.class, Eradication.DURATION).combo++;

                enemy.damage(dmg, new Bolt());

                if (!enemy.isAlive() && enemy == Dungeon.hero) {
                    Dungeon.fail(getClass());
                    GLog.n(Messages.get(this, "bolt_kill"));
                }
            /*}*/
        } else {
            enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
        }
    }

    public void onZapComplete() {
        zap();
        next();
    }

    @Override
    public void call() {
        next();
    }

    {
        resistances.add( Grim.class );
        immunities.add(WandOfPrismaticLight.class);
        immunities.add(Blindness.class);
        immunities.add(Vertigo.class);
    }

    public static class Eradication extends FlavourBuff {

        public static final float DURATION = 4f;

        {
            type = buffType.NEGATIVE;
            announced = true;
        }

        public int combo;

        @Override
        public void storeInBundle(Bundle bundle) {
            super.storeInBundle(bundle);
            bundle.put("combo", combo);
        }

        @Override
        public void restoreFromBundle(Bundle bundle) {
            super.restoreFromBundle(bundle);
            combo = bundle.getInt("combo");
        }

        @Override
        public int icon() {
            return BuffIndicator.ERADICATION;
        }

        @Override
        public String toString() {
            return Messages.get(this, "name");
        }

        @Override
        public String desc() {
            return Messages.get(this, "desc", dispTurns(), (float)Math.pow(1.2f, combo));
        }
    }
}
