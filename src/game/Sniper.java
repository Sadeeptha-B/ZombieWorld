package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;

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
