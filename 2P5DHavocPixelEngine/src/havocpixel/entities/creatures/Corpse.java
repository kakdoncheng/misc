package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Corpse extends Creature{

	protected BufferedImage d;
	public Corpse(Handler hdlr, float x, float y) {
		super(hdlr, x, y
				, Creature.DEFAULT_WIDTH
				, Creature.DEFAULT_HEIGHT
				);
		this.ally=255;
		//this.object=true;
		this.alive=false;
		
		bounds.x = 8;
		bounds.y = 20;
		bounds.width = 15;
		bounds.height = 11;
		
		label="DEAD_CORPSE";
		target="NONE";
		speed=0;
		damage=0;
		maxHealth=1;
		this.AC=0;
		d=Assets.corpse[(int)(Math.random()*10)];
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(d,
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
			//spawnGuts();
			dying=true;
		}
	}

}
