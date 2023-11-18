package havocpixel.entities.basic;

import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.Direction;
import havocpixel.entities.Entity;
import havocpixel.entities.Faction;
import havocpixel.entities.Weapon;
import havocpixel.gfx.CoreAssets;
import havocpixel.util.Utils;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Gib extends Entity{
	
	protected boolean bloody=true;
	protected boolean s=false;
	protected double fuse=0;
	protected int t;
	protected double sf;
	protected double a=1;
	
	protected BufferedImage getImg(){
		BufferedImage[] gib={
				CoreAssets.gib[0],CoreAssets.gib[1],CoreAssets.gib[2],CoreAssets.gib[3],CoreAssets.gib[4],
				CoreAssets.gib[5],CoreAssets.gib[6],CoreAssets.gib[7],CoreAssets.gib[8],CoreAssets.gib[9],
				CoreAssets.gib[10],CoreAssets.gib[11]
		};
		return gib[game.$randomInt(0,gib.length)];
	}

	public Gib(Game game, double x, double y) {
		super(game, x, y);
		t=game.$randomInt(0, 201);
		sf=0.75-game.$randomDouble(0, 0.25);
		maxHealth=500;
		width=32;
		height=32;
		health=maxHealth;
		speed=game.$randomInt(1,5)*30;
		strength=0;
		bounds=new Rectangle(8,10,15,21);
		armor=Armor.SKELETON;
		weapon=Weapon.DAMAGED_CLEAVER;
		faction=Faction.POSSESSED;
		dir=$randomDirection();
		kbdir=Direction.DOWN;
		name="Gib";
		swornTarget=null;
		currentTarget=null;
		active=true;
		dead=true;
		particle=false;
		object=false;
		projectile=false;
		item=false;
		fleshy=false;
		ghost=true;
		invulnerable=false;
		aggressive=false;
		spawning=false;
		canAttackWithProjectile=false;
		stopActions();
		stopEffects();
		
		img=getImg();
	}

	@Override
	public void update(double dt) {
		if((int)(fuse*100)%5==0&&(int)(fuse*100)<75)
			if(!(a<1))
				img=Utils.rotate(img,90);
		if(!s)
			move(dt);
		else
			s=move(dt);
		//x+=dir.$dx()*speed*dt;
		//y+=dir.$dy()*speed*dt;
		//if(s!=0)
		//	this.speed=0;
		if((int)(fuse*100)>(50+t-1)){
			speed=0;
		}
		if((int)(fuse*100)>(50+t)){
			a-=dt;
			if(a<0)
				active=false;
		}else{
			fuse+=(dt*sf);
		}
	}
	
	private boolean[] splat={
			true,true,true,true,true
			};

	public void render(Graphics g) {
		if(a<1){
			Utils.drawTranslucentImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),
					(float)a,
					32,32,g);
			return;
		}
		if(fuse<.05){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+0),null);
		}else if(fuse<.10){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+1),null);
		}else if(fuse<.15){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+2),null);
		}else if(fuse<.20){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+4),null);
		}else if(fuse<.25){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+6),null);
		}else if(fuse<.30){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
			if(bloody&&splat[0]){
				game.$currentWorld().$entityManager().addEntity(new Bloodstain(game,x,y+12,true));
				splat[0]=false;
			}
		}else if(fuse<.35){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+7),null);
		}else if(fuse<.40){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+5),null);
		}else if(fuse<.45){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+4),null);
		}else if(fuse<.50){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+6),null);
		}else if(fuse<.55){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+8),null);
		}else if(fuse<.60){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
			if(bloody&&splat[1]){
				game.$currentWorld().$entityManager().addEntity(new Bloodstain(game,x,y+12,true));
				splat[1]=false;
			}
			if(speed!=0)
				this.speed-=30;
		}else if(fuse<.65){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+8),null);
		}else if(fuse<.70){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+7),null);
		}else if(fuse<.75){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+8),null);
		}else if(fuse<.80){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+9),null);
		}else if(fuse<.85){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
			if(bloody&&splat[2]){
				game.$currentWorld().$entityManager().addEntity(new Bloodstain(game,x,y+12,true));
				splat[2]=false;
			}
		}else if(fuse<.90){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}else if(fuse<.95){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}else if(fuse<1.00){
			if(speed!=0)
				this.speed-=30;
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}else if(fuse<1.05){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}else if(fuse<1.10){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}else if(fuse<1.15){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}else if(fuse<1.20){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}else if(fuse<1.25){
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}else if(fuse<1.30){
			if(speed!=0)
				this.speed-=30;
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}else{
			g.drawImage(img,(int)(x-game.$camera().$xOffset()),(int)(this.y-game.$camera().$yOffset()+12),null);
		}
		//if(s!=0)
			//this.speed=0;
	}

	@Override
	protected void die() {
		// TODO Auto-generated method stub
		
	}

}
