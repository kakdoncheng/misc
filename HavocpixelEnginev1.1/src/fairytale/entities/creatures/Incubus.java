package fairytale.entities.creatures;

import fairytale.entities.objects.IncubusCorpse;
import fairytale.entities.projectiles.ExplosiveFireball;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Direction;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicFloatingEntity;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Graphics;

public class Incubus extends BasicFloatingEntity{
	
	protected boolean cloak;
	protected int fireballs;

	public Incubus(Game game, int x, int y) {
		super(game, x, y);
		name="Incubus";
		corpse=new IncubusCorpse(game,0,0);
		armor=Armor.DEMON_SKIN;
		weapon=Weapon.DEMON_CLAWS;
		faction=Faction.LESSER_DEMON;
		cloak=true;
		fireballs=3;
	}
	
	public void update(double dt){
		if(isKnockedBack()||isAttacking()){
			cloak=false;
		}
		super.update(dt);
	}
	
	public void render(Graphics g) {
		updateCurrentImg();
		if(cloak){
			Utils.drawTranslucentImage(img, 
					(int)(x-game.$camera().$xOffset()),
					(int)(y-game.$camera().$yOffset()+dy),(float)0.2,
					this.width,
					this.height,
					g);
			return;
		}
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
				img=CoreAssets.incubus[5];
			}else if(actualDir==Direction.UP){
				img=CoreAssets.incubus[6];
			}else if(actualDir==Direction.RIGHT){
				img=CoreAssets.incubus[7];
			}else if(actualDir==Direction.DOWN){
				img=CoreAssets.incubus[4];
			}
		}else{
			if(actualDir==Direction.LEFT){
				img=CoreAssets.incubus[1];
			}else if(actualDir==Direction.UP){
				img=CoreAssets.incubus[2];
			}else if(actualDir==Direction.RIGHT){
				img=CoreAssets.incubus[3];
			}else if(actualDir==Direction.DOWN){
				img=CoreAssets.incubus[0];
			}
		}
	}
	
	public void attackWithProjectile(){
		if(fireballs>0){
			fireballs--;
			game.$currentWorld().$entityManager().addEntity(new ExplosiveFireball(game, x, y, dir, this));
			if(fireballs<1){
				this.canAttackWithProjectile=false;
			}
		}
	}

}
