package havocpixel.entities.creatures;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GasterBlaster extends Creature{
	private Animation up,left,down,right;
	private BufferedImage bu0,bl0,bd0,br0,bu1,bl1,bd1,br1;
	private boolean renderBlast=false;
	private String owner;

	public GasterBlaster(Handler hdlr, float x, float y,int dir) {
		super(hdlr, x, y, 64,64);
		direction=dir;
		bounds=new Rectangle(-1,-1,0,0);
		health=1;
		damage=1;
		label="UNIQUE;GB";
		owner="UNIQUE;sans";
		up=new Animation(hdlr,80,Assets.gbu);
		left=new Animation(hdlr,80,Assets.gbl);
		down=new Animation(hdlr,80,Assets.gbd);
		right=new Animation(hdlr,80,Assets.gbr);
		
		bu0=Assets.bu0;
		bl0=Assets.bl0;
		bd0=Assets.bd0;
		br0=Assets.br0;
		bu1=Assets.bu1;
		bl1=Assets.bl1;
		bd1=Assets.bd1;
		br1=Assets.br1;
	}

	int b=0;
	@Override
	public void tick() {
		up.tick();
		left.tick();
		down.tick();
		int u=right.tick();
		if(u>1&&u<10){
			renderBlast=true;
			attack();
		}else
			renderBlast=false;
		if(u>10){
			health=0;
			active=false;
		}
	}

	@Override
	public void render(Graphics g) {
		if(renderBlast){
			if(this.direction==0){
				g.drawImage((b%2!=0)?bd0:bd1,
						(int)(x+0-hdlr.$camera().$xOffset()),
						(int)(y+height-hdlr.$camera().$yOffset()),
						width,
						height*10,null);
			}else if(this.direction==1){
				g.drawImage((b%2!=0)?br0:br1,
						(int)(x+width-hdlr.$camera().$xOffset()),
						(int)(y+0-hdlr.$camera().$yOffset()),
						width*10,
						height,null);
			}else if(this.direction==2){
				g.drawImage((b%2!=0)?bu0:bu1,
						(int)(x+0-hdlr.$camera().$xOffset()),
						(int)(0-hdlr.$camera().$yOffset()),
						width,
						(int)y,null);
			}else{
				g.drawImage((b%2!=0)?bl0:bl1,
						(int)(0-hdlr.$camera().$xOffset()),
						(int)(y+0-hdlr.$camera().$yOffset()),
						(int)x,
						height,null);
			}
		}
		g.drawImage(currentAnimation(),
				(int)(x-hdlr.$camera().$xOffset()),
				(int)(y-hdlr.$camera().$yOffset()),
				64,
				64,null);
		b++;
		
	}
	void attack(){
		Rectangle ar;
		if(this.direction==0){
			//down
			ar=new Rectangle(32,320);
			ar.x=(int)x+16;
			ar.y=(int)y;
		}else if(this.direction==1){
			//right
			ar=new Rectangle(320,32);
			ar.x=(int)x;
			ar.y=(int)y+16;
		}else if(this.direction==2){
			//up
			ar=new Rectangle(32,(int)y+height);
			ar.x=(int)x+16;
			ar.y=(int)0;
		}else{
			//left
			ar=new Rectangle((int)x+width,32);
			ar.x=0;
			ar.y=(int)y+16;
		}
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.label.equals(owner)||e.particle)
				continue;
			if(e.$collisionBounds(0,0).intersects(ar)){
				for(int i=0;i<10+(Math.random()*11);i++)
					e.damage(damage,this.owner,true);
			}
		}
	}
	protected BufferedImage currentAnimation() {
		if(this.direction==0)
			return down.$currentFrame();
		else if(this.direction==1)
			return right.$currentFrame();
		else if(this.direction==2)
			return up.$currentFrame();
		else
			return left.$currentFrame();
	}
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
