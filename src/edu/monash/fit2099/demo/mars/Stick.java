package edu.monash.fit2099.demo.mars;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;
import game.Ammunition;
import game.CraftableItem;
import game.WeaponHandler;

public class Stick extends WeaponHandler {

	public Stick() {
		super("stick", '/', 10, "pokes");
	}

	@Override
	public List<Action> allowableActions(Actor actor) {
		return null;
	}
}
