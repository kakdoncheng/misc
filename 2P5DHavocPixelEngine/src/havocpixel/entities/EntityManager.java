package havocpixel.entities;

//import havocpixel.Timer;
import havocpixel.entities.creatures.Player;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {
	private Handler hdlr;
	public Player player;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> addingQueue;
	private Animation f,v;
	
	private Comparator<Entity> renderSorter=new Comparator<Entity>(){
		public int compare(Entity a, Entity b) {
			if(!(a!=null&&b!=null))
				return 0;
			if(a.label.equals("LABEL:FLOATING_STRING")||b.label.equals("LABEL:FLOATING_STRING")){
				if(a.label.equals("LABEL:FLOATING_STRING")&&!b.label.equals("LABEL:FLOATING_STRING"))
					return 1;
				if(!a.label.equals("LABEL:FLOATING_STRING")&&b.label.equals("LABEL:FLOATING_STRING"))
					return -1;
				if(a.label.equals("LABEL:FLOATING_STRING")&&b.label.equals("LABEL:FLOATING_STRING")){
					if(a.$y()+a.$height()<b.$y()+b.$height())
						return -1;
					if(a.$y()+a.$height()>b.$y()+b.$height())
						return 1;
					return 0;
				}
			}
			if(a.$y()+a.$height()<b.$y()+b.$height())
				return -1;
			if(a.$y()+a.$height()>b.$y()+b.$height())
				return 1;
			return 0;
		}
		
	};

	public int $size(){
		return entities.size();
	}
	public EntityManager(Handler hdlr, Player player) {
		this.hdlr=hdlr;
		this.player=player;
		entities=new ArrayList<Entity>();
		addingQueue=new ArrayList<Entity>();
		addEntity(player);
		f=new Animation(hdlr,80,Assets.fire);
		v=new Animation(hdlr,80,Assets.venom);
	}
	public void removeMonsters(){
		for(Entity e:entities){
			if(!e.object&&!e.flora&&!e.trap&&!e.label.equals("DEAD_CORPSE"))
				e.active=false;
		}
	}
	public void removeItems(){
		for(Entity e:entities){
			if(e.item)
				e.active=false;
		}
	}
	private Rectangle u;
	//boolean res=false;
	public void respawn(){
		removeMonsters();
		removeItems();
		updateAll();
	}
	public void updateAll(){
		u=new Rectangle(0,0,hdlr.$currentWorld().$width()*32,hdlr.$currentWorld().$height()*32);
		if(addingQueue.size()>0){
			for(Entity e:addingQueue){
				entities.add(e);
			}
			addingQueue.clear();
		}
		Iterator<Entity> ie=entities.iterator();
		while(ie.hasNext()){
			Entity e=ie.next();
			if(!e.isActive()&&!e.equals($player()))
				ie.remove();
		}
		hdlr.$camera().centerOnEntity($player());
		//res=false;
	}
	public void tick() {
		//System.out.println("Entities: "+entities.size());
		f.tick();
		v.tick();
		//if(!res){
		//	u=new Rectangle(0,0,hdlr.$currentWorld().$width()*32,hdlr.$currentWorld().$height()*32);
		//	res=true;
		//}
		if(addingQueue.size()>0){
			for(Entity e:addingQueue){
				entities.add(e);
			}
			addingQueue.clear();
		}
		//int a=0;
		Iterator<Entity> ie=entities.iterator();
		while(ie.hasNext()){
			Entity e=ie.next();
			//BREAKS GAME MECHANICS
			//if(e.item){
			//	e.active=false;
			//	continue;
			//}
			if(e.$collisionBounds(0,0).intersects(u)||e.label.equals("UNIQUE;GB")){
				if(e.health<0){
					e.alive=false;
					e.active=false;
					e.die();
				}
				if(e.telefrag())
					e.damage(10000,"Telefrag",true);
				e.tick();
				if(e.isOnFire()){
					e.fireTick--;
					if(hdlr.$game().$tick()%30==0)
						e.damage(10+(int)(Math.random()*11),e.target,false);
				}
				if(e.isPoisoned()){
					e.poisonTick--;
					if(!e.alive)
						e.poisonTick=0;
					if(hdlr.$game().$tick()%30==0)
						e.damage(e.poisonTick/4+(int)(Math.random()*(e.poisonTick/4)),e.target,false);
				}
				//a++;
			}else if(e.particle){
				//e.active=false;
			}
			if(!e.isActive()&&!e.equals($player()))
				ie.remove();
		}
		//System.out.print("["+Timer.time()+"] Entities ticked: "+a+";\n");
		u=new Rectangle((int)hdlr.$camera().$xOffset()-(64*2),(int)hdlr.$camera().$yOffset()-(64*2),
				             hdlr.$game().$width()+(128*2),hdlr.$game().$height()+(128*2));
	}
	public void render(Graphics g) {
		if(entities.size()!=0){
			entities.sort(renderSorter);
			for (Entity e:entities) {
				if(e.$collisionBounds(0,0).intersects(u)||e.label.equals("UNIQUE;GB")){
					if(!e.ghost&&!e.particle&&!e.trap&&!e.item&&!e.label.contains("PROJECTILE")&&!e.label.contains("LOG")&&!e.label.equals("DEAD_CORPSE")&&!e.label.equals("DEAD_HUMAN_CORPSE")&&!e.label.equals("CORPSE_POLE")){
						g.setColor(Color.BLACK);
						///*
						g.fillOval(
								//(int)((e.x+(e.object?0:(e.width-e.bounds.width)/2))-hdlr.$camera().$xOffset()),
								(int)((e.x+((e.width-e.bounds.width)/2))-hdlr.$camera().$xOffset()),
								(int)((e.y+(e.height*5/6))-hdlr.$camera().$yOffset()),
								//e.object?e.width:e.bounds.width,
								e.bounds.width,
								(e.height*1/4));
						//*/
					}
					e.render(g);
					if(e.isOnFire()){
						g.drawImage(f.$currentFrame(),
								(int)(e.item||e.label.equals("Gib")?e.x+8-hdlr.$camera().$xOffset():e.x-hdlr.$camera().$xOffset()),
								(int)(e.item||e.label.equals("Gib")?e.y+8-hdlr.$camera().$yOffset():e.y-hdlr.$camera().$yOffset()-(e.label.equals("LOG")?32:0)),
								e.item||e.label.equals("Gib")?16:e.width,
								e.item||e.label.equals("Gib")?16:e.width,
								null);
					}
					if(e.isPoisoned()){
						g.drawImage(v.$currentFrame(),
								(int)(e.item||e.label.equals("Gib")?e.x+8-hdlr.$camera().$xOffset():e.x-hdlr.$camera().$xOffset()),
								(int)(e.item||e.label.equals("Gib")?e.y+8-hdlr.$camera().$yOffset():e.y-hdlr.$camera().$yOffset()-(e.label.equals("LOG")?32:0)),
								e.item||e.label.equals("Gib")?16:e.width,
								e.item||e.label.equals("Gib")?16:e.width,
								null);
					}
				}	
			}
		}
	}
	
	public void addEntity(Entity e) {
		addingQueue.add(e);
	}
	
	private ArrayList<String> loadedEntities = new ArrayList<String>();
	public void addGeneratedEntity(Entity e){
		//loadedEntities.add(e.);
		addEntity(e);
	}
	
	public Handler $hdlr() {
		return hdlr;
	}
	public void setHdlr(Handler hdlr) {
		this.hdlr = hdlr;
	}
	public Player $player() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public ArrayList<Entity> $entities() {
		return entities;
	}
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
