package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Leg classes for dimembered legs
 * 
 * @author Sadeeptha Bandara
 *
 */
public class Leg extends Limb {

	public Leg() {
		super("Leg", 'j', "mace");
	}
	
	
	/**
	 * For crafting maces
	 */
	public WeaponItem craft() {
		WeaponItem weapon = new Mace();
		return weapon;
	}
}
