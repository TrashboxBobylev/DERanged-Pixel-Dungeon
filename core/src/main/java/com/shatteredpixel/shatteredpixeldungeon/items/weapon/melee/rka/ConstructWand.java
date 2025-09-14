package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.rka;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Statue;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Evolution;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfWarding;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.alchemy.AlchemyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.sprites.StatueSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class ConstructWand extends MeleeWeapon implements AlchemyWeapon {

    {
        image = ItemSpriteSheet.CONSTRUCT_WAND;
        hitSound = Assets.Sounds.HIT_STRONG;
        hitSoundPitch = 0.85f;

        tier = 6;
    }

    @Override
    public int max(int lvl) {
        return Math.round(4.5f*(tier+1) + (tier)*lvl);
    }

    @Override
    public String statsInfo() {
        return Messages.get(this, "stats_desc", 7 + Dungeon.scalingDepth() * 3);
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        for (int i : PathFinder.NEIGHBOURS9){

            if (!Dungeon.level.solid[attacker.pos + i]
                    && !Dungeon.level.pit[attacker.pos + i]
                    && Actor.findChar(attacker.pos + i) == null
                    && attacker == Dungeon.hero) {

                GuardianKnight guardianKnight = new GuardianKnight();
                guardianKnight.weapon = this;
                guardianKnight.pos = attacker.pos + i;
                guardianKnight.aggro(defender);
                GameScene.add(guardianKnight);
                Dungeon.level.occupyCell(guardianKnight);

                CellEmitter.get(guardianKnight.pos).burst(Speck.factory(Speck.EVOKE), 4);
                break;
            }
        }
        return super.proc(attacker, defender, damage);
    }

    /*@Override
    public int warriorAttack(int damage, Char enemy) {
        for (int i : PathFinder.NEIGHBOURS9){

            if (!Dungeon.level.solid[Dungeon.hero.pos + i]
                    && !Dungeon.level.pit[Dungeon.hero.pos + i]
                    && Actor.findChar(Dungeon.hero.pos + i) == null) {

                GuardianKnight guardianKnight = new GuardianKnight();
                guardianKnight.weapon = this;
                guardianKnight.pos = Dungeon.hero.pos + i;
                guardianKnight.HP = guardianKnight.HT = guardianKnight.HT / 2;
                guardianKnight.aggro(enemy);
                GameScene.add(guardianKnight);
                Dungeon.level.occupyCell(guardianKnight);

                CellEmitter.get(guardianKnight.pos).burst(Speck.factory(Speck.EVOKE), 4);
            }
        }
        return 0;
    }*/

    @Override
    protected int baseChargeUse(Hero hero, Char target){
        return 5;
    }

    @Override
    protected void duelistAbility(Hero hero, Integer target) {
        Invisibility.dispel();
        beforeAbilityUsed(hero, null);
        Enchanting.show(hero, new ConstructWand(){
            @Override
            public ItemSprite.Glowing glowing() {
                return new ItemSprite.Glowing( 0x0000FF );
            }
        });
        ArrayList<GuardianKnight> badBoys = new ArrayList<>(9);
        Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
        for (int i : PathFinder.NEIGHBOURS9){

            if (!Dungeon.level.solid[Dungeon.hero.pos + i]
                    && !Dungeon.level.pit[Dungeon.hero.pos + i]
                    && Actor.findChar(Dungeon.hero.pos + i) == null) {

                GuardianKnight guardianKnight = new GuardianKnight();
                guardianKnight.weapon = this;
                guardianKnight.pos = Dungeon.hero.pos + i;
                GameScene.add(guardianKnight);
                Dungeon.level.occupyCell(guardianKnight);

                CellEmitter.get(guardianKnight.pos).burst(Speck.factory(Speck.EVOKE), 4);

                badBoys.add(guardianKnight);
            }
        }
        hero.busy();
        hero.sprite.operate(hero.pos, () -> {
            Sample.INSTANCE.play( Assets.Sounds.CHALLENGE );
            Buff.affect(hero, ConstructStasis.class, 10f);
            for (GuardianKnight knight: badBoys){
                Buff.affect(knight, Adrenaline.class, 10f);
                Buff.affect(knight, Bless.class, 10f);
                Buff.affect(knight, MagicImmune.class, 10f);
                Barkskin.conditionallyAppend(knight, 2 + hero.lvl/3, 2);
            }
            hero.next();
            afterAbilityUsed(hero);
        });
    }

    @Override
    public ArrayList<Class<?extends Item>> weaponRecipe() {
        return new ArrayList<>(Arrays.asList(WandOfWarding.class, WandOfLivingEarth.class, Evolution.class));
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

    public static class ConstructStasis extends FlavourBuff {
        @Override
        public boolean attachTo(Char target) {
            if (super.attachTo(target)){
                target.invisible++;
                target.paralysed++;
                Dungeon.observe();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void detach() {
            if (target.invisible > 0) target.invisible--;
            if (target.paralysed > 0) target.paralysed--;
            super.detach();
            target.sprite.idle();
            Dungeon.observe();
        }
    }

    public static class GuardianKnight extends Statue {
        {
            state = WANDERING;
            spriteClass = GuardianSprite.class;
            alignment = Alignment.ALLY;
        }

        public GuardianKnight() {
            HP = HT = 7 + Dungeon.scalingDepth() *3;
            defenseSkill = Dungeon.scalingDepth();
        }

        @Override
        public int damageRoll() {
            return Math.round(super.damageRoll()*0.67f);
        }

        @Override
        public void die(Object cause) {
            weapon = null;
            super.die(cause);
        }

        @Override
        public int drRoll() {
            return Random.Int(0, Dungeon.scalingDepth());
        }
    }

    public static class GuardianSprite extends StatueSprite {

        public GuardianSprite(){
            super();
            tint(0x84d4f6, 0.4f);
        }

        @Override
        public void resetColor() {
            super.resetColor();
            tint(0x84d4f6, 0.4f);
        }
    }
}
