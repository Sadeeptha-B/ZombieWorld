package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.WeaponItem;
import game.CraftableItem;

public class Stick extends WeaponItem {

	public Stick() {
		super("stick", '/', 10, "pokes");
	}

	public CraftableItem asCraftableItem(){
		return null;
	}

}
