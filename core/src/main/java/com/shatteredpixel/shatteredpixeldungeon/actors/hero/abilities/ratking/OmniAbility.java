package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ratking;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.effects.Transmuting;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

public class OmniAbility extends ArmorAbility {

    private ArmorAbility armorAbility = null;

    @Override public void use(ClassArmor armor, Hero hero) {
        if(armorAbility == null) setArmorAbility();
        else {
            armorAbility.use(armor, hero);
            // change armor ability afterwards ( see ClassArmor.useCharge() )
        }

    }
    @Override public void activate(ClassArmor armor, Hero hero, Integer target) {/*do nothing*/}

    // talent choosing
    public ArmorAbility activeAbility() { return armorAbility; }
    public void setArmorAbility() {
        Set<ArmorAbility> pool = new HashSet<>(abilities);
        pool.removeAll(activeAbilities());

        // Rat King's abilities show up less.
        // fixme should I weight things to attempt to 'even' out the results of rng?
        if(Random.Int(2) == 0) {
            //pool.remove(new Wrath());
            if (Random.Int(4) == 0) pool.remove(new Ratmogrify());
        }

        pool.remove(armorAbility);

        new Transmuting(
                armorAbility != null ? new HeroIcon(armorAbility) : new Image(),
                new HeroIcon(armorAbility = Objects.requireNonNull(Random.element(pool)))
        ).show(hero);
        Item.updateQuickslot();
    }

    @Override
    public int targetedPos(Char user, int dst) {
        return armorAbility != null ? armorAbility.targetedPos(user, dst) :
                super.targetedPos(user, dst);
    }

    @Override public boolean useTargeting() {
        return armorAbility != null ? armorAbility.useTargeting() : super.useTargeting();
    }

    @Override public float chargeUse(Hero hero) {
        return armorAbility != null ? armorAbility.chargeUse(hero) : 0;
    }

    @Override
    public String desc() {
        String desc = Messages.get(this,"desc");
        if(armorAbility != null) {
            desc += "\n\n"
                    + Messages.get(this, "ability_desc")
                    + "\n" + armorAbility.desc();
        }
        return desc;
    }

    @Override
    public String shortDesc() {
        String desc = super.shortDesc();
        if(armorAbility != null) {
            desc += "\n\n"
                    + Messages.get(this, "ability_desc")
                    + "\n" + armorAbility.shortDesc();
        }
        return desc;
    }

    @Override public Talent[] talents() {
        if (armorAbility != null){
            ArrayList<Talent> talents = new ArrayList<>(Arrays.asList(armorAbility.talents()));
            return talents.toArray(new Talent[0]);
        }
        return new Talent[0];
    }

    @Override
    public String actionName() {
        return armorAbility != null ? armorAbility.actionName() : super.actionName();
    }

    // note that if any other class ends up using bundling I'll need to store the ability list as well.
    private static final String ABILITY = "armorAbility";
    @Override public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(ABILITY, armorAbility);
    }
    @Override public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        armorAbility = (ArmorAbility) bundle.get(ABILITY);
    }
    // interactions with talent-related stuff.

    /** establishes that these interactions need to be performed. **/
    public static boolean isApplicable() {
        return hero != null && hero.armorAbility instanceof OmniAbility;
    }
    /** omniability checks based on ability usage. **/
    public static void markAbilityUsed(ArmorAbility ability) {
        if(isApplicable()) {
            OmniAbility omniAbility = (OmniAbility) hero.armorAbility;
            if(ability.equals(omniAbility.armorAbility)) omniAbility.setArmorAbility();
        }
    }
    public static LinkedHashMap<Talent, Integer> transferTalents(ArmorAbility armorAbility) {
        LinkedHashMap<Talent, Integer> talents = new LinkedHashMap<>();
        if(hero.talents.size() < 4) return talents;
        Iterator<Integer> iterator = hero.talents.get(3).values().iterator();
        for(Talent talent : armorAbility.talents()) {
            talents.put(talent, iterator.hasNext() ? iterator.next() : 0);
        }

        return talents;
    }
    /** returns the amount of points that would be in that talent if it corresponded, otherwise return null.
     * It is worth noting that this is only checked after a standard check would have failed, and the stored ability does not use this method.
     * If I wanted to exclude a talent from being transferred unless it's currently in the active ability, then here would be the place to do it.
     * **/
    public static Integer findTalent(Talent talent) {
        for(ArmorAbility ability : trackedAbilities()) {
            for(Talent t : ability.talents()) {
                if(t == talent) return transferTalents(ability).get(talent);
            }
        }
        return null;
    }

    /** Abilities that are still functioning and thus need access to their respective talents.
     * Anything in here will have their talents available for lookup by the talent system.
     * These can still be rolled by OmniAbility.
     **/
    public static Set<ArmorAbility> trackedAbilities() {
        HashSet<ArmorAbility> r = new HashSet();
        if(isApplicable()) for(ArmorAbility ability : abilities) {
            if((ability.isActive(hero) || ability.isTracked(hero))
                    && !ability.equals(( (OmniAbility) hero.armorAbility ).armorAbility)) {
                r.add(ability);
            }
        }
        return r;
    }

    /** set of all SELECTABLE additional abilities.
     * Anything in here cannot be rolled by OmniAbility. **/
    public static Set<ArmorAbility> activeAbilities() {
        Set<ArmorAbility> abilities = trackedAbilities();
        for(Iterator<ArmorAbility> i = abilities.iterator(); i.hasNext();) {
            ArmorAbility aa = i.next();
            if( !aa.isActive(hero) ) i.remove();
        }
        return abilities;
    }
    /** Allows you to select previous actions that still can be interacted with when applicable.
     *  Used with ClassArmor.java
     **/
    public static Map<String, ArmorAbility> additionalActions() {
        HashMap<String, ArmorAbility> map = new HashMap<>();
        for(ArmorAbility ability : activeAbilities()) map.put(ability.actionName(), ability);
        return map;
    }
    /** the pool by which abilities are searched.
     *
     * Anything not in this pool won't be rolled by omniability, and does not have to accommodate it.
     * By default everything is in this pool by default, however.
     **/
    private static final Set<ArmorAbility> abilities = new HashSet(); static {
        for(HeroClass cls : HeroClass.values()) {
            abilities.addAll(Arrays.asList(cls.armorAbilities()));
        }
        abilities.remove(new OmniAbility());
        //abilities.add(new Ratmogrify());
    }
}
