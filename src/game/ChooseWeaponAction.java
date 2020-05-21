package game;

import java.util.ArrayList;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;


public class ChooseWeaponAction extends Action {

	protected ArrayList<Item> weapons = new ArrayList<Item>();
	protected WeaponItem chosenWeapon;
	
	public ChooseWeaponAction(WeaponItem weapon) {
		chosenWeapon = weapon;
	}
	
	
	public String execute(Actor actor, GameMap map) {
		if (actor.getWeapon() == chosenWeapon)
			return chosenWeapon + " is in use.";
		
		
		ArrayList<Item> inventory = new ArrayList<Item>();
		
		for (Item item : actor.getInventory()) {
			if (item.toString() != chosenWeapon.toString()) {
				actor.removeItemFromInventory(item);
				inventory.add(item);
			}
		}
		
		for (Item item : inventory) {
			actor.addItemToInventory(item);
		}
		
		return actor + " switched weapon to " + chosenWeapon;
	}
	
	public String menuDescription(Actor actor) {
		return "Choose weapon - " + chosenWeapon;
	}
	
}
