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
	
	public CraftableItem asCraftableItem(){
		return this instanceof CraftableItem ? (CraftableItem) this : null;
	}

	@Override
	public int getHealPoints() {
		return 0;
	}
	
	@Override
	public boolean isEdible() {
		return false;
	}

	public List<Action> getAllowableActions() {
		List<Action> list = super.getAllowableActions();
		List<Action> actions = new ArrayList<Action>();
		
		for (Action action: list) {
			actions.add(action);
		}
		return actions;
	}
	
}
