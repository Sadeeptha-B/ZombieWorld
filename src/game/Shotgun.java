package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;

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
