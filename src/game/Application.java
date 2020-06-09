package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.demo.mars.MartianItem;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new GameHandler(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree(), new Brick());
		
		List<String> compoundMap = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap compound = new GameMap(groundFactory, compoundMap);
		world.addGameMap(compound);
		
		List<String> townMap = Arrays.asList(
				"................................................................................",
				"........................................#####################...................",
				".....................................####....................########...........",
				"......+++........................#####..+...........................####........",
				".....+.++........................#.....+..................=========....#####....",
				"......++.........................#........................=.......=........#....",
				".................................#........................=.......=........#....",
				".................................##.......................=.......=........#....",
				"..................................#.......................====.====........#....",
				"..................................#........................................#....",
				"..................................#........................................#....",
				"..................................####....................................##....",
				".....................................#...................................##.....",
				".....................................######............................###......",
				"..........................................#########..................###........",
				".................................................#####............#####.........",
				".....................................................#####....######............",
				".........................................................#....#.................",
				".........................................................#....#.................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................");
		
		GameMap town = new GameMap(groundFactory, townMap);
		world.addGameMap(town);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, compound.at(42, 15));
		
		// Placing a vehicle for player to move between maps
		Vehicle compoundVehicle = new Vehicle();
        compoundVehicle.addAction(new MoveActorAction(town.at(65, 20), "to Town!"));
        compound.at(50, 17).addItem(compoundVehicle);
		
	    // Place some random humans
		compound.at(31, 7).addActor(new Farmer("Ceres", 'F', 75));
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		addHumansToMap(humans, 30.0, 20.0, 5.0, 7.0, compound);
		
		// place a simple weapon
		compound.at(41, 15).addItem(new Plank());


		compound.at(30, 20).addActor(new Zombie("Groan"));
		compound.at(30,  18).addActor(new Zombie("Boo"));
		compound.at(10,  4).addActor(new Zombie("Uuuurgh"));
		compound.at(50, 18).addActor(new Zombie("Mortalis"));
		compound.at(1, 10).addActor(new Zombie("Gaaaah"));
		compound.at(62, 12).addActor(new Zombie("Aaargh"));	
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Town 
		town.at(29, 18).addActor(new Zombie("Gorbag"));
		town.at(37, 20).addActor(new Zombie("Shagrat"));
		town.at(19, 2).addActor(new Zombie("Gollum"));
		town.at(14, 23).addActor(new Zombie("Azog"));
		town.at(50, 23).addActor(new Zombie("Bolg"));
		town.at(75, 24).addActor(new Zombie("Ugl�k"));
		
		
		Vehicle townVehicle = new Vehicle();
        townVehicle.addAction(new MoveActorAction(compound.at(50, 17), "to the Compound!"));
        town.at(65, 20).addItem(townVehicle);
        
        String[] townHumans = {"Tobias", "Tom", "Dick", "Harry", "Samwise",
				"Elsa", "Clarissa", "X � A12", "Simon", "Kristy"};
		addHumansToMap(townHumans, 42.0, 14.0, 4.0, 10.0, town);
		
		world.run();
	}
	
	public static void addHumansToMap(String[] names,double xStartCoordinate,double xRange,double yStartCoordinate, double yRange, GameMap map) {
		int x, y;
		for (String name : names) {
			do {
				x = (int) Math.floor(Math.random() * xRange + xStartCoordinate);
				y = (int) Math.floor(Math.random() * yRange + yStartCoordinate);
			} 
			while (map.at(x, y).containsAnActor());
			map.at(x, y).addActor(new Human(name));	
		}
	}
}
