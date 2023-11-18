package havocpixel.entities.items;

import havocpixel.entities.ThrownKnifeAlt;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class ThrowingKnife extends Item{

	public ThrowingKnife(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.ku, "Throwing Knife", amt,0.25F);
		// TODO Auto-generated constructor stub
		weapon=true;
		item=true;
		desc[0]="Desc: A dagger made specifically for throwing";
		desc[1]="at things or people you don't like. As common";
		desc[2]="as the leaves and just about as dangerous.";
		desc[3]=" ";
		//desc="  ";
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$currentWorld().em.addEntity(new ThrownKnifeAlt(hdlr,tx,ty,
				hdlr.$mm().$x()-(int)hdlr.$camera().$xOffset(),
				hdlr.$mm().$y()-(int)hdlr.$camera().$xOffset(),owner,dir));
		this.quantity--;
	}

}
