package fairytale.entities.objects;

import java.awt.image.BufferedImage;

import havocpixel.Game;
import havocpixel.entities.basic.Gib;
import havocpixel.gfx.CoreAssets;

public class BunnyHeadGib extends Gib{

	public BunnyHeadGib(Game game, double x, double y) {
		super(game, x, y);
		t=200;
	}
	protected BufferedImage getImg(){
		return CoreAssets.bGib;
	}
}
