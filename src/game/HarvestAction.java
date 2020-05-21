package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * lets an actor harvest the crop as a food item
 * 
 * @author Kaveesha Nissanka
 *
 */
public class HarvestAction extends Action {

	public HarvestAction() {
		
	}
	/**
	 * executes the harvest action
	 * if the player harvests, the food is added to the inventory
	 * if a human harvests the food is droppped to the ground
	 * 
	 * @param actor the actor of the action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor instanceof Player) {
			map.locationOf(actor).setGround(new Dirt());
			actor.addItemToInventory(new Food());
			return menuDescription(actor);
		}
		if(actor instanceof Human) {
			map.locationOf(actor).setGround(new Dirt());
			map.locationOf(actor).addItem(new Food());
			return menuDescription(actor);
		}
		return null;
	}
	/**
	 * 
	 * returns the description of the action to be printed in the menu
	 * 
	 * @param actor the actor of the action
	 * 
	 */
	@Override
	public String menuDescription(Actor actor) {
		
		return actor + " harvests the crop";
	}

}
