package havocpixel.worlds;

import havocpixel.Timer;
import havocpixel.main.Handler;
import havocpixel.states.State;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class WorldManager {
	private World[][] u;
	private Handler hdlr;
	private int x,y,renderTick;
	private boolean loading,rendered,skip;
	public World $currentWorld(){
		return u[y][x];
	}
	public WorldManager(Handler hdlr){
		u=new World[][]{
				{new Aokigahara(hdlr),new Arena(hdlr)},
				{new TestArea0(hdlr),new TestArea1(hdlr),new TestArea2(hdlr),new TestArea3(hdlr)},
				{new CatacombTest(hdlr)},
				{new TowerStairs(hdlr)},
				{}//new TowerStairs(hdlr);
		};
		this.hdlr=hdlr;
		renderTick=3;
		loading=true;
		rendered=false;
		skip=false;
		x=0;//1
		y=0;
	}
	public void changeWorld(int sx,int sy,int ix,int iy){
		try{
			System.out.println("["+Timer.time()+"] Teleporting to World: "+u[sy][sx].$worldLabel());
			x=sx;
			y=sy;
			u[y][x].em.updateAll();
			u[y][x].em.$player().setX(ix*32);
			u[y][x].em.$player().setY(iy*32);
			u[y][x].em.updateAll();
			u[y][x].em.tick();
		}catch(Exception e){
			System.out.print("["+Timer.time()+"] [ERROR] A Fatal Exception Has Occurred; World Does Not Exist;\n");
		}
	}
	//loading screen and tasks now moved here
	public void tick(){
		//System.out.print(loading);
		if(loading){
			if(!rendered){
				return;
			}else{
				skip=false;
				renderTick=3;
				rendered=false;
				try{
					System.out.println("["+Timer.time()+"] Attempting Init World Index: x"+x+"y"+y+" ");
					System.out.print("["+Timer.time()+"] [INFO] Intitializing: "+u[y][x].$worldLabel()+"\n");
					u[y][x].em.updateAll();
					u[y][x].init();
					u[y][x].em.updateAll();
					u[y][x].respawnPlayer();
					u[y][x].em.tick();
					x++;
					System.out.println("["+Timer.time()+"] New World Index: x"+x+"y"+y+" ");
					u[y][x].$worldLabel();
				}catch(ArrayIndexOutOfBoundsException aie){
					//aie.printStackTrace();
					if(x!=0){
						skip=true;
						System.out.println("["+Timer.time()+"] Skipped World Index: x"+x+"y"+y+" ");
						y++;
						x=0;
					}else{
						//starting world
						System.out.println("["+Timer.time()+"] Terminating World Index Reached: x"+x+"y"+y+" ");
						x=0;//3
						y=0;//PLAYER SPAWN IN STARTING WORLD FIXED
						u[y][x].em.updateAll();
						u[y][x].respawnPlayer();
						u[y][x].em.tick();
						loading=false;
						State.setState(hdlr.$sm().$mainPortal());//for demo only
					}
					
				}
			}
		}else{
			//System.out.println("["+Timer.time()+"] Terminating World Index Reached: x"+x+"y"+y+" ");
			u[y][x].tick();
		}
	}
	public void render(Graphics g){
		//System.out.println("["+Timer.time()+"] Current World Index: x"+x+"y"+y+" rendered "+rendered+" skip "+skip+";");
		if(loading){
			renderTick--;
			if(renderTick<1)
				rendered=true;
			if(skip){
				return;
			}
			g.setColor(new Color(40,40,40));
			g.setFont(new Font("ReservoirGrunge", Font.PLAIN, 8));
			g.drawString("Loading "+u[y][x].$worldLabel()+"...", hdlr.$game().$width()/2-(g.getFontMetrics().stringWidth("Loading "+u[y][x].$worldLabel()+"...")/2), hdlr.$game().$height()/2);
			
		}else{
			u[y][x].render(g);
		}
	}
}
