package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Dragon;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Callback;

public class DragonSprite extends MobSprite {

    private Emitter particles;

    public DragonSprite() {
        super();

        texture( Assets.Sprites.DRAGON );

        TextureFilm frames = new TextureFilm( texture, 16, 16 );

        idle = new Animation( 10, true );
        idle.frames( frames, 0, 1 );

        run = new Animation( 20, true );
        run.frames( frames, 0, 2, 3 );

        attack = new Animation( 12, false );
        attack.frames( frames, 4, 5, 6, 7, 0);

        zap = attack.clone();

        die = new Animation( 15, false );
        die.frames( frames,  8, 9, 10, 11 );

        play( idle );
    }

    public void zap( int cell ) {

        turnTo( ch.pos , cell );
        play( zap );

        MagicMissile.boltFromChar( parent,
                MagicMissile.ABYSS,
                this,
                cell,
                new Callback() {
                    @Override
                    public void call() {
                        ((Dragon)ch).onZapComplete();
                    }
                } );
        Sample.INSTANCE.play( Assets.Sounds.ZAP );
    }



    @Override
    public void link( Char ch ) {
        super.link( ch );

        if (particles == null) {
            particles = createEmitter();
        }
    }

    @Override
    public void update() {
        super.update();

        if (particles != null){
            particles.visible = visible;
        }
    }

    @Override
    public void kill() {
        super.kill();
        if (particles != null){
            particles.killAndErase();
        }
    }

    @Override
    public void onComplete( Animation anim ) {
        if (anim == zap) {
            idle();
        }
        super.onComplete( anim );
    }

    protected Emitter createEmitter() {
        Emitter emitter = emitter();
        emitter.pour( MagicMissile.ForceParticle.FACTORY, 0.04f );
        return emitter;
    }

    @Override
    public int blood() {
        return 0xFFffffff;
    }

    @Override
    public void die() {
        emitter().burst( MagicMissile.ForceParticle.FACTORY, 12 );
        if (particles != null){
            particles.on = false;
        }
        super.die();
    }
}
