/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Arrays;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.World;
import edu.monash.fit2099.engine.Display;


/**
 * @author Sadeeptha
 *
 */
public class GameHandler extends World {
	
	private boolean winStatus = false;
	private GameDisplay gameDisplay;
	
	
	public GameHandler(Display display, GameDisplay gameDisplay) {
		super(display);
		this.gameDisplay = gameDisplay;
	}
	
	
	protected boolean winLose() {			
		ArrayList<Boolean> winLose = new ArrayList<Boolean>();
		
		for (Actor actor: actorLocations) {
			if (actor != player)
				winLose.add(actor.determineResult());
		}
		
		ArrayList<Boolean> container = new ArrayList<Boolean>(Arrays.asList(true, false));
		winStatus = winLose.contains(true);
		return winLose.containsAll(container);
		
	}
	
	
	protected boolean stillRunning() {
		boolean endCond = winLose();
		winStatus = winStatus && actorLocations.contains(player);
		return super.stillRunning() && endCond;
	}
	
	
	protected String endGameMessage() {
		String msg;
		if (winStatus)
			msg = gameDisplay.getWinMsg();
		else
			msg = gameDisplay.getLoseMsg();
		
		return msg + System.lineSeparator() + super.endGameMessage();
	}
}
