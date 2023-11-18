package fairytale.entities.creatures;

import fairytale.entities.objects.CultistCorpse;
import fairytale.entities.projectiles.ThrownKnife;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class Cultist extends BasicEntity{

	public Cultist(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.HUMAN;
		armor=Armor.DAMAGED_PLATE;
		weapon=Weapon.DAMAGED_CLEAVER;
		name="Cultist";
		maxHealth=750;
		health=maxHealth;
		strength=50;
		speed=120;
		
		corpse=new CultistCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.mcEmerge,0.75,true);
		up=new Animation(CoreAssets.mcUp,1.0);
		down=new Animation(CoreAssets.mcDown,1.0);
		right=new Animation(CoreAssets.mcRight,1.5);
		left=new Animation(CoreAssets.mcLeft,1.5);
		aUp=new Animation(CoreAssets.mAcUp,0.4);
		aDown=new Animation(CoreAssets.mAcDown,0.4);
		aRight=new Animation(CoreAssets.mAcRight,0.4);
		aLeft=new Animation(CoreAssets.mAcLeft,0.4);
		iUp=new Animation(CoreAssets.mciU,1);
		iDown=new Animation(CoreAssets.mciD,1);
		iRight=new Animation(CoreAssets.mciR,1);
		iLeft=new Animation(CoreAssets.mciL,1);
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new ThrownKnife(game, x, y, dir, this));
	}

}
