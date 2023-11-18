package havocpixel.entities.items;

import havocpixel.entities.DeathCurse;
import havocpixel.entities.Fireball;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Rectangle;

public class DeathWand extends Item{
	public DeathWand(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.wand2, "Wand of Death", amt, 1.0F);
		// TODO Auto-generated constructor stub
		desc[0]="Desc: A piece of wood embued with magical power.";
		desc[1]="The enscription on the wood says:";
		desc[2]="Yma tyh eflhs dan esobn be msonuedc;";
		desc[3]="";
		//desc=" Perfect for people who think ten fingers is too many to have.  ";
		weapon=true;
		item=true;
		charged=true;
		charges=7;
		qLim=1;
	}

	@Override
	public void useItem(int tx, int ty, int dir, String owner) {
		Rectangle ar;
		if(dir==0){
			//down
			ar=new Rectangle(128,320);
			ar.x=(int)tx-32;
			ar.y=(int)ty+32;
		}else if(dir==1){
			//right
			ar=new Rectangle(320,128);
			ar.x=(int)tx+32;
			ar.y=(int)ty-32;
		}else if(dir==2){
			//up
			ar=new Rectangle(128,320);
			ar.x=(int)tx-32;
			ar.y=(int)ty-320;
		}else{
			//left
			ar=new Rectangle(320,128);
			ar.x=(int)tx-320;
			ar.y=(int)ty-32;
		}
		for(havocpixel.entities.Entity u:hdlr.$currentWorld().$entityManager().$entities()){
			if(u.equals(this)||u.item||u.object||u.flora||u.particle)
				continue;
			if(u.$collisionBounds(0,0).intersects(ar)){
				hdlr.$currentWorld().$entityManager().addEntity(new DeathCurse(hdlr,tx,ty,owner,u.label));
			}
		}
		this.charges--;
		
	}

}
