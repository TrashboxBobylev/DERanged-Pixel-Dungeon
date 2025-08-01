package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Light;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.levels.AbyssLevel;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BlinkingManSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public class BlinkingMan extends AbyssalMob {

    private int blinkCooldown = 0;

    {
        spriteClass = BlinkingManSprite.class;

        HP = HT = 80;
        defenseSkill = 60;
        if (Dungeon.branch != AbyssLevel.BRANCH)
            defenseSkill = 20;
        viewDistance = Light.DISTANCE;
        baseSpeed = 0.75f;
        flying = true;

        EXP = 13;

        loot = Generator.Category.SCROLL;
        lootChance = 0.25f;

        properties.add(Property.DEMONIC);
        properties.add(Property.UNDEAD);
    }

    @Override
    public int damageRoll() {
        if (Dungeon.branch != AbyssLevel.BRANCH)
            return Random.NormalIntRange( 1, 7 );
        return Random.NormalIntRange( 5 + abyssLevel()*2, 16 + abyssLevel()*7 );
    }

    @Override
    public boolean canSee(int pos) {
        if (enemy != null)
            return new Ballistica( pos, enemy.pos, Ballistica.PROJECTILE).collisionPos == pos;
        else return new Ballistica( pos, Dungeon.hero.pos, Ballistica.PROJECTILE).collisionPos == pos;
    }

    @Override
    public int attackProc(Char enemy, int damage ) {
        damage = super.attackProc( enemy, damage );

        if (Random.Int( 2 ) == 0) {
            Ballistica trajectory = new Ballistica(pos, enemy.pos, Ballistica.STOP_TARGET);
            //trim it to just be the part that goes past them
            trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
            //knock them back along that ballistica
            WandOfBlastWave.throwChar(enemy, trajectory, Random.Int(1, 2), false, true, getClass());
        }

        return damage;
    }

    @Override
    public boolean canAttack(Char enemy) {
        return !Dungeon.level.adjacent( pos, enemy.pos ) &&
                (super.canAttack(enemy) || new Ballistica( pos, enemy.pos, Ballistica.PROJECTILE).collisionPos == enemy.pos);
    }

    @Override
    protected boolean getCloser( int target ) {
        if (fieldOfView[target] && Dungeon.level.distance( pos, target ) <= 3 && blinkCooldown <= 0) {

            blink( );
            spend( -1 / speed() );
            return true;

        } else {

            blinkCooldown--;
            return super.getFurther( target );

        }
    }

    @Override
    public float attackDelay() {
        return super.attackDelay() * 0.4f;
    }

    private void blink( ) {

        int direction = PathFinder.NEIGHBOURS8[Random.Int(8)];

        Ballistica route = new Ballistica( pos+direction, target, Ballistica.PROJECTILE);
        if (route.dist == 0){
            blink();
            return;
        }
        int cell = route.collisionPos;

        //can't occupy the same cell as another char, so move back one.
        if (Actor.findChar( cell ) != null && cell != this.pos)
            cell = route.path.get(route.dist-1);

        if (Dungeon.level.avoid[ cell ]){
            ArrayList<Integer> candidates = new ArrayList<>();
            for (int n : PathFinder.NEIGHBOURS8) {
                cell = route.collisionPos + n;
                if (Dungeon.level.passable[cell] && Actor.findChar( cell ) == null) {
                    candidates.add( cell );
                }
            }
            if (candidates.size() > 0)
                cell = Random.element(candidates);
            else {
                blinkCooldown = Random.IntRange(1, 6);
                return;
            }
        }

        ScrollOfTeleportation.appear( this, cell );

        blinkCooldown = Random.IntRange(1, 6);
    }

    @Override
    public int attackSkill( Char target ) {
        if (Dungeon.branch != AbyssLevel.BRANCH)
            return 20;
        return 45 + abyssLevel()*3;
    }

    @Override
    public int drRoll() {
        if (Dungeon.branch != AbyssLevel.BRANCH)
            return super.drRoll() + Random.NormalIntRange(0, 4);
        return super.drRoll() + Random.NormalIntRange(0 + abyssLevel()*4, 9 + abyssLevel()*6);
    }

    @Override
    public Item createLoot() {
        Class<?extends Scroll> loot;
        do{
            loot = (Class<? extends Scroll>) Random.oneOf(Generator.Category.SCROLL.classes);
        } while (loot == ScrollOfIdentify.class || loot == ScrollOfUpgrade.class);

        return Reflection.newInstance(loot);
    }

    {
        immunities.add( Charm.class );
    }

    private static final String BLINK_CD = "blink_cd";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(BLINK_CD, blinkCooldown);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        blinkCooldown = bundle.getInt(BLINK_CD);
    }
}
