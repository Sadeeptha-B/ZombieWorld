package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A primitive weapon.
 * 
 * @author ram
 *
 */
public class Plank extends WeaponItem {

	public Plank() {
		super("plank", ')', 20, "whacks");
		// TODO Auto-generated constructor stub
	}

	public CraftableItem asCraftableItem(){
		return null;
	}

	@Override
	public boolean isEdible() {
		return false;
	}
}
