package havocpixel.entities.creatures;

import havocpixel.entities.Entity;
import havocpixel.entities.FloatingString;
import havocpixel.entities.PoisonedThrownKnife;
import havocpixel.entities.ThrownKnifeAlt;
import havocpixel.entities.items.Coin;
import havocpixel.entities.items.ExplosiveThrowingKnife;
import havocpixel.entities.items.Grenade;
import havocpixel.entities.items.PoisonedThrowingKnife;
import havocpixel.entities.items.ThrowingKnife;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PossessedGrunt extends Imp{

	private boolean alt=false;
	protected boolean helmeted=false;
	protected BufferedImage[] altSpawn=Assets.phDeath0;
	public PossessedGrunt(Handler hdlr, float x, float y,String label0) {
		super(hdlr, x, y,label0);
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		label=label0;
		target="PLAYER";
		health=200;
		maxHealth=200;
		speed=2;
		damage=10;
		cx=x;
		cy=y;
		this.AC=30;
		ally=0;

		if(Math.random()<0.5F){
			alt=true;
		}
		//FIX THIS
		if(Math.random()<0.75F){
			death=new Animation(hdlr,200,Assets.phDeath);
			spawn=new Animation(hdlr,120,Assets.phSpawn);
			
			su=new Animation(hdlr,120,Assets.phUp);
			sl=new Animation(hdlr,120,Assets.phLeft);
			sd=new Animation(hdlr,120,Assets.phDown);
			sr=new Animation(hdlr,120,Assets.phRight);
			sui=new Animation(hdlr,240,Assets.phiU);
			sli=new Animation(hdlr,240,Assets.phiL);
			sdi=new Animation(hdlr,240,Assets.phiD);
			sri=new Animation(hdlr,240,Assets.phiR);
			hU=Assets.phiU[1];
			hL=Assets.phiL[1];
			hD=Assets.phiD[1];
			hR=Assets.phiR[1];
			shU=hU;
			shL=hL;
			shD=hD;
			shR=hR;
			
			asu=new Animation(hdlr,70,Assets.pAhUp);
			asl=new Animation(hdlr,70,Assets.pAhLeft);
			asd=new Animation(hdlr,70,Assets.pAhDown);
			asr=new Animation(hdlr,70,Assets.pAhRight);
			
			su0=new Animation(hdlr,120,Assets.phUp);
			sl0=new Animation(hdlr,120,Assets.phLeft);
			sd0=new Animation(hdlr,120,Assets.phDown);
			sr0=new Animation(hdlr,120,Assets.phRight);
			sui0=new Animation(hdlr,240,Assets.phiU);
			sli0=new Animation(hdlr,240,Assets.phiL);
			sdi0=new Animation(hdlr,240,Assets.phiD);
			sri0=new Animation(hdlr,240,Assets.phiR);
			asu0=new Animation(hdlr,70,Assets.pAhUp);
			asl0=new Animation(hdlr,70,Assets.pAhLeft);
			asd0=new Animation(hdlr,70,Assets.pAhDown);
			asr0=new Animation(hdlr,70,Assets.pAhRight);
			helmeted=true;
		}else{
			if(alt){
				death=new Animation(hdlr,200,Assets.phDeath0);
				spawn=new Animation(hdlr,120,Assets.phSpawn0);
				
				su=new Animation(hdlr,120,Assets.phUp0);
				sl=new Animation(hdlr,120,Assets.phLeft0);
				sd=new Animation(hdlr,120,Assets.phDown0);
				sr=new Animation(hdlr,120,Assets.phRight0);
				sui=new Animation(hdlr,240,Assets.phiU0);
				sli=new Animation(hdlr,240,Assets.phiL0);
				sdi=new Animation(hdlr,240,Assets.phiD0);
				sri=new Animation(hdlr,240,Assets.phiR0);
				
				asu=new Animation(hdlr,70,Assets.pAhUp0);
				asl=new Animation(hdlr,70,Assets.pAhLeft0);
				asd=new Animation(hdlr,70,Assets.pAhDown0);
				asr=new Animation(hdlr,70,Assets.pAhRight0);
				
				su0=new Animation(hdlr,120,Assets.phUp0);
				sl0=new Animation(hdlr,120,Assets.phLeft0);
				sd0=new Animation(hdlr,120,Assets.phDown0);
				sr0=new Animation(hdlr,120,Assets.phRight0);
				sui0=new Animation(hdlr,240,Assets.phiU0);
				sli0=new Animation(hdlr,240,Assets.phiL0);
				sdi0=new Animation(hdlr,240,Assets.phiD0);
				sri0=new Animation(hdlr,240,Assets.phiR0);
				asu0=new Animation(hdlr,70,Assets.pAhUp0);
				asl0=new Animation(hdlr,70,Assets.pAhLeft0);
				asd0=new Animation(hdlr,70,Assets.pAhDown0);
				asr0=new Animation(hdlr,70,Assets.pAhRight0);
				hU=Assets.phiU0[1];
				hL=Assets.phiL0[1];
				hD=Assets.phiD0[1];
				hR=Assets.phiR0[1];
				shU=hU;
				shL=hL;
				shD=hD;
				shR=hR;
			}else{
				death=new Animation(hdlr,200,Assets.phDeath01);
				spawn=new Animation(hdlr,120,Assets.phSpawn01);
				
				su=new Animation(hdlr,120,Assets.phUp01);
				sl=new Animation(hdlr,120,Assets.phLeft01);
				sd=new Animation(hdlr,120,Assets.phDown01);
				sr=new Animation(hdlr,120,Assets.phRight01);
				sui=new Animation(hdlr,240,Assets.phiU01);
				sli=new Animation(hdlr,240,Assets.phiL01);
				sdi=new Animation(hdlr,240,Assets.phiD01);
				sri=new Animation(hdlr,240,Assets.phiR01);
				
				asu=new Animation(hdlr,70,Assets.pAhUp01);
				asl=new Animation(hdlr,70,Assets.pAhLeft01);
				asd=new Animation(hdlr,70,Assets.pAhDown01);
				asr=new Animation(hdlr,70,Assets.pAhRight01);
				
				su0=new Animation(hdlr,120,Assets.phUp01);
				sl0=new Animation(hdlr,120,Assets.phLeft01);
				sd0=new Animation(hdlr,120,Assets.phDown01);
				sr0=new Animation(hdlr,120,Assets.phRight01);
				sui0=new Animation(hdlr,240,Assets.phiU01);
				sli0=new Animation(hdlr,240,Assets.phiL01);
				sdi0=new Animation(hdlr,240,Assets.phiD01);
				sri0=new Animation(hdlr,240,Assets.phiR01);
				asu0=new Animation(hdlr,70,Assets.pAhUp01);
				asl0=new Animation(hdlr,70,Assets.pAhLeft01);
				asd0=new Animation(hdlr,70,Assets.pAhDown01);
				asr0=new Animation(hdlr,70,Assets.pAhRight01);
				hU=Assets.phiU01[1];
				hL=Assets.phiL01[1];
				hD=Assets.phiD01[1];
				hR=Assets.phiR01[1];
				shU=hU;
				shL=hL;
				shD=hD;
				shR=hR;
			}
			helmeted=false;
		}
		
		if(helmeted){
			this.AC=35;
		}else{
			this.AC=20;
		}
		//spawning=false;
		//armored=false;
		possessed=false;
	}
	int aTick=0,hj=0;
	boolean dropsHelmet=Math.random()<0.65F;
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
					this.label=new String("DEAD_CORPSE");
					death=gibbed;
					gibb=true;
				}else{
					active=false;
					spawnBoneGibs();
					if(!gibb)
						spawnGuts();
				}
			}
			deathTick++;
			if(hj<4){
				hj=death.tick();
			}
			if(deathTick>180){
				//active=false;
			}
			if(possessed&&active){
				if(helmeted){
					spawn=new Animation(hdlr,120,new BufferedImage[]{
							Assets.phDeath[3],Assets.phDeath[3],Assets.phDeath[2],Assets.phDeath[2],Assets.phDeath[1],Assets.phDeath[1],Assets.phDeath[0],Assets.phDeath[0],
					});
				}else{
					spawn=new Animation(hdlr,120,new BufferedImage[]{
							altSpawn[3],altSpawn[3],altSpawn[2],altSpawn[2],altSpawn[1],altSpawn[1],altSpawn[0],altSpawn[0],
					});
				}
				death.reset();
				spawn.reset();
				dying=false;
				deathTick=0;
				health=maxHealth-(int)(Math.random()*maxHealth*3/4);
				cx=x;
				cy=y;
				spawning=true;
				hj=0;
				this.speed=1;
				this.ally=0;
				dropsHelmet=Math.random()<0.65F;
				this.label=new String("POSSESSED_HUMAN_"+Math.random());
				this.target=new String("PLAYER");
				//hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,x,y,"Possessed!",Color.WHITE,12));
			}
			return;
		}
		if(possessed){
			trailKoolAid();
			if(hdlr.$game().$tick()%5==0)
				health--;
			if(health<2){
				health-=100;
			}
		}
		if(!targetIsActive())
			this.target=new String("PLAYER");
		if((health<maxHealth/2)&&helmeted&&dropsHelmet){
			helmeted=false;
			this.AC=20;
			hdlr.$currentWorld().em.addEntity(new HelmetGib0(hdlr,this.x,this.y));
			if(alt){
				death=new Animation(hdlr,200,Assets.phDeath0);
				spawn=new Animation(hdlr,120,Assets.phSpawn0);
				
				su=new Animation(hdlr,120,Assets.phUp0);
				sl=new Animation(hdlr,120,Assets.phLeft0);
				sd=new Animation(hdlr,120,Assets.phDown0);
				sr=new Animation(hdlr,120,Assets.phRight0);
				sui=new Animation(hdlr,240,Assets.phiU0);
				sli=new Animation(hdlr,240,Assets.phiL0);
				sdi=new Animation(hdlr,240,Assets.phiD0);
				sri=new Animation(hdlr,240,Assets.phiR0);
				
				asu=new Animation(hdlr,70,Assets.pAhUp0);
				asl=new Animation(hdlr,70,Assets.pAhLeft0);
				asd=new Animation(hdlr,70,Assets.pAhDown0);
				asr=new Animation(hdlr,70,Assets.pAhRight0);
				hU=Assets.phiU0[1];
				hL=Assets.phiL0[1];
				hD=Assets.phiD0[1];
				hR=Assets.phiR0[1];
				shU=hU;
				shL=hL;
				shD=hD;
				shR=hR;
				
				su0=new Animation(hdlr,120,Assets.phUp0);
				sl0=new Animation(hdlr,120,Assets.phLeft0);
				sd0=new Animation(hdlr,120,Assets.phDown0);
				sr0=new Animation(hdlr,120,Assets.phRight0);
				sui0=new Animation(hdlr,240,Assets.phiU0);
				sli0=new Animation(hdlr,240,Assets.phiL0);
				sdi0=new Animation(hdlr,240,Assets.phiD0);
				sri0=new Animation(hdlr,240,Assets.phiR0);
				asu0=new Animation(hdlr,70,Assets.pAhUp0);
				asl0=new Animation(hdlr,70,Assets.pAhLeft0);
				asd0=new Animation(hdlr,70,Assets.pAhDown0);
				asr0=new Animation(hdlr,70,Assets.pAhRight0);
			}else{
				death=new Animation(hdlr,200,Assets.phDeath01);
				spawn=new Animation(hdlr,120,Assets.phSpawn01);
				
				su=new Animation(hdlr,120,Assets.phUp01);
				sl=new Animation(hdlr,120,Assets.phLeft01);
				sd=new Animation(hdlr,120,Assets.phDown01);
				sr=new Animation(hdlr,120,Assets.phRight01);
				sui=new Animation(hdlr,240,Assets.phiU01);
				sli=new Animation(hdlr,240,Assets.phiL01);
				sdi=new Animation(hdlr,240,Assets.phiD01);
				sri=new Animation(hdlr,240,Assets.phiR01);
				hU=Assets.phiU01[1];
				hL=Assets.phiL01[1];
				hD=Assets.phiD01[1];
				hR=Assets.phiR01[1];
				shU=hU;
				shL=hL;
				shD=hD;
				shR=hR;
				
				asu=new Animation(hdlr,70,Assets.pAhUp01);
				asl=new Animation(hdlr,70,Assets.pAhLeft01);
				asd=new Animation(hdlr,70,Assets.pAhDown01);
				asr=new Animation(hdlr,70,Assets.pAhRight01);
				
				su0=new Animation(hdlr,120,Assets.phUp01);
				sl0=new Animation(hdlr,120,Assets.phLeft01);
				sd0=new Animation(hdlr,120,Assets.phDown01);
				sr0=new Animation(hdlr,120,Assets.phRight01);
				sui0=new Animation(hdlr,240,Assets.phiU01);
				sli0=new Animation(hdlr,240,Assets.phiL01);
				sdi0=new Animation(hdlr,240,Assets.phiD01);
				sri0=new Animation(hdlr,240,Assets.phiR01);
				asu0=new Animation(hdlr,70,Assets.pAhUp01);
				asl0=new Animation(hdlr,70,Assets.pAhLeft01);
				asd0=new Animation(hdlr,70,Assets.pAhDown01);
				asr0=new Animation(hdlr,70,Assets.pAhRight01);
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
						(Math.random()<0.65)?new ThrownKnifeAlt(hdlr,this.x,this.y,0,0,label,this.direction):
							new PoisonedThrownKnife(hdlr,this.x,this.y,label,this.direction));
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
	public void die(){
		if(!dying&&!possessed){
			hdlr.$currentWorld().em.addEntity(new Coin(hdlr,this.x,this.y,(int)(Math.random()*21)-5));
			//hdlr.$currentWorld().em.addEntity(new ThrowingKnife(hdlr,this.x,this.y,(int)(Math.random()*10)-2));
			hdlr.$currentWorld().em.addEntity(new PoisonedThrowingKnife(hdlr,x,y,(int)(Math.random()*10)-2));
			hdlr.$currentWorld().em.addEntity(new Grenade(hdlr,x,y,4-(int)(Math.random()*7)));
		}
		this.ally=256;
		if(!dying){
			this.label=new String("DEAD_HUMAN_CORPSE");
			if(health<-50){
				health=-1;
				bounds.y=21;
				bounds.height=10;
				spawnBoneGibs();
				death=gibbed;
				gibb=true;
				this.label=new String("DEAD_CORPSE");
			}
			//hdlr.$world().em.addEntity(new SkullGib(hdlr,this.x-9,this.y-9));
		}
		dying=true;
		if(possessed){
			Entity e=new Ghost(hdlr,x,y,"GHOST_"+Math.random(),true);
			e.target=this.target;
			hdlr.$currentWorld().em.addEntity(e);
			//hdlr.$world().em.addEntity(new FloatingString(hdlr,x,y,Utils.rndString((int)(Math.random()*51)),Color.WHITE,12));
			possessed=false;
		}
		active=true;
	}
}
