package havocpixel.worlds;

import havocpixel.Timer;
import havocpixel.entities.BearTrap;
import havocpixel.entities.Entity;
import havocpixel.entities.EntityManager;
import havocpixel.entities.Flora;
import havocpixel.entities.SpikeTrap;
import havocpixel.entities.creatures.Bunny;
import havocpixel.entities.creatures.Corpse;
import havocpixel.entities.creatures.CorpseTree;
import havocpixel.entities.creatures.IndestructibleTree;
import havocpixel.entities.creatures.LootCrate;
import havocpixel.entities.creatures.Pumpkin;
import havocpixel.entities.creatures.Rock;
import havocpixel.entities.creatures.Sign;
import havocpixel.entities.creatures.TrappedCorpseDart;
import havocpixel.entities.creatures.TrappedCorpseIED;
import havocpixel.entities.creatures.Tree;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class World {
	protected Handler hdlr;
	protected int width, height;
	public int spawnX;
	public int spawnY;
	protected String worldLabel;
	public String $worldLabel(){
		return worldLabel;
	}
	protected int[][] pos;
	//private String[] raw;
	protected String r;
	protected boolean renderFog=false;
	
	protected boolean playerMoved=false;
	protected boolean renderBlackScreen=true;
	protected boolean inc=false;
	protected float blackScreen=1.1F;
	protected int id=0;
	
	public EntityManager em;//change to protected
	private Utils util;
	
	protected ArrayList<LeaveArea> area;
	
	private String u="ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßðñòóôõö÷øùúûüýþÿ€µƒABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~‚„…†‡ˆ‰‹ŒŽ‘’“”•–—˜™š›œšžŸ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´¶·¸¹º»¼½¾¿";
	private char u(int a){
		return this.u.charAt(a);
	}
	private int u(char a) {
		try{
			for(int i=0;true;i+=1)
				if(a!=u(i))
					continue;
				else 
					return i;
		}catch(StringIndexOutOfBoundsException e){
		}
		return -1;
	}
	public EntityManager $entityManager() {
		return em;
	}
	public World(Handler hdlr) {
		this.hdlr=hdlr;
		worldLabel="Default World";
		em=new EntityManager(hdlr,hdlr.$player());
		area=new ArrayList<LeaveArea>();
		
		//em.addEntity(new Npc0(hdlr,16*Tile.TILE_WIDTH,11*Tile.TILE_HEIGHT));
		//loadWorld("res/worlds/world0.txt");
		//em.$player().setX(spawnX);
		//em.$player().setY(spawnY);
		
	}
	protected class LeaveArea{
		private int x,y,dir;
		public int i0;
		private String l;
		private Rectangle b;
		public LeaveArea(int x,int y,int index,int dir,String location){
			this.x=x;
			this.y=y;
			this.i0=index;
			this.dir=dir;
			this.l=location;
			b=new Rectangle(x*32,y*32,32,32);
		}
		public void tick(){
			if(hdlr.$player().$collisionBounds(0,0).intersects(b)){
				playerMoved=true;
				renderBlackScreen=true;
				id=this.i0;
			}
		}
		public void render(Graphics g){
			if(dir==0)
				g.drawImage(Utils.rotate(Assets.def,270),(int)((x*32)-hdlr.$camera().$xOffset()),(int)((y*32)-hdlr.$camera().$yOffset()),null);
			else if(dir==1)
				g.drawImage(Utils.rotate(Assets.def,180),(int)((x*32)-hdlr.$camera().$xOffset()),(int)((y*32)-hdlr.$camera().$yOffset()),null);
			else if(dir==2)
				g.drawImage(Utils.rotate(Assets.def,90),(int)((x*32)-hdlr.$camera().$xOffset()),(int)((y*32)-hdlr.$camera().$yOffset()),null);
			else if(dir==3)
				g.drawImage(Assets.def,(int)((x*32)-hdlr.$camera().$xOffset()),(int)((y*32)-hdlr.$camera().$yOffset()),null);
		}
		public void trigger(){
			//DO THIS BULLSHIT
			String d[]=l.split(":");
			hdlr.$wm().changeWorld(Utils.parseInt(d[0]),Utils.parseInt(d[1]),Utils.parseInt(d[2]),Utils.parseInt(d[3]));
		}
	}
	public void debugBlackScreenReset(){
		blackScreen=1.05F;
		inc=false;
		renderBlackScreen=true;
	}
	
	//dialogue boxes
	protected boolean talk=false,setupTalk=false;
	protected String voice="?????",dia="May thy Flesh and Bones be Consumed.";
	protected String[] box=new String[64];
	private int textCooldown=0;
	protected boolean canInspect=false;
	//protected Font f=;
	public void playerCanInspect(){
		canInspect=true;
	}
	public boolean isTalking(){
		return talk;
	}
	public void talk(String voi,String text){
		voice=voi;
		dia=text;
		startRow=0;
		diaRow=0;
		diaInd=0;
		talk=true;
		setupTalk=true;
	}
	
	public void init(){
		;
	}
	public void tick() {
		if(renderBlackScreen){
			if(inc)
				blackScreen+=0.05F;
			else{
				blackScreen-=0.05F;//fix this FIXED?
			}
			if((blackScreen<0.1F&&!inc)||(blackScreen>1.05F&&inc)){
				if(inc)
					inc=false;
				else
					inc=true;
				renderBlackScreen=false;
				if(playerMoved){
					for(LeaveArea u:area){
						if(u.i0==id){
							renderBlackScreen=true;
							playerMoved=false;
							u.trigger();
							return;
						}
					}
					
				}
			}
			return;
		}
		canInspect=false;
		if(isTalking()){
			textCooldown++;
			if(textCooldown>10){
				textCooldown=10;
			}
			if(textCooldown>9){
				if(hdlr.$km().ENTER){
					if(diaRow<startRow+3){
						diaRow=startRow+3;
					}else{
						if(startRow+3>=endRow){
							talk=false;
						}
						startRow++;
					}
					textCooldown=0;
				}else if(hdlr.$km().isAnyKeyPressed()){
					talk=false;
				}
			}
			//return;
		}
		if(hdlr.showInv){
			hdlr.$inv().tick();
			//talk("???????","may thy flesh and bones be consumed0 FFFF FFFFF FFFFFFFF GGGG GGGG GGGGG GGGGG GGGGGG GGGGG GGGGG FFFF FFFFF XXXXXXX XXXXXXXXX XX XXXX XFFF FFFFFFFF FFFFF may thy flesh and bones be consumed1 may thy flesh and bones be consumed2 may thy flesh and bones be consumed3");
			//if(yt>9){
			//}else{
			//	yt++;
			//}
			return;
		}
		for(LeaveArea u:area){
			u.tick();
		}
		//for(Entity e:em.$entities())
		//	if(e.x<-64||e.x>this.width*32+64||e.y<0-64||e.y>this.width*32+64)
		//		e.damage((int)(Math.random()*100+1),"VOID",true);
		em.tick();
		//System.out.print("["+Timer.time()+"] "+em.$size()+";\n");
		//System.out.print("["+Timer.time()+"] "+pos.length*+pos[0].length+";\n");
		
	}
	
	//dialogue
	protected int startRow=0,endRow=0,diaRow=0,diaInd=0;
	public void render(Graphics g) {
		//((Graphics2D)g).scale(2.0,2.0);
		int xStart = (int) Math.max(0, (hdlr.$camera().$xOffset() / Tile.TILE_WIDTH));
		int xEnd = (int) Math.min(width, ((hdlr.$camera().$xOffset() + hdlr.$width()) / Tile.TILE_WIDTH + 1));
		int yStart = (int) Math.max(0, (hdlr.$camera().$yOffset() / Tile.TILE_HEIGHT));
		int yEnd = (int) Math.min(height, ((hdlr.$camera().$yOffset() + hdlr.$height()) / Tile.TILE_HEIGHT + 1));
		for(int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				if(!getTile(x,y).$tlabel().equals("TILE:Void"))
					getTile(x, y).render(g, (int) (x*Tile.TILE_WIDTH - hdlr.$camera().$xOffset()), (int) (y*Tile.TILE_HEIGHT - hdlr.$camera().$yOffset()));
			}
		}
		for(LeaveArea u:area){
			u.render(g);
		}
		em.render(g);
		if(renderFog)
			renderFog(g);
		if(hdlr.showInv){
			hdlr.$inv().render(g);
		}
		hdlr.$player().renderQU(g);
		if(canInspect){
			g.setFont(new Font("Lucida Console", Font.BOLD, 12));
			Utils.drawStringWithOutline(g,"PRESS [T] TO INSPECT",(hdlr.$game().$width()/2)-(g.getFontMetrics().stringWidth("PRESS [T] TO INSPECT")/2), hdlr.$game().$height()-100+(22*3),Color.WHITE);
		}
		if(isTalking()){
			int h=100;
			g.setFont(new Font("ReservoirGrunge", Font.BOLD, 14));
			g.setColor(Color.BLACK);
			g.fillRect(10,hdlr.$game().$height()-h-39,g.getFontMetrics().stringWidth(voice)+20,26);
			g.setColor(Color.WHITE);
			g.drawRect(10,hdlr.$game().$height()-h-39,g.getFontMetrics().stringWidth(voice)+20,26);
			g.drawString(voice,20,hdlr.$game().$height()-h-43+7+16);
			if(setupTalk){
				g.setFont(new Font("Lucida Console", Font.BOLD, 14));
				for(int i=0;i<box.length;i++)
					box[i]="EMPTY";
				int si=0,ei=0,lim=hdlr.$game().$width()-40;
				String ti=dia;
				//System.out.println(ti.length());
				for(int i=0;i<ti.length();i++){
					//System.out.println(ti.substring(si,i));
					if(g.getFontMetrics().stringWidth(ti.substring(si,i))>lim){
						String di=ti.substring(si,i);
						//System.out.println(di);
						box[ei]=di;
						ei++;
						si=i;
						//System.out.println(si);
						for(int c=di.length()-1;c>0;c--){
							if(di.charAt(c)==' '&&g.getFontMetrics().stringWidth(di.substring(0,c))<=lim){
								box[ei-1]=di.substring(0,c);
								si-=(di.length()-c-1);
								//System.out.println(si);
								break;
							}
						}
					}
					if(ei>box.length-2){
						break;
					}
				}
				box[ei]=ti.substring(si,ti.length());
				//System.out.println(box[1]);
				endRow=ei+1;
				//System.out.println(endRow);
				//setup text here
				setupTalk=false;
			}
			//render text here
			g.setColor(Color.BLACK);
			g.fillRect(10,hdlr.$game().$height()-h-10,hdlr.$game().$width()-20,h);
			g.setColor(Color.WHITE);
			g.drawRect(10,hdlr.$game().$height()-h-10,hdlr.$game().$width()-20,h);
			
			//update index & string values
			//startRow=0,endRow=0,diaRow=0,diaInd=0;
			String[] te=new String[3];
			for(int i=0;i<3;i++){
				int k=startRow+i;
				if(box[k].equals("EMPTY")){
					te[i]="EMPTY";
					if(k==diaRow){
						diaRow++;
						diaInd=0;
					}
					continue;
				}
				if(k<diaRow){
					te[i]=box[k];
				}else if(k==diaRow){
					diaInd+=3;
					if(diaInd>=box[k].length()){
						te[i]=box[k];
						diaRow++;
						diaInd=0;
					}else{
						te[i]=box[k].substring(0,diaInd);
					}
				}else{
					te[i]="";
				}
				
			}
			//render text
			g.setFont(new Font("Lucida Console", Font.BOLD, 14));
			for(int i=0;i<3;i++){
				g.drawString(!te[i].equals("EMPTY")?te[i]:"",20,hdlr.$game().$height()-h+(22*i)+14);
			}
			if(diaRow<endRow){
				if(hdlr.$game().$tick()<30)
					g.drawString(">>",hdlr.$game().$width()-22-10,hdlr.$game().$height()-h+(22*3)+14);
			}
		}
		if(renderBlackScreen){
			Utils.drawTranslucentImage(Assets.control,0,0,((blackScreen>1)?1:blackScreen),hdlr.$game().$width(),hdlr.$game().$height(),g);
		}
	}
	public void respawnPlayer(){
		//em.updateAll();
		int a=0;
		int b=(int)(Math.random()*height*width);
		hdlr.$player().x=0;
		hdlr.$player().y=0;
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				a++;
				if(a<b)
					continue;
				if(getTile(x,y).impassable())
					continue;
				else{
					Rectangle u=new Rectangle((x*32)-16,(y*32)-16,64,64);
					boolean hit=false;
					for(Entity e:em.$entities())
						if(u.intersects(e.$collisionBounds(0,0))){
							hit=true;
						}
					if(hit){
						continue;
					}else{
						hdlr.$player().x=x*32;
						hdlr.$player().y=y*32;
						Entity c=new Bunny(hdlr,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
						if(!getTile(x+1,y).impassable()&&!c.entityCollision(32,0)){
							c.x+=32;
							em.addEntity(c);
						}else if(!getTile(x-1,y).impassable()&&!c.entityCollision(-32,0)){
							c.x+=-32;
							em.addEntity(c);
						}else if(!getTile(x,y+1).impassable()&&!c.entityCollision(0,32)){
							c.y+=32;
							em.addEntity(c);
						}else if(!getTile(x,y-1).impassable()&&!c.entityCollision(0,-32)){
							c.y+=-32;
							em.addEntity(c);
						}
						System.out.print("["+Timer.time()+"] Spawned Player at x"+x+"y"+y+"\n");
						return;
					}
				}
				//if(y==height-1&&x==width-1){
				//	y=0;
				//	x=0;
				//	b=(int)(Math.random()*height*width);
				//}
			}
		}
		respawnPlayer();
	}
	
	float dx=0;
	protected int yt=0;
	public boolean getColdTick(){
		return yt>9;
	}
	public void renderFog(Graphics g){
		if(hdlr.showInv){
			if(yt>9){
				yt=0;
			}
		}
		if(dx<512){
			dx+=0.5F;
		}else{
			dx=0;
		}
		Rectangle u=new Rectangle((int)hdlr.$camera().$xOffset(),(int)hdlr.$camera().$yOffset(),hdlr.$game().$width(),hdlr.$game().$height());
		for(int iy=0;iy<(((height*32)/512)+1);iy++){
			for(int ix=0;ix<(((width*32)/512)+2);ix++){
				if(new Rectangle(0+(512*ix)-(int)dx,(512*iy),512,512).intersects(u))
					Utils.drawTranslucentImage(Assets.fog0,0+(512*ix)-(int)dx-(int)hdlr.$camera().$xOffset(),(512*iy)-(int)hdlr.$camera().$yOffset(),0.5F,512,512,g);
			}
		}
	}
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >=height) {
			return Tile.def0;
		}
		Tile t = Tile.tiles[pos[x][y]];
		if (t == null) {
			return Tile.def0;
		}
		return t;
	}
	public int loadLevel(BufferedImage img) {
		if(img!=null){
			System.out.print("["+Timer.time()+"] [INFO] loadLevel(): "+img.toString()+"\n");
		}else{
			System.out.print("["+Timer.time()+"] [ERROR] loadLevel() Exception; Null Input;\n");
			return 1;
		}
		try{
			width=img.getWidth();
			height=img.getHeight()-2;
			spawnX=0;
			spawnY=0;
			pos=new int[width][height];
			for(int y=0;y<height;y++) {
				for(int x=0;x<width;x++) {
					Color c=new Color(img.getRGB(x,y));
					String hexCode=String.format("%02x%02x%02x",c.getRed(),c.getGreen(),c.getBlue());
					pos[x][y]=Tile.matchHexcode(hexCode);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.print("["+Timer.time()+"] [ERROR] A Fatal Exception Has Occurred; World Failed to Load;\n");
			return 2;
			//hdlr.$game().stop();
		}
		return 0;
	}
	protected void loadWorld(String path) {
		System.out.print("["+Timer.time()+"] Loading New World;\n");
		try {
			//String file = Utils.loadFileAsString(path);
			String file = util.loadFileFromClassAsString(path);
			r=file;
			//raw = file.split("\\s+");
			loadData();
			System.out.print("["+Timer.time()+"] World Sucessfully Loaded;\n");
			return;
		} catch (NullPointerException npe) {
			System.out.print("["+Timer.time()+"] [ERROR] Specified World Does Not Exist; Loading Default World;\n");
			//raw = WorldData.defWorld.split("\\s+");
			r=WorldData.def;
			loadData();
			System.out.print("["+Timer.time()+"] Default World Sucessfully Loaded;\n");
			return;
		}
	}
	
	private void loadData() {
		//String n="";
		//for(String c:raw)
		//	n+=u(Utils.parseInt(c));
		//System.out.println(n);
		try {
			width=u(r.charAt(0));
			height=u(r.charAt(1));
			spawnX=u(r.charAt(2))*Tile.TILE_WIDTH;
			spawnY=u(r.charAt(3))*Tile.TILE_HEIGHT;
			pos=new int[width][height];
			for(int y=0;y<height;y++){
				for(int x=0;x<width;x++){
					pos[x][y]=u(r.charAt((x+y*width)+4));
				}
			}
		} catch (Exception e) {
			System.out.print("["+Timer.time()+"] [ERROR] A Fatal Exception Has Occurred; Game Failed To Load;\n");
			hdlr.$game().stop();
		}
		/*
		try {
			width=Utils.parseInt(raw[0]);
			height=Utils.parseInt(raw[1]);
			spawnX=Utils.parseInt(raw[2])*Tile.TILE_WIDTH;
			spawnY=Utils.parseInt(raw[3])*Tile.TILE_HEIGHT;
			pos=new int[width][height];
			for(int y=0;y<height;y++){
				for(int x=0;x<width;x++){
					pos[x][y] = Utils.parseInt(raw[(x+y*width)+4]);
				}
			}
		} catch (Exception e) {
			System.out.print("["+Timer.time()+"] [ERROR] A Fatal Exception Has Occurred; Game Failed To Load;\n");
			hdlr.$game().stop();
		}*/
	}
	
	//spawn terrain bits
	public void surroundWithTrees(){
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				if(getTile(x,y).impassable())
					continue;
				else{
					//add grass algorithm
					if(getTile(x+1,y).impassable()){
						em.addEntity(new IndestructibleTree(hdlr,(x)*Tile.TILE_WIDTH,(y-2)*Tile.TILE_HEIGHT));
						//em.addEntity(new Flora(hdlr,(x)*Tile.TILE_WIDTH,(y)*Tile.TILE_HEIGHT));
					}
					if(getTile(x-1,y).impassable()){
						em.addEntity(new IndestructibleTree(hdlr,(x-1)*Tile.TILE_WIDTH,(y-2)*Tile.TILE_HEIGHT));
					}
					if(getTile(x,y+1).impassable()){
						em.addEntity(new IndestructibleTree(hdlr,(x)*Tile.TILE_WIDTH,(y)*Tile.TILE_HEIGHT));
					}
					if(getTile(x,y-1).impassable()){
						em.addEntity(new IndestructibleTree(hdlr,(x)*Tile.TILE_WIDTH,(y-3)*Tile.TILE_HEIGHT));
					}
				}
			}
		}
	}
	public void spawnCorpses0(){
		for(int y=0;y<height*Tile.TILE_WIDTH;y++){
			for(int x=0;x<width*Tile.TILE_HEIGHT;x++){
				Entity u=new Corpse(hdlr,x,y);
				boolean occ=false;
				for(havocpixel.entities.Entity e:em.$entities()){
					if(e.$collisionBounds(0,0).intersects(u.$collisionBounds(0,0))
							||
							u.collision(x/Tile.TILE_WIDTH,y/Tile.TILE_HEIGHT)){
						occ=true;
						break;
					}
				}
				if(!occ&&Math.random()<0.000006F)
					em.addEntity(u);
			}
		}
		//
	}
	public void spawnTrees(){
		for(int y=0;y<height*Tile.TILE_HEIGHT;y+=8){
			for(int x=0;x<width*Tile.TILE_WIDTH;x+=8){
				Entity u=new Tree(hdlr,x,y);
				if(Math.random()<0.333F){
					//u=new Log(hdlr,x,y);
					//l=true;
				//}else if(Math.random()<0.667F){
					u=new CorpseTree(hdlr,x,y);
				}
				
				boolean occ=false;
				for(havocpixel.entities.Entity e:em.$entities()){
					if(e.$collisionBounds(0,0).intersects(u.$collisionBounds(0,0))
							||
							u.collision(x/Tile.TILE_WIDTH,y/Tile.TILE_HEIGHT)){
						occ=true;
						break;
					}
				}
				if(!occ&&Math.random()<(0.00003F*32)){
					em.addEntity(u);
					//spawnFlora((int)u.$x()+(l?8:32),(int)u.$y()+(l?16:100),20,0.009F);
					//spawnFlora((int)u.$x()+(l?8:32),(int)u.$y()+(l?16:100),50,0.0003F);
					/*if(l){
						spawnFlora((int)u.$x()+0,(int)u.$y()+16,20,0.009F);
						spawnFlora((int)u.$x()+0,(int)u.$y()+16,64,0.0005F);
					}else{
						
					}*/
				}
			}
		}
		//
	}
	public void spawnCorpses(){
		for(int y=0;y<height*Tile.TILE_WIDTH;y+=8){
			for(int x=0;x<width*Tile.TILE_HEIGHT;x+=8){
				Entity u;
				//if(Math.random()<0.15)
				//	u=new LeftHandPole(hdlr,x,y);
				//else if(Math.random()<0.3)
				//	u=new RightHandPole(hdlr,x,y);
				//else
					u=new Corpse(hdlr,x,y);
				boolean occ=false;
				for(havocpixel.entities.Entity e:em.$entities()){
					if(e.$collisionBounds(0,0).intersects(u.$collisionBounds(0,0))
							||
							u.collision(x/Tile.TILE_WIDTH,y/Tile.TILE_HEIGHT)){
						occ=true;
						break;
					}
				}
				if(!occ&&Math.random()<(0.000008F*64))
					em.addEntity(u);
			}
		}
		//
	}
	public void trapWorld(){
		for(int y=0;y<height;y+=8){
			for(int x=0;x<width;x+=8){
				Entity u;
				if(Math.random()<0.07)
					u=new TrappedCorpseDart(hdlr,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
				else if(Math.random()<0.1)
					u=new TrappedCorpseIED(hdlr,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
				else if(Math.random()<0.8)
					u=new SpikeTrap(hdlr,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT,"DECAPITATED_BUNNY");
				else
					u=new BearTrap(hdlr,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT,"DECAPITATED_BUNNY");
				boolean occ=false;
				for(havocpixel.entities.Entity e:em.$entities()){
					if(e.$collisionBounds(0,0).intersects(u.$collisionBounds(0,0))
							||
							u.collision(x,y)){
						occ=true;
						break;
					}
				}
				if(!occ&&Math.random()<(0.015F+0.03)*16)
					em.addEntity(u);
			}
		}
		//
	}
	public void spawnPumpkins(){
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				Entity u=new Pumpkin(hdlr,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
				boolean occ=false;
				for(havocpixel.entities.Entity e:em.$entities()){
					if(e.$collisionBounds(0,0).intersects(u.$collisionBounds(0,0))
							||
							u.collision(x,y)){
						occ=true;
						break;
					}
				}
				if(!occ&&Math.random()<0.004F){
					em.addEntity(u);
					spawnFlora((int)u.$x()+8,(int)u.$y()+16,15,0.009F);
					spawnFlora((int)u.$x()+8,(int)u.$y()+16,40,0.0003F);
				}
			}
		}
		//
	}
	public void spawnLootCrates(){
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				Entity u=new LootCrate(hdlr,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
				boolean occ=false;
				for(havocpixel.entities.Entity e:em.$entities()){
					if(e.$collisionBounds(0,0).intersects(u.$collisionBounds(0,0))
							||
							u.collision(x,y)){
						occ=true;
						break;
					}
				}
				if(!occ&&Math.random()<0.001F)
					em.addEntity(u);
			}
		}
		//
	}
	public void spawnSigns(){
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				Entity u=new Sign(hdlr,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
				boolean occ=false;
				for(havocpixel.entities.Entity e:em.$entities()){
					if(e.$collisionBounds(0,0).intersects(u.$collisionBounds(0,0))
							||
							u.collision(x,y)){
						occ=true;
						break;
					}
				}
				if(!occ&&Math.random()<0.011F){
					em.addEntity(u);
					em.addEntity(new Flora(hdlr,x*Tile.TILE_WIDTH,(y*Tile.TILE_HEIGHT)+32+4));
				}
			}
		}
		//
	}
	
	public void spawnRocks(){
		for(int y=0;y<height*Tile.TILE_HEIGHT;y++){
			for(int x=0;x<width*Tile.TILE_WIDTH;x++){
				Entity u=new Rock(hdlr,x,y);
				boolean occ=false;
				for(havocpixel.entities.Entity e:em.$entities()){
					if(e.$collisionBounds(0,0).intersects(u.$collisionBounds(0,0))
							||
							u.collision(x/Tile.TILE_WIDTH,y/Tile.TILE_HEIGHT)){
						occ=true;
						break;
					}
				}
				if(!occ&&Math.random()<0.00001F){
					em.addEntity(u);
					spawnFlora((int)u.$x()+8,(int)u.$y()+16,15,0.009F);
					spawnFlora((int)u.$x()+8,(int)u.$y()+16,40,0.0003F);
				}
			}
		}
		//
	}
	public void spawnFlora(int dx,int dy,int r,float c){
		//System.out.print("["+Timer.time()+"] "+(r*2)*(r*2)+" Loops;\n");
		for(int y=(dy-r);y<(dy+r);y++){
			for(int x=(dx-r);x<(dx+r);x++){
				Entity u=new Flora(hdlr,x,y);
				if(u.collision(x/Tile.TILE_WIDTH,y/Tile.TILE_HEIGHT)){
					continue;
				}
				if(Math.random()<c)//0.0001F
					em.addEntity(u);
			}
		}
		//
	}
	public void spawnFlora(){
		for(int y=0;y<height*Tile.TILE_HEIGHT;y++){
			for(int x=0;x<width*Tile.TILE_WIDTH;x++){
				Entity u=new Flora(hdlr,x,y);
				if(u.collision(x/Tile.TILE_WIDTH,y/Tile.TILE_HEIGHT)){
					continue;
				}
				if(Math.random()<0.00005F)
					em.addEntity(u);
			}
		}
		//
	}
	public void spawnMassFlora(){
		for(int y=0;y<height*Tile.TILE_HEIGHT;y++){
			for(int x=0;x<width*Tile.TILE_WIDTH;x++){
				Entity u=new Flora(hdlr,x,y);
				if(u.collision(x/Tile.TILE_WIDTH,y/Tile.TILE_HEIGHT)){
					continue;
				}
				if(Math.random()<0.005F)
					em.addEntity(u);
			}
		}
		//
	}
	
	public int $width() {
		return width;
	}
	public int $spawnX() {
		return spawnX;
	}
	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}
	public int $spawnY() {
		return spawnY;
	}
	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int $height() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
