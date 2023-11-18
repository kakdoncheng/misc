package havocpixel.entities.creatures;

import havocpixel.entities.items.ThrowingKnife;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class ThrownKnifeGib extends HelmetGib{

	public ThrownKnifeGib(Handler handler, float x, float y) {
		super(handler, x, y);
		gib=Assets.ku;
	}
	
	public void knocked(){
		hdlr.$currentWorld().em.addEntity(new ThrowingKnife(hdlr,this.x,this.y,1));
	}
}
