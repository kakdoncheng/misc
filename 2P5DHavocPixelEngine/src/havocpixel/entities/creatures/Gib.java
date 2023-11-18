package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Gib extends Creature{
	protected BufferedImage gib;
	protected int dDir;
	protected boolean bloody=true;
	protected int s=0;
	protected String owner;
	protected int fuse=0;
	public Gib(Handler handler, float x, float y) {
		super(handler, x, y,32,32);
		bounds=new Rectangle(12,12,8,8);
		//bounds=new Rectangle(0,0,0,0);
		health=1;
		damage=25;
		speed=1+(int)(Math.random()*2);
		label="Gib";
		gib=(Math.random()<0.4F)?Assets.gib0:Assets.gib3;
		dDir=(int)(Math.random()*8);
		this.particle=true;
		this.object=true;
		//this.item=true;
	}
	int t=(int)(Math.random()*101);
	@Override
	public void tick() {
		if(fuse%5==0&&fuse<50+t-1)
			gib=Utils.rotate(gib,90);
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
			//	y+=3;
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
			//	x-=3;
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
			move();
		else
			s=move();
		if(s!=0)
			this.speed=0;
		if(fuse>(50+t-1)){
			speed=0;
		}
		if(fuse>(50+t)){//||health<1){
			//hdlr.$world().em.addEntity(new Explosion(hdlr,this.x-16,this.y-16,this.owner));
			active=false;
		}else{
			if(bloody&&fuse<50+t&&speed!=0)
				trailKoolAid();
			fuse++;
		}
	}
	@Override
	public void render(Graphics g) {
		if(fuse<5){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+0),null);
		}else if(fuse<10){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+1),null);
		}else if(fuse<15){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+2),null);
		}else if(fuse<20){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+4),null);
		}else if(fuse<25){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+6),null);
		}else if(fuse<30){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<35){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+7),null);
		}else if(fuse<40){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+5),null);
		}else if(fuse<45){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+4),null);
		}else if(fuse<50){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+6),null);
		}else if(fuse<55){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+8),null);
		}else if(fuse<60){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
			if(speed!=0)
				this.speed--;
		}else if(fuse<65){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+8),null);
		}else if(fuse<70){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+7),null);
		}else if(fuse<75){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+8),null);
		}else if(fuse<80){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+9),null);
		}else if(fuse<85){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<90){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+11),null);
		}else if(fuse<95){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+10),null);
		}else if(fuse<100){
			if(speed!=0)
				this.speed--;
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+11),null);
		}else if(fuse<105){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<110){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+11),null);
		}else if(fuse<115){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<120){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<125){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<130){
			this.speed=0;
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<135){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<140){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<145){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}else if(fuse<150){
			g.drawImage(gib,(int)(x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()+12),null);
		}
		//if(s!=0)
			//this.speed=0;
	}
	@Override
	public void die() {
		// TODO Auto-generated method stub
		System.out.print("GIBBED");
	}
}
