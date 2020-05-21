package game;

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
	 * the growth of the crops happen with each tick 
	 * will grow after 20 ticks
	 * @param map the location of the item
	 * 
	 */
	public void tick(Location map) {
		count++;
		if (count > 20) {
			displayChar = '$';
			harvestable = true;
		}
	}
	public CraftableItem asCraftableItem() {
		return null;
	}
	
	public boolean isHarvestable() {
		return harvestable;
		
	}
	
}
