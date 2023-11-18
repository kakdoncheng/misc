package havocpixel.entities;

import java.awt.Color;
import java.awt.Graphics;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

public class Soot extends KoolAid{

	public Soot(Handler handler, float x, float y) {
		super(handler, x, y);
		c=new Color(0x0a0a0a);
		if(Math.random()<0.65F)
			p=1+(int)(Math.random()*4);
		if(Math.random()<0.2F)
			c=new Color(0x202020);
		//e=1.4F;
		//alt=true;
	}
	public void tick(){
		if(duration>(660-lim)){//((hdlr.$game().$FPS()>59)?360-lim:((hdlr.$game().$FPS()*5)-(lim/2)))){
			a-=0.02F;
			if(a<0.02F)
				active=false;
			
		}
		duration++;
	}
	public void render(Graphics g){
		if(a<1)
			Utils.drawTranslucentImage(Assets.soot[p%2],
					(int)(x-hdlr.$camera().$xOffset())-16,
					(int)(y-hdlr.$camera().$yOffset())+8,a,
					64,
					32, g);
			else
				g.drawImage(Assets.soot[p%2],
						(int)(x-hdlr.$camera().$xOffset())-16,
						(int)(y-hdlr.$camera().$yOffset())+8,
						64,
						32,null);
		
	}

}
