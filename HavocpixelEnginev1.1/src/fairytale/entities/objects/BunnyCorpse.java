package fairytale.entities.objects;

import havocpixel.Game;
import havocpixel.entities.basic.HurtEffect;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class BunnyCorpse extends Corpse{

	public BunnyCorpse (Game game, double x, double y) {
		super(game, x, y);
		name="Bunny Corpse";
		death=new Animation(CoreAssets.bDeath,0.75,true);
		//secDeath=secDeath;
	}
	
	public void die(){
		active=false;
		game.$currentWorld().$entityManager().addEntity(new HurtEffect(game,x,y));
		game.$currentWorld().$entityManager().addEntity(new BoneGib(game,x,y));
		game.$currentWorld().$entityManager().addEntity(new BoneGib(game,x,y));
	}
}
