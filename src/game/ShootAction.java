package game;

import java.util.ArrayList;

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
	private Action nextAction;
	
	public ShootAction(RangedWeapon weapon) {
		this.weapon = weapon;
	}
	
	
	@Override
	public String execute(Actor actor, GameMap map) {

		Scan weaponScan = weapon.weaponScan();
		
		weaponScan.scan(actor, map.locationOf(actor), map);
		ArrayList<Actor> targets = weaponScan.getTargets();
		
		Actions actions = new Actions();
		
		for (Actor target: targets) {
			actions.add(new SelectTargetAction(weapon,target));
		}
		actions.add(new GoToMainMenuAction());
		
		targetDisplay.println("-------------------------------");
		Action action = menu.showMenu(actor, actions, targetDisplay);
		String result = action.execute(actor, map);
		nextAction = action.getNextAction();
		
		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return "Player shoots with " + weapon;
	}
	
	public Action getNextAction() {
		return nextAction;
	}


}
