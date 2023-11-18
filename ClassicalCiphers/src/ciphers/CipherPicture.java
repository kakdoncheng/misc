package ciphers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CipherPicture {
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
	public static void hide(BufferedImage msg, BufferedImage target){
		for(int y=0;y<msg.getHeight();y++){
			for(int x=0;x<msg.getWidth();x++){
				int n=msg.getRGB(x,y);
				int m=target.getRGB(x,y);
				int b=(((m&0xff)>>2)<<2)+(((n&0xff)*4)/256);
				int g=((((m&0xff00)>>8)>>2)<<2)+((((n&0xff00)>>8)*4)/256);
				int r=((((m&0xff0000)>>16)>>2)<<2)+((((n&0xff0000)>>16)*4)/256);
				setRGBImg(target,x,y,r,g,b,255);
			}
		}
		try{
			ImageIO.write(target,"png",new File("C:\\Users\\Ben\\Desktop\\output\\result_enc.png"));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public static void recover(BufferedImage target){
		for(int y=0;y<target.getHeight();y++){
			for(int x=0;x<target.getWidth();x++){
				int n=target.getRGB(x,y);
				int b=(n&0xff)%4;
				int g=((n&0xff00)>>8)%4;
				int r=((n&0xff0000)>>16)%4;
				setRGBImg(target,x,y,(r*256)/4,(g*256)/4,(b*256)/4,255);
			}
		}
		try{
			ImageIO.write(target,"png",new File("C:\\Users\\Ben\\Desktop\\output\\result_dec.png"));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public static void hide3(BufferedImage msg, BufferedImage target){
		int dx=(target.getWidth()-msg.getWidth())/2;
		int dy=(target.getHeight()-msg.getHeight())/2;
		dx=0;dy=0;
		for(int y=0;y<msg.getHeight();y++){
			for(int x=0;x<msg.getWidth();x++){
				int n=msg.getRGB(x,y);
				int m=target.getRGB(x+dx,y+dy);
				int b=(((m&0xff)>>3)<<3)+(((n&0xff)*8)/256);
				int g=((((m&0xff00)>>8)>>3)<<3)+((((n&0xff00)>>8)*8)/256);
				int r=((((m&0xff0000)>>16)>>3)<<3)+((((n&0xff0000)>>16)*8)/256);
				setRGBImg(target,x+dx,y+dy,r,g,b,255);
			}
		}
		try{
			ImageIO.write(target,"png",new File("C:\\Users\\Ben\\Desktop\\output\\result_enc3.png"));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public static void recover3(BufferedImage target){
		for(int y=0;y<target.getHeight();y++){
			for(int x=0;x<target.getWidth();x++){
				int n=target.getRGB(x,y);
				int b=(n&0xff)%8;
				int g=((n&0xff00)>>8)%8;
				int r=((n&0xff0000)>>16)%8;
				setRGBImg(target,x,y,(r*256)/8,(g*256)/8,(b*256)/8,255);
			}
		}
		try{
			ImageIO.write(target,"png",new File("C:\\Users\\Ben\\Desktop\\output\\result_dec3.png"));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public static void makeNoise(int width, int height){
		BufferedImage target=new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
		for(int y=0;y<target.getHeight();y++){
			for(int x=0;x<target.getWidth();x++){
				//int c=(Math.random()<0.5)?0:255;
				int r=(int)(Math.random()*256);
				int g=(int)(Math.random()*256);
				int b=(int)(Math.random()*256);
				//r=c;g=c;b=c;
				setRGBImg(target,x,y,r,g,b,255);
			}
		}
		try{
			ImageIO.write(target,"png",new File("C:\\Users\\Ben\\Desktop\\noise.png"));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public static void main(String[] args){
		//hide(loadImageFromFile("C:\\Users\\Ben\\Desktop\\trashpics\\d.jpg"),loadImageFromFile("C:\\Users\\Ben\\Desktop\\trashpics\\p.jpg"));
		//recover(loadImageFromFile("C:\\Users\\Ben\\Desktop\\trashpics\\result_enc.png"));
		//hide3(loadImageFromFile("C:\\Users\\Ben\\Desktop\\59859145_2083534655280529_6144587574678650880_o.jpg"),loadImageFromFile("C:\\Users\\Ben\\Desktop\\noise.png"));
		recover(loadImageFromFile("C:\\Users\\Ben\\Desktop\\output\\launch_enigma.png"));
		//makeNoise(2048,1338);
		//makeNoise(256,256);
	}

}
