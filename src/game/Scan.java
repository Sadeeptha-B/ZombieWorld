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
/**
 * Scans a map and returns targets
 * Uses code written by Spike to find targets
 * 
 * @author Kaveesha Nissanka/ Sadeeptha Bandara
 *
 */
public abstract class Scan {

	
	private Class<?> targetClass;
	private int maxRange;
	private HashSet<Location> visitedLocations = new HashSet<Location>();
	
	public Scan(Class<?> cls, int range) {
		this.targetClass = cls;
		this.maxRange = range;
	}
	
	/**
	 * looks for targets and returns actions if required
	 * 
	 * @param actor The actor That is scanning
	 * @param hereThe location of the actor
	 * @param map The map
	 * @return 
	 */
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
	
	/**
	 * Looks through a layer to find targets
	 * 
	 * @param layer
	 * @param actor
	 * @param map
	 * @return
	 */
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
	
	/**
	 * Calls methoods that use Bresenhams line algorithm to validate target path to see if a bullet will pass through
	 * 
	 * @param location The location of the target
	 * @param actorLocation The actors target
	 * @param map The map
	 * @return boolean Returns true if the path is valid false if it is not
	 */
	protected boolean pathValid(Location location, Location actorLocation, GameMap map) {
		int xDistance = Math.abs(location.x() - actorLocation.x());
		int yDistance = Math.abs(location.y() - actorLocation.y());

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
				return highAnglePathValid(actorX, actorY, targetX, targetY,  map);
		}
		
	}
	
	/** 
	 * Checks to see if a bullet can pass through a location
	 * 
	 * @param x The x coordinate of the location
	 * @param y The y coordinate of the location
	 * @param map The map
	 * @return boolean Returns true and false depending on the ground
	 */
	protected boolean canBulletPassThrough(int x, int y, GameMap map) {
		if(map.at(x, y).getGround() instanceof Dirt) 
			return true;
		return false;		
	}
	
	/**
	 * if the angle from the player to the zombie is small then this method is used to check the paths validity
	 * The algorithm forms a close approximation to the path and checks if each location allows a bullet to pass through
	 * 
	 * @param x0 an actors x coordinate
	 * @param y0 an actors y coordinate
	 * @param x1 an actors x coordinate
	 * @param y1 an actors y coordinate
	 * @param map
	 * @return boolean Validity of the path
	 */
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
		while( x0 < x1) {
			int x = x0;
			if (!canBulletPassThrough(x, y, map))
				return false;
			if (D > 0) {
				y = y + yi;
				D = D - 2*dx;
			}
			D = D + 2*dy;
			x0 += 1;
		}
		return true;
	}
	/**
	 * if the angle from the player to the zombie is large then this method is used to check the paths validity
	 * The algorithm forms a close approximation to the path and checks if each location allows a bullet to pass through
	 * 
	 * @param x0 an actors x coordinate
	 * @param y0 an actors y coordinate
	 * @param x1 an actors x coordinate
	 * @param y1 an actors y coordinate
	 * @param map
	 * @return boolean Validity of the path
	 */
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
		while(y0 < y1) {
			int y = y0;
			if (!canBulletPassThrough(x, y, map))
				return false;
			if (D > 0) {
				x = x + xi;
				D = D - 2*dy;
			}
			D = D + 2*dx;
			y0 += 1;
		}
		return true;
	}
	
	/**
	 * Checks to see if the given location has the target
	 * 
	 * @param here the location that is being checked
	 * @return boolean Returns true or false 
	 */
	protected boolean containsTarget(Location here) {
		return (here.getActor() != null &&
				targetClass.isInstance(here.getActor()));
	}
	
	
	protected abstract Location foundTarget(ArrayList<Location> locations, Location location, Location actorLocation,GameMap map);
	protected abstract Action locationAction(Actor actor, Location location);
	public abstract ArrayList<Actor> getTargets();
}
