package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**
 * The reload action for the player
 * 
 * @author Kaveesha Nissanka
 *
 */
public class ReloadAction extends Action {
	

	private Ammunition ammo;
	private RangedWeapon weapon;
	
	public ReloadAction(RangedWeapon weapon, Ammunition ammo) {
		this.weapon = weapon;
		this.ammo = ammo;
	}
	

	@Override
	public String execute(Actor actor, GameMap map) {	
		int ammoAdded = weapon.reload(ammo.getAmmoCount());
		ammo.reduceAmmo(ammoAdded);
		
		if (ammo.getAmmoCount() == 0)
			actor.removeItemFromInventory(ammo);
		
		return actor + " reloads the " + weapon;
	}
	
	
	@Override
	public String menuDescription(Actor actor) {
		return "Reload gun";
	}
}
