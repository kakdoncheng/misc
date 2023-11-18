package fairytale.entities.particles;

import havocpixel.Game;
import havocpixel.entities.ParticleEffect;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class BulletImpactEffect extends ParticleEffect{
	public BulletImpactEffect(Game game, double x, double y) {
		super(game, x, y);
		death=new Animation(CoreAssets.bulletImpact[game.$randomInt(0, 2)],0.15,true);
	}
}
