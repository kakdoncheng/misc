package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RightHandPole extends Tombstone{

	protected BufferedImage t;
	public RightHandPole(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		bounds.x = 24;
		bounds.y = 4;
		bounds.width = 6;
		bounds.height = 26;
		immovable=true;
		label="CORPSE_POLE";
		int u=(int)(Math.random()*3);
		if(u==0){
			t=Assets.pole[0];
		}else if(u==1){
			t=Assets.pole[1];
		}else if(u==2){
			t=Assets.pole[3];
		}else{
			t=Assets.pole[0];
		}
	}

	public void render(Graphics g) {
		g.drawImage(t,
				(int)(x-hdlr.$camera().$xOffset()),
				(int)(y-32-hdlr.$camera().$yOffset()),
				width,
				height+32,null);
	}
	//corpse, poles, trees
	boolean dying=false;
	@Override
	public void die() {
		if(!dying){
			spawnBoneGibs();
			//spawnBoneGibs();
			spawnWoodGibs();
			//spawnWoodGibs();
			dying=true;
		}
	}
}
