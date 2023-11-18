package havocpixel.entities;

import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FloatingString extends Entity{
	protected String l;
	protected int duration=0;
	protected Color c;
	private int size=12;
	float dy;
	float dx;

	public FloatingString(Handler handler, float x, float y,String display,Color c,int s) {
		super(handler, x, y, 0, 0, 1);
		bounds=new Rectangle(0,0,32,32);
		health=1;
		damage=0;
		label="LABEL:FLOATING_STRING";
		size=s;
		l=display;
		this.c=c;
		//object=true;
		dx=x;
		dy=y;
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		dy-=0.5;
		if(duration>30){
			active=false;
		}
		duration++;
	}

	@Override
	public void render(Graphics g) {
		g.setFont((size>12)?new Font("ReservoirGrunge",Font.BOLD,size):new Font(g.getFont().getName(),Font.BOLD,size));
		//g.setColor(c);
		//g.drawString(l,(int)(x+14-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()));
		Utils.drawStringWithOutline(g,l,(int)(dx-hdlr.$camera().$xOffset()-(g.getFontMetrics().stringWidth(l)/2)),(int)(dy-hdlr.$camera().$yOffset()),c);
		g.setFont(hdlr.$game().$defaultFont());
	}
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
