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

	/**
	 * Executes the harvest action
	 * if the player harvests, the food is added to the inventory
	 * if a human harvests the food is dropped to the ground
	 * 
	 * @param actor the actor of the action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {	
		if (!(actor instanceof Human))
			throw new IllegalArgumentException("Only farmers and players can harvest");
		
		Human harvester = (Human) actor;
		map.locationOf(harvester).setGround(new Dirt());
		harvester.harvest(map);
		return menuDescription(actor);
	}
	
	
	/**
	 * 
	 * Returns the description of the action to be printed in the menu
	 * 
	 * @param actor the actor of the action
	 * 
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvests the crop";
	}

}
