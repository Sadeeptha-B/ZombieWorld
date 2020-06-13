package edu.monash.fit2099.interfaces;


import game.Ammunition;
import game.CraftableItem;
import game.Player;

import java.util.List;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
	public List<Action> playerAllowableActions(Player player);
	public CraftableItem asCraftableItem();
	public boolean isEdible();
	public Ammunition asAmmo();
	
}
