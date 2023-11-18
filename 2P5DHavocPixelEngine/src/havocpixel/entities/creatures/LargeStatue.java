package havocpixel.entities.creatures;

import java.awt.Rectangle;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class LargeStatue extends Rock{

	public LargeStatue(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		this.width=96;
		this.height=128;
		this.maxHealth=5000;
		this.health=5000;
		bounds=new Rectangle(28,78,40,50);
		img=Assets.st0;
		// TODO Auto-generated constructor stub
	}

}
