package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public abstract class Scan {

	
	private Class<?> targetClass;
	private int maxRange;
	private HashSet<Location> visitedLocations = new HashSet<Location>();
	
	public Scan(Class<?> cls, int range) {
		this.targetClass = cls;
		this.maxRange = range;
	}

	protected Action scan(Actor actor, Location here, GameMap map) {
		visitedLocations.clear();
		ArrayList<Location> now = new ArrayList<Location>();
		
		now.add(here);
		
		ArrayList<ArrayList<Location>> layer = new ArrayList<ArrayList<Location>>();
		layer.add(now);
		
		for (int i = 0; i < maxRange; i++) {
			layer = getNextLayer(actor, layer);
			Location there = search(layer, actor, map);
			if (there != null)
				return locationAction(actor, there);
		}
		return null;
	}
	
	
	protected ArrayList<ArrayList<Location>> getNextLayer(Actor actor, ArrayList<ArrayList<Location>> layer) {
		ArrayList<ArrayList<Location>> nextLayer = new ArrayList<ArrayList<Location>>();

		for (ArrayList<Location> path : layer) {
			List<Exit> exits = new ArrayList<Exit>(path.get(path.size() - 1).getExits());
			Collections.shuffle(exits);
			for (Exit exit : path.get(path.size() - 1).getExits()) {
				Location destination = exit.getDestination();
				if (!destination.getGround().canActorEnter(actor) || visitedLocations.contains(destination))
					continue;
				visitedLocations.add(destination);
				ArrayList<Location> newPath = new ArrayList<Location>(path);
				newPath.add(destination);
				nextLayer.add(newPath);
			}
		}
		return nextLayer;
	}
	
	protected Location search(ArrayList<ArrayList<Location>> layer, Actor actor, GameMap map) {
		for (ArrayList<Location> path : layer) {
			Location location = path.get(path.size() - 1);
			if (containsTarget(location)) {
				Location result = foundTarget(path, location, layer.get(0).get(0), map);
				if (result != null)
					return result;
			}
		}
		return null;
	}
	
	protected boolean pathValid(Location location, Location actorLocation, GameMap map) {
		int xDistance = Math.abs(actorLocation.x() - location.x());
		int yDistance = Math.abs(actorLocation.y() - location.y());
		
		if (xDistance == 0 || yDistance == 0)
			return straightPathValid(location, actorLocation, xDistance, yDistance, map);
		
		return false;
	}
	
	protected boolean straightPathValid(Location targetLocation, Location actorLocation, int x, int y, GameMap map) {
		
		if (x == 0) {
			int greaterX = Math.max(targetLocation.x(), actorLocation.x());
			int lesserX = Math.min(targetLocation.x(), actorLocation.x());
			
			while (lesserX < greaterX) {
				canBulletPassThrough(lesserX, y, map);
				lesserX += 1;
			}
		}
		else if (y == 0) {
			int greaterY = Math.max(targetLocation.y(), actorLocation.y());
			int lesserY = Math.min(targetLocation.y(), actorLocation.y());
			
			while (lesserY < greaterY) {
				canBulletPassThrough(x, lesserY, map);
				lesserY += 1;
			}
		}
		
		return true;
	}
	
	protected boolean canBulletPassThrough(int x, int y, GameMap map) {
		if(map.at(x, y).getGround() instanceof Fence)
			return false;
		else if(map.at(x, y).getGround() instanceof Brick)
			return false;
		return true;
	}
	
	protected boolean angledPath(Location targetLocation, Location actorLocation, GameMap map) {
		return false;
	}
	
	protected boolean containsTarget(Location here) {
		return (here.getActor() != null &&
				targetClass.isInstance(here.getActor()));
	}
	
	
	protected abstract Location foundTarget(ArrayList<Location> locations, Location location, Location actorLocation,GameMap map);
	protected abstract Action locationAction(Actor actor, Location location);
}
