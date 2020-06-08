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
		super("mace", '!', 35, "ker-plunks");
	}

	/**
	 * Whether item is craftable item. Implements the method in ItemInterface.
	 * Item is null, if not craftable
	 */
	public CraftableItem asCraftableItem(){
		return null;
	}
	
	@Override
	/**
	 * Returns whether edible
	 */
	public boolean isEdible() {
		return false;
	}

}
