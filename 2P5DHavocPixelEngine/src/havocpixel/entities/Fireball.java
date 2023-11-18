package havocpixel.entities;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Fireball extends ExplodingThrownKnife{
	protected BufferedImage img;
	public Fireball(Handler handler, float x,float y,String owner,int dir,boolean blowUp) {
		super(handler, x,y,0,0,owner,dir);
		damage=75;
		speed=7;
		if(direction==0){
			xSpeed=0;//1-(int)(Math.random()*3);
			ySpeed=speed;
		}else if(direction==1){
			xSpeed=speed;
			ySpeed=0;
		}else if(direction==2){
			xSpeed=0;
			ySpeed=-speed;
		}else{
			xSpeed=-speed;
			ySpeed=0;
		}
		this.label=new String("PROJECTILE:FIREBALL");
		explodes=blowUp;
		img=explodes?Assets.fireball:Assets.fireball0;
		// TODO Auto-generated constructor stub
	}
	public void render(Graphics g) {
		if(direction==0)
			g.drawImage(Utils.rotate(img,270),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==1)
			g.drawImage(Utils.rotate(img,180),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==2)
			g.drawImage(Utils.rotate(img,90),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==3)
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
	}
}
