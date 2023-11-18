package fairytale.entities.objects;

import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class CultistCorpse  extends Corpse{

	public CultistCorpse (Game game, double x, double y) {
		super(game, x, y);
		name="Human Corpse";
		death=new Animation(CoreAssets.cDeath,0.75,true);
	}
	
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new FleshGib(game,x,y));
		game.$currentWorld().$entityManager().addEntity(new FleshGib(game,x,y));
	}

}
