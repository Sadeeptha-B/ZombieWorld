package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * 
 * Implements harvest behaviours for farmers
 * 
 * @author Kaveesha Nissanka
 *
 */
public class HarvestBehaviour implements Behaviour {

	public HarvestBehaviour() {
	}
	
	/**
	 * Returns a harvest action if the crop is harvestable
	 * 
	 * @param actor the actor
	 * @param map the map that the actor is in
	 * @return Action a HarvestAction
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (!(actor instanceof Human))
			throw new IllegalArgumentException("Only farmers and players can harvest");
		
		if (map.locationOf(actor).getGround().isHarvestable())
			return new HarvestAction();
		return null;
	}

}
