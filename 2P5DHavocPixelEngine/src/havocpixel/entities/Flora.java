package havocpixel.entities;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Flora extends FloatingString{

	private BufferedImage img;
	public Flora(Handler handler, float x, float y) {
		super(handler, x, y, "Null", Color.RED, 0);
		label="Flora";
		this.x=x;
		this.y=y;
		bounds=new Rectangle(0,0,32,32);
		int u=(int)(Math.random()*5);
		if(u==0){
			img=Assets.g0;
		}else if(u==1){
			img=Assets.g1;
		}else if(u==2){
			img=Assets.g2;
		}else if(u==3){
			img=Assets.g3;
		}else if(u==4){
			img=Assets.g4;
		}
		particle=false;
		flora=true;
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		
	}
	public void render(Graphics g){
		g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(y-28-hdlr.$camera().$yOffset()),null);
	}

}
