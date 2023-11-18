package havocpixel.tiles;

import havocpixel.gfx.Assets;

public class def0 extends Tile{
	public def0(int ID) {
		super(Assets.def, ID);
	}
	public boolean impassable() {
		return false;
	}

}
