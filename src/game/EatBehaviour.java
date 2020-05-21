package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;


/**
 * implements the eat behaviour
 * 
 * @author Kaveesha Nissanka
 *
 */
public class EatBehaviour implements Behaviour {

	public EatBehaviour() {

	}
	
	/**
	 * returns an EatAction if the health of the actor is less than 75%
	 * 
	 * @param actor the actor
	 * @param map the map that the actor is in
	 * @return Action an EatAction
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (actor.getHealthPercantage()<75)
			for(Item item: actor.getInventory())
				if(item.isEdible())
					return new EatAction(item);
		return null;
	}
	
	

}
