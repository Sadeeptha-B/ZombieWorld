package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;

/**
 * The food class
 * 
 * @author Admin
 *
 */
public class Food extends PortableItem {

	
	public Food() {
		super("food", 'o');
	}
	
	/**
	 * Gives the amount of health points that will be given to an actor upon consumption
	 * 
	 * @return int the amount of health points
	 */
	@Override
	public int getHealPoints() {
		return 10;
	}
	
	/**
	 * Returns the edibility of the item
	 * 
	 * @return boolean true
	 * 
	 */
	@Override
	public boolean isEdible() {
		return true;
	}
	
	/**
	 * Allowable actions for food.
	 */
//	public List<Action> getAllowableActions() {
//		List<Action> actions = super.getAllowableActions();
//		actions.add(new EatAction(this));
//		return actions;
//	}
	
	
}
