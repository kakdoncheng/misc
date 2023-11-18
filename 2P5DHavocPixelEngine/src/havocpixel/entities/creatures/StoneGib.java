package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class StoneGib extends SkullGib{

	public StoneGib(Handler handler, float x, float y) {
		super(handler, x, y);
		bloody=false;
		gib=Assets.tgib0;
		if(Math.random()<0.5F)
			return;
		gib=Assets.tgib1;
	}

}
