package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.noosa.MovieClip;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.Callback;

public class BlinkingManSprite extends MobSprite {

    public BlinkingManSprite() {
        super();

        texture( Assets.Sprites.TENGU );

        TextureFilm frames = new TextureFilm( texture, 14, 16 );

        idle = new MovieClip.Animation( 10, true );
        idle.frames( frames, 0, 0, 0, 1 );

        run = new MovieClip.Animation( 30, false );
        run.frames( frames, 2, 3, 4, 5, 0 );

        attack = new MovieClip.Animation( 30, false );
        attack.frames( frames, 6, 7, 7, 0 );

        zap = attack.clone();

        die = new MovieClip.Animation( 12, false );
        die.frames( frames, 8, 9, 10, 10, 10, 10, 10, 10 );

        hardlight(0x8f8f8f);

        play( run.clone() );
    }

    @Override
    public void idle() {
        isMoving = false;
        super.idle();
    }

    @Override
    public void move( int from, int to ) {

        place( to );

        play( run );
        turnTo( from , to );

        isMoving = true;

        if (Dungeon.level.water[to]) {
            GameScene.ripple( to );
        }

    }

    @Override
    public void attack( int cell ) {
        if (!Dungeon.level.adjacent( cell, ch.pos )) {

            ((MissileSprite)parent.recycle( MissileSprite.class )).
                    reset( this, cell, new TenguJavelin(), new Callback() {
                        @Override
                        public void call() {
                            ch.onAttackComplete();
                        }
                    } );

            play( zap );
            turnTo( ch.pos , cell );

        } else {

            super.attack( cell );

        }
    }

    @Override
    public void onComplete( MovieClip.Animation anim ) {
        if (anim == run) {
            synchronized (this){
                isMoving = false;
                idle();

                notifyAll();
            }
        } else {
            super.onComplete( anim );
        }
    }

    public static class TenguJavelin extends Item {
        {
            image = ItemSpriteSheet.SPIRIT_ARROW;
        }
    }
}
