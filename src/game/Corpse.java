package game;

import java.util.Random;

import edu.monash.fit2099.engine.Location;

/**
 * Creates a corpse class for humans killed in the field 
 * 
 * @author Kaveesha Nissanka
 *
 */

public class Corpse extends PortableItem {
	
	protected Random rand = new Random();
	
	private int count = 0;
	private boolean diedAsHuman;
	
	public Corpse(String name, boolean diedAsHuman) {
		super("dead " + name, '%');
		this.diedAsHuman = diedAsHuman;
	}
	
	/**
	 * the tick method will call the mutate method after 5-10 turns for humans
	 * 
	 * @param location The location of the corpse 
	 */
	public void tick(Location location){
		count++;
		boolean condition = count > 5 && rand.nextInt(10 - count) == 0;
		if(diedAsHuman && condition)
			mutate(location);
	}
	

	/**
	 * It will turn the corpse into a zombie when called
	 * 
	 * @param location
	 */

	private void mutate(Location location) {
		if(locationValid(location)) {
			Zombie zombie = new Zombie(name);
			location.addActor(zombie);
			System.out.println(name + " has risen from the dead");
			location.removeItem(this);
		}
		
	}
	
	/**
	 * Whether or not locations is valid for transformation. (Addition of new actor to map)

	 * Checks if the location is valid for zombies to respawn
	 * 
	 * @param location
	 * @return boolean, returns the validity of the respawn location of the zombies
	 */
	private boolean locationValid(Location location) {
		if (location.containsAnActor()) {
			count --;
			return false;
		}
		return true;
	}
	
	
	

}
