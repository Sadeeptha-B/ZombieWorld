package game;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * 
 * 
 * @author Sadeeptha Bandara
 *
 */
public class Arm extends Limb{

	
	public Arm() {
		super("arm", '/', "club");
	}
	
	public WeaponItem craft() {
		WeaponItem weapon = new Club();
		return weapon;
	}
		
	
	
}
