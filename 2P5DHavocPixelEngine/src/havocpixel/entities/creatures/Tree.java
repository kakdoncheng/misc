package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tree extends Creature{
	protected BufferedImage t,th;
	protected float fx,fy;
	public Tree(Handler hdlr, float x, float y) {
		super(hdlr, x, y, 64, 96);
		health=500;
		label="TREE";
		object=true;
		immovable=true;
		bounds.x = 17;
		bounds.y = 64;
		bounds.width = 30;
		bounds.height = 32;
		fx=x;
		fy=y;
		this.AC=70;
		int u=(int)(Math.random()*4);
		if(u==0){
			t=Assets.t0;
			th=Assets.t1;
		}else if(u==1){
			t=Assets.th0;
			th=Assets.th1;
		}else if(u==2){
			t=Assets.t2;
			th=Assets.th2;
		}else if(u==3){
			t=Assets.t3;
			th=Assets.th3;
		}else{
			t=Assets.t0;
			th=Assets.t1;
		}
	}

	@Override
	public void tick() {
		this.AC=70+(int)(Math.random()*6);
		if(Math.random()<0.0001F){
			if(!entityCollision(0,-32)){
				hdlr.$currentWorld().em.addEntity(new Bunny(hdlr,this.x+16,this.y+32));
			}
		}
		if(hurt){
			spawnWoodChip();
			hurt=false;
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

	boolean dying=false;
	@Override
	public void die() {
		if(!dying){
			spawnWoodGibs();
			spawnWoodGibs();
			dying=true;
		}
	}

}
