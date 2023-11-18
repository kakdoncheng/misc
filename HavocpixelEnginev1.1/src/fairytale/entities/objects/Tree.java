package fairytale.entities.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import havocpixel.Game;
import havocpixel.entities.ImmoveableObject;
import havocpixel.gfx.CoreAssets;

public class Tree extends ImmoveableObject{

	public Tree(Game game, double x, double y) {
		super(game, x, y);
		bounds=new Rectangle(17,64,30,32);
		name="Tree";
		width=64;
		height=96;
		img=CoreAssets.tree[game.$randomInt(0, 4)];
	}
	
	public void render(Graphics g) {
		this.renderShadow(g);
		g.drawImage(img,
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()),
				width,
				height,null);
	}


}
