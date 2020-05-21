package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class Farmer extends Human {
	private Behaviour behaviours[] = {
			new PickUpBehaviour(),
			new SowCropBehaviour()
			
	};

	public Farmer(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(ZombieCapability.MOBILE);
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map,Display display) {
		for (Behaviour behaviour: behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) 
				return action;
			
		}
		return new WanderBehaviour(ZombieCapability.MOBILE).getAction(this, map);
	}
}
