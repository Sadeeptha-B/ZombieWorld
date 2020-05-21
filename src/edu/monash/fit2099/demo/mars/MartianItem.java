package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
import game.CraftableItem;

public class MartianItem extends Item{

	public MartianItem(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable);
	}
	
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}
	
	public CraftableItem asCraftableItem(){
		return null;
	}

	@Override
	public int getHealPoints() {return 0;
	}
	
	@Override
	public boolean isEdible() {
		return false;
	}
}
