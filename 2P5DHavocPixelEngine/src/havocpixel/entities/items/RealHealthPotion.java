package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class RealHealthPotion extends Item{
	public RealHealthPotion(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.hpot0,"Healing Potion", amt,0.1F);
		desc[0]="Desc: A vial of liquid imbued with magical power.";
		desc[1]="Actual potions of healing are very rare, and are";
		desc[2]="in turn extremely sought after. Varies in quality.";
		desc[3]="";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		//this.qLim=16;
		weapon=true;
		item=true;
	}
	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$player().healTick+=200+(int)(Math.random()*101);
		quantity--;
		return;
	}

}
