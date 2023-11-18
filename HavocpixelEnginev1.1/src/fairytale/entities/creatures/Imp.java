package fairytale.entities.creatures;

import fairytale.entities.objects.ImpCorpse;
import fairytale.entities.projectiles.Fireball;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class Imp extends BasicEntity{
	
	public Imp(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.LESSER_DEMON;
		armor=Armor.DEMON_SKIN;
		weapon=Weapon.DEMON_CLAWS;
		name="Imp";
		maxHealth=750;
		health=maxHealth;
		strength=200;
		canAttackWithProjectile=false;
		
		corpse=new ImpCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.iEmerge,0.75,true);
		up=new Animation(CoreAssets.iUp,1.0);
		down=new Animation(CoreAssets.iDown,1.0);
		right=new Animation(CoreAssets.iRight,1.5);
		left=new Animation(CoreAssets.iLeft,1.5);
		aUp=new Animation(CoreAssets.AiUp,0.4);
		aDown=new Animation(CoreAssets.AiDown,0.4);
		aRight=new Animation(CoreAssets.AiRight,0.4);
		aLeft=new Animation(CoreAssets.AiLeft,0.4);
		iUp=new Animation(CoreAssets.iiU,1);
		iDown=new Animation(CoreAssets.iiD,1);
		iRight=new Animation(CoreAssets.iiR,1);
		iLeft=new Animation(CoreAssets.iiL,1);
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new Fireball(game, x, y, dir, this));
	}

}
