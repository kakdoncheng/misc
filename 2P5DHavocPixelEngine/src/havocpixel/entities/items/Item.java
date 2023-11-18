package havocpixel.entities.items;

import havocpixel.Timer;
import havocpixel.entities.Entity;
import havocpixel.entities.FloatingString;
import havocpixel.main.Handler;




import java.awt.Color;
import java.awt.Font;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Item extends Entity{

	protected BufferedImage img;
	public int quantity,qLim;
	protected float weight=0.0F;
	protected float dy=0.15F;
	protected int t=0;
	protected int duration=0;
	protected String desc[];
	protected boolean inInventory=false,weapon=false,charged=false,pickup=false;
	protected int charges=0;
	public boolean empty=false;
	public Item(Handler handler, float x, float y,BufferedImage i,String label,int amt,float weight) {
		super(handler, x, y, 32, 32, 10000);
		bounds=new Rectangle(1,1,30,30);
		health=1;
		object=true;
		item=true;
		this.label=label;
		img=i;
		quantity=amt;
		qLim=655535;
		this.weight=weight;
		desc=new String[4];
		desc[0]="Desc: Nothing to look at here.";
		desc[1]=" ";
		desc[2]=" ";
		desc[3]=" ";
	}

	@Override
	public void tick() {
		//no items
		if(!this.label.equals("Energy"))
			active=false;
		if(pickup){
			fireTick=0;
		}
		if(hdlr.$player().godMode&&!pickup)
			active=false;
		if(isEmpty())
			active=false;
		if(!inInventory){
			duration++;
			if(duration>3600)
				active=false;
			if(t>59){
				t=1;
			}else{
				t++;
			}
			if(t>30){
				y+=dy;
			}else{
				y-=dy;
			}
			if(this.$collisionBounds(0,0).intersects(hdlr.$player().$collisionBounds(0,0))){
				if(pickup){
					onPickup();
					//hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,x,y,"Picked Up "+label,Color.WHITE,12));
					this.active=false;
				}else{
					if(isOnFire())
						hdlr.$currentWorld().em.player.setOnFire();
					if(hdlr.$inv().addItem(this)!=1){
						duration=0;
						inInventory=true;
						this.active=false;
						if(!isEmpty()){
							hdlr.$player().resetPIT();
							hdlr.$player().pItem=new String(label+" ("+quantity+")");
							fixBounds();
							hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,x,y,label+" +"+quantity,Color.WHITE,12));
							System.out.print("["+Timer.time()+"] Added Item to Inventory: "+this+"\n");
						}
						setCD(0,0);
					}else{
						if(t%30==0)
							hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,x,y,"Inventory Full!",Color.WHITE,12));
						System.out.print("["+Timer.time()+"] Inventory Full! Failed to add: "+this+"\n");
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		render(g,(int)(this.x-hdlr.$camera().$xOffset()),(int)(this.y-hdlr.$camera().$yOffset()));
	}
	public void render(Graphics g,int tx,int ty) {
		if(!(duration>3300&&(duration%20)<10))
			g.drawImage(img,tx,ty,null);
		if(inInventory){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Lucida Console", Font.BOLD, 10));
			g.drawString(quantity+"",tx+32-g.getFontMetrics().stringWidth(quantity+""),ty+32);
			if(charged){
				g.setColor(charges<4?Color.RED:Color.GREEN);
				g.setFont(new Font("Lucida Console", Font.BOLD, 10));
				g.drawString(charges+"",tx+1,ty+32);
				g.setColor(Color.WHITE);
			}
		}
		
		//g.setColor(Color.RED);
		//g.fillRect(tx+2,ty+2,28,28);
		//if(!isEmpty())
		//	g.drawImage(img,tx,ty,null);
	}
	public float $weight(){
		return quantity*weight;
	}

	public boolean isEmpty(){
		return quantity<1||(charged&&charges<1);
	}
	public boolean isWeapon(){
		return weapon;
	}
	public String[] $desc(){
		return desc;
	}
	public BufferedImage $img(){
		return img;
	}
	public void setCD(float dx,float dy){
		x=dx;
		y=dy;
	}
	public void fixBounds(){
		bounds=new Rectangle((int)hdlr.$game().$currentScaleWidth(),(int)hdlr.$game().$currentScaleHeight(),(int)(30*hdlr.$game().$currentScaleWidth()),(int)(30*hdlr.$game().$currentScaleHeight()));
		inInventory=true;
	}
	public abstract void useItem(int tx,int ty,int dir,String owner);
	public void onPickup(){
		
	}
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
