package havocpixel.entities.basic;

import java.awt.Graphics;
import java.awt.Rectangle;

import fairytale.entities.objects.GibbedCorpse;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class BasicFloatingEntity extends Entity{
	protected Entity corpse;
	protected double dy;
	protected boolean floatUp;

	public BasicFloatingEntity(Game game, int x, int y) {
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
		name="Basic Floating Entity";
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
		spawning=false;
		canAttackWithProjectile=true;
		stopActions();
		stopEffects();
		
		corpse=new GibbedCorpse(game,0,0);
		dy=0;
		floatUp=true;
		aUp=new Animation(CoreAssets.hAsUp,ACTION_LENGTH*4);
		aDown=new Animation(CoreAssets.hAsDown,ACTION_LENGTH*4);
		aRight=new Animation(CoreAssets.hAsRight,ACTION_LENGTH*4);
		aLeft=new Animation(CoreAssets.hAsLeft,ACTION_LENGTH*4);
	}

	public void update(double dt) {
		if(floatUp){
			dy+=dt*15;
		}else{
			dy-=dt*15;
		}
		if(dy>0){
			floatUp=false;
		}
		if(dy<-7){
			floatUp=true;
		}
		if(isDead()){
			active=false;
			return;
		}
		if(health<0){
			die();
		}
		resolveEffects(dt);
		if(isIdle()){
			resolveCurrentTarget();
			decideNextAction(dt);
		}
		resolveAction(dt);
	}

	public void render(Graphics g) {
		updateCurrentImg();
		this.renderShadow(g);
		g.drawImage(img, 
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()+dy),
				this.width,
				this.height,
				null);
	}
	
	public void updateCurrentImg(){
		Direction actualDir=$actualDirection(dir);
		if(isAttacking()){
			if(actualDir==Direction.LEFT){
				img=CoreAssets.defaultTile[0];
			}else if(actualDir==Direction.UP){
				img=CoreAssets.defaultTile[0];
			}else if(actualDir==Direction.RIGHT){
				img=CoreAssets.defaultTile[0];
			}else if(actualDir==Direction.DOWN){
				img=CoreAssets.defaultTile[0];
			}
		}else{
			if(actualDir==Direction.LEFT){
				img=CoreAssets.defaultTile[0];
			}else if(actualDir==Direction.UP){
				img=CoreAssets.defaultTile[0];
			}else if(actualDir==Direction.RIGHT){
				img=CoreAssets.defaultTile[0];
			}else if(actualDir==Direction.DOWN){
				img=CoreAssets.defaultTile[0];
			}
		}
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new BasicProjectile(game, x, y+dy, dir, this));
	}

	public void die() {
		dead=true;
		corpse.setXY(x, y);
		game.$currentWorld().$entityManager().addEntity(corpse);
	}

}
