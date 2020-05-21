package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A crafted weapon.
 * 
 * @author Kaveesha Nissanka
 *
 */
public class Club extends WeaponItem {
	
	public Club() {
		super("club", '|', 25, "whams");
	}
	
	public CraftableItem asCraftableItem(){
		return null;
	}

	@Override
	public int getHealPoints() {
		return 0;
	}
	
	@Override
	public boolean isEdible() {
		return false;
	}
}
