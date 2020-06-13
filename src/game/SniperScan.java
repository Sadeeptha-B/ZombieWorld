package game;

import java.util.ArrayList;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SniperScan extends Scan {

	private ArrayList<Actor> targets = new ArrayList<Actor>();
	
	
	public SniperScan() {
		super(Zombie.class, 20);
	}
	
	@Override
	public ArrayList<Actor> getTargets() {
		return new ArrayList<Actor>(targets);
	}
	
	public boolean hasTargets() {
		return !targets.isEmpty();
	}
	
	@Override
	protected Location foundTarget(ArrayList<Location> locations, Location location, Location actorLocation, GameMap map) {
		if (pathValid(location, actorLocation, map))
			targets.add(location.getActor());
		return null;
	}

	protected Action locationAction(Actor actor, Location location) {
		return null;
	}
	
	public boolean targetsFound() {
		return !targets.isEmpty();
	}
	
}

