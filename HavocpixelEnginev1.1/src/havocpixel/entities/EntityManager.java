package havocpixel.entities;

import havocpixel.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {
	private Game game;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> addingQueue;
	
	private Rectangle renderArea;
	private ArrayList<Entity> renderList;
	
	public void recenterRenderArea(){
		renderArea=new Rectangle((int)game.$camera().$xOffset()-(64*2),(int)game.$camera().$yOffset()-(64*2),
				game.$width()+(128*2),game.$height()+(128*2));
	}
	
	private Comparator<Entity> renderSorter=new Comparator<Entity>(){
		public int compare(Entity a, Entity b) {
			if(!(a!=null&&b!=null))
				return 0;
			if(a.isParticle()||b.isParticle()){
				if(a.isParticle()&&!b.isParticle()){
					return 1;
				}else if(!a.isParticle()&&b.isParticle()){
					return -1;
				}else{
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
	public EntityManager(Game game, Entity player) {
		this.game=game;
		entities=new ArrayList<Entity>();
		addingQueue=new ArrayList<Entity>();
		renderList=new ArrayList<Entity>();
		addEntity(player);
		recenterRenderArea();
	}
	
	public void update(double dt) {
		if(addingQueue.size()>0){
			for(Entity e:addingQueue){
				if(e!=null)
					entities.add(e);
			}
			addingQueue.clear();
		}
		Iterator<Entity> ie=entities.iterator();
		while(ie.hasNext()){
			Entity e=ie.next();
			//if(e.$collisionBounds(0,0).intersects(renderArea)){
				e.update(dt);
			//}
			if(!e.isActive())
				ie.remove();
			else if(e.name.equals("Bloodstain")||e.$collisionBounds(0,0).intersects(renderArea))
				renderList.add(e);
		}
		recenterRenderArea();
		//System.out.println("havocpixel.entities.EntityManager:INFO: RA:"+(int)renderArea.getX()+" "+(int)renderArea.getY()+" "+(int)renderArea.getWidth()+" "+(int)renderArea.getHeight());
	}
	public void render(Graphics g) {
		//now only using renderList instead of entities
		if(renderList.size()!=0){
			renderList.sort(renderSorter);
			for (Entity e:renderList) {
				//if(e.$collisionBounds(0,0).intersects(renderArea)){
					e.render(g);
					//highlightFaction(e,g);
				//}	
			}
			for (Entity e:renderList) {
				//if(e.$collisionBounds(0,0).intersects(renderArea)){
					e.renderOverlay(g);
				//}	
			}
			renderList.clear();
		}
	}
	
	public void highlightFaction(Entity e, Graphics g){
		if(e.$faction()==Faction.UNALIGNED)
			g.setColor(Color.WHITE);
		if(e.$faction()==Faction.POSSESSED)
			g.setColor(Color.BLUE);
		else if(e.$faction()==Faction.HUMAN)
			g.setColor(Color.MAGENTA);
		else if(e.$faction()==Faction.LESSER_DEMON)
			g.setColor(Color.GREEN);
		g.drawRect(e.$collisionBounds(0,0).x-(int)game.$camera().$xOffset(),
				e.$collisionBounds(0,0).y-(int)game.$camera().$yOffset(),
				e.$collisionBounds(0,0).width,e.$collisionBounds(0,0).height);
	}
	
	
	public void addEntity(Entity e) {
		addingQueue.add(e);
	}
	public ArrayList<Entity> $entities(){
		return entities;
	}
	public Rectangle $renderArea(){
		return renderArea;
	}

}
