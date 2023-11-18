package fairytale.entities.objects;

import fairytale.entities.creatures.ReanimatedHellsing;
import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

import java.awt.Rectangle;

public class HellsingCorpse extends Corpse{
	
	public HellsingCorpse(Game game, double x, double y) {
		super(game, x, y);
		name="Hellsing Corpse";
		bounds=new Rectangle(7,21,15,10);
		death=new Animation(CoreAssets.hiDeath,0.75,true);
		
		reanimate=true;
	}
	
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new HellsingGib(game,x,y));
		game.$currentWorld().$entityManager().addEntity(new HellsingGib(game,x,y));
	}
	
	public void reanimate(){
		active=false;
		game.$currentWorld().$entityManager().addEntity(new ReanimatedHellsing(game,(int)x,(int)y));
	}

}
