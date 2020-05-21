package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;


public class CraftAction extends Action {

	protected CraftableItem item;
	protected WeaponItem weapon;
	
	
	public CraftAction(CraftableItem item) {
		this.item = item;
	}
	
	
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(item);
		weapon = item.craft();
		if (weapon != null) 
			actor.addItemToInventory(weapon);
	
		return actor + " crafted "+ item + " to " + weapon;
	}
	
	public String menuDescription(Actor actor) {
		return actor + " crafts " + item + " to " + item.getCraftedItem();
	}
	
}
