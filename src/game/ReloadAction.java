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
	
	int ammoNeeded;
	
	@Override
	public String execute(Actor actor, GameMap map) {
		RangedWeapon weapon = (RangedWeapon) actor.getWeapon();
		Ammunition ammo = null;
		
		for (Item item: actor.getInventory()) {
			if (item instanceof Ammunition) {
				ammo = (Ammunition) item;
			}
		}
		
		int availableAmmo = ammo.getAmmoCount();
		if (weapon.getAmmoCount() < weapon.getMaxAmmo()) {
			
			if (ammoNeeded <= availableAmmo) {
				weapon.reload(ammoNeeded);
				ammo.reduceAmmo(ammoNeeded);
				return actor + " reloads the " + actor.getWeapon();
			}
			weapon.reload(availableAmmo);
			ammo.reduceAmmo(availableAmmo);
			actor.removeItemFromInventory(ammo);
			return actor + " reloads the " + actor.getWeapon();
			
		}
		
		return actor.getWeapon() + " is not reloadable";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Reload gun";
	}

}
