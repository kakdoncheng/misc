package fairytale.entities.particles;

import java.awt.Graphics;

import havocpixel.Game;
import havocpixel.entities.ParticleEffect;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

public class WispEffect extends ParticleEffect{

	public WispEffect(Game game, double x, double y) {
		super(game, x, y);
		death=new Animation(CoreAssets.wisp,0.5,true);
	}
	public void render(Graphics g) {
		Utils.drawTranslucentImage(death.$currentFrame(), 
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()),(float)(0.25),
				this.width,
				this.height,
				g);
	}

}
