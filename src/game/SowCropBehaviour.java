package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class SowCropBehaviour implements Behaviour {
	
	protected Random rand = new Random();
	
	public SowCropBehaviour(){
		
	}
	
	public Action getAction(Actor actor, GameMap map) {
		for(Item item: map.locationOf(actor).getItems()) {
			if(item instanceof Item)
				return null;
		}
			
		if (rand.nextInt(3) == 0 && map.locationOf(actor).getGround() instanceof Dirt) {
			return new SowCropAction(new Crop("crop",'_',false));
		}
		return null;
	}
}
