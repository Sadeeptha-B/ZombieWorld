package game;

/**
 * The food class
 * 
 * @author Admin
 *
 */
public class Food extends PortableItem {

	
	public Food() {
		super("food", '$');
	}
	
	/**
	 * gives the amount of health points that will be given to an actor upon consumption
	 * 
	 * @return int the amount of health points
	 */
	@Override
	public int getHealPoints() {
		return 10;
	}
	
	/**
	 * returns the edibility of the item
	 * 
	 * @return boolean true
	 * 
	 */
	@Override
	public boolean isEdible() {
		return true;
	}
	
	
}
