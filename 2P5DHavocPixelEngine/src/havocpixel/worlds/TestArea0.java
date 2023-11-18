package havocpixel.worlds;

import havocpixel.gfx.ImageLoader;
import havocpixel.main.Handler;

public class TestArea0 extends World{

	public TestArea0(Handler hdlr) {
		super(hdlr);
		loadLevel(ImageLoader.loadImage("/txr/warptest0.png"));
		worldLabel="Test Area 0:Forest";
		//renderFog=true;
		area.add(new LeaveArea(24,0,0,0,"1:1:24:47"));
		area.add(new LeaveArea(25,0,1,0,"1:1:25:47"));
		// TODO Auto-generated constructor stub
	}
	public void init(){
		surroundWithTrees();
		spawnCorpses();
		spawnTrees();
		//trapWorld();
	}

}
