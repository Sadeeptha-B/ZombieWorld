package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;

public class MamboMarie extends ZombieActor {

	public MamboMarie(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE, ZombieCapability.MOBILE);
	}
	
	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10, ZombieCapability.MOBILE),
			new WanderBehaviour(ZombieCapability.MOBILE)
	};

	
	public void chant() {
		
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
	public Behaviour[] getBehaviours() {
		return null;
	}

	@Override
	public boolean determineResult() {
		return false;
	}

}
