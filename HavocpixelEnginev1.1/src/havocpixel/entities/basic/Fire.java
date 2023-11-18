package havocpixel.entities.basic;

import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

public class Fire extends Entity{
	private Entity owner;
	private Animation fire;
	private double duration;
	private double smokeTick, tick;
	
	public Fire(Game game, double x, double y, Entity owner) {
		super(game, x, y);
		maxHealth=1;
		health=1;
		bounds=new Rectangle(4,16,15,24);
		speed=0;
		name="Fire";
		faction=Faction.UNALIGNED;
		weapon=Weapon.FIREBALL;
		active=true;
		dead=true;
		ghost=true;
		dir=Direction.DOWN;
		this.owner=owner;
		fire=new Animation(CoreAssets.fire, 0.75, false);
		duration=5.0;
		smokeTick=1.0/2;
		tick=0;
	}
	
	public void update(double dt) {
		duration-=dt;
		if(duration<0){
			active=false;
			return;
		}
		fire.update(dt);
		tick+=dt;
		if(tick>=smokeTick){
			tick-=smokeTick;
			game.$currentWorld().$entityManager().addEntity(new BigSmokeParticle(game,x,y+8));
		}
		/*
		if(hit[fire.$currentIndex()]){
			for(Entity e:game.$currentWorld().$entityManager().$entities()){
				if(!canCollideWith(e))
					continue;
				if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))){
					damage(e);
				}
			}
			hit[fire.$currentIndex()]=false;
		}
		*/
	}
	
	public void render(Graphics g) {
		if(duration>1){
			g.drawImage(fire.$currentFrame(), 
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()),
				this.width,
				this.height,
				null);
		}else{
			Utils.drawTranslucentImage(fire.$currentFrame(),
					(int)(x-game.$camera().$xOffset()),
					(int)(y-game.$camera().$yOffset()),
					(float)duration,
					this.width,
					this.height,
					g);
		}
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
		//e.knockback(this.$actualDirection(this.$directionTowards(e)),6);
	}
	
	public void die() {
		active=false;
	}
}
