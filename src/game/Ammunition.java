package game;
/**
 * The abstract ammunition class
 * 
 * @author Sadeeptha Bandara/Kaveesha Nissanka
 *
 */
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
	/**
	 * Reduces the ammo count of the ammo box
	 * 
	 * @param ammo the amount of ammo that is reduces
	 */
	public void reduceAmmo(int ammo) {
		ammoCount = Math.max(0, ammoCount - ammo);
	}
	
	protected int getAmmoCount() {
		return ammoCount;
	}
	
	
}
