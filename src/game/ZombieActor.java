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
	
	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team, ZombieCapability mobility) {
		super(name, displayChar, hitPoints);
		addCapability(team);
		addCapability(mobility);
	}
	
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour: this.getBehaviours()) {
			Action action = behaviour.getAction(this, map);
			if(action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}
	
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new AttackAction(this));
		return list;
	}
	
	
	public Corpse death(boolean diedAs) {
		return new Corpse(this.toString(), diedAs);
	}
	
	
	public Limb dismember() {
		return null;
	};
	
	
	public abstract Behaviour[] getBehaviours();

}
