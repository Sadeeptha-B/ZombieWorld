package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

public class Vehicle extends Item {

	public Vehicle() {
		super("vehicle", 'D', false);
	}
	
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}
	
	@Override
	public CraftableItem asCraftableItem() {
		return null;
	}

	@Override
	public boolean isEdible() {
		return false;
	}

}
