package fairytale.entities.objects;

import havocpixel.Game;
import havocpixel.entities.basic.HurtEffect;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class DeerCorpse extends Corpse{

	public DeerCorpse(Game game, double x, double y) {
		super(game, x, y);
		name="Deer Corpse";
		death=new Animation(CoreAssets.dbDeath,0.75,true);
		//secDeath=secDeath;
	}
	
	public void die(){
		active=false;
		game.$currentWorld().$entityManager().addEntity(new HurtEffect(game,x,y));
		game.$currentWorld().$entityManager().addEntity(new BoneGib(game,x,y));
		game.$currentWorld().$entityManager().addEntity(new BoneGib(game,x,y));
	}

}
