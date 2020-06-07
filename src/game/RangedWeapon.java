package game;

import edu.monash.fit2099.engine.WeaponItem;

public abstract class RangedWeapon extends WeaponItem {

	private int ammo_count;
	
	
	public RangedWeapon(String name, char displayChar, int damage) {
		super(name, displayChar, damage, "shoots");
		ammo_count = getMaxAmmo();
	}
	
	
	public RangedWeapon(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}
	
	
	protected abstract int getMaxAmmo();
	
	
	protected int getAmmoCount() {
		return ammo_count;
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
