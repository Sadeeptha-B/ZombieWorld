package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
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

	
	/**
	 * Allowable actions for portable items.
	 */
	public List<Action> getAllowableActions() {
		List<Action> list = super.getAllowableActions();
		List<Action> actions = new ArrayList<Action>();
		
		for (Action action: list) {
			actions.add(action);
		}
		return actions;
	}
	
	public List<Action> allowableActions(Actor actor){
		return getAllowableActions();
	}
}
