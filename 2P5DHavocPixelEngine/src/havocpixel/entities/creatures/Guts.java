package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Guts extends Gib{

	public Guts(Handler handler, float x, float y) {
		super(handler, x, y);
		if(Math.random()<0.33F)
			gib=Assets.dGib0;
		else if(Math.random()<0.66F)
			gib=Assets.dGib1;
		else
			gib=Assets.dGib2;
		// TODO Auto-generated constructor stub
	}

}
