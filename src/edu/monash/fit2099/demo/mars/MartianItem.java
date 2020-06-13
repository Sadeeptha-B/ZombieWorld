package edu.monash.fit2099.demo.mars;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import game.Ammunition;
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
	public boolean isEdible() {
		return false;
	}

	@Override
	public Ammunition asAmmo() {
		return null;
	}

	@Override
	public List<Action> playerAllowableActions(game.Player player) {
		return null;
	}
}
