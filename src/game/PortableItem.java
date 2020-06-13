package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;


/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
	
	/**
	 * Returns whether portable item is craftable.
	 */
	public CraftableItem asCraftableItem(){
		return this instanceof CraftableItem ? (CraftableItem) this : null;
	}
	
	
	@Override
	/**
	 * Whether item is edible
	 */
	public boolean isEdible() {
		return false;
	}	
	
	
	public List<Action> playerAllowableActions(Player player){
		List<Action> actions = new ArrayList<Action>();
		return actions;
	}

	@Override
	public Ammunition asAmmo() {
		return this instanceof Ammunition ? (Ammunition) this : null;
	}
}
