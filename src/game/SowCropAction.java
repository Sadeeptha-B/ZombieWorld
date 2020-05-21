package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * lets farmers plant the crops
 * 
 * @author Kaveeesha Nissnka
 *
 */
public class SowCropAction extends Action {
	
	
	private Crop crop;
	
	public SowCropAction(Crop crop) {
		this.crop = crop;
	}
	/**
	 * sets the ground as a crop class
	 * 
	 */
	@Override
	public String execute(Actor actor,GameMap map) {
		map.locationOf(actor).setGround(crop);;
		return menuDescription(actor);
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " sows crop";
	}

}
