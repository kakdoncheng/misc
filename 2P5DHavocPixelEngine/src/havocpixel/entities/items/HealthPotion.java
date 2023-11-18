package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class HealthPotion extends Item{
	public HealthPotion(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.hpot,"Health Potion", amt,0.1F);
		desc[0]="Desc: On closer inspection, it's just a vial";
		desc[1]="of colored water. It's harmless, but also";
		desc[2]="completely useless. Magical items really are ";
		desc[3]="too much to hope for after all.";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		//this.qLim=16;
		weapon=true;
		item=true;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		quantity--;
		return;
	}
}
