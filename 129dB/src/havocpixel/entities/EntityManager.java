package havocpixel.entities;

//import havocpixel.Timer;
import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {
	private Handler hdlr;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> addingQueue;
	
	@SuppressWarnings("unused")
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
	public Character $char(int i){
		return (Character)entities.get(i);
	}
	public EntityManager(Handler hdlr) {
		this.hdlr=hdlr;
		entities=new ArrayList<Entity>();
		addingQueue=new ArrayList<Entity>();
	}
	//boolean res=false;
	public void respawn(){
		
	}
	public void updateAll(){
		if(addingQueue.size()>0){
			for(Entity e:addingQueue){
				entities.add(e);
			}
			addingQueue.clear();
		}
		Iterator<Entity> ie=entities.iterator();
		while(ie.hasNext()){
			Entity e=ie.next();
			if(!e.isActive())//)//&&!e.equals($player()))
				ie.remove();
		}
		//hdlr.$camera().centerOnEntity($player());
		//res=false;
	}
	public void tick() {
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
			if(!e.isActive())//&&!e.equals($player()))
				ie.remove();
			e.tick();
		}
	}
	
	public void render(Graphics g) {
		if(entities.size()!=0){
			//entities.sort(renderSorter);
			for (Entity e:entities) {
				if(!e.active){
					g.setColor(Color.BLACK);
					g.fillOval(
							//(int)((e.x+(e.object?0:(e.width-e.bounds.width)/2))-hdlr.$camera().$xOffset()),
							(int)((e.x+((e.width-e.bounds.width)/2))-hdlr.$camera().$xOffset()),
							(int)((e.y+(e.height*5/6))-hdlr.$camera().$yOffset()),
							//e.object?e.width:e.bounds.width,
							e.bounds.width,
							(e.height*1/4));
				}
				e.render(g);	
			}
		}
	}
	
	public void addEntity(Entity e) {
		addingQueue.add(e);
	}
	
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
	public ArrayList<Entity> $entities() {
		return entities;
	}
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	public void removeChar() {
		entities.remove(1);
		entities.remove(1);
	}
}
