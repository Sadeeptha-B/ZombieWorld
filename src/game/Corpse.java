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
	public boolean diedAsHuman;
	
	public Corpse(String name, boolean diedAsHuman) {
		super("dead " + name, '%');
		this.diedAsHuman = diedAsHuman;
	}
	
	/**
	 * the tick method will create a Zombie after 5-10 turns
	 * 
	 * @param location The location of the corpse 
	 */
	public void tick(Location location){
		count++;
		if(diedAsHuman)
			mutate(location);
	}
	
	private void mutate(Location location) {
		//FIXME : Possible IllegalArgumentException
		boolean condition = count > 5 && rand.nextInt(10 - count) == 0 && locationValid(location); 
		if (condition) {
			Zombie zombie = new Zombie(name);
			location.addActor(zombie);
			System.out.println(name + " has risen from the dead");
			location.removeItem(this);
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
