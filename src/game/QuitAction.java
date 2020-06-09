package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuitAction extends Action {

	@Override
	public String execute(Actor actor, GameMap map) {
		actor.addCapability(ZombieCapability.QUITTED);
		map.removeActor(actor);
		return actor + " decided to quit the game";
	}

	@Override
	public String menuDescription(Actor actor) {
		
		return "Quit game";
	}

}
