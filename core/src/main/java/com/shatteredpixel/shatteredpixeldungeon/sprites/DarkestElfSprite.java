package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DarkestElf;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;

public class DarkestElfSprite extends MobSprite {

    private Animation charging;

    public DarkestElfSprite(){
        super();

        texture( Assets.Sprites.NECRO );
        TextureFilm film = new TextureFilm( texture, 16, 16 );

        idle = new Animation( 4, true );
        idle.frames( film, 0, 0, 0, 1, 0, 0, 0, 0, 1 );

        run = new Animation( 16, true );
        run.frames( film, 0, 0, 0, 2, 3, 4 );

        zap = new Animation( 20, false );
        zap.frames( film, 5, 6, 7, 8 );

        charging = new Animation( 10, true );
        charging.frames( film, 7, 8 );

        die = new Animation( 10, false );
        die.frames( film, 9, 10, 11, 12 );

        hardlight(0x8f8f8f);

        attack = zap.clone();

        idle();
    }

    public void charge(){
        play(charging);
    }

    @Override
    public void zap(int cell) {
        super.zap(cell);
        if (visible && ch instanceof DarkestElf && ((DarkestElf) ch).summoning){
            Sample.INSTANCE.play( Assets.Sounds.CHARGEUP, 3f, 0.8f );
        }
    }

    @Override
    public void onComplete(Animation anim) {
        super.onComplete(anim);
        if (anim == zap){
            if (ch instanceof DarkestElf){
                if (((DarkestElf) ch).summoning){
                    charge();
                } else {
                    ((DarkestElf)ch).onZapComplete();
                    idle();
                }
            } else {
                idle();
            }
        }
    }
}
