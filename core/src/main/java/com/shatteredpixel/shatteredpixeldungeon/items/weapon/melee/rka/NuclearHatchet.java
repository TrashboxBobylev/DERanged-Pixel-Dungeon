package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.rka;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ConfusionGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StenchGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfToxicEssence;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Evolution;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.HandAxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.alchemy.AlchemyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ConeAOE;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class NuclearHatchet extends MeleeWeapon implements AlchemyWeapon {

    {
        image = ItemSpriteSheet.NUCLEAR_HATCHET;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1f;

        tier = 6;
        ACC = 1.5f; //50% boost to accuracy
    }

    @Override
    public int max(int lvl) {
        return  5*(tier-2) +    //20 base, down from 30
                lvl*(tier-2);   //scaling unchanged
    }

    /*@Override
    public int warriorAttack(int damage, Char enemy) {
        GameScene.add( Blob.seed( enemy.pos, 700, CorrosiveGas.class ).setStrength((int) (2 + Dungeon.scalingDepth() /2.5f)));
        return super.warriorAttack(damage, enemy);
    }*/

    @Override
    public String targetingPrompt() {
        return Messages.get(this, "prompt");
    }

    @Override
    protected DuelistAbility duelistAbility() {
        return new Irradiate();
    }

    @Override
    public ArrayList<Class<?extends Item>> weaponRecipe() {
        return new ArrayList<>(Arrays.asList(HandAxe.class, ElixirOfToxicEssence.class, Evolution.class));
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

    public static class Irradiate extends MeleeAbility {

        @Override
        public void afterHit(Char enemy, boolean hit) {
            Buff.affect(enemy, Exposed.class, Exposed.DURATION);

            //trace a ballistica to our target (which will also extend past them
            Ballistica trajectory = new Ballistica(Dungeon.hero.pos, enemy.pos, Ballistica.STOP_TARGET);
            //trim it to just be the part that goes past them
            trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.STOP_SOLID);

            ConeAOE cone = new ConeAOE( trajectory,
                    7,
                    90,
                    Ballistica.STOP_TARGET | Ballistica.STOP_SOLID | Ballistica.IGNORE_SOFT_SOLID);

            for( int cell : cone.cells ){

                //ignore caster cell
                if (cell == trajectory.sourcePos){
                    continue;
                }

                //knock doors open
                if (Dungeon.level.map[cell] == Terrain.DOOR){
                    Level.set(cell, Terrain.OPEN_DOOR);
                    GameScene.updateMap(cell);
                }

                if (Dungeon.level.heroFOV[cell]) {
                    Splash.at(cell, 0x68cf32, 25);
                }

                Char ch = Actor.findChar( cell );
                if (ch != null && ch.alignment != Char.Alignment.ALLY) {
                    Buff.affect(ch, Exposed.class, Exposed.DURATION);
                }
            }
        }
    }

    @Override
    protected int baseChargeUse(Hero hero, Char target) {
        return 2;
    }

    public static class Effect extends Buff {

        {
            type = buffType.POSITIVE;
        }

        public static final float DURATION	= 50f;

        protected float left;

        private static final String LEFT	= "left";

        @Override
        public void storeInBundle( Bundle bundle ) {
            super.storeInBundle( bundle );
            bundle.put( LEFT, left );
        }

        @Override
        public void restoreFromBundle( Bundle bundle ) {
            super.restoreFromBundle( bundle );
            left = bundle.getFloat( LEFT );
        }

        public void set( float duration ) {
            this.left = duration;
        }

        @Override
        public boolean act() {
            GameScene.add(Blob.seed(target.pos, 75, ToxicGas.class));
            GameScene.add(Blob.seed(target.pos, 75, ConfusionGas.class));
            GameScene.add(Blob.seed(target.pos, 17, StenchGas.class));

            spend(TICK);
            left -= TICK;
            if (left <= 0){
                detach();
            }

            return true;
        }

        {
            immunities.add( ToxicGas.class );
            immunities.add( ConfusionGas.class );
            immunities.add( StenchGas.class );
        }
    }

    public static class Exposed extends FlavourBuff {

        {
            type = buffType.NEGATIVE;
            announced = true;
        }

        public static final float DURATION	= 15f;

        @Override
        public int icon() {
            return BuffIndicator.CORRUPT;
        }

        @Override
        public void tintIcon(Image icon) {
            icon.hardlight(0x377b22);
        }

        @Override
        public float iconFadePercent() {
            return Math.max(0, (DURATION - visualcooldown()) / DURATION);
        }

    }
}
