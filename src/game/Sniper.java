package game;

public class Sniper extends RangedWeapon {
	
	private static final int MAX_AMMO = 5;
	
	
	public Sniper() {
		super("Sniper", '^', 40);
	}
	
	
	@Override
	protected int getMaxAmmo() {
		return MAX_AMMO;
	}
	
}
