package havocpixel.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.Game;

public class MoveableObject extends Entity{

	public MoveableObject(Game game, double x, double y) {
		super(game, x, y);
		maxHealth=100;
		width=32;
		height=32;
		health=maxHealth;
		speed=60;
		strength=0;
		bounds=new Rectangle(8,10,15,21);
		armor=Armor.SKELETON;
		weapon=Weapon.UNARMED;
		faction=Faction.POSSESSED;
		dir=Direction.DOWN;
		kbdir=Direction.DOWN;
		name="Moveable Object";
		swornTarget=null;
		currentTarget=null;
		active=true;
		dead=false;
		particle=false;
		object=true;
		projectile=false;
		item=false;
		fleshy=false;
		ghost=false;
		invulnerable=true;
		aggressive=false;
		spawning=false;
		canAttackWithProjectile=false;
		stopActions();
		stopEffects();
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		if(isKnockedBack()){
			getKnockedBack(dt);
			return;
		}
		if(health<0){
			die();
		}
		double dx=0, dy=0;
		if(entityCollision(1,0)){
			dx=-speed*dt;
		}
		if(entityCollision(-1,0)){
			dx=speed*dt;
		}
		if(entityCollision(0,1)){
			dy=-speed*dt;
		}
		if(entityCollision(0,-1)){
			dy=speed*dt;
		}
		if(dx!=0||dy!=0)
			move(dx,dy);
		dx=0;
		dy=0;
	}

	@Override
	public void render(Graphics g) {
		this.renderShadow(g);
		g.setColor(Color.GRAY);
		g.fillRect((int)($collisionBounds(0,0).x-game.$camera().$xOffset()), 
				(int)($collisionBounds(0,0).y-game.$camera().$yOffset()), 
				bounds.width, bounds.height);
		g.setColor(Color.BLACK);
		g.drawRect((int)($collisionBounds(0,0).x-game.$camera().$xOffset()), 
				(int)($collisionBounds(0,0).y-game.$camera().$yOffset()), 
				bounds.width, bounds.height);
	}

	@Override
	public void die() {
		active=false;
	}

}
