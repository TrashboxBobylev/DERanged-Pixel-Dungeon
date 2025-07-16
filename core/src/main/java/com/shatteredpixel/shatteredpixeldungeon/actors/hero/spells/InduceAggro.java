package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HolyTome;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class InduceAggro extends TargetedClericSpell {
    public static final InduceAggro INSTANCE = new InduceAggro();

    @Override
    public int icon(){
        return HeroIcon.INDUCE_AGGRO;
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", 2+Dungeon.hero.pointsInTalent(Talent.INDUCE_AGGRO, Talent.RK_PALADIN)) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
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

        Sample.INSTANCE.play(Assets.Sounds.READ);

        hero.busy();
        hero.spendAndNext(Actor.TICK);
        hero.sprite.zap(target);

        if (ch.alignment == Char.Alignment.ENEMY) {
            if (Char.hasProp(ch, Char.Property.BOSS) || Char.hasProp(ch, Char.Property.MINIBOSS)) {
                Buff.prolong(ch, StoneOfAggression.Aggression.class, 2f);
            } else {
                Buff.prolong(ch, StoneOfAggression.Aggression.class, 2+hero.pointsInTalent(Talent.INDUCE_AGGRO, Talent.RK_PALADIN));
            }
        } else if (ch.alignment == Char.Alignment.ALLY) {
            Buff.prolong(ch, StoneOfAggression.Aggression.class, 3*(2+hero.pointsInTalent(Talent.INDUCE_AGGRO, Talent.RK_PALADIN)));
        }

        CellEmitter.center(target).start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );

        onSpellCast(tome, hero);
    }
}
