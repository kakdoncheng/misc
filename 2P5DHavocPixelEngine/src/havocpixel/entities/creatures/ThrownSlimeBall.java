package havocpixel.entities.creatures;

import java.awt.Graphics;

import havocpixel.entities.Smoke;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class ThrownSlimeBall extends LitGrenade{

	public ThrownSlimeBall(Handler handler, float x, float y, String owner,
			int dir) {
		super(handler, x, y, owner, dir);
		label="PROJECTILE:SLIME";
		img=Assets.slime;
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
		if(s!=0){
			fuse=300;
			this.speed=0;
		}
		fuse++;
		if(fuse>(50+(int)(Math.random()*101))||health<1){
			hdlr.$currentWorld().em.addEntity(new GelatinousCube(hdlr,this.x,this.y,"GELATINOUS_CUBE_"+Math.random(),2));
			active=false;
		}
		
	}
	public void render(Graphics g) {
		int y=2;
		if(fuse<5){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+0),null);
		}else if(fuse<10){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+1),null);
		}else if(fuse<15){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+2),null);
		}else if(fuse<20){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+4),null);
		}else if(fuse<25){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+6),null);
		}else if(fuse<30){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<35){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+7),null);
		}else if(fuse<40){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+5),null);
		}else if(fuse<45){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+4),null);
		}else if(fuse<50){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+6),null);
		}else if(fuse<55){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+8),null);
		}else if(fuse<60){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
			this.speed=2;
		}else if(fuse<65){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+8),null);
		}else if(fuse<70){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+7),null);
		}else if(fuse<75){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+8),null);
		}else if(fuse<80){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+9),null);
		}else if(fuse<85){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<90){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+11),null);
		}else if(fuse<95){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+10),null);
		}else if(fuse<100){
			this.speed=1;
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+11),null);
		}else if(fuse<105){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<110){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+11),null);
		}else if(fuse<115){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<120){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<125){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<130){
			this.speed=0;
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<135){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<140){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<145){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<150){
			g.drawImage(img,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}
	}
	
}
