package edu.monash.fit2099.demo.mars;

import java.util.*;

import game.Behaviour;
import game.Corpse;
import edu.monash.fit2099.engine.*;
import game.Limb;
import game.UtilityGameMap;


public class Bug extends Actor {

	private Random rand = new Random();
	public List<Behaviour> actionFactories = new ArrayList<Behaviour>();

	public Bug() {
		super("Feature", 'x', 1);
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return actions.get(rand.nextInt(actions.size()));
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.add(new KickAction(this));
		return list;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(1, "stings");
	}
	
	public Limb dismember() {
		return null;
	}

	@Override
	public float getHealthPercantage() {
		return 0;
	}
	
	public Corpse death() {
		return null;
	}
	
	public Action pickUpItem(GameMap map) {
		return null;
	}

	@Override
	public void dropItems(GameMap map) {}
	
	
	@Override
	public boolean determineResult() {
		return false;
	}

	@Override
	public boolean isPlayer() {
		return false;
	}

	@Override
	public void tick(UtilityGameMap map) {}

	@Override
	public void death(GameMap map) {
	}
	
}
