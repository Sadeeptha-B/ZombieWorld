package game;

public class Ammunition extends PortableItem {

	private int ammo_count;
	private final int ammo_capacity = 30;
	
	public Ammunition(String name, char displayChar) {
		super(name, displayChar);
		ammo_count = getAmmoCapacity();
	}
	
	protected int getAmmoCapacity() {
		return ammo_capacity;
	}
	
}
