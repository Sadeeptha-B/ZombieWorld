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
	
	public int count = 0;
	
	public Corpse(String name, char displayChar) {
		super(name, displayChar);
	}
	/**
	 * the tick method will create a Zombie after 5-10 turns
	 * 
	 * @param location The location of the corpse 
	 */
	public void tick(Location location){
		count++;
		mutate(location);
	}
	
	private void mutate(Location location) {
		if (count > 5) {
			if(rand.nextInt(10 - count) == 0 && locationValid(location)) {
				Zombie zombie = new Zombie(name);
				location.addActor(zombie);
				System.out.println(name + " has risen from the dead");
				location.removeItem(this);
			}
		}
	}
	
	private boolean locationValid(Location location) {
		if (location.containsAnActor()) {
			count --;
			return false;
		}
		return true;
	}

}
