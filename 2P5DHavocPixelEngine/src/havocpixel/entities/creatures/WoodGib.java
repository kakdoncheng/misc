package havocpixel.entities.creatures;

import havocpixel.entities.items.Wood;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class WoodGib extends SkullGib{

	public WoodGib(Handler handler, float x, float y) {
		super(handler, x, y);
		bloody=false;
		gib=Assets.wgib0;
		if(Math.random()<0.5F)
			return;
		gib=Assets.wgib1;
	}
	public void knocked(){
		if(Math.random()<0.5F)
			hdlr.$currentWorld().em.addEntity(new Wood(hdlr,x,y,1));
	}

}
