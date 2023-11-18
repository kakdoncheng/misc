package havocpixel.entities.basic;

import havocpixel.Game;
import havocpixel.entities.FloatingString;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BigSmokeParticle extends FloatingString{
	private BufferedImage smoke;
	private double error,xError,yError;
	private double alpha;
	public BigSmokeParticle(Game game, double x, double y) {
		super(game, x, y, "", null);
		smoke=CoreAssets.bigSmoke[game.$randomInt(0, CoreAssets.bigSmoke.length)];
		name="Smoke";
		error=0.15;
		maxDuration=3;
		duration=maxDuration;
		dy=64;
		alpha=0;
		xError=game.$randomDouble(-error, error);
		yError=game.$randomDouble(-error, error);
	}
	@Override
	public void update(double dt) {
		super.update(dt);
		x+=xError;
		y+=yError;
		if(duration<0.5){
			alpha=duration;
		}else if(duration>2.5){
			alpha=maxDuration-duration;
		}else{
			alpha=0.5;
		}
	}

	@Override
	public void render(Graphics g) {
		Utils.drawTranslucentImage(smoke,
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()),
				(float)alpha,
				this.width,
				this.height,
				g);
	}
}
