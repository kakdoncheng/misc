package havocpixel.worlds;

import havocpixel.gfx.ImageLoader;
import havocpixel.main.Handler;

public class TowerStairs extends TextureWorld{

	public TowerStairs(Handler hdlr) {
		super(hdlr);
		loadLevel(ImageLoader.loadImage("/txr/tower_stairs.png"));
		world=ImageLoader.loadImage("/txr/tower_stairs_texture.png");
		worldLabel="Stairs to the Tower of Lost Souls";
		// TODO Auto-generated constructor stub
	}
	public void init(){
		//em.addEntity(new Bunny(hdlr,47*Tile.TILE_WIDTH,46*Tile.TILE_HEIGHT));
		spawnTrees();
		spawnCorpses();
		//spawnSigns();
	}

}
