package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Rock extends Tombstone{

	protected BufferedImage img,h;
	public Rock(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		bounds=new Rectangle(6,12,20,20);
		int i=(int)(Math.random()*3);
		if(i==0){
			img=Assets.r0;
		}else if(i==1){
			img=Assets.r1;
		}else if(i==2){
			bounds=new Rectangle(6,22,20,10);
			img=Assets.r2;
		}
		// TODO Auto-generated constructor stub
	}
	public void render(Graphics g){
		g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
	}

}
