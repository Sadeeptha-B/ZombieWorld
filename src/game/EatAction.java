package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class EatAction extends Action {
	
	private Item food;
	
	public EatAction(Item item) {
		food = item;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.heal(food.returnHealPoints());
		actor.removeItemFromInventory(food);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		
		return actor + " eats " + food + " to gain " + food.returnHealPoints() + " points";
	}

}
