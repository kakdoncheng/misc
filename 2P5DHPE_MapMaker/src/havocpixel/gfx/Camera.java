package havocpixel.gfx;

import havocpixel.main.Handler;

public class Camera {
	
	private float xOffset, yOffset;
	private Handler handler;
	int scale=1;
	
	public Camera(Handler game, float xOffset, float yOffset,int scale) {
		this.handler = game;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.scale=scale;
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		//checkBlankSpace();
	}

	public float $xOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset+=xOffset;
	}

	public float $yOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset+=yOffset;
	}
}