package havocpixel.gfx;

import havocpixel.Game;
import havocpixel.Tile;
import havocpixel.entities.Entity;

public class Camera {
	
	private double xOffset, yOffset;
	private Game game;
	private double speed;
	private int lock;
	
	public Camera(Game game, float xOffset, float yOffset) {
		this.game = game;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		speed=60;
		lock=16;
	}
	
	
	public void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > (game.$currentWorld().$width()*Tile.TILE_WIDTH - game.$width())) {
			xOffset = (game.$currentWorld().$width()*Tile.TILE_WIDTH - game.$width());
		}
		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > (game.$currentWorld().$height()*Tile.TILE_HEIGHT - game.$height())) {
			yOffset = (game.$currentWorld().$height()*Tile.TILE_HEIGHT - game.$height());
		}
	}
	
	public void hardCenterOnEntity(Entity e) {
		xOffset = ((int)e.$x()-game.$width()/2+e.$width()/2);
		yOffset = ((int)e.$y()-game.$height()/2+e.$height()/2);
	}
	public void centerOnEntity(Entity e, double dt) {
		if(e==null){
			return;
		}
		//System.out.println("havocpixel.Camera:INFO: ID: "+ID);
		//System.out.println("havocpixel.Camera:INFO: dxy:"+String.format("%.3f", speed*dt)+
		//		" xOff:"+String.format("%.3f", xOffset)+
		//		" yOff:"+String.format("%.3f", yOffset));
		double cx=(e.$x()-game.$width()/2+e.$width()/2);
		double cy=(e.$y()-game.$height()/2+e.$height()/2);
		int v=100;//,l=0;
		if(xOffset-cx<-v||xOffset-cx>v||yOffset-cy<-v||yOffset-cy>v){
			//hardCenterOnEntity(e);
		}
		if(xOffset-cx<-lock){
			xOffset=cx-lock;
		}else if(xOffset-cx>lock){
			xOffset=cx+lock;
		}
		if(yOffset-cy<-lock){
			yOffset=cy-lock;
		}else if(yOffset-cy>lock){
			yOffset=cy+lock;
		}
		
		double dxy=speed*dt;
		if((int)xOffset<(int)cx){
			xOffset+=dxy;
		}else if((int)xOffset>(int)cx){
			xOffset-=dxy;
		}
		if((xOffset-cx<dxy&&xOffset-cx>-dxy)||
				(cx-xOffset<dxy&&cx-xOffset>-dxy)){
			xOffset=cx;
		}
		if((int)yOffset<(int)cy){
			yOffset+=dxy;
		}else if((int)yOffset>(int)cy){
			yOffset-=dxy;
		}
		if((yOffset-cy<dxy&&yOffset-cy>-dxy)||
				(yOffset-cy<dxy&&yOffset-cy>-dxy)){
			yOffset=cy;
		}
		//System.out.println("havocpixel.Camera:INFO: dxy:"+String.format("%.3f", speed*dt)+
		//		" xOff:"+String.format("%.3f", xOffset)+
		//		" x:"+String.format("%.3f", cx)+
		//		" yOff:"+String.format("%.3f", yOffset)+
		//		" y:"+String.format("%.3f", cy));
		//checkBlankSpace();
	}

	public double $xOffset() {
		return xOffset;
	}

	public double $yOffset() {
		return yOffset;
	}
	
	public void setxOffset(double x){
		xOffset=x;
	}
	public void setyOffset(double y){
		yOffset=y;
	}

}
