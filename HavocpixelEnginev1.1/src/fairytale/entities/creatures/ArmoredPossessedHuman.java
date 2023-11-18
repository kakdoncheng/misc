package fairytale.entities.creatures;

import fairytale.entities.objects.ArmoredPossessedHumanCorpse;
import fairytale.entities.projectiles.ThrownKnife;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class ArmoredPossessedHuman extends BasicEntity{
	
	public ArmoredPossessedHuman(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.POSSESSED;
		armor=Armor.STEEL_PLATE;
		weapon=Weapon.DAMAGED_SWORD;
		name="Possessed Human";
		maxHealth=750;
		health=maxHealth;
		strength=50;
		speed=120;
		
		corpse=new ArmoredPossessedHumanCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.aphEmerge,0.75,true);
		up=new Animation(CoreAssets.aphUp,1.0);
		down=new Animation(CoreAssets.aphDown,1.0);
		right=new Animation(CoreAssets.aphRight,1.5);
		left=new Animation(CoreAssets.aphLeft,1.5);
		aUp=new Animation(CoreAssets.aAphUp,0.4);
		aDown=new Animation(CoreAssets.aAphDown,0.4);
		aRight=new Animation(CoreAssets.aAphRight,0.4);
		aLeft=new Animation(CoreAssets.aAphLeft,0.4);
		iUp=new Animation(CoreAssets.aphiU,1);
		iDown=new Animation(CoreAssets.aphiD,1);
		iRight=new Animation(CoreAssets.aphiR,1);
		iLeft=new Animation(CoreAssets.aphiL,1);
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new ThrownKnife(game, x, y, dir, this));
	}

}
