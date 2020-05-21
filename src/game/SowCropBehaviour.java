package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SowCropBehaviour implements Behaviour {
	
	protected Random rand = new Random();
	
	public SowCropBehaviour(){
		
	}
	
	public Action getAction(Actor actor, GameMap map) {
		if (map.locationOf(actor).getItems() instanceof Crop) {
			return null;
		}
			
		if (rand.nextInt(3) == 0) {
			return new SowCropAction(new Crop("crop",'_',false));
		}
		return null;
	}
}
