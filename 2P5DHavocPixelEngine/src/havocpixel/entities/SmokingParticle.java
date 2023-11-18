package havocpixel.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import havocpixel.main.Handler;
import havocpixel.util.Utils;

public class SmokingParticle extends FloatingString{

	private int dir,dx=0,dy=0,t=0,lim;
	public SmokingParticle(Handler handler, float x, float y) {
		super(handler, x, y, "\n\n.",Color.RED,12);
		label="PARTICLE";
		object=true;
		int u=3;
		this.dir=(int)(Math.random()*8);
		if(dir==0){
			dx+=u;
		}else if(dir==1){
			dy+=1;
		}else if(dir==2){
			dx-=u;
		}else if(dir==3){
			dy-=u;
		}else if(dir==4){
			dx+=u;
			dy+=1;
		}else if(dir==5){
			dx-=u;
			dy-=u;
		}else if(dir==6){
			dx-=u;
			dy+=1;
		}else if(dir==7){
			dx+=u;
			dy-=u;
		}
		lim=15+(int)(Math.random()*14);
	}

	public void tick(){
		if(t>lim){
			this.active=false;
		}
		t++;
		if(dx!=0){
			dy+=(int)(0.05F*t);
		}
		this.x+=dx;
		this.y+=dy;//+(2*t);
		//if(t%2==1)
			hdlr.$currentWorld().em.addEntity(new Smoke(hdlr,this.x,this.y));
	}
	public void render(Graphics g) {
		g.setFont(new Font(g.getFont().getName(),Font.BOLD,12));
		//g.setColor(c);
		//g.drawString(l,(int)(x+14-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()));
		Utils.drawStringWithOutline(g,l,(int)(x+14-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset())+16,c);
	}
}
