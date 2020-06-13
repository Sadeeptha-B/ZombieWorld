package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SelectTargetAction extends Action {

	private Actor target;
	private RangedWeapon weapon;
	private Action nextAction;

	public SelectTargetAction(RangedWeapon weapon, Actor target) {
		this.weapon = weapon;
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		Action action = weapon.subMenuActions(actor, target);
		nextAction = action.getNextAction();
		return action.execute(actor, map);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "target: "+  target;
	}

	public Action getNextAction() {
		return nextAction;
	}

}
