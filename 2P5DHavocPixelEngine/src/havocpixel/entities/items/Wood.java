package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;


public class Wood extends Item{

	public Wood(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.wgib0, "Wood", amt, 10.0F);
		desc[0]="Desc: A huge plank of wood. Heavy as fuck. It";
		desc[1]="looks like a giant splinter, as if the tree it";
		desc[2]="came from was obliterated by an explosion. Or";
		desc[3]="monster. It's of a decent quality though.";
		if(Math.random()<0.5F)
			img=Assets.wgib1;
		weapon=false;
		item=true;
		//qLim=8;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		// TODO Auto-generated method stub
		
	}

}
