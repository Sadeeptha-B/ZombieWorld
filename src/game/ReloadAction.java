package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class ReloadAction extends Action {

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
			int ammoNeeded = weapon.getMaxAmmo() -  weapon.getAmmoCount();
			if (weapon.getAmmoCount() < weapon.getMaxAmmo()) {
				if (ammoNeeded >= availableAmmo) {
					weapon.reload(ammoNeeded);
					ammo.reduceAmmo(ammoNeeded);
					return actor + " reloads the " + actor.getWeapon();
				}
				weapon.reload(availableAmmo);
				ammo.reduceAmmo(availableAmmo);
				actor.removeItemFromInventory(ammo);
				return actor + " reloads the " + actor.getWeapon();
				
			}
			
			return actor.getWeapon().verb() + " is not reloadable";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Reload gun";
	}

}
