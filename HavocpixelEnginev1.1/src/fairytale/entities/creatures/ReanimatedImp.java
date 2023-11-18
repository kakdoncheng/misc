package fairytale.entities.creatures;

import fairytale.entities.objects.ImpCorpse;
import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class ReanimatedImp extends Imp{

	public ReanimatedImp(Game game, int x, int y) {
		super(game, x, y);
		name="Reanimated Imp";
		
		corpse=new ImpCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.riEmerge,0.75,true);
		up=new Animation(CoreAssets.riUp,1.0);
		down=new Animation(CoreAssets.riDown,1.0);
		right=new Animation(CoreAssets.riRight,1.5);
		left=new Animation(CoreAssets.riLeft,1.5);
		aUp=new Animation(CoreAssets.rAiUp,0.4);
		aDown=new Animation(CoreAssets.rAiDown,0.4);
		aRight=new Animation(CoreAssets.rAiRight,0.4);
		aLeft=new Animation(CoreAssets.rAiLeft,0.4);
		iUp=new Animation(CoreAssets.riiU,1);
		iDown=new Animation(CoreAssets.riiD,1);
		iRight=new Animation(CoreAssets.riiR,1);
		iLeft=new Animation(CoreAssets.riiL,1);
	}

}
