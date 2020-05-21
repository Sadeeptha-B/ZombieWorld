package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Crop extends Item {
	
	public Crop(String name, char displayChar, boolean portable) {
		super(name,displayChar,portable);
	}
	
	private int count = 0;
	
	public void tick(Location map) {
		count++;
		if (count > 20) {
			this.displayChar = '$';
			portable = true;
		}
	}
	

}
