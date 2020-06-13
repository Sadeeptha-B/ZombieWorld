package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Action for crafting weapons
 * 
 * @author Sadeeptha Bandara
 *
 */
public class CraftAction extends Action {

	protected CraftableItem item;
	protected WeaponItem weapon;
	
	/**
	 * Constructor
	 * Takes the item to be crafted
	 * @param item
	 */
	public CraftAction(CraftableItem item) {
		this.item = item;
	}
	
	/**
	 * Execute method for action
	 * Crafts item and removes the starting items from the map
	 */
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(item);
		map.locationOf(actor).removeItem(item);
		
		weapon = item.craft();
		if (weapon != null) 
			actor.addItemToInventory(weapon);
	
		return actor + " crafted "+ item + " to " + weapon;
	}
	
	/**
	 * Description of action for the menu
	 */
	public String menuDescription(Actor actor) {
		return actor + " crafts " + item + " to " + item.getCraftedItem();
	}

}
