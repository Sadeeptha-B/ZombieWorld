package edu.monash.fit2099.interfaces;

import game.Limb;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import game.Corpse;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
	public void dropItems(GameMap map);
	public Limb dismember();
	public float getHealthPercantage();
	public Corpse death();
	public Action pickUpItem(GameMap map);
	public boolean determineResult();
}
