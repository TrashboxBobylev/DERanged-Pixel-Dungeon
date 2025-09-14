package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.rka;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Pickaxe;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Evolution;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfRegrowth;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.alchemy.AlchemyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;
import java.util.Arrays;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

public class BloomingPick extends MeleeWeapon implements AlchemyWeapon {

    public static final String AC_MINE	= "MINE";

    public static final float TIME_TO_MINE = 3f;

    {
        image = ItemSpriteSheet.BLOOMING_PICK;
        hitSound = Assets.Sounds.EVOKE;
        hitSoundPitch = 1.2f;
        defaultAction = AC_MINE;

        tier = 6;
    }

    @Override
    public int max(int lvl) {
        return  Math.round(4*(tier+1)) +     //24 base, down from 30
                lvl*Math.round((tier+1));  //+7 per level
    }

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_MINE );
        return actions;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        int healAmt = Math.round(damage*0.666f);
        healAmt = Math.min( healAmt, attacker.HT - attacker.HP );

        if (healAmt > 0 && attacker.isAlive()) {

            attacker.HP += healAmt;
            attacker.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
            attacker.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );

        }
        return super.proc(attacker, defender, damage);
    }

    @Override
    public void execute( final Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals(AC_MINE)) {

            if (Dungeon.bossLevel()){
                GLog.w(Messages.get(this, "no_boss"));
            } else {
                for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {

                    final int pos = hero.pos + PathFinder.NEIGHBOURS8[i];
                    if (Dungeon.level.insideMap(pos) && (Dungeon.level.map[pos] == Terrain.WALL || Dungeon.level.map[pos] == Terrain.DOOR)) {

                        hero.spend(TIME_TO_MINE);
                        hero.busy();

                        hero.sprite.attack(pos, new Callback() {

                            @Override
                            public void call() {

                                CellEmitter.center(pos).burst(Speck.factory(Speck.STAR), 7);
                                Sample.INSTANCE.play(Assets.Sounds.EVOKE);
                                Sample.INSTANCE.play(Assets.Sounds.ROCKS);

                                Level.set(pos, Terrain.EMBERS);
                                GameScene.updateMap(pos);

                                hero.onOperateComplete();
                            }
                        });

                        return;
                    }
                }

                GLog.w(Messages.get(this, "no_vein"));
            }

        }
    }

    /*@Override
    public int warriorAttack(int damage, Char enemy) {
        WandOfRegrowth regrowth = new WandOfRegrowth();
        regrowth.upgrade(level());
        regrowth.fx(new Ballistica(hero.pos, enemy.pos, Ballistica.MAGIC_BOLT), () -> {
            regrowth.onZap(new Ballistica(hero.pos, enemy.pos, Ballistica.MAGIC_BOLT));
        });
        return super.warriorAttack(damage, enemy);
    }*/

    @Override
    public String targetingPrompt() {
        return Messages.get(this, "prompt");
    }

    @Override
    protected DuelistAbility duelistAbility() {
        return new VineStalling();
    }

    @Override
    public ArrayList<Class<?extends Item>> weaponRecipe() {
        return new ArrayList<>(Arrays.asList(Pickaxe.class, WandOfRegrowth.class, Evolution.class));
    }

    @Override
    public String discoverHint() {
        return AlchemyWeapon.hintString(weaponRecipe());
    }

    @Override
    public String desc() {
        String info = super.desc();

        info += "\n\n" + AlchemyWeapon.hintString(weaponRecipe());

        return info;
    }

    public static class VineStalling extends MeleeAbility {

        @Override
        public float dmgMulti(Char enemy) {
            return 0f;
        }

        @Override
        protected void playSFX() {
            Sample.INSTANCE.play(Sample.INSTANCE.play(Assets.Sounds.PLANT, 2f, 0.8f));
        }

        @Override
        public void afterHit(Char enemy, boolean hit) {
            if (enemy.isAlive()) {
                Buff.affect(enemy, VineCovered.class);
                float vineTime = (enemy.properties().contains(Char.Property.BOSS) || enemy.properties().contains(Char.Property.MINIBOSS)) ? 5f : 100_000_000f;
                enemy.spendConstant( vineTime );
                ((Mob) enemy).clearEnemy();
                for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
                    if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos] && Dungeon.level.distance(mob.pos, enemy.pos) <= 3) {
                        mob.aggro(enemy);
                    }
                }
                enemy.sprite.centerEmitter().burst( LeafParticle.LEVEL_SPECIFIC, 15 );
                for (Buff buff : enemy.buffs()) {
                    if (buff.type == Buff.buffType.NEGATIVE && !(buff instanceof VineCovered)) {
                        buff.detach();
                    }
                }
            }
        }
    }

    @Override
    protected int baseChargeUse(Hero hero, Char target) {
        return 3;
    }

    public static class VineCovered extends AllyBuff {

        {
            type = buffType.NEGATIVE;
            announced = true;

            properties.add(Char.Property.IMMOVABLE);
        }

        @Override
        public void fx(boolean on) {
            if (on) target.sprite.add(CharSprite.State.VINECOVERED);
            else    target.sprite.remove(CharSprite.State.VINECOVERED);
        }

        @Override
        public int icon() {
            return BuffIndicator.HERB_HEALING;
        }

        @Override
        public void detach() {
            super.detach();
            target.alignment = Char.Alignment.ENEMY;
            AllyBuff.affectAndLoot((Mob) target, hero, PlaceVineHolder.class);
        }
    }

    public static class PlaceVineHolder extends AllyBuff {}
}
