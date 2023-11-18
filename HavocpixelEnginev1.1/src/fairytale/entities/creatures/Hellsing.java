package fairytale.entities.creatures;

import fairytale.entities.objects.HellsingCorpse;
import fairytale.entities.projectiles.Fireball;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

import java.awt.Rectangle;

public class Hellsing extends BasicEntity{
	
	public Hellsing(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.LESSER_DEMON;
		armor=Armor.DEMON_SKIN;
		weapon=Weapon.DEMON_CLAWS;
		name="Hellsing";
		maxHealth=500;
		health=maxHealth;
		strength=50;
		bounds=new Rectangle(7,10,15,21);
		
		corpse=new HellsingCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.hiEmerge,0.75,true);
		up=new Animation(CoreAssets.hiUp,1.0);
		down=new Animation(CoreAssets.hiDown,1.0);
		right=new Animation(CoreAssets.hiRight,1.5);
		left=new Animation(CoreAssets.hiLeft,1.5);
		aUp=new Animation(CoreAssets.hAiUp,0.4);
		aDown=new Animation(CoreAssets.hAiDown,0.4);
		aRight=new Animation(CoreAssets.hAiRight,0.4);
		aLeft=new Animation(CoreAssets.hAiLeft,0.4);
		iUp=new Animation(CoreAssets.hiiU,1);
		iDown=new Animation(CoreAssets.hiiD,1);
		iRight=new Animation(CoreAssets.hiiR,1);
		iLeft=new Animation(CoreAssets.hiiL,1);
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new Fireball(game, x, y, dir, this));
	}
}
