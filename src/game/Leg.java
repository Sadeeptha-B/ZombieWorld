package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * 
 * @author Sadeeptha Bandara
 *
 */
public class Leg extends Limb {

	public Leg() {
		super("Leg", 'J', "mace");
	}

	public WeaponItem craft() {
		WeaponItem weapon = new Mace();
		return weapon;
	}
}
