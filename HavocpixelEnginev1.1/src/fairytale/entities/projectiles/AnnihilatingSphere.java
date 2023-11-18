package fairytale.entities.projectiles;

import fairytale.entities.particles.BigSmokeEffect;
import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.PiercingProjectile;
import havocpixel.entities.basic.Explosion;
import havocpixel.gfx.CoreAssets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//known bugs:
//will still die when hit by another projectile or explosion

public class AnnihilatingSphere extends PiercingProjectile{

	private double smokeTick, tick;
	private int dtick;
	
	public AnnihilatingSphere(Game game, double x, double y,
			Direction direction, Entity owner) {
		super(game, x, y, direction, owner, 1.0, CoreAssets.defaultTile[2], 666);
		bounds=new Rectangle(0,0,32,32);
		//maxHealth=666666;
		//health=maxHealth;
		speed=900;
		moveT=2.0;
		this.invulnerable=true;
		this.particle=true;
		smokeTick=1.0/60;
		tick=0;
		dtick=0;
	}
	
	public void update(double dt) {
		tick+=dt;
		dtick++;
		if(tick>=smokeTick){
			tick-=smokeTick;
			game.$currentWorld().$entityManager().addEntity(new BigSmokeEffect(game,x+game.$randomInt(-3,4),y+game.$randomInt(-3,4)));
		}
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
				game.$currentWorld().$entityManager().addEntity(new Explosion(game, e.$x()+16, e.$y()+16, owner));
			}
		}
		if(moveT<0){
			die();
		}
		//System.out.println(x+" "+" "+y+" "+" "+active);
	}
	
	public void render(Graphics g){
		Color c=g.getColor();
		//g.setColor(Color.YELLOW);
		//g.fillOval((int)(this.x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()),this.width, this.height);
		g.setColor((dtick/2)%2!=0?Color.BLACK:Color.WHITE);
		g.fillOval((int)(this.x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()),this.width, this.height);
		//g.fillOval((int)(this.x-game.$camera().$xOffset())+1,(int)(this.y-game.$camera().$yOffset())+1,this.width-2, this.height-2);
		g.setColor(c);
	}
	
	public void die(){
		super.die();
	}

}
