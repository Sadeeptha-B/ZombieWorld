package game;

import java.util.List;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;


/**
 * Abstract class for all items that can be crafted to weapons
 * 
 * @author Sadeeptha Bandara
 *
 */
public abstract class CraftableItem extends PortableItem {

	private String craftedTo;
	
	public CraftableItem(String name, char displayChar, String craftedTo) {
		super(name, displayChar);
		this.craftedTo = craftedTo;
	}
	
	/**
	 * Get string of item being crafted
	 */
	public String getCraftedItem() {
		return craftedTo;
	}
	
	/**
	 * Abstract method for crafting
	 */
	public abstract WeaponItem craft();
	

	public List<Action> playerAllowableActions(Player player) {
		List<Action> actions = super.playerAllowableActions(player);
		if (player.getInventory().contains(this))
			actions.add(new CraftAction(this));
		return actions;
	}
	
}
