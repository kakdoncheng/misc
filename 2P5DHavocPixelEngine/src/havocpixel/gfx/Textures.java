package havocpixel.gfx;

import java.awt.image.BufferedImage;
public class Textures {
	private BufferedImage texture;
	public Textures(BufferedImage texture) {
		this.texture=texture;
	}
	public BufferedImage crop(){
		return texture;
	}
	public BufferedImage crop(int x, int y, int w, int h) {
		return texture.getSubimage(x, y, w, h);
	}
}
