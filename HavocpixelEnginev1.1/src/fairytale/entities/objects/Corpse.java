package fairytale.entities.objects;

import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Faction;
import havocpixel.entities.MoveableObject;
import havocpixel.entities.Weapon;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Graphics;

public class Corpse extends MoveableObject{
	
	protected Animation secDeath;
	protected double a, ghostTimer, rotTimer;
	protected boolean reanimate;
	protected double reanimateTimer;

	public Corpse(Game game, double x, double y) {
		super(game, x, y);
		faction=Faction.UNALIGNED;
		armor=Armor.SKELETON;
		weapon=Weapon.UNARMED;
		name="Dead Corpse";
		maxHealth=150;
		health=maxHealth;
		strength=0;
		
		bounds.y=21;
		bounds.height=10;
		secDeath=new Animation(CoreAssets.gibCorpse[game.$randomInt(0, 4)],0.75,true);
		death=new Animation(CoreAssets.iDeath,0.75,true);
		a=1;
		ghostTimer=0;
		rotTimer=30;
		dead=true;
		invulnerable=false;
		fleshy=true;
		
		reanimate=false;
		reanimateTimer=2;
	}
	
	public void update(double dt){
		if(!isGhost()&&rotTimer>=0){
			rotTimer-=dt;
		}
		if(!isGhost()&&rotTimer<0){
			System.out.println(this+" rotted.");
			die();
		}
		if(!isGhost()&&health<0){
			die();
		}
		reanimateTimer-=dt;
		if(!isGhost()&&!entityCollision(0,-10)&&reanimate&&reanimateTimer<0){
			reanimate();
		}
		if(ghost){
			ghostTimer+=dt;
			if(ghostTimer>5){
				a-=dt;
			}
			if(a<0){
				active=false;
			}
		}
		if(!death.isLastFrame())
			death.update(dt);
		if(!isGhost())
			super.update(dt);
	}
	
	public void render(Graphics g){
		img=death.$currentFrame();
		if(a<1)
			Utils.drawTranslucentImage(img,
					(int)(x-game.$camera().$xOffset()),
					(int)(y-game.$camera().$yOffset()),(float)a,
					32,
					32, g);
		else
			g.drawImage(img,
					(int)(x-game.$camera().$xOffset()),
					(int)(y-game.$camera().$yOffset()),
					32,
					32,null);
		return;
	}
	
	public void die(){
		death=secDeath;
		ghost=true;
	}
	
	public void reanimate(){
		
	}

}
