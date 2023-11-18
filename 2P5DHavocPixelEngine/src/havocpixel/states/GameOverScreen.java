package havocpixel.states;

import havocpixel.Timer;
import havocpixel.entities.Entity;
import havocpixel.entities.Explosion;
import havocpixel.entities.creatures.LeftHandPole;
import havocpixel.entities.creatures.RightHandPole;
import havocpixel.entities.items.Inventory;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
//import havocpixel.sfx.AmbPlayer;
import havocpixel.sfx.AmbPlayer;
import havocpixel.ui.ClickListener;
import havocpixel.ui.UIManager;
import havocpixel.ui.UITextButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOverScreen extends State{

	private Animation grim,genghis,rain;
	private int fixTick=0;
	private int rnd=(int)(Math.random()*3);
	private boolean done=false,audioStopped=false;
	private String def="qwertyuiopasdfghjklzxcvbnm,./?><1234567890-=!@#$%^&*()_+\'\";:QWERTYUIOPASDFGHJKLZXCVBNM`~";
	private String rndString(){
		String k="";
		int l=(int)(Math.random()*200)+1;
		for(int u=0;u<l;u++)
			k+=def.charAt((int)(Math.random()*def.length()));
		return k;
	}
	
	private UIManager uim;
	private boolean sw=false;
	public GameOverScreen(Handler hdlr) {
		super(hdlr);
		uim=new UIManager(hdlr);
		uim.addObject(new UITextButton(hdlr,hdlr.$game().$width()/2,(hdlr.$game().$height()*3)/4+20,0,0,10,"PRESS ENTER TO DIE AGAIN",new ClickListener(){
			public void onClick() {
				sw=false;
				hdlr.$mm().setUIManager(null);
				Entity u;
				if(Math.random()<0.5)
					u=new LeftHandPole(hdlr,hdlr.$player().x,hdlr.$player().y);
				else
					u=new RightHandPole(hdlr,hdlr.$player().x,hdlr.$player().y);
				hdlr.$currentWorld().em.addEntity(u);
				hdlr.$currentWorld().em.respawn();
				hdlr.$player().resetPlayerVar();
				hdlr.setInv(new Inventory(hdlr));
				hdlr.$currentWorld().respawnPlayer();
				hdlr.$camera().centerOnEntity(hdlr.$player());
				hdlr.$currentWorld().em.updateAll();
				hdlr.$currentWorld().em.tick();
				hdlr.$currentWorld().debugBlackScreenReset();
				State.setState(hdlr.$sm().$controls());
			}
		}));
		System.out.print("["+Timer.time()+"] Loading Game Over Screen. Exit code: "+rnd+";\n");
		grim=new Animation(hdlr,76,Assets.playerDown);
		genghis=new Animation(hdlr,80,Assets.playerDown);
		rain=new Animation(hdlr,61,Assets.playerDown);
	}
	public void tick() {
		if(hdlr.$km().ESC||hdlr.$km().ENTER){
			sw=false;
			hdlr.$mm().setUIManager(null);
			Entity u;
			if(Math.random()<0.5)
				u=new LeftHandPole(hdlr,hdlr.$player().x,hdlr.$player().y);
			else
				u=new RightHandPole(hdlr,hdlr.$player().x,hdlr.$player().y);
			hdlr.$currentWorld().em.addEntity(u);
			hdlr.$currentWorld().em.respawn();
			hdlr.$player().resetPlayerVar();
			hdlr.setInv(new Inventory(hdlr));
			hdlr.$currentWorld().respawnPlayer();
			hdlr.$camera().centerOnEntity(hdlr.$player());
			hdlr.$currentWorld().em.updateAll();
			hdlr.$currentWorld().em.tick();
			hdlr.$currentWorld().debugBlackScreenReset();
			State.setState(hdlr.$sm().$controls());
		}
		if(!sw){
			hdlr.$mm().setUIManager(uim);
			sw=true;
		}
		//System.out.print("["+Timer.time()+"] Loading Game Over Screen. "+rnd+"\n");
		//if(!done&&hdlr.$game().death){
		//	if(!audioStopped){
		//		Assets.stopMp3();
		//		audioStopped=true;
		//	}
		//	Utils.export(new File(
					//Commons.rawPath(Commons.fixws(Commons.jarPath))
		//			));
		//	new Thread(new Runnable(){
	    //	    public void run(){
	    	    	//Commons.newProcess("java -jar OpenAL64.jar");
	    //	    }
	    //	}).start();
		//	done=true;
		//}else{
			if(!audioStopped){
				//AmbPlayer.stopMp3();
				//AmbPlayer.playMp3("/amb/otomatonebullshit.mp3");
				audioStopped=true;
			}
		//}
		//
		if(rnd==0){
			if(grim.$index()%8==0){
				if(fixTick%124!=0)
					grim.fixTimer(-1);
				fixTick++;
			}
			grim.tick();
			return;
		}
		else if(rnd==1){
			if(genghis.$index()%5==0){
				if(fixTick%5==0)
					genghis.fixTimer(1);
				fixTick++;
			}
			genghis.tick();
			return;
		}
		else{
			if(rain.$index()%6==0){
				if(fixTick%2==0)
					rain.fixTimer(-1);
				fixTick++;
			}
			rain.tick();
			return;
		}
		
		//State.delayTick++;
	}
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, hdlr.$game().$width(), hdlr.$game().$height());
		//Utils.drawTranslucentImage(Assets.hurt1,0,0,0.2F,hdlr.$game().$width(),hdlr.$game().$height(),g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("ReservoirGrunge", Font.BOLD, 30));
		g.drawString("GAME OVER", hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth("GAME OVER")/2), 100);
		g.setFont(new Font("ReservoirGrunge", Font.BOLD, 12));
		g.drawString("You done fucked up kid.", hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth("You done fucked up kid.")/2), 120);
		if(rnd==0){
			g.drawImage(grim.$currentFrame(), hdlr.$game().$width()/2-32, hdlr.$game().$height()/3+32, 64, 64, null);
		}
		else if(rnd==1){
			g.drawImage(genghis.$currentFrame(), hdlr.$game().$width()/2-32, hdlr.$game().$height()/3+32, 64, 64, null);
		}
		else{
			g.drawImage(rain.$currentFrame(), hdlr.$game().$width()/2-32, hdlr.$game().$height()/3+32, 64, 64, null);
		}
		g.setFont(new Font("ReservoirGrunge", Font.BOLD, 14));
		g.drawString("SCORE: "+hdlr.$currentWorld().em.player.kills, hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth("SCORE: "+hdlr.$currentWorld().em.player.kills)/2), (hdlr.$game().$height()*3)/4);
		g.setFont(new Font("ReservoirGrunge", Font.BOLD, 18));
		uim.render(g);
		if(done){
			g.drawString("MAY THY FLESH BE CONSUMED", hdlr.$game().$width()/2-
				(g.getFontMetrics().stringWidth("MAY THY FLESH BE CONSUMED")/2), hdlr.$game().$height()-90);
			System.out.print("["+Timer.time()+"] [WARNING] FATAL ERROR HAS OCCURRED; DATA STACK CORRUPT;\n");
			System.out.print("["+Timer.time()+"] [WARNING] CORE DUMP TRACE:\n");
			for(int u=(int)(Math.random()*100);u<100;u++)
				System.out.print(rndString()+"\n");
		}
	}

}