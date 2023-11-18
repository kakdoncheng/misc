package havocpixel.entities;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Graphics;

public class KoolAid extends FloatingString{

	private float dx,dy;
	protected int p=1;
	protected float e=1.0F;
	private int px0=1;
	protected int lim=0;
	protected float a=1.0F;
	
	protected boolean alt=false;
	private int u=(int)(Math.random()*Assets.blood.length);
	
	public KoolAid(Handler handler, float x, float y) {
		super(handler,x+16,y+16,"FFF",new Color(0x800000),12);
		//System.out.print("KOOL AID");
		duration=0;
		label="KOOLAID";
		if(Math.random()<0.65F)
			p=1+(int)(Math.random()*3);
		if(Math.random()<0.2F)
			c=new Color(0x600000);
		lim=(int)(Math.random()*91);
		// TODO Auto-generated constructor stub
	}
	boolean landed=false;
	public void tick(){
		if(duration<25){
			dx=(float)(Math.random()*2-1)*2;
			dy=-(float)Math.random()*2+1;
		}
		if(duration>13&&!landed){
			px0=p;
			landed=true;
		}
		if(duration<13){
			x+=dx;
			dy+=0.12F*duration;
			y+=dy;
		}else if(duration<13){
			x+=dx;
		}
		if(duration>(660-lim)){//((hdlr.$game().$FPS()>59)?360-lim:((hdlr.$game().$FPS()*5)-(lim/2)))){
			if(px0!=1){
				if(duration%5==0)
					px0--;
			}else{
				if(alt)
					active=false;
				else{
					a-=0.02F;
					if(a<0.02F)
						active=false;
				}
			}
			
		}
		duration++;
	}
	public void render(Graphics g){
		if(alt){
			g.setColor(c);
			if(!landed){
				g.fillRect((int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),px0,px0);
			}else{
				if(lim%2!=0){
					g.fillRect((int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),px0,px0);
					if(px0>2){
						//g.fillOval((int)(x-hdlr.$camera().$xOffset())-1,(int)(y-hdlr.$camera().$yOffset())-1,px0+2,px0+2);
					}
				}else{
					g.fillOval((int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),px0+(px0!=1?(px0/3):0),px0);
				}
			}
		}else{
			g.setColor(c);
			if(!landed){
				label="LABEL:FLOATING_STRING";
				g.fillOval((int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()),p,p+2);
			}else{
				label="KOOLAID";
				if(p!=3){
					active=false;
					return;
				}
				if(a<1)
				Utils.drawTranslucentImage(Assets.blood[u],
						(int)(x-hdlr.$camera().$xOffset())-16,
						(int)(y-hdlr.$camera().$yOffset())-16,a,
						32,
						32, g);
				else
				g.drawImage(Assets.blood[u],
						(int)(x-hdlr.$camera().$xOffset())-16,
						(int)(y-hdlr.$camera().$yOffset())-16,
						32,
						32,null);
			}
		}
		
	}

}
