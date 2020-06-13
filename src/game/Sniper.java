package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
/**
 * The sniper class
 * 
 * @author Saddeptha Bandara/Kaveesha Nissanka
 *
 */
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
	public SniperScan weaponScan() {
		return new SniperScan();
	}
	
	/**
	 * This method will shoot the target with a probability of 75% if the player has not aimed at all
	 * The more the player aims the more damage and more accurate the shot will be
	 * If the player aims for 2 rounds then the target is killed instantly
	 * 
	 * @param target: The target actor
	 * @param map: The GameMap 
	 * @param actorLocation: The location of the player
	 * @return: String The string containing details about the execution
	 * 
	 */
	@Override
	public String shoot(Actor target, GameMap map, Location actorLocation) {
		ammoCount -= 1;
		String printMsg;
		
		if (!(rand.nextInt(4) == 0) &&  aimCount == 1) {
			target.hurt(noAimDamage);
			printMsg = actorLocation.getActor() + " shoots " + target + " for " + noAimDamage;
			
		}else if (!(rand.nextInt(10) == 1) && aimCount == 2) {
			target.hurt(oneRoundAim);
			printMsg = actorLocation.getActor() + " shoots " + target + " for " + oneRoundAim;
			
		}else if (aimCount == 3) {
			target.death(map);
			printMsg = actorLocation.getActor() + " instakills " + target;
			
		} else {
			printMsg = actorLocation.getActor() + " misses " + target;
		}
		
		
		return printMsg;
	}
	
	/**
	 * displays a ShootTargetAction, AimAction or a GoToMainMenuAction for the player to choose the sub menu
	 * 
	 * 
	 * @param actor: The player
	 * @param target: The actor that is being targeted
	 * @return: Action The action the player chooses
	 * 
	 */

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



