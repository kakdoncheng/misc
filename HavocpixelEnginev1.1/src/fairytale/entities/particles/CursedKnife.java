package fairytale.entities.particles;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Weapon;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Graphics;
import java.awt.Rectangle;

//base code for homing projectile?

public class CursedKnife extends Entity{

	private Entity owner;
	private double pixelsMoved, rotateT, rotateThres;

	public CursedKnife(Game game, double x, double y, Entity target, Entity owner) {
		super(game, x, y);
		this.bounds=new Rectangle(8,8,16,16);
		active=true;
		particle=true;
		name="Cursed Knife";
		speed=400;
		strength=0;
		weapon=Weapon.THROWING_KNIFE;
		rotateT=0;
		rotateThres=1/20;
		img=CoreAssets.cursedKnife;
		//img=Utils.rotate(img, 90);
		
		this.swornTarget=target;
		this.currentTarget=swornTarget;
		this.owner=owner;
	}

	public void update(double dt) {
		if(pixelsMoved>16){
			//game.$currentWorld().$entityManager().addEntity(new FloatingString(game, x+16, y+16, game.$randomString(game.$randomInt(1,3)), Color.BLACK));
		}
		rotateT+=dt;
		if(rotateT>rotateThres){
			rotateT=0;
			img=Utils.rotate(img, 90);
		}
		dir=this.$directionTowards(currentTarget);
		if(this.$actualDirection(dir)==Direction.UP){
			img=CoreAssets.cursedKnife;
		}else if(this.$actualDirection(dir)==Direction.LEFT){
			img=Utils.rotate(CoreAssets.cursedKnife, 270);
		}else if(this.$actualDirection(dir)==Direction.DOWN){
			img=Utils.rotate(CoreAssets.cursedKnife, 180);
		}else if(this.$actualDirection(dir)==Direction.RIGHT){
			img=Utils.rotate(CoreAssets.cursedKnife, 90);
		}
		double dx=dir.$dx()*speed*dt;
		double dy=dir.$dy()*speed*dt;
		int tx=(int)this.$x();
		int ty=(int)this.$y();
		int ex=(int)currentTarget.$x();
		int ey=(int)currentTarget.$y();
		if(tx<ex){
			if(this.$x()+dx>currentTarget.$x()){
				dx=currentTarget.$x()-this.$x();
			}
		}else if(tx>ex){
			if(this.$x()+dx<currentTarget.$x()){
				dx=currentTarget.$x()-this.$x();
			}
		}else if(tx==ex){
			dx=0;
		}
		if(ty<ey){
			if(this.$y()+dy>currentTarget.$y()){
				dy=currentTarget.$y()-this.$y();
			}
		}else if(ty>ey){
			if(this.$y()+dy<currentTarget.$y()){
				dy=currentTarget.$y()-this.$y();
			}
		}else if(ty==ey){
			dy=0;
		}
		this.x+=dx;
		this.y+=dy;
		pixelsMoved+=(Math.abs(dx)+Math.abs(dy))/2;
		if(this.$collisionBounds(0,0).intersects(currentTarget.$collisionBounds(0,0))){
			//strength=currentTarget.$health()/2;
			damage(currentTarget, owner);
			die();
		}
	}

	public void render(Graphics g) {
		//g.fillRect((int)x,(int)y,1,1);
		//Color b=g.getColor();
		//g.setColor(Color.BLACK);
		//g.drawRect((int)(x+bounds.x+16-game.$camera().$xOffset()),
		//		(int)(y+bounds.y+16-game.$camera().$yOffset()),
		//		1,1);
		//g.setColor(b);
		if(pixelsMoved>16){
			g.drawImage(img, 
					(int)(x-game.$camera().$xOffset()),
					(int)(y-game.$camera().$yOffset()),
					this.width,
					this.height,
					null);
		}
		
	}

	protected void die() {
		active=false;
	}

}
