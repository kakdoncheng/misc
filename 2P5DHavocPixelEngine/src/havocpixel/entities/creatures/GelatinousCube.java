package havocpixel.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.entities.Entity;
import havocpixel.entities.items.SlimeBall;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;
import havocpixel.util.Utils;

public class GelatinousCube extends Skeleton{
	
	private int size;
	private boolean moving=false,spawned=false;
	private Animation idle,move,death,spawn;

	public GelatinousCube(Handler hdlr, float x, float y, String label0, int size) {
		super(hdlr, x, y, 50, false, label0);
		
		this.size=size;
		this.ally=255;
		this.health=50;
		this.maxHealth=50;
		this.object=true;
		this.corrosive=true;
		
		if(this.size>3){
			this.size=3;
		}else if(this.size<1){
			this.active=false;
		}
		if(this.size==3){
			this.width=64;
			this.height=64;
			this.health=200;
			this.maxHealth=200;
			bounds=new Rectangle(16,20,15+15,21+19);
		}else if(this.size==2){
			this.width=32;
			this.height=32;
			this.health=100;
			this.maxHealth=100;
			bounds=new Rectangle(8,10,15,20);
		}else if(this.size==1){
			this.width=16;
			this.height=16;
			this.health=16;
			this.maxHealth=50;
			bounds=new Rectangle(4,4,8,10);
		}
		
		target="NO_TARGET";
		speed=1;
		cx=x;
		cy=y;
		damage=0;
		this.AC=50;
		this.fleshy=false;
		this.armored=false;
		moveTick=0;
		
		idle=new Animation(hdlr,240,Assets.ci);
		move=new Animation(hdlr,120,Assets.cm);
		death=new Animation(hdlr,120,Assets.cd);
		spawn=new Animation(hdlr,120,Assets.cs);
	}
	int aTick=0,hj=0;
	private boolean dying=false;
	private int deathTick=0;
	int p0=0;
	float alpha=0.75F;
	public void tick() {
		//ADD CORROSIVE DAMAGE -DONE
		if(dying){
			if(health<-120){
				//active=false;
				//spawnBoneGibs();
			}
			deathTick++;
			if(hj<4){
				hj=death.tick();
			}else{
				if(!spawned){
					if(size>1){
						hdlr.$currentWorld().em.addEntity(
								new GelatinousCube(hdlr,(x),(y+(height/2)),"GELATINOUS_CUBE_"+Math.random(),size-1)
						);hdlr.$currentWorld().em.addEntity(
								new GelatinousCube(hdlr,(x+width/2),(y+(height/2)),"GELATINOUS_CUBE_"+Math.random(),size-1)//-(int)(Math.random()*2)
						);
					}
					spawned=true;
				}
			}
			if(deathTick>360){
				alpha-=0.05F;
				if(alpha<0.05)
					active=false;
			}
			return;
		}
		if(hdlr.$game().$tick()%5==0){
			int k=1;
			int cx=(int)(this.x+(width/2)),cy=(int)(this.y+(height/2));
			for(Entity e:hdlr.$currentWorld().$entityManager().$entities()){
				if(e.equals(this)||e.item||e.particle||e.flora||e.corrosive)
					continue;
				if(this.$collisionBounds(0,3).intersects(e.$collisionBounds(0,0))||
						this.$collisionBounds(0,-3).intersects(e.$collisionBounds(0,0))||
						this.$collisionBounds(-3,0).intersects(e.$collisionBounds(0,0))||
						this.$collisionBounds(3,0).intersects(e.$collisionBounds(0,0))){
					e.damage(damage+(25*size)+(int)(Math.random()*25*size),this.label,false);
					if(e.immovable)
						continue;
					if(e.y+16>cy){
						//down
						int ty=(int)(e.y+k+e.bounds.y+e.bounds.height)/Tile.TILE_HEIGHT;
						if(!e.entityCollision(0,k)
								&&!e.collision((int)(e.x+e.bounds.x)/Tile.TILE_WIDTH,ty)
								&&!e.collision((int)(e.x+e.bounds.x+e.bounds.width)/Tile.TILE_WIDTH,ty)
								)
							e.y+=k;
					}
					if(e.x+16>cx){
						//right
						int tx=(int)(e.x+k+e.bounds.x+e.bounds.width)/Tile.TILE_WIDTH;
						if(!e.entityCollision(k,0)
								&&!e.collision(tx,(int)(e.y+e.bounds.y)/Tile.TILE_HEIGHT)
								&&!e.collision(tx,(int)(e.y+e.bounds.y+e.bounds.height)/Tile.TILE_HEIGHT)
								)
							e.x+=k;
					}
					if(e.y+16<cy){
						//up
						int ty=(int)(e.y-k+e.bounds.y)/Tile.TILE_HEIGHT;
						if(!e.entityCollision(0,-k)
								&&!e.collision((int)(e.x+e.bounds.x)/Tile.TILE_WIDTH,ty)
								&&!e.collision((int)(e.x+e.bounds.x+e.bounds.width)/Tile.TILE_WIDTH,ty)
								)
							e.y-=k;
					}
					if(e.x+16<cx){
						//left
						int tx=(int)(e.x-k+e.bounds.x)/Tile.TILE_WIDTH;
						if(!e.entityCollision(-k,0)
								&&!e.collision(tx,(int)(e.y+e.bounds.y)/Tile.TILE_HEIGHT)
								&&!e.collision(tx,(int)(e.y+e.bounds.y+e.bounds.height)/Tile.TILE_HEIGHT)
								)
							e.x-=k;
					}
				}
				
				
			}
		}
		if(spawning){
			if(spawn.tick()==7){
				spawning=false;
			}
			x=cx;
			y=cy;
			//health=100;
			return;
		}
		
		
		stunTick++;
		aTick++;
		if(aTick>2){
			if(!attacking){
				if(health<maxHealth)
					health++;
				aTick=0;
			}
		}
		if(hurt){
			idle.reset();
			aTick=0;
			attacking=false;
			throwProjectile=false;
			grenade=false;
			if(damageTick<DL){
				damageTick++;
				return;
			}else{
				damageTick=0;
				hurt=false;
			}
		}
		idle.tick();
		
		if (moveTick<1&&!moving) {
			moveTick=rndInt(100);
			xMove=0;
			yMove=0;
			p0=rndInt(5);
		} else {
			int j=-1;
			if(p0>0)
				j=move.tick();
			if(j>4&&j<11){
				moving=true;
				if (p0==1) {
					xMove=speed;
				} else if (p0==2) {
					xMove=-speed;
				} else if (p0==3) {
					yMove=speed;
				} else if (p0==4) {
					yMove=-speed;
				}
			}else{
				moving=false;
				xMove=0;
				yMove=0;
			}
			moveTick--;
		}
		if(!isStunned())
			move();
	}
	public void render(Graphics g) {
		if(dying){
			Utils.drawTranslucentImage(death.$currentFrame(),
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),alpha,
					width,
					height,g);
			return;
		}
		if(spawning){
			Utils.drawTranslucentImage(spawn.$currentFrame(),
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),0.75F,
					width,
					height,g);
			return;
		}
		if(hurt){
			Utils.drawTranslucentImage(moving?move.$currentFrame():idle.$currentFrame(),
					(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),0.75F,width,height,g);
			return;
		}
		Utils.drawTranslucentImage(moving?move.$currentFrame():idle.$currentFrame(),
				(int)(x-hdlr.$camera().$xOffset()),
				(int)(y-hdlr.$camera().$yOffset()),0.75F,
				width,
				height,g);
	}
	public void die(){
		if(!dying){
			particle=true;
			item=true;
			if(size<2&&Math.random()<0.5F)
				hdlr.$currentWorld().em.addEntity(new SlimeBall(hdlr,x,y,1));
			//hdlr.$currentWorld().em.addEntity(new DecapitatedBunnyHead(hdlr,this.x-10,this.y+4));
		}
		this.ally=256;
		dying=true;
		this.label=new String("DEAD_CORPSE");
		active=true;
	}

}
