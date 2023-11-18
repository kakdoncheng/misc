package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class LeftHandPole extends RightHandPole{

	public LeftHandPole(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		bounds.x = 4;
		bounds.y = 4;
		bounds.width = 6;
		bounds.height = 26;
		int u=(int)(Math.random()*3);
		if(u==0){
			t=Assets.pole[2];
		}else if(u==1){
			t=Assets.pole[4];
		}else if(u==2){
			t=Assets.pole[5];
		}else{
			t=Assets.pole[0];
		}
	}

}
