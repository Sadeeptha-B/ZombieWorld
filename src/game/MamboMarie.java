package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

public class MamboMarie extends ZombieActor {

	private Random rand = new Random();
	private static final ZombieCapability attackableTeam = ZombieCapability.ALIVE;
	private ZombieCapability mobility = ZombieCapability.MOBILE;
	private final int STAY_TURNS = 30;
	private int turnCount;
	
	
	
	private Behaviour[] behaviours = {
			new AttackBehaviour(attackableTeam),
			new HuntBehaviour(Human.class, 10, mobility),
			new WanderBehaviour(mobility)
	};
	
	
	
	public MamboMarie(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.WITCH, ZombieCapability.MOBILE);
	}
	
	
	
	public void tick(UtilityGameMap map) {
		turnCount ++;
//		if (turnCount % 1 == 0) {
//			chant(map);
//		}
//		
		if (map.contains(this) && !map.isActorInDimension(this) && turnCount >= STAY_TURNS) {
			map.removeActor(this);
			map.addActorToDimension(this);
	    } else if (map.isActorInDimension(this) && rand.nextInt(20) == 0) {
			map.removeActorFromDimension(this);
			map.addActor(this, getMamboLocation(map));
			resetCount();
		}
		
	}
	
	
	
	public Location getMamboLocation(UtilityGameMap map){
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
		int x, y;
		do {
			coords = mamboCoordinates.get(rand.nextInt(mamboCoordinates.size()));
			x = coords[0];
			y = coords[1];
		} while(!map.at(x, y).canActorEnter(this));
		return map.at(coords[0], coords[1]);
	}
	
	
	private void resetCount() {
		turnCount = 0;
	}
	
	
	public void chant(GameMap map) {
		int count = 0;
		while (count < 5){
			Zombie zombie = new Zombie(name);
			int x = rand.nextInt(80);
			int y = rand.nextInt(25);
			if(map.at(x, y).canActorEnter(zombie)){
				map.at(x, y).addActor(zombie);
				count += 1;
			}
		}
			
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
