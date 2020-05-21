package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
<<<<<<< HEAD
	private Behaviour behaviours[] = {
			new PickUpBehaviour()
	};
=======
	private Behaviour behaviour = new WanderBehaviour(ZombieCapability.MOBILE);
>>>>>>> branch 'master' of https://git.infotech.monash.edu/fit2099-s1-2020/JavaJuveniles/project.git

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE);
		this.addCapability(ZombieCapability.MOBILE);
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
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// FIXME humans are pretty dumb, maybe they should at least run away from zombies?
		for (Behaviour behaviour: behaviours) {
			Action action = behaviour.getAction(this, map);
			if(action != null) {
				System.out.println("1");
				return action;
			}
		}
		System.out.println("2");
		return new WanderBehaviour(ZombieCapability.CAPABLE).getAction(this, map);

	}
	
	
	public Limb dismember() {
		return null;
	}

}
