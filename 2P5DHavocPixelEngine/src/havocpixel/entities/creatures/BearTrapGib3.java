package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class BearTrapGib3 extends SkullGib{

	public BearTrapGib3(Handler handler, float x, float y) {
		super(handler, x, y);
		gib=Assets.gbt3;
		bloody=false;
	}
}
