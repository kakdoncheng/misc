package havocpixel.entities.creatures;

import java.awt.Rectangle;

import havocpixel.entities.ShotArrow;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class ArcherSkeleton extends GraveSkeleton{

	public ArcherSkeleton(Handler hdlr, float x, float y, String label0) {
		super(hdlr, x, y, label0);
		
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		label=label0;
		target="PLAYER";
		maxHealth=150;
		speed=2;
		damage=1;
		cx=x;
		cy=y;
		this.AC=0;
		
		su0=new Animation(hdlr,120,Assets.agsUp0);
		sl0=new Animation(hdlr,120,Assets.agsLeft0);
		sd0=new Animation(hdlr,120,Assets.agsDown0);
		sr0=new Animation(hdlr,120,Assets.agsRight0);
		sui0=new Animation(hdlr,240,Assets.agsiU0);
		sli0=new Animation(hdlr,240,Assets.agsiL0);
		sdi0=new Animation(hdlr,240,Assets.agsiD0);
		sri0=new Animation(hdlr,240,Assets.agsiR0);
		asu0=new Animation(hdlr,180,Assets.agAsUp0);
		asl0=new Animation(hdlr,180,Assets.agAsLeft0);
		asd0=new Animation(hdlr,180,Assets.agAsDown0);
		asr0=new Animation(hdlr,180,Assets.agAsRight0);
		spawn=new Animation(hdlr,120,Assets.aemerge);
		
		//hU=Assets.agshU0;
		//hL=Assets.agshL0;
		//hD=Assets.agshD0;
		//hR=Assets.agshR0;
		
		//hurt animations same as idle
		hU=Assets.agsiU0[1];
		hL=Assets.agsiL0[1];
		hD=Assets.agsiD0[1];
		hR=Assets.agsiR0[1];
		
		armored=false;
		meleeCooldown=true;
	}
	int aTick=0;
	boolean thrownProjectile=false;
	public void tick() {
		lookForTarget();
		stunTick++;
		aTick++;
		if(!targetIsActive())
			this.target=new String("PLAYER");
		if(aTick>5){
			if(!attacking){
				if(health<maxHealth)
					health++;
				aTick=0;
			}
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
		if(hurt){
			aTick=0;
			attacking=false;
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
			if(Math.random()>0.97F){
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
						thrownProjectile=true;
						return;
					}
				}
			}
		}
		if(attack&&!isStunned()){
			aTick=0;
			if(thrownProjectile){
				meleeCooldown=true;
				hdlr.$currentWorld().em.addEntity(
						new ShotArrow(hdlr,this.x,this.y,
								hdlr.$mm().$x()-(int)hdlr.$camera().$xOffset(),
								hdlr.$mm().$y()-(int)hdlr.$camera().$xOffset(),label,this.direction));
			}else{
				attack();
			}
			thrownProjectile=false;
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
			//attempts to sense the targets fear; if so, will stupidly chase down target;
			if(angry&&!attacking){
				Rectangle ar=new Rectangle((int)(this.x-224),(int)(this.y-224),480,480);
				for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
					if(e.equals(this)||e.particle)
						continue;
					if(e.$collisionBounds(0,0).intersects(ar)){
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

}
