package havocpixel.entities;

import havocpixel.Game;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FloatingString extends Entity{

	private String msg;
	private int size;
	private Color c;
	
	protected double maxDuration,duration;
	protected int dy;
	
	public FloatingString(Game game, double x, double y, String msg, Color c) {
		super(game, x, y);
		bounds=new Rectangle(0,0,32,32);
		health=1;
		maxHealth=1;
		name="Floating String";
		active=true;
		particle=true;
		this.msg=msg;
		maxDuration=1.0;
		duration=maxDuration;
		dy=16;
		size=8;
		this.c=c;
	}

	@Override
	public void update(double dt) {
		duration-=dt;
		if(duration<0){
			active=false;
			return;
		}
		y-=(dy/maxDuration)*dt;
	}

	@Override
	public void render(Graphics g) {
		g.setFont((size>12)?new Font("ReservoirGrunge",Font.BOLD,size):new Font(g.getFont().getName(),Font.BOLD,size));
		//g.setColor(c);
		//g.drawString(l,(int)(x+14-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()));
		Utils.drawStringWithOutline(g, msg,(int)(x-game.$camera().$xOffset()-(g.getFontMetrics().stringWidth(msg)/2)),(int)(y-game.$camera().$yOffset()),c);
		g.setFont(game.$defaultFont());
	}

	@Override
	protected void die() {
		active=false;
	}

}
