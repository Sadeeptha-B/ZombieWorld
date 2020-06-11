package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;

import edu.monash.fit2099.engine.WeaponItem;

public abstract class WeaponHandler extends WeaponItem {

	public WeaponHandler(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Action> getAllowableActions() {
		List<Action> list = super.getAllowableActions();
		List<Action> actions = new ArrayList<Action>();
		for (Action action: list) {
			actions.add(action);
		}
		return actions;
	}
	

	@Override
	public CraftableItem asCraftableItem() {
		return null;
	}

	@Override
	public boolean isEdible() {
		return false;
	}

	@Override
	public Ammunition asAmmo() {
		return null;
	}

}
