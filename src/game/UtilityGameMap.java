package game;

import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

public class UtilityGameMap extends GameMap {

	protected Random rand = new Random();
	private HashMap<Actor, Location> actorDimensionLocations = new HashMap<Actor, Location>();
	
	public UtilityGameMap(GroundFactory groundFactory, char groundChar, int width, int height) {
		super(groundFactory, groundChar, width, height);
	}
	
	public UtilityGameMap(GroundFactory groundFactory, List<String> lines) {
		super(groundFactory, lines);
	}
	
	public UtilityGameMap(GroundFactory groundFactory, String mapFile) throws IOException {
		this(groundFactory, Files.readAllLines(Paths.get(mapFile)));
	}
	

	public void tick() {
		super.tick();
		for (Actor actor: actorLocations) {
			actor.tick(this);
		}
	}
	
	
	public void addActorToDimension(Actor actor) {
		int x = rand.nextInt() + this.getXRange().max();
		int y = rand.nextInt() + this.getYRange().max();
		
		Location dimensionLocation = new Location(this, x, y);
		actorDimensionLocations.put(actor, dimensionLocation);
		this.addActor(actor, dimensionLocation);
	}
		
	public boolean isActorInDimension(Actor actor) {
		return actorDimensionLocations.containsKey(actor);
	}
	
	public void removeActorFromDimension(Actor actor) {
		this.removeActor(actor);
		actorDimensionLocations.remove(actor);
	}
}
