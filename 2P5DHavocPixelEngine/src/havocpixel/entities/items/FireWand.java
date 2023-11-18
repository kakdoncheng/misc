package havocpixel.entities.items;

import havocpixel.entities.Fireball;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class FireWand extends Item{

	public FireWand(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.wand0, "Wand of Firebolt", amt, 1.0F);
		// TODO Auto-generated constructor stub
		desc[0]="Desc: A piece of wood embued with magical power.";
		desc[1]="This particular one is basic and fairly easy to use;";
		desc[2]="just wave it and watch the flames make the enemy go";
		desc[3]="poof.";
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
				new Fireball(hdlr,tx,ty,owner,dir,true));
		//this.summonInfernoWave(tx, ty, dir, owner, false);
		this.charges--;
		
	}

}
