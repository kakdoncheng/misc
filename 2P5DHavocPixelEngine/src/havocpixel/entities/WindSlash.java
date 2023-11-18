package havocpixel.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;

public class WindSlash extends ThrownKnife{

	private int totalDmg=400;
	public WindSlash(Handler handler, float x, float y, String owner, int dir) {
		super(handler, x, y,0,0,owner, dir);
		health=totalDmg;
		speed=7;
		if(direction==0){
			bounds=new Rectangle(8,0,16,32);
			xSpeed=0;//1-(int)(Math.random()*3);
			ySpeed=speed;
		}else if(direction==1){
			bounds=new Rectangle(0,8,32,16);
			xSpeed=speed;
			ySpeed=0;
		}else if(direction==2){
			bounds=new Rectangle(8,0,16,32);
			xSpeed=0;
			ySpeed=-speed;
		}else{
			bounds=new Rectangle(0,8,32,16);
			xSpeed=-speed;
			ySpeed=0;
		}
		//particle=true;
		damage=40;
		duration=90;
	}

	public void tick() {
		left.tick();
		right.tick();
		x+=xSpeed;
		y+=ySpeed;
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.label.equals(owner)||e.item)
				continue;
			if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))){
				int dmg=damage+(int)(Math.random()*(totalDmg/10));
				e.damage(dmg,this.owner,false);
				totalDmg-=dmg;
				//this.active=false;
				if(e.immovable)
					continue;
				int k=1;
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
		if(duration>120||totalDmg<0){
			active=false;
		}
		duration++;
	}
	public void render(Graphics g) {
		if(direction==0)
			g.drawImage(Assets.w1,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==1)
			g.drawImage(Assets.w3,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==2)
			g.drawImage(Assets.w0,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==3)
			g.drawImage(Assets.w2,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
	}
	public void die(){
		
	}
}
