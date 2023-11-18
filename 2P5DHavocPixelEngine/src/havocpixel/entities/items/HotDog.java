package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class HotDog extends Item{

	public HotDog(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.hotdog,"Hot Dog", amt,0.5F);
		desc[0]="Desc: Red meat and white bread together make for";
		desc[1]="a hell of a tasty snack. If you weren\'t a skeleton";
		desc[2]="hanging on to it for about two years. The mustard";
		desc[3]="does hide the smell a little though.";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		this.qLim=1;
		weapon=true;
		item=true;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$player().eat(250+(int)(Math.random()*101));
		this.quantity--;
	}

}
