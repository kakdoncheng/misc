package havocpixel.gfx;

import havocpixel.main.Handler;

import java.awt.image.BufferedImage;

public class Animation {
	private Handler hdlr;
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(Handler hdlr, int speed, BufferedImage[] frames) {
		this.speed=speed;
		this.frames=frames;
		index=0;
		timer=0;
		lastTime=System.currentTimeMillis();
	}
	public int tick() {
		if(hdlr!=null)
			if(hdlr.showInv){
				if(hdlr.$currentWorld().getColdTick())
					timer+=System.currentTimeMillis()-lastTime;
			}else{
				timer+=System.currentTimeMillis()-lastTime;
			}
		//else
			timer+=System.currentTimeMillis()-lastTime;
		lastTime=System.currentTimeMillis();
		if(timer>speed) {
			index++;
			timer-=speed;
			if(timer>speed){
				timer=0;
			}
			if(index>=frames.length) {
				index=0;
			}
		}
		return index;
	}
	public void reset(){
		index=0;
		timer=0;
	}
	public int $index(){
		return index;
	}
	public void fixTimer(int n){
		timer+=n;
	}
	public BufferedImage $currentFrame() {
		return frames[index];
	}
}
