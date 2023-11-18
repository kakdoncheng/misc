package havocpixel.entities;

import java.awt.Graphics;

import havocpixel.entities.creatures.ThrownKnifeGib;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class ShotArrow extends ThrownKnife{

	public ShotArrow(Handler handler, float x, float y, int xTarget,
					int yTarget, String owner, int dir) {
				super(handler, x, y, xTarget, yTarget, owner, dir);
				duration=60;
				label="PROJECTILE:SHOTARROW";
				// TODO Auto-generated constructor stub
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
			}
			public void render(Graphics g) {
				if(direction==0)
					g.drawImage(Assets.ad,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
				else if(direction==1)
					g.drawImage(Assets.ar,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
				else if(direction==2)
					g.drawImage(Assets.au,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
				else if(direction==3)
					g.drawImage(Assets.al,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
			}

			public void die(){
				//hdlr.$world().em.addEntity(new ThrownKnifeGib(hdlr,this.x,this.y));
			}
}
