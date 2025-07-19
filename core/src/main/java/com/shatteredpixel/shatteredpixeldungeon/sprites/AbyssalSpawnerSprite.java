package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class AbyssalSpawnerSprite extends SpawnerSprite {

    public AbyssalSpawnerSprite() {
        super();

        texture( Assets.Sprites.ABYSS_SPAWNER );

        perspectiveRaise = 8 / 16f;
        shadowOffset = 1.25f;
        shadowHeight = 0.4f;
        shadowWidth = 1f;

        TextureFilm frames = new TextureFilm( texture, 16, 16 );

        idle = new Animation( 8, true );
        idle.frames( frames, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 );

        run = idle.clone();

        attack = idle.clone();

        die = idle.clone();

        play( idle );
    }
}
