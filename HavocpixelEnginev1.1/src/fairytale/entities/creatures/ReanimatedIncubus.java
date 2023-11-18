package fairytale.entities.creatures;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.basic.Explosion;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class ReanimatedIncubus extends Incubus{

	public ReanimatedIncubus(Game game, int x, int y) {
		super(game, x, y);
		spawn=new Animation(CoreAssets.rincubusSpawn,0.75,true);
		speed=60;
		this.cloak=false;
		this.canAttackWithProjectile=false;
		spawning=true;
		name="Reanimated Incubus";
		maxHealth=1000;
		health=maxHealth;
		// TODO Auto-generated constructor stub
	}
	
	public void update(double dt){
		if(spawning){
			if(spawn.isLastFrame()){
				spawning=false;
			}
			spawn.update(dt);
			return;
		}
		super.update(dt);
	}
	
	public void updateCurrentImg(){
		if(spawning){
			img=spawn.$currentFrame();
			return;
		}
		Direction actualDir=$actualDirection(dir);
		if(isAttacking()){
			if(actualDir==Direction.LEFT){
				img=CoreAssets.rincubus[5];
			}else if(actualDir==Direction.UP){
				img=CoreAssets.rincubus[6];
			}else if(actualDir==Direction.RIGHT){
				img=CoreAssets.rincubus[7];
			}else if(actualDir==Direction.DOWN){
				img=CoreAssets.rincubus[4];
			}
		}else{
			if(actualDir==Direction.LEFT){
				img=CoreAssets.rincubus[1];
			}else if(actualDir==Direction.UP){
				img=CoreAssets.rincubus[2];
			}else if(actualDir==Direction.RIGHT){
				img=CoreAssets.rincubus[3];
			}else if(actualDir==Direction.DOWN){
				img=CoreAssets.rincubus[0];
			}
		}
	}
	
	public void attack(){
		die();
	}
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new Explosion(game, x+16, y+16, this));
	}

}
