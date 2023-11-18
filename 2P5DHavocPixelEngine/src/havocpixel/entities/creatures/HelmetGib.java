package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class HelmetGib extends SkullGib{

	public HelmetGib(Handler handler, float x, float y) {
		super(handler, x, y);
		gib=Assets.gibas;
		bloody=false;
	}

}
