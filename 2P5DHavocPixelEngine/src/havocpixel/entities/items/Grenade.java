package havocpixel.entities.items;

import havocpixel.entities.creatures.LitGrenade;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Grenade extends Item{

	public Grenade(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.gren, "Grenade", amt, 1.0F);
		// TODO Auto-generated constructor stub
		desc[0]="Desc: An explosive device that will detonate ";
		desc[1]="after a set amount of time when the firing pin ";
		desc[2]="is pulled. Problem is there's no way of knowing ";
		desc[3]="what that time is. ";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		weapon=true;
		item=true;
		//qLim=16;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		if(dir==0){
			//down
			hdlr.$currentWorld().em.addEntity(
					new LitGrenade(hdlr,tx,ty+32,owner,dir));
		}else if(dir==1){
			//right
			hdlr.$currentWorld().em.addEntity(
					new LitGrenade(hdlr,tx+32,ty,owner,dir));
		}else if(dir==2){
			//up
			hdlr.$currentWorld().em.addEntity(
					new LitGrenade(hdlr,tx,ty-32,owner,dir));
		}else{
			//left
			hdlr.$currentWorld().em.addEntity(
					new LitGrenade(hdlr,tx-32,ty,owner,dir));
		}
		this.quantity--;
		
	}

}
