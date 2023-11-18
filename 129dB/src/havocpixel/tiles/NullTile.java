package havocpixel.tiles;

import havocpixel.gfx.Assets;

public class NullTile extends Tile{

	public NullTile(int ID) {
		super(Assets.nul, ID);
		// TODO Auto-generated constructor stub
	}

	public boolean impassable() {
		return true;
	}
}
