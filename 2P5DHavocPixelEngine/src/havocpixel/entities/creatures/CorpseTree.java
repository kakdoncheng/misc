package havocpixel.entities.creatures;

import java.awt.Graphics;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class CorpseTree extends Tree{

	public CorpseTree(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		int u=(int)(Math.random()*4);
		if(u==0){
			t=Assets.ct0;
		}else if(u==1){
			t=Assets.ct1;
		}else if(u==2){
			t=Assets.ct2;
		}else if(u==3){
			t=Assets.ct3;
		}else{
			t=Assets.ct;
		}
	}


}
