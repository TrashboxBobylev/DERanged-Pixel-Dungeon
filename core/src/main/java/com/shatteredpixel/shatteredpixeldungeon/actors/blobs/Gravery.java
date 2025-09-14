package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Doom;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class Gravery extends Blob implements Hero.Doom{

    @Override
    protected void evolve() {

        int cell;

        for (int i = area.left-1; i <= area.right; i++) {
            for (int j = area.top-1; j <= area.bottom; j++) {
                cell = i + j*Dungeon.level.width();
                if (cur[cell] > 0) {

                    Gravery.corrupt(cell);

                    off[cell] = cur[cell] - 1;
                    volume += off[cell];
                } else {
                    off[cell] = 0;
                }
            }
        }
    }

    public static void corrupt(int cell ){
        Char ch = Actor.findChar( cell );
        if (ch != null) {
            if (ch.properties().contains(Char.Property.UNDEAD)){
                if (ch.isImmune(Corruption.class))
                    Buff.affect(ch, Doom.class);
                else
                    Buff.affect(ch, Corruption.class);
            } else if (!ch.properties().contains(Char.Property.BOSS) && !ch.properties().contains(Char.Property.MINIBOSS)){
                ch.damage((Dungeon.scalingDepth()/5)*2+2, new Gravery());
            }
        }

        Heap heap = Dungeon.level.heaps.get( cell );
        if (heap != null){
            heap.burn();
            heap.explode();
            heap.freeze();
        }
    }

    @Override
    public void use( BlobEmitter emitter ) {
        super.use( emitter );
        emitter.start( ShadowParticle.UP, 0.05f, 0 );
    }

    @Override
    public String tileDesc() {
        return Messages.get(this, "desc");
    }

    @Override
    public void onDeath() {
        Dungeon.fail( getClass() );
        GLog.n( Messages.get(this, "ondeath") );
    }
}
