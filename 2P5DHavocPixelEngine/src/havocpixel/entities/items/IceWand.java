package havocpixel.entities.items;

import havocpixel.entities.Icebolt;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class IceWand extends Item{
	public IceWand(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.wand1, "Wand of Lightning", amt, 1.0F);
		// TODO Auto-generated constructor stub
		desc[0]="Desc: A piece of wood embued with magical power.";
		desc[1]="Typically magic in this world is much more brutal";
		desc[2]="then is typically romanticized.";
		desc[3]="";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		weapon=true;
		item=true;
		charged=true;
		charges=16;
		qLim=1;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$currentWorld().em.addEntity(
				new Icebolt(hdlr,tx,ty,owner,dir));
		//this.summonInfernoWave(tx, ty, dir, owner, false);
		this.charges--;
		
	}
}
