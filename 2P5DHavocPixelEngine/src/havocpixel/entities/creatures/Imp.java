package havocpixel.entities.creatures;

import havocpixel.entities.Fireball;
import havocpixel.entities.items.Coin;
import havocpixel.entities.items.RottenFlesh;
import havocpixel.entities.items.ScrapMetal;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Imp extends Skeleton{

	protected Animation death,spawn,gibbed;
	protected boolean gibb=false;
	//private boolean spawning=true;
	//private float cx,cy;
	protected boolean dying=false;
	protected int deathTick=0,gibTick=0,gibLim=(int)(Math.random()*61);
	public Imp(Handler hdlr, float x, float y,String label0) {
		super(hdlr, x, y,200,true, label0);
		this.maxHealth=200;
		this.ally=0;
		
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		target="PLAYER";
		speed=2;
		cx=x;
		cy=y;
		damage=55;
		this.AC=25;
		this.fleshy=true;
		this.armored=false;
		gibbed=new Animation(hdlr,180,Math.random()>0.49F?Assets.gore:Assets.gore0);
		death=new Animation(hdlr,200,Assets.dDeath);
		spawn=new Animation(hdlr,120,Assets.dSpawn);
		
		su=new Animation(hdlr,120,Assets.dUp);
		sl=new Animation(hdlr,120,Assets.dLeft);
		sd=new Animation(hdlr,120,Assets.dDown);
		sr=new Animation(hdlr,120,Assets.dRight);
		sui=new Animation(hdlr,240,Assets.diU);
		sli=new Animation(hdlr,240,Assets.diL);
		sdi=new Animation(hdlr,240,Assets.diD);
		sri=new Animation(hdlr,240,Assets.diR);
		
		asu=new Animation(hdlr,70,Assets.AdUp);
		asl=new Animation(hdlr,70,Assets.AdLeft);
		asd=new Animation(hdlr,70,Assets.AdDown);
		asr=new Animation(hdlr,70,Assets.AdRight);
		
		su0=new Animation(hdlr,120,Assets.dUp);
		sl0=new Animation(hdlr,120,Assets.dLeft);
		sd0=new Animation(hdlr,120,Assets.dDown);
		sr0=new Animation(hdlr,120,Assets.dRight);
		sui0=new Animation(hdlr,240,Assets.diU);
		sli0=new Animation(hdlr,240,Assets.diL);
		sdi0=new Animation(hdlr,240,Assets.diD);
		sri0=new Animation(hdlr,240,Assets.diR);
		asu0=new Animation(hdlr,70,Assets.AdUp);
		asl0=new Animation(hdlr,70,Assets.AdLeft);
		asd0=new Animation(hdlr,70,Assets.AdDown);
		asr0=new Animation(hdlr,70,Assets.AdRight);
		
		//hU=Assets.dhU;
		hU=Assets.diU[1];
		hL=Assets.diL[1];
		hD=Assets.diD[1];
		hR=Assets.diR[1];
		shU=hU;
		shL=hL;
		shD=hD;
		shR=hR;
		// TODO Auto-generated constructor stub
	}
	int aTick=0,hj=0;
	public void tick() {
		lookForTarget();
		if(dying){
			if(gibTick<gibLim){
				gibTick++;
			}
			if(health<-120){
				if(gibTick<gibLim||isOnFire()){
					if(gibTick<gibLim)
						health=-1;
					bounds.y=21;
					bounds.height=10;
					if(!gibb)
						spawnBoneGibs();
					fireTick=0;
					hj=0;
					death=gibbed;
					gibb=true;
				}else{
					active=false;
					if(!gibb){
						spawnImpGibs();
						spawnGuts();
					}else{
						spawnBoneGibs();
					}
				}
			}
			deathTick++;
			if(hj<4){
				hj=death.tick();
			}
			if(deathTick>180){
				//active=false;
			}
			return;
		}
		if(!targetIsActive())
			this.target=new String("PLAYER");
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
		if(aTick>10){
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
					ar=new Rectangle(32,100);
					ar.x=(int)x;
					ar.y=(int)y+height+60;
				}else if(this.direction==1){
					//right
					ar=new Rectangle(100,32);
					ar.x=(int)x+width+60;
					ar.y=(int)y;
				}else if(this.direction==2){
					//up
					ar=new Rectangle(32,100);
					ar.x=(int)x;
					ar.y=(int)y-160;
				}else{
					//left
					ar=new Rectangle(100,32);
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
			if(Math.random()>0.87F){
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
				hdlr.$currentWorld().em.addEntity(
						new Fireball(hdlr,this.x,this.y,label,this.direction,false));
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
		if(dying){
			g.drawImage(death.$currentFrame(),
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
			return;
		}
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
				g.drawImage(armored?Assets.shD:hD,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
			}else if(this.direction==1){
				//right
				g.drawImage(armored?Assets.shR:hR,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
			}else if(this.direction==2){
				//up
				g.drawImage(armored?Assets.shU:hU,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
			}else{
				//left
				g.drawImage(armored?Assets.shL:hL,
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
	protected void renderHP(Graphics g){
		if(!showHP)return;
		if(health<maxHealth){
			g.setColor(Color.BLACK);
			g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()-3),24,1);
			g.setColor(Color.GREEN);
			if(health>0)
				g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()-3),(int)(((float)(health)/(float)(maxHealth))*24),1);
		}
	}
	public void die(){
		if(!dying){
			hdlr.$currentWorld().em.addEntity(new Coin(hdlr,this.x,this.y,(int)(Math.random()*21)-5));
			if(Math.random()<0.35F)
				hdlr.$currentWorld().em.addEntity(new RottenFlesh(hdlr,x,y,1+(int)(Math.random()*3)));
			hdlr.$currentWorld().em.addEntity(new ScrapMetal(hdlr,this.x,this.y,1));
			if(health<-50){
				gibb=true;
				health=-1;
				bounds.y=21;
				bounds.height=10;
				spawnBoneGibs();
				death=gibbed;
			}
		}
		dying=true;
		this.ally=256;
		this.label=new String("DEAD_CORPSE");
		active=true;
	}
}
