package havocpixel.worlds;

import havocpixel.Timer;
import havocpixel.entities.creatures.Bunny;
import havocpixel.entities.creatures.Sans;
import havocpixel.gfx.ImageLoader;
import havocpixel.main.Handler;
import havocpixel.states.State;
import havocpixel.tiles.Tile;

public class Aokigahara extends World{

	public Aokigahara(Handler hdlr) {
		super(hdlr);
		// TODO Auto-generated constructor stub
		//renderFog=true;
		loadLevel(ImageLoader.loadImage("/txr/test1.png"));
		//if(u!=0)
		//	loadWorld("res/worlds/world0.txt");
		worldLabel="Aokigahara";
		//em.addEntity(new Sans(hdlr,48*Tile.TILE_WIDTH,46*Tile.TILE_HEIGHT));
		//em.$player().setX((47*Tile.TILE_WIDTH));//+16);//+(2*Tile.TILE_WIDTH)+0);
		//em.$player().setY((48*Tile.TILE_HEIGHT));//)+(4*Tile.TILE_HEIGHT));
	}
	public void init(){
		//em.addEntity(new Bunny(hdlr,47*Tile.TILE_WIDTH,46*Tile.TILE_HEIGHT));
		surroundWithTrees();
		System.out.print("["+Timer.time()+"] Trapping World;\n");
		trapWorld();
		System.out.print("["+Timer.time()+"] Spawning Pumpkins;\n");
		//spawnPumpkins();
		System.out.print("["+Timer.time()+"] Airdropping Loot Crates;\n");
		//spawnLootCrates();
		System.out.print("["+Timer.time()+"] Growing Trees;\n");
		spawnTrees();
		System.out.print("["+Timer.time()+"] Slaughtering Humans;\n");
		spawnCorpses();
		//$spawnCorpsePoles();unused
		System.out.print("["+Timer.time()+"] Spawning Grass;\n");
		//spawnFlora();
		//spawnMassFlora();
		System.out.print("["+Timer.time()+"] Spawning Rocks;\n");
		//spawnRocks();
		//spawnSigns();
	}

}
