package havocpixel.entities.creatures;

import havocpixel.entities.ExplodingThrownKnife;
import havocpixel.entities.PoisonedThrownKnife;
import havocpixel.entities.items.ExplosiveThrowingKnife;
import havocpixel.entities.items.PoisonedThrowingKnife;
//import havocpixel.entities.Explosion;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

import java.awt.Rectangle;

public class TrappedCorpseDart extends Corpse{

	private Rectangle range;
	private boolean triggered=false,venom=Math.random()>0.49;
	public TrappedCorpseDart(Handler hdlr, float x, float y) {
		super(hdlr, x, y);
		d=Assets.tcorpse[(int)(Math.random()*3)+2];
		range=new Rectangle((int)x-320+64,(int)y+16,672-128,10);
		trap=true;
	}

	//d0r1u2l3
	//630 453 7910
	public void tick(){
		if(!triggered){
			for(havocpixel.entities.Entity e:hdlr.$currentWorld().$entityManager().$entities()){
				if(e.equals(this)||e.item||e.particle||e.ghost||e.object||e.immovable||e.trap||e.label.equals("DEAD_CORPSE"))
					continue;
				if(e.$collisionBounds(0,0).intersects(range)){
					if(e.x<this.x){
						if(!venom)
							hdlr.$currentWorld().em.addEntity(new ExplodingThrownKnife(hdlr,this.x,this.y+16,0,0,label,3));
						else
							hdlr.$currentWorld().em.addEntity(new PoisonedThrownKnife(hdlr,this.x,this.y+16,label,3));
					}else{
						if(!venom)
							hdlr.$currentWorld().em.addEntity(new ExplodingThrownKnife(hdlr,this.x,this.y+16,0,0,label,1));
						else
							hdlr.$currentWorld().em.addEntity(new PoisonedThrownKnife(hdlr,this.x,this.y+16,label,1));
					}
					triggered=true;
					trap=false;
					break;
				}
			}
		}
		
	}
	boolean dying=false;
	@Override
	public void die() {
		if(!dying){
			spawnBoneGibs();
			if(!triggered){
				if(!venom)
					hdlr.$currentWorld().em.addEntity(new ExplosiveThrowingKnife(hdlr,x,y,1));
				else
					hdlr.$currentWorld().em.addEntity(new PoisonedThrowingKnife(hdlr,x,y,1));
			}
			//spawnGuts();
			dying=true;
		}
	}
}
