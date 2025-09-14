package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Light;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Shrink;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.TimedShrink;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WarriorParry;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SpectreRatSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.Arrays;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

public class SpectreRat extends AbyssalMob implements Callback {

    private static final float TIME_TO_ZAP	= 1f;

    {
        spriteClass = SpectreRatSprite.class;

        HP = HT = 100;
        defenseSkill = 23;
        viewDistance = Light.DISTANCE;

        EXP = 13;

        loot = Generator.Category.POTION;
        lootChance = 0.33f;

        properties.add(Property.DEMONIC);
    }

    @Override
    public int attackSkill( Char target ) {
        return 36 + abyssLevel();
    }

    @Override
    public int drRoll() {
        return super.drRoll() + Random.NormalIntRange(0 + abyssLevel()*5, 10 + abyssLevel()*10);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 4 + abyssLevel()*2, 14 + abyssLevel()*3 );
    }

    @Override
    public float attackDelay() {
        return super.attackDelay()*0.5f;
    }

    @Override
    public boolean canAttack(Char enemy) {
        if (buff(Talent.AntiMagicBuff.class) != null){
            return super.canAttack(enemy);
        }
        return super.canAttack(enemy) || new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
    }

    protected boolean doAttack( Char enemy ) {
        if (buff(Talent.AntiMagicBuff.class) != null){
            return super.doAttack(enemy);
        }
       if (Dungeon.level.adjacent( pos, enemy.pos )
                || new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos != enemy.pos) {

            return super.doAttack( enemy );

        } else {

            if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
                sprite.zap( enemy.pos );
                return false;
            } else {
                zap();
                return true;
            }
        }
    }

    //used so resistances can differentiate between melee and magical attacks
    public static class DarkBolt{}

    protected void zap() {

        spend(TIME_TO_ZAP);

        Char enemy = this.enemy;

        if (hit( this, enemy, true )) {
            //TODO would be nice for this to work on ghost/statues too
            if (enemy == hero && enemy.buff(WarriorParry.BlockTrock.class) == null && Random.Int( 2 ) == 0) {
                Buff.prolong( enemy, Random.element(Arrays.asList(
                        Blindness.class, Slow.class, Vulnerable.class, Hex.class,
                        Weakness.class, Degrade.class, Cripple.class
                )), Degrade.DURATION );
                Sample.INSTANCE.play( Assets.Sounds.DEBUFF );
            }

            int dmg = Random.NormalIntRange( 14 + abyssLevel()*6, 20 + abyssLevel()*9 );
            if (buff(Shrink.class) != null|| enemy.buff(TimedShrink.class) != null) dmg *= 0.6f;
            if (enemy.buff(WarriorParry.BlockTrock.class) != null){
                enemy.sprite.emitter().burst( Speck.factory( Speck.FORGE ), 15 );
                SpellSprite.show(enemy, SpellSprite.BLOCK, 2f, 2f, 2f);
                Buff.affect(enemy, Barrier.class).incShield(Math.round(dmg*1.25f));
                hero.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString(Math.round(dmg*1.25f)), FloatingText.SHIELDING );
                enemy.buff(WarriorParry.BlockTrock.class).triggered = true;
            } else {
                enemy.damage(dmg, new DarkBolt());

                if (enemy == hero && !enemy.isAlive()) {
                    Dungeon.fail(getClass());
                    GLog.n(Messages.get(this, "bolt_kill"));
                }
            }
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

    @Override
    public Item createLoot(){

        if (Random.Int(3) == 0 && Random.Int(10) > Dungeon.LimitedDrops.SPECTRE_RAT.count ){
            Dungeon.LimitedDrops.SPECTRE_RAT.drop();
            return new PotionOfHealing();
        } else {
            Item i = Generator.random(Generator.Category.POTION);
            int healingTried = 0;
            while (i instanceof PotionOfHealing){
                healingTried++;
                i = Generator.random(Generator.Category.POTION);
            }

            //return the attempted healing potion drops to the pool
            if (healingTried > 0){
                for (int j = 0; j < Generator.Category.POTION.classes.length; j++){
                    if (Generator.Category.POTION.classes[j] == PotionOfHealing.class){
                        Generator.Category.POTION.probs[j] += healingTried;
                    }
                }
            }

            return i;
        }

    }
}
