package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class GlassOfMilk extends Item{
	public GlassOfMilk(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.milk,"Glass of Milk", amt,1.0F);
		desc[0]="Desc: A glass of milk. Filled with calcium,";
		desc[1]="taurine, caffeine, and all of the various";
		desc[2]="other nutrients at a warrior needs. Don't";
		desc[3]="ask where the milk came from.";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		this.qLim=1;
		weapon=true;
		item=true;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$player().cureEffects();
		quantity--;
		return;
	}
}
