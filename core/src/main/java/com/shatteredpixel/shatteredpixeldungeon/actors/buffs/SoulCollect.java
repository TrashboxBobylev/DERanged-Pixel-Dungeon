package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Wraith;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class SoulCollect extends Buff {

    private int souls = 0;

    {
        type = buffType.POSITIVE;
    }

    public void killMob(Mob enemy) {
        int maxSouls = maxSouls();
        if (Dungeon.hero.lvl <= enemy.maxLvl + 2 || Dungeon.hero.buff(AscensionChallenge.class) != null) {
            souls = Math.min(++souls, maxSouls);

            MagicMissile.boltFromChar(Dungeon.hero.sprite.parent, MagicMissile.LIGHT_MISSILE, enemy.sprite, Dungeon.hero.pos, null);
        }
    }

    public int maxSouls() {
        if (Dungeon.hero != null) {
            return 1+(int)(((Hero) target).lvl/10f);
        } else {
            return 0;
        }
    }

    @Override
    public int icon() {
        return BuffIndicator.POISON;
    }

    @Override
    public float iconFadePercent() {
        return Math.max(0, (maxSouls() - souls) / (float) maxSouls());
    }

    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", souls, maxSouls(), healAmount(((Hero) target)));
    }

    public boolean soulUsed(Hero hero) {
        if (this.souls > 0) {
            souls --;
            hero.heal(healAmount(hero), false);
            onResurrect();
            if (souls <= 0) {
                detach();
            }
            return true;
        } else {
            return false;
        }
    }

    public int healAmount(Hero hero) {
        return Math.round(hero.HT * 0.05f) + 1 + 3*hero.pointsInTalent(Talent.OVERCOME, Talent.RK_DEATHKNIGHT);
    }

    public void onResurrect() {
        Hero hero = (Hero) target;
        switch (hero.pointsInTalent(Talent.DEATHS_CHILL, Talent.RK_DEATHKNIGHT)) {
            case 3:
                for (Char ch : Actor.chars()) {
                    if (Dungeon.level.heroFOV[ch.pos] && ch.alignment == Char.Alignment.ENEMY) {
                        Buff.affect(ch, Frost.class, 20f);
                    }
                }
            case 2:
                for (Char ch : Actor.chars()) {
                    Sample.INSTANCE.play(Assets.Sounds.SHATTER);

                    if (Dungeon.level.heroFOV[ch.pos] && ch.alignment == Char.Alignment.ENEMY) {
                        for (int c : PathFinder.NEIGHBOURS9) {
                            int cell = ch.pos + c;
                            GameScene.add( Blob.seed( cell, 20, Freezing.class ) );
                        }
                    }
                }
            case 1:
                Buff.affect(hero, FrostImbue.class, 20f);
            case 0: default:
                break;
        }

        if (hero.hasTalent(Talent.DEATHS_FEAR, Talent.RK_DEATHKNIGHT)) {
            for (Char ch : Actor.chars()) {
                if (Dungeon.level.heroFOV[ch.pos] && ch.alignment == Char.Alignment.ENEMY) {
                    switch (hero.pointsInTalent(Talent.DEATHS_FEAR, Talent.RK_DEATHKNIGHT)) {
                        case 3:
                            Buff.affect(ch, Terror.class, 10f);
                        case 2:
                            Buff.affect(ch, Vulnerable.class, 10f);
                        case 1: default:
                            Buff.affect(ch, Weakness.class, 10f);
                    }
                }
            }
        }

        if (hero.hasTalent(Talent.RESENTMENT, Talent.RK_DEATHKNIGHT)) {
            int wraith = hero.pointsInTalent(Talent.RESENTMENT, Talent.RK_DEATHKNIGHT);
            if (hero.pointsInTalent(Talent.RESENTMENT, Talent.RK_DEATHKNIGHT) == 3) wraith++;

            ArrayList<Integer> candidates = new ArrayList<>();

            for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
                int p = hero.pos + PathFinder.NEIGHBOURS8[i];
                if (Actor.findChar( p ) == null && (Dungeon.level.passable[p] || Dungeon.level.avoid[p])) {
                    candidates.add( p );
                }
            }

            ArrayList<Integer> respawnPoints = new ArrayList<>();

            while (wraith > 0 && !candidates.isEmpty()) {
                int index = Random.index( candidates );

                respawnPoints.add( candidates.remove( index ) );
                wraith--;
            }

            for (Integer point : respawnPoints) {
                Wraith w = Wraith.spawnAt(point, Wraith.class);
                if (w != null) {
                    Buff.affect(w, Corruption.class);
                    if (Dungeon.level.heroFOV[point]) {
                        CellEmitter.get(point).burst(ShadowParticle.CURSE, 6);
                        Sample.INSTANCE.play(Assets.Sounds.CURSED);
                    }
                }
            }
        }
    }

    private static final String SOULS = "souls";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(SOULS, souls);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        souls = bundle.getInt(SOULS);
    }
}
