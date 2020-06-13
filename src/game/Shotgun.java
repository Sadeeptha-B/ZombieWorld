package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Menu;

public class Shotgun extends RangedWeapon {
	
	private static final int MAX_AMMO = 5;
	private ReloadCapability ammoCapability = ReloadCapability.SHOTGUN;

	public Shotgun() {
		super("Shotgun", '~', 35);
	}

	@Override
	protected int getMaxAmmo() {
		return MAX_AMMO;
	}
	
	public ReloadCapability getAmmoCapability() {
		return ammoCapability;
	}

	@Override
	public void shoot(Actor target) {
	}
	
	public Action subMenuActions(Actor actor, Actor target) {
		return new ShootTargetAction(this, target);
	}

	
}
