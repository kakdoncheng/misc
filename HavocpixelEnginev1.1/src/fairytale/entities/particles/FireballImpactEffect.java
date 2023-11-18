package fairytale.entities.particles;

import havocpixel.Game;
import havocpixel.entities.ParticleEffect;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class FireballImpactEffect extends ParticleEffect{
	public FireballImpactEffect(Game game, double x, double y) {
		super(game, x, y);
		death=new Animation(CoreAssets.fireballImpact[game.$randomInt(0, 2)],0.15,true);
	}
}
