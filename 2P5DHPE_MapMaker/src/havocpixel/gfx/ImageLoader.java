package havocpixel.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	public static BufferedImage loadImageFromFile(String path) {
		File input;
		try {
			input=new File(path);
			return ImageIO.read(input);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
