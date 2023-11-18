package havocpixel.entities;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Poof extends Entity{

	protected Animation u;
	private boolean t;
	protected int k;
	public Poof(Handler handler, float x, float y,boolean translucent) {
		super(handler, x, y, 0, 0, 1);
		bounds=new Rectangle(0,0,32,32);
		health=1;
		damage=0;
		//object=true;
		item=true;
		particle=true;
		
		u=new Animation(handler,60,Assets.poof);
		label="LABEL:POOF";
		t=translucent;
		k=0;
	}
	public void tick(){
		k=u.tick();
		if(k>6){
			//System.out.println("POOF "+x/32+" "+y/32);
			active=false;
		}
	}
	public void render(Graphics g){
		if(!t){
			g.drawImage(u.$currentFrame(),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),32,32,null);
		}else{
			Utils.drawTranslucentImage(u.$currentFrame(),
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),0.5F,
					32,
					32,g);
		}
		;
	}
	@Override
	public void die() {
		
	}

}
