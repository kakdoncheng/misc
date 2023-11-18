package havocpixel.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;

public class SpikeTrap extends BearTrap{

	private float cx,cy;
	public SpikeTrap(Handler handler, float x, float y, String owner) {
		super(handler, x, y, owner);
		bounds=new Rectangle(0,0,32,32);
		range=new Rectangle(2+(int)x,16+(int)y,28,15);
		label="SPIKE_TRAP";
		a=new Animation(hdlr,90,new BufferedImage[]{
				Assets.spike[0],Assets.spike[1],Assets.spike[2],(Math.random()>0.499)?Assets.spike[3]:Assets.aspike
		});
		object=true;
		trap=true;
		cx=x;
		cy=y;
	}
	int u=0;
	@Override
	public void tick() {
		if(!triggered){
			ghost=true;
			AC=100;
			health=50;
			
		}else{
			AC=0;
			ghost=false;
		}
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(triggered)
				break;
			if(e.equals(this)||e.item||e.particle||e.ghost)
				continue;
			if(e.$collisionBounds(0,0).intersects(range)){
				triggered=true;
			}
		}
		if(triggered){
			if(u!=3)
				u=a.tick();
			if(hdlr.$game().$tick()%5==0){
				int k=3;
				int cx=(int)(this.x+(width/2)),cy=(int)(this.y+(height/2));
				for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
					if(e.equals(this)||e.item||e.particle||e.ghost||e.label.equals("PROJECTILE:INFERNO_FIREBALL")||e.label.equals("TREE"))
						continue;
					if(e.$collisionBounds(0,0).intersects(range)){
						//hit=true;
						e.damage(50+(int)(Math.random()*51),owner,false);
						if(e.immovable)
							continue;
						if(e.y+16>cy){
							//down
							int ty=(int)(e.y+k+e.bounds.y+e.bounds.height)/Tile.TILE_HEIGHT;
							if(!e.entityCollision(0,k)
									&&!e.collision((int)(e.x+e.bounds.x)/Tile.TILE_WIDTH,ty)
									&&!e.collision((int)(e.x+e.bounds.x+e.bounds.width)/Tile.TILE_WIDTH,ty)
									)
								e.y+=k;
						}
						if(e.x+16>cx){
							//right
							int tx=(int)(e.x+k+e.bounds.x+e.bounds.width)/Tile.TILE_WIDTH;
							if(!e.entityCollision(k,0)
									&&!e.collision(tx,(int)(e.y+e.bounds.y)/Tile.TILE_HEIGHT)
									&&!e.collision(tx,(int)(e.y+e.bounds.y+e.bounds.height)/Tile.TILE_HEIGHT)
									)
								e.x+=k;
						}
						if(e.y+16<cy){
							//up
							int ty=(int)(e.y-k+e.bounds.y)/Tile.TILE_HEIGHT;
							if(!e.entityCollision(0,-k)
									&&!e.collision((int)(e.x+e.bounds.x)/Tile.TILE_WIDTH,ty)
									&&!e.collision((int)(e.x+e.bounds.x+e.bounds.width)/Tile.TILE_WIDTH,ty)
									)
								e.y-=k;
						}
						if(e.x+16<cx){
							//left
							int tx=(int)(e.x-k+e.bounds.x)/Tile.TILE_WIDTH;
							if(!e.entityCollision(-k,0)
									&&!e.collision(tx,(int)(e.y+e.bounds.y)/Tile.TILE_HEIGHT)
									&&!e.collision(tx,(int)(e.y+e.bounds.y+e.bounds.height)/Tile.TILE_HEIGHT)
									)
								e.x-=k;
						}
					
					}
				}
			}
			if(u==4){
				//active=false;
				//die();
				//spawn gibs
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		x=cx;
		y=cy;
		g.drawImage(a.$currentFrame(),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()), width, height,null);
		
	}

}
