package fairytale.entities.projectiles;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;

import java.awt.Rectangle;

public class RifleBlast extends Bullet{

	public RifleBlast(Game game, double x, double y, Direction direction,
			Entity owner) {
		super(game, x, y, direction, owner);
		bounds=new Rectangle(0,0);
		particle=true;
		game.$currentWorld().$entityManager().addEntity(new Bullet(game, x, y+2, dir, owner));
		name="Rifle Blast";
	}
	public void update(double dt) {
		active=false;
	}

}
