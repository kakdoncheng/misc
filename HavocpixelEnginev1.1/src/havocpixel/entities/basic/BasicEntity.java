package havocpixel.entities.basic;

import fairytale.entities.objects.GibbedCorpse;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEntity extends Entity{
	
	protected Entity corpse;

	public BasicEntity(Game game, int x, int y) {
		super(game, x, y);
		maxHealth=500;
		width=32;
		height=32;
		health=maxHealth;
		speed=90;
		strength=0;
		bounds=new Rectangle(8,10,15,21);
		armor=Armor.SKELETON;
		weapon=Weapon.DAMAGED_CLEAVER;
		faction=Faction.POSSESSED;
		dir=Direction.DOWN;
		kbdir=Direction.DOWN;
		name="Basic Entity";
		swornTarget=null;
		currentTarget=null;
		active=true;
		dead=false;
		particle=false;
		object=false;
		projectile=false;
		item=false;
		fleshy=true;
		ghost=false;
		invulnerable=false;
		aggressive=true;
		spawning=true;
		canAttackWithProjectile=true;
		stopActions();
		stopEffects();
		
		corpse=new GibbedCorpse(game,0,0);
		
		spawn=new Animation(CoreAssets.hsEmerge,1.5,true);
		up=new Animation(CoreAssets.hsUp,1.0);
		down=new Animation(CoreAssets.hsDown,1.0);
		right=new Animation(CoreAssets.hsRight,1.5);
		left=new Animation(CoreAssets.hsLeft,1.5);
		aUp=new Animation(CoreAssets.hAsUp,0.4);
		aDown=new Animation(CoreAssets.hAsDown,0.4);
		aRight=new Animation(CoreAssets.hAsRight,0.4);
		aLeft=new Animation(CoreAssets.hAsLeft,0.4);
		iUp=new Animation(CoreAssets.hsiU,1);
		iDown=new Animation(CoreAssets.hsiD,1);
		iRight=new Animation(CoreAssets.hsiR,1);
		iLeft=new Animation(CoreAssets.hsiL,1);
	}

	public void update(double dt) {
		if(spawning){
			spawn.update(dt);
			if(spawn.isLastFrame()){
				spawning=false;
			}
			if(health<0){
				spawning=false;
				die();
			}
			return;
		}
		if(isDead()){
			active=false;
			return;
		}
		if(health<0){
			die();
		}
		updateMoveAnimations(dt);
		resolveEffects(dt);
		if(isIdle()){
			resolveCurrentTarget();
			decideNextAction(dt);
		}
		resolveAction(dt);
	}

	public void render(Graphics g) {
		updateCurrentImg();
		//this.renderDebug(g);
		if(spawning){
			img=spawn.$currentFrame();
			g.drawImage(img, 
					(int)(x-game.$camera().$xOffset()),
					(int)(y-game.$camera().$yOffset()),
					this.width,
					this.height,
					null);
			return;
		}
		if(dead){
			img=down.$currentFrame();
			g.drawImage(img, 
					(int)(x-game.$camera().$xOffset()),
					(int)(y-game.$camera().$yOffset()),
					this.width,
					this.height,
					null);
			return;
		}
		this.renderShadow(g);
		Direction actualDir=$actualDirection(dir);
		if(isAttacking()){
			if(actualDir==Direction.LEFT){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset())-32,
						(int)(y-game.$camera().$yOffset()),
						this.width+32,
						this.height,
						null);
			}else if(actualDir==Direction.UP){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset()),
						(int)(y-game.$camera().$yOffset())-32,
						this.width,
						this.height+32,
						null);
			}else if(actualDir==Direction.RIGHT){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset()),
						(int)(y-game.$camera().$yOffset()),
						this.width+32,
						this.height,
						null);
			}else if(actualDir==Direction.DOWN){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset()),
						(int)(y-game.$camera().$yOffset()),
						this.width,
						this.height+32,
						null);
			}
		}else{
			g.drawImage(img, 
					(int)(x-game.$camera().$xOffset()),
					(int)(y-game.$camera().$yOffset()),
					this.width,
					this.height,
					null);
		}
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new BasicProjectile(game, x, y, dir, this));
	}

	public void die() {
		dead=true;
		corpse.setXY(x, y);
		game.$currentWorld().$entityManager().addEntity(corpse);
	}

}
