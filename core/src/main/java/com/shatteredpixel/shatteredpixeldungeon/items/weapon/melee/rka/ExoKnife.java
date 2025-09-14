package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.rka;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Shockwave;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.ShieldHalo;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ExoParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDivineInspiration;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Evolution;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shortsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.alchemy.AlchemyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;
import java.util.Arrays;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

public class ExoKnife extends MeleeWeapon implements AlchemyWeapon {
    {
        image = ItemSpriteSheet.EXO_KNIFE;
        tier = 6;
        RCH = 12;
    }

    @Override
    public int min(int lvl) {
        return 12 + lvl;
    }

    public static class RunicMissile extends Item {
        {
            image = ItemSpriteSheet.EXO_KNIFE;
        }

        @Override
        public Emitter emitter() {
            Emitter e = new Emitter();
            e.pos(7.5f, 7.5f);
            e.fillTarget = true;
            e.autoKill = false;
            e.pour(ExoParticle.FACTORY, 0.002f);
            return e;
        }
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        PathFinder.buildDistanceMap( defender.pos, BArray.not( Dungeon.level.solid, null ), 2 );
        for (int i = 0; i < PathFinder.distance.length; i++) {
            if (PathFinder.distance[i] < Integer.MAX_VALUE) {
                Char ch = Actor.findChar(i);
                if (ch != null && ch != attacker && ch.alignment != Char.Alignment.ALLY && ch.isAlive()){
                    ch.damage(damageRoll(attacker), hero);
                    if (enchantment != null)
                        enchantment.proc(this, attacker, ch, damage);
                }
                CellEmitter.get(i).burst(ExoParticle.FACTORY, 5);
            }
        }
        return super.proc(attacker, defender, damage);
    }

    @Override
    public int max(int lvl) {
        return  5*(tier-2) +    //20 base, down from 35
                lvl*(tier-1);   //scaling changed to +5
    }

    /*@Override
    public int warriorAttack(int damage, Char enemy) {
        Ballistica bolt = new Ballistica(hero.pos, enemy.pos, Ballistica.WONT_STOP);
        int maxDist = 10;
        int dist = Math.min(bolt.dist, maxDist);
        curUser = hero;
        ConeAOE cone = new ConeAOE( bolt,
                maxDist,
                90,
                Ballistica.STOP_TARGET | Ballistica.STOP_SOLID | Ballistica.IGNORE_SOFT_SOLID);
        ArrayList<Char> affectedChars = new ArrayList<>();
        for( int cell : cone.cells ){

            //ignore caster cell
            if (cell == bolt.sourcePos){
                continue;
            }

            //knock doors open
            if (Dungeon.level.map[cell] == Terrain.DOOR){
                Level.set(cell, Terrain.OPEN_DOOR);
                GameScene.updateMap(cell);
            }

            Wand wand = Reflection.newInstance(Random.element(wands));
            if (wand != null) {
                wand.level(level());
                wand.fx(new Ballistica(hero.pos, cell, Ballistica.STOP_TARGET), () -> {
                    wand.onZap(new Ballistica(hero.pos, cell, Ballistica.STOP_TARGET));
                });
            }

            Char ch = Actor.findChar( cell );
            if (ch != null) {
                affectedChars.add(ch);
            }
        }
        for ( Char ch : affectedChars ){
            ch.damage(damage, this);
        }
        Sample.INSTANCE.play( Assets.Sounds.ZAP );
        Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );
        return 0;
    }

    @Override
    public float warriorDelay() {
        return 5;
    }*/

    @Override
    public String targetingPrompt() {
        return Messages.get(this, "prompt");
    }

    @Override
    public String abilityInfo() {
        if (levelKnown){
            return Messages.get(this, "ability_desc", augment.damageFactor(min()*5), augment.damageFactor(max())*5);
        } else {
            return Messages.get(this, "typical_ability_desc", min(0)*5, max(0)*5);
        }
    }

    public String upgradeAbilityStat(int level){
        return augment.damageFactor(min(level)*5) + "-" + augment.damageFactor(max(level)*5);
    }

    @Override
    protected DuelistAbility duelistAbility() {
        return new ExoEffect();
    }

    @Override
    public ArrayList<Class<?extends Item>> weaponRecipe() {
        return new ArrayList<>(Arrays.asList(Shortsword.class, PotionOfDivineInspiration.class, Evolution.class));
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

    public static class ExoEffect extends MeleeAbility {

        @Override public float accMulti() { return 1f; }

        @Override
        public void afterHit(Char enemy, boolean hit) {
            PathFinder.buildDistanceMap( enemy.pos, BArray.not( Dungeon.level.solid, null ), 2 );
            for (int i = 0; i < PathFinder.distance.length; i++) {
                if (PathFinder.distance[i] < Integer.MAX_VALUE) {
                    Char ch = Actor.findChar(i);
                    if (ch != null && ch.alignment != Char.Alignment.ALLY && ch.isAlive()){
                        Buff.affect(ch, Paralysis.class, 10f);
                        Buff.affect(ch, Shockwave.ShockForceStunHold.class, 10f);
                        ShieldHalo shield = new ShieldHalo(ch.sprite);
                        GameScene.effect(shield);
                        shield.putOut();
                        Viscosity.DeferedDamage deferred = Buff.affect( ch, Viscosity.DeferedDamage.class );
                        int amount = weapon().damageRoll(hero) * 5;
                        deferred.postpone(amount);

                        ch.sprite.showStatus( CharSprite.WARNING, Messages.get(Viscosity.class, "deferred", amount) );
                    }
                    CellEmitter.get(i).burst(ExoParticle.FACTORY, 15);
                }
            }
        }
    }

    @Override
    protected int baseChargeUse(Hero hero, Char target){
        return 3;
    }
}
