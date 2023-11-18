package havocpixel.entities.creatures;

import java.awt.Rectangle;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Log extends Rock{

	public Log(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		width=64;
		label="LOG";
		bounds=new Rectangle(7,12,50,20);
		img=Assets.log0;
	}

	boolean dying=false;
	public void die(){
		if(!dying){
			spawnWoodGibs();
			spawnWoodGibs();
			dying=true;
		}
	}
}
