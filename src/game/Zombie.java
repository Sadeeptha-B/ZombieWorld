package game;

import java.util.Random;
import java.util.ArrayList;

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
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	
	private int armCount = 2;
	private int legCount = 2;
	
	protected Random rand = new Random();
	
	private Behaviour[] behaviours = {
			new PickUpBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10, ZombieCapability.CAPABLE),
			new WanderBehaviour(ZombieCapability.CAPABLE)
	};

	
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		this.addCapability(ZombieCapability.CAPABLE);
	}
	

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		IntrinsicWeapon attack; 
		
		if (rand.nextBoolean()) {
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
		
		if (legCount == 0) {
			this.removeCapability(ZombieCapability.CAPABLE);
		}
			
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
	
	
	public String zombiePhrases() {
		String[] phrases = {"Braaaainnns", "Dieeeee", "Humans yummy", "Arrrghhh", "Gaggghhh"};
		int randomIndex = rand.nextInt(phrases.length);
		return this + " says " + phrases[randomIndex];
	}

	
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
	
}
