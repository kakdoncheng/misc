package fairytale.entities.projectiles;

import fairytale.entities.particles.FireballImpactEffect;
import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Projectile;
import havocpixel.entities.Weapon;
import havocpixel.gfx.CoreAssets;

public class Fireball extends Projectile{

	public Fireball(Game game, double x, double y, 
			Direction direction,Entity owner) {
		super(game, x, y, direction, owner, 0.85, CoreAssets.fireball);
		speed=400;
		weapon=Weapon.FIREBALL;
		name="Fireball";
	}
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new FireballImpactEffect(game,x,y));
	}

}
