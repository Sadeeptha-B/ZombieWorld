package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class EatBehaviour implements Behaviour {

	public EatBehaviour() {

	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (actor.getHealthPercantage()<75)
			for(Item item: actor.getInventory())
				if(item.isEdible())
					return new EatAction(item);
		return null;
	}
	
	

}
