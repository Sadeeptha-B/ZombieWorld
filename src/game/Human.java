package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.DoNothingAction;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {

	private Behaviour behaviours[] = {
			new PickUpBehaviour(),
			new EatBehaviour(),
			new WanderBehaviour(ZombieCapability.MOBILE)
			
	};

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */	
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE, ZombieCapability.MOBILE);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE, ZombieCapability.MOBILE);
	}

	
	public Action pickUpItem(GameMap map) {
		for (Item item: map.locationOf(this).getItems())
			if (item.isEdible())
				return(item.getPickUpAction());
		return null;
	}
	

	public Behaviour[] getBehaviours() {
		return this.behaviours;
	}
	
	
	public Corpse death() {
		return super.death(true);
	}
	
	public void harvest(GameMap map) {};
}
