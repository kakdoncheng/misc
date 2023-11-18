package havocpixel;

import fairytale.entities.creatures.ArmoredPossessedHuman;
import fairytale.entities.creatures.ArmoredSkeleton;
import fairytale.entities.creatures.Cultist;
import fairytale.entities.creatures.Ghost;
import fairytale.entities.creatures.Hellsing;
import fairytale.entities.creatures.HoodedSkeleton;
import fairytale.entities.creatures.Imp;
import fairytale.entities.creatures.Incubus;
import fairytale.entities.creatures.NavigatorCultist;
import fairytale.entities.creatures.PossessedHuman;
import fairytale.entities.creatures.Revenant;
import fairytale.entities.creatures.RifleCultist;
import fairytale.entities.creatures.ShotgunCultist;
import fairytale.entities.creatures.Skeleton;
import fairytale.entities.creatures.Zombie;
import fairytale.entities.objects.Tree;
import havocpixel.entities.Entity;
import havocpixel.entities.EntityManager;
import havocpixel.entities.WarpJump;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class World {
	protected int width, height;
	protected Game game;
	protected int spawnX;
	protected int spawnY;
	protected String worldLabel;
	public String $worldLabel(){
		return worldLabel;
	}
	protected int[][] pos;
	protected String r;
	protected boolean renderFog=false;
	
	protected boolean playerMoved=false;
	protected boolean renderBlackScreen=true;
	protected boolean inc=false;
	protected double blackScreen=1.1;
	protected int leaveID=0;
	
	protected EntityManager em;//change to protected
	protected ArrayList<LeaveArea> area;
	
	public EntityManager $entityManager() {
		return em;
	}
	
	public World(Game game) {
		this.game=game;
		worldLabel="Default World";
		em=new EntityManager(game, game.$player());
		area=new ArrayList<LeaveArea>();
		
	}
	protected class LeaveArea{
		private int x,y,dir;
		private int id;
		private String l;
		private Rectangle b;
		public LeaveArea(int x,int y,int index,int dir,String location){
			this.x=x;
			this.y=y;
			this.id=index;
			this.dir=dir;
			this.l=location;
			b=new Rectangle(x*32,y*32,32,32);
		}
		public void tick(){
			if(game.$player()!=null&&game.$player().$collisionBounds(0,0).intersects(b)){
				playerMoved=true;
				renderBlackScreen=true;
				leaveID=this.id;
			}
		}
		public void render(Graphics g){
			if(dir==0)
				g.drawImage(Utils.rotate(CoreAssets.defaultTile[3],270),(int)((x*32)-game.$camera().$xOffset()),(int)((y*32)-game.$camera().$yOffset()),null);
			else if(dir==1)
				g.drawImage(Utils.rotate(CoreAssets.defaultTile[3],180),(int)((x*32)-game.$camera().$xOffset()),(int)((y*32)-game.$camera().$yOffset()),null);
			else if(dir==2)
				g.drawImage(Utils.rotate(CoreAssets.defaultTile[3],90),(int)((x*32)-game.$camera().$xOffset()),(int)((y*32)-game.$camera().$yOffset()),null);
			else if(dir==3)
				g.drawImage(CoreAssets.defaultTile[3],(int)((x*32)-game.$camera().$xOffset()),(int)((y*32)-game.$camera().$yOffset()),null);
		}
		public void trigger(){
			String d[]=l.split(":");
			game.$wm().teleportTo(Utils.parseInt(d[0]),Utils.parseInt(d[1]),Utils.parseInt(d[2]));
		}
		public int $ID(){
			return id;
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
	protected double textCooldown=0;
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
	
	public abstract void load();
	
	public void update(double dt) {
		if(renderBlackScreen){
			if(inc)
				blackScreen+=(2.0*dt);
			else{
				blackScreen-=(2.0*dt);//fix this FIXED?
			}
			if((blackScreen<0.1&&!inc)||(blackScreen>1.05&&inc)){
				if(inc)
					inc=false;
				else
					inc=true;
				renderBlackScreen=false;
				if(playerMoved){
					for(LeaveArea leaveArea:area){
						if(leaveArea.$ID()==leaveID){
							renderBlackScreen=true;
							playerMoved=false;
							leaveArea.trigger();
							return;
						}
					}
					
				}
			}
			//return;
		}
		canInspect=false;
		if(isTalking()){
			textCooldown+=dt;
			if(textCooldown>0.167){
				textCooldown=0.167;
			}
			if(textCooldown>0.15){
				if(game.$km().ENTER){
					if(diaRow<startRow+3){
						diaRow=startRow+3;
					}else{
						if(startRow+3>=endRow){
							talk=false;
						}
						startRow++;
					}
					textCooldown=0;
				}else if(game.$km().isAnyKeyPressed()){
					talk=false;
				}
			}
			//return;
		}
		for(LeaveArea u:area){
			u.tick();
		}
		em.update(dt);
		
	}
	
	//dialogue
	protected int startRow=0,endRow=0,diaRow=0,diaInd=0;
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, (game.$camera().$xOffset() / Tile.TILE_WIDTH));
		int xEnd = (int) Math.min(width, ((game.$camera().$xOffset() + game.$width()) / Tile.TILE_WIDTH + 1));
		int yStart = (int) Math.max(0, (game.$camera().$yOffset() / Tile.TILE_HEIGHT));
		int yEnd = (int) Math.min(height, ((game.$camera().$yOffset() + game.$height()) / Tile.TILE_HEIGHT + 1));
		for(int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				if(!getTile(x,y).$label().equals("TILE:Void"))
					getTile(x, y).render(g, (int) (x*Tile.TILE_WIDTH - game.$camera().$xOffset()), (int) (y*Tile.TILE_HEIGHT - game.$camera().$yOffset()));
			}
		}
		for(LeaveArea u:area){
			u.render(g);
		}
		em.render(g);
		if(canInspect){
			g.setFont(new Font("Lucida Console", Font.BOLD, 12));
			Utils.drawStringWithOutline(g,"PRESS [T] TO INSPECT",(game.$width()/2)-(g.getFontMetrics().stringWidth("PRESS [T] TO INSPECT")/2), game.$height()-100+(22*3),Color.WHITE);
		}
		if(isTalking()){
			int h=100;
			g.setFont(new Font("ReservoirGrunge", Font.BOLD, 14));
			g.setColor(Color.BLACK);
			g.fillRect(10,game.$height()-h-39,g.getFontMetrics().stringWidth(voice)+20,26);
			g.setColor(Color.WHITE);
			g.drawRect(10,game.$height()-h-39,g.getFontMetrics().stringWidth(voice)+20,26);
			g.drawString(voice,20,game.$height()-h-43+7+16);
			if(setupTalk){
				g.setFont(new Font("Lucida Console", Font.BOLD, 14));
				for(int i=0;i<box.length;i++)
					box[i]="EMPTY";
				int si=0,ei=0,lim=game.$width()-40;
				String ti=dia;
				for(int i=0;i<ti.length();i++){
					if(g.getFontMetrics().stringWidth(ti.substring(si,i))>lim){
						String di=ti.substring(si,i);
						box[ei]=di;
						ei++;
						si=i;
						for(int c=di.length()-1;c>0;c--){
							if(di.charAt(c)==' '&&g.getFontMetrics().stringWidth(di.substring(0,c))<=lim){
								box[ei-1]=di.substring(0,c);
								si-=(di.length()-c-1);
								break;
							}
						}
					}
					if(ei>box.length-2){
						break;
					}
				}
				box[ei]=ti.substring(si,ti.length());
				endRow=ei+1;
				setupTalk=false;
			}
			g.setColor(Color.BLACK);
			g.fillRect(10,game.$height()-h-10,game.$width()-20,h);
			g.setColor(Color.WHITE);
			g.drawRect(10,game.$height()-h-10,game.$width()-20,h);
			
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
				g.drawString(!te[i].equals("EMPTY")?te[i]:"",20,game.$height()-h+(22*i)+14);
			}
			if(diaRow<endRow){
				if(game.$currentSecond()<0.5)
					g.drawString(">>",game.$width()-22-10,game.$height()-h+(22*3)+14);
			}
		}
		if(renderBlackScreen){
			Utils.drawTranslucentImage(CoreAssets.blackout,0,0,(float)((blackScreen>1)?1:blackScreen),game.$width(),game.$height(),g);
		}
	}
	
	public boolean entityIsInValidSpace(Entity e){
		Rectangle b=e.$collisionBounds(0,0);
		int tx=(int)(b.x);
		int ty=(int)(b.y);
		if(getTile(tx/32,ty/32).impassable())
			return false;
		tx=(int)(b.x+b.width);
		ty=(int)(b.y);
		if(getTile(tx/32,ty/32).impassable())
			return false;
		tx=(int)(b.x);
		ty=(int)(b.y+b.height);
		if(getTile(tx/32,ty/32).impassable())
			return false;
		tx=(int)(b.x+b.width);
		ty=(int)(b.y+b.height);
		if(getTile(tx/32,ty/32).impassable())
			return false;
		return (int)e.$x()>32&&(int)e.$y()>32&&
				(int)e.$x()<($width()*32)-32&&
				(int)e.$y()<($height()*32)-32&&
				!e.entityCollision(0,0);
	}
	public boolean spawnEntityInRenderArea(Entity e){
		int x=game.$randomInt(em.$renderArea().x+64, em.$renderArea().x+em.$renderArea().width-64);
		int y=game.$randomInt(em.$renderArea().y+64, em.$renderArea().y+em.$renderArea().height-64);
		e.setXY(x,y);
		if(entityIsInValidSpace(e)){
			em.addEntity(e);
			System.out.print("havocpixel.World:INFO: Spawned "+e+" at ("+x+","+y+").\n");
			return true;
		}else{
			System.out.print("havocpixel.World:WARNING: Spawn failed. Collision at ("+x+","+y+").\n");
			return false;
		}
	}
	public void spawnEntityInWorld(Entity e){
		int x=game.$randomInt(64, $width()*32-64);
		int y=game.$randomInt(64, $height()*32-64);
		e.setXY(x,y);
		if(entityIsInValidSpace(e)){
			em.addEntity(e);
			System.out.print("havocpixel.World:INFO: Spawned "+e+" at ("+x+","+y+").\n");
		}else{
			System.out.print("havocpixel.World:WARNING: Spawn failed. Collision at ("+x+","+y+").\n");
			System.out.print("havocpixel.World:WARNING: Reattempting to spawn "+e+".\n");
			spawnEntityInWorld(e);
		}
	}
	
	public void spawnRandomMonster(){
		Entity e;
		boolean defaultSpawn=false;
		int rnd=game.$randomInt(2,13);//game.$randomInt(0,13);
		if(rnd==0){
			e=new ArmoredSkeleton(game,0,0);
			this.spawnEntityInRenderArea(e);
			return;
		}else if(rnd==1){
			e=new Skeleton(game,0,0);
			this.spawnEntityInRenderArea(e);
			return;
		}else if(rnd==2){
			e=new Zombie(game,0,0);
			this.spawnEntityInRenderArea(e);
			return;
		}else if(rnd==3){
			e=new HoodedSkeleton(game,0,0);
			this.spawnEntityInRenderArea(e);
			return;
		}else if(rnd==4){
			e=new PossessedHuman(game,0,0);
		}else if(rnd==5){
			e=new ArmoredPossessedHuman(game,0,0);
		}else if(rnd==6){
			e=new NavigatorCultist(game,0,0);
		}else if(rnd==7){
			e=new ShotgunCultist(game,0,0);
		}else if(rnd==8){
			e=new Cultist(game,0,0);
		}else if(rnd==9){
			e=new RifleCultist(game,0,0);
		}else if(rnd==10){
			e=new Incubus(game,0,0);
			this.spawnEntityInRenderArea(e);
			return;
		}else if(rnd==11){
			e=new Hellsing(game,0,0);
		}else if(rnd==12){
			e=new Imp(game,0,0);
		}else if(rnd==13){
			e=new Revenant(game,0,0);
			this.spawnEntityInRenderArea(e);
			return;
		}else{
			e=new Ghost(game,0,0);
			this.spawnEntityInRenderArea(e);
			return;
		}
		this.spawnEntityInRenderArea(new WarpJump(game,0,0,0,0,e,defaultSpawn,true,1));
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >=height) {
			return Tile.defaultTile;
		}
		Tile t = Tile.tiles[pos[x][y]];
		if (t == null) {
			return Tile.defaultTile;
		}
		return t;
	}
	public int loadLevel(BufferedImage img) {
		if(img!=null){
			System.out.print("havocpixel.World:INFO: Loading world "+img.hashCode()+"\n");
		}else{
			System.out.print("havocpixel.World:WARNING: World data does not exist.\n");
			return 1;
		}
		try{
			width=img.getWidth();
			height=img.getHeight();
			spawnX=0;
			spawnY=0;
			pos=new int[width][height];
			for(int y=0;y<height;y++) {
				for(int x=0;x<width;x++) {
					pos[x][y]=Tile.matchColorCode(new Color(img.getRGB(x,y)));
					//Color c=new Color(img.getRGB(x,y));
					//String hexCode=String.format("%02x%02x%02x",c.getRed(),c.getGreen(),c.getBlue());
					//pos[x][y]=Tile.matchHexcode(hexCode);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.print("havocpixel.World:WARNING: Arbitrary exception. World failed to load.\n");
			return 2;
		}
		return 0;
	}
	
	public void loadDefaultWorld(){
		System.out.print("havocpixel.World:WARNING: Fallback; Loading default world.\n");
		width=50;
		height=50;
		spawnX=1;
		spawnY=1;
		pos=new int[width][height];
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				pos[x][y]=Tile.defaultTile.$ID();
			}
		}
	}
	
	//spawn terrain bits
		public void surroundWithTrees(){
			for(int y=0;y<height;y++){
				for(int x=0;x<width;x++){
					if(getTile(x,y).impassable())
						continue;
					else{
						if(getTile(x+1,y).impassable()){
							em.addEntity(new Tree(game,(x)*Tile.TILE_WIDTH,(y-2)*Tile.TILE_HEIGHT));
						}
						if(getTile(x-1,y).impassable()){
							em.addEntity(new Tree(game,(x-1)*Tile.TILE_WIDTH,(y-2)*Tile.TILE_HEIGHT));
						}
						if(getTile(x,y+1).impassable()){
							em.addEntity(new Tree(game,(x)*Tile.TILE_WIDTH,(y)*Tile.TILE_HEIGHT));
						}
						if(getTile(x,y-1).impassable()){
							em.addEntity(new Tree(game,(x)*Tile.TILE_WIDTH,(y-3)*Tile.TILE_HEIGHT));
						}
					}
				}
			}
		}
	
	public int $width(){
		return width;
	}
	public int $height(){
		return height;
	}
}
