package game;

import java.util.Random;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
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
	 * Returns the intrinsic attack of a Zombie, which can be a bite or a punch
	 * 
	 * The attack depends on the arm count of the Zombie, the checks are done by private method limbChecl
	 */
	public IntrinsicWeapon getIntrinsicWeapon() {
		IntrinsicWeapon attack; 
		final int LIMB_CHECK_KEY = 1;
		
		if (limbCheck(LIMB_CHECK_KEY)) {
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
	 * LimbCheck is used to determine whether to drop items.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		final int LIMB_CHECK_KEY = 0; 
		
		if (rand.nextBoolean())
			display.println(zombiePhrases());
		
		if(limbCheck(LIMB_CHECK_KEY))
				dropItems(map);
			
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
		if ((armCount == 0) && (legCount== 0))
			return null;	

		if (legCount != 0 && rand.nextInt(4) == 0){	
			legCount -= 1;	
			return new Leg();
		}

		if (armCount != 0 && rand.nextInt(2) == 0) {
			armCount -= 1;
			return new Arm();
		}

		return null;	
	}
	
	
	/**
	 * Private method called by playTurn and IntrinsicWeapon
	 * To check the number of limbs each turn decide on Intrinsic attack and 
	 * drop item action.
	 * 
	 * @param map : GameMap, passed in by PlayTurn
	 */
	private boolean limbCheck(int flag) {
		boolean biteProbability;
		boolean drop = false;
		
		if (flag != 0 && flag != 1)
			throw new IllegalArgumentException("LimbCheck only handles 0 and 1");
		
		if (legCount == 0) 
			this.removeCapability(ZombieCapability.MOBILE);
		
		
		switch(armCount){
		case 0:
			this.addCapability(ZombieCapability.ARMLESS);
			drop = true;
			biteProbability = true;
			break;
		case 1:
			if (rand.nextBoolean())
				drop = true;
			biteProbability = rand.nextBoolean() | rand.nextBoolean();
			break;
		default:
			biteProbability = rand.nextBoolean();
		}
		
		
		switch (flag) {
		case 0:
			return drop;
		case 1:
			return biteProbability;
		}
		return false;	
	}
	
	
	
	/**
	 * Drops all items. Called by PlayTurn based on the results of limbCheck
	 * 
	 * @param map : GameMap for getting the location at which to drop weapons 
	 */
	private void dropItems(GameMap map) {
		Actions dropActions = new Actions();
		for (Item item: this.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)
			drop.execute(this, map);
	}

	

	/**
	 * Enables the Zombie to pick up items
	 * Zombie can only pick up items of weapon type.
	 */
	public Action pickUpItem(GameMap map) {
		for (Item item: map.locationOf(this).getItems())
			if (item.asWeapon() != null)
				return(item.getPickUpAction());
		return null;
	}
	
	/**
	 * Creates corpse if Zombie dies.
	 */
	public Corpse death() {
		return super.death(false);
	}
	
	
	/**
	 * Returns the behaviours of the Zombie.
	 */
	public Behaviour[] getBehaviours() {
		Behaviour [] behaviours = new Behaviour[this.behaviours.length];
		for (int i = 0; i < this.behaviours.length; i++)
			behaviours[i] = this.behaviours[i];
		return behaviours;
	}
	
}
