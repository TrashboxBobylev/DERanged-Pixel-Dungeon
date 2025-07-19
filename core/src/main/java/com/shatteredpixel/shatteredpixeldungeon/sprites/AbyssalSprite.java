package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.particles.Emitter;

public class AbyssalSprite extends MobSprite {

    private Emitter cloud;

    public AbyssalSprite() {
        super();

        texture( Assets.Sprites.WRAITH );

        TextureFilm frames = new TextureFilm( texture, 14, 15 );

        idle = new Animation( 10, true );
        idle.frames( frames, 0, 1 );

        run = new Animation( 20, true );
        run.frames( frames, 0, 1 );

        attack = new Animation( 20, false );
        attack.frames( frames, 0, 2, 3 );

        die = new Animation( 16, false );
        die.frames( frames, 0, 4, 5, 6, 7 );

        play( idle );
    }

    @Override
    public int blood() {
        return 0x88000000;
    }

    @Override
    public void link( Char ch ) {
        super.link( ch );

        if (cloud == null) {
            cloud = emitter();
            cloud.pour( Speck.factory( Speck.SMOKE ), 0.1f );
        }
    }

    @Override
    public void update() {

        super.update();

        if (cloud != null) {
            cloud.visible = visible;
        }
    }

    @Override
    public void kill() {
        super.kill();

        if (cloud != null) {
            cloud.on = false;
        }
    }
}
