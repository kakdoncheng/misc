package havocpixel.states;

import havocpixel.gfx.ImageLoader;
import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

public class ControlInfo extends State{

	public ControlInfo(Handler hdlr) {
		super(hdlr);
		back=ImageLoader.loadImage("/txr/controls0.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		if(b<120)
			return;
		if(hdlr.$km().ESC||hdlr.$km().ENTER){
			b=0;
			k=Math.random();
			State.setState(hdlr.$sm().$defWorld());
		}
	}

	private String fonts[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private int w=0,q=0,b=0;
	double k=Math.random();
	BufferedImage back;
	@Override
	public void render(Graphics g) {
		if(w>0){
			w--;
		}else{
			w=(int)(Math.random()*100)-85;
			q=(int)(Math.random()*fonts.length);
		}
		b++;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, hdlr.$game().$width(), hdlr.$game().$height());
		g.setColor(Color.WHITE);
		g.setFont(new Font(fonts[q], Font.PLAIN, 10));
		//g.setFont(new Font("ReservoirGrunge", Font.BOLD, 30));
		//g.drawString("CONTROLS", hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth("CONTROLS")/2), 60);
		
		//k=0.7;
		if(k<0.166D){
			String f="Do you think God stays in heaven";
			g.drawString(f, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(f)/2),hdlr.$game().$height()/2-6);
			String t="because he lives in constant fear of what he has created?";
			g.drawString(t, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(t)/2),hdlr.$game().$height()/2+6);
			//g.setFont(new Font("Lucida Console", Font.BOLD, 16));
		}else if(k<0.333D){
			String f="Speak of the devil, and he shall appear.";
			g.drawString(f, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(f)/2),hdlr.$game().$height()/2-6);
		}else if(k<0.5D){
			String f="Give someone a mask, and they will show you their true face.";
			g.drawString(f, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(f)/2),hdlr.$game().$height()/2-6);
		}else if(k<0.666D){
			String f="A samurai who smells of sunflowers? Sunflowers don’t have a scent.";
			g.drawString(f, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(f)/2),hdlr.$game().$height()/2-6);
			String t="So wouldn’t that mean the samurai you’re searching for… doesn’t even exist?";
			g.drawString(t, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(t)/2),hdlr.$game().$height()/2+6);
		}else if(k<0.833D){
			String f="Rome was not built in a day. Yet it was burnt down in one.";
			g.drawString(f, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(f)/2),hdlr.$game().$height()/2-6);
		}else{
			String f="Everyone around you is fighting a battle";
			g.drawString(f, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(f)/2),hdlr.$game().$height()/2-6);
			String t="that you know nothing about.";
			g.drawString(t, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(t)/2),hdlr.$game().$height()/2+6);
		}
		//g.drawImage(back, 0, 0, hdlr.$game().$width(), hdlr.$game().$height(), null);
		
		//int u=200;
		//Do you think God stays in heaven because he lives in constant fear of what he has created?
		//A samurai who smells of sunflowers? Sunflowers don’t have a scent. So wouldn’t that mean the samurai you’re searching for…doesn’t even exist?
		//g.drawString("[ARROW KEYS]    TO MOVE.", u, 100);
		//g.drawString("[I]             Open Player Inventory.", u, 120);
		//g.drawString("[ESC]           Exit Player Inventory.", u, 140);
		//g.drawString("[E]             Use Item in D Slot.", u, 160);
		//g.drawString("[R]             Use Item in F Slot.", u, 180);
		//g.drawString("[SPACE]         Swing Your Sword.", u, 200);
		//g.setFont(new Font("ReservoirGrunge", Font.BOLD, 20));
		if(b>120){
			g.setFont(new Font("Lucida Console", Font.BOLD, 8));
			g.setColor(Color.GRAY);
			g.drawString("PRESS [ENTER] TO DIE",(hdlr.$game().$width()/2)-(g.getFontMetrics().stringWidth("PRESS [ENTER] TO DIE")/2), hdlr.$game().$height()-175+(22*3));
		}
		
		
	}

}
