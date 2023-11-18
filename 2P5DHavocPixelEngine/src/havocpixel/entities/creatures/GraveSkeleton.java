package havocpixel.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class GraveSkeleton extends Skeleton{

	
	public GraveSkeleton(Handler hdlr, float x, float y, String label0) {
		super(hdlr, x, y, 150, true, label0);
		
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		label=label0;
		target="PLAYER";
		maxHealth=150;
		speed=1;
		damage=30;
		cx=x;
		cy=y;
		this.AC=0;

		su0=new Animation(hdlr,120,Assets.gsUp0);
		sl0=new Animation(hdlr,120,Assets.gsLeft0);
		sd0=new Animation(hdlr,120,Assets.gsDown0);
		sr0=new Animation(hdlr,120,Assets.gsRight0);
		sui0=new Animation(hdlr,240,Assets.gsiU0);
		sli0=new Animation(hdlr,240,Assets.gsiL0);
		sdi0=new Animation(hdlr,240,Assets.gsiD0);
		sri0=new Animation(hdlr,240,Assets.gsiR0);
		asu0=new Animation(hdlr,70,Assets.gAsUp0);
		asl0=new Animation(hdlr,70,Assets.gAsLeft0);
		asd0=new Animation(hdlr,70,Assets.gAsDown0);
		asr0=new Animation(hdlr,70,Assets.gAsRight0);
		spawn=new Animation(hdlr,120,Assets.emerge);
		
		//hU=Assets.gshU0;
		//hL=Assets.gshL0;
		//hD=Assets.gshD0;
		//hR=Assets.gshR0;
		hU=Assets.gsiU0[1];
		hL=Assets.gsiL0[1];
		hD=Assets.gsiD0[1];
		hR=Assets.gsiR0[1];
		armored=false;
	}
	int aTick=0;
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
			if(Math.random()>0.89F){
				//simple offensive AI idea
				//bigger sight/hearing rectangle;
				//if player is within the rectangle go to the player's coordinate
				
				//check if the player is within any of the directions;
				//if player intersects the rectangle for direction 0,
				//	turn to direction 0&launch an attack;
				int u=20;
				int thr=0,lim=20;
				Rectangle ar0=new Rectangle(u,u),//down
						ar1=new Rectangle(u,u),//right
						ar2=new Rectangle(u,u),//up
						ar3=new Rectangle(u,u),//left
						cb=$collisionBounds(0,0);
				//down
				ar0.x=cb.x+cb.width/2-u/2;
				ar0.y=cb.y+cb.height;
				//right
				ar1.x=cb.x+cb.width;
				ar1.y=cb.y-cb.height/2+u/2;
				//up
				ar2.x=cb.x+cb.width/2-u/2;
				ar2.y=cb.y-u;
				//left
				ar3.x=cb.x-u;
				ar3.y=cb.y-cb.height/2+u/2;
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
			attack();
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

	//public void die(){
		
	//}
}
