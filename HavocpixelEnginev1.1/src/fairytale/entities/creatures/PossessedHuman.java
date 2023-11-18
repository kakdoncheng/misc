package fairytale.entities.creatures;

import fairytale.entities.objects.PossessedHumanCorpse;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class PossessedHuman extends ArmoredPossessedHuman{

	public PossessedHuman(Game game, int x, int y) {
		super(game, x, y);
		armor=Armor.DAMAGED_PLATE;
		corpse=new PossessedHumanCorpse(game,0,0);
		spawn=new Animation(CoreAssets.phEmerge,0.75,true);
		up=new Animation(CoreAssets.phUp,1.0);
		down=new Animation(CoreAssets.phDown,1.0);
		right=new Animation(CoreAssets.phRight,1.5);
		left=new Animation(CoreAssets.phLeft,1.5);
		aUp=new Animation(CoreAssets.AphUp,0.4);
		aDown=new Animation(CoreAssets.AphDown,0.4);
		aRight=new Animation(CoreAssets.AphRight,0.4);
		aLeft=new Animation(CoreAssets.AphLeft,0.4);
		iUp=new Animation(CoreAssets.phiU,1);
		iDown=new Animation(CoreAssets.phiD,1);
		iRight=new Animation(CoreAssets.phiR,1);
		iLeft=new Animation(CoreAssets.phiL,1);
	}

}
