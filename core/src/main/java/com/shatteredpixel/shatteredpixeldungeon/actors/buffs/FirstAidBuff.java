package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class FirstAidBuff extends Buff implements ActionIndicator.Action {
    {
        type = buffType.POSITIVE;
    }

    int recentDamage = 0;

    public static final String RECENT_DAMAGE = "recentDamage";

    public void set(int damage) {
        ActionIndicator.setAction(this);
        this.recentDamage = damage;
    }

    @Override
    public boolean attachTo(Char target) {
        if (super.attachTo(target)){
            ActionIndicator.setAction(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void detach() {
        ActionIndicator.clearAction();
        super.detach();
    }

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(RECENT_DAMAGE, recentDamage);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        recentDamage = bundle.getInt(RECENT_DAMAGE);
    }

    @Override
    public int actionIcon() {
        return HeroIcon.FIRST_AID;
    }

    @Override
    public int indicatorColor() {
        return 0x00FA6C;
    }

    @Override
    public void doAction() {
        Hero hero = Dungeon.hero;
        if (hero.hasTalent(Talent.OINTMENT, Talent.RK_THERAPIST)) {
            float healPercent = 1f/(float)(1+hero.pointsInTalent(Talent.OINTMENT, Talent.RK_THERAPIST));
            Healing healing = Buff.affect(hero, Healing.class);
            healing.setHeal(healAmt(), healPercent, 0);
            healing.applyVialEffect();
        } else {
            hero.heal(healAmt());
        }
        onHeal();
        hero.sprite.operate(hero.pos);
        Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
        Buff.affect(hero, FirstAidBuffCooldown.class).set();

        detach();
    }

    @Override
    public Visual secondaryVisual() {
        int healAmt = healAmt();
        BitmapText txt = new BitmapText(PixelScene.pixelFont);
        txt.text( Integer.toString(healAmt) );
        txt.hardlight(CharSprite.POSITIVE);
        txt.measure();
        return txt;
    }

    public int healAmt() {
        if (Dungeon.hero.hasTalent(Talent.OINTMENT, Talent.RK_THERAPIST)) {
            return Math.max(1, Math.round(recentDamage * 0.4f * (1+0.25f*Dungeon.hero.pointsInTalent(Talent.OINTMENT, Talent.RK_THERAPIST))));
        } else {
            return Math.max(1, Math.round(recentDamage * 0.4f));
        }
    }

    public void onHeal() {
        Hero hero = Dungeon.hero;
        if (hero.hasTalent(Talent.COMPRESS_BANDAGE, Talent.RK_THERAPIST)) {
            Buff.prolong(hero, PainKiller.class, hero.pointsInTalent(Talent.COMPRESS_BANDAGE, Talent.RK_THERAPIST));
        }
        if (hero.hasTalent(Talent.ANTIBIOTICS, Talent.RK_THERAPIST)) {
            switch (hero.pointsInTalent(Talent.ANTIBIOTICS, Talent.RK_THERAPIST)) {
                case 3:
                    Buff.prolong(hero, PotionOfCleansing.Cleanse.class, 2f);
                case 2:
                    Buff.prolong(hero, BlobImmunity.class, 2f);
                case 1: default:
                    for (Buff b : hero.buffs()){
                        if (b.type == Buff.buffType.NEGATIVE
                                && !(b instanceof AllyBuff)
                                && !(b instanceof LostInventory)){
                            b.detach();
                        }
                    }
            }
        }
        if (hero.hasTalent(Talent.SPLINT, Talent.RK_THERAPIST)) {
            Buff.affect(hero, GreaterHaste.class).set(Dungeon.hero.pointsInTalent(Talent.SPLINT, Talent.RK_THERAPIST));
        }
        if (hero.hasTalent(Talent.DEFIBRILLATOR, Talent.RK_THERAPIST)) {
            Sample.INSTANCE.play(Assets.Sounds.CHARGEUP);
            Sample.INSTANCE.play(Assets.Sounds.LIGHTNING);

            ArrayList<Lightning.Arc> arcs = new ArrayList<>();
            ArrayList<Char> affected = new ArrayList<>();

            for (int c : PathFinder.NEIGHBOURS8) {
                Char enemy = findChar(hero.pos + c);
                if (enemy instanceof Mob) {
                    Shocking.arc(hero, enemy, 2, affected, arcs);
                }
            }
            for (Char ch : affected) {
                if (ch.alignment != hero.alignment) {
                    ch.damage(healAmt()*hero.pointsInTalent(Talent.DEFIBRILLATOR, Talent.RK_THERAPIST), hero);
                }
            }
            hero.sprite.parent.addToFront( new Lightning( arcs, null ) );
        }
    }

    public static class FirstAidBuffCooldown extends Buff {
        private float coolDown;
        private int maxCoolDown;

        @Override
        public int icon() {
            return BuffIndicator.TIME;
        }

        @Override
        public void tintIcon(Image icon) {
            icon.hardlight(0x00FA6C);
        }

        @Override
        public float iconFadePercent() {
            return Math.max(0, (maxCoolDown - coolDown)/ maxCoolDown);
        }

        @Override
        public String iconTextDisplay() {
            if (coolDown <= 0) {
                return "";
            }
            return Messages.decimalFormat("#", coolDown);
        }

        public void kill() {
            detach();
            BuffIndicator.refreshHero();
        }

        public void set() {
            switch (Dungeon.hero.pointsInTalent(Talent.QUICK_PREPARE, Talent.RK_THERAPIST)) {
                case 0: default:
                    maxCoolDown = -1;
                    break;
                case 1:
                    maxCoolDown = 50;
                    break;
                case 2:
                    maxCoolDown = 30;
                    break;
                case 3:
                    maxCoolDown = 20;
                    break;
            }
            coolDown = maxCoolDown;
        }

        @Override
        public boolean act() {
            if (Dungeon.hero.hasTalent(Talent.QUICK_PREPARE, Talent.RK_THERAPIST)) {
                if ((coolDown -= TICK) <= 0) {
                    detach();
                }
                spend(TICK);
                return true;
            } else {
                return super.act();
            }
        }

        @Override
        public String desc() {
            if (maxCoolDown == -1) {
                return Messages.get(this, "desc_kill");
            } else {
                return Messages.get(this, "desc", Messages.decimalFormat("#.##", coolDown));
            }
        }

        private static final String MAXCOOLDOWN = "maxCoolDown";
        private static final String COOLDOWN  = "cooldown";

        @Override
        public void storeInBundle(Bundle bundle) {
            super.storeInBundle(bundle);
            bundle.put(MAXCOOLDOWN, maxCoolDown);
            bundle.put(COOLDOWN, coolDown);
        }

        @Override
        public void restoreFromBundle(Bundle bundle) {
            super.restoreFromBundle(bundle);
            maxCoolDown = bundle.getInt( MAXCOOLDOWN );
            coolDown = bundle.getInt( COOLDOWN );
        }
    }
}
