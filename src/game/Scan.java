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
		int xDistance = Math.abs(location.x() - actorLocation.x());
		int yDistance = Math.abs(location.y() - actorLocation.y());
		
		if (xDistance == 0 || yDistance == 0)
			return straightPathValid(location, actorLocation, xDistance, yDistance, map);
		int actorX = actorLocation.x();
		int targetX = location.x();
		int actorY =  actorLocation.y();
		int targetY = location.y();

		if (yDistance < xDistance) {
			if (actorX > targetX)
				return lowAnglePathValid(targetX, targetY, actorX, actorY, map);
			else
				return lowAnglePathValid(actorX, actorY, targetX, targetY,  map);
		}
		else {
			if (actorY > targetY) 
				return highAnglePathValid(targetX, targetY, actorX, actorY, map);
			else
				return lowAnglePathValid(actorX, actorY, targetX, targetY,  map);
		}
		
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
				if (!canBulletPassThrough(x, lesserY, map))
					return false;
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
	
	protected boolean lowAnglePathValid(int x0, int y0, int x1, int y1, GameMap map) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		int yi = 1;
		if (dy < 0){
	        yi = -1;
	        dy = -dy;
		}
		int D = 2*dy - dx;
		int y = y0;
		for(int x = x0; x0 < x1; x0++) {
			if (!canBulletPassThrough(x, y, map))
				return false;
			if (D > 0) {
				y = y + yi;
				D = D - 2*dx;
			}
			D = D + 2*dy;
		}
		return true;
	}
	
	protected boolean highAnglePathValid(int x0, int y0, int x1, int y1, GameMap map) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		int xi = 1;
		if (dx < 0){
	        xi = -1;
	        dx = -dx;
		}
		int D = 2*dx - dy;
		int x = x0;
		for(int y = y0; y0 < y1; y0++) {
			if (!canBulletPassThrough(x, y, map))
				return false;
			if (D > 0) {
				x = x + xi;
				D = D - 2*dy;
			}
			D = D + 2*dx;
		}
		return true;
	}
	
	
	
	protected boolean containsTarget(Location here) {
		return (here.getActor() != null &&
				targetClass.isInstance(here.getActor()));
	}
	
	
	protected abstract Location foundTarget(ArrayList<Location> locations, Location location, Location actorLocation,GameMap map);
	protected abstract Action locationAction(Actor actor, Location location);
}
