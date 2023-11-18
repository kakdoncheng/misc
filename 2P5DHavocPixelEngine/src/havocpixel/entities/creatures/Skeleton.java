package havocpixel.entities.creatures;

import havocpixel.entities.PoisonedThrownKnife;
import havocpixel.entities.ThrownKnifeAlt;
import havocpixel.entities.items.BowlOfRice;
import havocpixel.entities.items.Coin;
import havocpixel.entities.items.Grenade;
import havocpixel.entities.items.HotDog;
import havocpixel.entities.items.ThrowingKnife;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Skeleton extends Creature{
	protected Animation su,sl,sd,sr,asu,asl,asd,asr,sui,sli,sdi,sri;
	protected Animation su0,sl0,sd0,sr0,asu0,asl0,asd0,asr0,sui0,sli0,sdi0,sri0;
	protected BufferedImage hU,hL,hD,hR,shU,shL,shD,shR;
	protected boolean armored=true,gibbed=false;
	protected Animation spawn;
	protected boolean spawning=true;
	protected float cx,cy;
	//use this as base class for new enemy entities with melee attacks so no messy copypasta needed/
	public Skeleton(Handler hdlr,float x, float y,int health,boolean angry,String label0) {
		super(hdlr, x, y
				, Creature.DEFAULT_WIDTH
				, Creature.DEFAULT_HEIGHT
				);
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		label=label0;
		target="PLAYER";
		speed=1;
		damage=25;
		cx=x;
		cy=y;
		this.health=health;
		maxHealth=200;
		this.angry=angry;
		this.AC=50;
		this.ally=0;
		
		su=new Animation(hdlr,120,Assets.sUp);
		sl=new Animation(hdlr,120,Assets.sLeft);
		sd=new Animation(hdlr,120,Assets.sDown);
		sr=new Animation(hdlr,120,Assets.sRight);
		sui=new Animation(hdlr,240,Assets.siU);
		sli=new Animation(hdlr,240,Assets.siL);
		sdi=new Animation(hdlr,240,Assets.siD);
		sri=new Animation(hdlr,240,Assets.siR);
		asu=new Animation(hdlr,90,Assets.AsUp);
		asl=new Animation(hdlr,90,Assets.AsLeft);
		asd=new Animation(hdlr,90,Assets.AsDown);
		asr=new Animation(hdlr,90,Assets.AsRight);
		
		su0=new Animation(hdlr,120,Assets.sUp0);
		sl0=new Animation(hdlr,120,Assets.sLeft0);
		sd0=new Animation(hdlr,120,Assets.sDown0);
		sr0=new Animation(hdlr,120,Assets.sRight0);
		sui0=new Animation(hdlr,240,Assets.siU0);
		sli0=new Animation(hdlr,240,Assets.siL0);
		sdi0=new Animation(hdlr,240,Assets.siD0);
		sri0=new Animation(hdlr,240,Assets.siR0);
		asu0=new Animation(hdlr,90,Assets.AsUp0);
		asl0=new Animation(hdlr,90,Assets.AsLeft0);
		asd0=new Animation(hdlr,90,Assets.AsDown0);
		asr0=new Animation(hdlr,90,Assets.AsRight0);
		
		spawn=new Animation(hdlr,120,Assets.emerge0);
		shU=Assets.siU[1];
		shL=Assets.siL[1];
		shD=Assets.siD[1];
		shR=Assets.siR[1];
		hU=Assets.siU0[1];
		hL=Assets.siL0[1];
		hD=Assets.siD0[1];
		hR=Assets.siR0[1];
	}
	protected Random r = new Random();
	protected int moveTick=0;
	protected int rndInt(int k) {
		return r.nextInt(k);
	}
	int aTick=0;
	protected boolean throwProjectile=false,grenade=false;
	public void tick() {
		lookForTarget();
		stunTick++;
		if(!targetIsActive())
			this.target=new String("PLAYER");
		if(health<121){
			if(armored)
				hdlr.$currentWorld().em.addEntity(new HelmetGib(hdlr,x,y));
			armored=false;
			speed=2;
			damage=35;
			this.AC=0;
		}
		if(spawning){
			if(spawn.tick()==15){
				spawning=false;
			}
			x=cx;
			y=cy;
			//health=100;
			return;
		}
		aTick++;
		if(aTick>5){
			if(!attacking){
				if(health<maxHealth)
					health++;
				aTick=0;
			}
		}
		if(hurt){
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
		//IMPLEMENT OFFENSIVE AI
		if(angry&&!attacking&&!isStunned()){
			if(Math.random()>0.98F){
				Rectangle ar;
				if(this.direction==0){
					//down
					ar=new Rectangle(32,160);
					ar.x=(int)x;
					ar.y=(int)y+height;
				}else if(this.direction==1){
					//right
					ar=new Rectangle(160,32);
					ar.x=(int)x+width;
					ar.y=(int)y;
				}else if(this.direction==2){
					//up
					ar=new Rectangle(32,160);
					ar.x=(int)x;
					ar.y=(int)y-160;
				}else{
					//left
					ar=new Rectangle(160,32);
					ar.x=(int)x-160;
					ar.y=(int)y;
				}
				for(havocpixel.entities.Entity u:hdlr.$currentWorld().$entityManager().$entities()){
					if(u.equals(this)||u.particle)
						continue;
					if(u.$collisionBounds(0,0).intersects(ar)&&(u.label.equals(this.target)||(!targetFound&&u.ally==1))){
						attacking=true;
						throwProjectile=true;
						//if(Math.random()<0.1F)
							//grenade=true;
						return;
					}
				}
			}
			if(Math.random()>0.89F){
				//simple offensive AI idea
				//bigger sight/hearing rectangle;
				//if player is within the rectangle go to the player's coordinate
				
				//check if the player is within any of the directions;
				//if player intersects the rectangle for direction 0,
				//	turn to direction 0&launch an attack;
				int u1=20;
				int thr=0,lim=20;
				Rectangle ar0=new Rectangle(u1,u1),//down
						ar1=new Rectangle(u1,u1),//right
						ar2=new Rectangle(u1,u1),//up
						ar3=new Rectangle(u1,u1),//left
						cb=$collisionBounds(0,0);
				//down
				ar0.x=cb.x+cb.width/2-u1/2;
				ar0.y=cb.y+cb.height;
				//right
				ar1.x=cb.x+cb.width;
				ar1.y=cb.y-cb.height/2+u1/2;
				//up
				ar2.x=cb.x+cb.width/2-u1/2;
				ar2.y=cb.y-u1;
				//left
				ar3.x=cb.x-u1;
				ar3.y=cb.y-cb.height/2+u1/2;
				for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
					if(e.equals(this)||e.particle)
						continue;
					if(e.$collisionBounds(0,0).intersects(ar0)){
						if(e.label.equals(this.target)||(!targetFound&&e.ally==1)){
							this.direction=0;
							if(rndInt(lim)>thr)
								attacking=true;
							return;
						}
					}else if(e.$collisionBounds(0,0).intersects(ar1)){
						if(e.label.equals(this.target)||(!targetFound&&e.ally==1)){
							this.direction=1;
							if(rndInt(lim)>thr)
								attacking=true;
							return;
						}
					}else if(e.$collisionBounds(0,0).intersects(ar2)){
						if(e.label.equals(this.target)||(!targetFound&&e.ally==1)){
							this.direction=2;
							if(rndInt(lim)>thr)
								attacking=true;
							return;
						}
					}else if(e.$collisionBounds(0,0).intersects(ar3)){
						if(e.label.equals(this.target)||(!targetFound&&e.ally==1)){
							this.direction=3;
							if(rndInt(10)>thr)
								attacking=true;
							return;
						}
					}
						
				}
			}
			
		}
		if(attack&&!isStunned()){
			aTick=0;
			if(throwProjectile){
				meleeCooldown=true;
				if(grenade){
					if(direction==0){
						//down
						hdlr.$currentWorld().em.addEntity(
								new LitGrenade(hdlr,this.x,this.y+32,label,this.direction));
					}else if(direction==1){
						//right
						hdlr.$currentWorld().em.addEntity(
								new LitGrenade(hdlr,this.x+32,this.y,label,this.direction));
					}else if(direction==2){
						//up
						hdlr.$currentWorld().em.addEntity(
								new LitGrenade(hdlr,this.x,this.y-32,label,this.direction));
					}else{
						//left
						hdlr.$currentWorld().em.addEntity(
								new LitGrenade(hdlr,this.x-32,this.y,label,this.direction));
					}
				}else{
					hdlr.$currentWorld().em.addEntity(
							(Math.random()<0.79)?new ThrownKnifeAlt(hdlr,this.x,this.y,0,0,label,this.direction):
								new PoisonedThrownKnife(hdlr,this.x,this.y,label,this.direction));
				}
				throwProjectile=false;
				grenade=false;
			}else{
				attack();
			}
			attack=false;
		}
		if(armored){
			su.tick();
			sl.tick();
			sd.tick();
			sr.tick();
			sui.tick();
			sli.tick();
			sdi.tick();
			sri.tick();
		}else{
			su0.tick();
			sl0.tick();
			sd0.tick();
			sr0.tick();
			sui0.tick();
			sli0.tick();
			sdi0.tick();
			sri0.tick();
		}
		
		if(this.attacking){
			asu.tick();
			asl.tick();
			asd.tick();
			asu0.tick();
			asl0.tick();
			asd0.tick();
			asr0.tick();
			if(asr.tick()==2){
				attack=true;
			}
			if(asr.tick()==0){
				attacking=false;
				meleeCooldown=false;
			}
			
		}else{
			//TEMPORARY FLAG IMPLEMENT AI
			//checks to see whether target is in earshot; if so, will stupidly chase down target;
			if(angry&&!attacking){
				Rectangle ar1=new Rectangle((int)(this.x-224),(int)(this.y-224),480,480);
				for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
					if(e.equals(this)||e.particle)
						continue;
					if(e.$collisionBounds(0,0).intersects(ar1)){
						if(e.label.equals(this.target)||(!targetFound&&e.ally==1)){
							int ex=(int)e.x,ey=(int)e.y,tx=(int)this.x,ty=(int)this.y;
							xMove=0;
							yMove=0;
							if(ex>tx){
								if(ex-tx==1)
									xMove=1;
								else
									xMove=speed;
							}else if(ex<tx){
								if(ex-tx==-1)
									xMove=-1;
								else
									xMove=-speed;
							}
							if(ey>(int)ty){
								if(ey-ty==1)
									yMove=1;
								else
									yMove=speed;
							}else if(ey<ty){
								if(ey-ty==-1)
									yMove=-1;
								else
									yMove=-speed;
							}
							if(!isStunned())
								move();
							return;
						}
					}
				}
			}
			if (moveTick<1) {
				moveTick=rndInt(100);
				xMove=0;
				yMove=0;
				int p=rndInt(5);
				if (p==1) {
					xMove=speed;
				} else if (p==2) {
					xMove=-speed;
				} else if (p==3) {
					yMove=speed;
				} else if (p==4) {
					yMove=-speed;
				}
			} else {
				moveTick--;
			}
			if(!isStunned())
				move();
		}
		//hdlr.$camera().centerOnEntity(this);
	}
	public void render(Graphics g) {
		renderHP(g);
		if(spawning){
			g.drawImage(spawn.$currentFrame(),
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
			return;
		}
		if(hurt){
			if(this.direction==0){
				//down
				g.drawImage(armored?shD:hD,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
			}else if(this.direction==1){
				//right
				g.drawImage(armored?shR:hR,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
			}else if(this.direction==2){
				//up
				g.drawImage(armored?shU:hU,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
			}else{
				//left
				g.drawImage(armored?shL:hL,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
			}
			return;
		}
		if(attacking){
			if(this.direction==0){
				g.drawImage(currentAnimation(),
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height*2,null);
			}else if(this.direction==1){
				g.drawImage(currentAnimation(),
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width*2,
						height,null);
			}else if(this.direction==2){
				g.drawImage(currentAnimation(),
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-height-hdlr.$camera().$yOffset()),
						width,
						height*2,null);
			}else{
				g.drawImage(currentAnimation(),
						(int)(x-width-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width*2,
						height,null);
			}
		}else{
			g.drawImage(currentAnimation(),
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
		}
	}
	
	@SuppressWarnings("unused")
	private void showBounds(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)(x+bounds.x-hdlr.$camera().$xOffset()),(int)(y+bounds.y-hdlr.$camera().$yOffset()),bounds.width, bounds.height);
	}
	protected BufferedImage currentAnimation() {
		if(attacking){
			if(this.direction==0)
				return armored?asd.$currentFrame():asd0.$currentFrame();
			else if(this.direction==1)
				return armored?asr.$currentFrame():asr0.$currentFrame();
			else if(this.direction==2)
				return armored?asu.$currentFrame():asu0.$currentFrame();
			else
				return armored?asl.$currentFrame():asl0.$currentFrame();
		}else{
			if(xMove<0) {
				return armored?sl.$currentFrame():sl0.$currentFrame();
			} else if (xMove>0) {
				return armored?sr.$currentFrame():sr0.$currentFrame();
			} else if (yMove<0) {
				return armored?su.$currentFrame():su0.$currentFrame();
			} else if (yMove>0) {
				return armored?sd.$currentFrame():sd0.$currentFrame();
			} else {
				if(this.direction==0)
					return armored?sdi.$currentFrame():sdi0.$currentFrame();
				else if(this.direction==1)
					return armored?sri.$currentFrame():sri0.$currentFrame();
				else if(this.direction==2)
					return armored?sui.$currentFrame():sui0.$currentFrame();
				else
					return armored?sli.$currentFrame():sli0.$currentFrame();
			}
		}
	}
	protected void attack(){
		if(meleeCooldown)
			return;
		meleeCooldown=true;
		int u=20;
		Rectangle ar=new Rectangle(u,u),cb=$collisionBounds(0,0);
		if(this.direction==0){
			//down
			ar.x=cb.x+cb.width/2-u/2;
			ar.y=cb.y+cb.height;
		}else if(this.direction==1){
			//right
			ar.x=cb.x+cb.width;
			ar.y=cb.y-cb.height/2+u/2;
		}else if(this.direction==2){
			//up
			ar.x=cb.x+cb.width/2-u/2;
			ar.y=cb.y-u;
		}else{
			//left
			ar.x=cb.x-u;
			ar.y=cb.y-cb.height/2+u/2;
		}
		int k=10;
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.particle||e.flora)
				continue;
			if(e.$collisionBounds(0,0).intersects(ar)){
				//??CHECK EVERY COORD VAR WILL BELONG TO Entity e
				e.damage(damage+(int)(Math.random()*30),this.label,false);
				//if(!e.label.equals("PLAYER")){
				if(e.immovable)
					continue;
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
				//}
				//return;
			}
				
		}
			
	}
	public void die() {
		hdlr.$currentWorld().em.addEntity(new Coin(hdlr,this.x,this.y,(int)(Math.random()*21)-5));
		if(Math.random()<0.99F){
			hdlr.$currentWorld().em.addEntity(new ThrowingKnife(hdlr,this.x,this.y,(int)(Math.random()*10)-2));
			hdlr.$currentWorld().em.addEntity(new Grenade(hdlr,this.x,this.y,(int)(Math.random()*4)-2));
		}else{
			if(Math.random()<0.50F)
				hdlr.$currentWorld().em.addEntity(new HotDog(hdlr,this.x,this.y,1));
			else
				hdlr.$currentWorld().em.addEntity(new BowlOfRice(hdlr,this.x,this.y,1));
		}
		if(!gibbed){
			spawnGibs();
			gibbed=true;
		}
		
	}

}
