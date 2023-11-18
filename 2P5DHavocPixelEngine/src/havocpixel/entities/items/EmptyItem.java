package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class EmptyItem extends Item{

	public EmptyItem(Handler handler, float x, float y) {
		super(handler, x, y, Assets.def,"<EMPTY>",1,0.0F);
		fixBounds();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		// TODO Auto-generated method stub
		
	}
	public boolean isEmpty(){
		return true;
	}

}
