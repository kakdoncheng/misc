package havocpixel.entities;

import havocpixel.Game;
import havocpixel.util.Utils;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{

	protected Entity owner;
	protected double accuracy;
	protected double xError;
	protected double yError;
	protected double pixelsMoved;
	//protected double range;
	
	public Projectile(Game game, double x, double y, Direction direction, Entity owner, 
			double accuracy, BufferedImage img) {
		super(game, x, y);
		maxHealth=1;
		health=1;
		speed=450;
		name="Basic Projectile";
		faction=Faction.UNALIGNED;
		weapon=Weapon.UNARMED;
		active=true;
		projectile=true;
		dir=$actualDirection(direction);
		this.owner=owner;
		this.accuracy=accuracy;
		if(accuracy>1)
			accuracy=1;
		if(accuracy<0)
			accuracy=0;
		double error=1.0-accuracy;
		xError=game.$randomDouble(-error, error);
		yError=game.$randomDouble(-error, error);
		moveT=0.75;
		pixelsMoved=0;
		this.x=x;
		this.y=y;
		if(dir==Direction.RIGHT){
			this.img=Utils.rotate(img, 90);
		}else if(dir==Direction.DOWN){
			this.img=Utils.rotate(img, 180);
		}else if(dir==Direction.LEFT){
			this.img=Utils.rotate(img, 270);
		}else{
			this.img=img;
		}
		if(dir==Direction.RIGHT){
			bounds=new Rectangle(6,14,20,4);
		}else if(dir==Direction.DOWN){
			bounds=new Rectangle(14,6,4,20);
		}else if(dir==Direction.LEFT){
			bounds=new Rectangle(6,14,20,4);
		}else{
			bounds=new Rectangle(14,6,4,20);
		}
		//range=speed*moveT;
	}
	
	protected boolean hit=false;
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
			if(!canCollideWith(e)||e.equals(owner))
				continue;
			if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))){
				damage(e, owner);
				hit=true;
			}
		}
		if(hit||moveT<0||collision(((int)x+16)/32, ((int)y+16)/32)){
			die();
		}
		//System.out.println(x+" "+" "+y+" "+" "+active);
	}
	
	public void render(Graphics g) {
		if(pixelsMoved>16)
		g.drawImage(img, 
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()),
				this.width,
				this.height,
				null);
	}
	
	public void die() {
		active=false;
	}

}
