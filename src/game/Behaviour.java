package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A factory for creating actions. 
 *  
 * A Behaviour represents a kind of objective that an Actor can have.  For example
 * it might want to seek out a particular kind of object, or follow another Actor, 
 * or run away and hide.  Each implementation of Behaviour returns an Action that the 
 * Actor could take to achieve its objective, or null if no useful options are available.
 * 
 * An Actor's playTurn() method can use Behaviours to help decide which Action to 
 * perform next.  It can also simply create Actions itself, but using Behaviours allows
 * us to modularize the code that decides what to do, and that means that it can be 
 * reused if (e.g.) more than one kind of Actor needs to be able to seek, follow, or hide.
 * 
 * @author ram
 *
 */
public interface Behaviour {

	/**
	 * A factory for creating actions. 
	 *
	 * A Behaviour represents a kind of objective that an Actor can have.  For example
	 * it might want to seek out a particular kind of object, or follow another Actor, 
	 * or run away and hide.  Each implementation of Behaviour returns an Action that the 
	 * Actor could take to achieve its objective, or null if no useful options are available.
	 *
	 * An Actor's playTurn() method can use Behaviours to help decide which Action to 
	 * perform next.  It can also simply create Actions itself, but using Behaviours allows
	 * us to modularize the code that decides what to do, and that means that it can be 
	 * reused if (e.g.) more than one kind of Actor needs to be able to seek, follow, or hide.
	 *
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return an Action that actor can perform, or null if actor can't do this.
	 */
	Action getAction(Actor actor, GameMap map);
}
