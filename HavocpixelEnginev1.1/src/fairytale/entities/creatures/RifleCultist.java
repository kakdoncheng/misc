package fairytale.entities.creatures;

import fairytale.entities.projectiles.RifleBlast;
import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class RifleCultist extends ShotgunCultist{

	private int burst;
	public RifleCultist(Game game, int x, int y) {
		super(game, x, y);
		name="Rifle Cultist";
		burst=2;
		spawn=new Animation(CoreAssets.rcEmerge,0.75,true);
		death=new Animation(CoreAssets.rcDeath,0.75,true);
		up=new Animation(CoreAssets.rcUp,1.0);
		down=new Animation(CoreAssets.rcDown,1.0);
		right=new Animation(CoreAssets.rcRight,1.5);
		left=new Animation(CoreAssets.rcLeft,1.5);
		aUp=new Animation(CoreAssets.rAcUp,0.4);
		aDown=new Animation(CoreAssets.rAcDown,0.4);
		aRight=new Animation(CoreAssets.rAcRight,0.4);
		aLeft=new Animation(CoreAssets.rAcLeft,0.4);
		iUp=new Animation(CoreAssets.rciU,1);
		iDown=new Animation(CoreAssets.rciD,1);
		iRight=new Animation(CoreAssets.rciR,1);
		iLeft=new Animation(CoreAssets.rciL,1);
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
			if(burst>0){
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
	
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new RifleBlast(game, x, y, dir, this));
	}

}
