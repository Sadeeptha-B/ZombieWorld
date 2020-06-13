package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class AimAction extends Action {

	private Actor target;
	private RangedWeapon weapon;
	private int aimCount;
	private final int AIM_LIMIT = 2;

	public AimAction(RangedWeapon weapon, Actor target, int aimCount) {
		this.weapon = weapon;
		this.target = target;
		this.aimCount = aimCount;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		aimCount ++;
		return actor + " aims at " + target;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Aim: " + target;
	}
	
	
	public Action getNextAction() {
		if (aimCount <= AIM_LIMIT)
			return new SelectTargetAction(weapon, target);
		return null;
	}
	
}
