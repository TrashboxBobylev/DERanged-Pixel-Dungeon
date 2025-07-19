package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.FinalFroggit;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class FinalFroggitSprite extends MobSprite {

    public FinalFroggitSprite() {
        super();

        texture( Assets.Sprites.FINAL_FROGGIT );

        TextureFilm frames = new TextureFilm( texture, 16, 16 );

        idle = new Animation( 10, true );
        idle.frames( frames, 0, 1, 0 );

        run = new Animation( 15, true );
        run.frames( frames, 2, 3, 4 );

        die = new Animation( 10, false );
        die.frames( frames, 5, 6, 7, 8 );

        attack = new Animation( 10, false );
        attack.frames( frames, 9, 10, 11 );

        zap = attack.clone();

        play(idle);
    }

    @Override
    public int blood() {
        return 0xFF808080;
    }

    public void zap( int cell ) {

        turnTo( ch.pos , cell );
        play( zap );

        MagicMissile.boltFromChar( parent,
                MagicMissile.MAGIC_MISSILE,
                this,
                cell,
                new Callback() {
                    @Override
                    public void call() {
                        ((FinalFroggit)ch).onZapComplete();
                    }
                } );
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
