package fairytale.entities.objects;

import havocpixel.Game;
import havocpixel.entities.basic.Gib;
import havocpixel.gfx.CoreAssets;

import java.awt.image.BufferedImage;

public class ImpGib extends Gib{

	public ImpGib(Game game, double x, double y) {
		super(game, x, y);
	}
	
	protected BufferedImage getImg(){
		BufferedImage[] gib={
				CoreAssets.gib[0],CoreAssets.gib[1],CoreAssets.gib[2],CoreAssets.gib[3],CoreAssets.gib[4],
				CoreAssets.gib[5],CoreAssets.gib[6],CoreAssets.gib[7]
		};
		return gib[game.$randomInt(0,gib.length)];
	}

}
