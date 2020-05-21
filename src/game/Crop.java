package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * Class for the crops planted by farmers
 * 
 * @author Kaveesha Nissanka
 *
 */
public class Crop extends Ground {
	
	private int count = 0;
	private boolean harvestable = false;
	
	public Crop() {
		super('_');
	}
	
	
	/**
	 * 
	 * The growth of the crops happen with each tick 
	 * will grow after 20 ticks
	 * @param map the location of the item
	 * 
	 */
	public void tick(Location location) {
		count++;
		
		if (location.getActor() instanceof Farmer)
			count += 2;
		
		if (count > 20) {
			displayChar = '$';
			harvestable = true;
		}
		
	}
	
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = super.allowableActions(actor, location, direction);
		actions.add(new HarvestAction());
		return actions;
	}
	
	
	public boolean isHarvestable() {
		return harvestable;	
	}
	
}
