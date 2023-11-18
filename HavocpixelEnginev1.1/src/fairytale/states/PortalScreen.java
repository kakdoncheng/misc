package fairytale.states;

import havocpixel.Game;
import havocpixel.State;
import havocpixel.amb.AmbPlayer;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Graphics;

public class PortalScreen extends State{

	private float alpha=0.0F,beta=-0.1F;
	private boolean fade=false,reverseOpen=false;//Math.random()>0.5;
	
	public PortalScreen(Game game) {
		super(game);
		//reverseOpen=(Math.random()>0.5);
	}
	
	public void update(double dt) {
		if(!playingAmb){
			playingAmb=true;
			//AmbPlayer.playMp3("/amb/Wintergatan_-_Sommarf_gel_converted.mp3");
			if(reverseOpen){
				//AmbPlayer.playMp3Loop("/amb/dejavuwerc86edit.mp3");
				//AmbPlayer.playMp3("/amb/map_of_the_problematique_conv_end_chorus.mp3");
			}else{
				//AmbPlayer.playMp3("/amb/bloodandwine.mp3");
				//AmbPlayer.playMp3("/amb/map_of_the_problematique_conv.mp3");
			}//AmbPlayer.playMp3("/amb/werc86purgatory.mp3");
		}
		if(beta<1.5){
			//if(game.$km().isAnyKeyPressed())
			//	beta=1;
			beta+=dt/3;
			return;
		}
		if(game.$km().isAnyKeyPressed()){
			game.$sm().setCurrentState(3);
			//AmbPlayer.stopMp3();
			AmbPlayer.playWav("/amb/click.wav");
			//AmbPlayer.playWav("/amb/boom.wav");
		}
		if(fade){
			alpha-=dt/4;
			if(alpha<0){
				fade=false;
			}
		}else{
			alpha+=dt/4;
			if(alpha>1){
				fade=true;
			}
		}
	}

	public void render(Graphics g) {
		if(reverseOpen){
			g.setColor(Color.WHITE);
			g.fillRect(-1, -1, game.$width()+1, game.$height()+1);
		}
		Utils.drawTranslucentImage(CoreAssets.title, 0, 0, beta, game.$width(), game.$height(), g);
		if(beta>0.99)
			Utils.drawTranslucentImage(CoreAssets.pak, 
					(int)(game.$width()/2-55),
					(int)(0.6957*game.$height()), alpha, 110, 10, g);
	}

}
