package fairytale.entities.projectiles;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Weapon;

public class ShotgunPellet extends Bullet{

	public ShotgunPellet(Game game, double x, double y, Direction direction,
			Entity owner) {
		super(game, x, y, direction, owner);
		weapon=Weapon.BUCKSHOT;
		name="Buckshot";
		speed=900;
		strength=50;
		accuracy=0.75;
		if(accuracy>1)
			accuracy=1;
		if(accuracy<0)
			accuracy=0;
		double error=1.0-accuracy;
		xError=game.$randomDouble(-error, error);
		yError=game.$randomDouble(-error, error);
		moveT=0.25;
	}

}
