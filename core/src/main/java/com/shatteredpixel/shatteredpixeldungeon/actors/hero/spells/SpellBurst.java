package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HolyTome;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class SpellBurst extends TargetedClericSpell implements Hero.Doom {
    public static final SpellBurst INSTANCE = new SpellBurst();

    @Override
    public int icon(){
        return HeroIcon.SPELL_BURST;
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", minDamage(), maxDamage() )+ "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
    }

    @Override
    public float chargeUse(Hero hero) {
        return 2f;
    }

    private int minDamage() {
        return 2+Dungeon.hero.lvl/5;
    }

    private int maxDamage() {
        return 10+Dungeon.hero.lvl;
    }
//
//    private int minDamage() {
//        return 2+Dungeon.hero.lvl/5;
//    }
//
//    private int maxDamage() {
//        return 4+Dungeon.hero.lvl/2;
//    }

    @Override
    public int targetingFlags() {
        return Ballistica.STOP_TARGET;
    }

    @Override
    protected void onTargetSelected(HolyTome tome, Hero hero, Integer target) {
        if (target == null) return;

        if (!Dungeon.level.heroFOV[target]) {
            GLog.w(Messages.get(ClericSpell.class, "not_in_fov"));
            return;
        }

        Char ch = Actor.findChar(target);
        if (ch == null) {
            GLog.w(Messages.get(ClericSpell.class, "no_target"));
            return;
        }

        int buffs = 0;
        for (Buff b : ch.buffs()) {
            if (b.type == Buff.buffType.NEGATIVE) {
                buffs++;
                b.detach();
                Sample.INSTANCE.play(Assets.Sounds.BURNING);
            }
        }

        if (buffs <= 0) {
            GLog.n(Messages.get(this, "no_debuffs"));
            return;
        }

        for (int i = 0; i < buffs; i++) {
            if (!ch.isAlive()) break;

            if (ch.alignment == Char.Alignment.ALLY) {
                ch.damage(minDamage(), INSTANCE);
                if (ch == hero && !hero.isAlive()) {
                    Badges.validateDeathFromFriendlyMagic();
                }
            } else {
                ch.damage(Random.NormalIntRange(minDamage(), maxDamage()), INSTANCE);
                if (!ch.isAlive()) tome.directCharge(2);
            }
        }

        Sample.INSTANCE.play(Assets.Sounds.READ);

        ch.sprite.emitter().burst( ShadowParticle.UP, 5+2*buffs );

        hero.busy();
        hero.spendAndNext(Actor.TICK);
        hero.sprite.zap(target);

        onSpellCast(tome, hero);
    }

    @Override
    public void onDeath() {
        Dungeon.fail( this );
        GLog.n(Messages.get(this, "ondeath"));
    }
}
