package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;

public abstract class WeaponHandler extends WeaponItem {
	
	public WeaponHandler(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}

	@Override
	public CraftableItem asCraftableItem() {
		return null;
	}

	@Override
	public boolean isEdible() {
		return false;
	}

	public List<Action> playerAllowableActions(Player player) {
		List<Action> actions = new ArrayList<Action>();
		if (this != player.getWeapon() && player.getInventory().contains(this)) {
			actions.add(new ChooseWeaponAction(this));
		}
		return actions;
	}
	
	@Override
	public Ammunition asAmmo() {
		return null;
	}
	
	public boolean isRangedWeapon() {
		return false;
	};
	
	public int getMeleeDamage() {
		return this.damage();
	}

}
