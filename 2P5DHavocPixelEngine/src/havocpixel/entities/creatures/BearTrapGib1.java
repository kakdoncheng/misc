package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class BearTrapGib1  extends SkullGib{

	public BearTrapGib1(Handler handler, float x, float y) {
		super(handler, x, y);
		gib=Assets.gbt1;
		bloody=false;
	}
}
