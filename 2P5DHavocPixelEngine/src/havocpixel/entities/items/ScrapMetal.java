package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;


public class ScrapMetal extends Item{

	public ScrapMetal(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.gbt0, "Piece of Metal", amt, 0.01F);
		desc[0]="Desc: A scrap of metal. You don't really know why";
		desc[1]="you picked it up as it seems to be completely ";
		desc[2]="worthless. It's not even shiny.";
		desc[3]="";
		int u=(int)(Math.random()*4);
		if(u==0)
			img=Assets.gbt0;
		else if(u==1)
			img=Assets.gbt1;
		else if(u==2)
			img=Assets.gbt2;
		else if(u==3)
			img=Assets.gbt3;
		weapon=false;
		item=true;
		//qLim=10000;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		// TODO Auto-generated method stub
		
	}

}
