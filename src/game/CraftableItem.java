package game;

import edu.monash.fit2099.engine.WeaponItem;

public abstract class CraftableItem extends PortableItem {

	private String craftedTo;
	
	public CraftableItem(String name, char displayChar, String craftedTo) {
		super(name, displayChar);
		this.craftedTo = craftedTo;
	}

	public String getCraftedItem() {
		return craftedTo;
	}
	
	public abstract WeaponItem craft();
	

}
