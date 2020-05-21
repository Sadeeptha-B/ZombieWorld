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

	public CraftableItem asCraftableItem(){
		return null;
	}

	@Override
	public int returnHealPoints() {
		return 0;
	}

	@Override
	public boolean isEdible() {
		return false;
	}
}
