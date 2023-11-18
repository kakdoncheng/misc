package havocpixel.gfx;

import java.awt.Color;
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
	public static void setRGBImg(BufferedImage img,int x,int y,int r,int g,int b,int a){
		int c=(a<<24)|(r<<16)|(g<<8)|b;
		img.setRGB(x,y,c);
	}
	public static void drawGradient(){
		BufferedImage u=new BufferedImage(26,14,6);
		for(int a=0;a<26;a++)
			System.out.print("|"+Integer.toHexString(a*10)+"|");
		System.out.println();
		for(int a=0;a<26;a++)
			System.out.print("|"+Integer.toHexString(a*10+5)+"|");
		System.out.println();
		for(int a=0;a<26;a++)
			setRGBImg(u,a,0,a*10,a*10,a*10,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,1,a*10+5,a*10+5,a*10+5,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,2,a*10,0,0,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,3,a*10+5,0,0,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,4,0,a*10,0,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,5,0,a*10+5,0,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,6,0,0,a*10,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,7,0,0,a*10+5,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,8,a*10,a*10,0,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,9,a*10+5,a*10+5,0,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,10,a*10,0,a*10,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,11,a*10+5,0,a*10+5,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,12,0,a*10,a*10,255);
		for(int a=0;a<26;a++)
			setRGBImg(u,a,13,0,a*10+5,a*10+5,255);
		try{
			ImageIO.write(u,"png",new File("gradient.png"));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public static Color hex2Rgb(String hexcode) {
	    return new Color(
	            Integer.valueOf(hexcode.substring(1,3),16),
	            Integer.valueOf(hexcode.substring(3,5),16),
	            Integer.valueOf(hexcode.substring(5,7),16));
	}
	public static void drawFullGradient(){
		BufferedImage u=new BufferedImage(4096,4096,6);
		//int i=0,x=0,y=0;
		//for(int r=0;r<256;r++){
		//	for(int g=0;g<256;g++){
		//		for(int b=0;b<256;b++){
		//			setRGBImg(u,x,y,r,g,b,255);
		//			x++;
		//			if(x>4095){
		//				x=0;
		//				y++;
		//			}
		//			i++;
		//		}
		//	}
		//}
		for(int y=0;y<4096;y++){
			for(int x=0;x<4096;x++){
				//u.setRGB(x,y,(255<<24)|(x*y));
			}
		}
		try{
			ImageIO.write(u,"png",new File("fullgradient.png"));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		//System.out.println(i);
	}
}
