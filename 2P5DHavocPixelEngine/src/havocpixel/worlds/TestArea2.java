package havocpixel.worlds;

import havocpixel.gfx.ImageLoader;
import havocpixel.main.Handler;

public class TestArea2 extends World{
	public TestArea2(Handler hdlr) {
		super(hdlr);
		loadLevel(ImageLoader.loadImage("/txr/warptest1.png"));
		//renderFog=true;
		worldLabel="Test Area 2:Forest";
		area.add(new LeaveArea(24,49,0,0,"1:1:24:3"));
		area.add(new LeaveArea(25,49,1,0,"1:1:25:3"));
		// TODO Auto-generated constructor stub
	}
	public void init(){
		surroundWithTrees();
		spawnCorpses();
		trapWorld();
	}

}
