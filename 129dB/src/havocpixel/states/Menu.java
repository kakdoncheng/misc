package havocpixel.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import havocpixel.main.Handler;

public class Menu extends State{

	public Menu(Handler hdlr) {
		super(hdlr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		if(hdlr.$km().keys[KeyEvent.VK_E]){
			hdlr.$sm().$arena().start();
			State.setState(hdlr.$sm().$arena());
		}else if(hdlr.$km().keys[KeyEvent.VK_S]){
			State.setState(hdlr.$sm().$client());
		}else if(hdlr.$km().keys[KeyEvent.VK_Q]){
			State.setState(hdlr.$sm().$singleArena());
		}else if(hdlr.$km().keys[KeyEvent.VK_F]){
			hdlr.$game().toggleFullScreen();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		//g.drawString("FPS "+Math.round(ae0*10.0)/10.0+"", 2, 12);
		for(int i=0;i<75;i++)
			g.drawString("129dB: Test Menu.", 2, 12*i);
		g.setColor(Color.WHITE);
		g.drawString("[INFO]: 129dB: 129 Decibels.", 2, 12*2);
		g.drawString("[INFO]: 129dB: A client-server based game where a multitude of people can guide two shining fighters to victory using speech recognition.", 2, 12*3);
		g.drawString("[INFO]: 129dB: Implements the third party JARVIS and Sphinx Java APIs. Written in Java.", 2, 12*4);
		g.drawString("[INFO]: 129dB: A game design by Decapitated Bunny.", 2, 12*6);
		g.drawString("[INFO]: 129dB: Credits:", 2, 12*8);
		g.drawString("[INFO]: 129dB: Linh-Han Van - Project Lead, Game Engine Designer, Artwork & Animations.", 2, 12*9);
		g.drawString("[INFO]: 129dB: Ryan Le - Programmer, JARVIS sound API.", 2, 12*10);
		g.drawString("[INFO]: 129dB: Jeffrey Yueng - Programmer, Sphinx Speech Recognition API.", 2, 12*11);
		g.drawString("[INFO]: 129dB: Jenny Luc - Artwork Touch Up & QC", 2, 12*12);
		g.drawString("[INFO]: 129dB: Press [E] to start server arena.", 2, 12*14);
		g.drawString("[INFO]: 129dB: Press [S] to start client.", 2, 12*15);
		g.drawString("[INFO]: 129dB: Press [Q] to start AI demo.", 2, 12*16);
		g.drawString("[INFO]: 129dB: Press [F] to relaunch the game in fullscreen mode.", 2, 12*17);
		g.drawString("[INFO]: 129dB: Press [ESC] to exit at any time.", 2, 12*18);
		//g.drawString("[2P5DHavocPixel Engine][32bit]["+version+"]", 2, height-5);
	}

	

}
