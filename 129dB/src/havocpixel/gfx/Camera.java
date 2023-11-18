package havocpixel.gfx;

import havocpixel.main.Handler;
import havocpixel.entities.*;

public class Camera {
	
	private float xOffset, yOffset;
	private Handler handler;
	double scale=1;
	
	public Camera(Handler game, float xOffset, float yOffset,double scale2) {
		this.handler = game;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.scale=scale2;
	}
	
	
	public void checkBlankSpace() {
		/*
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > (handler.$currentWorld().$width()*Tile.TILE_WIDTH - handler.$width())) {
			xOffset = (handler.$currentWorld().$width()*Tile.TILE_WIDTH - handler.$width());
		}
		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > (handler.$currentWorld().$height()*Tile.TILE_HEIGHT - handler.$height())) {
			yOffset = (handler.$currentWorld().$height()*Tile.TILE_HEIGHT - handler.$height());
		}*/
	}
	
	public void centerOnEntity0(Entity e) {
		xOffset = (e.$x()-handler.$width()/2+e.$width());
		yOffset = (e.$y()-handler.$height()/2+e.$height());
		//checkBlankSpace();
	}
	public void centerOnEntity(Entity e) {
		float cx=(e.$x()-handler.$width()/2+e.$width());
		float cy=(e.$y()-handler.$height()/2+e.$height());
		int v=100;//,l=0;
		if(xOffset-cx<-v||xOffset-cx>v||yOffset-cy<-v||yOffset-cy>v){
			centerOnEntity0(e);
		}else{
			int q=15;
			if(xOffset-cx<-q){
				xOffset=cx-q;
			}else if(xOffset-cx>q){
				xOffset=cx+q;
			}
			if(yOffset-cy<-q){
				yOffset=cy-q;
			}else if(yOffset-cy>q){
				yOffset=cy+q;
			}
		}
		//l=(int) Math.abs(xOffset-cx);
		//if(l>30)
			//l=31;
		if((int)xOffset<(int)cx){
			xOffset++;//=l/10!=0?l:1;
		}else if((int)xOffset>(int)cx){
			xOffset--;//=l/10!=0?l:1;
		}
		//l=(int) Math.abs(yOffset-cy);
		//if(l>30)
			//l=31;
		if((int)yOffset<(int)cy){
			yOffset++;//=l/10!=0?l:1;
		}else if((int)yOffset>(int)cy){
			yOffset--;//=l/10!=0?l:1;
		}
		//checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public float $xOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float $yOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}