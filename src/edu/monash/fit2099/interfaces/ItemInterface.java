package edu.monash.fit2099.interfaces;

import game.CraftableItem;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
	
	public CraftableItem asCraftableItem();
	
	public int returnHealPoints();
	
	public boolean isEdible();
}
