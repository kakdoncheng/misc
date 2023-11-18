package havocpixel.states;

import havocpixel.entities.Character;
import havocpixel.entities.EntityManager;
import havocpixel.entities.Stage;
import havocpixel.gfx.ImageLoader;
import havocpixel.gfx.Textures;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class SingleArena extends State{
	
	private boolean AI=true;
	public SingleArena(Handler hdlr) {
		super(hdlr);
		// TODO Auto-generated constructor stub
		s=new Stage(hdlr);
		em=new EntityManager(hdlr);
		em.addEntity(s);
		em.addEntity(new Character(hdlr,0,200,100));
		em.addEntity(new Character(hdlr,1,hdlr.$game().$width()-175-200,100));
	}
	public void reset(){
		em.removeChar();
		em.addEntity(new Character(hdlr,0,200,100));
		em.addEntity(new Character(hdlr,1,hdlr.$game().$width()-175-200,100));
		count=3;
		ti=30;
	}
	
	int timer=0;
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(getTime()==0&&AI){
			timer++;
			if(timer>180){
				timer=0;
				reset();
			}
		}
		if(!(count>0)){
			if(getTime()==0){
				State.$State().em.$char(1).damage(10000);
				State.$State().em.$char(2).damage(10000);
			}
			em.tick();
		}else{
			last=System.currentTimeMillis()+900;
			em.updateAll();
		}
		if(hdlr.$km().keys[KeyEvent.VK_R]){
			//em.$char(0).shittyMove(0);
			reset();
		}
		if(AI){
			if(!em.$char(1).isDead()&&!em.$char(2).isDead()){
				em.$char(1).move(0);
				em.$char(2).move(1);
			}
			if(Math.random()<0.2){
				if(Math.random()<0.2)
					em.$char(1).block();
				else
					em.$char(1).attack();
			}
			if(Math.random()<0.2){
				if(Math.random()<0.2)
					em.$char(2).block();
				else
					em.$char(2).attack();
			}
		}else{
			if(hdlr.$km().keys[KeyEvent.VK_UP]){
				//em.$char(0).shittyMove(0);
				em.$char(1).jump();
			}
			if(hdlr.$km().keys[KeyEvent.VK_RIGHT]){
				//em.$char(0).shittyMove(1);
				em.$char(1).move(0);
			}
			if(hdlr.$km().keys[KeyEvent.VK_DOWN]){
				em.$char(1).block();
			}
			if(hdlr.$km().keys[KeyEvent.VK_LEFT]){
				//em.$char(0).shittyMove(3);
				em.$char(1).move(1);
			}
			if(hdlr.$km().keys[KeyEvent.VK_SPACE]){
				//em.$char(0).shittyMove(3);
				em.$char(1).attack();
			}
			
			if(hdlr.$km().keys[KeyEvent.VK_W]){
				//em.$char(0).shittyMove(0);
				em.$char(2).jump();
			}
			if(hdlr.$km().keys[KeyEvent.VK_D]){
				//em.$char(0).shittyMove(1);
				em.$char(2).move(0);
			}
			if(hdlr.$km().keys[KeyEvent.VK_S]){
				em.$char(2).block();
			}
			if(hdlr.$km().keys[KeyEvent.VK_A]){
				//em.$char(0).shittyMove(3);
				em.$char(2).move(1);
			}
			if(hdlr.$km().keys[KeyEvent.VK_F]){
				//em.$char(0).shittyMove(3);
				em.$char(2).attack();
			}
		}
	}
	int count=3,ti=30;
	BufferedImage[] co={
			new Textures(ImageLoader.loadImage("/txr/fight.png")).crop(),
			new Textures(ImageLoader.loadImage("/txr/1.png")).crop(),
			new Textures(ImageLoader.loadImage("/txr/2.png")).crop(),
			new Textures(ImageLoader.loadImage("/txr/3.png")).crop()
	};
	private long last=System.currentTimeMillis();
	private int getTime(){
		int u=((AI?20000:90000)-(int)(System.currentTimeMillis()-last))/1000;
		return u>0?u:0;
	}
	BufferedImage back=new Textures(ImageLoader.loadImage("/txr/Tgkpf.png")).crop();
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GRAY);
		//g.drawString("FPS "+Math.round(ae0*10.0)/10.0+"", 2, 12);
		for(int i=0;i<75;i++)
			g.drawString("129dB: Singleplayer Arena.", 2, 12*i);
		g.setColor(Color.WHITE);
		g.drawImage(back,0,0,hdlr.$game().$width(),hdlr.$game().$height(),null);
		em.render(g);
		g.setFont(new Font("San Serif",Font.BOLD,64));
		if(getTime()>9){
			Utils.drawStringWithOutline(g,(getTime()>9)?""+getTime():"0"+getTime(), hdlr.$game().$width()/2-38, 64,Color.YELLOW);
		}else{
			Utils.drawStringWithOutline(g,(getTime()>9)?""+getTime():"0"+getTime(), hdlr.$game().$width()/2-38, 64, (hdlr.$game().$tick()<30)?Color.YELLOW:Color.RED);
		}
		//g.setFont(new Font("San Serif",Font.BOLD,10));
		//Utils.drawStringWithOutline(g,"TIME LEFT", hdlr.$game().$width()/2-28, 80, Color.YELLOW);
		if(count>-1){
			Utils.drawTranslucentImage(co[count],0,0,(count>0)?ti/30.0f:1.0f,hdlr.$game().$width(),hdlr.$game().$height(),g);
			if(ti>0){
				ti--;
			}else{
				count--;
				ti=30;
			}
		}else{
			/*
			if(!State.$State().em.$char(1).isDead()&&State.$State().em.$char(2).isDead()){
				g.setFont(new Font("ReservoirGrunge",Font.BOLD,64));
				Utils.drawStringWithOutline(g,"LEFT PATRICK WINS!", hdlr.$game().$width()/2-150, hdlr.$game().$height()/3, Color.WHITE);
			}else if(State.$State().em.$char(1).isDead()&&!State.$State().em.$char(2).isDead()){
				g.setFont(new Font("ReservoirGrunge",Font.BOLD,64));
				Utils.drawStringWithOutline(g,"RIGHT PATRICK WINS!", hdlr.$game().$width()/2-150, hdlr.$game().$height()/3, Color.WHITE);
			}else if(State.$State().em.$char(1).isDead()&&State.$State().em.$char(2).isDead()){
				g.setFont(new Font("ReservoirGrunge",Font.BOLD,64));
				Utils.drawStringWithOutline(g,"DRAW", hdlr.$game().$width()/2-60, hdlr.$game().$height()/3, Color.WHITE);
			}*/
		}
	}
	

}
