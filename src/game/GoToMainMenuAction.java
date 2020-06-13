package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * Lets a player go back to the main menu
 * 
 * @author Sadeeptha Bandara
 *
 */
public class GoToMainMenuAction extends Action {

	@Override
	public String execute(Actor actor, GameMap map) {
		return actor + " did not select target";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Return to main menu";
	}


}
