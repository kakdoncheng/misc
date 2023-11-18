package havocpixel.entities.items;

import havocpixel.entities.creatures.LitGrenade;
import havocpixel.entities.creatures.ThrownSlimeBall;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class SlimeBall extends Item{
	public SlimeBall(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.slime, "Slime Ball", amt, 1.0F);
		desc[0]="Desc: A ball of disgusting green slime. You have";
		desc[1]="no fucking clue why you even bothered to even pick";
		desc[2]="it up. It smells noxious and seriously burns to the";
		desc[3]="touch.";
		weapon=true;
		item=true;
		//qLim=16;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		if(dir==0){
			//down
			hdlr.$currentWorld().em.addEntity(
					new ThrownSlimeBall(hdlr,tx,ty+32,owner,dir));
		}else if(dir==1){
			//right
			hdlr.$currentWorld().em.addEntity(
					new ThrownSlimeBall(hdlr,tx+32,ty,owner,dir));
		}else if(dir==2){
			//up
			hdlr.$currentWorld().em.addEntity(
					new ThrownSlimeBall(hdlr,tx,ty-32,owner,dir));
		}else{
			//left
			hdlr.$currentWorld().em.addEntity(
					new ThrownSlimeBall(hdlr,tx-32,ty,owner,dir));
		}
		this.quantity--;
		
	}
}
