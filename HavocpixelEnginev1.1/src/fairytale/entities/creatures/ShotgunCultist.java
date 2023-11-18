package fairytale.entities.creatures;

import fairytale.entities.objects.CultistCorpse;
import fairytale.entities.projectiles.ShotgunBlast;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Direction;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

import java.awt.Rectangle;

public class ShotgunCultist extends BasicEntity{
	
	public ShotgunCultist(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.HUMAN;
		armor=Armor.DAMAGED_PLATE;
		weapon=Weapon.UNARMED;
		name="Shotgun Cultist";
		maxHealth=750;
		health=maxHealth;
		strength=0;
		speed=60;
		
		corpse=new CultistCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.cEmerge,0.75,true);
		up=new Animation(CoreAssets.cUp,1.0);
		down=new Animation(CoreAssets.cDown,1.0);
		right=new Animation(CoreAssets.cRight,1.5);
		left=new Animation(CoreAssets.cLeft,1.5);
		aUp=new Animation(CoreAssets.AcUp,0.4);
		aDown=new Animation(CoreAssets.AcDown,0.4);
		aRight=new Animation(CoreAssets.AcRight,0.4);
		aLeft=new Animation(CoreAssets.AcLeft,0.4);
		iUp=new Animation(CoreAssets.ciU,1);
		iDown=new Animation(CoreAssets.ciD,1);
		iRight=new Animation(CoreAssets.ciR,1);
		iLeft=new Animation(CoreAssets.ciL,1);
	}
	
	protected boolean attemptToAttackTarget(){
		Direction toTarget=$actualDirection($directionTowards(currentTarget));
		if(canAttackWithProjectile&&nextAttackWithProjectile>nextAttackWithProjectileThres){
			Rectangle ar;
			if(toTarget==Direction.DOWN){
				ar=new Rectangle(32,100);
				ar.x=(int)x;
				ar.y=(int)y+height+0;
			}else if(toTarget==Direction.RIGHT){
				ar=new Rectangle(100,32);
				ar.x=(int)x+width+0;
				ar.y=(int)y;
			}else if(toTarget==Direction.UP){
				ar=new Rectangle(32,100);
				ar.x=(int)x;
				ar.y=(int)y-100;
			}else{ //left
				ar=new Rectangle(100,32);
				ar.x=(int)x-100;
				ar.y=(int)y;
			}
			if(currentTarget.$collisionBounds(0,0).intersects(ar)){
				nextAttackWithProjectile=0;
				nextAttackWithProjectileThres=game.$randomDouble(1, 2.5);
				attackT=aDown.$speed();
				attackWithProjectile=true;
				dir=toTarget;
				attacked=false;
				aDown.reset();
				aUp.reset();
				aRight.reset();
				aLeft.reset();
				return true;
			}
		}
		return false;
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new ShotgunBlast(game, x, y, dir, this));
	}

}
