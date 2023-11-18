package havocpixel.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.entities.Fireball;
import havocpixel.entities.items.RottenFlesh;
import havocpixel.entities.items.ScrapMetal;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Bunny extends Skeleton{

	protected Animation death,spawn;
	protected boolean dying=false;
	protected int deathTick=0;
	public Bunny(Handler hdlr, float x, float y) {
		super(hdlr,x,y,1,false,"BUNNY_"+Math.random());
		bounds.x = 10;
		bounds.y = 20;
		bounds.width = 12;
		bounds.height = 10;
		target="NO_TARGET";
		speed=3;
		damage=1;
		health=1;
		maxHealth=1;
		this.ally=255;
		this.AC=0;
		this.fleshy=true;
		this.armored=false;
		this.spawning=false;
		
		su0=new Animation(hdlr,120,Assets.bUp);
		sl0=new Animation(hdlr,120,Assets.bLeft);
		sd0=new Animation(hdlr,120,Assets.bDown);
		sr0=new Animation(hdlr,120,Assets.bRight);
		sui0=new Animation(hdlr,240,Assets.biU);
		sli0=new Animation(hdlr,240,Assets.biL);
		sdi0=new Animation(hdlr,240,Assets.biD);
		sri0=new Animation(hdlr,240,Assets.biR);
		
		death=new Animation(hdlr,120,Assets.bDeath);
		
		hU=Assets.bUp[1];
		hL=Assets.bLeft[1];
		hD=Assets.bDown[1];
		hR=Assets.bRight[1];
	}
	int aTick=0,hj=0;
	public void tick() {
		if(dying){
			if(health<-120){
				active=false;
				spawnBoneGibs();
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
		su0.tick();
		sl0.tick();
		sd0.tick();
		sr0.tick();
		sui0.tick();
		sli0.tick();
		sdi0.tick();
		sri0.tick();
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
	
	public void render(Graphics g) {
		if(dying){
			g.drawImage(death.$currentFrame(),
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
			g.drawImage(currentAnimation(),
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
	}
	
	public void die(){
		if(!dying){
			//if(Math.random()<0.35F)
			//	hdlr.$currentWorld().em.addEntity(new RottenFlesh(hdlr,x,y,1+(int)(Math.random()*3)));
			hdlr.$currentWorld().em.addEntity(new DecapitatedBunnyHead(hdlr,this.x-10,this.y+4));
		}
		this.ally=255;
		dying=true;
		this.label=new String("DEAD_CORPSE");
		active=true;
	}
}


