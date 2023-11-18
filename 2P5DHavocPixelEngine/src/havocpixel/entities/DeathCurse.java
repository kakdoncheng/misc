package havocpixel.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

public class DeathCurse extends Entity{
	//homing curse that deals insane damage to a single target

	protected String owner;
	public DeathCurse(Handler handler,float x,float y,String owner,String target) {
		super(handler, x, y, 32, 32, 1);
		bounds=new Rectangle(8,8,16,16);
		health=1;
		damage=500;
		speed=6;
		label="CURSE:POWERWORDKILL";
		this.owner=owner;
		this.target=target;
		this.object=true;
		this.particle=true;
	}

	@Override
	public void tick() {//d0r1u2l3
		health=1;
		hdlr.$currentWorld().em.addEntity(new BlackFog(hdlr,x+16,y));
		boolean hit=false;
		for(Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.label.equals(owner)||e.label.equals("DEAD_CORPSE")||e.label.equals("DEAD_HUMAN_CORPSE")||e.item||e.object||e.particle)
				continue;
			if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))&&e.label.equals(target)){
				e.damage(damage+(int)(Math.random()*damage+1),this.owner,true);
				this.active=false;
				hdlr.$currentWorld().em.addEntity(new Poof(hdlr,this.x,this.y,true));
			}
			if(e.label.equals(target)){
				hit=true;
				int ex=(int)e.x,ey=(int)e.y,tx=(int)this.x,ty=(int)this.y;
				xMove=0;
				yMove=0;
				if(ex>tx){
					if(ex-tx<speed)
						x+=ex-tx;
					else
						x+=speed;
					this.direction=3;
				}else if(ex<tx){
					if(ex-tx>-speed)
						x+=ex-tx;
					else
						x+=-speed;
					this.direction=1;
				}
				if(ey>(int)ty){
					if(ey-ty<speed)
						y+=ey-ty;
					else
						y+=speed;
					this.direction=0;
				}else if(ey<ty){
					if(ey-ty>-speed)
						y+=ey-ty;
					else
						y+=-speed;
					this.direction=2;
				}
			}
		}
		if(!hit){
			this.active=false;
			hdlr.$currentWorld().em.addEntity(new Poof(hdlr,this.x,this.y,true));
		}
	}

	@Override
	public void render(Graphics g) {
		if(this.direction==0){
			Utils.drawTranslucentImage(Assets.gd01,
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),0.4F,
					width,
					height,g);
		}else if(this.direction==1){
			Utils.drawTranslucentImage(Assets.gr01,
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),0.4F,
					width,
					height,g);
		}else if(this.direction==2){
			Utils.drawTranslucentImage(Assets.gu01,
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),0.4F,
					width,
					height,g);
		}else{
			Utils.drawTranslucentImage(Assets.gl01,
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),0.4F,
					width,
					height,g);
		}
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
