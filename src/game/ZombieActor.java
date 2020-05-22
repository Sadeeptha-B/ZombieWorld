package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Base class for Actors in the Zombie World
 * @author ram
 *
 */
public abstract class ZombieActor extends Actor {
	
	/**
	 * Constructor for Zombie Actor, sets it's ZombieCapability, whether it's attackable or not
	 * @param name	: Zombie Actor name
	 * @param displayChar : Display Character
	 * @param hitPoints	  : Health Points
	 * @param team		  : Whether actor is alive or not, to discern who attacks
	 * @param mobility	  : Whether actor can move
	 */
	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team, ZombieCapability mobility) {
		super(name, displayChar, hitPoints);
		addCapability(team);
		addCapability(mobility);
	}
	
	
	@Override
	/**
	 * Called upon each turn for each Zombie actor.
	 * Loops through the behaviours for the actors and returns an action if non-null. Else
	 * returns DoNothingAction()
	 * 
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour: this.getBehaviours()) {
			Action action = behaviour.getAction(this, map);
			if(action != null) 
				return action;
		}
		return new DoNothingAction();
	}
	
	
	@Override
	/**
	 * Allowable actions for actors of type : ZombieActor
	 * ZombieActor is the base class for the actor types in the game
	 */
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new AttackAction(this));
		return list;
	}
	
	
	/**
	 * Creates corpse for ZombieActor if killed and returns it
	 * Method is called whenever an actor dies
	 * @param diedAs : Boolean that holds whether or not dead actor is a human
	 * @return	: Created Corpse class
	 */
	public Corpse death(boolean diedAsHuman) {
		return new Corpse(this.toString(), diedAsHuman);
	}
	
	/**
	 * Whether actors of type ZombieActor can be dismembered.
	 */
	public Limb dismember() {
		return null;
	};
	
	
	@Override
	/**
	 *  Get the health percentage of actor 
	 */
	public float getHealthPercantage() {
		return this.hitPoints/this.maxHitPoints;
	}
	
	
	/**
	 * Abstract method to store behaviours, implemented by subclasses.
	 */
	public abstract Behaviour[] getBehaviours();

	

}
