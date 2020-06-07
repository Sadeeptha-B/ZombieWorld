package game;
import edu.monash.fit2099.engine.Weapon;
import java.util.ArrayList;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Class to enable choosing of weapons. Called upon each turn if 
 * 
 * @author Sadeeptha Bandara
 *
 */
public class ChooseWeaponAction extends Action {

	protected WeaponItem chosenWeapon;
	
	
	public ChooseWeaponAction(WeaponItem weapon) {
		chosenWeapon = weapon;
	}
	
	/**
	 * Brings the chosen weapon to the beginning of the inventory
	 */
	public String execute(Actor actor, GameMap map) {
		if (!(actor instanceof Player))
			throw new IllegalArgumentException("Only player can choose weapons");
		
		ArrayList<Item> inventory_collection = new ArrayList<Item>();
		
		for (Item item : actor.getInventory()) {
			if (item != chosenWeapon) {
				actor.removeItemFromInventory(item);
				inventory_collection.add(item);
			}
		}
		
		for (Item item : inventory_collection) {
			actor.addItemToInventory(item);
		}
		
		return actor + " switched weapon to " + chosenWeapon;
	}
	
	/**
	 * Description for menu
	 */
	public String menuDescription(Actor actor) {
		return "Choose weapon - " + chosenWeapon;
	}
	
}
