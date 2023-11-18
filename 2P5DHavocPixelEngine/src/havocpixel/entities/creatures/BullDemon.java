package havocpixel.entities.creatures;

import java.awt.Rectangle;

import havocpixel.entities.Fireball;
import havocpixel.entities.items.RottenFlesh;
import havocpixel.entities.items.ScrapMetal;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class BullDemon extends Imp{

	public BullDemon(Handler hdlr, float x, float y, String label0) {
		super(hdlr, x, y, label0);
		this.maxHealth=300;
		this.health=300;
		this.ally=0;
		
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		target="PLAYER";
		speed=2;
		cx=x;
		cy=y;
		damage=115;
		this.AC=25;
		this.fleshy=true;
		this.armored=false;
		death=new Animation(hdlr,200,Assets.pdDeath);
		spawn=new Animation(hdlr,120,Assets.pdSpawn);
		
		su=new Animation(hdlr,120,Assets.pdUp);
		sl=new Animation(hdlr,120,Assets.pdLeft);
		sd=new Animation(hdlr,120,Assets.pdDown);
		sr=new Animation(hdlr,120,Assets.pdRight);
		sui=new Animation(hdlr,240,Assets.pdiU);
		sli=new Animation(hdlr,240,Assets.pdiL);
		sdi=new Animation(hdlr,240,Assets.pdiD);
		sri=new Animation(hdlr,240,Assets.pdiR);
		
		asu=new Animation(hdlr,70,Assets.pAdUp);
		asl=new Animation(hdlr,70,Assets.pAdLeft);
		asd=new Animation(hdlr,70,Assets.pAdDown);
		asr=new Animation(hdlr,70,Assets.pAdRight);
		
		su0=new Animation(hdlr,120,Assets.pdUp);
		sl0=new Animation(hdlr,120,Assets.pdLeft);
		sd0=new Animation(hdlr,120,Assets.pdDown);
		sr0=new Animation(hdlr,120,Assets.pdRight);
		sui0=new Animation(hdlr,240,Assets.pdiU);
		sli0=new Animation(hdlr,240,Assets.pdiL);
		sdi0=new Animation(hdlr,240,Assets.pdiD);
		sri0=new Animation(hdlr,240,Assets.pdiR);
		asu0=new Animation(hdlr,70,Assets.pAdUp);
		asl0=new Animation(hdlr,70,Assets.pAdLeft);
		asd0=new Animation(hdlr,70,Assets.pAdDown);
		asr0=new Animation(hdlr,70,Assets.pAdRight);
		
		//hU=Assets.pdhU;
		//hL=Assets.pdhL;
		//hD=Assets.pdhD;
		//hR=Assets.pdhR;
		//hurt animations same as idle
		hU=Assets.pdiU[1];
		hL=Assets.pdiL[1];
		hD=Assets.pdiD[1];
		hR=Assets.pdiR[1];
	}
	int aTick=0,hj=0;
	boolean zigUp=false;
	int zag=0,zig=0;
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
					//death=gibbed;
					//gibb=true;
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
			//setTargetDirection();
			//simple offensive AI idea
			//bigger sight/hearing rectangle;
			//if player is within the rectangle go to the player's coordinate
			
			//check if the player is within any of the directions;
			//if player intersects the rectangle for direction 0,
			//	turn to direction 0&launch an attack;
			
			//if living enitity in front of demon, will launch an attack at them regardless
			//of allaiance
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
			Rectangle u0=new Rectangle((int)x-64,(int)y-64,width+128,height+128);
			for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
				if(!u0.intersects(e.$collisionBounds(0,0)))
					continue;
				if(e.equals(this)||e.particle||e.item||e.flora)
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
				//attacking in front of it
				//if(!(targetDirection==-1))
				/*
				if(e.$collisionBounds(0,0).intersects(ar0)){
					if(targetDirection==0){
						attacking=true;
						return;
					}
				}else if(e.$collisionBounds(0,0).intersects(ar1)){
					if(targetDirection==1){
						attacking=true;
						return;
					}
				}else if(e.$collisionBounds(0,0).intersects(ar2)){
					if(targetDirection==2){
						attacking=true;
						return;
					}
				}else if(e.$collisionBounds(0,0).intersects(ar3)){
					if(targetDirection==3){
						attacking=true;
						return;
					}
				}*/
					
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
			//randomly zigzags in an attempt to avoid projectiles
			if(angry&&!attacking){
				Rectangle ar1=new Rectangle((int)(this.x-224),(int)(this.y-224),480,480);
				for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
					if(e.equals(this)||e.particle)
						continue;
					if(e.$collisionBounds(0,0).intersects(ar1)){
						if(e.label.equals(this.target)||(!targetFound&&e.ally==1)){
							//NOTE: DIRECTIONS //d0r1u2l3
							//zigzag the shit here
							//keep track of target but shift sight up and down
							int ex=(int)e.x,ey=(int)e.y,tx=(int)this.x,ty=(int)this.y;
							int mx=0,my=0;
							if(Math.abs(ex-tx)>10||Math.abs(ey-ty)>10){
								if(zigUp){
									zag+=2;
									if(zag>zig){
										zigUp=false;
										zig=(int)(Math.random()*64)+1;
									}
								}else{
									zag-=2;
									if(zag<-zig){
										zigUp=true;
										zig=(int)(Math.random()*64)+1;
									}
								}
								if(ey>ty||ey<ty){
									//modify x
									mx=zag;
									//my=0;
									//System.out.println(zag);
								}
								if(ex>tx||ex<tx){
								//else{
									//modify y
									//mx=0;
									my=zag;
									//System.out.println(zag);
								}
							}else{
								//dont zigzag
								mx=0;
								my=0;
							}
							ex+=mx;
							ey+=my;
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
	public void die(){
		if(!dying){
			if(Math.random()<0.35F)
				hdlr.$currentWorld().em.addEntity(new RottenFlesh(hdlr,x,y,1+(int)(Math.random()*3)));
			hdlr.$currentWorld().em.addEntity(new ScrapMetal(hdlr,this.x,this.y,1));
			if(health<-50){
				//gibb=true;
				health=-1;
				bounds.y=21;
				bounds.height=10;
				spawnBoneGibs();
				//death=gibbed;
			}
		}
		dying=true;
		this.ally=256;
		this.label=new String("DEAD_CORPSE");
		active=true;
	}
}
