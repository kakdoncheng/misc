package fairytale.entities.particles;

import havocpixel.Game;
import havocpixel.entities.ParticleEffect;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class BigSmokeEffect extends ParticleEffect{
	public BigSmokeEffect(Game game, double x, double y) {
		super(game, x, y);
		this.x-=8;
		this.y-=8;
		this.height+=16;
		this.width+=16;
		death=new Animation(CoreAssets.bigSmokeEffect,0.2,true);
	}
}