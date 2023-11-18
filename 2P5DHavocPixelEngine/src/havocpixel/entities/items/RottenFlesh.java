package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class RottenFlesh extends Item{

	public RottenFlesh(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.rFlesh, "Rotten Flesh", amt, 1.25F);
		desc[0]="Desc: Turns out that demon flesh instantly rots";
		desc[1]="once exposed to fresh air. It's barely edible,";
		desc[2]="but don\'t you have anything better to eat ";
		desc[3]="anyway?";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		//this.qLim=8;
		weapon=true;
		item=true;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$player().eat(50+(int)(Math.random()*51));
		this.quantity--;
	}
}
