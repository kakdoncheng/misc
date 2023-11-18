package havocpixel.entities;

import havocpixel.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ImmoveableObject extends Entity{

	public ImmoveableObject(Game game, double x, double y) {
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
		name="Immoveable Object";
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
		if(health<0){
			die();
		}
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
