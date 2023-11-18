package havocpixel.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.main.Handler;

public class RisingSmoke extends Entity{

	public RisingSmoke(Handler handler, float x, float y) {
		super(handler, x, y,0,0,1);
		bounds=new Rectangle(0,0,32,32);
		health=1;
		damage=0;
		label="LABEL;";
		//object=true;
		item=true;
		particle=true;
		lim=200+(int)(Math.random()*31);
		//trailSoot();
	}
	private int duration=0,lim=0,k=6;
	@Override
	public void tick() {
		if(duration>((hdlr.$game().$FPS()>59)?360-lim:((hdlr.$game().$FPS()*5)-(lim/2)))){
			active=false;
		}
		if(duration>30)
			k=12;
		if(duration>60)
			k=30;
		duration++;
		if(hdlr.$game().$tick()%6==0)
			hdlr.$currentWorld().em.addEntity(new Smoke(hdlr,this.x,this.y,1F));
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
