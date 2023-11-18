package fairytale.entities.particles;

import havocpixel.Game;
import havocpixel.entities.ParticleEffect;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class WarpShadowEffect extends ParticleEffect{

	public WarpShadowEffect(Game game, double x, double y, boolean reverse) {
		super(game, x, y);
		death=new Animation(reverse?CoreAssets.warpR:CoreAssets.warp,0.75,true);
	}

}
