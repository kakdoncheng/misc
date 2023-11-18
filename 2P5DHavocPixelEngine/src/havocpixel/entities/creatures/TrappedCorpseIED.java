package havocpixel.entities.creatures;

import havocpixel.entities.Explosion;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Rectangle;

public class TrappedCorpseIED extends Corpse{

	private Rectangle range;
	public TrappedCorpseIED(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		//label="DECAPITATED_BUNNY";
		health=1;
		d=Assets.tcorpse[(int)(Math.random()*2)];
		range=new Rectangle(2+(int)x,16+(int)y,28,15);
		trap=true;
	}
	public void tick(){
		for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
			if(e.equals(this)||e.item||e.particle||e.ghost||e.object||e.immovable||e.trap)
				continue;
			if(e.$collisionBounds(0,0).intersects(range)){
				hdlr.$currentWorld().$entityManager().addEntity(new Explosion(hdlr,this.x-16,this.y-16, label));
				this.active=false;
				spawnBoneGibs();
			}
		}
	}
	boolean dying=false;
	@Override
	public void die() {
		if(!dying){
			spawnBoneGibs();
			spawnGuts();
			hdlr.$currentWorld().$entityManager().addEntity(new Explosion(hdlr,this.x-16,this.y-16, label));
			dying=true;
		}
	}

}
