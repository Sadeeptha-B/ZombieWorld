package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public abstract class RangedWeapon extends WeaponHandler {

	private int ammoCount;
	
	
	public RangedWeapon(String name, char displayChar, int damage) {
		super(name, displayChar, damage, "shoots");
		ammoCount = 0;
	}
	
	
	public RangedWeapon(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}
	

	public int getAmmoCount() {
		return ammoCount;
	}
	
	
	protected int reload(int ammo) {
		int ammoToAdd;
		int ammoNeeded = this.getMaxAmmo() -  this.getAmmoCount();
		ammoToAdd = Math.min(ammoNeeded, ammo);
		ammoCount += ammoToAdd;
		return ammoToAdd;
	}
	
	
	public boolean fullyReloaded() {
		return this.getAmmoCount() == this.getMaxAmmo();
	}
	
	
	@Override
	public List<Action> allowableActions(Actor actor) {
		List<Action> actions = super.getAllowableActions();
		Ammunition ammo = null;
		
		for (Item item: actor.getInventory()) {
			if (item.asAmmo() != null) {
				ammo = item.asAmmo();
			}		
		}
		
		boolean weaponAmmoCond = actor.getWeapon() == this && ammo != null;
		if (weaponAmmoCond && !fullyReloaded()) {
			actions.add(new ReloadAction(this, ammo));
		}
		return actions;
	}

	
	protected abstract int getMaxAmmo();
}
