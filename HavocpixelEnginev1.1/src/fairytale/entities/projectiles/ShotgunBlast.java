package fairytale.entities.projectiles;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;

import java.awt.Rectangle;

public class ShotgunBlast extends Bullet{

	public ShotgunBlast(Game game, double x, double y, Direction direction,
			Entity owner) {
		super(game, x, y, direction, owner);
		bounds=new Rectangle(0,0);
		particle=true;
		name="Shotgun Blast";
		y+=2;
		//for(int i=0; i<7; i++){
		//	game.$currentWorld().$entityManager().addEntity(new ShotgunPellet(game, x, y+2, dir, owner));
		//}
		///*
		game.$currentWorld().$entityManager().addEntity(new ShotgunPellet(game, x-1, y, dir, owner));
		game.$currentWorld().$entityManager().addEntity(new ShotgunPellet(game, x-1, y-1, dir, owner));
		game.$currentWorld().$entityManager().addEntity(new ShotgunPellet(game, x, y-1, dir, owner));
		game.$currentWorld().$entityManager().addEntity(new ShotgunPellet(game, x, y, dir, owner));
		//game.$currentWorld().$entityManager().addEntity(new ShotgunPellet(game, x+1, y, dir, owner));
		//game.$currentWorld().$entityManager().addEntity(new ShotgunPellet(game, x+1, y+1, dir, owner));
		//game.$currentWorld().$entityManager().addEntity(new ShotgunPellet(game, x, y+1, dir, owner));
		//*/
	}
	public void update(double dt) {
		active=false;
	}

}
