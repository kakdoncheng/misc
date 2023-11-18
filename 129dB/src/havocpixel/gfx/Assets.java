package havocpixel.gfx;

import havocpixel.Launcher;
import havocpixel.Timer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Assets {
	public static final int px=32;
	public static BufferedImage def,grass,moss,wall,stone,brick,nul,cobble,hurt,hurt2;
	public static BufferedImage testLevel;
	public static BufferedImage[][] textures;
	
	public static BufferedImage block0, hurt0,win0,win1,pin0,pin1;
	public static BufferedImage[] move0, idle0, punch0,death0;
	
	public static BufferedImage block1, hurt1;
	public static BufferedImage[] move1, idle1, punch1,death1, background;
	
	public static BufferedImage verticalflip(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(w, h, img.getColorModel()
		.getTransparency());
		Graphics2D g = dimg.createGraphics();
		g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
		g.dispose();
		return dimg;
		}
	public static void registerFont(String path){
		InputStream is = Launcher.class.getResourceAsStream(path);
		Font uniFont = null;
		try {
			uniFont = Font.createFont(Font.TRUETYPE_FONT,is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		Font f = uniFont.deriveFont(24f);
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(f);
	}
	public static void load() {
		System.out.print("["+Timer.time()+"] Registering Fonts;\n");
		String fonts[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		//for(int i=0;i<fonts.length;i++)System.out.println(fonts[i]);
		boolean exist=false;
		for(String u:fonts)
			if(u.equals("ReservoirGrunge"))
				exist=true;
		if(!exist){
			registerFont("/RESEGRG_.TTF");
		}
		registerFont("/abh.ttf");
		registerFont("/beeskeps.otf");
		registerFont("/Daedra.otf");
		registerFont("/kadosh_samaritan_1_10.ttf");
		registerFont("/roswreck.ttf");
		registerFont("/sf_nandr.ttf");
		registerFont("/Zdyk_Sagittarius.otf");
		System.out.print("["+Timer.time()+"] Loading Textures;\n");
		//Textures def0=new Textures(ImageLoader.loadImage("/txr/default.png"));
		Textures pat=new Textures(ImageLoader.loadImage("/txr/patrick0.png"));
		Textures death=new Textures(ImageLoader.loadImage("/txr/death.png"));
		
		System.out.print("["+Timer.time()+"] Loading Animations;\n");
		
		//patrick w175 h190
		hurt0=pat.crop(175*3,190*0,175,190);
		block0=pat.crop(175*3,190*1,175,190);
		win0=pat.crop(175*3,190*2,175,190);
		pin0=pat.crop(175*3,190*3,175,190);
		move0=new BufferedImage[6];
		idle0=new BufferedImage[4];
		punch0=new BufferedImage[6];
		for(int i=0;i<6;i++)
			punch0[i]=pat.crop(175*0,190*(1+i),175*2,190);
		for(int i=0;i<3;i++)
			idle0[i]=pat.crop(175*i,190*0,175,190);
		idle0[3]=pat.crop(175*1,190*0,175,190);
		for(int i=0;i<4;i++)
			move0[i]=pat.crop(175*2,190*(1+i),175,190);
		move0[4]=pat.crop(175*2,190*(3),175,190);
		move0[5]=pat.crop(175*2,190*(2),175,190);
		
		hurt1=verticalflip(hurt0);
		block1=verticalflip(block0);
		win1=verticalflip(win0);
		pin1=verticalflip(pin0);
		move1=new BufferedImage[6];
		idle1=new BufferedImage[4];
		punch1=new BufferedImage[6];
		for(int i=0;i<6;i++)
			punch1[i]=verticalflip(punch0[i]);
		for(int i=0;i<4;i++)
			idle1[i]=verticalflip(idle0[i]);
		for(int i=0;i<6;i++)
			move1[i]=verticalflip(move0[i]);
		
		death0=new BufferedImage[6];
		death1=new BufferedImage[6];
		for(int i=0;i<6;i++)
			death0[i]=death.crop(175*i,190*0,175,190);
		for(int i=0;i<6;i++)
			death1[i]=verticalflip(death0[i]);
		
		background=new BufferedImage[21];
		for(int i=0;i<21;i++){
			//background[i]=new Textures(ImageLoader.loadImage("/txr/frame_"+(i+8)+".png")).crop();
		}
		
		/*
		rage=rage0.crop(0,0,750,460);
		hurt=hurt0.crop(0,0,750,460);
		hurt1=hurt01.crop(0,0,750,460);
		hurt2=hurt02.crop(0,0,750,460);
		control=controls.crop(0,0,750,460);
		fog0=fog.crop(0,0,512,512);
		testLevel=lvl0.crop();
		
		//soot
		soot=new BufferedImage[2];
		for(int i=0;i<2;i++)
			soot[i]=tomb.crop(px*(3+(i*2)), px*16, px*2, px);
		//blood
		blood=new BufferedImage[32];
		for(int i=0;i<8;i++)
			blood[i]=b.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			blood[i+8]=b.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			blood[i+16]=b.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			blood[i+8+16]=b.crop(px*i, px*3, px, px);
		
		//coins
		coin=new BufferedImage[5];
		for(int i=0;i<5;i++)
			coin[i]=coi.crop(px*i, px*0, px, px);
		sign=new BufferedImage[3];
		for(int i=0;i<3;i++)
			sign[i]=coi.crop(px*(i+5), px*0, px, px);
		*/
	}
}
