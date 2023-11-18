package havocpixel.entities;

import java.awt.Graphics;

import havocpixel.Game;
import havocpixel.gfx.Animation;
import havocpixel.gfx.CoreAssets;

public class WarpJump extends Entity{

	private Entity passenger;
	private double dx, dy, time;
	private boolean jumped, deployed;
	public WarpJump(Game game, double x, double y, double dx, double dy, Entity passenger, boolean spawning, boolean jumped, double accuracy) {
		super(game, x, y);
		name="Warp Jump Frag";
		invulnerable=true;
		dead=true;
		active=true;
		spawn=new Animation(CoreAssets.warpR,0.6,true);
		death=new Animation(CoreAssets.warp,0.6,true);
		this.passenger=passenger;
		this.passenger.active=false;
		this.passenger.spawning=spawning;
		double error=(1-accuracy)*(dx+dy);
		this.dx=dx+game.$randomDouble(-error, error);
		this.dy=dy+game.$randomDouble(-error, error);
		this.jumped=jumped;
		this.deployed=false;
	}

	public void update(double dt) {
		time+=dt;
		spawn.update(dt);
		if(spawn.$currentIndex()>2){
			passenger.setXY(x+dx, y+dy);
		}
		death.update(dt);
		if(death.$currentIndex()>4&&!deployed){
			passenger.active=true;
			game.$currentWorld().$entityManager().addEntity(passenger);
			deployed=true;
		}
		if(time>death.$speed()){
			die();
		}
	}

	public void render(Graphics g) {
		if(!jumped){
			if(spawn.$currentIndex()<3){
				passenger.render(g);
			}
			g.drawImage(spawn.$currentFrame(), 
					(int)(x-game.$camera().$xOffset()),
					(int)(y-game.$camera().$yOffset()),
					this.width,
					this.height,
					null);
		}
		if(death.$currentIndex()>3&&!deployed){
			passenger.render(g);
		}
		g.drawImage(death.$currentFrame(), 
				(int)(x+dx-game.$camera().$xOffset()),
				(int)(y+dy-game.$camera().$yOffset()),
				this.width,
				this.height,
				null);
	}
	public void renderOverlay(Graphics g){
	}

	public void die() {
		active=false;
	}

}
