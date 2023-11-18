package havocpixel.entities;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.tiles.Tile;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Explosion extends Entity{
	private Animation boom;
	private Rectangle range;
	private String owner;
	private boolean flame=false;

	public Explosion(Handler handler, float x, float y,String owner) {
		super(handler, x, y,64,64,1);
		//label="EXPLOSION";
		label="LABEL:FLOATING_STRING";
		this.owner=owner;
		boom=new Animation(hdlr,80,Assets.boom);
		this.bounds=new Rectangle(-8,-8,16+32,16+32);
		range=new Rectangle((int)x-16,(int)y-16,64+32,64+32);
		this.damage=25;
		this.object=true;
		this.particle=true;
		hdlr.$currentWorld().em.addEntity(new RisingSmoke(hdlr,this.x+16,this.y+16));
	}

	@Override
	public void tick() {
		int u=boom.tick();
		if(u==1){
			hdlr.$currentWorld().em.addEntity(new SmokingParticle(hdlr,this.x+16,this.y+16));
			if(!flame){
				hdlr.$currentWorld().em.addEntity(new Fire(hdlr,this.x+16,this.y+16,owner));
				flame=true;
			}
		}
		if(u==9){
			this.active=false;
		}
		if(u==4){
			hdlr.$currentWorld().em.addEntity(new Soot(hdlr,this.x,this.y));
		}
		if(u>1&&u<7){
			int k=6;
			int cx=(int)(this.x+32),cy=(int)(this.y+32);
			for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
				if(e.equals(this)||e.label.equals("Gib")||e.label.equals("PROJECTILE:INFERNO_FIREBALL")||e.particle||e.flora)
					continue;
				if(e.$collisionBounds(0,0).intersects(range)){
					if(!e.isOnFire())
						e.setOnFire();
					e.damage(this.damage+(int)(Math.random()*76),this.owner,false);
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
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(boom.$currentFrame(),
				(int)(x-hdlr.$camera().$xOffset()),
				(int)(y-hdlr.$camera().$yOffset()),
				width,
				height,null);
	}

	@Override
	public void die() {
		
	}

}
