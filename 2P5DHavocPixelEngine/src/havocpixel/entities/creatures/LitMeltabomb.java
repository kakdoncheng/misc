package havocpixel.entities.creatures;

import havocpixel.entities.Explosion;
import havocpixel.entities.InfernoFireball;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class LitMeltabomb extends LitGrenade{

	public LitMeltabomb(Handler handler, float x, float y, String owner, int dir) {
		super(handler, x, y, owner, dir);
		img=Assets.mb;
	}
	public void tick() {
		if(direction==0){
			if(entityCollision(0,0)){
				y-=3;
			}
			xMove=0;//1-(int)(Math.random()*3);
			yMove=speed;
		}else if(direction==1){
			if(entityCollision(0,0)){
				x-=3;
			}
			xMove=speed;
			yMove=0;
		}else if(direction==2){
			if(entityCollision(0,0)){
				y+=3;
			}
			xMove=0;
			yMove=-speed;
		}else{
			if(entityCollision(0,0)){
				x+=3;
			}
			xMove=-speed;
			yMove=0;
		}
		if(s>0)
			move();
		else
			s=move();
		if(s!=0)
			this.speed=0;
		fuse++;
		if(fuse>(50+(int)(Math.random()*101))||health<1){
			hdlr.$currentWorld().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.owner));
			for(float u=-5.5F;u<5.5F;u+=0.25F)
				hdlr.$currentWorld().em.addEntity(new InfernoFireball(hdlr,x,y,owner,0,true,true,u,5));
			for(float u=-5.5F;u<5.5F;u+=0.25F)
				hdlr.$currentWorld().em.addEntity(new InfernoFireball(hdlr,x,y,owner,1,true,true,u,5));
			for(float u=-5.5F;u<5.5F;u+=0.25F)
				hdlr.$currentWorld().em.addEntity(new InfernoFireball(hdlr,x,y,owner,2,true,true,u,5));
			for(float u=-5.5F;u<5.5F;u+=0.25F)
				hdlr.$currentWorld().em.addEntity(new InfernoFireball(hdlr,x,y,owner,3,true,true,u,5));
			active=false;
		}
		
	}
	

}
