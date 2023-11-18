package fairytale.entities.creatures;

import fairytale.entities.objects.NavigatorCultistCorpse;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Weapon;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class NavigatorCultist extends Cultist{

	public NavigatorCultist(Game game, int x, int y) {
		super(game, x, y);
		this.canAttackWithProjectile=false;
		armor=Armor.STEEL_PLATE;
		weapon=Weapon.MORNINGSTAR;
		
		corpse=new NavigatorCultistCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.ncEmerge,0.75,true);
		up=new Animation(CoreAssets.ncUp,1.0);
		down=new Animation(CoreAssets.ncDown,1.0);
		right=new Animation(CoreAssets.ncRight,1.5);
		left=new Animation(CoreAssets.ncLeft,1.5);
		aUp=new Animation(CoreAssets.nAcUp,0.4);
		aDown=new Animation(CoreAssets.nAcDown,0.4);
		aRight=new Animation(CoreAssets.nAcRight,0.4);
		aLeft=new Animation(CoreAssets.nAcLeft,0.4);
		iUp=new Animation(CoreAssets.nciU,1);
		iDown=new Animation(CoreAssets.nciD,1);
		iRight=new Animation(CoreAssets.nciR,1);
		iLeft=new Animation(CoreAssets.nciL,1);
	}
	
	

}
