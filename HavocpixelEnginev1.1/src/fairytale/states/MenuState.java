package fairytale.states;

import havocpixel.Game;
import havocpixel.State;
import havocpixel.amb.AmbPlayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class MenuState extends State{
	
	ArrayList<String> options;
	double thres=0.3,timer=0,openTimer=0.5;
	int index=0;
	
	public MenuState(Game game) {
		super(game);
		options=new ArrayList<String>();
		options.add("  Start Demo");
		options.add("  Quit");
	}
	
	public void update(double dt) {
		if(openTimer>0){
			openTimer-=dt;
			return;
		}
		if(!game.$km().isAnyKeyPressed()){
			//game.$sm().setCurrentState(2);
			timer=0;
		}else{
			timer-=dt;
		}
		if(timer<0){
			if(game.$km().up){
				index--;
				if(index<0)
					index=options.size()-1;
				timer=thres;
				AmbPlayer.playWav("/amb/click.wav");
			}else if(game.$km().down){
				index=(index+1)%options.size();
				timer=thres;
				AmbPlayer.playWav("/amb/click.wav");
			}
		}
		if(game.$km().ENTER){
			if(index==0){
				//AmbPlayer.playMp3("/amb/werc86thegooddieyoung.mp3");
				AmbPlayer.stopMp3();
				AmbPlayer.playMp3Loop("/amb/Wintergatan_-_Sommarf_gel_converted.mp3");
				//AmbPlayer.playMp3Loop("/amb/needsleep.mp3");
				game.$sm().setCurrentState(3);
			}else if(index==1){
				System.exit(0);
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Lucida Console",Font.BOLD,8));
		for(int i=0; i<options.size(); i++){
			g.drawString(index!=i?options.get(i):"~"+options.get(i), game.$width()/4, game.$height()*3/4+(10*i));
		}
	}

}

