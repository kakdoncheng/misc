package havocpixel.entities.creatures;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class HoodedSkeleton extends GraveSkeleton{

	public HoodedSkeleton(Handler hdlr, float x, float y, String label0) {
		super(hdlr, x, y, label0);
		
		speed=1;
		
		su0=new Animation(hdlr,120,Assets.hsUp);
		sl0=new Animation(hdlr,120,Assets.hsLeft);
		sd0=new Animation(hdlr,120,Assets.hsDown);
		sr0=new Animation(hdlr,120,Assets.hsRight);
		sui0=new Animation(hdlr,240,Assets.hsiU);
		sli0=new Animation(hdlr,240,Assets.hsiL);
		sdi0=new Animation(hdlr,240,Assets.hsiD);
		sri0=new Animation(hdlr,240,Assets.hsiR);
		asu0=new Animation(hdlr,90,Assets.hAsUp);
		asl0=new Animation(hdlr,90,Assets.hAsLeft);
		asd0=new Animation(hdlr,90,Assets.hAsDown);
		asr0=new Animation(hdlr,90,Assets.hAsRight);
		
		hU=Assets.hsiU[1];
		hL=Assets.hsiL[1];
		hD=Assets.hsiD[1];
		hR=Assets.hsiR[1];
		spawn=new Animation(hdlr,120,Assets.hemerge);
	}

}
