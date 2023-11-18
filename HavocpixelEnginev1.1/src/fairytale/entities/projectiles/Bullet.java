package fairytale.entities.projectiles;

import fairytale.entities.particles.BulletImpactEffect;
import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Projectile;
import havocpixel.entities.Weapon;
import havocpixel.gfx.CoreAssets;

import java.awt.Rectangle;
public class Bullet extends Projectile{

	public Bullet(Game game, double x, double y, 
			Direction direction,Entity owner) {
		super(game, x, y, direction, owner, 0.9, CoreAssets.bullet);
		name="Bullet";
		speed=900;
		strength=0;
		moveT=0.35;
		if(dir==Direction.RIGHT){
			bounds=new Rectangle(0,14,32,4);
		}else if(dir==Direction.DOWN){
			bounds=new Rectangle(14,0,4,32);
		}else if(dir==Direction.LEFT){
			bounds=new Rectangle(0,14,32,4);
		}else{
			bounds=new Rectangle(14,0,4,32);
		}
		weapon=Weapon.BULLET;
	}
	
	public boolean canCollideWith(Entity e){
		if(e.isProjectile()||e.equals(owner))
			return false;
		return super.canCollideWith(e);
	}
	
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new BulletImpactEffect(game,x,y));
	}

}
