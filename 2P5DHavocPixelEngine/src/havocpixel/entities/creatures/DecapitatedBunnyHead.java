package havocpixel.entities.creatures;

import havocpixel.entities.items.BunnyHead;
import havocpixel.entities.items.ThrowingKnife;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class DecapitatedBunnyHead extends SkullGib{

	public DecapitatedBunnyHead(Handler handler, float x, float y) {
		super(handler, x, y);
		gib=Assets.bGib;
	}
	public void knocked(){
		hdlr.$currentWorld().em.addEntity(new BunnyHead(hdlr,this.x,this.y,1));
	}

}
