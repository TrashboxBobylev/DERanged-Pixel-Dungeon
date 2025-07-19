package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.SpectreRat;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class SpectreRatSprite extends MobSprite {

    public SpectreRatSprite() {
        super();

        texture( Assets.Sprites.RAT );

        TextureFilm frames = new TextureFilm( texture, 16, 15 );

        idle = new Animation( 4, true );
        idle.frames( frames, 62, 62, 62, 62, 61, 61, 60, 60, 60, 60, 61, 61, 61 );

        run = new Animation( 10, true );
        run.frames( frames, 59, 60, 61, 62, 61, 60, 59 );

        attack = new Animation( 50, false );
        attack.frames( frames, 50, 51, 52, 53, 48 );

        zap = attack.clone();
        zap.delay *= 2.25f;

        die = new Animation( 10, false );
        die.frames( frames, 59, 60, 61, 62 );

        hardlight(0x8f8f8f);

        play( idle );
    }

    public void zap( int cell ) {

        turnTo( ch.pos , cell );
        play( zap );

        MagicMissile.boltFromChar( parent,
                MagicMissile.SHADOW,
                this,
                cell,
                new Callback() {
                    @Override
                    public void call() {
                        ((SpectreRat)ch).onZapComplete();
                    }
                } );
        Sample.INSTANCE.play( Assets.Sounds.ZAP );
    }

    public static void zap(CharSprite sprite, int cell, Callback onZapComplete) {
        MagicMissile.boltFromChar( sprite.parent, MagicMissile.SHADOW, sprite, cell, onZapComplete);
        Sample.INSTANCE.play( Assets.Sounds.ZAP );
    }

    @Override
    public void onComplete( Animation anim ) {
        if (anim == zap) {
            idle();
        }
        super.onComplete( anim );
    }
}
