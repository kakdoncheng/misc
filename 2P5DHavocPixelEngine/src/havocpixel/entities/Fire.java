package havocpixel.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Fire extends Entity{

	private String owner;
	private Animation ani;
	private int dur,lim;
	public Fire(Handler handler,float x,float y,String owner) {
		super(handler, x, y,32,32,1);
		label="Fire";
		this.owner=owner;
		ani=new Animation(hdlr,80,Assets.fire0);
		this.bounds=new Rectangle(3,6,26,26);
		this.damage=20;
		this.object=true;
		this.particle=true;
		dur=0;
		lim=150+(int)(Math.random()*151);
	}

	@Override
	public void tick() {
		ani.tick();
		dur++;
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.label.equals("PROJECTILE:INFERNO_FIREBALL")||e.particle||e.flora||e.label.equals("Gib"))
				continue;
			if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))){
				if(!e.isOnFire()){
					e.damage(this.damage+(int)(Math.random()*26),this.owner,false);
					e.setOnFire();
				}
			}
			
		}
		if(dur>lim)
			active=false;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ani.$currentFrame(),
				(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
	}

	@Override
	public void die() {
		;
	}

}
