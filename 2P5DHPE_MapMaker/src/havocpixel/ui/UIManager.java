package havocpixel.ui;

import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

	private Handler hdlr;
	private ArrayList<UIObject> objects;
	public UIManager(Handler hdlr){
		this.hdlr=hdlr;
		objects=new ArrayList<UIObject>();
	}
	
	public void addObject(UIObject e){
		objects.add(e);
	}
	public void removeObject(int i){
		objects.remove(i);
	}
	
	public void tick(){
		for(UIObject e:objects)
			e.tick();
	}
	public void render(Graphics g){
		for(UIObject e:objects)
			e.render(g);
	}
	public void onMouseMove(MouseEvent e){
		for(UIObject o:objects)
			o.onMouseMove(e);
	}
	public void onMouseReleased(MouseEvent e){
		for(UIObject o:objects)
			o.onMouseReleased(e);
	}
	

	public Handler $hdlr() {
		return hdlr;
	}
	public void setHdlr(Handler hdlr) {
		this.hdlr = hdlr;
	}
	public ArrayList<UIObject> $objects() {
		return objects;
	}
	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}
