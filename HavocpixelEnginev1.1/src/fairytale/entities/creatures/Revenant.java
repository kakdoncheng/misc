package fairytale.entities.creatures;

import fairytale.entities.objects.RevenantCorpse;
import fairytale.entities.projectiles.ExplosiveFireball;
import havocpixel.Game;
import havocpixel.Tile;
import havocpixel.entities.Armor;
import havocpixel.entities.Direction;
import havocpixel.entities.Faction;
import havocpixel.entities.WarpJump;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicFloatingEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Revenant extends BasicFloatingEntity{

	private int burst;
	private double jumpT, jumpTC;
	public Revenant(Game game, int x, int y) {
		super(game, x, y);
		name="Revenant";
		corpse=new RevenantCorpse(game,0,0);
		armor=Armor.DEMON_SKIN;
		weapon=Weapon.DEMON_CLAWS;
		faction=Faction.GREATER_DEMON;
		
		maxHealth=2500;
		health=maxHealth;
		
		jumpT=0;
		jumpTC=1.0;
		
		burst=2;
		spawning=true;
		aggressive=false;
		this.canAttackWithProjectile=true;
		
		spawn=new Animation(CoreAssets.reSpawn,0.4);
		aDown=new Animation(new BufferedImage[]{CoreAssets.revenant[0],CoreAssets.revenant[3],CoreAssets.revenant[3]},0.4);
		aRight=new Animation(new BufferedImage[]{CoreAssets.revenant[1],CoreAssets.revenant[4],CoreAssets.revenant[4]},0.4);
		aLeft=new Animation(new BufferedImage[]{CoreAssets.revenant[2],CoreAssets.revenant[5],CoreAssets.revenant[5]},0.4);
	}
	
	public void update(double dt){
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
		jumpT+=dt;
		//if(isAttacking()){
		//	game.distortGraphics(0.5);
		//}
		super.update(dt);
	}
	
	protected boolean attemptToAttackTarget(){
		Direction toTarget=$actualDirection($directionTowards(currentTarget));
		if(toTarget==Direction.UP){
			return false;
		}
		if(currentTarget.$collisionBounds(0,0).intersects(weapon.$hitBounds($collisionBounds(0, 0), toTarget))){
			attackT=aDown.$speed();
			attackWithProjectile=false;
			dir=toTarget;
			attacked=false;
			aDown.reset();
			aUp.reset();
			aRight.reset();
			aLeft.reset();
			return true;
		}
		if(canAttackWithProjectile&&nextAttackWithProjectile>nextAttackWithProjectileThres){
			Rectangle ar;
			if(toTarget==Direction.DOWN){
				ar=new Rectangle(32,100);
				ar.x=(int)x;
				ar.y=(int)y+height+60;
			}else if(toTarget==Direction.RIGHT){
				ar=new Rectangle(100,32);
				ar.x=(int)x+width+60;
				ar.y=(int)y;
			}else{ //left
				ar=new Rectangle(100,32);
				ar.x=(int)x-160;
				ar.y=(int)y;
			}
			if(currentTarget.$collisionBounds(0,0).intersects(ar)){
				nextAttackWithProjectile=0;
				nextAttackWithProjectileThres=game.$randomDouble(0.75, 2.0);
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
	
	public void attacking(double dt){
		attackT-=dt;
		aDown.update(dt);
		aUp.update(dt);
		aRight.update(dt);
		aLeft.update(dt);
		if(aDown.$currentIndex()>=2){
			if(!attacked){
				if(!attackWithProjectile)
					attack();
				else
					attackWithProjectile();
				attacked=true;
			}
			if(attackWithProjectile&&burst>0){
				attackT=aDown.$speed();
				attackWithProjectile=true;
				attacked=false;
				aDown.reset();
				aUp.reset();
				aRight.reset();
				aLeft.reset();
				burst--;
			}
		}
		if(attackT<0){
			nothingT=ACTION_LENGTH*game.$randomDouble(1, 4);
			burst=2;
		}
	}
	
	private void warpToSwornTarget(){
		if(this.health<1||this.swornTarget==null){
			return;
		}
		int d=game.$randomInt(0, 3);
		boolean canJump=true;
		double nx=this.swornTarget.$x(),ny=this.swornTarget.$y();
		int off=0;
		switch(d){
			case 0: //facing down
				off=32*3;
				while(this.entityCollision(nx-x, ny-off-y)||this.collision((int)(nx/Tile.TILE_WIDTH),(int)((ny-off)/Tile.TILE_HEIGHT))){
					if(off<32){
						canJump=false;
						break;
					}
					off--;
				}
				if(canJump){
					this.dir=Direction.DOWN;
					game.$currentWorld().$entityManager().addEntity(new WarpJump(game,x,y,nx-x,ny-off-y,this,true,false,1));
					break;
				}else{
					canJump=true;
				}
			case 1: //facing right
				off=32*3;
				while(this.entityCollision(nx-off-x, ny-y)||this.collision((int)((nx-off)/Tile.TILE_WIDTH),(int)(ny/Tile.TILE_HEIGHT))){
					if(off<32){
						canJump=false;
						break;
					}
					off--;
				}
				if(canJump){
					this.dir=Direction.RIGHT;
					game.$currentWorld().$entityManager().addEntity(new WarpJump(game,x,y,nx-off-x,ny-y,this,true,false,1));
					break;
				}else{
					canJump=true;
				}
			case 2: //facing left
				off=32*3;
				while(this.entityCollision(nx+off-x, ny-y)||this.collision((int)((nx+off)/Tile.TILE_WIDTH),(int)(ny/Tile.TILE_HEIGHT))){
					if(off<32){
						canJump=false;
						break;
					}
					off--;
				}
				if(canJump){
					this.dir=Direction.LEFT;
					game.$currentWorld().$entityManager().addEntity(new WarpJump(game,x,y,nx+off-x,ny-y,this,true,false,1));
					break;
				}else{
					canJump=true;
				}
			default:
				canJump=false;
				break;
		}
		
		if(canJump){
			//nextAttackWithProjectile=nextAttackWithProjectileThres;
			burst=2;
			attackT=aDown.$speed();
			attackWithProjectile=true;
			attacked=false;
			aDown.reset();
			aUp.reset();
			aRight.reset();
			aLeft.reset();
		}
		
	}
	
	protected void resolveAction(double dt){
		nextAttackWithProjectile+=dt;
		if(this.isKnockedBack()){
			getKnockedBack(dt);
			if(jumpT>jumpTC){
				jumpT=0;
				knockbackT=0;
				stopActions();
				warpToSwornTarget();
				
			}
			return;
		}
		if(this.isAttacking()){
			attacking(dt);
			return;
		}
		if(this.isMoving()){
			if(!doesNotExist(currentTarget)&&isAggressive()){
				moveTowardsTarget(dt);
			}else{
				move(dt);
			}
			return;
		}
		if(this.isDoingNothing()){
			doNothing(dt);
			return;
		}
	}
	
	public void render(Graphics g) {
		updateCurrentImg();
		this.renderShadow(g);
		if(spawning){
			g.drawImage(spawn.$currentFrame(), 
					(int)(x-game.$camera().$xOffset())-16,
					(int)(y-game.$camera().$yOffset()+dy)-16,
					64,
					64,
					null);
			return;
		}
		g.drawImage(img, 
				(int)(x-game.$camera().$xOffset())-16,
				(int)(y-game.$camera().$yOffset()+dy)-16,
				64,
				64,
				null);
	}
	
	public void updateCurrentImg(){
		Direction actualDir=$actualDirection(dir);
		if(isAttacking()){
			if(actualDir==Direction.LEFT){
				img=aLeft.$currentFrame();
			}else if(actualDir==Direction.RIGHT){
				img=aRight.$currentFrame();
			}else if(actualDir==Direction.DOWN){
				img=aDown.$currentFrame();
			}
		}else{
			if(actualDir==Direction.LEFT){
				img=CoreAssets.revenant[2];
			}else if(actualDir==Direction.RIGHT){
				img=CoreAssets.revenant[1];
			}else if(actualDir==Direction.DOWN){
				img=CoreAssets.revenant[0];
			}
		}
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new ExplosiveFireball(game, x, y, dir, this));
		//game.$currentWorld().$entityManager().addEntity(new CursedKnife(game, x, y, currentTarget, this));
	}

}
