package fairytale.entities.creatures;

import fairytale.entities.projectiles.ThrownKnife;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Faction;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class Zombie extends BasicEntity{

	public Zombie(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.POSSESSED;
		name="Zombie";
		armor=Armor.ROTTING_FLESH;
		strength=50;
		this.canAttackWithProjectile=false;
		speed=60;
		
		spawn=new Animation(CoreAssets.zsEmerge,1.5,true);
		up=new Animation(CoreAssets.zsUp,1.0);
		down=new Animation(CoreAssets.zsDown,1.0);
		right=new Animation(CoreAssets.zsRight,1.5);
		left=new Animation(CoreAssets.zsLeft,1.5);
		aUp=new Animation(CoreAssets.zAsUp,0.4);
		aDown=new Animation(CoreAssets.zAsDown,0.4);
		aRight=new Animation(CoreAssets.zAsRight,0.4);
		aLeft=new Animation(CoreAssets.zAsLeft,0.4);
		iUp=new Animation(CoreAssets.zsiU,1);
		iDown=new Animation(CoreAssets.zsiD,1);
		iRight=new Animation(CoreAssets.zsiR,1);
		iLeft=new Animation(CoreAssets.zsiL,1);
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new ThrownKnife(game, x, y, dir, this));
	}

}
