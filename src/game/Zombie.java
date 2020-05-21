package game;

import java.util.Random;

import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram, Student Sadeeptha Bandara
 * 
 *
 */
public class Zombie extends ZombieActor {
	
	private int armCount = 2;
	private int legCount = 2;
	
	protected Random rand = new Random();
	
	private Behaviour[] behaviours = {
			new PickUpBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10, ZombieCapability.MOBILE),
			new WanderBehaviour(ZombieCapability.MOBILE)
	};

	
	/**
	 * Constructor
	 * 
	 * Initializes the Zombie and adds capabilities of undead and mobile
	 * @param name : Zombie's name
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD, ZombieCapability.MOBILE);
		
	}
	

	@Override
	/**
	 * Returns the intrinsic attack of a Zombie, if the Zombie does not have a
	 * weapon
	 * 
	 * The attack depends on the arm count of the Zombie
	 */
	public IntrinsicWeapon getIntrinsicWeapon() {
		IntrinsicWeapon attack; 
		boolean condition = rand.nextBoolean();
		
		switch (armCount) {
		case 0:
			condition = true;
		case 1:
			condition = rand.nextBoolean() | rand.nextBoolean();
			break;
		}
		
		
		if (condition) {
			attack = new IntrinsicWeapon(20,"bites");
			heal(5);
		} else {
			attack = new IntrinsicWeapon(10, "punches");
		}
		
		return attack;
	}
	
	
	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (rand.nextBoolean())
			display.println(zombiePhrases());
		
		limbCheck(map);
			
		Action action = super.playTurn(actions, lastAction, map, display);
		return action;
	}
	
	
	
	/**
	 * Generates a random phrase that the Zombie's will scream
	 * @return : Zombie phrase
	 */
	public String zombiePhrases() {
		String[] phrases = {"Braaaainnns", "Dieeeee", "Humans yummy", "Arrrghhh", "Gaggghhh"};
		int randomIndex = rand.nextInt(phrases.length);
		return this + " says " + phrases[randomIndex];
	}

	
	/**
	 * Called upon each attack on Zombie.
	 * 
	 * Randomly dictates limb fall and creates an Arm or Leg class accordingly
	 * Returns a Limb class if a limb falls, else returns null
	 * 
	 */
	public Limb dismember() {
		if ((armCount == 0) || (legCount== 0)){
			return null;
		}
			
		Limb fallenLimb;
		
		if (rand.nextInt(2) == 0) {
			fallenLimb = new Arm();
			armCount -= 1;
		} else if (rand.nextInt(4) == 0){	
			fallenLimb = new Leg();
			legCount -= 1;	
		} else {
			return null;
		}
		
		return fallenLimb;
	}
	
	
	/**
	 * Private method called by playTurn
	 * To check the number of limbs each turn and drop Items accordingly.	
	 * @param map : Gamemap, passed in by PlayTurn
	 */
	private void limbCheck(GameMap map) {
		if (legCount == 0) {
			this.removeCapability(ZombieCapability.MOBILE);
		}
		
		switch(armCount){
		case 0:
			dropItems(map);
			break;
		case 1:
			if (rand.nextBoolean())
				dropItems(map);
			break;
		}
	}
	
	
	/**
	 * Drops all items. Called by limbcheck randomly based on the count of limbs
	 * 
	 * @param map : Gamemap for getting the location at which to drop weapons 
	 */
	private void dropItems(GameMap map) {
		Actions dropActions = new Actions();
		for (Item item: this.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)
			drop.execute(this, map);
	}

	
	public Action pickUpItem(GameMap map) {
		for (Item item: map.locationOf(this).getItems())
			if (item.asWeapon() != null)
				return(item.getPickUpAction());
		return null;
	}
	
	
	@Override
	public float getHealthPercantage() {
		return 0;
	}
	
	public Corpse death() {
		return super.death(false);
	}
	
	public Behaviour[] getBehaviours() {
		return this.behaviours;
	}
	
}
