package fairytale.entities.projectiles;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;

import java.awt.Rectangle;

public class BurstRifleBlast extends Bullet{
	
	private int burst;
	private double timer, thres;
	public BurstRifleBlast(Game game, double x, double y, Direction direction,
			Entity owner) {
		super(game, x, y, direction, owner);
		name="Burst Rifle Blast";
		bounds=new Rectangle(0,0);
		particle=true;
		burst=3;
		thres=0.1;
		timer=thres;
	}
	public void update(double dt) {
		timer+=dt;
		if(burst>1){
			if(timer>=thres){
				burst--;
				timer=0;
				game.$currentWorld().$entityManager().addEntity(new Bullet(game, x, y+2, dir, owner));
			}
			return;
		}
		active=false;
	}

}
