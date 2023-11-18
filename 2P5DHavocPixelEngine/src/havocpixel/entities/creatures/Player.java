package havocpixel.entities.creatures;

import havocpixel.Timer;
import havocpixel.entities.DeathCurse;
import havocpixel.entities.Entity;
import havocpixel.entities.ExplodingThrownKnife;
import havocpixel.entities.FloatingString;
import havocpixel.entities.Icebolt;
import havocpixel.entities.ThrownKnifeAlt;
import havocpixel.entities.WindSlash;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.states.State;
import havocpixel.tiles.Tile;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Creature{
	//warrior class
	//add nausea
	//BE SURE TO UPDATE RESET METHOD WHEN PLAYER VARIABLES ARE MODIFIED
	private Animation up, left, down, right, idle,death;
	private Animation su,sl,sd,sr,asu,asl,asd,asr,sui,sli,sdi,sri;
	private Animation su0,sl0,sd0,sr0,asu0,asl0,asd0,asr0,sui0,sli0,sdi0,sri0;
	private Animation au,al,ad,ar;
	protected BufferedImage hU,hL,hD,hR,shU,shL,shD,shR;
	private boolean armored=false;
	public boolean marked=true;
	public boolean bleed=false;
	public boolean zwei=true;
	public int kills=0;
	
	public float weightLim=100.0F,weight=0.0F;
	private float maxSpeed=2.5F;
	private int U=0;
	public int stamina=1000;
	private int incDamage=50;
	private int oldHealth=0;
	private int energy=100,eRegenCD=1,enTick=0,maxDamage=50;
	private int blastLim=0,bt=0;
	private int regenCD=180,rcd=0;
	private int regenLim=5,rt=0;
	private int deathTick=0;
	private boolean dying=false;
	
	//pickup bonuses
	public int power=0;
	
	//alt attack animation
	private boolean altAttack=false;
	
	//power attack
	private int addDmg=0;
	private int chain=0;
	private int cooldown=0;
	private int chainCD=0;
	private boolean charging=false;
	
	//blocking mechanics
	private boolean blocking=false;
	public int counterCD=0;
	public boolean isBlocking(){
		return blocking;
	}
	public int storedDmg=0,counterDmg=0;
	private int storedDmgTick=0;
	private int shieldHP=350;
	
	private int boostStamina=0;
	public void eat(int calories){
		boostStamina+=calories;
	}
	public String pItem="";
	private int pITick=180,pitLim=180;
	public void resetPIT(){
		pITick=0;
	}
	public void setOnFire(){
		if(blocking)
			return;
		fireTick+=150+(int)(Math.random()*151);
	}
	public boolean godMode=false;
	
	//healing tick
	public int healTick=0;
	private int totalHealth=500;
	
	public Player(float x, float y, Handler hdlr) {
		super(hdlr, x, y
				, Creature.DEFAULT_WIDTH
				, Creature.DEFAULT_HEIGHT
				);
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		//object=true;
		speed=maxSpeed;
		damage=maxDamage;
		label="PLAYER";
		//1000;
		health=totalHealth;
		maxHealth=health;
		oldHealth=health;
		target="IDLEMINDSARETHEDEVILSWORKSHOP";
		this.AC=35;
		this.ally=1;
		this.fleshy=true;
		
		//godMode=true;
		//up = new Animation(200,Assets.sansUp);
		//left = new Animation(200,Assets.sansLeft);
		//down = new Animation(200,Assets.sansDown);
		//right = new Animation(200,Assets.sansRight);
		//idle = new Animation(200,Assets.sansIdle);
		up = new Animation(hdlr,60,Assets.playerUp);
		left = new Animation(hdlr,90,Assets.playerLeft);
		down = new Animation(hdlr,60,Assets.playerDown);
		right = new Animation(hdlr,90,Assets.playerRight);
		idle = new Animation(hdlr,60,Assets.playerIdle);
		death=new Animation(hdlr,200,Assets.hDeath);
		su=new Animation(hdlr,120,Assets.hUp0);
		sl=new Animation(hdlr,120,Assets.hLeft0);
		sd=new Animation(hdlr,120,Assets.hDown0);
		sr=new Animation(hdlr,120,Assets.hRight0);
		sui=new Animation(hdlr,240,Assets.hiU0);
		sli=new Animation(hdlr,240,Assets.hiL0);
		sdi=new Animation(hdlr,240,Assets.hiD0);
		sri=new Animation(hdlr,240,Assets.hiR0);
		asu=new Animation(hdlr,70,Assets.AhUp0);
		asl=new Animation(hdlr,70,Assets.AhLeft0);
		asd=new Animation(hdlr,70,Assets.AhDown0);
		asr=new Animation(hdlr,70,Assets.AhRight0);
		
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
		au=new Animation(hdlr,70,Assets.AhUpAlt);
		al=new Animation(hdlr,70,Assets.AhLeftAlt);
		ad=new Animation(hdlr,70,Assets.AhDownAlt);
		ar=new Animation(hdlr,70,Assets.AhRightAlt);
		hU=Assets.hiU[1];
		hL=Assets.hiL[1];
		hD=Assets.hiD[1];
		hR=Assets.hiR[1];
		shU=Assets.hiU0[1];
		shL=Assets.hiL0[1];
		shD=Assets.hiD0[1];
		shR=Assets.hiR0[1];
		
		if(zwei){
			int sp=95;//95
			death=new Animation(hdlr,200,Assets.phDeath);
			su=new Animation(hdlr,120,Assets.zU);
			sl=new Animation(hdlr,120,Assets.zL);
			sd=new Animation(hdlr,120,Assets.zD);
			sr=new Animation(hdlr,120,Assets.zR);
			sui=new Animation(hdlr,240,Assets.ziU);
			sli=new Animation(hdlr,240,Assets.ziL);
			sdi=new Animation(hdlr,240,Assets.ziD);
			sri=new Animation(hdlr,240,Assets.ziR);
			asu=new Animation(hdlr,sp,Assets.zAU[0]);
			asl=new Animation(hdlr,sp,Assets.zAL[0]);
			asd=new Animation(hdlr,sp,Assets.zAD[0]);
			asr=new Animation(hdlr,sp,Assets.zAR[0]);
			
			su0=new Animation(hdlr,120,Assets.zU);
			sl0=new Animation(hdlr,120,Assets.zL);
			sd0=new Animation(hdlr,120,Assets.zD);
			sr0=new Animation(hdlr,120,Assets.zR);
			sui0=new Animation(hdlr,240,Assets.ziU);
			sli0=new Animation(hdlr,240,Assets.ziL);
			sdi0=new Animation(hdlr,240,Assets.ziD);
			sri0=new Animation(hdlr,240,Assets.ziR);
			asu0=new Animation(hdlr,sp,Assets.zAU[0]);
			asl0=new Animation(hdlr,sp,Assets.zAL[0]);
			asd0=new Animation(hdlr,sp,Assets.zAD[0]);
			asr0=new Animation(hdlr,sp,Assets.zAR[0]);
			
			au=new Animation(hdlr,sp,Assets.zAU[1]);
			al=new Animation(hdlr,sp,Assets.zAL[1]);
			ad=new Animation(hdlr,sp,Assets.zAD[1]);
			ar=new Animation(hdlr,sp,Assets.zAR[1]);
			
			hU=Assets.ziU[1];
			hL=Assets.ziL[1];
			hD=Assets.ziD[1];
			hR=Assets.ziR[1];
			shU=hU;
			shL=hL;
			shD=hD;
			shR=hR;
		}
	}
	private int num=0;
	private float t=0.0000005F;
	private float c=0.996F-0.004F;//-0.05F;
	public void annoyPlayer(){
		int x=(int)this.x;
		int y=(int)this.y;
		if(Math.random()>c){//&&em.$size()<500){
			int r=(int)(Math.random()*9);
			//r=(int)(Math.random()*3)+3;
			//r=0;
			Entity spawn=null;
			if(r==0){
				spawn=new Ghost(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"GHOST_"+num,false);
			}else if(r==1){
				spawn=new PossessedGrunt(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"POSSESSED_HUMAN_"+num);
			}else if(r==2){
				spawn=new Cultist(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"IMP_"+num);
			}else if(r==3){
				//spawn=new ArcherSkeleton(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"ARCHER_SKELETON_"+num);
				spawn=new HoodedSkeleton(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"GRAVE_SKELETON_"+num);
			}else if(r==4){
				spawn=new GraveSkeleton(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"GRAVE_SKELETON_"+num);
			}else if(r==5){
				spawn=new Skeleton(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),200,true,"ARMORED_SKELETON_"+num);
			}else if(r==6){
				spawn=new Imp(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"GRAVE_SKELETON_"+num);
			}else if(r==7){
				spawn=new Incubus(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"INCUBUS_"+num);
			}else if(r==8){
				spawn=new GelatinousCube(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"GELATINOUS_CUBE_"+num,(int)(Math.random()*3)+1);
			}else if(r==9){
				spawn=new BullDemon(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"BULLDEMON_"+num);
			}
			//spawn=new Ghost(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"INCUBUS_"+num);
			//spawn=new BullDemon(hdlr,(x-160+(int)(Math.random()*320)),(y-160+(int)(Math.random()*320)),"IMP_"+num);
			if(!spawn.entityCollision(0,0)
					&&spawn.x>0&&spawn.y>0&&spawn.x<hdlr.$currentWorld().$width()*32&&spawn.y<hdlr.$currentWorld().$height()*32
					&&!spawn.telefrag()){
					//&&!spawn.collision((int)(spawn.x)/32,(int)(spawn.y)/32)){
				hdlr.$currentWorld().em.addEntity(spawn);
				System.out.print("["+Timer.time()+"] Spawned Monster; Threshold: "+c+"\n");
			}
			num++;
		}
		c-=t;
	}
	private void getInput() {
		//if(attacking)
			//return;
		if(hdlr.$currentWorld().isTalking())
			return;
		if(cooldown>0){
			cooldown--;
			return;
		}
		if(hdlr.$km().d0){
			cooldown=10;
		}
		if(hdlr.$km().d2){
			if(!hdlr.$currentWorld().isTalking())
				hdlr.$currentWorld().talk(Utils.rndString((int)(Math.random()*30)+1),Utils.rndString((int)(Math.random()*351)+1));
		}
		xMove=0;
		yMove=0;
		//directions 0=down,1=right,2=up,3=left
		if(hdlr.$km().Q){
			//power attack
			if(addDmg>149){
				health--;
			}else{
				addDmg+=godMode?10:5;
			}
			blocking=false;
			charging=true;
		}else if(hdlr.$km().W&&hdlr.$km().space&&armored){//&&damageTick!=0){
			//blocking
			blocking=false;
			storedDmgTick=30;
			counterCD=30;
			attacking=true;
			return;
		}else if(hdlr.$km().W&&armored){
			//blocking
			blocking=true;
			storedDmgTick=30;
			return;
		}else if(hdlr.$km().space){
			//energy-=5;
			blocking=false;
			attacking=true;
		}else{
			bt++;
			charging=false;
			blocking=false;
			if(addDmg!=0)
				return;
			if(hdlr.$km().qu0&&bt>blastLim&&!hdlr.$inv().$qu1().isEmpty()){
				bt=0;
				throwProjectile=true;
				attacking=true;
			}
			if(hdlr.$km().qu1&&bt>blastLim&&!hdlr.$inv().$qu0().isEmpty()){
				bt=0;
				throwProjectile=true;
				grenade=true;
				attacking=true;
			}
			if (hdlr.$km().up) {
				this.direction=2;
				yMove=-speed;
			}
			if (hdlr.$km().down) {
				this.direction=0;
				yMove=speed;
			}
			if (hdlr.$km().left) {
				this.direction=3;
				xMove=-speed;
			}
			if (hdlr.$km().right) {
				this.direction=1;
				xMove=speed;
			}
			if (hdlr.$km().suicide) {
				//this.poison();
				if(hdlr.$game().$tick()%6==0){
					if(!godMode){
						//hdlr.$currentWorld().em.addEntity(new ExplodingThrownKnife(hdlr,this.x,this.y,hdlr.$mm().$x()-(int)hdlr.$camera().$xOffset(),hdlr.$mm().$y()-(int)hdlr.$camera().$xOffset(),label,this.direction));
						//hdlr.$world().em.addEntity(new LitGrenade(hdlr,this.x,this.y,label,this.direction));
						hdlr.$currentWorld().em.addEntity(new ThrownKnifeAlt(hdlr,this.x,this.y,hdlr.$mm().$x()-(int)hdlr.$camera().$xOffset(),hdlr.$mm().$y()-(int)hdlr.$camera().$xOffset(),label,this.direction));
						//hdlr.$currentWorld().em.addEntity(new DeathCurse(hdlr,this.x,this.y,this.label,this.target));
					}else{
						hdlr.$currentWorld().em.addEntity(new ExplodingThrownKnife(hdlr,this.x,this.y,hdlr.$mm().$x()-(int)hdlr.$camera().$xOffset(),hdlr.$mm().$y()-(int)hdlr.$camera().$xOffset(),label,this.direction));
					}
				}
				//damage(1,this.label,false);
				//stamina-=100;
				//maxSpeed=2.5F;
				//armored=false;
				//this.AC=0;
			}
			if (hdlr.$km().I) {
				hdlr.showInv=true;
				/*bt=0;
				int x=0,y=0;
				if(this.direction==0){
					//down
					System.out.println("DOWN");
					x=(int)this.x-16;
					y=(int)this.y+height;
				}else if(this.direction==1){
					//right
					System.out.println("RIGHT");
					x=(int)this.x+width;
					y=(int)this.y-16;
				}else if(this.direction==2){
					//up
					System.out.println("UP");
					x=(int)this.x-16;
					y=(int)this.y-64;
				}else{
					//left
					System.out.println("LEFT");
					x=(int)this.x-64;
					y=(int)this.y-16;
				}
				try{
					hdlr.$world().em.addEntity(new GasterBlaster(hdlr,x,y,this.direction,this.label));
				}catch(Exception ex){
					
				}*/
			}
		}
	}
	private int atick=0,hj=0;
	private boolean throwProjectile=false,grenade=false,gibbed=false;
	public void tick() {
		if(godMode){
			//hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,x,y+22,Utils.rndString((int)(Math.random()*3)+1),Color.black,12));
		}
		if(dying){
			addDmg=0;
			if(health<-50&&!gibbed){
				gibbed=true;
				death=new Animation(hdlr,180,Math.random()>0.49F?Assets.gore:Assets.gore0);
				spawnBoneGibs();
				spawnGuts();
				hdlr.$currentWorld().em.addEntity(new HelmetGib0(hdlr,this.x,this.y));
				hj=0;
			}
			deathTick++;
			if(hj<4){
				hj=death.tick();
			}
			if(deathTick>180){
				//active=false;
				State.setState(hdlr.$game().$stateManager().$death());
			}
			return;
		}
		surge();
		this.AC=counterCD!=0||addDmg!=0?0:35;
		if(counterCD>0){
			counterCD--;
		}
		if(chainCD>0){
			chainCD--;
		}else{
			if(hdlr.$game().$tick()%15==1&&chain>0)
				chain--;
		}
		if(storedDmg>shieldHP){
			blocking=false;
			armored=false;
			storedDmg=0;
			storedDmgTick=0;
			spawnTrapBits();
			hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,x,y,"Shield Broken!",Color.WHITE,12));
		}
		if(storedDmgTick!=0){
			storedDmgTick--;
		}else{
			if(storedDmg<2){
				storedDmg=0;
			}else if(storedDmg!=0){
				storedDmg-=1;
			}
		}
		if(health>maxHealth){
			if(healTick>0)
				healTick-=2;
			if(hdlr.$game().$tick()%10==6)
				health--;
		}
		if(stamina>1000&&!(boostStamina>0)){
			stamina--;
		}
		if(boostStamina>0){
			stamina++;
			boostStamina--;
		}
		if(hdlr.$inv().$qu0().isEmpty()){
			hdlr.$inv().reset$qu0();
		}
		if(hdlr.$inv().$qu1().isEmpty()){
			hdlr.$inv().reset$qu1();
		}
		//tweak stamina debuffs
		U++;
		pITick++;
		if(bleed){
			trailKoolAid();
			if(hdlr.$game().$tick()%5==0)
				health--;
		}
		if(stamina>1&&U%
				((xMove!=0||yMove!=0||attacking||health<1000)?120:600)==0)
			stamina--;
		if(stamina<2){
			stamina=1;
		}
		this.eRegenCD=1+((stamina<501)?(500-stamina)/20:0);//((500/stamina)*3)
		if(maxSpeed-(float)(((stamina<501)?(500-stamina)/50:0)*0.1F)>1.9F){
			speed=maxSpeed-(float)(((stamina<501)?(500-stamina)/50:0)*0.1F);
			//speed=maxSpeed-(float)(1-(float)((stamina)/1000));
		}else{
			speed=1.9F;
		}
		if(this.damage>0)
			this.damage=maxDamage-((stamina<501)?(500-stamina)/20:0);//*(maxDamage/2));
		else
			this.damage=0;
		
		if(marked)
			annoyPlayer();
		if(enTick>eRegenCD&&energy<100){
			energy++;
		}
		if(rcd>regenCD){
			regenLim=120;
			//if(xMove!=0||yMove!=0||attacking)
			//	regenLim=(5*(1000/totalHealth))+((stamina<501)?(500-stamina)/10:0);
			//else
			//	regenLim=(1*(1000/totalHealth))+((stamina<501)?(500-stamina)/20:0);
		}else{
			regenLim=120;
			//regenLim=(30*(1000/totalHealth))+((stamina<501)?(500-stamina)/5:0);
		}
		if(healTick>0){
			healTick-=1;
			if(health<maxHealth)
				health+=1;
		}
		if(rt>regenLim&&health<maxHealth&&stamina>99){
			rt=0;
			health++;
		}
		rt++;
		rcd++;
		enTick++;
		if(!attacking&&throwProjectile){
			throwProjectile=false;
			grenade=false;
		}
		if(godMode){
			if(health<1000)health=1000;stamina=1000;damage=9999;//GOD MODE LINE
		}
		if(hurt){
			rcd=0;
			if(surge||godMode){
				hurt=false;
				return;
			}
			if(damageTick<DL){
				damageTick++;
				return;
			}else{
				damageTick=0;
				hurt=false;
			}
			if(zwei){
				return;
			}
			attacking=false;
			throwProjectile=false;
			grenade=false;
			charging=false;
			addDmg=0;
		}
		if(!charging&&addDmg!=0){
			attacking=true;
		}
		if(attack){
			//stamina--;
			if(throwProjectile){
				meleeCooldown=true;
				if(grenade){
					hdlr.$inv().$qu0().useItem((int)this.x,(int)this.y,this.direction,this.label);
					/*
					if(direction==0){
						//down
						hdlr.$world().em.addEntity(
								new LitGrenade(hdlr,this.x,this.y+32,label,this.direction));
					}else if(direction==1){
						//right
						hdlr.$world().em.addEntity(
								new LitGrenade(hdlr,this.x+32,this.y,label,this.direction));
					}else if(direction==2){
						//up
						hdlr.$world().em.addEntity(
								new LitGrenade(hdlr,this.x,this.y-32,label,this.direction));
					}else{
						//left
						hdlr.$world().em.addEntity(
								new LitGrenade(hdlr,this.x-32,this.y,label,this.direction));
					}*/
				}else{
					hdlr.$inv().$qu1().useItem((int)this.x,(int)this.y,this.direction,this.label);
					//hdlr.$world().em.addEntity(new ThrownKnifeAlt(hdlr,this.x,this.y,hdlr.$mm().$x()-(int)hdlr.$camera().$xOffset(),hdlr.$mm().$y()-(int)hdlr.$camera().$xOffset(),label,this.direction));
					//hdlr.$world().em.addEntity(new ExplodingThrownKnife(hdlr,this.x,this.y,hdlr.$mm().$x()-(int)hdlr.$camera().$xOffset(),hdlr.$mm().$y()-(int)hdlr.$camera().$xOffset(),label,this.direction));
				}
				throwProjectile=false;
				grenade=false;
			}else{
				attack();
			}
			attack=false;
		}
		up.tick();
		left.tick();
		down.tick();
		right.tick();
		atick=idle.tick();
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
			au.tick();
			al.tick();
			ad.tick();
			ar.tick();
			asu0.tick();
			asl0.tick();
			asd0.tick();
			asr0.tick();
			if(asr.tick()==2){
				attack=true;
				if(zwei){
					xMove=0;
					yMove=0;
					if(this.direction==0){
						yMove=speed;
					}else if(this.direction==1){
						xMove=speed;
					}else if(this.direction==2){
						yMove=-speed;
					}else{
						xMove=-speed;
					}
					move();
				}
			}
			if(asr.tick()==0){
				asu.reset();
				asl.reset();
				asd.reset();
				au.reset();
				al.reset();
				ad.reset();
				ar.reset();
				asu0.reset();
				asl0.reset();
				asd0.reset();
				asr0.reset();
				altAttack=!altAttack;
				attacking=false;
				meleeCooldown=false;
			}
		}else{
			getInput();
			move();
		}
		hdlr.$camera().centerOnEntity(this);
		if(U>99999)
			U=0;
	}
	public void render(Graphics g) {
		renderHUD(g);
		if(pITick<pitLim){
		//	g.setFont(new Font(hdlr.$game().$defaultFontName(), Font.BOLD,14));
		//	g.drawString("Picked up "+pItem,(hdlr.$game().$width()-g.getFontMetrics().stringWidth("Picked up "+pItem))/2,hdlr.$game().$height()-6);
		}
		if(boostStamina>0){
			g.setColor(Color.YELLOW);
			g.drawRect(0,0,hdlr.$game().$width()-1,hdlr.$game().$height()-1);
			g.drawRect(1,1,hdlr.$game().$width()-3,hdlr.$game().$height()-3);
			g.drawRect(2,2,hdlr.$game().$width()-5,hdlr.$game().$height()-5);
		}
		if(dying){
			g.drawImage(death.$currentFrame(),
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
			return;
		}
		if(blocking){
			if(this.direction==0){
				g.drawImage(Assets.shhD0,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height,null);
			}else if(this.direction==1){
				g.drawImage(Assets.shhR0,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height,null);
			}else if(this.direction==2){
				g.drawImage(Assets.shhU0,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height,null);
			}else{
				g.drawImage(Assets.shhL0,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height,null);
			}
			return;
		}
		if(hurt){
			if(this.direction==0){
				//down
				g.drawImage(armored?shD:hD,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())-(zwei?32:0),
						width,
						height*(zwei?2:1),
						null);
			}else if(this.direction==1){
				//right
				g.drawImage(armored?shR:hR,
						(int)(x-hdlr.$camera().$xOffset())-(zwei?32:0),
						(int)(y-hdlr.$camera().$yOffset()),
						width*(zwei?2:1),
						height,
						null);
			}else if(this.direction==2){
				//up
				g.drawImage(armored?shU:hU,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height*(zwei?2:1),
						null);
			}else{
				//left
				g.drawImage(armored?shL:hL,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width*(zwei?2:1),
						height,
						null);
			}
			return;
		}
		if(charging){
			if(this.direction==0){
				g.drawImage(armored?Assets.AhDown0[0]:Assets.AhDown[0],
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height*2,null);
			}else if(this.direction==1){
				g.drawImage(armored?Assets.AhRight0[0]:Assets.AhRight[0],
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width*2,
						height,null);
			}else if(this.direction==2){
				g.drawImage(armored?Assets.AhUp0[0]:Assets.AhUp[0],
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-height-hdlr.$camera().$yOffset()),
						width,
						height*2,null);
			}else{
				g.drawImage(armored?Assets.AhLeft0[0]:Assets.AhLeft[0],
						(int)(x-width-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width*2,
						height,null);
			}
			return;
		}
		if(attacking){
			if(this.direction==0){
				g.drawImage(currentAnimation(),
						(int)(x-hdlr.$camera().$xOffset())-(zwei?32:0),
						(int)(y-hdlr.$camera().$yOffset())-(zwei?32:0),
						width*(zwei?3:1),
						height*(zwei?3:2),null);
			}else if(this.direction==1){
				g.drawImage(currentAnimation(),
						(int)(x-hdlr.$camera().$xOffset())-(zwei?32:0),
						(int)(y-hdlr.$camera().$yOffset())-(zwei?32:0),
						width*(zwei?3:2),
						height*(zwei?2:1),null);
			}else if(this.direction==2){
				g.drawImage(currentAnimation(),
						(int)(x-hdlr.$camera().$xOffset())-(zwei?32:0),
						(int)(y-height-hdlr.$camera().$yOffset()),
						width*(zwei?3:1),
						height*(zwei?3:2),null);
			}else{
				g.drawImage(currentAnimation(),
						(int)(x-width-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())-(zwei?32:0),
						width*(zwei?3:2),
						height*(zwei?2:1),null);
			}
		}else{
			if(zwei){
				if(this.direction==0){
					g.drawImage(currentAnimation(),
							(int)(x-hdlr.$camera().$xOffset()),
							(int)(y-height-hdlr.$camera().$yOffset()),
							width,
							height*2,null);
				}else if(this.direction==1){
					g.drawImage(currentAnimation(),
							(int)(x-width-hdlr.$camera().$xOffset()),
							(int)(y-hdlr.$camera().$yOffset()),
							width*2,
							height,null);
				}else if(this.direction==2){
					g.drawImage(currentAnimation(),
							(int)(x-hdlr.$camera().$xOffset()),
							(int)(y-hdlr.$camera().$yOffset()),
							width,
							height*2,null);
				}else{
					g.drawImage(currentAnimation(),
							(int)(x-hdlr.$camera().$xOffset()),
							(int)(y-hdlr.$camera().$yOffset()),
							width*2,
							height,null);
				}
			}else
			g.drawImage(currentAnimation(),
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
		}
	}
	public static final String CREDITS="gAme_PROgrAmMIng&DesIGN:LiNH-HAN_vAn'J `/ '` `/;ARt&AnIMATIOns:LH&sARa_PelaYO&JayDE_ToM";
	@SuppressWarnings("unused")
	private void showBounds(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)(x+bounds.x-hdlr.$camera().$xOffset()),(int)(y+bounds.y-hdlr.$camera().$yOffset()),bounds.width, bounds.height);
	}
	private void renderHUD(Graphics g){
		if(addDmg>0){
			g.setColor(Color.BLACK);
			g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()),24,1);
			g.setColor((addDmg>149&&hdlr.$game().$tick()%10>5)?Color.WHITE:addDmg>149?Color.cyan:Color.YELLOW);
			g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()),(int)(((float)(addDmg)/150.0F)*24),1);
		}
		if(!showHP)return;
		if(health<1||health>1999)
			return;
		g.setColor(Color.BLACK);
		g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()-1),24,1);
		g.setColor(Color.GREEN);
		if(health>0)
			g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()-1),(int)(((float)(health)/(float)(maxHealth))*24),1);
		g.setColor(Color.BLACK);
		//g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()+1),24,1);
		g.setColor(Color.YELLOW);
		//if(energy>0)
			//g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()+1),(int)(((float)(energy)/(float)(100))*24),1);
	}

	private float DmgF=0.0f;
	private int q0=0;
	public void renderQU(Graphics g){
		int u0=0;
		if(surge){
			drawSurge(g);
		}
		if(health<(maxHealth)){
			g.setColor(new Color(0xF30000));
			//g.drawRect(0,0,hdlr.$game().$width(),hdlr.$game().$height());
			if(hurt){
				//g.drawRect(0,0,hdlr.$game().$width()-1,hdlr.$game().$height()-1);
				//g.drawRect(1,1,hdlr.$game().$width()-1,hdlr.$game().$height()-1);
			}
		}
		if(hurt)
			q0=35;
		if(health<(maxHealth/2)){
			if(hdlr.$game().$tick()==0||hdlr.$game().$tick()==25){
				q0=35;
			}else if(q0<1){
				q0=35;
			}
		}
		if(q0>0){
			float d;
			if(health>(maxHealth/2)){
				d=0;
			}else{
				d=(float)((maxHealth/2)-health)/(maxHealth/2);
			}
			d+=q0/35.0F*0.2F;
			//int alpha=(int)(d*31);
			//if(alpha<0)
			//	alpha=0;
			//if(alpha>31)
			//	alpha=31;
			//g.drawImage(health>(maxHealth/4)?Assets.hurt50[alpha]:Assets.hurt25[alpha], 
			//		0, 0, hdlr.$game().$width(), hdlr.$game().$height(), null);
			if(d<0)
				d=0.0F;
			if(d>1)
				d=1.0F;
			Utils.drawTranslucentImage(health>(maxHealth/4)?Assets.hurt2:Assets.hurt,
					0,0,d,
					hdlr.$game().$width(),hdlr.$game().$height(),g);
			q0--;
		}
		if(hdlr.$game().$tick()%60==0)
			oldHealth=health;
		g.setColor(Color.WHITE);
		if(!hdlr.$inv().$qu1().isEmpty()){
			hdlr.$inv().$qu1().render(g, hdlr.$game().$width()-32, hdlr.$game().$height()-32);
			g.setFont(new Font("Lucida Console", Font.BOLD, 10));
			g.drawString(hdlr.$inv().$qu1().quantity+"",hdlr.$game().$width()-g.getFontMetrics().stringWidth(hdlr.$inv().$qu1().quantity+""),hdlr.$game().$height());
		}
		if(!hdlr.$inv().$qu0().isEmpty()){
			hdlr.$inv().$qu0().render(g, hdlr.$game().$width()-64, hdlr.$game().$height()-32);
			g.setFont(new Font("Lucida Console", Font.BOLD, 10));
			g.drawString(hdlr.$inv().$qu0().quantity+"",hdlr.$game().$width()-32-g.getFontMetrics().stringWidth(hdlr.$inv().$qu0().quantity+""),hdlr.$game().$height());
		}
		//g.setFont(new Font(hdlr.$game().$defaultFontName(), Font.PLAIN,12));
		g.setFont(hdlr.$game().$defaultFont());
		g.setColor(Color.WHITE);
		if(hdlr.$game().debug){
			g.drawString("HP: "+(int)((float)health*100/(float)maxHealth)+"%",2,24);//+"% ATTACK LVL: "+chain+" SHIELD DMG: "+storedDmg+" AC: "+AC, 2, 26);
		//g.drawString("Kills: "+kills,2,40); //NO DEBUG
		//g.drawString("Kills: "+kills+" Current World: "+hdlr.$currentWorld().$worldLabel(),2,40);//+" DEBUG:q0: "+q0+" DEBUG:oldHealth: "+oldHealth,2,40);
			g.drawString("Kills: "+kills+" Current World: "+hdlr.$currentWorld().$worldLabel()+" x: "+Math.round((this.x/32)*10.0)/10.0+" y: "+Math.round((this.y/32)*10.0)/10.0,2,36);//+" Current Target: "+this.target,2,40);
		}
	}
	private BufferedImage currentAnimation() {
		if(attacking){
			if(altAttack){
				if(this.direction==0)
					return armored?asd.$currentFrame():ad.$currentFrame();
				else if(this.direction==1)
					return armored?asr.$currentFrame():ar.$currentFrame();
				else if(this.direction==2)
					return armored?asu.$currentFrame():au.$currentFrame();
				else
					return armored?asl.$currentFrame():al.$currentFrame();
			}else{
				if(this.direction==0)
					return armored?asd.$currentFrame():asd0.$currentFrame();
				else if(this.direction==1)
					return armored?asr.$currentFrame():asr0.$currentFrame();
				else if(this.direction==2)
					return armored?asu.$currentFrame():asu0.$currentFrame();
				else
					return armored?asl.$currentFrame():asl0.$currentFrame();
			}
		}else{
			if(this.xMove!=0||this.yMove!=0){
				if(this.direction==3) {
					return armored?sl.$currentFrame():sl0.$currentFrame();
				} else if (this.direction==1) {
					return armored?sr.$currentFrame():sr0.$currentFrame();
				} else if (this.direction==2) {
					return armored?su.$currentFrame():su0.$currentFrame();
				} else {
					return armored?sd.$currentFrame():sd0.$currentFrame();
				}
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
			/*
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
			}*/
		}
	}
	private int adrenalineTick=0;
	private boolean surge=false,overdose=false;
	public void cureEffects(){
		adrenalineTick=0;
		surge=false;
		overdose=false;
	}
	private void surge(){
		if(adrenalineTick>0){
			if(!surge){
				this.health/=2;
				damage(1,"Vial of Methylenedioxyphenethylamine",false);
			}
			if(adrenalineTick>1500){
				health--;
				overdose=true;
			}else if(health<1001){
				if(overdose){
					if(adrenalineTick%60==0)
						health++;
				}else{
					if(stamina>251)
						health++;
					else{
						if(adrenalineTick%5==0)
							health++;
					}
				}
			}else if(health<2001){
				if(!overdose&&stamina>251){
					if(adrenalineTick%2==0)
						health++;
				}
			}
			if(adrenalineTick%2==0)
				stamina--;
			surge=true;
			adrenalineTick--;
		}else{
			surge=false;
			overdose=false;
		}
	}
	private void drawSurge(Graphics g){
		if(adrenalineTick%60<5)
			Utils.drawTranslucentImage(Assets.rage,0,0,0.45F,hdlr.$game().$width(),hdlr.$game().$height(),g);
		else if(adrenalineTick%60<10)
			Utils.drawTranslucentImage(Assets.rage,0,0,0.55F,hdlr.$game().$width(),hdlr.$game().$height(),g);
		else if(adrenalineTick%60<15)
			Utils.drawTranslucentImage(Assets.rage,0,0,0.45F,hdlr.$game().$width(),hdlr.$game().$height(),g);
		else if(adrenalineTick%60<20)
			Utils.drawTranslucentImage(Assets.rage,0,0,0.35F,hdlr.$game().$width(),hdlr.$game().$height(),g);
		else if(adrenalineTick%60<25)
			Utils.drawTranslucentImage(Assets.rage,0,0,0.45F,hdlr.$game().$width(),hdlr.$game().$height(),g);
		else if(adrenalineTick%60<30)
			Utils.drawTranslucentImage(Assets.rage,0,0,0.55F,hdlr.$game().$width(),hdlr.$game().$height(),g);
		else if(adrenalineTick%60<35)
			Utils.drawTranslucentImage(Assets.rage,0,0,0.45F,hdlr.$game().$width(),hdlr.$game().$height(),g);
		else
			Utils.drawTranslucentImage(Assets.rage,0,0,0.35F,hdlr.$game().$width(),hdlr.$game().$height(),g);
	}
	public void snortCrack(int u){
		adrenalineTick+=u;
	}
	private void attack(){
		if(meleeCooldown)
			return;
		//altAttack=!altAttack;
		meleeCooldown=true;
		int u=storedDmgTick!=0||addDmg!=0||surge?32:20;
		Rectangle ar=new Rectangle(u,u),cb=$collisionBounds(0,0);
		if(this.direction==0){
			//down
			ar.x=cb.x+cb.width/2-u/2;
			ar.y=cb.y+cb.height;
			if(zwei){
				ar.x-=16;
				ar.y-=16;
				ar.width*=2;
				ar.height*=2;
			}
		}else if(this.direction==1){
			//right
			ar.x=cb.x+cb.width;
			ar.y=cb.y+u/4;//-cb.height/2-u/2;
			if(zwei){
				ar.x-=16;
				ar.y-=16;
				ar.width*=2;
				ar.height*=2;
			}
		}else if(this.direction==2){
			//up
			ar.x=cb.x+cb.width/2-u/2;
			ar.y=cb.y-u;
			if(zwei){
				ar.x-=16;
				ar.y-=16;
				ar.width*=2;
				ar.height*=2;
			}
		}else{
			//left
			ar.x=cb.x-u;
			ar.y=cb.y+u/4;//-cb.height/2-u/2;
			if(zwei){
				ar.x-=16;
				ar.y-=16;
				ar.width*=2;
				ar.height*=2;
			}
		}
		int k=10;
		boolean chained=false;
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.particle||e.flora)
				continue;
			if(e.$collisionBounds(0,0).intersects(ar)){
				boolean wasAlive=e.alive;
				//??CHECK EVERY COORD VAR WILL BELONG TO Entity e
				int dmg=damage+power+(storedDmgTick>0?counterDmg*2:0)+((chain>25)?50:chain*2)+(surge?500+adrenalineTick/2:0)+addDmg+(int)(Math.random()*incDamage+1);
				if(zwei)
					dmg+=150+(int)(Math.random()*150);
				e.damage(dmg,this.label,false);
				if(addDmg>0&&wasAlive&&!e.alive&&!e.object&&!e.ghost){
					healTick+=dmg/3;
					hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,this.x,this.y,"Brutality Bonus! +"+(int)((float)(dmg)/30.0F)+"% HP",Color.WHITE,12));
				}
				if(!chained){
					chain+=(chain>24)?0:1;
					chainCD=120;
					chained=true;
				}
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
				//return;
			}
				
		}
		//storedDmg=0;
		//storedDmgTick=0;
		if(addDmg>149){
			hdlr.$currentWorld().em.addEntity(new WindSlash(hdlr,this.x,this.y,this.label,this.direction));
		}
		counterDmg=0;
		addDmg=0;
			
	}
	
	public void resetPlayerVar(){
		speed=maxSpeed;
		damage=maxDamage;
		label="PLAYER";
		health=totalHealth;
		maxHealth=health;
		oldHealth=health;
		target="IDLEMINDSARETHEDEVILSWORKSHOP";
		this.AC=35;
		this.ally=1;
		this.fleshy=true;
		armored=false;
		marked=true;
		kills=0;
		weightLim=100.0F;
		weight=0.0F;
		maxSpeed=2.5F;
		U=0;
		stamina=1000;
		incDamage=50;
		oldHealth=0;
		energy=100;
		eRegenCD=1;
		enTick=0;
		maxDamage=50;
		blastLim=0;
		bt=0;
		regenCD=180;
		rcd=0;
		regenLim=5;
		rt=0;
		deathTick=0;
		dying=false;
		addDmg=0;
		chain=0;
		cooldown=0;
		chainCD=0;
		charging=false;
		blocking=false;
		counterCD=0;
		storedDmg=0;
		counterDmg=0;
		storedDmgTick=0;
		shieldHP=350;
		boostStamina=0;
		pItem="";
		pITick=180;
		pitLim=180;
		num=0;
		t=0.000001F;
		c=0.996F;
		atick=0;
		hj=0;
		throwProjectile=false;
		grenade=false;
		gibbed=false;
		DmgF=0.0f;
		q0=0;
		adrenalineTick=0;
		surge=false;
		overdose=false;
		healTick=0;
		fireTick=0;
		power=0;
		bleed=false;
	}
	
	public void die() {
		this.ally=256;
		this.label=new String("DEAD_CORPSE");
		dying=true;
		active=true;
		//State.setState(hdlr.$game().$stateManager().$death());
	}
	
}
