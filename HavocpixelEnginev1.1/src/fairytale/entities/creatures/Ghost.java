package fairytale.entities.creatures;

import fairytale.entities.objects.GibbedCorpse;
import fairytale.entities.particles.WispEffect;
import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Direction;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.entities.basic.BasicFloatingEntity;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Graphics;

public class Ghost extends BasicFloatingEntity{
	
	private boolean fading;
	private double a;
	
	public Ghost(Game game, int x, int y) {
		super(game, x, y);
		name="Ghost";
		corpse=new GibbedCorpse(game,0,0);
		maxHealth=1;
		health=maxHealth;
		armor=Armor.ROTTING_FLESH;
		weapon=Weapon.DEMON_CLAWS;
		faction=Faction.POSSESSED;
		this.attackWithProjectile=false;
		this.spawning=false;
		this.fleshy=false;
		fading=true;
		a=0.5;
	}
	
	public void update(double dt){
		if(fading){
			a-=dt;
			if(a<0){
				a=0;
				fading=false;
			}
		}
		super.update(dt);
	}
	
	public void render(Graphics g) {
		updateCurrentImg();
		Utils.drawTranslucentImage(img, 
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()+dy),(float)(0.5-a),
				this.width,
				this.height,
				g);
		return;
	}
	
	public void updateCurrentImg(){
		Direction actualDir=$actualDirection(dir);
		if(isAttacking()){
			if(actualDir==Direction.RIGHT){
				img=CoreAssets.ghost[5];
			}else if(actualDir==Direction.UP){
				img=CoreAssets.ghost[6];
			}else if(actualDir==Direction.LEFT){
				img=CoreAssets.ghost[7];
			}else if(actualDir==Direction.DOWN){
				img=CoreAssets.ghost[4];
			}
		}else{
			if(actualDir==Direction.RIGHT){
				img=CoreAssets.ghost[1];
			}else if(actualDir==Direction.UP){
				img=CoreAssets.ghost[2];
			}else if(actualDir==Direction.LEFT){
				img=CoreAssets.ghost[3];
			}else if(actualDir==Direction.DOWN){
				img=CoreAssets.ghost[0];
			}
		}
	}
	
	public void attackWithProjectile(){
		
	}
	
	public void die(){
		active=false;
		game.$currentWorld().$entityManager().addEntity(new WispEffect(game,x,y));
	}
}
