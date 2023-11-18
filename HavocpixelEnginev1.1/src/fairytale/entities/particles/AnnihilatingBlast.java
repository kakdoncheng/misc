package fairytale.entities.particles;

import fairytale.entities.projectiles.AnnihilatingSphere;
import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.ParticleEffect;
import havocpixel.entities.Weapon;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AnnihilatingBlast extends ParticleEffect{
	
	protected Entity owner;
	private int dw, tick;
	private double ddt;
	private Rectangle tail,head;
	
	public AnnihilatingBlast(Game game, double x, double y, Direction dir, Entity owner) {
		super(game, x, y);
		name="Annihilating Blast";
		this.dir=dir;
		this.owner=owner;
		this.strength=1;
		this.weapon=Weapon.THROWING_KNIFE;
		
		dw=16;
		ddt=0.15;
		tick=0;
		death=new Animation(CoreAssets.defaultTile,0.25,true);
		updateNewBounds(dw);
	}

	private void updateNewBounds(int offset){
		if(dir==Direction.RIGHT){
			bounds=new Rectangle(32,offset,320,32-(offset*2));
			head=new Rectangle(32+16,offset,320,32-(offset*2));
			tail=new Rectangle(32,offset,32,32-(offset*2));
		}else if(dir==Direction.DOWN){
			bounds=new Rectangle(offset,32,32-(offset*2),320);
			head=new Rectangle(offset,32+16,32-(offset*2),320);
			tail=new Rectangle(offset,32,32-(offset*2),32);
		}else if(dir==Direction.LEFT){
			bounds=new Rectangle(-320,offset,320,32-(offset*2));
			head=new Rectangle(-320,offset,320-16,32-(offset*2));
			tail=new Rectangle(-32,offset,32,32-(offset*2));
		}else{
			bounds=new Rectangle(offset,-320,32-(offset*2),320);
			head=new Rectangle(offset,-320,32-(offset*2),320-16);
			tail=new Rectangle(offset,-32,32-(offset*2),32);
		}
	}
	
	public void update(double dt){
		tick++;
		int ddw=(int)(game.$currentSecond()*10000)%2!=0?dw:dw-2;
		double mdt=(ddt-time)/ddt;
		updateNewBounds((int)(ddw*(mdt<0?0:mdt)));
		for(Entity e:game.$currentWorld().$entityManager().$entities()){
			if(e.isInvulnerable()||e.isParticle()||e.equals(owner))
				continue;
			if(e.$collisionBounds(0,0).intersects(this.$collisionBounds(0,0))){
				damage(e, owner);
			}
		}
		super.update(dt);
	}
	
	public void render(Graphics g) {
		Color c=g.getColor();
		g.setColor((tick/2)%2!=0?Color.BLACK:Color.WHITE);
		g.fillOval((int)(this.x+tail.x-game.$camera().$xOffset()),
				(int)(this.y+tail.y-game.$camera().$yOffset()),
				tail.width,
				tail.height);
		g.fillRect((int)(this.x+head.x-game.$camera().$xOffset()),
				(int)(this.y+head.y-game.$camera().$yOffset()),
				head.width,
				head.height);
		g.setColor(c);
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
		e.knockback(dir);
	}
	
	public void die(){
		game.$currentWorld().$entityManager().addEntity(new AnnihilatingSphere(game, x, y, dir, owner));
		super.die();
	}
}
