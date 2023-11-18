package fairytale.entities.objects;

import havocpixel.Game;
import havocpixel.entities.basic.Gib;
import havocpixel.gfx.CoreAssets;

import java.awt.image.BufferedImage;

public class HelmetGib extends Gib{

	public HelmetGib(Game game, double x, double y) {
		super(game, x, y);
		t=200;
	}
	protected BufferedImage getImg(){
		return CoreAssets.helmGib;
	}
}
