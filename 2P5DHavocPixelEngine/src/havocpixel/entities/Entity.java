package havocpixel.entities;

//import havocpixel.Timer;
import havocpixel.Timer;
import havocpixel.entities.creatures.BearTrapGib0;
import havocpixel.entities.creatures.BearTrapGib1;
import havocpixel.entities.creatures.BearTrapGib2;
import havocpixel.entities.creatures.BearTrapGib3;
import havocpixel.entities.creatures.DecapitatedBunnyHead;
import havocpixel.entities.creatures.Gib;
import havocpixel.entities.creatures.Guts;
import havocpixel.entities.creatures.ImpGib;
import havocpixel.entities.creatures.SkullGib;
import havocpixel.entities.creatures.StoneGib;
import havocpixel.entities.creatures.WoodGib;
import havocpixel.entities.items.Energy;
import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	//directions 0=up,1=left,2=down,3=right REV
	protected int direction,targetDirection;
	public float speed, xMove, yMove;
	public static final float DEFAULT_SPEED=3.0F;
	
	public String label;
	public String target="NONE";
	
	protected int AC=0,stunTick=30;
	public int fireTick=0;
	public boolean isOnFire(){
		return fireTick!=0;
	}
	public void setOnFire(){
		fireTick=60+(int)(Math.random()*121);
	}
	public int poisonTick=0;
	public boolean isPoisoned(){
		return poisonTick!=0;
	}
	public void poison(){
		poisonTick=90+(int)(Math.random()*151);
	}
	
	public boolean collision(int x, int y) {
		return hdlr.$currentWorld().getTile(x,y).impassable();
	}
	protected int health,damageTick=0,damage,maxHealth;
	public static final int DEFAULT_HEALTH=100,DL=5;
	public float x;
	public float y;
	protected int width, height;
	protected Handler hdlr;
	public Rectangle bounds;
	
	//status
	public boolean active=true;
	public boolean alive=true;
	public boolean possessed=false;
	public boolean crippled=false;
	
	//traits
	public boolean item=false;
	public boolean particle=false;
	public boolean wood=false;
	protected boolean hurt=false;
	protected boolean angry=false;
	protected boolean targetFound=false;
	public boolean object=false;
	protected boolean fleshy=false;
	public boolean ghost=false;
	public boolean trap=false;
	public boolean flora=false;
	public boolean immovable=false;
	public boolean corrosive=false;
	public int ally=256;
	
	public boolean isActive(){
		return active;
	}
	
	public boolean targetIsActive(){
		for(Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.item||e.particle||e.flora)
				continue;
			if(e.label.equals(this.target))
				return true;
		}
		return false;
	}
	public boolean isTargetSighted(){
		Rectangle ar1=new Rectangle((int)(this.x-224),(int)(this.y-224),480,480);
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.item||e.particle||e.flora)
				continue;
			if(e.$collisionBounds(0,0).intersects(ar1)){
				if(e.label.equals(this.target)){
					return true;
				}
			}
		}
		return false;
	}
	public void lookForTarget(){
		targetFound=isTargetSighted();
	}
	//MAKE A METHOD THAT WILL DETERMINE THE NEW TARGET
	
	public void setTargetDirection(){
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.item||e.particle||e.flora)
				continue;
			if(e.label.equals(this.target)){
				if((e.x>this.x)&&!(e.x<this.x)){
					//right
					targetDirection=1;
				}else if(!(e.x>this.x)&&(e.x<this.x)){
					//left
					targetDirection=3;
				}else if((e.y>this.y)&&!(e.y<this.y)){
					//down
					targetDirection=0;
				}else if(!(e.y>this.y)&&(e.y<this.y)){
					//up
					targetDirection=2;
				}else{
					//jagged
					targetDirection=-1;
				}
			}
		}
		targetDirection=-1;
	}
	public boolean telefrag(){
		//returns true if entity is inside of an impassable tile
		if(immovable||label.equals("PROJECTILE:INFERNO_FIREBALL"))
			return false;
		boolean a0=!collision((int)(x+bounds.x+bounds.width)/32,(int)((y+bounds.y)/32))&&!collision((int)(x+bounds.x+bounds.width)/32,(int)((y+bounds.y+bounds.height)/32));
		boolean b0=!collision((int)(x+bounds.x)/32,(int)((y+bounds.y)/32))&&!collision((int)(x+bounds.x)/32,(int)((y+bounds.y+bounds.height)/32));
		boolean c0=!collision((int)(x+bounds.x)/32,(int)(y+bounds.y)/32)&&!collision((int)(x+bounds.x+bounds.width)/32,(int)(y+bounds.y)/32);
		boolean d0=!collision((int)(x+bounds.x)/32,(int)(y+bounds.y+bounds.height)/32)&&!collision((int)(x+bounds.x+bounds.width)/32,(int)(y+bounds.y+bounds.height)/32);
		return !(a0&&b0&&c0&&d0);
	}
	public Entity(Handler handler, float x, float y, int width, int height,int health) {
		this.hdlr = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health=health;
		bounds = new Rectangle(0, 0, width, height);
	}
	public void trailKoolAid(){
		int lim=(int)(Math.random()*51);
		lim=7;
		if(Math.random()<0.05F)
		for(int j=0;j<lim;j++)
			//if(!this.object)
				hdlr.$currentWorld().em.addEntity(new KoolAid(hdlr,this.x,this.y));
	}
	public void trailSoot(){
		for(int j=0;j<(int)(Math.random()*51);j++)
			hdlr.$currentWorld().em.addEntity(new Soot(hdlr,this.x-6,this.y-6));
		for(int j=0;j<(int)(Math.random()*51);j++)
			hdlr.$currentWorld().em.addEntity(new Soot(hdlr,this.x+6,this.y+6));
		for(int j=0;j<(int)(Math.random()*51);j++)
			hdlr.$currentWorld().em.addEntity(new Soot(hdlr,this.x-6,this.y+6));
		for(int j=0;j<(int)(Math.random()*51);j++)
			hdlr.$currentWorld().em.addEntity(new Soot(hdlr,this.x+6,this.y-6));
		for(int j=0;j<40+(int)(Math.random()*51);j++)
				hdlr.$currentWorld().em.addEntity(new Soot(hdlr,this.x,this.y));
	}
	public void spawnWoodChip(){
		hdlr.$currentWorld().em.addEntity(new WoodGib(hdlr,this.x+32,this.y+50));
	}
	public void spawnWoodGibs(){
		hdlr.$currentWorld().em.addEntity(new WoodGib(hdlr,this.x+18,50+this.y+18));
		if(Math.random()<0.35F)
			hdlr.$currentWorld().em.addEntity(new WoodGib(hdlr,this.x-18,50+this.y+18));
		hdlr.$currentWorld().em.addEntity(new WoodGib(hdlr,this.x+18,50+this.y+18));
	}
	public void spawnStoneGibs(){
		hdlr.$currentWorld().em.addEntity(new StoneGib(hdlr,this.x+9,this.y+9));
		if(Math.random()<0.35F)
			hdlr.$currentWorld().em.addEntity(new StoneGib(hdlr,this.x-9,this.y+9));
		hdlr.$currentWorld().em.addEntity(new StoneGib(hdlr,this.x+9,this.y+9));
	}
	public void spawnBoneGibs(){
		if(object)
			return;
		hdlr.$currentWorld().em.addEntity(new Gib(hdlr,this.x+9,this.y+9));
		if(Math.random()<0.35F)
			hdlr.$currentWorld().em.addEntity(new Gib(hdlr,this.x-9,this.y+9));
		hdlr.$currentWorld().em.addEntity(new Gib(hdlr,this.x+9,this.y+9));
	}
	public void spawnGibs(){
		if(object)
			return;
		hdlr.$currentWorld().em.addEntity(new Gib(hdlr,this.x+9,this.y+9));
		hdlr.$currentWorld().em.addEntity(new Gib(hdlr,this.x-9,this.y+9));
		hdlr.$currentWorld().em.addEntity(new SkullGib(hdlr,this.x-9,this.y-9));
	}
	public void spawnImpGibs(){
		if(object)
			return;
		hdlr.$currentWorld().em.addEntity(new ImpGib(hdlr,this.x-9,this.y-9));
		hdlr.$currentWorld().em.addEntity(new Gib(hdlr,this.x+9,this.y+9));
		if(Math.random()<0.35F)
			hdlr.$currentWorld().em.addEntity(new ImpGib(hdlr,this.x-9,this.y+9));
		else if(Math.random()<0.15F)
			hdlr.$currentWorld().em.addEntity(new Gib(hdlr,this.x+9,this.y-9));
	}
	public void spawnGuts(){
		if(object)
			return;
		hdlr.$currentWorld().em.addEntity(new Guts(hdlr,this.x-9,this.y-9));
		hdlr.$currentWorld().em.addEntity(new Guts(hdlr,this.x+9,this.y+9));
	}
	public void spawnTrapBits(){
		hdlr.$currentWorld().em.addEntity(new BearTrapGib0(hdlr,(int)x-16,(int)y));
		hdlr.$currentWorld().em.addEntity(new BearTrapGib1(hdlr,(int)x,(int)y-16));
		hdlr.$currentWorld().em.addEntity(new BearTrapGib2(hdlr,(int)x+16,(int)y));
		hdlr.$currentWorld().em.addEntity(new BearTrapGib3(hdlr,(int)x,(int)y+16));
	}
	
	public void damage(int amount,String attackerLabel,boolean ignoreAC){
		damage(amount,attackerLabel,ignoreAC,-1.0F);
	}
	public void damage(int amount,String attackerLabel,boolean ignoreAC,float critChance){
		//if(attackerLabel.equals("player0"))
		boolean crit=false;
		if(this.particle||this.item|this.flora||(this.ghost&&attackerLabel.contains("GHOST")))
			return;
		if(Math.random()<critChance){
			crit=true;
			ignoreAC=true;
		}
		stunTick=0;
		stunL=(int)(5+(Math.random()*16));
		angry=true;
		hurt=true;
		int dmg;
		if(!ignoreAC)
			dmg=((amount*(((float)101-(float)AC)/100))>1)?(int)(amount*(((float)101-(float)AC)/100)):1;
		else
			dmg=amount;
		if(this.label.equals("PLAYER")){
			if(!hdlr.$player().bleed)
				hdlr.$player().bleed=Math.random()<0.01;
		}
		if(this.label.equals("PLAYER")&&hdlr.$player().isBlocking()){
			hdlr.$player().storedDmg+=dmg;
			hdlr.$player().counterDmg=dmg;
			if(crit){
				hdlr.$player().storedDmg+=dmg*2;
			}
			hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,this.x+(this.width/2),this.y,(crit)?dmg+"":dmg+"",Color.GRAY,crit?13:12));
		}else if(this.label.equals("PLAYER")&&hdlr.$player().counterCD!=0){
			health-=dmg*2;
			if(crit){
				health-=dmg*2;
			}
			hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,this.x+(this.width/2),this.y,(crit)?dmg+"":dmg+"",Color.RED,crit?13:12));
		}else{
			health-=dmg;
			if(crit)
				health-=dmg*2;
			hdlr.$currentWorld().em.addEntity(new FloatingString(hdlr,this.x+(this.width/2),this.y,(crit)?dmg+"":dmg+"",Color.RED,crit?13:12));
		}
		if(!attackerLabel.equals("UNIQUE;sans")//&&attackerLabel.equals("DEBUG")
				){
			if(this.label.equals(attackerLabel)&&!this.item){
				System.out.print("["+Timer.time()+"] "+attackerLabel+" hit themselves for "+dmg+" points of Damage;\n");
			}else{
				System.out.print("["+Timer.time()+"] "+attackerLabel+" hit "+this.label+" for "+dmg+" points of Damage;\n");
				if(!target.equals(attackerLabel)){//&&!this.label.equals("PLAYER")
					System.out.print("["+Timer.time()+"] "+attackerLabel+" angered "+this.label+";\n");
					this.target=new String(attackerLabel);
				}
			}
		}else{
			this.target=new String(attackerLabel);
			//System.out.print("["+Timer.time()+"] Sans unleashes a blast of pure energy!;\n");
		}
		
		int m=((dmg/2)>100)?100:dmg/2;
		m=7;
		if(!this.object&&!this.ghost){
			for(int j=0;j<m;j++)
				hdlr.$currentWorld().em.addEntity(new KoolAid(hdlr,this.x,this.y));
			hdlr.$currentWorld().em.addEntity(new BloodSlash(hdlr,this.x,this.y));
		}
		if(health<1){
			fireTick=0;
			if(this.target.equals("PLAYER")&&!this.object&&!this.possessed&&!this.label.equals("DEAD_CORPSE")&&!this.label.equals("DEAD_HUMAN_CORPSE")){
				hdlr.$currentWorld().em.player.kills++;
				if(!this.ghost){
					if(Math.random()<0.667)
						hdlr.$currentWorld().em.addEntity(new Energy(hdlr,this.x,this.y-4));
				}
			}
			active=false;
			alive=false;
			die();
			if(!this.object&&!this.fleshy&&!this.ghost){
				//spawnGibs();
			}
		}
	}
	protected int stunL=(int)(5+(Math.random()*16));
	public boolean isStunned(){
		return stunTick<stunL;
	}
	public Rectangle $collisionBounds(float xOff,float yOff){
		return new Rectangle((int)(x+bounds.x+xOff),(int)(y+bounds.y+yOff),bounds.width,bounds.height);
	}
	public boolean entityCollision(float xOff,float yOff){
		Rectangle u=new Rectangle((int)x-64,(int)y-64,width+128,height+128);
		for(Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(!u.intersects(e.$collisionBounds(0,0)))
				continue;
			if(e.equals(this)||e.trap||e.item||e.flora||e.particle||((e.ghost&&!this.ghost)||(!e.ghost&&this.ghost))||(e.label.equals("Gib")&&this.label.equals("Gib")))
				continue;
			if(e.$collisionBounds(0,0).intersects($collisionBounds(xOff,yOff)))
				return true;
		}
		return false;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public boolean showHP=false;
	protected void renderHP(Graphics g){
		if(!showHP)return;
		if(health<maxHealth){
			g.setColor(Color.BLACK);
			g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()),24,1);
			g.setColor(Color.GREEN);
			if(health>0)
				g.drawRect((int)(x-hdlr.$camera().$xOffset()+4),(int)(y-hdlr.$camera().$yOffset()),(int)(((float)(health)/(float)(maxHealth))*24),1);
		}
	}
	public void summonInfernoWave(int tx,int ty,int dir,String owner,boolean charged){
		for(float u=-1.25F;u<1.25F;u+=0.125F)
			hdlr.$currentWorld().em.addEntity(new InfernoFireball(hdlr,tx,ty,owner,dir,charged,false,u,1));
	}
	public abstract void die();
	
	public float $x() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float $y() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int $width() {
		return width;
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
