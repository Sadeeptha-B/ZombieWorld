package game;

public class ShotgunAmmo extends Ammunition {
	
	private ReloadCapability weaponCapability = ReloadCapability.SHOTGUN;

	public ShotgunAmmo() {
		super("Shotgun Ammo", 'p');
	}
	
	public ReloadCapability getWeaponCapability() {
		return weaponCapability;
	}

	
}
