package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
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

	/**
	 * Limb is craftable. All subclasses should implement craft.
	 */
	public abstract WeaponItem craft();
	
}
