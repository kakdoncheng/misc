package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

public class SkullGib extends Gib{

	public SkullGib(Handler handler, float x, float y) {
		super(handler, x, y);
		gib=Assets.gib1;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void tick() {
		if(fuse%5==0&&fuse<100)
			gib=Utils.rotate(gib,90);
		if(fuse<150&&bloody)
			trailKoolAid();
		if(dDir==0){
			//if(entityCollision(0,0)){
			//	y-=3;
			//}
			xMove=0;//1-(int)(Math.random()*3);
			yMove=speed;
		}else if(dDir==1){
			//if(entityCollision(0,0)){
			//	x-=3;
			//}
			xMove=speed;
			yMove=0;
		}else if(dDir==2){
			//if(entityCollision(0,0)){
			///	y+=3;
			//}
			xMove=0;
			yMove=-speed;
		}else if(dDir==3){
			//if(entityCollision(0,0)){
			//	x+=3;
			//}
			xMove=-speed;
			yMove=0;
		}else if(dDir==4){
			//if(entityCollision(0,0)){
			//	x+=3;
			//	y+=3;
			//}
			xMove=-speed;
			yMove=-speed;
		}else if(dDir==5){
			//if(entityCollision(0,0)){
			//	x+=3;
			//	y-=3;
			//}
			xMove=-speed;
			yMove=+speed;
		}else if(dDir==6){
			//if(entityCollision(0,0)){
			////	x-=3;
			//	y+=3;
			//}
			xMove=+speed;
			yMove=-speed;
		}else if(dDir==7){
			//if(entityCollision(0,0)){
			//	x-=3;
			//	y-=3;
			//}
			xMove=+speed;
			yMove=+speed;
		}
		if(s>0)
			;//move();
		else
			s=move();
		if(s!=0)
			this.speed=0;
		fuse++;
		if(fuse>(100+(int)(Math.random()*51))||health<1){
			//hdlr.$world().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.owner));
			active=false;
			knocked();
		}
		
	}
	protected void knocked(){
		
	}

}
