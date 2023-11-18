package fairytale.entities.projectiles;

import havocpixel.Game;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

public class Rocket extends ExplosiveFireball{

	public Rocket(Game game, double x, double y, Direction direction,
			Entity owner) {
		super(game, x, y, direction, owner);
		name="Rocket";
		img=CoreAssets.rocket;
		if(dir==Direction.RIGHT){
			this.img=Utils.rotate(img, 90);
		}else if(dir==Direction.DOWN){
			this.img=Utils.rotate(img, 180);
		}else if(dir==Direction.LEFT){
			this.img=Utils.rotate(img, 270);
		}
	}

}