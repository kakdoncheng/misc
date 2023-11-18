package havocpixel.entities;

import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Color;

public class BlackFog extends FloatingString{

	public BlackFog(Handler hdlr, float x, float y) {
		super(hdlr,x,y+22,Utils.rndString((int)(Math.random()*3)+1),Color.black,12);
		// TODO Auto-generated constructor stub
	}
	public void tick() {
		// TODO Auto-generated method stub
		dy-=0.5;
		if(duration>10){
			active=false;
		}
		duration++;
	}
}
