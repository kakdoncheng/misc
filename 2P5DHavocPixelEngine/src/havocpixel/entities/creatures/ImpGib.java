package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class ImpGib extends Gib{

	public ImpGib(Handler handler, float x, float y) {
		super(handler, x, y);
		if(Math.random()<0.33F)
			gib=Assets.dGibarm;
		else if(Math.random()<0.66F)
			gib=Assets.dGibleg;
		else
			gib=Assets.dGibrib;
		
		// TODO Auto-generated constructor stub
	}

}
