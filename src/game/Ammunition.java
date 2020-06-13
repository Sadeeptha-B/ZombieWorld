package game;

public abstract class Ammunition extends PortableItem {

	private int ammoCount;
	private final int ammoCapacity = 30;
	
	public Ammunition(String name, char displayChar) {
		super(name, displayChar);
		ammoCount = getAmmoCapacity();
		this.addCapability(getWeaponCapability());
	}
	
	protected abstract ReloadCapability getWeaponCapability();

	protected int getAmmoCapacity() {
		return ammoCapacity;
	}
	
	public void reduceAmmo(int ammo) {
		ammoCount = Math.max(0, ammoCount - ammo);
	}
	
	protected int getAmmoCount() {
		return ammoCount;
	}
	
	
}
