package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Phantom;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Callback;

public class PhantomSprite extends MobSprite {

    private Emitter cloud;

    public PhantomSprite() {
        super();

        texture( Assets.Sprites.DM200 );

        TextureFilm frames = new TextureFilm( texture, 21, 18 );

        idle = new Animation( 10, true );
        idle.frames( frames, 14);

        run = new Animation( 110, true );
        run.frames( frames, 14, 14);

        attack = new Animation( 15, false );
        attack.frames( frames, 14, 14, 14 );

        zap = new Animation( 15, false );
        zap.frames( frames, 14, 14, 14);

        die = new Animation( 8, false );
        die.frames( frames, 14, 14, 14);

        play( idle );
    }

    public void zap( int cell ) {

        turnTo( ch.pos , cell );
        play( zap );

        MagicMissile.boltFromChar( parent,
                MagicMissile.INVISI,
                this,
                cell,
                new Callback() {
                    @Override
                    public void call() {
                        ((Phantom)ch).onZapComplete();
                    }
                } );
        Sample.INSTANCE.play( Assets.Sounds.ATK_CROSSBOW );
    }

    @Override
    public void place(int cell) {
        if (parent != null) parent.bringToFront(this);
        super.place(cell);
    }

    @Override
    public void die() {
        emitter().burst( Speck.factory( Speck.WOOL ), 8 );
        super.die();
        if (cloud != null) {
            cloud.on = false;
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
    public void onComplete( Animation anim ) {
        if (anim == zap) {
            idle();
        }
        super.onComplete( anim );
    }

    @Override
    public void link( Char ch ) {
        super.link( ch );

        if (cloud == null) {
            cloud = emitter();
            cloud.pour( Speck.factory( Speck.DUST ), 1.25f );
        }
    }

}
