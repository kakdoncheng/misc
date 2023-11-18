package fairytale.states;

import havocpixel.Game;
import havocpixel.State;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

public class BeginDemoWorldState extends State{

	public BeginDemoWorldState(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	private String fonts[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private double w=0,b=0;
	private int q=0;
	double k=Math.random();
	
	@Override
	public void update(double dt) {
		if(w>0){
			w-=dt;
		}else{
			w=(int)(Math.random()*45)-43.5;
			q=(int)(Math.random()*fonts.length);
		}
		b+=dt;
		if(b<2)
			return;
		if(game.$km().ENTER){
			b=0;
			k=Math.random();
			game.$sm().setCurrentState(4);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, game.$width(), game.$height());
		g.setColor(Color.WHITE);
		g.setFont(new Font(fonts[q], Font.PLAIN, 8));
		//g.setFont(new Font("ReservoirGrunge", Font.BOLD, 30));
		//g.drawString("CONTROLS", game.$width()/2-(g.getFontMetrics().stringWidth("CONTROLS")/2), 60);
		
		//k=0.7;
		if(k<0.166D){
			String f="Do you think God stays in heaven";
			g.drawString(f, game.$width()/2-(g.getFontMetrics().stringWidth(f)/2),game.$height()/2-6);
			String t="because he lives in constant fear of what he has created?";
			g.drawString(t, game.$width()/2-(g.getFontMetrics().stringWidth(t)/2),game.$height()/2+4);
			//g.setFont(new Font("Lucida Console", Font.BOLD, 16));
		}else if(k<0.333D){
			String f="Speak of the devil, and he shall appear.";
			g.drawString(f, game.$width()/2-(g.getFontMetrics().stringWidth(f)/2),game.$height()/2-6);
		}else if(k<0.5D){
			String f="Give someone a mask, and they will show you their true face.";
			g.drawString(f, game.$width()/2-(g.getFontMetrics().stringWidth(f)/2),game.$height()/2-6);
		}else if(k<0.666D){
			String f="A samurai who smells of sunflowers? Sunflowers don’t have a scent.";
			g.drawString(f, game.$width()/2-(g.getFontMetrics().stringWidth(f)/2),game.$height()/2-6);
			String t="So wouldn’t that mean the samurai you’re searching for… doesn’t even exist?";
			g.drawString(t, game.$width()/2-(g.getFontMetrics().stringWidth(t)/2),game.$height()/2+4);
		}else if(k<0.833D){
			String f="Rome was not built in a day. Yet it was burnt down in one.";
			g.drawString(f, game.$width()/2-(g.getFontMetrics().stringWidth(f)/2),game.$height()/2-6);
		}else{
			String f="Everyone around you is fighting a battle";
			g.drawString(f, game.$width()/2-(g.getFontMetrics().stringWidth(f)/2),game.$height()/2-6);
			String t="that you know nothing about.";
			g.drawString(t, game.$width()/2-(g.getFontMetrics().stringWidth(t)/2),game.$height()/2+4);
		}
		if(b>2){
			g.setFont(new Font("Lucida Console", Font.BOLD, 6));
			g.setColor(Color.GRAY);
			g.drawString("PRESS [ENTER] TO DIE",(game.$width()/2)-(g.getFontMetrics().stringWidth("PRESS [ENTER] TO DIE")/2), game.$height()-175+(22*3));
		}
		
		
	}

}
