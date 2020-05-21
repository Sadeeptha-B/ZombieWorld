package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.GameMap;

public class SowCropAction extends Action {
	
	
	private Item crop;
	
	public SowCropAction(Item item) {
		crop = item;
	}
	
	@Override
	public String execute(Actor actor,GameMap map) {
		map.locationOf(actor).addItem(crop);
		return menuDescription(actor);
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " sows crop";
	}

}
