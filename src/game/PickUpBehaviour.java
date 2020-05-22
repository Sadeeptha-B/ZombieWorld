package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Allows an actor to pickup an item
 * 
 * @author Kaveesha Nissanka
 *
 */

public class PickUpBehaviour implements Behaviour{
	
	private Random random = new Random();
	
	
	/**
	 * Returns a PickUpItemAction to pick up an Item 
	 * Never meant to be called by player, therefore allows Zombie or
	 * human to only have one item in inventory.
	 * 
	 * If picking up is not possible or if the actor has an item in their inventory, returns 
	 * null.
	 * If multiple objects are available for picking up, selects one in random.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no PickUpAction is possible
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (actor instanceof Player)
			throw new IllegalArgumentException("Actor cannot be player.");
		
		if (actor.hasCapability(ZombieCapability.ARMLESS))
			return null;
		
		
		ArrayList<Action> actions = new ArrayList<Action>();
		if (!actor.getInventory().isEmpty()) {
			return null;
		}
		actions.add(actor.pickUpItem(map));
		
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		return null;
	}

}
