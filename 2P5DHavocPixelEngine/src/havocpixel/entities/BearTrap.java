package havocpixel.entities;

import havocpixel.entities.items.ScrapMetal;
import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//base trap
public class BearTrap extends Entity{
	protected String owner;
	protected Animation a,d;
	protected Rectangle range;
	protected int deploy;
	protected boolean triggered=false,hit=false,deployed=false,broken=false;
	public BearTrap(Handler handler,float x,float y,String owner) {
		super(handler, x, y, 32, 32, 1);
		bounds=new Rectangle(0,0,32,32);
		range=new Rectangle(2+(int)x,16+(int)y,28,15);
		label="BEAR_TRAP";
		this.owner=owner;
		a=new Animation(hdlr,90,Assets.bt0);
		d=new Animation(hdlr,90,new BufferedImage[]{
				Assets.bt0[3],Assets.bt0[2],Assets.bt0[1],Assets.bt0[0]
		});
		object=true;
		trap=true;
		//particle=true;
		//ghost=true;
	}

	@Override
	public void tick() {
		deploy=d.tick();
		if(deploy==3&&!deployed){
			hdlr.$currentWorld().em.addEntity(new Smoke(hdlr,(int)x,(int)y));
			deployed=true;
		}
		if(!deployed)
			return;
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.item||e.particle||e.ghost)
				continue;
			if(e.$collisionBounds(0,0).intersects(range)){
				triggered=true;
			}
		}
		if(triggered){
			int u=a.tick();
			if(u==2&&!hit){
				for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
					if(e.equals(this)||e.item||e.particle||e.ghost||e.label.equals("PROJECTILE:INFERNO_FIREBALL"))
						continue;
					if(e.$collisionBounds(0,0).intersects(range)){
						hit=true;
						e.damage(750+(int)(Math.random()*251),owner,false);
					}
				}
			}
			if(u==4){
				active=false;
				die();
				//spawn gibs
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(!deployed){
			g.drawImage(d.$currentFrame(),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()), width, height,null);
		}else{
			g.drawImage(a.$currentFrame(),(int)(x-hdlr.$camera().$xOffset()),(int)(y-hdlr.$camera().$yOffset()), width, height,null);
		}
		
	}

	@Override
	public void die() {
		if(!broken){
			spawnTrapBits();
			hdlr.$currentWorld().em.addEntity(new ScrapMetal(hdlr,this.x,this.y,1));
			broken=true;
		}
	}

}
