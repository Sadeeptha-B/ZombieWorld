package game;


import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;

public abstract class RangedWeapon extends WeaponHandler {

	protected int ammoCount;
	protected Menu menu = new Menu();
	protected Display shootDisplay = new Display();
	protected Random rand = new Random();
	
	public RangedWeapon(String name, char displayChar, int damage) {
		super(name, displayChar, damage, "shoots");
		this.ammoCount = 0;
	}
	
	
	public RangedWeapon(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}
	

	public int getAmmoCount() {
		return ammoCount;
	}
	
	/**
	 * Reloads the weapon
	 * 
	 * @param ammo The amount of ammo needed
	 * @return The amount of ammo that has been reloaded
	 */
	protected int reload(int ammo) {
		int ammoToAdd;
		int ammoNeeded = this.getMaxAmmo() -  this.getAmmoCount();
		ammoToAdd = Math.min(ammoNeeded, ammo);
		ammoCount += ammoToAdd;			
		return ammoToAdd;
	}
	
	
	public boolean fullyLoaded() {
		return this.getAmmoCount() == this.getMaxAmmo();
	}
	
	
	private void rangedWeaponDisplay() {
		System.out.println(this + " Ammo Rounds - " + ammoCount + "  ||   Max Capacity - " + getMaxAmmo());
	}
	
	public boolean isEmpty() {
		return this.getAmmoCount() == 0;
	}
	
	/**
	 * 
	 * Returns a list of allowable actions for the items in the players inventory
	 * @param player: The player
	 * @return : List<Action> The allowed actions
	 * 
	 */
	@Override
	public List<Action> playerAllowableActions(Player player) {	
		List<Action> actions = super.playerAllowableActions(player);
		
		if (player.getWeapon() != this)
			return actions;
		
		rangedWeaponDisplay();
		Ammunition ammo = null;
		
		for (Item item: player.getInventory()) {
			if(item.asAmmo() != null) {
				ammo = (Ammunition) item;
				ammo = ammoPresent(ammo);
				if (ammo != null)
					break;
			}
		}
		
		if (ammo != null && !fullyLoaded()) 
			actions.add(new ReloadAction(this, ammo));
		
		if (!this.isEmpty()) {
			actions.add(new ShootAction(this));
		}
		
		return actions;
	}

	/**
	 * Checks if the ammo is of that gun type and returns it
	 * 
	 * @param ammo: The ammo
	 * @return Ammunition the correct type of ammo or null
	 */
	public Ammunition ammoPresent(Ammunition ammo) {
		if (ammo.getWeaponCapability() == this.getAmmoCapability()) {
			return ammo;
		}
		return null;
	}
	
	public abstract String shoot(Actor target, GameMap map, Location actorLocation);
	public abstract ReloadCapability getAmmoCapability();
	public abstract Action subMenuActions(Actor actor, Actor target);
	public abstract Scan weaponScan();
	
	protected abstract int getMaxAmmo();
}
