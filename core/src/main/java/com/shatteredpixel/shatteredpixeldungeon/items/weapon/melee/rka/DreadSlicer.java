package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.rka;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfDread;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Evolution;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Longsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.alchemy.AlchemyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class DreadSlicer extends MeleeWeapon implements Talent.SpellbladeForgeryWeapon, AlchemyWeapon {
    {
        image = ItemSpriteSheet.DREAD_SWORD;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1.2f;

        tier = 6;
    }

    @Override
    public int min(int lvl) {
        // basically doubled
        return  tier*2 +
                lvl*2;
    }

    @Override
    public boolean canReach(Char owner, int target) {
        Char ch;
        if ((ch = Actor.findChar(target)) != null && ch.buff(Terror.class) != null){
            return canReach(owner, target, Dungeon.level.distance(owner.pos, ch.pos));
        }
        return super.canReach(owner, target);
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if (!Dungeon.level.adjacent(attacker.pos, defender.pos))
            damage /= 2;
        if (Random.Int(2) == 0){
            Terror terror = Buff.prolong(defender, Terror.class, 8f);
            if (terror != null) {
                terror.object = curUser.id();
            }
        }
        if (defender.buff(Terror.class) != null){
            defender.buff(Terror.class).ignoreNextHit = true;
        }
        return super.proc(attacker, defender, damage);
    }

    /*@Override
    public int warriorAttack(int damage, Char enemy) {
        if (!enemy.isImmune(Dread.class)){
            Buff.affect( enemy, Dread.class ).object = curUser.id();
        } else {
            Buff.affect( enemy, Terror.class, Terror.DURATION * 2f ).object = curUser.id();
        }
        return super.warriorAttack(damage, enemy)*2;
    }*/

    @Override
    public String targetingPrompt() {
        return Messages.get(this, "prompt");
    }

    @Override
    protected int baseChargeUse(Hero hero, Char target) {
        return super.baseChargeUse(hero, target)*2;
    }

    @Override
    public String abilityInfo() {
        if (levelKnown){
            return Messages.get(this, "ability_desc", augment.damageFactor(min()*2), augment.damageFactor(max())*2);
        } else {
            return Messages.get(this, "typical_ability_desc", min(0)*2, max(0)*2);
        }
    }

    public String upgradeAbilityStat(int level){
        return augment.damageFactor(min(level)*2) + "-" + augment.damageFactor(max(level)*2);
    }

    @Override
    protected DuelistAbility duelistAbility() {
        return new MeleeAbility(2) {

            @Override
            protected void proc(Hero hero, Char enemy) {
                if (!enemy.isImmune(Dread.class)){
                    Buff.affect( enemy, Dread.class ).object = curUser.id();
                } else {
                    Buff.affect( enemy, Terror.class, Terror.DURATION * 2f ).object = curUser.id();
                }
            }
        };
    }

    @Override
    public ArrayList<Class<?extends Item>> weaponRecipe() {
        return new ArrayList<>(Arrays.asList(Longsword.class, ScrollOfDread.class, Evolution.class));
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
