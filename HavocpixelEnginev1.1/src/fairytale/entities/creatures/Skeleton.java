package fairytale.entities.creatures;

import fairytale.entities.projectiles.ThrownKnife;
import havocpixel.Game;
import havocpixel.entities.Faction;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class Skeleton extends BasicEntity{

	public Skeleton(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.POSSESSED;
		name="Skeleton";
		this.canAttackWithProjectile=false;
		speed=60;
		
		spawn=new Animation(CoreAssets.sEmerge,1.5,true);
		up=new Animation(CoreAssets.sUp,1.0);
		down=new Animation(CoreAssets.sDown,1.0);
		right=new Animation(CoreAssets.sRight,1.5);
		left=new Animation(CoreAssets.sLeft,1.5);
		aUp=new Animation(CoreAssets.AsUp,0.4);
		aDown=new Animation(CoreAssets.AsDown,0.4);
		aRight=new Animation(CoreAssets.AsRight,0.4);
		aLeft=new Animation(CoreAssets.AsLeft,0.4);
		iUp=new Animation(CoreAssets.siU,1);
		iDown=new Animation(CoreAssets.siD,1);
		iRight=new Animation(CoreAssets.siR,1);
		iLeft=new Animation(CoreAssets.siL,1);
	}
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new ThrownKnife(game, x, y, dir, this));
	}

}
