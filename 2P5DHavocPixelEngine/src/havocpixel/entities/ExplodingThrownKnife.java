package havocpixel.entities;

import java.awt.Graphics;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;

public class ExplodingThrownKnife extends ThrownKnifeAlt{

	protected boolean explodes=true,defused=false;
	public ExplodingThrownKnife(Handler handler, float x, float y, int xTarget,
			int yTarget, String owner, int dir) {
		super(handler, x, y, xTarget, yTarget, owner, dir);
		label="PROJECTILE:EXPLODINGTHROWNKNIFE";
		// TODO Auto-generated constructor stub
	}
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
		if(health<1){
			active=false;
			if(explodes&&!defused){
				defused=true;
				hdlr.$currentWorld().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.owner));
			}
		}
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.label.equals(owner)||e.item||e.ghost||e.particle)
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
		if(duration>120){
			if(explodes)
				hdlr.$currentWorld().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.owner));
			active=false;
		}
		duration++;
		if(duration%(explodes?1:3)==0)
			hdlr.$currentWorld().em.addEntity(new Smoke(hdlr,this.x,this.y));
	}
	public void render(Graphics g) {
		if(direction==0)
			g.drawImage(Assets.ekd,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==1)
			g.drawImage(Assets.ekr,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==2)
			g.drawImage(Assets.eku,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==3)
			g.drawImage(Assets.ekl,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
	}
	//hdlr.$world().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16));
	public void die(){
		if(explodes&&!defused){
			defused=true;
			hdlr.$currentWorld().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.owner));
		}
	}
}
