package game;

public class SniperAmmo extends Ammunition {

	private ReloadCapability weaponCapability = ReloadCapability.SNIPER;
	
	public SniperAmmo() {
		super("Sniper Ammo", '*');
	}
	
	public ReloadCapability getWeaponCapability() {
		return weaponCapability;
	}

}
