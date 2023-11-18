package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class AdrenalineShot extends Item{
	public AdrenalineShot(Handler handler, float x, float y,int amt) {
		//epinephrine,n-methylamphetamine
		super(handler, x, y, Assets.adrenal,"Methylenedioxyphenethylamine 1ML", amt,0.1F);
		desc[0]="Desc: A hypodermic syringe designed for quick";
		desc[1]="injection of pure fUCKHeUG RAAGGEEEEEEE";
		desc[2]="directly to your bloodstream. Be careful,";
		desc[3]="as side effects and overdose is nasty.";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		this.qLim=1;
		weapon=true;
		item=true;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$player().snortCrack(500+(int)(Math.random()*1000));
		quantity--;
		return;
	}
}
