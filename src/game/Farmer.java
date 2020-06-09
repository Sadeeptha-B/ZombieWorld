package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
/**
 * The farmer class
 *  
 * @author Kaveesha Nissanka
 *
 */
public class Farmer extends Human {
	// Get behaviours from inheritance
	private Behaviour behaviours[] = {
			new PickUpBehaviour(),
			new EatBehaviour(),
			new HarvestBehaviour(),
			new SowCropBehaviour(),
			new WanderBehaviour(ZombieCapability.MOBILE)
	};

	public Farmer(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}
	
	/**
	 * Returns the behaviours of a farmer.
	 */
	public Behaviour[] getBehaviours() {
		Behaviour [] behaviours = new Behaviour[this.behaviours.length];
		for (int i = 0; i < this.behaviours.length; i++)
			behaviours[i] = this.behaviours[i];
		return behaviours;
	}
	
	/**
	 * Harvesting for farmer. Farmers drops ripened crops at location
	 */
	public void harvest(GameMap map) {
		map.locationOf(this).addItem(new Food());
	}

}
