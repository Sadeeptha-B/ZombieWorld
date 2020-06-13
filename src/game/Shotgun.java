package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Shotgun extends RangedWeapon {
	
	private static final int MAX_AMMO = 5;
	private ReloadCapability ammoCapability = ReloadCapability.SHOTGUN;
	
	public Shotgun() {
		super("Shotgun", '~', 35);
	}

	@Override
	protected int getMaxAmmo() {
		return MAX_AMMO;
	}
	
	public ReloadCapability getAmmoCapability() {
		return ammoCapability;
	}
	
	@Override
	public ShotgunScan weaponScan() {
		return new ShotgunScan();
	}

	@Override
	public String shoot(Actor target, GameMap map, Location actorLocation) {
		Location targetLocation = map.locationOf(target);
		ArrayList<Actor> collateral = new ArrayList<Actor>();
		
		int maxX = actorLocation.x();
		int maxY = actorLocation.y();
		int minX = actorLocation.x();
		int minY = actorLocation.y();
		if (maxX > targetLocation.x())
			minX -= 3;
		else
			maxX += 3;
		if (maxY > targetLocation.y())
			minY -= 3;
		else
			maxY += 3;
		
		for (int x = minX; minX < maxX; x++) {
			for(int y = minY; minY < maxY; y++) {
				if(map.at(x, y).containsAnActor() && map.at(x, y) != targetLocation)
					collateral.add(map.getActorAt(map.at(x, y)));
			}
		}
			
		
		if (!(rand.nextInt(4) == 0))
			target.hurt(this.damage());
		
		for (Actor human: collateral) {
			if ((rand.nextInt(3) == 0))
				human.hurt(this.damage());
		}
		return null;
	}

	public Action subMenuActions(Actor actor, Actor target) {
		return new ShootTargetAction(this, target);
	}

	
}
