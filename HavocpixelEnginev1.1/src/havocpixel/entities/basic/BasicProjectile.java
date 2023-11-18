package havocpixel.entities.basic;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Projectile;
import havocpixel.entities.Weapon;
import havocpixel.gfx.CoreAssets;

public class BasicProjectile extends Projectile{

	public BasicProjectile(Game game, double x, double y, 
			Direction direction,Entity owner) {
		super(game, x, y, direction, owner, 0.75, CoreAssets.knife);
		speed=600;
		weapon=Weapon.THROWING_KNIFE;
	}

}
