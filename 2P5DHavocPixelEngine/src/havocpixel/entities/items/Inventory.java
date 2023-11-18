package havocpixel.entities.items;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Inventory {
	private Handler hdlr;
	private Item[][] items;
	private Item[] qub;
	//private Item offhand;
	public Item currentSel,temp;
	private BufferedImage img;
	private int iQ=0;
	private String hover="<NOTHING SELECTED>";
	private int iy=3,ix=18,invx=128,invy=110,offX=7,offY=211
			,qubLim=2,qudx=99;
	//mouse swap
	private boolean hold=false;

	//add mouse handling input
	//add offhand & quickuse slots
	//add inventory screen to render
	
	public Inventory(Handler hdlr){
		this.hdlr=hdlr;
		invx=(hdlr.$game().$width()-639)/2;
		invy=(hdlr.$game().$height()-319)/2;
		items=new Item[iy][ix];
		qub=new Item[qubLim];
		//offhand=new EmptyItem(hdlr,0,0);
		for(int r=0;r<qubLim;r++){
			qub[r]=new EmptyItem(hdlr,0,0);//2+(32*r)+invx+offX,2+invy+offY
		}
		for(int u=0;u<iy;u++){
			for(int j=0;j<ix;j++){
					items[u][j]=new EmptyItem(hdlr,0,0);
			}
		}
		currentSel=new EmptyItem(hdlr,0,0);
		giftPlayer();
		//y,x
	}
	public Item $qu0(){
		return qub[0];
	}
	public Item $qu1(){
		return qub[1];
	}
	public void reset$qu0(){
		qub[0]=new EmptyItem(hdlr,0,0);
	}
	public void reset$qu1(){
		qub[1]=new EmptyItem(hdlr,0,0);
	}
	public float $invWeight(){
		float w=0;
		for(int r=0;r<qubLim;r++){
			w+=qub[r].weight;//2+(32*r)+invx+offX,2+invy+offY
		}
		for(int u=0;u<iy;u++){
			for(int j=0;j<ix;j++){
					w+=items[u][j].weight;
			}
		}
		return w;
	}
	public void giftPlayer(){
		addItem(new ExplosiveThrowingKnife(hdlr,0,0,100));
		addItem(new PoisonedThrowingKnife(hdlr,0,0,100));
		addItem(new AdrenalineShot(hdlr,0,0,1));
		//addItem(new BearTrapItem(hdlr,0,0,2));
		//addItem(new ThrowingKnife(hdlr,0,0,999));
		//addItem(new Grenade(hdlr,0,0,5));
		//addItem(new BowlOfRice(hdlr,0,0,1));
		//addItem(new FireWand(hdlr,0,0,1));
		//addItem(new IceWand(hdlr,0,0,1));
		addItem(new SlimeBall(hdlr,0,0,16));
		addItem(new DeathWand(hdlr,0,0,1));
		addItem(new RealHealthPotion(hdlr,0,0,3));
		addItem(new Meltabomb(hdlr,0,0,1));
		for(int i=0;i<qubLim;i++)
			if(qub[i]!=null)
				qub[i].fixBounds();
		for(int u=0;u<iy;u++){
			for(int j=0;j<ix;j++){
				if(items[u][j]!=null)
					items[u][j].fixBounds();
			}
		}
	}
	public int addItem(Item p){
		int u=addItemQub(p);
		if(u!=0)
			return addItem(p,0,0);
		else
			return u;
	}
	public int addItemQub(Item k){
		Item e=k;
		if(e.isEmpty())
			return 2;
		for(int i=1;i>-1;i--){
			if(qub[i]==null||qub[i].label.equals("<EMPTY>")){
				//items[u][j]=new EmptyItem(hdlr,0,0);
				qub[i]=e;
				return 0;
			}
			if(qub[i].label.equals(e.label)){
				if(qub[i].quantity+e.quantity>e.qLim){
					if(qub[i].quantity!=e.qLim){
						e.quantity=(qub[1].quantity+e.quantity)-e.qLim;
						qub[i].quantity=e.qLim;
					}
					continue;
				}else{
					qub[i].quantity+=e.quantity;
					return 0;
				}
			}
		}
		return 1;
	}
	public int addItem(Item k,int startY,int startX){
		Item e=k;
		if(e.isEmpty())
			return 2;
		for(int u=startY;u<iy;u++){
			for(int j=startX;j<ix;j++){
				if(items[u][j]==null||items[u][j].label.equals("<EMPTY>")){
					//items[u][j]=new EmptyItem(hdlr,0,0);
					items[u][j]=e;
					return 0;
				}
				if(items[u][j].label.equals(e.label)){
					if(items[u][j].quantity+e.quantity>e.qLim){
						if(items[u][j].quantity!=e.qLim){
							e.quantity=(items[u][j].quantity+e.quantity)-e.qLim;
							items[u][j].quantity=e.qLim;
						}
						continue;
					}else{
						items[u][j].quantity+=e.quantity;
						return 0;
					}
				}
			}
		}
		return 1;
	}
	
	public void tick(){
		//UPDATE
		img=new BufferedImage(1,1,BufferedImage.TRANSLUCENT);
		hover="       <NOTHING SELECTED>";
		desc=new String[]{
				"<EMPTY>","","",""
		};
		iQ=0;
		isE=true;
		if(hdlr.$km().ESC)
			hdlr.showInv=false;
		//currentSel.tick();
		if((currentSel.isEmpty()&&!currentSel.label.equals("<EMPTY>"))||currentSel==null)
			currentSel=new EmptyItem(hdlr,0,0);
		for(int r=0;r<qubLim;r++){
			if(qub[r].$collisionBounds((11+(r*qudx)+(32*r)+invx)*(float)hdlr.$game().$currentScaleWidth(),(90+invy)*(float)hdlr.$game().$currentScaleHeight()).contains(new Point(hdlr.$mm().$x(),hdlr.$mm().$y()))&&
					hdlr.$mm().isLeftPressed()&&!hold){
				//until bug is fixed rely on addItems method
				if(//false
						qub[r].label.equals(currentSel.label)&&qub[r].quantity<qub[r].qLim
						&&!currentSel.label.equals("<EMPTY>")
						){
					if(qub[r].quantity+currentSel.quantity>currentSel.qLim){
						currentSel.quantity=qub[r].quantity+currentSel.quantity-currentSel.qLim;
						qub[r].quantity=currentSel.qLim;
					}else{
						qub[r].quantity+=currentSel.quantity;
						currentSel=new EmptyItem(hdlr,0,0);
					}
					//addItem(currentSel,u,j);
					//items[hu][hj]=new EmptyItem(hdlr,0,0);
				}else{
					temp=currentSel;
					currentSel=qub[r];
					qub[r]=temp;
					
				}
				hold=true;
				continue;
			}
			if(qub[r].$collisionBounds((11+(r*qudx)+(32*r)+invx)*(float)hdlr.$game().$currentScaleWidth(),(90+invy)*(float)hdlr.$game().$currentScaleHeight()).contains(new Point(hdlr.$mm().$x(),hdlr.$mm().$y()))){
				isE=qub[r].isEmpty();
				if(!isE)
					hover=new String(qub[r].label);
				desc=qub[r].$desc();
				img=qub[r].$img();
				iQ=qub[r].quantity;
			}
		}
		for(int u=0;u<iy;u++){
			for(int j=0;j<ix;j++){
				//if((items[u][j].isEmpty()&&!items[u][j].label.equals("<EMPTY>"))||items[u][j]==null)
				//	items[u][j]=new EmptyItem(hdlr,0,0);
				if(items[u][j]!=null)
					items[u][j].tick();
				if(items[u][j].$collisionBounds(((3*j)+(32*j)+invx+offX)*(float)hdlr.$game().$currentScaleWidth(),((3*u)+(32*u)+invy+offY)*(float)hdlr.$game().$currentScaleHeight()).contains(new Point(hdlr.$mm().$x(),hdlr.$mm().$y()))&&
						hdlr.$mm().isLeftPressed()&&!hold){
					//until bug is fixed rely on addItems method
					if(//false
							items[u][j].label.equals(currentSel.label)&&items[u][j].quantity<items[u][j].qLim
							&&!currentSel.label.equals("<EMPTY>")
							){
						if(items[u][j].quantity+currentSel.quantity>currentSel.qLim){
							currentSel.quantity=items[u][j].quantity+currentSel.quantity-currentSel.qLim;
							items[u][j].quantity=currentSel.qLim;
						}else{
							items[u][j].quantity+=currentSel.quantity;
							currentSel=new EmptyItem(hdlr,0,0);
						}
						//addItem(currentSel,u,j);
						//items[hu][hj]=new EmptyItem(hdlr,0,0);
					}else{
						temp=currentSel;
						currentSel=items[u][j];
						items[u][j]=new EmptyItem(hdlr,0,0);
						addItem(temp,u,j);
					}
					hold=true;
					continue;
				}
				if(items[u][j].$collisionBounds(
						((3*j)+(32*j)+invx+offX)*(float)hdlr.$game().$currentScaleWidth(),
						((3*u)+(32*u)+invy+offY)*(float)hdlr.$game().$currentScaleHeight()
						).contains(new Point(hdlr.$mm().$x(),hdlr.$mm().$y()))){
					isE=items[u][j].isEmpty();
					if(!isE)
						hover=new String(items[u][j].label);
					desc=items[u][j].$desc();
					img=items[u][j].$img();
					iQ=items[u][j].quantity;
				}
			}
		}
		if(!hdlr.$mm().isLeftPressed())
			hold=false;
	}
	String[] desc=new String[]{
			"<EMPTY>","","",""
	};
	boolean isE=true;
	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.drawImage(Assets.inventory,invx,invy,639,319,null);
		//if(!offhand.label.equals("<EMPTY>"))
		for(int r=0;r<qubLim;r++){
			if(qub[r]!=null&&!qub[r].isEmpty()){
				qub[r].render(g,11+(r*qudx)+(32*r)+invx,90+invy);//2+(32*r)+invx+offX,2+invy+offY
				//g.setColor(Color.WHITE);
				//g.setFont(new Font("Lucida Console", Font.BOLD, 10));
				//g.drawString(qub[r].quantity+"",11+(r*qudx)+(32*r)+invx+32-g.getFontMetrics().stringWidth(qub[r].quantity+""),90+invy+32);
			}
		}
		for(int u=0;u<iy;u++){
			for(int j=0;j<ix;j++){
				if(items[u][j]!=null&&!items[u][j].isEmpty()){
					//addString
					items[u][j].render(g,(3*j)+(32*j)+invx+offX,(3*u)+(32*u)+invy+offY);//fix later in according to rendered inventory screen
					//g.setColor(Color.WHITE);
					//g.setFont(new Font("Lucida Console", Font.BOLD, 10));
					//g.drawString(items[u][j].quantity+"",(3*j)+(32*j)+invx+offX+32-g.getFontMetrics().stringWidth(items[u][j].quantity+""),(3*u)+(32*u)+invy+offY+32);
				}
			}
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("ReservoirGrunge", Font.PLAIN, 14));
		//if(new Rectangle(invx,invy,(32*ix)+offX,(32*iy)+offY).contains(new Point(hdlr.$mm().$x(),hdlr.$mm().$y())))
		int Q=12,F=18;
		if(!isE)
			g.drawImage(img,187+invx,11+invy,48,48,null);
		g.drawString(hover, 230+invx+Q,20+invy+F);
		//Utils.drawStringWithOutline(g,hover,230+invx+Q,20+invy+F,Color.WHITE);
		g.setFont(new Font("Lucida Console", Font.BOLD, 12));
		if(!isE){
			g.drawString(desc[0], 210+invx+Q,68+invy+F);
			g.drawString(desc[1], 210+invx+Q,82+invy+F);
			g.drawString(desc[2], 210+invx+Q,96+invy+F);
			g.drawString(desc[3], 210+invx+Q,110+invy+F);
		}
		if(iQ!=0&&!isE)
			g.drawString("Quantity: "+iQ,210+invx+Q,130+invy+F);
		
		g.drawString("DEBUG:ITEM HELD: "+currentSel.toString()+" "+currentSel.quantity,190+invx,180+invy);
		g.drawString("Inventory Weight: "+$invWeight()+" lbs.",190+invx,192+invy);
		if(!currentSel.isEmpty()){
			//g.setColor(Color.WHITE);
			//g.setFont(new Font("Lucida Console", Font.BOLD, 10));
			//g.drawString(currentSel.quantity+"",((hdlr.$mm().$x()-g.getFontMetrics().stringWidth(currentSel.quantity+""))/hdlr.$game().$renderScale())+(16),(hdlr.$mm().$y()/hdlr.$game().$renderScale())+(16));
			currentSel.render(g,(int)(hdlr.$mm().$x()/hdlr.$game().$currentScaleWidth())-(16),(int)(hdlr.$mm().$y()/hdlr.$game().$currentScaleHeight())-(16));
		}
	}
}
