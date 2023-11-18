package havocpixel.entities;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Icebolt extends Fireball{

	public Icebolt(Handler handler, float x, float y, String owner, int dir) {
		super(handler, x, y, owner, dir,false);
		damage=315;
		speed=9;
		health=400;
		if(direction==0){
			xSpeed=0;//1-(int)(Math.random()*3);
			ySpeed=speed;
		}else if(direction==1){
			xSpeed=speed;
			ySpeed=0;
		}else if(direction==2){
			xSpeed=0;
			ySpeed=-speed;
		}else{
			xSpeed=-speed;
			ySpeed=0;
		}
		this.label=new String("PROJECTILE:PLASMABOLT");
		img=Assets.icebolt;
	}

}
