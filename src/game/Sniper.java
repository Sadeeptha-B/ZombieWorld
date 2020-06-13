package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;

public class Sniper extends RangedWeapon {
	
	protected Menu menu = new Menu();
	
	private static final int MAX_AMMO = 5;
	private ReloadCapability ammoCapability = ReloadCapability.SNIPER;
	private int aimCount = 0;
	
	private final int noAimDamage = this.damage();
	private final int oneRoundAim = this.damage() * 2;
	

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
	public void shoot(Actor target, GameMap map, Location actorLocation) {
		if (!(rand.nextInt(4) == 0) &&  aimCount == 0)
			target.hurt(this.damage());
		else if (!(rand.nextInt(10) == 1) && aimCount == 1)
			target.hurt(this.damage() * 2);
		else {
			target.death(map);
		}	
		
	}
	
	

	@Override
	public Action subMenuActions(Actor actor, Actor target) {
		Actions actions = new Actions();
		actions.add(new ShootTargetAction(this, target));
		actions.add(new AimAction(this, target, aimCount));
		
		actions.add(new GoToMainMenuAction());
		shootDisplay.println("------------------------");
		Action action = menu.showMenu(actor, actions, shootDisplay);
		aimCount++;
		return action;
	}
		
}



