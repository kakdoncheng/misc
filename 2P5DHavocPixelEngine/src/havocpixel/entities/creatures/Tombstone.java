package havocpixel.entities.creatures;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tombstone extends Creature{

	protected BufferedImage s0,s1,s2,sh0,sh1,sh2;
	protected float fx,fy;
	public Tombstone(Handler hdlr, float x, float y) {
		super(hdlr, x, y, 32, 32);
		health=500;
		label="TOMBSTONE";
		object=true;
		immovable=true;
		bounds.x = 8;
		bounds.y = 10;
		bounds.width = 15;
		bounds.height = 21;
		fx=x;
		fy=y;
		this.AC=75;
		int u=(int)(Math.random()*4);
		if(u==0||u==2){
			s0=Assets.s0;
			s1=Assets.s1;
			s2=Assets.s2;
			sh0=Assets.sh0;
			sh1=Assets.sh1;
			sh2=Assets.sh2;
		}else if(u==1){
			s0=Assets.s00;
			s1=Assets.s01;
			s2=Assets.s02;
			sh0=Assets.sh00;
			sh1=Assets.sh01;
			sh2=Assets.sh02;
		}else if(u==3){
			s0=Assets.s10;
			s1=Assets.s11;
			s2=Assets.s12;
			sh0=Assets.sh10;
			sh1=Assets.sh11;
			sh2=Assets.sh12;
		}
	}

	@Override
	public void tick() {
		if(hurt){
			attacking=false;
			if(damageTick<DL){
				damageTick++;
				return;
			}else{
				damageTick=0;
				hurt=false;
			}
		}
		if(health==0){
			active=false;
		}
	}

	int d=2;
	@Override
	public void render(Graphics g) {
		x=fx;
		y=fy;
		if(hurt){
			if(health>301){
				g.drawImage(sh0,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height,null);
			}else if(health>151){
				if(d==2){
					d--;
					//spawnStoneGibs();
				}
				g.drawImage(sh1,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height,null);
			}else if(health>1){
				if(d==1){
					d--;
					//spawnStoneGibs();
				}
				g.drawImage(sh2,
						(int)(x-hdlr.$camera().$xOffset()),
						(int)(y-hdlr.$camera().$yOffset()),
						width,
						height,null);
			}
			return;
		}
		if(health>301){
			g.drawImage(s0,
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
		}else if(health>151){
			g.drawImage(s1,
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
		}else if(health>1){
			g.drawImage(s2,
					(int)(x-hdlr.$camera().$xOffset()),
					(int)(y-hdlr.$camera().$yOffset()),
					width,
					height,null);
		}
		
	}

	boolean dying=false;
	@Override
	public void die() {
		if(!dying){
			spawnStoneGibs();
			dying=true;
		}
		//spawnStoneGibs();
	}

}
