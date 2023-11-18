package havocpixel.states;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.sfx.AmbPlayer;
import havocpixel.util.Utils;

import java.awt.Graphics;


public class PortalScreen extends State{

	public PortalScreen(Handler hdlr) {
		super(hdlr);
		// TODO Auto-generated constructor stub
	}

	private float alpha=0.0F,beta=0.0F;
	boolean fade=false;
	@Override
	public void tick() {
		if(!playingAudio){
			playingAudio=true;
			AmbPlayer.playMp3("/amb/Wintergatan_-_Sommarf_gel_converted.mp3");
			//AmbPlayer.playMp3("/amb/hardcodedEmotions.mp3");
			//AmbPlayer.playMp3("/amb/mm10endless.mp3");
			//AmbPlayer.playMp3("/amb/twostepsfromhellnero.mp3");
			//AmbPlayer.playMp3("/amb/sinisterOSTsacrifice.mp3");
			//AmbPlayer.playMp3("/amb/evilmendowerc85.mp3");
		}
		if(beta<0.995){
			if(hdlr.$km().isAnyKeyPressed())
				beta=1;
			beta+=0.005F;
			return;
		}
		if(hdlr.$km().isAnyKeyPressed())
			State.setState(hdlr.$sm().$controls());
		if(fade){
			alpha-=0.01F;
			if(alpha<0){
				fade=false;
			}
		}else{
			alpha+=0.01F;
			if(alpha>1){
				fade=true;
			}
		}
		
		//alpha-=0.01F;
		//if(alpha<0){
		//	alpha=1.0F;
		//}
	}

	@Override
	public void render(Graphics g) {
		//g.drawImage(Assets.title, 0, 0, hdlr.$game().$width(), hdlr.$game().$height(), null);
		Utils.drawTranslucentImage(Assets.title, 0, 0, beta, hdlr.$game().$width(), hdlr.$game().$height(), g);
		if(beta>0.99)
			Utils.drawTranslucentImage(Assets.pak, 
					(int)(hdlr.$game().$width()/2-55),
					(int)(0.6957*hdlr.$game().$height()), alpha, 110, 10, g);
	}


}
