package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Menu;

public class Sniper extends RangedWeapon {
	
	protected Menu menu = new Menu();
	
	private static final int MAX_AMMO = 5;
	private ReloadCapability ammoCapability = ReloadCapability.SNIPER;
	
	public Sniper() {
		super("Sniper", '^', 40);
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


	@Override
	public Action subMenuActions(Actor actor, Actor target) {
		Actions actions = new Actions();
		actions.add(new ShootTargetAction(this, target));
		actions.add(new AimAction(target));
		shootDisplay.println("------------------------");
		Action action = menu.showMenu(actor, actions, shootDisplay);
		return action;
	}
	
	
	
}



