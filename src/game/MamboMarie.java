package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

public class MamboMarie extends ZombieActor {

	private Random rand = new Random();
	
	private int turnCount;
	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10, ZombieCapability.MOBILE),
			new WanderBehaviour(ZombieCapability.MOBILE)
	};
	
	
	public MamboMarie(String name, char displayChar, int hitPoints, UtilityGameMap ... maps) {
		super(name, displayChar, hitPoints, ZombieCapability.WITCH, ZombieCapability.MOBILE);
		for (UtilityGameMap map: maps) {
			map.addActorToDimension(this);
		}
	}
	
	
	public void tick(UtilityGameMap map) {
		turnCount += 1;
		
		if (map.contains(this) && !map.isActorInDimension(this) && turnCount >= 30) {
			map.removeActor(this);
			map.addActorToDimension(this);
	    }
		else if (rand.nextInt(19) == 0) {
			map.removeActor(this);
			map.addActor(this, mamboLocation(map));
			resetCount();
		}
			
	}
	
	
	public Location mamboLocation(UtilityGameMap map){
		NumberRange xCoords = map.getXRange();
		NumberRange yCoords = map.getYRange();
		ArrayList<int[]> mamboCoordinates = new ArrayList<int[]>();
		
		int[] widthMaxMin = {xCoords.min(), xCoords.max()};
		int[] heightMaxMin = {yCoords.min(), yCoords.max()};
		
		for (int x : xCoords) {
			for (int y: heightMaxMin) {
				mamboCoordinates.add(new int[] {x,y});
			}
		}
		
		for (int y: yCoords) {
			for (int x: widthMaxMin) {
				mamboCoordinates.add(new int[] {x,y});
			}
		}
		
		
		int[] coords;
		do {
			coords = mamboCoordinates.get(rand.nextInt(mamboCoordinates.size()));
		} while(map.at(coords[0], coords[1]).containsAnActor());
			

		return map.at(coords[0], coords[1]);
	}
	
	
	private void resetCount() {
		turnCount = 0;
	}
	
	
	public void chant() {
		
	}
	

	@Override
	public Behaviour[] getBehaviours() {
		Behaviour [] behaviours = new Behaviour[this.behaviours.length];
		for (int i = 0; i < this.behaviours.length; i++)
			behaviours[i] = this.behaviours[i];
		return behaviours;
	}

	
	@Override
	public Corpse death() {
		return super.death(false);
	}
	
	@Override
	public Action pickUpItem(GameMap map) {
		return null;
	}
	
	@Override
	public boolean determineResult() {
		return false;
	}

}
