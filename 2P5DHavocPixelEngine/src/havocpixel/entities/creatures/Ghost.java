package havocpixel.entities.creatures;

import havocpixel.entities.Poof;
import havocpixel.entities.items.AdrenalineShot;
import havocpixel.entities.items.HealthPotion;
import havocpixel.entities.items.RealHealthPotion;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Ghost extends Skeleton{

	private boolean cloak,hasPossessed;
	private int alt;
	private BufferedImage u,l,d,r,au,al,ad,ar,hu,hl,hd,hr;
	protected int dy=0,t=0;
	protected float alpha=0.0F;
	public Ghost(Handler hdlr, float x, float y, String label0, boolean hasPossessed) {
		super(hdlr, x, y, 2, true, label0);
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		label=label0;
		
		speed=3;
		damage=10;
		alt=(int)(Math.random()*5);
		//alt=1;
		if(alt==0){
			hu=Assets.ghu;
			hl=Assets.ghl;
			hd=Assets.ghu;
			hr=Assets.ghr;
			u=Assets.gu;
			l=Assets.gl;
			d=Assets.gd;
			r=Assets.gr;
			au=Assets.gAu;
			al=Assets.gAl;
			ad=Assets.gAd;
			ar=Assets.gAr;
		}else if(alt==1){
			hu=Assets.ghu01;
			hl=Assets.ghr01;
			hd=Assets.ghu01;
			hr=Assets.ghl01;
			u=Assets.gu01;
			l=Assets.gr01;
			d=Assets.gd01;
			r=Assets.gl01;
			au=Assets.gAu01;
			al=Assets.gAr01;
			ad=Assets.gAd01;
			ar=Assets.gAl01;
		}else if(alt==2){
			hu=Assets.ghu012;
			hl=Assets.ghr012;
			hd=Assets.ghu012;
			hr=Assets.ghl012;
			u=Assets.gu012;
			l=Assets.gr012;
			d=Assets.gd012;
			r=Assets.gl012;
			au=Assets.gAu012;
			al=Assets.gAr012;
			ad=Assets.gAd012;
			ar=Assets.gAl012;
		}else if(alt==3){
			hu=Assets.ghu2;
			hl=Assets.ghr2;
			hd=Assets.ghu2;
			hr=Assets.ghl2;
			u=Assets.gu2;
			l=Assets.gr2;
			d=Assets.gd2;
			r=Assets.gl2;
			au=Assets.gAu2;
			al=Assets.gAr2;
			ad=Assets.gAd2;
			ar=Assets.gAl2;
		}else if(alt==4){
			hu=Assets.ghu3;
			hl=Assets.ghr3;
			hd=Assets.ghu3;
			hr=Assets.ghl3;
			u=Assets.gu3;
			l=Assets.gr3;
			d=Assets.gd3;
			r=Assets.gl3;
			au=Assets.gAu3;
			al=Assets.gAr3;
			ad=Assets.gAd3;
			ar=Assets.gAl3;
		}
		this.health=1;
		maxHealth=1;
		this.cloak=true;
		this.angry=true;
		this.AC=0;
		this.ghost=true;
		this.ally=0;
		this.hasPossessed=hasPossessed;
		if(!hasPossessed)
			target="DEAD_HUMAN_CORPSE";
		else
			target="PLAYER";
		// TODO Auto-generated constructor stub
	}
	int aTick=0;
	public void tick() {
		//burnTargetToMemory();
		lookForTarget();
		if(t<480){
			t++;
		}else{
			t=0;
			dy--;
		}
		if(t%3==0){
			if(t%60<30)
				dy--;
			else
				dy++;
		}
		//lookForNewTarget
		//SEARCH FOR DEAD BODIES
		if(!targetIsActive()){
			if(!hasPossessed){
				this.target=new String("DEAD_HUMAN_CORPSE");
			}else{
				this.target=new String("PLAYER");
			}
		}
		stunTick++;
		aTick++;
		if(aTick>60){
			if(!attacking){
				aTick=0;
			}
		}
		if(cloak){
			if(alpha<0.50F){
				alpha+=0.01F;
			}else{
				cloak=false;
			}
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
						if(rndInt(lim)>thr){
							if(!hasPossessed&&this.target.equals("DEAD_HUMAN_CORPSE")&&e.label.equals("DEAD_HUMAN_CORPSE")){
								e.possessed=true;
								this.active=false;
							}else{
								attacking=true;
							}
						}
						return;
					}
				}else if(e.$collisionBounds(0,0).intersects(ar1)){
					if(e.label.equals(this.target)||(!targetFound&&e.ally==1)){
						this.direction=1;
						if(rndInt(lim)>thr){
							if(!hasPossessed&&this.target.equals("DEAD_HUMAN_CORPSE")&&e.label.equals("DEAD_HUMAN_CORPSE")){
								e.possessed=true;
								this.active=false;
							}else{
								attacking=true;
							}
						}
						return;
					}
				}else if(e.$collisionBounds(0,0).intersects(ar2)){
					if(e.label.equals(this.target)||(!targetFound&&e.ally==1)){
						this.direction=2;
						if(rndInt(lim)>thr){
							if(!hasPossessed&&this.target.equals("DEAD_HUMAN_CORPSE")&&e.label.equals("DEAD_HUMAN_CORPSE")){
								e.possessed=true;
								this.active=false;
							}else{
								attacking=true;
							}
						}
						return;
					}
				}else if(e.$collisionBounds(0,0).intersects(ar3)){
					if(e.label.equals(this.target)||(!targetFound&&e.ally==1)){
						this.direction=3;
						if(rndInt(lim)>thr){
							if(!hasPossessed&&this.target.equals("DEAD_HUMAN_CORPSE")&&e.label.equals("DEAD_HUMAN_CORPSE")){
								e.possessed=true;
								this.active=false;
							}else{
								attacking=true;
							}
						}
						return;
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
								else if(ex-tx==2)
									xMove=2;
								else
									xMove=speed;
							}else if(ex<tx){
								if(ex-tx==-1)
									xMove=-1;
								else if(ex-tx==-2)
									xMove=-2;
								else
									xMove=-speed;
							}
							if(ey>(int)ty){
								if(ey-ty==1)
									yMove=1;
								else if(ey-ty==2)
									yMove=2;
								else
									yMove=speed;
							}else if(ey<ty){
								if(ey-ty==-1)
									yMove=-1;
								else if(ey-ty==-2)
									xMove=-2;
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
		//renderHP(g);
		if(hurt){
			if(this.direction==0){
				Utils.drawTranslucentImage(ad,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha+0.1F,
						width,
						height,g);
			}else if(this.direction==1){
				Utils.drawTranslucentImage(ar,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha+0.1F,
						width,
						height,g);
			}else if(this.direction==2){
				Utils.drawTranslucentImage(au,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha+0.1F,
						width,
						height,g);
			}else{
				Utils.drawTranslucentImage(al,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha+0.1F,
						width,
						height,g);
			}
			/*if(this.direction==0){
				//down
				g.drawImage(hd,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset())+dy,width,height,null);
			}else if(this.direction==1){
				//right
				g.drawImage(hr,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset())+dy,width,height,null);
			}else if(this.direction==2){
				//up
				g.drawImage(hu,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset())+dy,width,height,null);
			}else{
				//left
				g.drawImage(hl,
						(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset())+dy,width,height,null);
			}*/
			return;
		}
		if(attacking){
			if(this.direction==0){
				Utils.drawTranslucentImage(ad,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha+0.1F,
						width,
						height,g);
			}else if(this.direction==1){
				Utils.drawTranslucentImage(ar,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha+0.1F,
						width,
						height,g);
			}else if(this.direction==2){
				Utils.drawTranslucentImage(au,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha+0.1F,
						width,
						height,g);
			}else{
				Utils.drawTranslucentImage(al,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha+0.1F,
						width,
						height,g);
			}
		}else{
			if(this.direction==0){
				Utils.drawTranslucentImage(d,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha,
						width,
						height,g);
			}else if(this.direction==1){
				Utils.drawTranslucentImage(r,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha,
						width,
						height,g);
			}else if(this.direction==2){
				Utils.drawTranslucentImage(u,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha,
						width,
						height,g);
			}else{
				Utils.drawTranslucentImage(l,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset())+dy,alpha,
						width,
						height,g);
			}
		}
	}
	public void die(){
		hdlr.$currentWorld().em.addEntity(new Poof(hdlr,this.x,this.y+dy,true));
		//hdlr.$world().em.addEntity(new GlassOfMilk(hdlr,this.x,this.y,(int)(Math.random()*4)-2));
		hdlr.$currentWorld().em.addEntity(new HealthPotion(hdlr,this.x,this.y,(int)(Math.random()*11)-9));
		hdlr.$currentWorld().em.addEntity(new RealHealthPotion(hdlr,this.x,this.y,(int)(Math.random()*21)-19));
		hdlr.$currentWorld().em.addEntity(new AdrenalineShot(hdlr,this.x,this.y,(int)(Math.random()*11)-9));
	}

}
