package havocpixel.entities.basic;

import havocpixel.Game;
import havocpixel.entities.ParticleEffect;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class HurtEffect extends ParticleEffect{

	public HurtEffect(Game game, double x, double y) {
		super(game, x, y);
		death=new Animation(CoreAssets.bloodEffect[game.$randomInt(0, 4)],0.4,true);
	}

	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new Bloodstain(game,x,y));
		if(game.$randomDouble(0, 1)>0.49)
			game.$currentWorld().$entityManager().addEntity(new Bloodstain(game,x,y));
	}
}
