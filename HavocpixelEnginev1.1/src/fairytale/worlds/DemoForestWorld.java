package fairytale.worlds;

import havocpixel.Game;
import havocpixel.World;
import havocpixel.gfx.Texture;

public class DemoForestWorld extends World{

	public DemoForestWorld (Game game) {
		super(game);
		worldLabel="Demo World";
	}

	@Override
	public void load() {
		game.setLoadingText("Loading Demo World");
		System.out.print("havocpixel.DemoForestWorld:INFO: Loading.\n");
		//this.loadLevel(Texture.loadImage("/forest.png"));
		this.loadLevel(Texture.loadImage("/txr/test_forest.png"));
		//loadDefaultWorld();
		this.surroundWithTrees();
		//em.addEntity(new VolatileBarrel(game,600,600));
		//em.addEntity(new BasicEntity(game,300,100));
	}
}
