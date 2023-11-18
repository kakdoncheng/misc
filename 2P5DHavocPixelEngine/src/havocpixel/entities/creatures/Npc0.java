package havocpixel.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Npc0 extends Creature{
	//make this into a prototype shopkeeper?
	private Random r = new Random();
	private int rndInt(int k) {
		return r.nextInt(k);
	}
	
	private Animation up, left, down, right;
	private int moveTick=0;
	public Npc0(Handler hdlr, float x, float y) {
		super(hdlr, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		
		speed=2;
		health=5;
		
		up = new Animation(hdlr,500,Assets.npcUp);
		left = new Animation(hdlr,500,Assets.npcLeft);
		down = new Animation(hdlr,500,Assets.npcDown);
		right = new Animation(hdlr,500,Assets.npcRight);
	}
	private void getMoveVar() {
		if (moveTick<1) {
			moveTick=rndInt(100);
			xMove=0;
			yMove=0;
			int p=rndInt(5);
			if (p==1) {
				xMove=speed;
			} else if (p==2) {
				xMove=-speed;
			} else if (p==3) {
				yMove=speed;
			} else if (p==4) {
				yMove=-speed;
			}
		} else {
			moveTick--;
		}
	}

	public void tick() {
		up.tick();
		left.tick();
		down.tick();
		right.tick();
		
		getMoveVar();
		move();
	}

	public void render(Graphics g) {
		//g.setColor(Color.RED);
		//g.fillRect((int)(x+bounds.x-hdlr.$camera().$xOffset()),(int)(y+bounds.y-hdlr.$camera().$yOffset()),bounds.width, bounds.height);
		g.drawImage(currentAnimation(),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),width,height,null);
	}
	private BufferedImage currentAnimation() {
		if(xMove<0) {
			return left.$currentFrame();
		} else if (xMove>0) {
			return right.$currentFrame();
		} else if (yMove<0) {
			return up.$currentFrame();
		} else {
			return down.$currentFrame();
		} 
	}
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
