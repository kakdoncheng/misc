package havocpixel.tiles;

import java.awt.image.BufferedImage;

public class FormlessTile extends Tile{

	private boolean isSolid;
	public FormlessTile(BufferedImage texture,int ID,boolean solid,String hex,String label) {
		super(texture, ID);
		hexcode=hex;
		tlabel=label;
		isSolid=solid;
		// TODO Auto-generated constructor stub
	}
	public boolean impassable() {
		return isSolid;
	}

}
