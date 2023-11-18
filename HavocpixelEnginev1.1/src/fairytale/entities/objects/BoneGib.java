package fairytale.entities.objects;

import java.awt.image.BufferedImage;

import havocpixel.Game;
import havocpixel.entities.basic.Gib;
import havocpixel.gfx.CoreAssets;

public class BoneGib extends Gib{

	public BoneGib(Game game, double x, double y) {
		super(game, x, y);
		// TODO Auto-generated constructor stub
	}
	protected BufferedImage getImg(){
		BufferedImage[] gib={
				CoreAssets.gib[8],
				CoreAssets.gib[10],CoreAssets.gib[11]
		};
		return gib[game.$randomInt(0,gib.length)];
	}

}
