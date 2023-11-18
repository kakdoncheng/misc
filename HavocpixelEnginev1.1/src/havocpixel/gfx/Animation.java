package havocpixel.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	private BufferedImage[] frames;
	private int index;
	private double speed;
	private double timePassed;
	private boolean stopOnLastFrame;
	
	public Animation(BufferedImage[] frames, double speed){
		this.frames=frames;
		this.speed=speed;
		index=0;
		timePassed=0;
		stopOnLastFrame=false;
	}
	public Animation(BufferedImage[] frames, double speed, boolean stop){
		this.frames=frames;
		this.speed=speed;
		index=0;
		timePassed=0;
		stopOnLastFrame=stop;
	}
	public void update(double dt){
		timePassed+=dt;
		if(!stopOnLastFrame)
			timePassed%=speed;
		index=(int)(timePassed/(speed/frames.length));
		if(!(index<frames.length)){
			index=frames.length-1;
		}
	}
	public int $currentIndex(){
		return index;
	}
	public double $speed(){
		return speed;
	}
	public BufferedImage $currentFrame(){
		return frames[index];
	}
	public int $indexOfLastFrame(){
		return frames.length-1;
	}
	public boolean isLastFrame(){
		return index==frames.length-1;
	}
	public boolean isStopped(){
		return stopOnLastFrame&&(timePassed>=speed);
	}
	public void reset(){
		index=0;
		timePassed=0;
	}

}
