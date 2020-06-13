package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class AimAction extends Action {

	private Actor target;

	public AimAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return actor + " aims at " + target;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Aim: " + target;
	}

}
