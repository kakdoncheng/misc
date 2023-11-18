package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;

public class SkullPile extends Tombstone{

	public SkullPile(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		health=100;
		height=64;
		width=64;
		bounds.x = 8;
		bounds.y = 21;
		bounds.width = 53;
		bounds.height = 40;
	}
	public void render(Graphics g){
		g.drawImage(Assets.skullpile,
				(int)(x-hdlr.$camera().$xOffset()),
				(int)(y-hdlr.$camera().$yOffset()),
				width,
				height,null);
	}
	boolean dying=false;
	@Override
	public void die() {
		if(!dying){
			spawnBoneGibs();
			hdlr.$currentWorld().em.addEntity(new SkullGib(hdlr,this.x+9+32,this.y+9+32));
			hdlr.$currentWorld().em.addEntity(new SkullGib(hdlr,this.x-9+32,this.y+9+32));
			hdlr.$currentWorld().em.addEntity(new SkullGib(hdlr,this.x-9+32,this.y-9+32));
			hdlr.$currentWorld().em.addEntity(new SkullGib(hdlr,this.x+9+32,this.y-9+32));
			dying=true;
		}
	}

}
