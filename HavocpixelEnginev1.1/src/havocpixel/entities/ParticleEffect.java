package havocpixel.entities;

import java.awt.Graphics;

import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class ParticleEffect extends Entity{

	protected double time;
	public ParticleEffect(Game game, double x, double y) {
		super(game, x, y);
		name="Particle Effect";
		particle=true;
		active=true;
		death=new Animation(CoreAssets.gibCorpse[game.$randomInt(0, 4)],0.75,true);
		time=0;
	}

	public void update(double dt) {
		time+=dt;
		death.update(dt);
		if(time>death.$speed()){
			die();
		}
	}

	public void render(Graphics g) {
		g.drawImage(death.$currentFrame(), 
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()),
				this.width,
				this.height,
				null);
	}

	public void die() {
		active=false;
	}

}
