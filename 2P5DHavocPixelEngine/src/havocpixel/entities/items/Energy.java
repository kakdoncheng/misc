package havocpixel.entities.items;

import havocpixel.entities.Poof;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Graphics;

public class Energy extends Item{

	//FINISH THIS CLASS
	//USE AS BASE FOR BONUS PICKUPS
	private Animation i;
	public Energy(Handler handler, float x, float y) {
		super(handler, x, y, Assets.bGib, "Energy", 1, 1.0F);
		weapon=false;
		item=true;
		qLim=1;
		pickup=true;
		i=new Animation(hdlr,60,Assets.energyBall);
	}

	public void onPickup(){
		hdlr.$player().power+=1;
		hdlr.$player().healTick+=25;
		hdlr.$currentWorld().em.addEntity(new Poof(hdlr,this.x,this.y+dy,true));
	}
	
	@Override
	public void render(Graphics g) {
		i.tick();
		Utils.drawTranslucentImage(i.$currentFrame(),
				(int)(x-hdlr.$camera().$xOffset()),
				(int)(y-hdlr.$camera().$yOffset()),0.60F,
				32,
				32,g);
	}
	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		// TODO Auto-generated method stub
	}

}
