package havocpixel.entities.basic;

import java.awt.Graphics;

import havocpixel.Game;
import havocpixel.entities.Entity;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

public class Bloodstain extends Entity{

	private double dx, dy;
	private double time, thres;
	private double a;
	public Bloodstain(Game game, double x, double y, boolean fixed) {
		this(game, x, y);
		dx=x;
		dy=y;
	}
	public Bloodstain(Game game, double x, double y) {
		super(game, x, y);
		//this.x-=game.$width();
		this.y-=game.$height();
		name="Bloodstain";
		dead=true;
		ghost=true;
		active=true;
		img=CoreAssets.blood[game.$randomInt(0,CoreAssets.blood.length)];
		dx=x+game.$randomDouble(-16, 16);
		dy=y+game.$randomDouble(0, 16);
		time=0;
		thres=5;
		a=1.1;
	}
	public void update(double dt) {
		time+=dt;
		if(time>thres){
			a-=dt;
		}
		if(a<0){
			active=false;
		}
	}

	public void render(Graphics g) {
		if(a<1)
			Utils.drawTranslucentImage(img,
					(int)(dx-game.$camera().$xOffset()),
					(int)(dy-game.$camera().$yOffset()),(float)a,
					32,
					32, g);
		else
			g.drawImage(img,
					(int)(dx-game.$camera().$xOffset()),
					(int)(dy-game.$camera().$yOffset()),
					32,
					32,null);
	}

	public void die() {
		
	}

}
