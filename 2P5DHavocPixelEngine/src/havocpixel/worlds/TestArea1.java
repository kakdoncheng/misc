package havocpixel.worlds;

import havocpixel.gfx.ImageLoader;
import havocpixel.main.Handler;

public class TestArea1 extends World{
	public TestArea1(Handler hdlr) {
		super(hdlr);
		loadLevel(ImageLoader.loadImage("/txr/testbridge1.png"));
		worldLabel="Test Area 1:Bridge";
		area.add(new LeaveArea(23,49,0,0,"0:1:24:3"));
		area.add(new LeaveArea(24,49,1,0,"0:1:25:3"));
		area.add(new LeaveArea(25,49,2,0,"0:1:24:3"));
		area.add(new LeaveArea(26,49,3,0,"0:1:25:3"));
		area.add(new LeaveArea(23,0,4,0,"2:1:24:47"));
		area.add(new LeaveArea(24,0,5,0,"2:1:25:47"));
		area.add(new LeaveArea(25,0,6,0,"2:1:24:47"));
		area.add(new LeaveArea(26,0,7,0,"2:1:25:47"));
		// TODO Auto-generated constructor stub
	}
	public void init(){
		//surroundWithTrees();
	}

}
