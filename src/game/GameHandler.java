/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Arrays;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.World;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Location;


/**
 * The game handler for the game
 * gives access to world functions 
 * 
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

	/**
	 * Checks to see if the moment is a win or a loss for the player
	 * 
	 * @return boolean whether or not it is a win/loose scenario
	 */
	protected boolean winLoseScenario() {		
		ArrayList<Boolean> winLose = new ArrayList<Boolean>();
		
		for (Actor actor: actorLocations) {
			if (actor != player)
				winLose.add(actor.determineResult());
		}
		
		ArrayList<Boolean> container = new ArrayList<Boolean>(Arrays.asList(true, false));
		winStatus = winLose.contains(true);
		return winLose.containsAll(container);
	}
	
	/**
	 * checks to see if the game conditions for running the game are still valid
	 * 
	 * @return boolean 
	 */
	protected boolean stillRunning() {
		boolean endCond = winLoseScenario();
		winStatus = winStatus && actorLocations.contains(player);
		return super.stillRunning() && endCond;
	}
	
	
	protected String endGameMessage() {
		String msg;
		if (player.hasCapability(ZombieCapability.QUITTED))
			return super.endGameMessage();
		
		msg = gameDisplay.getEndMsg(winStatus);
		return msg + System.lineSeparator() + super.endGameMessage();
	}
	

}


