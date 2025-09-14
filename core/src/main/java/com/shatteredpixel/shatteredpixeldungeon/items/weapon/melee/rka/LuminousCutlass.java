package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.rka;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Evolution;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Scimitar;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.alchemy.AlchemyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;
import java.util.Arrays;

public class LuminousCutlass extends MeleeWeapon implements Talent.SpellbladeForgeryWeapon, AlchemyWeapon {

    {
        image = ItemSpriteSheet.LUMINOUS_CUTLASS;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1.2f;

        tier = 6;
        DLY = 0.8f; //1.25x speed
    }

    @Override
    public int max(int lvl) {
        return  4*(tier) +    //16 base, down from 20
                lvl*(tier);   //scaling unchanged
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {

        affected.clear();
        arcs.clear();

        arc(attacker, defender, /*attacker.buff(BrawlerBuff.BrawlingTracker.class) != null ? 100000 : */3, affected, arcs);

        affected.remove(defender); //defender isn't hurt by lightning
        for (Char ch : affected) {
            if (ch.alignment != attacker.alignment) {
                int dmg = damage;
                if (enchantment != null) {
                    dmg = enchantment.proc(this, attacker, ch, damage);
                }
                ch.damage(Math.round(dmg * 0.5f), this);
            }
        }

        attacker.sprite.parent.addToFront( new Lightning( arcs, null ) );
        Sample.INSTANCE.play( Assets.Sounds.ZAP );
        return super.proc(attacker, defender, damage);
    }

    private ArrayList<Char> affected = new ArrayList<>();

    private ArrayList<Lightning.Arc> arcs = new ArrayList<>();

    public static void arc( Char attacker, Char defender, int dist, ArrayList<Char> affected, ArrayList<Lightning.Arc> arcs ) {

        affected.add(defender);

        defender.sprite.centerEmitter().burst(SparkParticle.FACTORY, 12);
        defender.sprite.centerEmitter().burst(MagicMissile.MagicParticle.FACTORY, 12);
        defender.sprite.flash();

        PathFinder.buildDistanceMap( defender.pos, BArray.not( Dungeon.level.solid, null ), dist );
        for (int i = 0; i < PathFinder.distance.length; i++) {
            if (PathFinder.distance[i] < Integer.MAX_VALUE) {
                Char n = Actor.findChar(i);
                if (n != null && n != attacker && !affected.contains(n)) {
                    arcs.add(new Lightning.Arc(defender.sprite.center(), n.sprite.center()));
                    arc(attacker, n, arcs.size()+3, affected, arcs);
                }
            }
        }
    }

    @Override
    public ItemSprite.Glowing glowing() {
        return enchantment != null && (cursedKnown || !enchantment.curse()) ?
                new ItemSprite.Glowing(enchantment.glowing().color, 0.33f*enchantment.glowing().period) : WHITE;
    }

    /*@Override
    public int warriorAttack(int damage, Char enemy) {
        return damage*2;
    }*/

    private static ItemSprite.Glowing WHITE = new ItemSprite.Glowing( 0xFFFFFF, 0.33f );

    @Override
    public String targetingPrompt() {
        return Messages.get(this, "prompt");
    }

    @Override
    protected DuelistAbility duelistAbility() {
        return new ShortCircuit();
    }

    @Override
    public ArrayList<Class<?extends Item>> weaponRecipe() {
        return new ArrayList<>(Arrays.asList(Scimitar.class, WandOfLightning.class, Evolution.class));
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

    public static class ShortCircuit extends MeleeAbility {

        @Override
        public void afterHit(Char enemy, boolean hit) {
            LuminousCutlass weapon = (LuminousCutlass) weapon();
            weapon.affected.clear();
            weapon.arcs.clear();

            arc(Dungeon.hero, enemy, 3, weapon.affected, weapon.arcs);

            for (Char ch : weapon.affected) {
                if (ch.alignment != Dungeon.hero.alignment) {
                    GameScene.add( Blob.seed( ch.pos, 3, Electricity.class ) );
                    Buff.affect( ch, Paralysis.class, Paralysis.DURATION/3 );
                }
            }
        }
    }
}
