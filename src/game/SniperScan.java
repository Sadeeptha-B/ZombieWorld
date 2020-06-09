package game;

import java.util.ArrayList;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;

public class SniperScan extends Scan {

	private ArrayList<Actor> targets = new ArrayList<Actor>();
	
	
	public SniperScan(int range) {
		super(Zombie.class, range);
	}
	
	public ArrayList<Actor> getTargets() {
		return new ArrayList<Actor>(targets);
	}
	
	@Override
	protected Location foundTarget(ArrayList<Location> locations, Location location) {
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

