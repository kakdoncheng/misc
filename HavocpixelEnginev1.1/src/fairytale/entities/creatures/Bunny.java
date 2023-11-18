package fairytale.entities.creatures;

import fairytale.entities.objects.BunnyCorpse;
import fairytale.entities.objects.BunnyHeadGib;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.entities.basic.DebugPlayer;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

import java.awt.Rectangle;

public class Bunny extends BasicEntity{
	
	public Bunny(Game game, int x, int y) {
		super(game, x, y);
		bounds=new Rectangle(10,20,12,10);
		faction=Faction.UNALIGNED;
		armor=Armor.FLESH;
		weapon=Weapon.UNARMED;
		name="Bunny";
		maxHealth=100;
		health=maxHealth;
		strength=5;
		canAttackWithProjectile=false;
		aggressive=false;
		
		corpse=new BunnyCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.biL,1);
		up=new Animation(CoreAssets.bUp,1.0);
		down=new Animation(CoreAssets.bDown,1.0);
		right=new Animation(CoreAssets.bRight,1.5);
		left=new Animation(CoreAssets.bLeft,1.5);
		iUp=new Animation(CoreAssets.biU,1);
		iDown=new Animation(CoreAssets.biD,1);
		iRight=new Animation(CoreAssets.biR,1);
		iLeft=new Animation(CoreAssets.biL,1);
	}
	
	public void update(double dt){
		if(this.swornTarget!=null&&swornTarget instanceof DebugPlayer){
			((DebugPlayer)swornTarget).mark();
		}
		super.update(dt);
	}
	
	protected void decideNextAction(double dt){
		doNextRandomAction(dt);
	}
	
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new BunnyHeadGib(game,this.x-10,this.y-14));
	}
}
