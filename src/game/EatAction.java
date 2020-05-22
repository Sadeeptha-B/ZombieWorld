package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**
 * lets actors eat to regain health
 * 
 * @author Kaveesha Nissanka
 *
 */
public class EatAction extends Action {
	
	private Item food;
	
	public EatAction(Item item) {
		this.food = item;
	}
	
	/**
	 * heals the actor and returns a string describing the action
	 * 
	 * @param actor the actor that will eat food and heal
	 * @param map the map of the actor
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.heal(food.getHealPoints());
		actor.removeItemFromInventory(food);
		map.locationOf(actor).removeItem(food);
		return menuDescription(actor);
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats " + food + " to gain " + food.getHealPoints() + " points";
	}

}
