package fairytale.entities.creatures;

import fairytale.entities.objects.HelmetGib;
import fairytale.entities.projectiles.ThrownKnife;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Faction;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class ArmoredSkeleton extends BasicEntity{
	public ArmoredSkeleton(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.POSSESSED;
		name="Armored Skeleton";
		armor=Armor.WROUGHT_IRON_PLATE;
		maxHealth=500;
		health=maxHealth;
		speed=60;
		
		spawn=new Animation(CoreAssets.asEmerge,1.5,true);
		up=new Animation(CoreAssets.asUp,1.0);
		down=new Animation(CoreAssets.asDown,1.0);
		right=new Animation(CoreAssets.asRight,1.5);
		left=new Animation(CoreAssets.asLeft,1.5);
		aUp=new Animation(CoreAssets.aAsUp,0.4);
		aDown=new Animation(CoreAssets.aAsDown,0.4);
		aRight=new Animation(CoreAssets.aAsRight,0.4);
		aLeft=new Animation(CoreAssets.aAsLeft,0.4);
		iUp=new Animation(CoreAssets.asiU,1);
		iDown=new Animation(CoreAssets.asiD,1);
		iRight=new Animation(CoreAssets.asiR,1);
		iLeft=new Animation(CoreAssets.asiL,1);
	}
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new ThrownKnife(game, x, y, dir, this));
	}
	public void die(){
		game.$currentWorld().$entityManager().addEntity(new HelmetGib(game, x, y));
		super.die();
	}
}
