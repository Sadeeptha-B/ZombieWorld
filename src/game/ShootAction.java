package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

public class ShootAction extends Action {
	
	private RangedWeapon weapon;
	protected Menu menu = new Menu();
	protected Display targetDisplay = new Display();
	
	
	public ShootAction(RangedWeapon weapon) {
		this.weapon = weapon;
	}
	
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Actor[] targets = {new Zombie("Lol"), new Zombie("Lmao")};
		Actions actions = new Actions();
		
		for (Actor target: targets) {
			actions.add(new SelectTargetAction(weapon,target));
		}
		actions.add(new GoToMainMenuAction());
		
		targetDisplay.println("-------------------------------");
		Action action = menu.showMenu(actor, actions, targetDisplay);
		
		String result = action.execute(actor, map);
		
		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return "Player shoots with " + weapon;
	}

}
