package fairytale.entities.creatures;

import fairytale.entities.projectiles.ThrownKnife;
import havocpixel.Game;
import havocpixel.entities.basic.BasicEntity;

public class HoodedSkeleton extends BasicEntity{

	public HoodedSkeleton(Game game, int x, int y) {
		super(game, x, y);
		this.name="Hooded Skeleton";
		speed=60;
	}
	public void attackWithProjectile(){
		game.$currentWorld().$entityManager().addEntity(new ThrownKnife(game, x, y, dir, this));
	}

}
