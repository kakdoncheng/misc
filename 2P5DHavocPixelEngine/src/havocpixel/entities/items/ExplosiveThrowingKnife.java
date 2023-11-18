package havocpixel.entities.items;

import havocpixel.entities.ExplodingThrownKnife;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class ExplosiveThrowingKnife extends Item{

	public ExplosiveThrowingKnife(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.ekd, "Explosive Throwing Knife", amt, 1.5F);
		// TODO Auto-generated constructor stub
		weapon=true;
		desc[0]="Desc: A grenade strapped onto a throwing knife";
		desc[1]="with the fuse modified to detonate on contact.";
		desc[2]="Possibly the most useful invention ever made on";
		desc[3]="this forsaken island.";
		//desc="   ";
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		hdlr.$currentWorld().em.addEntity(new ExplodingThrownKnife(hdlr,tx,ty,0,0,owner,dir));
		this.quantity--;
	}

}
