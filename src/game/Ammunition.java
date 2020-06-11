package game;

public class Ammunition extends PortableItem {

	private int ammoCount;
	private final int ammoCapacity = 30;
	
	public Ammunition(String name, char displayChar) {
		super(name, displayChar);
		ammoCount = getAmmoCapacity();
	}
	
	protected int getAmmoCapacity() {
		return ammoCapacity;
	}
	
	public void reduceAmmo(int ammo) {
		ammoCount = Math.max(0, ammoCount - ammo);
	}
	
	protected int getAmmoCount() {
		return ammoCount;
	}
	
	public Ammunition asAmmo() {
		return this instanceof Ammunition ? (Ammunition) this : null;
	}
}
