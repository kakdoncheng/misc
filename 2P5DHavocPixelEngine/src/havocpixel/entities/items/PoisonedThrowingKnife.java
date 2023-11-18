package havocpixel.entities.items;

import java.awt.Graphics;

import havocpixel.entities.PoisonedThrownKnife;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class PoisonedThrowingKnife extends Item{
	public PoisonedThrowingKnife(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.pkd, "Poisoned Throwing Knife", amt, 0.25F);
		// TODO Auto-generated constructor stub
		weapon=true;
		desc[0]="Desc: A poisoned throwing knife. Will leave some";
		desc[1]="really nasty gangrene on contact. Don't stub";
		desc[2]="your toe on this one.";
		desc[3]="";
		//desc="   ";
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$currentWorld().em.addEntity(new PoisonedThrownKnife(hdlr,tx,ty,owner,dir));
		this.quantity--;
	}
}
