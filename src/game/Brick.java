package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
/**
 * Another ground class like fence
 * 
 * @author Kaveesha Nissanka
 *
 */
public class Brick extends Ground {
	
	public Brick() {
		super('=');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public boolean isHarvestable() {
		return false;
	}
	
}
