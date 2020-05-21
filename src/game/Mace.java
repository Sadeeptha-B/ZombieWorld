package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A crafted weapon.
 * 
 * @author Kaveesha Nissanka
 *
 */
public class Mace extends WeaponItem {
	public Mace() {
		super("mace", '!', 35, "ker-plunk");
	}

	public CraftableItem asCraftableItem(){
		return null;
	}
}
