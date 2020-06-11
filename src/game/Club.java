package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A crafted weapon.
 * 
 * @author Kaveesha Nissanka
 *
 */
public class Club extends WeaponHandler {
	
	public Club() {
		super("club", '|', 25, "whams");
	}

	@Override
	public List<Action> allowableActions(Actor actor) {
		return null;
	}
	
}
