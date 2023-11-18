package fairytale.entities.objects;

import fairytale.entities.creatures.ReanimatedImp;
import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class ImpCorpse extends Corpse{

	public ImpCorpse(Game game, double x, double y) {
		super(game, x, y);
		maxHealth=750;
		health=maxHealth;
		name="Imp Corpse";
		death=new Animation(CoreAssets.iDeath,0.75,true);
		reanimate=true;
	}
	
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new ImpGib(game,x,y));
		game.$currentWorld().$entityManager().addEntity(new ImpGib(game,x,y));
	}
	
	public void reanimate(){
		active=false;
		game.$currentWorld().$entityManager().addEntity(new ReanimatedImp(game,(int)x,(int)y));
	}

}
