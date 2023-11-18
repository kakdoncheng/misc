package havocpixel.entities;

//import havocpixel.Timer;
import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	//directions 0=up,1=left,2=down,3=right REV
	public float speed, xMove, yMove;
	public static final float DEFAULT_SPEED=3.0F;
	
	public String label;
	protected int health,damageTick=0,damage,maxHealth;
	public static final int DEFAULT_HEALTH=100,DL=5;
	public float x;
	public float y;
	protected int width, height;
	protected Handler hdlr;
	public Rectangle bounds;
	
	//status
	public boolean active=true;
	
	public boolean isActive(){
		return active;
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
	
	public Rectangle $collisionBounds(float xOff,float yOff){
		return new Rectangle((int)(x+bounds.x+xOff),(int)(y+bounds.y+yOff),bounds.width,bounds.height);
	}
	public boolean entityCollision(float xOff,float yOff){
		return false;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	protected void renderHP(Graphics g){

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
