package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sign extends Creature{
	protected BufferedImage t,th;
	protected float fx,fy;
	private Rectangle r;
	public Sign(Handler hdlr, float x, float y) {
		super(hdlr, x, y, 32, 32);
		health=50;
		label="SIGN";
		object=true;
		immovable=true;
		bounds.x = 14;
		bounds.y = 21;
		bounds.width = 4;
		bounds.height = 8;
		r=new Rectangle((int)this.x,(int)this.y+8,32,32);
		fx=x;
		fy=y;
		this.AC=70;
		int u=(int)(Math.random()*3);
		t=Assets.sign[u];
		th=Assets.sign[u];
	}

	@Override
	public void tick() {
		this.AC=70+(int)(Math.random()*6);
		if(hdlr.$player().$collisionBounds(0,0).intersects(r)){
			hdlr.$currentWorld().playerCanInspect();
			if(hdlr.$km().T&&!hdlr.$currentWorld().isTalking()){
				hdlr.$currentWorld().talk("Sign", "The sign reads: \"GO FUCK YOURSELF.\"");
			}
		}
		if(hurt){
			attacking=false;
			if(damageTick<DL){
				damageTick++;
				return;
			}else{
				damageTick=0;
				hurt=false;
			}
		}
		if(health==0){
			active=false;
		}
		
	}

	@Override
	public void render(Graphics g) {
		x=fx;
		y=fy;
		if(hurt){
			g.drawImage(th,
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
			return;
		}
		g.drawImage(t,
				(int)(x-hdlr.$camera().$xOffset()),
				(int)(y-hdlr.$camera().$yOffset()),
				width,
				height,null);
	}

	boolean dying=false;
	@Override
	public void die() {
		if(!dying){
			spawnWoodGibs();
			//spawnWoodGibs();
			dying=true;
		}
	}

}
