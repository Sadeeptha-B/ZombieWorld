package edu.monash.fit2099.demo.mars;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;
import game.CraftableItem;

public class Stick extends WeaponItem {

	public Stick() {
		super("stick", '/', 10, "pokes");
	}

	public CraftableItem asCraftableItem(){
		return null;
	}
	
	@Override
	public boolean isEdible() {
		return false;
	}

	@Override
	public List<Action> allowableActions(Actor actor) {
		return null;
	}
}
