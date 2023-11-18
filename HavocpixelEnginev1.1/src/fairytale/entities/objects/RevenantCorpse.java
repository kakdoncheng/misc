package fairytale.entities.objects;

import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

import java.awt.Graphics;

public class RevenantCorpse extends Corpse{

	private double time;
	public RevenantCorpse(Game game, double x, double y) {
		super(game, x, y);
		secDeath=new Animation(CoreAssets.reDeath,0.5,true);
		death=secDeath;
		health=-1;
		ghost=true;
		time=0;
	}
	
	public void update(double dt){
		time+=dt;
		if(time>=death.$speed()){
			active=false;
		}
		super.update(dt);
	}
	public void render(Graphics g){
		img=death.$currentFrame();
		g.drawImage(img,
					(int)(x-game.$camera().$xOffset())-16,
					(int)(y-game.$camera().$yOffset())-16,
					64,
					64,null);
		return;
	}

}
