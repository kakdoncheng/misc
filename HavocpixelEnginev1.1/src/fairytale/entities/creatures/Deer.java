package fairytale.entities.creatures;

import java.awt.Rectangle;

import fairytale.entities.objects.DeerCorpse;
import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class Deer extends Bunny{
	public Deer(Game game, int x, int y) {
		super(game, x, y);
		name="Deer";
		bounds=new Rectangle(8,10,15,21);
		corpse=new DeerCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.dbiL,1);
		up=new Animation(CoreAssets.dbUp,0.5);
		down=new Animation(CoreAssets.dbDown,0.5);
		right=new Animation(CoreAssets.dbRight,0.75);
		left=new Animation(CoreAssets.dbLeft,0.75);
		iUp=new Animation(CoreAssets.dbiU,1);
		iDown=new Animation(CoreAssets.dbiD,1);
		iRight=new Animation(CoreAssets.dbiR,1);
		iLeft=new Animation(CoreAssets.dbiL,1);
	}
	public void die(){
		dead=true;
		corpse.setXY(x, y);
		game.$currentWorld().$entityManager().addEntity(corpse);
	}

}
