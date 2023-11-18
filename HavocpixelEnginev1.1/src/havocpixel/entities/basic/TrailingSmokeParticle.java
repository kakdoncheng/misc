package havocpixel.entities.basic;

import havocpixel.Game;
import havocpixel.entities.FloatingString;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Graphics;

public class TrailingSmokeParticle extends FloatingString{
	private Animation smoke;
	private double error,xError,yError;
	public TrailingSmokeParticle(Game game, double x, double y) {
		super(game, x, y, "", null);
		smoke=new Animation(CoreAssets.smoke, 0.25, true);
		name="Smoke";
		error=0.5;
		maxDuration=0.25;
		duration=maxDuration;
		dy=8;
		xError=game.$randomDouble(-error, error);
		yError=game.$randomDouble(-error, error);
	}
	@Override
	public void update(double dt) {
		super.update(dt);
		x+=xError;
		y+=yError;
		smoke.update(dt);
	}

	@Override
	public void render(Graphics g) {
		Utils.drawTranslucentImage(smoke.$currentFrame(),
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()),
				(float)0.5,
				this.width,
				this.height,
				g);
	}


}
