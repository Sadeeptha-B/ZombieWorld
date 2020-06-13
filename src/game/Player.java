package game;

import java.util.ArrayList;
import java.util.List;

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
	/**
	 * Called upon each turn for player
	 * 
	 * Displays the menu of the actions available.
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (lastAction.getNextAction() != null) 
			return lastAction.getNextAction();
		
		display.println("Player Health : " + this.getHealthPercantage());
	
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
		ArrayList<WeaponItem> weapons = new ArrayList<WeaponItem>();
		
		
		if (map.locationOf(this).getGround().isHarvestable())
			actions.add(new HarvestAction());
			
		
		for (Item item : this.getInventory()) {
			List<Action> itemAllowableActions = item.playerAllowableActions(this);
			if (itemAllowableActions != null)
				actions.add(itemAllowableActions);
			
			if (item.asWeapon() != null) 
				weapons.add((WeaponItem) item);
		}		
		actions.add(new QuitAction());
		return actions;
	}
	

	/**
	 * Method for when player harvests crops.
	 */
	public void harvest(GameMap map) {
		this.addItemToInventory(new Food());
	}
	
	@Override
	public boolean isPlayer() {
		return true;
	}

}
