package fairytale.entities.creatures;

import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class ReanimatedHellsing extends Hellsing{

	public ReanimatedHellsing(Game game, int x, int y) {
		super(game, x, y);
		spawning=true;
		name="Reanimated Hellsing";
		maxHealth=1000;
		health=maxHealth;
		this.canAttackWithProjectile=false;
		strength=200;
		speed=60;
		spawn=new Animation(CoreAssets.rhiEmerge,0.75,true);
		up=new Animation(CoreAssets.rhiUp,1.0);
		down=new Animation(CoreAssets.rhiDown,1.0);
		right=new Animation(CoreAssets.rhiRight,1.5);
		left=new Animation(CoreAssets.rhiLeft,1.5);
		aUp=new Animation(CoreAssets.rhAiUp,0.4);
		aDown=new Animation(CoreAssets.rhAiDown,0.4);
		aRight=new Animation(CoreAssets.rhAiRight,0.4);
		aLeft=new Animation(CoreAssets.rhAiLeft,0.4);
		iUp=new Animation(CoreAssets.rhiiU,1);
		iDown=new Animation(CoreAssets.rhiiD,1);
		iRight=new Animation(CoreAssets.rhiiR,1);
		iLeft=new Animation(CoreAssets.rhiiL,1);
	}

}
