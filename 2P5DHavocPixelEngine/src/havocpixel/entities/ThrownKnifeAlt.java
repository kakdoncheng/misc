package havocpixel.entities;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;

public class ThrownKnifeAlt extends ThrownKnife{

	public ThrownKnifeAlt(Handler handler, float x, float y, int xTarget,
			int yTarget, String owner, int dir) {
		super(handler, x, y, xTarget, yTarget, owner, dir);
		damage=25;
		duration=60;
		// TODO Auto-generated constructor stub
	}
	public void render(Graphics g) {
		if(direction==0)
			g.drawImage(Assets.kd,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==1)
			g.drawImage(Assets.kr,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==2)
			g.drawImage(Assets.ku,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
		else if(direction==3)
			g.drawImage(Assets.kl,(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),null);
	}

}
