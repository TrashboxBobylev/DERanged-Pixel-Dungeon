package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FrostBurn;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.FrostfireParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;

public class FrostFire extends Blob {

    @Override
    protected void evolve() {

        boolean[] flamable = Dungeon.level.flamable;
        int cell;
        int fire = 0;

        Fire firetile = (Fire) Dungeon.level.blobs.get( Fire.class );

        boolean observe = false;

        for (int i = area.left-1; i <= area.right; i++) {
            for (int j = area.top-1; j <= area.bottom; j++) {
                cell = i + j*Dungeon.level.width();
                if (cur[cell] > 0) {

                    if (firetile != null && firetile.volume > 0 && firetile.cur[cell] > 0){
                        firetile.clear(cell);
                        off[cell] = cur[cell] = 0;
                        continue;
                    }

                    burn( cell );

                    fire = cur[cell] - 1;
                    if (fire <= 0 && flamable[cell]) {

                        Dungeon.level.destroy( cell );

                        observe = true;
                        GameScene.updateMap( cell );

                    }

                } else if (firetile == null || firetile.volume <= 0 || firetile.cur[cell] <= 0) {

                    if (flamable[cell]
                            && (cur[cell-1] > 0
                            || cur[cell+1] > 0
                            || cur[cell-Dungeon.level.width()] > 0
                            || cur[cell+Dungeon.level.width()] > 0)) {
                        fire = 4;
                        burn( cell );
                        area.union(i, j);
                    } else {
                        fire = 0;
                    }

                } else {
                    fire = 0;
                }

                volume += (off[cell] = fire);
            }
        }

        if (observe) {
            Dungeon.observe();
        }
    }

    public static void burn( int pos ) {
        Char ch = Actor.findChar( pos );
        if (ch != null && !ch.isImmune(FrostFire.class)) {
            Buff.affect( ch, FrostBurn.class ).reignite( ch );
        }

        Heap heap = Dungeon.level.heaps.get( pos );
        if (heap != null) {
            heap.burn();
        }

        Plant plant = Dungeon.level.plants.get( pos );
        if (plant != null){
            plant.wither();
        }
    }

    @Override
    public void use( BlobEmitter emitter ) {
        super.use( emitter );
        emitter.pour( FrostfireParticle.FACTORY, 0.03f );
    }

    @Override
    public String tileDesc() {
        return Messages.get(this, "desc");
    }
}
