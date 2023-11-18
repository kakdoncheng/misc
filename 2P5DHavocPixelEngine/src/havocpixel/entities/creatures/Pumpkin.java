package havocpixel.entities.creatures;

import havocpixel.entities.Explosion;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Pumpkin extends Tombstone{
	public Pumpkin(Handler handler, float x, float y) {
		super(handler, x, y);
		health=150;
		label="PUMPKIN";
		this.AC=0;
		int u=(int)(Math.random()*4);
		if(u==0||u==2){
			s0=Assets.p0;
			s1=Assets.p0;
			s2=Assets.p0;
			sh0=Assets.ph0;
			sh1=Assets.ph0;
			sh2=Assets.ph0;
		}else if(u==1){
			s0=Assets.p1;
			s1=Assets.p1;
			s2=Assets.p1;
			sh0=Assets.ph1;
			sh1=Assets.ph1;
			sh2=Assets.ph1;
		}else if(u==3){
			s0=Assets.p2;
			s1=Assets.p2;
			s2=Assets.p2;
			sh0=Assets.ph1;
			sh1=Assets.ph1;
			sh2=Assets.ph1;
		}
	}

	@Override
	public void die() {
		hdlr.$currentWorld().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.label));
	}

}
