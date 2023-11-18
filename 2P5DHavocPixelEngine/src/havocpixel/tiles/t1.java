package havocpixel.tiles;

import havocpixel.gfx.Assets;

public class t1 extends Tile{
	public t1(int ID) {
		super(Assets.stone, ID);
	}
	public boolean impassable() {
		return true;
	}

}