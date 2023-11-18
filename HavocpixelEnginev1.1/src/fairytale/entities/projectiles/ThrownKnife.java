package fairytale.entities.projectiles;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Projectile;
import havocpixel.entities.Weapon;
import havocpixel.gfx.CoreAssets;

public class ThrownKnife extends Projectile{

	public ThrownKnife(Game game, double x, double y, 
				Direction direction,Entity owner) {
			super(game, x, y, direction, owner, 0.65, CoreAssets.knife);
			speed=350;
			strength=25;
			moveT=0.8;
			weapon=Weapon.THROWING_KNIFE;
			name="Thrown Knife";
	}

}
