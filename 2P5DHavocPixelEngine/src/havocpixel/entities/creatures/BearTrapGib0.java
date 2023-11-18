package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class BearTrapGib0 extends SkullGib{

	public BearTrapGib0(Handler handler, float x, float y) {
		super(handler, x, y);
		gib=Assets.gbt0;
		bloody=false;
	}
}
