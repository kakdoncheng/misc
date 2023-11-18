package fairytale.worlds;

import fairytale.entities.objects.VolatileBarrel;
import havocpixel.Game;
import havocpixel.World;
import havocpixel.gfx.Texture;

public class Arena extends World{

	public Arena(Game game) {
		super(game);
		worldLabel="Arena";
	}

	@Override
	public void load() {
		game.setLoadingText("Loading Arena");
		System.out.print("havocpixel.Arena:INFO: Loading.\n");
		this.loadLevel(Texture.loadImage("/txr/arena.png"));
		//loadDefaultWorld();
		for(int i=0;i<4;i++){
			this.spawnEntityInWorld(new VolatileBarrel(game,0,0));
		}
		//this.surroundWithTrees();
		//em.addEntity(new VolatileBarrel(game,600,600));
		//em.addEntity(new BasicEntity(game,300,100));
	}

}
