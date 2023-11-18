package havocpixel.main;

import havocpixel.Timer;
import havocpixel.gfx.Assets;
import havocpixel.gfx.Camera;
import havocpixel.gfx.ImageLoader;
import havocpixel.gui.Display;
import havocpixel.input.KeyManager;
import havocpixel.input.MouseManager;
import havocpixel.tiles.Tile;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game implements Runnable{
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	public Handler hdlr;
	private KeyManager km;
	private MouseManager mm;
	public KeyManager $keyManager() {
		return km;
	}
	public MouseManager $mouseManager() {
		return mm;
	}
	private Camera camera;
	public Camera $camera() {
		return camera;
	}
	
	private Thread thread;
	
	private boolean running=false;
	private int width, height,width0, height0;
	private String title;
	public String version="d50w631f3458a";
	public String define="[2P5DHavocPixel Engine][32bit]["+version+"]";
	public int FPSLimit=60;
	private int scale=1;
	private int tick=0;
	private Font f;
	protected int[][] pos;
	
	private BufferedImage world;//=ImageLoader.loadImageFromFile(Utils.jarPath+"INPUT.png");
	private BufferedImage edit;
	
	public Font $defaultFont(){
		return f;
	}
	public String $defaultFontName(){
		return f.getFontName();
	}
	public int $width() {
		return this.width;
	}
	public int $height() {
		return this.height;
	}
	public int $renderScale(){
		return scale;
	}
	public void toggleScale(){
		if(scale==1){
			scale=2;
		}else
			scale=1;
	}
	public Game(String title, int width, int height) {
		if (title!=null) {
			this.title=title;
		} else {
			this.title=this.define;
		}
		this.width=width;
		this.height=height;
		hdlr=new Handler(this);
		km=new KeyManager();
		mm=new MouseManager();
	}
	
	private void init() {
		Utils.init();
		Assets.load();
		System.out.println(Utils.jarPath+"\\INPUT.png");
		world=ImageLoader.loadImageFromFile(Utils.rawPath(Utils.jarPath)+"INPUT.png");
		edit=ImageLoader.loadImageFromFile(Utils.rawPath(Utils.jarPath)+"INPUT.png");
		loadLevel(world);
		camera=new Camera(hdlr, 0, 0,scale);
		display=new Display(this.width*scale,this.height*scale,this.title);
		display.$frame().addKeyListener(km);
		display.$frame().addMouseListener(mm);
		display.$frame().addMouseMotionListener(mm);
		display.$canvas().addMouseListener(mm);
		display.$canvas().addMouseMotionListener(mm);
		//State.setState(sm.$controls());
	}
	int cool=0,curTile=0;
	String dis="";
	private void tick() {
		km.tick();
		mm.tick();
		if(cool>5){
			if(km.up){
				cool=0;
				camera.setyOffset(-32);
			}else if(km.left){
				cool=0;
				camera.setxOffset(-32);
			}else if(km.right){
				cool=0;
				camera.setxOffset(32);
			}else if(km.down){
				cool=0;
				camera.setyOffset(32);
			}
			if(km.Q){
				cool=0;
				if(curTile<1){
					curTile=0;
				}else{
					curTile--;
				}
			}else if(km.W){
				cool=0;
				if(curTile<0){
					curTile=0;
				}else{
					curTile++;
				}
			}else if(km.qu0){
				cool=0;
			}else if(km.qu1){
				cool=0;
			}
			if(mm.isLeftPressed()){
				try{
					pos[(int)((mm.$x()+camera.$xOffset())/32)][(int)((mm.$y()+camera.$yOffset())/32)]=curTile;
				}catch(Exception e){
					
				}
				
				cool=0;
			}
			if(km.ENTER){
				dis="!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
				for(int y=0;y<height0;y++) {
					for(int x=0;x<width0;x++){
						String hexcode=Tile.tiles[pos[x][y]].$hexcode();
						if(hexcode.equals("Unused"))
							continue;
						int r=Integer.valueOf(hexcode.substring(0,2),16);
						int g=Integer.valueOf(hexcode.substring(2,4),16);
						int b=Integer.valueOf(hexcode.substring(4,6),16);
						int c=(255<<24)|(r<<16)|(g<<8)|b;
						edit.setRGB(x,y,c);
					}
				}
				try{
					ImageIO.write(edit,"png",new File("EDIT.png"));
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
				
				cool=0;
			}
		}
		cool++;
	}
	private void render() {
		bs=display.$canvas().getBufferStrategy();
		if (bs!=null) {
		} else {
			display.$canvas().createBufferStrategy(3);
			return;
		}
		g=(Graphics2D)bs.getDrawGraphics();
		f=g.getFont();
		((Graphics2D) g).scale(scale,scale);
		g.clearRect(0, 0, width, height);
		
		
		
		///*
		int xStart = (int) Math.max(0, (hdlr.$camera().$xOffset() / Tile.TILE_WIDTH));
		int xEnd = (int) Math.min(width0, ((hdlr.$camera().$xOffset() + hdlr.$width()) / Tile.TILE_WIDTH + 1));
		int yStart = (int) Math.max(0, (hdlr.$camera().$yOffset() / Tile.TILE_HEIGHT));
		int yEnd = (int) Math.min(height0, ((hdlr.$camera().$yOffset() + hdlr.$height()) / Tile.TILE_HEIGHT + 1));
		for(int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				if(!getTile(x,y).$tlabel().equals("TILE:Void"))
					getTile(x, y).render(g, (int) (x*Tile.TILE_WIDTH - hdlr.$camera().$xOffset()), (int) (y*Tile.TILE_HEIGHT - hdlr.$camera().$yOffset()));
			}
		}//*/
		
		g.setColor(Color.RED);
		g.drawRect(0-(int)hdlr.$camera().$xOffset(),0-(int)hdlr.$camera().$yOffset(),width0*32,height0*32);
		g.drawRect(
				((mm.$x()+(int)hdlr.$camera().$xOffset())/32)*32-(int)hdlr.$camera().$xOffset(),
				((mm.$y()+(int)hdlr.$camera().$yOffset())/32)*32-(int)hdlr.$camera().$yOffset(),
				32,32);
		
		g.drawString(dis,mm.$x(),mm.$y());
		if(Tile.tiles[curTile]!=null){
			dis=Tile.tiles[curTile].$hexcode();
			Tile.tiles[curTile].render(g,mm.$x()+16,mm.$y()+16);
		}else{
			dis="DEFAULT";
			Tile.tiles[1023].render(g,mm.$x()+16,mm.$y()+16);
		}
		
		for(int v=0;v<20;v++){
			if(v+curTile-10<0){
				Tile.tiles[1023].render(g,width/2-(32*10)+(v*32),0);
			}else{
				Tile t=Tile.tiles[v+curTile-10];
				if(t!=null){
					Tile.tiles[v+curTile-10].render(g,width/2-(32*10)+(v*32),0);
				}else{
					Tile.tiles[1023].render(g,width/2-(32*10)+(v*32),0);
				}
			}
		}
		g.drawRect(width/2-(32*10)+(10*32),0,32,32);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("FPS "+Math.round(ae0*10.0)/10.0+"", 2, 12);
		//g.drawString("[FPS "+(int)ae0+"]", 2, 12);
		g.drawString(define, 2, height-5);
		bs.show();
		g.dispose();
	}
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >=height) {
			return Tile.def0;
		}
		Tile t = Tile.tiles[pos[x][y]];
		if (t == null) {
			return Tile.def0;
		}
		return t;
	}
	public int loadLevel(BufferedImage img) {
		if(img!=null){
			System.out.print("["+Timer.time()+"] [INFO] loadLevel(): "+img.toString()+"\n");
		}else{
			System.out.print("["+Timer.time()+"] [ERROR] loadLevel() Exception; Null Input;\n");
			return 1;
		}
		try{
			width0=img.getWidth();
			height0=img.getHeight()-2;
			pos=new int[width0][height0];
			for(int y=0;y<height0;y++) {
				for(int x=0;x<width0;x++) {
					Color c=new Color(img.getRGB(x,y));
					String hexCode=String.format("%02x%02x%02x",c.getRed(),c.getGreen(),c.getBlue());
					pos[x][y]=Tile.matchHexcode(hexCode);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.print("["+Timer.time()+"] [ERROR] A Fatal Exception Has Occurred; World Failed to Load;\n");
			return 2;
			//hdlr.$game().stop();
		}
		return 0;
	}
	public BufferedImage loadImageFromFile(String path) {
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
	public static Color hex2Rgb(String hexcode) {
	    return new Color(
	            Integer.valueOf(hexcode.substring(1,3),16),
	            Integer.valueOf(hexcode.substring(3,5),16),
	            Integer.valueOf(hexcode.substring(5,7),16));
	}
	public void setRGBImg(BufferedImage img,int x,int y,int r,int g,int b,int a){
		int c=(a<<24)|(r<<16)|(g<<8)|b;
		img.setRGB(x,y,c);
	}
	public void writeMap(){
		try{
			ImageIO.write(edit,"png",new File("EDITED.png"));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	float ae=0,ae0=0;
	public float $FPS(){
		return ae;
	}
	public void run(){
		init();
		// convert the time to seconds;
		double d=1.0D/FPSLimit;
		double l=(double)System.nanoTime()/1000000000.0D;
		double n=l;
		double lR=l;
		double maxD=0.5D;
		int sF=1;
		int msF=5;
		while(running){
			// convert the time to seconds
			n=(double)System.nanoTime()/1000000000.0D;
			if((n-l)>maxD)
				l=n;
			if(n>=l){
				// assign the time for the next update
				l+=d;
				tick();
				if(tick>59){
					tick=0;
				}else{
					tick++;
				}
				if((n<l)||(sF>msF)){
					render();
					ae=(float)(1.0F/(n-lR));
					if($tick()%20==0)
						ae0=ae;
					lR=n;
					sF=1;
				}else{
					sF++;
				}
			}else{
				double w=(l-n);
				if(w>0){
					double last=(double)System.nanoTime()/1000000000.0D;
					double now=last;
					while(true){
						now=(double)System.nanoTime()/1000000000.0D;
						if(now-last>=w){
							break;
						}else{
							Thread.yield();
						}
					}
				}
				/*
				int w=(int)(1000.0D*(l-n));
				if(w>0){
					try{
						Thread.sleep(w);
					}catch(InterruptedException e){
						e.printStackTrace();;
					}//
				}//*/
			}
		}
	stop();
	}
	/*
	long last=System.currentTimeMillis();
	long now=last;
	while(true){
		now=System.currentTimeMillis();
		if(now-last>=w){
			break;
		}
	}
	 */
	public synchronized void start() {
		if (running) {
			return;
		} else {
			running=true;
			thread=new Thread(this);
			thread.start();
			System.out.print("["+Timer.time()+"] Main Game Loop Initiated;\n");
		}
	}
	public synchronized void stop() {
		if (!running) {
			return;
		} else {
			running=false;
			try {
				thread.join();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.print("["+Timer.time()+"] Main Game Loop Terminated;\n");
		}
	}
	public int $tick() {
		return tick;
	}
	
	
}
