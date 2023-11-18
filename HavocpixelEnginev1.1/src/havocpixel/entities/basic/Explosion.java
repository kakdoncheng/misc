package havocpixel.entities.basic;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Explosion extends Entity{
	
	private Entity owner;
	private Animation expl;
	private boolean spawnedFire;
	private boolean[] hit={
			false, false, true, true, true, 
			true, false, false, false, false
	};
	
	public Explosion(Game game, double x, double y, Entity owner) {
		super(game, x, y);
		maxHealth=1;
		health=1;
		width=64;
		height=64;
		bounds=new Rectangle(-40,-40,80,80);
		speed=0;
		name="Explosion";
		faction=Faction.UNALIGNED;
		weapon=Weapon.EXPLOSION;
		active=true;
		ghost=true;
		dead=true;
		dir=Direction.DOWN;
		this.owner=owner;
		expl=new Animation(CoreAssets.explosion, 0.75, true);
		spawnedFire=false;
	}
	
	public void update(double dt) {
		if(expl.$currentIndex()>4&&!spawnedFire){
			game.$currentWorld().$entityManager().addEntity(new Fire(game,this.x-16,this.y,this.owner));
			spawnedFire=true;
		}
		if(!expl.isLastFrame()){
			expl.update(dt);
		}else{
			active=false;
			return;
		}
		if(hit[expl.$currentIndex()]){
			for(Entity e:game.$currentWorld().$entityManager().$entities()){
				if(!canCollideWith(e))
					continue;
				if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))){
					damage(e);
				}
			}
			hit[expl.$currentIndex()]=false;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(expl.$currentFrame(), 
				(int)(x-game.$camera().$xOffset())-32,
				(int)(y-game.$camera().$yOffset())-32,
				this.width,
				this.height,
				null);
	}

	public void damage(Entity e){
		double effectiveArmor = e.$armor().$AC() * ((1000.0 - weapon.$AP()) / 1000.0);
		double damageMod = ((1000.0 - effectiveArmor) / 1000.0);
		if (weapon.$AP() < 0 && e.$armor().$AC() <= 0)
			damageMod = ((1000.0 + weapon.$AP()) / 1000.0);
		else if (weapon.$AP() > 0 && e.$armor().$AC() <= 0)
			damageMod = ((1000.0 - weapon.$AP()) / 1000.0);
		double damage = (strength + (weapon.$maxDamage() * 0.5) + (weapon.$maxDamage() * game.$randomDouble(0, 0.5))) * damageMod;
		if(owner.equals(e)){
			System.out.println(owner+" hit themselves for "+(int)damage+" points of damage.");
		}else{
			System.out.println(owner+" hit "+e+" for "+(int)damage+" points of damage.");
		}
		e.makeSwornTarget(owner);
		e.reduceHealth((int)damage);
		e.knockback(this.$actualDirection(this.$directionTowards(e)),6);
	}
	
	public void die() {
		active=false;
	}

}
