package fairytale.entities.objects;

import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class GibbedCorpse extends Corpse{

	public GibbedCorpse(Game game, double x, double y) {
		super(game, x, y);
		health=-1;
		secDeath=new Animation(CoreAssets.gibCorpse[game.$randomInt(0, 4)],0.75,true);
		death=secDeath;
	}
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new BoneGib(game,x,y));
		game.$currentWorld().$entityManager().addEntity(new BoneGib(game,x,y));
	}

}
