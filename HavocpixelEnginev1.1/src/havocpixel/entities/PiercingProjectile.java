package havocpixel.entities;

import havocpixel.Game;
import havocpixel.entities.basic.Explosion;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PiercingProjectile extends Projectile{

	protected int ap;
	protected ArrayList<Entity> entitiesHit;
	
	public PiercingProjectile(Game game, double x, double y,
			Direction direction, Entity owner, double accuracy,
			BufferedImage img, int ap) {
		super(game, x, y, direction, owner, accuracy, img);
		this.ap=ap;
		entitiesHit=new ArrayList<Entity>();
		// TODO Auto-generated constructor stub
	}
	
	public void update(double dt) {
		moveT-=dt;
		double dx=dir.$dx()*speed*dt;
		double dy=dir.$dy()*speed*dt;
		dx+=speed*dt*((pixelsMoved>8)?xError:0);
		dy+=speed*dt*((pixelsMoved>8)?yError:0);
		x+=dx;
		y+=dy;
		pixelsMoved+=(Math.abs(dx)+Math.abs(dy))/2;
		for(Entity e:game.$currentWorld().$entityManager().$entities()){
			if(!canCollideWith(e)||e.equals(owner)||entitiesHit.contains(e))
				continue;
			if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))){
				entitiesHit.add(e);
				damage(e, owner);
				ap++;
			}
		}
		if(ap<0||moveT<0||collision(((int)x+16)/32, ((int)y+16)/32)){
			die();
			game.$currentWorld().$entityManager().addEntity(new Explosion(game, x+16, y+16, owner));
		}
		//System.out.println(x+" "+" "+y+" "+" "+active);
	}
	
	

}
