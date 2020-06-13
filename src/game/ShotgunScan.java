package game;

import java.util.ArrayList;
import java.lang.Math;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class ShotgunScan extends Scan {
	
	private ArrayList<Actor> targets = new ArrayList<Actor>();

	public ShotgunScan() {
		super(Zombie.class, 3);
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
	
	@Override
	protected Action locationAction(Actor actor, Location location) {
		return null;
	}

}
