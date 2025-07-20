package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.Rosary;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.HashSet;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

public class Pray extends Buff implements ActionIndicator.Action {

    public static final int EXP_FOR_PRAY = 5;

    {
        revivePersists = true;
    }

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        ActionIndicator.setAction(this);
    }

    @Override
    public int actionIcon() {
        return HeroIcon.PRAY;
    }

    @Override
    public int indicatorColor() {
        return 0xFF6F1B;
    }

    @Override
    public boolean usable() {
        return hero != null && hero.exp >= 5;
    }

    @Override
    public void doAction() {

        Hero hero = Dungeon.hero;

        hero.sprite.operate(hero.pos);
        Sample.INSTANCE.play(Assets.Sounds.READ);

        if (hero.expUsed(5)) {
            onPray();
            GLog.p(Messages.get(this, "pray_sucess"));
        } else {
            GLog.i(Messages.get(this, "pray_fail"));
        }

        hero.spendAndNext(Actor.TICK);
    }

    public void onPray() {
        Buff.affect(hero, Praying.class, 1f);

        if (hero.buff(Talent.PrayForDeadTracker.class) != null) {
            switch (hero.pointsInTalent(Talent.PRAY_FOR_DEAD, Talent.RK_CRUSADER)) {
                case 3:
                    hero.heal(3);
                case 2:
                    hero.sprite.showStatusWithIcon(CharSprite.POSITIVE, Integer.toString(EXP_FOR_PRAY), FloatingText.EXPERIENCE);
                    hero.earnExp(EXP_FOR_PRAY, this.getClass());
                case 1: default:
                    Buff.affect(hero, Bless.class, 3f);
            }
            hero.buff(Talent.PrayForDeadTracker.class).detach();
        }
        if (hero.buff(Bless.class) != null) {
            Buff.affect(hero, Bless.class, 3f + 1f); //연장 시 Buff.affect()애서 제공하는 추가 1턴이 없기 때문에 별개로 추가 -> 기도하는 1턴 + 3턴 -> 3턴 연장
        } else {
            Buff.affect(hero, Bless.class, 3f);
        }
        Rosary rosary = hero.belongings.getItem(Rosary.class);
        if (rosary != null) {
            rosary.onPray(hero);
        }
    }

    @Override
    public boolean attachTo(Char target) {
        if (super.attachTo(target)){
            if (hero != null) {
                if (hero.subClass.is(HeroSubClass.CRUSADER)) {
                    ActionIndicator.setAction(this);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static class Praying extends FlavourBuff {

//        {
//            //acts after mobs, this should be detached after mob attack
//            actPriority = MOB_PRIO - 1;
//        }

        public void defenseProc(Char enemy, int damage) {
            if (hero.hasTalent(Talent.GODS_JUDGEMENT, Talent.RK_CRUSADER)) {
                Buff.affect(enemy, Talent.JudgementTracker.class, Talent.JudgementTracker.DURATION);
            }
            if (hero.hasTalent(Talent.PUNISHMENT, Talent.RK_CRUSADER)) {
                Buff.affect(hero, Punishment.class);
            }
        }

        public static final HashSet<Class> RESISTS = new HashSet<>();
        static {
            RESISTS.add( Burning.class );
            RESISTS.add( Chill.class );
            RESISTS.add( Frost.class );
            RESISTS.add( Ooze.class );
            RESISTS.add( Paralysis.class );
            RESISTS.add( Poison.class );
            RESISTS.add( Corrosion.class );

            RESISTS.add( ToxicGas.class );
            RESISTS.add( Electricity.class );

            RESISTS.addAll( AntiMagic.RESISTS );
        }

        public static float resist( Char target, Class effect ){
            if (!(target instanceof Hero)) return 1f;
            if (!((Hero)target).hasTalent(Talent.CLEANSING_PRAY, Talent.RK_CRUSADER)) return 1f;

            for (Class c : RESISTS){
                if (c.isAssignableFrom(effect)){
                    return 1f - hero.pointsInTalent(Talent.CLEANSING_PRAY, Talent.RK_CRUSADER)/3f; //67%/33%/0% damage at +1/+2/+3
                }
            }

            return 1f;
        }

//        @Override
//        public boolean act() {
//
//            return true;
//        }
    }

    public static class Punishment extends CounterBuff {

        @Override
        public boolean attachTo(Char target) {
            Sample.INSTANCE.play(Assets.Sounds.LIGHTNING);
            return super.attachTo(target);
        }

        public void hit(Char enemy, int damage) {
            enemy.damage(Hero.heroDamageIntRange(Math.round(damage*0.2f), Math.round(damage*0.6f)), target);
            ThunderImbue.thunderEffect(enemy.sprite);
            countUp(1);
            if (count() >= hero.pointsInTalent(Talent.PUNISHMENT, Talent.RK_CRUSADER)) { //it counts from 0, so the count become +1 after 1 hit
                detach();
            }
        }

        @Override
        public void fx(boolean on) {
            if (on) target.sprite.add(CharSprite.State.ELECTRIC);
            else target.sprite.remove(CharSprite.State.ELECTRIC);
        }
    }
}
