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
	 * if picking up is not possible or if the actor has an item in their inventory, returns null
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no PickUpAction is possible
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		if (!actor.getInventory().isEmpty()) {
			return null;
		}
		if(actor instanceof Zombie) {
			for(Item item : map.locationOf(actor).getItems()) {
				if (item instanceof WeaponItem)
					actions.add(item.getPickUpAction());
			}
		}
		
		if (actor instanceof Human) {
			for(Item item : map.locationOf(actor).getItems()){
				if(item.isEdible()) { 
					Action action = item.getPickUpAction();
					if(action != null)
						actions.add(action);
				}
			}
		}
		if (! actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
			}
		return null;
	
		}

	}
