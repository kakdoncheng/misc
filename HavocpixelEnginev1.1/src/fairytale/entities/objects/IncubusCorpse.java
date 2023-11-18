package fairytale.entities.objects;

import fairytale.entities.creatures.ReanimatedIncubus;
import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class IncubusCorpse extends Corpse{

	public IncubusCorpse(Game game, double x, double y) {
		super(game, x, y);
		name="Incubus Corpse";
		death=new Animation(CoreAssets.incubusDeath,0.75,true);
		
		reanimate=true;
	}
	
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new FleshGib(game,x,y));
		game.$currentWorld().$entityManager().addEntity(new FleshGib(game,x,y));
	}
	
	public void reanimate(){
		active=false;
		game.$currentWorld().$entityManager().addEntity(new ReanimatedIncubus(game,(int)x,(int)y));
	}

}
