package fairytale.entities.creatures;

import fairytale.entities.projectiles.ThrownKnife;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Direction;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicEntity;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Jayde extends BasicEntity{
	
	private double deathTimer, comboTimer;
	private double attackSpeed;
	private int altAttack=0;
	
	//temp
	private double HPBarTimer=-1;
	
	public Jayde(Game game, int x, int y) {
		super(game, x, y);
		faction=Faction.HUMAN;
		armor=Armor.STEEL_PLATE;
		weapon=Weapon.ZWEIHANDER;
		name="Jayde";
		maxHealth=1000;
		health=maxHealth;
		strength=500;
		speed=180;
		spawning=false;
		
		spawn=new Animation(CoreAssets.cEmerge,0.75,true);
		up=new Animation(CoreAssets.zU,1.0);
		down=new Animation(CoreAssets.zD,1.0);
		right=new Animation(CoreAssets.zR,1.5);
		left=new Animation(CoreAssets.zL,1.5);
		aUp=new Animation(CoreAssets.zAU[altAttack],0.5);
		aDown=new Animation(CoreAssets.zAD[altAttack],0.5);
		aRight=new Animation(CoreAssets.zAR[altAttack],0.5);
		aLeft=new Animation(CoreAssets.zAL[altAttack],0.5);
		iUp=new Animation(CoreAssets.ziU,1);
		iDown=new Animation(CoreAssets.ziD,1);
		iRight=new Animation(CoreAssets.ziR,1);
		iLeft=new Animation(CoreAssets.ziL,1);
		
		invulnerable=true;
		
		deathTimer=10;
		comboTimer=0;
		attackSpeed=0;
	}
	
	public void update(double dt) {
		game.$camera().centerOnEntity(this, dt);
		if(nothingT>ACTION_LENGTH){
			nothingT=ACTION_LENGTH;
		}
		if(spawning){
			spawn.update(dt);
			if(spawn.isLastFrame()){
				spawning=false;
			}
			return;
		}
		if(isDead()){
			if(isKnockedBack()){
				getKnockedBack(dt);
			}
			if(!death.isLastFrame())
				death.update(dt);
			if(deathTimer<1){
				game.$currentWorld().spawnEntityInWorld(new Jayde(game,0,0));
				active=false;
			}else{
				deathTimer-=dt;
			}
			return;
		}
		if(health<0){
			die();
		}
		comboTimer-=dt;
		if(comboTimer<0){
			if(attackSpeed>0)
				attackSpeed-=dt/10;
		}
		updateMoveAnimations(dt);
		resolveEffects(dt);
		if(isIdle()){
			if(game.$km().up){
				if(game.$km().left){
					dir=Direction.UPLEFT;
				}else if(game.$km().right){
					dir=Direction.UPRIGHT;
				}else{
					dir=Direction.UP;
				}
				moveT=ACTION_LENGTH;
			}else if(game.$km().left){
				if(game.$km().up){
					dir=Direction.UPLEFT;
				}else if(game.$km().down){
					dir=Direction.DOWNLEFT;
				}else{
					dir=Direction.LEFT;
				}
				moveT=ACTION_LENGTH;
			}else if(game.$km().down){
				if(game.$km().left){
					dir=Direction.DOWNLEFT;
				}else if(game.$km().right){
					dir=Direction.DOWNRIGHT;
				}else{
					dir=Direction.DOWN;
				}
				moveT=ACTION_LENGTH;
			}else if(game.$km().right){
				if(game.$km().up){
					dir=Direction.UPRIGHT;
				}else if(game.$km().down){
					dir=Direction.DOWNRIGHT;
				}else{
					dir=Direction.RIGHT;
				}
				moveT=ACTION_LENGTH;
			}else if(game.$km().space){
				attackT=aDown.$speed();
				attacked=false;
				attackWithProjectile=false;
				aDown.reset();
				aUp.reset();
				aRight.reset();
				aLeft.reset();
			}else if(game.$km().qu1||game.$km().qu0){
				attackT=aDown.$speed();
				attacked=false;
				attackWithProjectile=true;
				aDown.reset();
				aUp.reset();
				aRight.reset();
				aLeft.reset();
			}else{
				nothingT=ACTION_LENGTH;
			}
		}
		//temp
		if(HPBarTimer>0){
			HPBarTimer-=dt;
		}
		resolveAction(dt);
	}
	
	public void knockback(Direction kbdir){
		if(spawning)
			return;
		knockbackT=0.1;
		this.kbdir=kbdir;
		//temp
		HPBarTimer=3;
		//stopActions();
	}
	public void attacking(double dt){
		attackT-=dt;
		aDown.update(dt);
		aUp.update(dt);
		aRight.update(dt);
		aLeft.update(dt);
		if(aDown.$currentIndex()>=2){
			if(!attacked){
				comboTimer=3;
				attackSpeed+=0.02;
				if(attackSpeed>0.1){
					attackSpeed=0.1;
				}
				if(attackSpeed<0){
					attackSpeed=0;
				}
				if(!attackWithProjectile)
					attack();
				else
					attackWithProjectile();
				attacked=true;
			}
			if(aDown.$currentIndex()<3)
				move(dt);
		}
		if(attackT<0){
			nothingT=ACTION_LENGTH;
			altAttack=(altAttack+1)%2;
			aUp=new Animation(CoreAssets.zAU[altAttack],0.5-attackSpeed);
			aDown=new Animation(CoreAssets.zAD[altAttack],0.5-attackSpeed);
			aRight=new Animation(CoreAssets.zAR[altAttack],0.5-attackSpeed);
			aLeft=new Animation(CoreAssets.zAL[altAttack],0.5-attackSpeed);
		}
	}
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new ThrownKnife(game, x, y, dir, this));
	}
	
	public void render(Graphics g){
		updateCurrentImg();
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
			if(isDead()){
				String num=(int)deathTimer+"";
				g.setFont(new Font("Lucida Console",Font.BOLD,10));
				Utils.drawStringWithOutline(g,num,
						(int)(this.x+16-(g.getFontMetrics().stringWidth(num)/2)-game.$camera().$xOffset()),
						(int)(this.y-game.$camera().$yOffset())+10,Color.RED);
			}
			return;
		}
		this.renderShadow(g);
		Direction actualDir=$actualDirection(dir);
		if(isAttacking()){
			if(actualDir==Direction.LEFT){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset())-32,
						(int)(y-game.$camera().$yOffset())-32,
						this.width*3,
						this.height*2,
						null);
			}else if(actualDir==Direction.UP){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset())-32,
						(int)(y-game.$camera().$yOffset())-32,
						this.width*3,
						this.height*3,
						null);
			}else if(actualDir==Direction.RIGHT){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset())-32,
						(int)(y-game.$camera().$yOffset())-32,
						this.width*3,
						this.height*2,
						null);
			}else if(actualDir==Direction.DOWN){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset())-32,
						(int)(y-game.$camera().$yOffset())-32,
						this.width*3,
						this.height*3,
						null);
			}
		}else{
			if(actualDir==Direction.LEFT){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset()),
						(int)(y-game.$camera().$yOffset()),
						this.width*2,
						this.height,
						null);
			}else if(actualDir==Direction.UP){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset()),
						(int)(y-game.$camera().$yOffset()),
						this.width,
						this.height*2,
						null);
			}else if(actualDir==Direction.RIGHT){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset())-32,
						(int)(y-game.$camera().$yOffset()),
						this.width*2,
						this.height,
						null);
			}else if(actualDir==Direction.DOWN){
				g.drawImage(img, 
						(int)(x-game.$camera().$xOffset()),
						(int)(y-game.$camera().$yOffset())-32,
						this.width,
						this.height*2,
						null);
			}
		}
		//temp
		if(HPBarTimer>0){
			this.renderHP(g);
		}
		//this.renderDebug(g);
		//g.setFont(game.$defaultFont());
		//g.drawString(comboTimer+" "+attackSpeed+"",(int)(x-game.$camera().$xOffset()),(int)(y-game.$camera().$yOffset()));
	}
}