package havocpixel.entities.items;

import havocpixel.entities.creatures.LitMeltabomb;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Meltabomb extends Item{
	public Meltabomb(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.mb, "Melta Bomb", amt, 14.4F);
		// TODO Auto-generated constructor stub
		desc[0]="Desc: Some mad scientist found a way to combine";
		desc[1]="napalm, thermite, and the magical energy of nuclear";
		desc[2]="fusion to create this nightmare. No one really knows what";
		desc[3]="it does because everyone who used it died trying.";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		weapon=true;
		item=true;
		qLim=1;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		if(dir==0){
			//down
			hdlr.$currentWorld().em.addEntity(
					new LitMeltabomb(hdlr,tx,ty+32,owner,dir));
		}else if(dir==1){
			//right
			hdlr.$currentWorld().em.addEntity(
					new LitMeltabomb(hdlr,tx+32,ty,owner,dir));
		}else if(dir==2){
			//up
			hdlr.$currentWorld().em.addEntity(
					new LitMeltabomb(hdlr,tx,ty-32,owner,dir));
		}else{
			//left
			hdlr.$currentWorld().em.addEntity(
					new LitMeltabomb(hdlr,tx-32,ty,owner,dir));
		}
		this.quantity--;
		
	}
}
