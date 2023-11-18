package fairytale.entities.projectiles;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Projectile;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.Explosion;
import havocpixel.entities.basic.TrailingSmokeParticle;
import havocpixel.gfx.CoreAssets;

public class ExplosiveFireball extends Projectile{
	
	private double smokeTick, tick;
	
	public ExplosiveFireball(Game game, double x, double y, 
			Direction direction,Entity owner) {
		super(game, x, y, direction, owner, 0.85, CoreAssets.bigFireball);
		name="Explosive Fireball";
		speed=500;
		weapon=Weapon.UNARMED;
		smokeTick=1.0/30;
		tick=0;
	}
	
	public void update(double dt){
		tick+=dt;
		if(tick>=smokeTick){
			tick-=smokeTick;
			game.$currentWorld().$entityManager().addEntity(new TrailingSmokeParticle(game,x,y));
		}
		super.update(dt);
	}
	
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new Explosion(game, x+16, y+16, owner));
	}

}