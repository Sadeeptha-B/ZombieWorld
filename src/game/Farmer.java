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
	
	public Behaviour[] getBehaviours() {
		return this.behaviours;
	}
	
	public void harvest(GameMap map) {
		map.locationOf(this).addItem(new Food());
	}

}
