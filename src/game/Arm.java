package game;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Arm class for fallen arms
 * 
 * @author Sadeeptha Bandara
 *
 */
public class Arm extends Limb{

	
	public Arm() {
		super("arm", '/', "club");
	}
	
	/**
	 * For crafting clubs
	 */
	public WeaponItem craft() {
		WeaponItem weapon = new Club();
		return weapon;
	}
			
}
