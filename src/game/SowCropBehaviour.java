package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * behaviour for farmers to plant crops
 * 
 * @author Kaveesha Nissanka
 *
 */
public class SowCropBehaviour implements Behaviour {
	
	protected Random rand = new Random();
	
	/**
	 * 
	 * Returns a SowCropAction with a 33% chance
	 * 
	 * @param actor the actors that will grow the crops
	 * 
	 */
	public Action getAction(Actor actor, GameMap map) {
		if (!(actor instanceof Farmer))
			throw new IllegalArgumentException("Only farmers are allowed to sow crops");
		
		if (rand.nextInt(3) == 0 && map.locationOf(actor).getGround() instanceof Dirt) {
			return new SowCropAction(new Crop());
		}
		return null;
	}
}
