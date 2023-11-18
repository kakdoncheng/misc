package havocpixel.entities;

import havocpixel.entities.creatures.ThrownKnifeGib;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;

import java.awt.Graphics;
import java.awt.Rectangle;

public class ThrownKnife extends Entity{
	protected int duration=0,dx,dy;
	protected Animation left,right;
	protected float speed=6,xSpeed,ySpeed;
	protected String owner;

	public ThrownKnife(Handler handler, float x, float y,int xTarget,int yTarget,String owner,int dir) {
		super(handler, x, y, 32, 32, 1);
		bounds=new Rectangle(8,8,16,16);
		health=1;
		damage=35;
		label="PROJECTILE:THROWNKNIFE";
		this.owner=owner;
		this.direction=dir;
		left=new Animation(hdlr,30,Assets.pr);
		right=new Animation(hdlr,30,Assets.pl);
		this.object=true;
		
		if(direction==0){
			xSpeed=0;//1-(int)(Math.random()*3);
			ySpeed=speed;
		}else if(direction==1){
			xSpeed=speed;
			ySpeed=0;
		}else if(direction==2){
			xSpeed=0;
			ySpeed=-speed;
		}else{
			xSpeed=-speed;
			ySpeed=0;
		}
		
		//int tx=(int)x;
		//int ty=(int)y;
		dx=xTarget;
		dy=yTarget;
		/*
		if(dy-ty==0){
			xSpeed=speed;
			ySpeed=0;
			return;
		}
		if(dx-tx==0){
			xSpeed=0;
			ySpeed=speed;
			return;
		}
		//range 360
		if(Math.abs(dx-tx)>Math.abs(dy-ty)){
			if(dx-tx<0){
				xSpeed=-speed;
				if(dy-ty<0){
					ySpeed=(ty-dy)/((360-tx)/speed);
				}else{
					ySpeed=(dy-ty)/((360-tx)/speed);
				}
			}else{
				xSpeed=speed;
				if(dy-ty<0){
					ySpeed=(ty-dy)/((tx-360)/speed);
				}else{
					ySpeed=(dy-ty)/((tx-360)/speed);
				}
			}
			
			return;
		}
		if(Math.abs(dx-tx)<Math.abs(dy-ty)){
			if(dy-ty<0){
				ySpeed=-speed;
				if(dx-tx<0){
					xSpeed=(tx-dx)/((360-ty)/speed);
				}else{
					ySpeed=(dy-ty)/((360-ty)/speed);
				}
			}else{
				ySpeed=speed;
				if(dx-tx<0){
					xSpeed=(tx-dx)/((ty-360)/speed);
				}else{
					xSpeed=(dx-tx)/((ty-360)/speed);
				}
			}
			return;
		}*/
		;
	}
	protected float f=((float)Math.random()-0.919F),error=Math.abs(f)>0.8F?0:f;
	protected boolean eUP=Math.random()<0.5F,eLEFT=Math.random()<0.5F;
	@Override
	public void tick() {
		left.tick();
		right.tick();
		x+=xSpeed;
		y+=ySpeed;
		if(eUP){
			y-=f;
		}else{
			y+=f;
		}
		if(eLEFT){
			x-=f;
		}else{
			x+=f;
		}
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.label.equals(owner)||e.item||e.ghost||e.particle)
				continue;
			if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))){
				e.damage(damage+(int)(Math.random()*26),this.owner,false);
				this.active=false;
				if(e.immovable)
					continue;
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
		if(duration>120){
			active=false;
		}
		duration++;
	}

	@Override
	public void render(Graphics g) {
		if(direction==3||direction==2)
			g.drawImage(left.$currentFrame(),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else
			g.drawImage(right.$currentFrame(),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
	}

	@Override
	public void die() {
		hdlr.$currentWorld().em.addEntity(new ThrownKnifeGib(hdlr,this.x,this.y));
	}
}
