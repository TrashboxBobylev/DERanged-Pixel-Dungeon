package com.shatteredpixel.shatteredpixeldungeon.effects.particles;

import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.Arrays;

public class ExoParticle extends PixelParticle {

    public static final Emitter.Factory FACTORY = new Emitter.Factory() {
        @Override
        public void emit( Emitter emitter, int index, float x, float y ) {
            ((ExoParticle)emitter.recycle( ExoParticle.class )).reset( x, y );
        }
        @Override
        public boolean lightMode() {
            return true;
        }
    };

    public ExoParticle() {
        super();

        lifespan = 1f;
        color( Random.element(Arrays.asList(
                0xc73e3e, 0xf27049, 0xf27049, 0x37916a,
                0x392e73, 0x69f0dc, 0xa6f069, 0xe096a5,
                0x4965ba, 0x392e73, 0x872b56, 0x193e42,
                0x446140, 0xfffff0, 0xbac3d6)) );

        speed.polar( Random.Float( PointF.PI2*4 ), Random.Float( 34, 64 ) );
    }

    public void reset( float x, float y ) {
        revive();

        left = lifespan;

        size = 10;

        this.x = x - speed.x * lifespan;
        this.y = y - speed.y * lifespan;
        angularSpeed = Random.Float( 180 );
    }

    @Override
    public void update() {
        super.update();

        float p = left / lifespan;
        am = p < 0.5f ? p * p * 4 : (1 - p) * 2;
        size( Random.Float( 6 * (left / lifespan) ) );
    }
}
