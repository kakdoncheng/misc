package havocpixel.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.entities.ThrownKnifeAlt;
import havocpixel.entities.items.BowlOfRice;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class HumanGrunt extends Skeleton{
	private Animation death;
	private int deathTick=0;
	private boolean dying=false;
	public HumanGrunt(Handler hdlr, float x, float y,String label) {
		super(hdlr, x, y, 500,true,label);
		this.maxHealth=500;
		this.ally=1;
		
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		target="NO_TARGET";
		speed=2;
		damage=10;
		this.AC=30;
		this.fleshy=true;
		this.armored=false;
		death=new Animation(hdlr,200,Assets.hDeath);
		
		su=new Animation(hdlr,120,Assets.hUp);
		sl=new Animation(hdlr,120,Assets.hLeft);
		sd=new Animation(hdlr,120,Assets.hDown);
		sr=new Animation(hdlr,120,Assets.hRight);
		sui=new Animation(hdlr,240,Assets.hiU);
		sli=new Animation(hdlr,240,Assets.hiL);
		sdi=new Animation(hdlr,240,Assets.hiD);
		sri=new Animation(hdlr,240,Assets.hiR);
		
		asu=new Animation(hdlr,70,Assets.AhUp);
		asl=new Animation(hdlr,70,Assets.AhLeft);
		asd=new Animation(hdlr,70,Assets.AhDown);
		asr=new Animation(hdlr,70,Assets.AhRight);
		
		su0=new Animation(hdlr,120,Assets.hUp);
		sl0=new Animation(hdlr,120,Assets.hLeft);
		sd0=new Animation(hdlr,120,Assets.hDown);
		sr0=new Animation(hdlr,120,Assets.hRight);
		sui0=new Animation(hdlr,240,Assets.hiU);
		sli0=new Animation(hdlr,240,Assets.hiL);
		sdi0=new Animation(hdlr,240,Assets.hiD);
		sri0=new Animation(hdlr,240,Assets.hiR);
		asu0=new Animation(hdlr,70,Assets.AhUp);
		asl0=new Animation(hdlr,70,Assets.AhLeft);
		asd0=new Animation(hdlr,70,Assets.AhDown);
		asr0=new Animation(hdlr,70,Assets.AhRight);
		
		hU=Assets.hhU;
		hL=Assets.hhL;
		hD=Assets.hhD;
		hR=Assets.hhR;
		// TODO Auto-generated constructor stub
	}
	int aTick=0,hj=0;
	public void tick() {
		if(dying){
			if(health<-120){
				active=false;
				spawnGibs();
				spawnGuts();
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
		stunTick++;
		aTick++;
		if(aTick>60){
			if(!attacking){
				//this.target=new String("player0");
				if(health<121)
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
					if(u.equals(this)||u.ally==this.ally||u.particle)
						continue;
					if(u.$collisionBounds(0,0).intersects(ar)&&(u.label.equals(this.target)||u.ally==0)){
						attacking=true;
						throwProjectile=true;
						//if(Math.random()<0.1F)
							//grenade=true;
					}
				}
			}
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
				if(e.equals(this)||e.ally==this.ally)
					continue;
				if(e.$collisionBounds(0,0).intersects(ar0)){
					if(e.label.equals(this.target)||e.ally==0){
						this.direction=0;
						if(rndInt(lim)>thr)
							attacking=true;
						return;
					}
				}else if(e.$collisionBounds(0,0).intersects(ar1)){
					if(e.label.equals(this.target)||e.ally==0){
						this.direction=1;
						if(rndInt(lim)>thr)
							attacking=true;
						return;
					}
				}else if(e.$collisionBounds(0,0).intersects(ar2)){
					if(e.label.equals(this.target)||e.ally==0){
						this.direction=2;
						if(rndInt(lim)>thr)
							attacking=true;
						return;
					}
				}else if(e.$collisionBounds(0,0).intersects(ar3)){
					if(e.label.equals(this.target)||e.ally==0){
						this.direction=3;
						if(rndInt(10)>thr)
							attacking=true;
						return;
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
							new ThrownKnifeAlt(hdlr,this.x,this.y,
									hdlr.$mm().$x()-(int)hdlr.$camera().$xOffset(),
									hdlr.$mm().$y()-(int)hdlr.$camera().$xOffset(),label,this.direction));
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
					if(e.equals(this)||e.ally==this.ally)
						continue;
					if(e.$collisionBounds(0,0).intersects(ar1)){
						if(e.label.equals(this.target)||e.ally==0){
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
	public void die(){
		if(!dying)
			if(Math.random()<0.20F)
				hdlr.$currentWorld().em.addEntity(new BowlOfRice(hdlr,x,y,1));
		dying=true;
		this.ally=256;
		this.label=new String("DEAD_CORPSE");
		active=true;
	}
}
