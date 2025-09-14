package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.rka;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Evolution;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blazing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.alchemy.AlchemyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MoltenStrife extends MeleeWeapon implements Talent.SpellbladeForgeryWeapon, AlchemyWeapon {
    {
        image = ItemSpriteSheet.MOLTEN_STRIFE;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1.6f;

        tier = 6;
    }

    @Override
    public int max(int lvl) {
        return 4*(tier+1) + tier*lvl;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if (Random.Int(5+buffedLvl()) < (buffedLvl()+1)) {
            Bomb.doNotDamageHero = true;
            new Bomb().explode(defender.pos);
            Bomb.doNotDamageHero = false;
        }

        return super.proc(attacker, defender, damage);
    }

    @Override
    public String statsInfo() {
        if (isIdentified())
            return Messages.get(this, "stats_desc", new DecimalFormat("#.#").
                    format(100 * ((buffedLvl()+1f) / (5f+buffedLvl()))));
        return Messages.get(this, "stats_desc", new DecimalFormat("#.#").format(20));
    }

    /*@Override
    public int warriorAttack(int damage, Char enemy) {
        Bomb.doNotDamageHero = true;
        new ShrapnelBomb().explode(enemy.pos);
        new ShrapnelBomb().explode(enemy.pos);
        Bomb.doNotDamageHero = false;
        return super.warriorAttack(damage, enemy);
    }*/

    @Override
    protected int baseChargeUse(Hero hero, Char target){
        return 3;
    }

    @Override
    public String abilityInfo() {
        if (levelKnown){
            return Messages.get(this, "ability_desc", (4 + Dungeon.scalingDepth())*5/4, (12 + 3*Dungeon.scalingDepth())*5/4);
        } else {
            return Messages.get(this, "typical_ability_desc", (4 + Dungeon.scalingDepth())*5/4, (12 + 3*Dungeon.scalingDepth())*5/4);
        }
    }

    public String upgradeAbilityStat(int level){
        return (4 + Dungeon.scalingDepth())*5/4 + "-" + (12 + 3*Dungeon.scalingDepth())*5/4;
    }

    @Override
    protected void duelistAbility(Hero hero, Integer target) {
        beforeAbilityUsed(hero, null);
        Enchanting.show(hero, new MoltenStrife(){
            @Override
            public ItemSprite.Glowing glowing() {
                return Blazing.ORANGE;
            }
        });
        hero.busy();
        hero.sprite.operate(hero.pos, () -> {
            ArrayList<Char> affected = new ArrayList<>();
            Sample.INSTANCE.play( Assets.Sounds.BLAST, 3f );
            PathFinder.buildDistanceMap( hero.pos, BArray.not( Dungeon.level.solid, null ), hero.viewDistance );
            for (int i = 0; i < PathFinder.distance.length; i++) {
                if (PathFinder.distance[i] < Integer.MAX_VALUE) {
                    if (Dungeon.level.heroFOV[i]) {
                        CellEmitter.get(i).burst(SmokeParticle.FACTORY, 5);
                    }
                    Char ch = Actor.findChar(i);
                    if (ch != null){
                        if (ch instanceof Hero) {
                            continue;
                        }
                        affected.add(ch);
                    }
                }
            }

            for (Char ch : affected){
                // 125% bomb damage that pierces armor.
                int damage = Math.round(Random.NormalIntRange( 4 + Dungeon.scalingDepth(), 12 + 3*Dungeon.scalingDepth() )*1.25f);
                ch.damage(damage, this);
            }

            hero.sprite.attack(hero.pos, () -> {
                hero.sprite.idle();
                hero.spendAndNext(Actor.TICK);
                afterAbilityUsed(hero);
            });
        });
    }

    @Override
    public ArrayList<Class<?extends Item>> weaponRecipe() {
        return new ArrayList<>(Arrays.asList(Sword.class, Bomb.class, Evolution.class));
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

}
