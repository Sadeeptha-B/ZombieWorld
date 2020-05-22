package game;

import edu.monash.fit2099.engine.WeaponItem;


/**
 * Limb class is the parent class of arm and leg
 * 
 * @author Sadeeptha Bandara
 *
 */
public abstract class Limb extends CraftableItem{

	public Limb(String limbType, char displayChar, String craftedTo) {
		super(limbType,displayChar, craftedTo);
	}

	public abstract WeaponItem craft();
	
	
}
