package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class BunnyHead extends Item{

	public BunnyHead(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.bGib, "Decapitated Bunny Head", amt, 1.0F);
		desc[0]="Desc: A bunny head. Also a gruesome token to";
		desc[1]="your unparalled barbarity and savagery. It still";
		desc[2]="smells freshly horrifying. Don't even think about";
		desc[3]="eating it.";
		weapon=false;
		item=true;
		qLim=1;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		// TODO Auto-generated method stub
	}

}
