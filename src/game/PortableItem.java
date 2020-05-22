package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;


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
	 * Returns the amount of heal points of an edible item
	 * Default value is 0, which will be overriden by edible items.
	 */
	public int getHealPoints() {
		return 0;
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
	
}
