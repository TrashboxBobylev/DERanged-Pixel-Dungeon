package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Callback;

public class EchSprite extends MobSprite {

    private int cellToAttack;

    public EchSprite() {
        super();

        texture( Assets.Sprites.ECH );

        TextureFilm frames = new TextureFilm( texture, 12, 14 );

        idle = new Animation( 6, true );
        idle.frames( frames, 0, 0, 0, 0, 0, 0, 1 );

        run = new Animation( 10, true );
        run.frames( frames, 0, 1 );

        attack = new Animation( 16, false );
        attack.frames( frames, 2, 3, 4, 5, 3, 2, 0 );

        zap = attack.clone();

        die = new Animation( 10, false );
        die.frames( frames, 0 );

        play( idle );
    }

    @Override
    public void attack( int cell ) {
        if (!Dungeon.level.adjacent( cell, ch.pos )) {

            cellToAttack = cell;
            turnTo( ch.pos , cell );
            play( zap );
            Sample.INSTANCE.play(Assets.Sounds.ZAP);

        } else {

            super.attack( cell );

        }
    }

    @Override
    public void onComplete( Animation anim ) {
        if (anim == zap) {
            idle();

            parent.recycle( MissileSprite.class ).
                    reset( this, cellToAttack, new RunicMissile(), new Callback() {
                        @Override
                        public void call() {
                            ch.onAttackComplete();
                        }
                    } );
        } else {
            super.onComplete( anim );
        }
    }

    public static class RunicMissile extends Item {
        {
            image = ItemSpriteSheet.WAND_PRISMATIC_LIGHT;
        }

        @Override
        public Emitter emitter() {
            Emitter e = new Emitter();
            e.pos(7.5f, 7.5f);
            e.fillTarget = true;
            e.autoKill = false;
            e.pour(MagicMissile.WhiteParticle.FACTORY, 0.0075f);
            return e;
        }
    }
}
