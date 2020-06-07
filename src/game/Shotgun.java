package game;

public class Shotgun extends RangedWeapon {
	
	private static final int MAX_AMMO = 5;

	public Shotgun() {
		super("Shotgun", '~', 35);
	}

	@Override
	protected int getMaxAmmo() {
		return MAX_AMMO;
	}
	
}
