package havocpixel.ui;

import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected Handler hdlr;
	protected float x,y;
	protected int w,h;
	protected Rectangle bounds;
	protected boolean hover=false;
	
	public UIObject(Handler hdlr,float x,float y,int w,int h){
		this.hdlr=hdlr;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		bounds=new Rectangle((int)x,(int)y,w,h);
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void onClick();
	
	public void onMouseMove(MouseEvent e){
		if(bounds.contains(e.getX(),e.getY()))
			hover=true;
		else
			hover=false;
	}
	public void onMouseReleased(MouseEvent e){
		if(hover)
			onClick();
	}
	
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
	public int $w() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int $h() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public boolean isHover() {
		return hover;
	}
	public void setHover(boolean hover) {
		this.hover = hover;
	}
}
