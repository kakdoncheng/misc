package havocpixel.entities;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class BloodSlash extends Poof{

	public BloodSlash(Handler hdlr, float x, float y) {
		super(hdlr, x, y, false);
		u=new Animation(hdlr,120,Assets.ouch[(int)(Math.random()*4)]);
		label="LABEL:FLOATING_STRING";
	}

	public void tick(){
		k=u.tick();
		if(k>2){
			//System.out.println("POOF "+x/32+" "+y/32);
			active=false;
		}
	}
}
