package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.MovieClip;
import com.watabou.noosa.TextureFilm;

public class GhostChickenSprite extends MobSprite {

    public GhostChickenSprite() {
        super();

        texture( Assets.Sprites.CHICKEN );

        TextureFilm frames = new TextureFilm( texture, 15, 15 );

        idle = new MovieClip.Animation( 20, true );
        idle.frames( frames, 0, 1 );

        run = new MovieClip.Animation( 32, true );
        run.frames( frames, 0, 1 );

        attack = new MovieClip.Animation( 26, false );
        attack.frames( frames, 2, 3, 2, 1 );

        die = new MovieClip.Animation( 20, false );
        die.frames( frames, 4, 5, 6 );

        play( idle );

        alpha(0.3f);
    }
}
