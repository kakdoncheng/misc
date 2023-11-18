package havocpixel.entities.items;

import havocpixel.entities.BearTrap;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class BearTrapItem extends Item{

	public BearTrapItem(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.bt0[0], "Bear Trap", amt, 10.0F);
		desc[0]="Desc: A heavily modified bear trap that tears a ";
		desc[1]="new asshole to anything that is stupid enough to ";
		desc[2]="step on it. The modded spring is so strong that";
		desc[3]="it will tear the trap apart after it goes off. ";
		weapon=true;
		item=true;
		//qLim=2;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		if(dir==0){
			//down
			hdlr.$currentWorld().em.addEntity(
					new BearTrap(hdlr,tx,ty+32,owner));
		}else if(dir==1){
			//right
			hdlr.$currentWorld().em.addEntity(
					new BearTrap(hdlr,tx+32,ty,owner));
		}else if(dir==2){
			//up
			hdlr.$currentWorld().em.addEntity(
					new BearTrap(hdlr,tx,ty-32,owner));
		}else{
			//left
			hdlr.$currentWorld().em.addEntity(
					new BearTrap(hdlr,tx-32,ty,owner));
		}
		this.quantity--;
		
	}
}
