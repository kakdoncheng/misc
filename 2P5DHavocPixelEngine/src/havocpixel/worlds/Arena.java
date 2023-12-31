package havocpixel.worlds;

import havocpixel.Timer;
import havocpixel.entities.creatures.LargeStatue;
import havocpixel.entities.creatures.Sans;
import havocpixel.entities.creatures.Tombstone;
import havocpixel.entities.creatures.Tree;
import havocpixel.gfx.ImageLoader;
import havocpixel.main.Handler;
import havocpixel.states.State;
import havocpixel.tiles.Tile;

public class Arena extends TextureWorld{
	public Arena(Handler hdlr) {
		super(hdlr);
		loadLevel(ImageLoader.loadImage("/txr/arena.png"));
		world=ImageLoader.loadImage("/txr/arena_texture.png");
		//if(u!=0)
		//	loadWorld("res/worlds/world0.txt");
		//em.$player().setX(spawnX+48);
		//em.$player().setY(spawnY+128);
		em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
		
		em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
		
		em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
		
		em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
		em.addEntity(new LargeStatue(hdlr,(this.width*Tile.TILE_WIDTH/2)-(2*Tile.TILE_WIDTH)+16,(this.height*Tile.TILE_HEIGHT/2)-(4*Tile.TILE_HEIGHT)));
		
		//renderFog=true;
		worldLabel="Arena";
	}
	public void init(){
		surroundWithTrees();
		System.out.print("["+Timer.time()+"] Trapping World;\n");
		trapWorld();
		spawnCorpses();
		System.out.print("["+Timer.time()+"] Spawning Pumpkins;\n");
		//spawnPumpkins();
		System.out.print("["+Timer.time()+"] Airdropping Loot Crates;\n");
		//spawnLootCrates();
		System.out.print("["+Timer.time()+"] Growing Trees;\n");
		spawnTrees();
		System.out.print("["+Timer.time()+"] Spawning Grass;\n");
		//spawnFlora();
		System.out.print("["+Timer.time()+"] Spawning Rocks;\n");
		//spawnRocks();
	}
}
