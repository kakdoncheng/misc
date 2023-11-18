package havocpixel.entities;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;
import havocpixel.util.Utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class InfernoFireball extends ExplodingThrownKnife{

	private BufferedImage img;
	private float l=0;
	private boolean a;
	public InfernoFireball(Handler handler, float x,float y,String owner,int dir,boolean blowUp,boolean hot,float err,int waveDull) {
		super(handler, x,y,0,0,owner,dir);
		damage=75;
		this.label=new String("PROJECTILE:INFERNO_FIREBALL");
		explodes=blowUp;
		img=explodes?Assets.fireball:Assets.fireball0;
		health=1;
		f=err;
		l=f*f/waveDull;
		a=hot;
	}
	public void tick() {
		left.tick();
		right.tick();
		x+=xSpeed;
		y+=ySpeed;
		if(this.direction==0){
			//down
			x+=f;
			y-=l;
		}else if(this.direction==1){
			//right
			y+=f;
			x-=l;
		}else if(this.direction==2){
			//up
			x-=f;
			y+=l;
		}else{
			//left
			y-=f;
			x+=l;
		}
		if(health<1&&!a){
			active=false;
			if(explodes&&!defused){
				defused=true;
				hdlr.$currentWorld().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.owner));
			}
		}
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.label.equals(a?"no_one":owner)||e.label.equals("Fire")||e.label.equals(this.label)||e.label.contains("TRAP")||e.item||e.particle||e.fireTick>0)
				continue;
			if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))){
				e.damage(damage+(int)(Math.random()*26),this.owner,false);
				if(explodes&&!defused){
					defused=true;
					hdlr.$currentWorld().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.owner));
				}
				this.active=false;
				int k=10;
				if(this.direction==0){
					//down
					int ty=(int)(e.y+k+e.bounds.y+e.bounds.height)/Tile.TILE_HEIGHT;
					if(!e.entityCollision(0,k)
							&&!e.collision((int)(e.x+e.bounds.x)/Tile.TILE_WIDTH,ty)
							&&!e.collision((int)(e.x+e.bounds.x+e.bounds.width)/Tile.TILE_WIDTH,ty)
							)
						e.y+=k;
				}else if(this.direction==1){
					//right
					int tx=(int)(e.x+k+e.bounds.x+e.bounds.width)/Tile.TILE_WIDTH;
					if(!e.entityCollision(k,0)
							&&!e.collision(tx,(int)(e.y+e.bounds.y)/Tile.TILE_HEIGHT)
							&&!e.collision(tx,(int)(e.y+e.bounds.y+e.bounds.height)/Tile.TILE_HEIGHT)
							)
						e.x+=k;
				}else if(this.direction==2){
					//up
					int ty=(int)(e.y-k+e.bounds.y)/Tile.TILE_HEIGHT;
					if(!e.entityCollision(0,-k)
							&&!e.collision((int)(e.x+e.bounds.x)/Tile.TILE_WIDTH,ty)
							&&!e.collision((int)(e.x+e.bounds.x+e.bounds.width)/Tile.TILE_WIDTH,ty)
							)
						e.y-=k;
				}else{
					//left
					int tx=(int)(e.x-k+e.bounds.x)/Tile.TILE_WIDTH;
					if(!e.entityCollision(-k,0)
							&&!e.collision(tx,(int)(e.y+e.bounds.y)/Tile.TILE_HEIGHT)
							&&!e.collision(tx,(int)(e.y+e.bounds.y+e.bounds.height)/Tile.TILE_HEIGHT)
							)
						e.x-=k;
				}
				return;
			}
		}
		if(a){
			if(!active)
				this.active=true;
			if(defused)
				defused=false;
		}
		if(duration>120){
			active=false;
		}
		duration++;
		if(duration%(explodes?1:3)==0)
			hdlr.$currentWorld().em.addEntity(new Smoke(hdlr,this.x,this.y));
	}
	public void render(Graphics g) {
		if(direction==0)
			g.drawImage(Utils.rotate(img,270),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==1)
			g.drawImage(Utils.rotate(img,180),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==2)
			g.drawImage(Utils.rotate(img,90),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==3)
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
	}
	
}
