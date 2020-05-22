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
			new WanderBehaviour(ZombieCapability.MOBILE),
			new EatBehaviour()
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

	
	/**
	 * Enables Humans to pick up items
	 * Humans can only pick up items of type: food
	 */
	public Action pickUpItem(GameMap map) {
		for (Item item: map.locationOf(this).getItems())
			if (item.isEdible())
				return(item.getPickUpAction());
		return null;
	}
	

	/**
	 * Returns the behaviours of a Human
	 */
	public Behaviour[] getBehaviours() {
		Behaviour [] behaviours = new Behaviour[this.behaviours.length];
		for (int i = 0; i < this.behaviours.length; i++)
			behaviours[i] = this.behaviours[i];
		return behaviours;
	}
	

	/**
	 * Creates corpse if a Human dies.
	 */
	public Corpse death() {
		return super.death(true);
	}
	
	/**
	 * Harvesting for humans. Only human types, players and farmers harvest
	 * Hence, does not implement. 
	 */
	public void harvest(GameMap map) {};
}
