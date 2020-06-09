package game;

import edu.monash.fit2099.engine.WeaponItem;

public abstract class RangedWeapon extends WeaponItem {

	private int ammoCount;
	
	
	public RangedWeapon(String name, char displayChar, int damage) {
		super(name, displayChar, damage, "shoots");
		ammoCount = getMaxAmmo();
	}
	
	
	public RangedWeapon(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}
	
	
	protected abstract int getMaxAmmo();
	
	protected int getAmmoCount() {
		return ammoCount;
	}
	
	protected void reload(int ammo) {
		ammoCount += ammo;
	}
	
	@Override
	public CraftableItem asCraftableItem() {
		return null;
	}

	@Override
	public boolean isEdible() {
		return false;
	}

	public boolean isRanged() {
		return true;
	}
	
}
