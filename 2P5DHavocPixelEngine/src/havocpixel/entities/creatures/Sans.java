package havocpixel.entities.creatures;

import havocpixel.entities.items.HotDog;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Sans extends Creature{
	private Animation up, left, down, right, idle;
	private int GBcooldown=120,q=0;
	private int regenCooldown=50,rt=0;
	public Sans(Handler hdlr, float x, float y) {
		super(hdlr, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		speed=1;
		this.AC=99;
		health=666;
		maxHealth=666;
		label="UNIQUE;sans";
		target="NONE";
		up = new Animation(hdlr,200,Assets.sansUp);
		left = new Animation(hdlr,200,Assets.sansLeft);
		down = new Animation(hdlr,200,Assets.sansDown);
		right = new Animation(hdlr,200,Assets.sansRight);
		idle = new Animation(hdlr,200,Assets.sansIdle);
	}

	private Random r = new Random();
	private int moveTick=0;
	private int rndInt(int k) {
		return r.nextInt(k);
	}
	public void tick() {
		if(rt>regenCooldown&&health<666){
			this.health++;
			rt=0;
		}
		rt++;
		q++;
		if(hurt){
			attacking=false;
			if(damageTick<DL){
				damageTick++;
				return;
			}else{
				damageTick=0;
				hurt=false;
			}
		}
		up.tick();
		left.tick();
		down.tick();
		right.tick();
		idle.tick();
		
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
		move();
		if(q>GBcooldown){
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
			for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
				if(e.equals(this))
					continue;
				if(e.$collisionBounds(0,0).intersects(ar)&&e.label.equals(this.target)){
					int x=0,y=0;
					if(this.direction==0){
						//down
						x=(int)this.x-16;
						y=(int)this.y+height;
					}else if(this.direction==1){
						//right
						x=(int)this.x+width;
						y=(int)this.y-16;
					}else if(this.direction==2){
						//up
						x=(int)this.x-16;
						y=(int)this.y-64;
					}else{
						//left
						x=(int)this.x-64;
						y=(int)this.y-16;
					}
					try{
						hdlr.$currentWorld().em.addEntity(new GasterBlaster(hdlr,x,y,this.direction));
					}catch(Exception ex){
						
					}
					q=0;
					return;
				}
			}
		}
	}

	public void render(Graphics g) {
		renderHP(g);
		if (hurt) {
			g.drawImage(Assets.sansHurt,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
			return;
		}
		g.drawImage(currentAnimation(),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
	}
	private BufferedImage currentAnimation() {
		if(xMove<0) {
			return left.$currentFrame();
		} else if (xMove>0) {
			return right.$currentFrame();
		} else if (yMove<0) {
			return up.$currentFrame();
		} else if (yMove>0) {
			return down.$currentFrame();
		} else {
			return idle.$currentFrame();
		}
	}
	@Override
	public void die() {
		hdlr.$currentWorld().em.addEntity(new HotDog(hdlr,x,y,1));
	}
}
