package havocpixel.states;

import havocpixel.main.Handler;
import havocpixel.sfx.AmbPlayer;
import havocpixel.ui.ClickListener;
import havocpixel.ui.UIManager;
import havocpixel.ui.UITextButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DemoPortalScreen extends State{

	private UIManager uim;
	public DemoPortalScreen(Handler hdlr) {
		super(hdlr);
		uim=new UIManager(hdlr);
		hdlr.$mm().setUIManager(uim);
		uim.addObject(new UITextButton(hdlr,hdlr.$game().$width()/2,300,0,0,15,"TRY IT OUT",new ClickListener(){
			public void onClick() {
				hdlr.$mm().setUIManager(null);
				State.setState(hdlr.$sm().$controls());
			}
		}));
		uim.addObject(new UITextButton(hdlr,hdlr.$game().$width()/2,325,0,0,15,"ACTUALLY I'M GOOD",new ClickListener(){
			public void onClick() {
				System.exit(0);
			}
		}));
	}

	@Override
	public void tick() {
		if(!playingAudio){
			playingAudio=true;
			AmbPlayer.playMp3("/amb/Wintergatan_-_Sommarf_gel_converted.mp3");
			//AmbPlayer.playMp3("/amb/hardcodedEmotions.mp3");
			//AmbPlayer.playMp3("/amb/mm10endless.mp3");
			//AmbPlayer.playMp3("/amb/afrovkuma.mp3");
			//AmbPlayer.playMp3("/amb/sinisterOSTsacrifice.mp3");
			//AmbPlayer.playMp3("/amb/jackvshannabal.mp3");
		}
		uim.tick();
		//TEMPORARY FLAG
		//hdlr.$mm().setUIManager(null);
		//State.setState(hdlr.$sm().$defWorld());
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, hdlr.$game().$width(), hdlr.$game().$height());
		g.setColor(Color.WHITE);
		g.setFont(new Font("ReservoirGrunge", Font.BOLD, 25));
		//g.drawString("a FaiRY tALe;", hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth("A FAIRY TALE")/2), 150);
		g.drawString("[SNAPSHOT "+hdlr.$game().version+"]", hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth("[SNAPSHOT "+hdlr.$game().version+"]")/2), 150);
		g.setFont(new Font("ReservoirGrunge", Font.PLAIN, 12));
		g.drawString(hdlr.$game().define, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth(hdlr.$game().define)/2), 180);
		uim.render(g);
		
	}

}
