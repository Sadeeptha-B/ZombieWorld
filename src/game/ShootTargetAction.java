package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ShootTargetAction extends Action {
	
	private RangedWeapon weapon;
	private Actor target;
	
	public ShootTargetAction(RangedWeapon weapon, Actor target) {
		this.weapon = weapon;
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		weapon.shoot(target, map, map.locationOf(actor));
		return actor + " shoots " + target;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Shoot :" + target;
	}



}
