package havocpixel.entities.creatures;

import java.awt.Graphics;

import havocpixel.main.Handler;

public class IndestructibleTree extends Tree{

	public IndestructibleTree(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		// TODO Auto-generated constructor stub
	}
	public void tick() {
		this.AC=70+(int)(Math.random()*6);
		this.health=9999;
		
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
		g.drawImage(t,
				(int)(x-hdlr.$camera().$xOffset()),
				(int)(y-hdlr.$camera().$yOffset()),
				width,
				height,null);
	}

}
