package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class BowlOfRice extends Item{

	public BowlOfRice(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.rice,"Bowl Of Rice", amt,2.0F);
		desc[0]="Desc: A bowl of steamed, white rice. Not of the";
		desc[1]="best quality, but will certainly serve to keep";
		desc[2]="you energized enough to fight creeps if you eat ";
		desc[3]="it. Comes with chopsticks.";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		this.qLim=1;
		weapon=true;
		item=true;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$player().eat(500+(int)(Math.random()*101));
		this.quantity--;
	}

}
