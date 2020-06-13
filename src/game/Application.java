package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.NumberRange;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {
	
	private static Random rand = new Random();
	static GameDisplay gameDisplay = new GameDisplay();
	static World world = new GameHandler(new Display(), gameDisplay);
	static FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree(), new Brick());
	
	public static void main(String[] args) {
		
		UtilityGameMap compound = addUtilityMaps(groundFactory, gameDisplay.getMap("Compound"));
		GameMap town = addMaps(groundFactory, gameDisplay.getMap("Town"));
		
		//Player
		Actor player = new Player("Player", '@', 100);
		add(compound, 38, 15, player);
		
		//MamboMarie
		MamboMarie mambo = new MamboMarie("Mambo Marie", 'M', 100);
		compound.addActorToDimension(mambo);
		

		// Placing a vehicle for player to move between maps
		Vehicle compoundVehicle = new Vehicle();
        compoundVehicle.addAction(new MoveActorAction(town.at(65, 20), "to Town!"));
        add(compound, 50, 17, compoundVehicle);
    
		
	    // Farmer
        add(compound, 31, 7, new Farmer("Ceres", 'F', 75));

        //Humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy","Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		addHumansToMap(humans, 30, 20.0, 5, 7.0, compound);
		
		//Items
		add(compound, 42, 15, new Shotgun());
		add(compound, 41, 15, new Plank());
		add(compound, 41, 17, new Sniper());
		add(compound, 43, 15, new SniperAmmo());
		
		//Zombies
		add(compound, 30, 20, new Zombie("Groan"));
		add(compound, 30, 18, new Zombie("Boo"));
		add(compound, 10, 4, new Zombie("Uuuurgg"));
		add(compound, 50, 18, new Zombie("Mortalis"));
		add(compound, 1, 10, new Zombie("Gaaaah"));
		add(compound, 62, 12, new Zombie("Aaargh"));
		

 /**********************************************************************************************************************/
		// Town 
		add(town, 29, 18, new Zombie("Gorbag"));
		add(town, 37, 20, new Zombie("Shagrat"));
		add(town, 19, 2, new Zombie("Gollum"));
		add(town, 14, 23, new Zombie("Azog"));
		add(town, 50, 23, new Zombie("Bolg"));
		add(town, 75, 24, new Zombie("Uglúk"));
		
		//Vehicle
		Vehicle townVehicle = new Vehicle();
        townVehicle.addAction(new MoveActorAction(compound.at(50, 17), "to the Compound!"));
        add(town, 65, 20, townVehicle);

        //Humans
        String[] townHumans = {"Tobias", "Tom", "Dick", "Harry", "Samwise","Elsa", "Clarissa", "X Æ A12", "Simon", "Kristy"};
		addHumansToMap(townHumans, 42, 14.0, 4, 10.0, town);
		
		
		try {
			world.run();
		}catch (IllegalStateException e) {
			Actor player_failsafe = new Player("Player", '@', 100);
			add(compound, 42, 15, player_failsafe);
			world.run();
		}
		
	}
	
	public static UtilityGameMap addUtilityMaps(FancyGroundFactory groundFactory, List<String> bluePrint) {
		UtilityGameMap map = new UtilityGameMap(groundFactory, bluePrint);
		world.addGameMap(map);
		return map;
	}
	
	public static GameMap addMaps(FancyGroundFactory groundFactory, List<String> bluePrint) {
		GameMap map = new GameMap(groundFactory, bluePrint);
		world.addGameMap(map);
		return map;
	}
	
	
	private static Location getMapValidLocation(GameMap map, int x, int y) {
		Location location;
		try {
			location = map.at(x, y);
		} catch (Exception e) {
			x = rand.nextInt(map.getXRange().max());
			y = rand.nextInt(map.getYRange().min());
			location = map.at(x, y);
		}
		return location;
	}
	
	public static void add(GameMap map, int x, int y, Item item) {
		getMapValidLocation(map, x, y).addItem(item);
	}
	
	public static void add(GameMap map, int x, int y, Actor actor) {
		Location location = actorValidLocation(actor, getMapValidLocation(map, x, y), 2,2);
		if (actor.isPlayer())
			world.addPlayer(actor, location);
		else 
			location.addActor(actor);
	}
	
	private static Location actorValidLocation(Actor actor, Location location, double xRange, double yRange) {
		int x, y;
		while (!location.canActorEnter(actor)) {
			x = (int) Math.floor(Math.random() * xRange + location.x());
			y = (int) Math.floor(Math.random() * yRange + location.y()); 
			location = getMapValidLocation(location.map(),x, y);
		}
		return location;
	}
	
	public static void addHumansToMap(String[] names,int xStartCoordinate,double xRange,int yStartCoordinate, double yRange, GameMap map) {
		for (String name : names) {
			Human human = new Human(name);
			Location location = actorValidLocation(human, map.at(xStartCoordinate, yStartCoordinate), xRange, yRange);
			add(map,location.x(), location.y(), human);
		}
	}
	
}
