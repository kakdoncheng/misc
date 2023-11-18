package havocpixel;

import havocpixel.entities.Entity;
import havocpixel.gfx.Camera;
import havocpixel.gfx.CoreAssets;
import havocpixel.gfx.Texture;
import havocpixel.input.KeyManager;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Game implements Runnable{
	
	@SuppressWarnings("unused")
	private String version="v1.92";
	
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	private Thread thread;
	
	private boolean running=false;
	private int width, height;
	private String title;
	private boolean full;
	private boolean debug=false;
	private int FPSLimit=60;
	//private int frame=0;
	private double scale=1.0;
	private double runSpeed=1.0;
	private boolean fixedStep=false;
	public void setRenderScale(double scale){
		this.scale=scale;
	}
	public void setFPSLimit(int limit){
		FPSLimit=limit;
	}
	public void setRunSpeed(double speed){
		this.runSpeed=speed;
	}
	public void setFixedStep(boolean fixed){
		this.fixedStep=fixed;
	}

	private Font f;
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
	
	//deprecated, use $currentScale instead.
	public double $renderScale(){
		return scale;
	}
	public double $currentScaleWidth(){
		if(full)
			return display.$frame().getWidth()/(double)this.width;
		else
			return $renderScale();
	}
	public double $currentScaleHeight(){
		if(full)
			return display.$frame().getHeight()/(double)this.height;
		else
			return $renderScale();
	}
	
	public Game(String title, int width, int height, boolean debug, boolean fullscreen) {
		if (title!=null) {
			this.title=title;
		} else {
			this.title="null";
		}
		this.width=width;
		this.height=height;
		this.full=fullscreen;
		this.debug=debug;
	}
	
	private KeyManager km;
	public KeyManager $km(){
		return km;
	}
	private StateManager sm;
	public StateManager $sm(){
		return sm;
	}
	private WorldManager wm;
	public WorldManager $wm(){
		return wm;
	}
	public World $currentWorld(){
		return wm.$currentWorld();
	}
	private Camera camera;
	public Camera $camera(){
		return camera;
	}
	private Entity player;
	public Entity $player(){
		return player;
	}
	private Random prng;
	private long seed=0;
	private String alphabet=" ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßðñòóôõö÷øùúûüýþÿ€µƒABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~‚„…†‡ˆ‰‹ŒŽ‘’“”•–—˜™š›œšžŸ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´¶·¸¹º»¼½¾¿";
	private char $charAt(int a){
		return this.alphabet.charAt(a);
	}
	public void setPRNGSeed(long seed) {
		this.seed=seed;
	}
	public int $randomInt(int lower, int upper){
		return lower+prng.nextInt((upper-lower));
	}
	public double $randomDouble(double lower, double upper){
		return lower+(prng.nextDouble()*(upper-lower));
	}
	public char $randomChar(){
		return $charAt($randomInt(0,alphabet.length()));
	}
	public String $randomString(int length){
		String out="";
		for(int i=0; i<length; i++)
			out+=$randomChar();
		return out;
	}
	private void init() {
		Utils.init();
		prng=new Random(seed!=0?seed:System.currentTimeMillis());
		f=new Font("Lucida Console", Font.BOLD, 8);
		//loadIcon=Texture.loadImage("/icon.png");
		km=new KeyManager();
		display=new Display((int)(this.width*scale),(int)(this.height*scale),this.title,full);
		display.$frame().addKeyListener(km);
		new Thread(new Runnable(){
    	    public void run(){
    	    	//isLoading=true;
    	    	mainLoad();
    	    	//long l=System.currentTimeMillis();
    	    	//while(System.currentTimeMillis()-l<1000){
    	    	//	continue;
    	    	//}
    	    	isLoading=false;
    	    }
    	}).start();
	}
	private void mainLoad(){
		setLoadingText("Loading Textures");
		CoreAssets.load();
		setLoadingText("Loading Tilesets");
		Tile.loadTiles();
		//TEMP PLAYER
		setLoadingText("Setting up core variables");
		player=null;
		sm=new StateManager(this);
		wm=new WorldManager(this);
		camera=new Camera(this,0,0);
		setLoadingText("Loading States");
		sm.loadStates();
		setLoadingText("Loading Worlds");
		wm.loadWorlds();
		sm.setCurrentState(1);
		//wm.teleportTo(0, 0, 1);
	}
	private boolean isLoading=true;
	public void setIsLoading(boolean isLoading){
		this.isLoading=isLoading;
	}
	private double loadingDt=0;
	//private BufferedImage loadIcon;
	private String loadingText="";
	private String[] load={
		".  ",
		".. ",
		"..."
	};
	public void setLoadingText(String text){
		loadingText=text;
	}
	
	private void update(double dt) {
		km.update();
		if(km.ESC){
			System.exit(0);
		}
		if(isLoading){
			loadingDt+=dt;
		}else{
			if(sm.$currentState()!=null){
				sm.$currentState().update(dt);
			}
		}
		
		//distortion
		//distortionT=1;
		if(distortionT>0)
			distortionT-=dt;
	}
	
	//trial render
	private BufferedImage img;
    private Graphics2D g2d;
    
    //distortion
	private double distortionT=0;
	public void distortGraphics(double dt){
		distortionT=dt;
	}
    
	private void render() {
		//normal canvas
		///*
		bs=display.$canvas().getBufferStrategy();
		if (bs!=null) {
		} else {
			display.$canvas().createBufferStrategy(2);
			return;
		}
		g=(Graphics2D)bs.getDrawGraphics();
		if(!display.isFullScreen())
			((Graphics2D) g).scale(scale,scale);
		else
			((Graphics2D) g).scale(display.$frame().getWidth()/(double)this.width,display.$frame().getHeight()/(double)this.height);
		g.clearRect(0, 0, width, height);
		//*/
		
		//alt canvas
		if(img!=null){
			g2d=(Graphics2D) img.getGraphics();
			g2d.clearRect(0, 0, width, height);
		}else{
			img = Texture.getAcceleratedBufferedImage(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));
			g2d = img.createGraphics();
		}
		
		//render here
		if(isLoading){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			//g.drawImage(loadIcon, width-60, height-45-14, 60, 45, null);
			g.setColor(Color.GRAY);
			g.setFont(f);
			String text=loadingText+load[(int)(loadingDt*3)%load.length];
			g.drawString(text, width-5-g.getFontMetrics().stringWidth(text), height-5);
		}else{
			if(sm.$currentState()!=null){
				if(sm.$currentState().isUsingCompressedRendering()){
					sm.$currentState().render(g2d);
					g.drawImage(img, 0, 0, width, height, null);
					
					//test distortion
					if(distortionT>0){
						BufferedImage dimg=Utils.makeImageTranslucent(img, 0.2);
						g.drawImage(dimg, 2, 0, width, height, null);
						//g.drawImage(dimg, $randomInt(1,4), 0, width, height, null);
					}
					
					
				}else{
					sm.$currentState().render(g);
				}
			}
			
			//debug centerpoint
			//g.setColor(Color.RED);
			//g.setFont(f);
			//g.drawLine(($width()/2)-10,$height()/2,($width()/2)+10,$height()/2);
			//g.drawLine($width()/2,($height()/2)-10,$width()/2,($height()/2)+10);
			
			//int testx=(int)($width()/2+$camera().$xOffset()),testy=(int)($height()/2+$camera().$yOffset());
			//boolean c=$currentWorld().getTile(testx/32,testy/32).impassable();
			//g.drawString(c+" ("+testx+","+testy+")", width-5-g.getFontMetrics().stringWidth(c+" ("+testx+","+testy+")"), height-5);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(f);
		if(debug){
			g.drawString("FPS "+String.format("%.1f",$FPS())+"", 0, 14);
			//g.drawString("[HavocPixel Engine "+version+"]["+title+"]", 2, height-5);
		}
		
		bs.show();
		//BufferedImage rimg=Texture.resize(img, display.$Mwidth(), display.$Mheight());
		//this.display.$altCanvas().setIcon(new ImageIcon(rimg));
		g.dispose();
	}
	
	//FPS calculation
	private double cFPS=0,dFPS=0,avg=0;
	private double halfSec=0;
	private int frames=0;
	public double $FPS(){
		return dFPS;
	}
	public double $currentSecond(){
		return halfSec;
	}
	public void run(){
		init();
		double d=1.0/FPSLimit;
		double l=System.nanoTime()/1000000000.0;
		double n=l;
		while(running){
			n=System.nanoTime()/1000000000.0;
			halfSec=n%1.0;
			double dt=n-l;
			if(dt>=d){
				//System.out.print("havocpixel.Game:INFO: Frame "+frame+".\n");
				update(fixedStep?d:runSpeed*dt);
				render();
				cFPS=1.0/(dt);
				if(frames>dFPS/5){
					dFPS=avg/frames;
					if(dFPS<FPSLimit-1){
						System.out.print("havocpixel.Game:WARNING: Significant frame rate drop below cap. ("+
								((FPSLimit-dFPS)/5)+
								" frames lost.)\n");
					}
					avg=0;
					frames=0;
				}
				avg+=cFPS;
				frames++;
				l=n;
				//frame++;
			}
		}
		stop();
	}
	public synchronized void start() {
		if (running) {
			return;
		} else {
			running=true;
			thread=new Thread(this);
			thread.start();
			System.out.print("havocpixel.Game:INFO: Game Loop Initiated.\n");
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
			System.out.print("havocpixel.Game:INFO: Game Loop Terminated.\n");
		}
	}
	
	
}
