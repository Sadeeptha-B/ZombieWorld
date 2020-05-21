package game;

import java.util.ArrayList;
import java.util.HashSet;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}
      
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null) 
			return lastAction.getNextAction();
	
		addSpecificActions(actions, map);
		Action action =  menu.showMenu(this, actions, display);

		return action;
	}
	
	/**
	 * Private method to add specific actions, including crafting and choosing weapons.
	 * 
	 * @param actions : Menu actions for player
	 * @return  The actions class with new added actions
	 */
	private Actions addSpecificActions(Actions actions, GameMap map) {
		HashSet<WeaponItem> weapons = new HashSet<WeaponItem>();
		if (map.locationOf(this).getGround() instanceof Crop)
			actions.add(new HarvestAction());
		for (Item item : this.getInventory()) {
			if (item.isEdible()) {
				actions.add(new EatAction(item));
			}
			if (item.asCraftableItem() != null) {
				actions.add(new CraftAction(item.asCraftableItem()));
			}
			
			if (item.asWeapon() != null ) {
				weapons.add((WeaponItem) item.asWeapon());
			}
		}
		
		if (weapons.size() > 1)
			for (WeaponItem weapon: weapons) 
				actions.add(new ChooseWeaponAction(weapon));

		return actions;
	}
	
}
