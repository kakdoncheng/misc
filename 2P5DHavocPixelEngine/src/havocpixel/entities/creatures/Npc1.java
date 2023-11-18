package havocpixel.entities.creatures;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Npc1 extends Creature{
	private Animation up, left, down, right;
	//private int moveTick=31;
	public Npc1(Handler hdlr, float x, float y) {
		super(hdlr, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 19;
		this.speed=2;
		
		up = new Animation(hdlr,500,Assets.npc1Up);
		left = new Animation(hdlr,500,Assets.npc1Left);
		down = new Animation(hdlr,500,Assets.npc1Down);
		right = new Animation(hdlr,500,Assets.npc1Right);
		yMove=speed;
	}
	private void getMoveVar() {
		
		
		/*
		int p=30;
		moveTick++;
		if (moveTick>p) {
			//turn around
			if (hitSolidObject) {
				if(xMove<0) {
					if (moveTick>p) {
						xMove=speed;
						yMove=0;
					} else {
						this.y+=-speed;
					}
				} else if (xMove>0) {
					if (moveTick>p) {
						xMove=-speed;
						yMove=0;
					} else {
						this.y+=speed;
					}
				} else if (yMove<0) {
					if (moveTick>p) {
						xMove=0;
						yMove=speed;
					} else {
						this.x+=-speed;
					}
				} else if (yMove>0){
					if (moveTick>p) {
						xMove=0;
						yMove=-speed;
					} else {
						this.x+=speed;
					}
				} 
				hitSolidObject=false;
			}
			//check left side
			if(xMove<0&&!hdlr.$world().getTile((int)(this.x)/Tile.TILE_WIDTH,(int)(this.y+32)/Tile.TILE_HEIGHT).impassable()) {
				System.out.println("true down");
				xMove=0;
				yMove=speed;
				moveTick=0;
			} else if (xMove>0&&!hdlr.$world().getTile((int)(this.x)/Tile.TILE_WIDTH,(int)(this.y-32)/Tile.TILE_HEIGHT).impassable()) {
				System.out.println("true up");
				xMove=0;
				yMove=-speed;
				moveTick=0;
			} else if (yMove<0&&!hdlr.$world().getTile((int)(this.x-32)/Tile.TILE_WIDTH,(int)(this.y)/Tile.TILE_HEIGHT).impassable()) {
				System.out.println("true left");
				xMove=-speed;
				yMove=0;
				moveTick=0;
			} else if (yMove>0&&!hdlr.$world().getTile((int)(this.x+32)/Tile.TILE_WIDTH,(int)(this.y)/Tile.TILE_HEIGHT).impassable()){
				System.out.println("true right");
				xMove=speed;
				yMove=0;
				moveTick=0;
			} 
			
			
		}*/
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