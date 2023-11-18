package havocpixel.entities;
import java.awt.Color;
import java.awt.Graphics;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Smoke extends FloatingString{

	private Animation smoke;
	private float dy=0.5F;
	public Smoke(Handler handler, float x, float y) {
		super(handler, x, y," ",Color.RED,12);
		label="LABEL:FLOATING_STRING";
		smoke=new Animation(hdlr,80,Assets.smoke);
		object=true;
	}
	public Smoke(Handler handler, float x, float y,float rise) {
		super(handler, x, y," ",Color.RED,12);
		label="LABEL:FLOATING_STRING";
		smoke=new Animation(hdlr,80,Assets.smoke);
		object=true;
		dy=rise;
	}
	
	int u=0;
	float er=(float)(Math.random()*1.75F);
	public void tick() {
		x+=er;
		y-=dy;
		if(u==5){
			active=false;
		}
		u=smoke.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(smoke.$currentFrame(),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
	}

}
