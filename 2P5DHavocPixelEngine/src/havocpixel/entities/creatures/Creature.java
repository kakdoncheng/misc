package havocpixel.entities.creatures;

import havocpixel.entities.Entity;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;

public abstract class Creature extends Entity {
	
	public static final int
			DEFAULT_WIDTH=32,
			DEFAULT_HEIGHT=32;
	protected boolean attacking=false,attack=false,meleeCooldown=false;
	
	public Creature(Handler hdlr, float x, float y, int width, int height) {
		super(hdlr, x, y, width, height,DEFAULT_HEALTH);
		speed=DEFAULT_SPEED;
		xMove=0;
		yMove=0;
		direction=0;
	}
	public int move() {
		int u=0;
		//xMove*=60/hdlr.$game().$calcFPS();
		//yMove*=60/hdlr.$game().$calcFPS();
		if(!entityCollision(xMove,0)){
			u=moveX();
		}else{
			u=1;
		}
		if(!entityCollision(0,yMove)){
			if(u>0)
				moveY();
			else
				u=moveY();
		}else{
			u=1;
		}
		return u;
	}
	
	public int moveX(){
		int k=0;
		if(xMove>0){
			int tx=(int)(x+xMove+bounds.x+bounds.width)/Tile.TILE_WIDTH;
			if(!collision(tx,(int)((y+bounds.y)/Tile.TILE_HEIGHT))&&!collision(tx,(int)((y+bounds.y+bounds.height)/Tile.TILE_HEIGHT))){
				x+=xMove;
			}else{
				x=tx*Tile.TILE_WIDTH-bounds.x-bounds.width-1;
				k=1;
			}
			direction=1;
		}else if(xMove < 0){
			int tx=(int)(x+xMove+bounds.x)/Tile.TILE_WIDTH;
			if(!collision(tx,(int)((y+bounds.y)/Tile.TILE_HEIGHT))&&!collision(tx,(int)((y+bounds.y+bounds.height)/Tile.TILE_HEIGHT))){
				x+=xMove;
			}else{
				x=tx*Tile.TILE_WIDTH+Tile.TILE_WIDTH-bounds.x;
				k=1;
			}
			direction=3;
		}
		return k;
	}
	public int moveY(){
		int k=0;
		if(yMove<0){
			int ty=(int)(y+yMove+bounds.y)/Tile.TILE_HEIGHT;
			if(!collision((int)(x+bounds.x)/Tile.TILE_WIDTH,ty)&&!collision((int)(x+bounds.x+bounds.width)/Tile.TILE_WIDTH,ty)){
				y+=yMove;
			}else{
				y=ty*Tile.TILE_HEIGHT+Tile.TILE_HEIGHT-bounds.y;
				k=1;
			}
			direction=2;
		}else if(yMove>0){
			int ty=(int)(y+yMove+bounds.y+bounds.height)/Tile.TILE_HEIGHT;
			if(!collision((int)(x+bounds.x)/Tile.TILE_WIDTH,ty)&&!collision((int)(x+bounds.x+bounds.width)/Tile.TILE_WIDTH,ty)){
				y+=yMove;
			} else {
				y=ty*Tile.TILE_HEIGHT-bounds.y-bounds.height-1;
				k=1;
			}
			direction=0;
		}
		return k;
	}
	public float $xMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float $yMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	public int $health() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public float $speed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}
