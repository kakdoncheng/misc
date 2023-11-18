package fairytale.entities.projectiles;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Projectile;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.Explosion;
import havocpixel.gfx.CoreAssets;

public class PlasmaBolt extends Projectile{
	public PlasmaBolt(Game game, double x, double y, 
			Direction direction,Entity owner) {
		super(game, x, y, direction, owner, 0.9, CoreAssets.bolt);
		speed=800;
		weapon=Weapon.UNARMED;
		moveT=0.5;
		name="Plasma Bolt";
	}
	
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new Explosion(game, x+16, y+16, owner));
	}

}
