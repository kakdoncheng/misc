package havocpixel.gfx;

import havocpixel.CoreLauncher;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	private BufferedImage texture;
	public Texture(BufferedImage img) {
		this.texture=img;
	}
	public BufferedImage crop(){
		return texture;
	}
	public BufferedImage crop(int x, int y, int w, int h) {
		return texture.getSubimage(x, y, w, h);
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	public static BufferedImage loadImage(String path) {
		try {
			BufferedImage image = ImageIO.read(CoreLauncher.class.getResource(path));
			return getAcceleratedBufferedImage(image);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static BufferedImage getAcceleratedBufferedImage(BufferedImage sourceImage){
		try {
			// attempt to crate hardware accelerated image
			// create an accelerated image of the right size to store image in
			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
			BufferedImage image = gc.createCompatibleImage(sourceImage.getWidth(),sourceImage.getHeight(),Transparency.BITMASK);
			image.getGraphics().drawImage(sourceImage,0,0,null);
			return image;
		} catch (Exception e) {
			e.printStackTrace();
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
