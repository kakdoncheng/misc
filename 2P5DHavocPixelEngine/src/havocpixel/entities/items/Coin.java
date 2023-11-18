package havocpixel.entities.items;

import java.awt.Color;

import havocpixel.Timer;
import havocpixel.entities.FloatingString;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Coin extends Item{
	public Coin(Handler handler, float x, float y,int amt) {
		super(handler, x, y, Assets.bGib, "Hellish Dranchma", amt, 0.01F);
		desc[0]="Desc: Coins stamped from a metal which appears to always";
		desc[1]="be harshly cold to the touch. Due to its rarity, knives";
		desc[2]="are generally used as currency instead. Even your rank in";
		desc[3]="hell is determined by money it seems.";
		weapon=false;
		item=true;
		qLim=999999;
	}
	@Override
	public void tick() {
		active=false;
		if(pickup){
			fireTick=0;
		}
		if(hdlr.$player().godMode&&!pickup)
			active=false;
		if(isEmpty())
			active=false;
		if(quantity>999){
			img=Assets.coin[4];
		}else if(quantity>99){
			img=Assets.coin[3];
		}else if(quantity>9){
			img=Assets.coin[2];
		}else if(quantity>2){
			img=Assets.coin[1];
		}else{
			img=Assets.coin[0];
		}
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
	public void useItem(int tx, int ty, int dir, String owner) {
		// TODO Auto-generated method stub
	}

}
