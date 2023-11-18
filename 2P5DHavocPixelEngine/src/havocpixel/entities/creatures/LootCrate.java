package havocpixel.entities.creatures;

import havocpixel.entities.Explosion;
import havocpixel.entities.FloatingString;
import havocpixel.entities.items.AdrenalineShot;
import havocpixel.entities.items.HealthPotion;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class LootCrate extends Creature{

	protected BufferedImage crate=Assets.dc[0];
	protected float fx,fy;
	protected boolean opening=false,opened=false;
	public LootCrate(Handler hdlr,float x,float y) {
		super(hdlr, x, y, 32, 32);
		health=1;
		label="LOOTCRATE";
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		fx=x;
		fy=y;
		object=true;
	}

	int h=0;
	@Override
	public void tick() {
		x=fx;
		y=fy;
		if(health<150&&!opening&&target.equals("PLAYER")){
			opening=true;
		}
		if(opening&&!opened){
			dropItem();
			opened=true;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if(hurt){
			g.drawImage(crate,
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
			return;
		}
		g.drawImage(crate,
				(int)(x-hdlr.$camera().$xOffset()),
				(int)(y-hdlr.$camera().$yOffset()),
				width,
				height,null);
	}
	
	private void dropItem(){
		if(Math.random()<0.1){
			hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,this.x,this.y,"Trapped!",Color.WHITE,12));
			hdlr.$currentWorld().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.label));
		}
		hdlr.$currentWorld().em.addEntity(new HealthPotion(hdlr,this.x+(int)((Math.random()*11)-5),this.y+(int)((Math.random()*11)-5),1+(int)(Math.random()*4)));
		hdlr.$currentWorld().em.addEntity(new AdrenalineShot(hdlr,this.x+(int)((Math.random()*11)-5),this.y+(int)((Math.random()*11)-5),1));
	}

	boolean dying=false;
	@Override
	public void die() {
		//spawnStoneGibs();
		if(!dying){
			spawnWoodGibs();
		}
		dying=true;
		
		if(!opened){
			dropItem();
		}
	}

}
