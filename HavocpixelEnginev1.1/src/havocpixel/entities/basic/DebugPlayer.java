package havocpixel.entities.basic;

import fairytale.entities.creatures.Bunny;
import fairytale.entities.creatures.Deer;
import fairytale.entities.creatures.Revenant;
import fairytale.entities.objects.CultistCorpse;
import fairytale.entities.projectiles.ShotgunBlast;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Direction;
import havocpixel.entities.Faction;
import havocpixel.entities.WarpJump;
import havocpixel.entities.Weapon;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DebugPlayer extends BasicEntity{

	private double deathTimer;
	private boolean marked=false;
	private boolean spawnBunny=true,spawnRev=true;
	private double currentThres=0, nextThres=0;
	private int maxJump=32*3;
	private double jumpAcc=0.8;
	
	/*
	 * For the player, reloading is done as an action only while the player is not
	 * moving, getting knocked back, or attacking.
	 * 
	 * There are currently a few workaround variables specifically for rendering, 
	 * as rendering is done only after the entity finishes its actions and therefore 
	 * uses up its ticks, in order to get smooth transitions from repeatedly
	 * firing & reloading, as well as moving around while still needing to reload.
	 * 
	 * For the npc, hopefully the implementation will be simpler as the AI can decide
	 * for itself when to be reloading, if at all.
	 * 
	 */
	
	//ammo & reloading
	private int cap=5;
	private int maxCap=5;
	//private int ammoRes=100;
	private double reloadT=0;
	private boolean isReloading(){
		return reloadT>0;
	}
	private void attemptReload(double dt){
		if(reloadT>0){
			if(cap==maxCap){
				reload.update(dt);
				reloadR.update(dt);
				if(reload.isStopped()||isAttacking()){
					reloadT=0;
				}
			}else if(isNotMovingOrAttacking()){
				reload.update(dt);
				reloadR.update(dt);
				if(reload.$currentIndex()>2){
					cap++;
					//ammoRes--;
					if(cap!=maxCap){
						reload.reset();
						reloadR.reset();
					}
				}
			}else{
				reload.reset();
				reloadR.reset();
			}
		}
	}
	private Animation reload=new Animation(CoreAssets.cReload,0.5,true);
	private Animation reloadR=new Animation(CoreAssets.cReloadR,0.5,true);
	private Direction reloadDir=Direction.LEFT;
	
	//temp variable to see if previously moved or knocked back
	private boolean hasBeenActive=false;
	
	public DebugPlayer(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.HUMAN;
		armor=Armor.DAMAGED_PLATE;
		weapon=Weapon.UNARMED;
		name="Debug Player";
		maxHealth=750;
		health=maxHealth;
		strength=0;
		speed=180;
		
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
		
		corpse=new CultistCorpse(game,0,0);
		
		regenRate=60;
		deathTimer=5;
		
	}
	
	public void update(double dt) {
		//game.$camera().hardCenterOnEntity(this);
		game.$camera().centerOnEntity(this, dt);
		//health=100000;
		//knockbackT=-1;
		if(critA>0)
			critA-=dt;
		if(nothingT>ACTION_LENGTH){
			nothingT=ACTION_LENGTH;
		}
		if(spawning){
			spawn.update(dt);
			if(spawn.$currentIndex()>0){
				if(spawnBunny){
					while(!game.$currentWorld().spawnEntityInRenderArea(new Bunny(game,0,0)));
					while(!game.$currentWorld().spawnEntityInRenderArea(new Deer(game,0,0)));
					spawnBunny=false;
				}
			}
			if(spawn.isLastFrame()){
				spawning=false;
			}
			return;
		}
		if(isDead()){
			if(isKnockedBack()){
				getKnockedBack(dt);
			}
			if(deathTimer<1){
				game.$currentWorld().spawnEntityInWorld(new DebugPlayer(game,0,0));
				active=false;
			}else{
				deathTimer-=dt;
			}
			return;
		}
		if(health<0){
			die();
		}
		updateMoveAnimations(dt);
		resolveEffects(dt);
		if(isIdle()){
			if(game.$km().up){
				game.$currentWorld().$entityManager().addEntity(new WarpJump(game,this.x,this.y,0,-maxJump,this,false,false,jumpAcc));
			}else if(game.$km().left){
				game.$currentWorld().$entityManager().addEntity(new WarpJump(game,this.x,this.y,-maxJump,0,this,false,false,jumpAcc));
			}else if(game.$km().down){
				game.$currentWorld().$entityManager().addEntity(new WarpJump(game,this.x,this.y,0,maxJump,this,false,false,jumpAcc));
			}else if(game.$km().right){
				game.$currentWorld().$entityManager().addEntity(new WarpJump(game,this.x,this.y,maxJump,0,this,false,false,jumpAcc));
			}else if(game.$km().W){
				if(game.$km().A){
					dir=Direction.UPLEFT;
				}else if(game.$km().D){
					dir=Direction.UPRIGHT;
				}else{
					dir=Direction.UP;
				}
				moveT=ACTION_LENGTH;
			}else if(game.$km().A){
				if(game.$km().W){
					dir=Direction.UPLEFT;
				}else if(game.$km().S){
					dir=Direction.DOWNLEFT;
				}else{
					dir=Direction.LEFT;
				}
				moveT=ACTION_LENGTH;
			}else if(game.$km().S){
				if(game.$km().A){
					dir=Direction.DOWNLEFT;
				}else if(game.$km().D){
					dir=Direction.DOWNRIGHT;
				}else{
					dir=Direction.DOWN;
				}
				moveT=ACTION_LENGTH;
			}else if(game.$km().D){
				if(game.$km().W){
					dir=Direction.UPRIGHT;
				}else if(game.$km().S){
					dir=Direction.DOWNRIGHT;
				}else{
					dir=Direction.RIGHT;
				}
				moveT=ACTION_LENGTH;
			}else if(game.$km().space){
				//implementing ammo
				if(cap>0){
					cap--;
					if(cap<1){
						reload.reset();
						reloadR.reset();
						reloadT=reload.$speed()*(maxCap+1);
					}
					attackT=aDown.$speed();
					attackWithProjectile=true;
					attacked=false;
					aDown.reset();
					aUp.reset();
					aRight.reset();
					aLeft.reset();
				}
			}else if(game.$km().R){
				if(cap<maxCap){
					reload.reset();
					reloadR.reset();
					reloadT=reload.$speed()*(maxCap+1);
				}else{
					reloadT=0;
				}
			}else if(game.$km().d1){
				if(!marked){
					mark();
					damage(this);
				}
			}else if(game.$km().d2){
				this.regenerateHealth(dt);
			}else if(game.$km().d3){
				if(spawnRev){
					spawnRev=false;
					while(!game.$currentWorld().spawnEntityInRenderArea(new Revenant(game,0,0)));
				}
				
			}else if(game.$km().d4){
				game.$currentWorld().talk("??????", game.$randomString(game.$randomInt(50, 250)));
			}else{
				nothingT=ACTION_LENGTH;
			}
		}
		//temp
		//if(HPBarTimer>0){
		//	HPBarTimer-=dt;
		//}
		
		
		
		if(isKnockedBack())
			q0=1;
		q0-=dt;
		
		//temp active indicator
		if(!isMoving()){//&&!isKnockedBack()
			hasBeenActive=false;
		}else{
			hasBeenActive=true;
		}
		
		//reloading
		if(isReloading()){
			attemptReload(dt);
		}
		resolveAction(dt);
		
		//get reload direction
		if(dir==Direction.LEFT||dir==Direction.DOWNLEFT){
			reloadDir=Direction.LEFT;
		}
		if(dir==Direction.RIGHT||dir==Direction.UPRIGHT){
			reloadDir=Direction.RIGHT;
		}
		
		
		//spawn monsters
		if(marked){
			currentThres+=dt;
			if(currentThres>nextThres){
				game.$currentWorld().spawnRandomMonster();
				nextThres=game.$randomDouble(0.25, 0.5);
				currentThres=0;
			}
			
		}
		//if(blackA>0){
		//	blackA-=dt*3;
		//}
	}
	
	//temp
	//private double HPBarTimer=-1;
	private double q0=0;
	private double critA=0;
	//private double blackA=1.5;
	
	public void render(Graphics g){
		if(isDead()){
			return;
		}
		//this.renderDebug(g);
		if(isReloading()&&!isAttacking()&&!hasBeenActive){
			this.renderShadow(g);
			if((reloadDir==Direction.LEFT)){
				g.drawImage(reload.$currentFrame(), 
						(int)(x-game.$camera().$xOffset())-32,
						(int)(y-game.$camera().$yOffset()),
						this.width+32,
						this.height,
						null);
			}else{
				g.drawImage(reloadR.$currentFrame(), 
						(int)(x-game.$camera().$xOffset()),
						(int)(y-game.$camera().$yOffset()),
						this.width+32,
						this.height,
						null);
			}
			
			//workaround for glitchy attack render after finishing reload
			attackT=0;
			updateCurrentImg();
			return;
		}
		//temp
		super.render(g);
		//if(HPBarTimer>0){
			//this.renderHP(g);
		//}
	}
	
	public void renderOverlay(Graphics g){
		if(dead){
			Utils.drawTranslucentImage(CoreAssets.hurtd,
					0,0,1,
					game.$width(),game.$height(),g);
			if(critA>0){
				Utils.drawTranslucentImage(CoreAssets.crit,
					0,0,(float)critA,
					game.$width(),game.$height(),g);
				//Utils.drawTranslucentImage(CoreAssets.blackout,0,0,(float)(5-critA*5),game.$width(),game.$height(),g);
			}
			if(critA<0.4){
				//Utils.drawTranslucentImage(CoreAssets.blackout,0,0,1,game.$width(),game.$height(),g);
			}
			
			String num="You died. Respawning in "+(int)deathTimer+".";
			g.setFont(new Font("Lucida Console",Font.BOLD,10));
			Utils.drawStringWithOutline(g,num,
					(int)(game.$width()/2-g.getFontMetrics().stringWidth(num)/2)-0,
					(int)(game.$height())-24,Color.RED);
		}else{
			if(health<(maxHealth*2)/3){
				if(q0<0){
					q0=1;
				}
			}
			if(q0>0){
				float d;
				if(health>((maxHealth*2)/3)){
					d=0;
				}else{
					d=(float)(((maxHealth*2)/3)-health)/((maxHealth*2)/3);
				}
				if(d<0)
					d=0.0F;
				if(d>0.8)
					d=0.8F;
				d+=q0*0.2F;
				Utils.drawTranslucentImage(health>(maxHealth/3)?CoreAssets.hurt2:CoreAssets.hurt,
						0,0,d,
						game.$width(),game.$height(),g);
			}
			//if(blackA>0){
			//	Utils.drawTranslucentImage(CoreAssets.blackout,0,0,(float)blackA,game.$width(),game.$height(),g);
			//}
		}
		
		//ammo
		//String num=cap+"/"+ammoRes;
		//g.setFont(game.$defaultFont());
		//Utils.drawStringWithOutline(g,num,
		//		(int)(game.$width()-g.getFontMetrics().stringWidth(num))-2,
		//		(int)(game.$height())-2,Color.WHITE);
	}
	
	public void die(){
		ghost=true;
		critA=1.1;
		super.die();
	}
	
	
	public void knockback(Direction kbdir){
		if(spawning)
			return;
		knockbackT=0.1;
		this.kbdir=kbdir;
		//temp
		//HPBarTimer=3;
		stopActions();
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
			//continuous fire, implementing reload
			if(game.$km().space){
				if(cap>0){
					cap--;
					if(cap<1){
						reload.reset();
						reloadR.reset();
						reloadT=reload.$speed()*(maxCap+1);
					}
					attackT=aDown.$speed();
					attackWithProjectile=true;
					attacked=false;
					aDown.reset();
					aUp.reset();
					aRight.reset();
					aLeft.reset();
				}
			}
		}
		if(attackT<0){
			nothingT=ACTION_LENGTH;
		}
	}
	
	public void mark(){
		marked=true;
	}
	
	public void attackWithProjectile(){
		//game.$currentWorld().$entityManager().addEntity(new AnnihilatingBlast(game, x, y, dir, this));
		//game.$currentWorld().$entityManager().addEntity(new RifleBlast(game, x, y, dir, this));
		game.$currentWorld().$entityManager().addEntity(new ShotgunBlast(game, x, y, dir, this));
		//game.$currentWorld().$entityManager().addEntity(new Rocket(game, x, y, dir, this));
		//game.$currentWorld().$entityManager().addEntity(new ThrownKnife(game, x, y, dir, this));
	}
}
