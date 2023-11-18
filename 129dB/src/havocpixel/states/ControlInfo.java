package havocpixel.states;

import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

public class ControlInfo extends State{

	public ControlInfo(Handler hdlr) {
		super(hdlr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		if(hdlr.$km().ESC||hdlr.$km().ENTER)
			State.setState(hdlr.$sm().$menu());
	}

	private String fonts[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private int w=0,q=0,b=0;
	double k=Math.random();
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
		
		
		if(k<0.5D){
			String f="Do you think God stays in heaven";
			g.drawString(f, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(f)/2),hdlr.$game().$height()/2-6);
			String t="because he lives in constant fear of what he has created?";
			g.drawString(t, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(t)/2),hdlr.$game().$height()/2+6);
			//g.setFont(new Font("Lucida Console", Font.BOLD, 16));
		}else{
			String f="Speak of the devil, and he shall appear.";
			g.drawString(f, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(f)/2),hdlr.$game().$height()/2-6);
		}
		
		
		//int u=200;
		//Do you think God stays in heaven because he lives in constant fear of what he has created?
		
		//g.drawString("[ARROW KEYS]    TO MOVE.", u, 100);
		//g.drawString("[I]             Open Player Inventory.", u, 120);
		//g.drawString("[ESC]           Exit Player Inventory.", u, 140);
		//g.drawString("[E]             Use Item in D Slot.", u, 160);
		//g.drawString("[R]             Use Item in F Slot.", u, 180);
		//g.drawString("[SPACE]         Swing Your Sword.", u, 200);
		//g.setFont(new Font("ReservoirGrunge", Font.BOLD, 20));
		if(b>180){
			g.setFont(new Font("Lucida Console", Font.BOLD, 12));
			g.drawString("PRESS [ENTER] TO DIE",(hdlr.$game().$width()/2)-(g.getFontMetrics().stringWidth("PRESS [ENTER] TO DIE")/2), hdlr.$game().$height()-150+(22*3));
		}
		
		
	}

}
