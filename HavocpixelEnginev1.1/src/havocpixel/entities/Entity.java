package havocpixel.entities;

import havocpixel.Game;
import havocpixel.Tile;
import havocpixel.entities.basic.HurtEffect;
import havocpixel.gfx.Animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
	
	protected static final double ACTION_LENGTH=1.0/15;
	
	//core variables
	protected Game game;
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected int maxHealth;
	protected int health;
	protected int speed;
	protected int strength;
	protected Rectangle bounds;
	
	protected Armor armor;
	protected Weapon weapon;
	protected Faction faction;
	protected Direction dir;
	protected Direction kbdir;
	
	protected String name;
	protected Entity swornTarget;
	protected Entity currentTarget;
	
	//status
	protected boolean active;
	protected boolean dead;
	protected boolean spawning;
	
	//properties
	protected boolean particle;
	protected boolean object;
	protected boolean projectile;
	protected boolean ghost;
	protected boolean item;
	protected boolean fleshy;
	protected boolean invulnerable;
	protected boolean aggressive;
	
	//animations
	protected BufferedImage img;
	protected Animation up, aUp, iUp;
	protected Animation down, aDown, iDown;
	protected Animation left, aLeft, iLeft;
	protected Animation right, aRight, iRight;
	protected Animation spawn, death;
	
	//actions
	protected double moveT;
	public boolean isMoving(){
		return moveT>0;
	}
	protected double knockbackT;
	protected int knockbackAmt;
	public boolean isKnockedBack(){
		return knockbackT>0;
	}
	protected double attackT;
	protected boolean attacked;
	protected boolean attackWithProjectile;
	protected double nextAttackWithProjectile;
	protected double nextAttackWithProjectileThres;
	protected boolean canAttackWithProjectile;
	public boolean isAttacking(){
		return attackT>0;
	}
	protected double nothingT;
	public boolean isDoingNothing(){
		return nothingT>0;
	}
	protected int currentRandomAction=0;
	protected double dtUntilNextAction=0;
	public void doNextRandomAction(double dt){
		dtUntilNextAction-=dt;
		if(dtUntilNextAction<0){
			dtUntilNextAction=game.$randomDouble(ACTION_LENGTH, ACTION_LENGTH*3);
			currentRandomAction=game.$randomInt(0, 8);
		}
		if(currentRandomAction==0){
			//move down;
			dir=Direction.DOWN;
			moveT=ACTION_LENGTH;
		}else if(currentRandomAction==1){
			//move right;
			dir=Direction.RIGHT;
			moveT=ACTION_LENGTH;
		}else if(currentRandomAction==2){
			//move up;
			dir=Direction.UP;
			moveT=ACTION_LENGTH;
		}else if(currentRandomAction==3){
			//move left;
			dir=Direction.LEFT;
			moveT=ACTION_LENGTH;
		}else{
			//doNothing;
			nothingT=ACTION_LENGTH;
		}
	}
	public void stopActions(){
		moveT=0;
		//knockbackT=0;
		attackT=0;
		attacked=true;
		attackWithProjectile=false;
		nothingT=0;
	}
	public boolean isNotMovingOrAttacking(){
		return !isMoving()&&!isKnockedBack()&&!isAttacking();
	}
	public boolean isIdle(){
		return !isMoving()&&!isKnockedBack()&&!isAttacking()&&!isDoingNothing();
	}
	
	//health regen
	protected int regenPool;
	protected double regenRate;
	protected double regenT;
	public int $health(){
		return health;
	}
	public double $regenRate(){
		return regenRate;
	}
	public void regenerateHealth(double dt){
		if(!($regenRate()>0)){
			regenT=0;
			return;
		}
		regenT+=regenRate*dt;
		while(regenT>1&&regenPool>0&&health<maxHealth){
			regenT-=1.0;
			regenPool--;
			health++;
		}
	}
	
	//status effects
	protected double bleedT;
	public boolean isBleeding(){
		return bleedT>0;
	}
	protected void resolveEffects(double dt){
		if(isBleeding()){
			bleedT-=dt;
		}
	}
	public void stopEffects(){
		bleedT=0;
	}
	
	//constructor
	public Entity(Game game, double x, double y){
		this.game=game;
		maxHealth=1000;
		this.x=x;
		this.y=y;
		width=32;
		height=32;
		health=maxHealth;
		speed=120;
		strength=0;
		bounds=new Rectangle(0,0,32,32);
		armor=Armor.SKELETON;
		weapon=Weapon.UNARMED;
		faction=Faction.UNALIGNED;
		dir=Direction.DOWN;
		kbdir=Direction.DOWN;
		name="Entity";
		swornTarget=null;
		currentTarget=null;
		active=false;
		dead=false;
		particle=false;
		object=false;
		projectile=false;
		item=false;
		fleshy=false;
		ghost=false;
		invulnerable=false;
		aggressive=false;
		canAttackWithProjectile=false;
		nextAttackWithProjectile=0;
		nextAttackWithProjectileThres=game.$randomDouble(0.125, 1.5);
		stopActions();
		stopEffects();
	}
	
	//targeting & collisions
	public double distanceTo(Entity e){
		double dx=x-e.$x();
		double dy=y-e.$y();
		return Math.sqrt((dx*dx)+(dy*dy));
	}
	public Rectangle $collisionBounds(double xOff,double yOff){
		return new Rectangle((int)(x+bounds.x+xOff),(int)(y+bounds.y+yOff),bounds.width,bounds.height);
	}
	public boolean collision(int x, int y) {
		return game.$currentWorld().getTile(x,y).impassable();
	}
	protected boolean canTarget(Entity e){
		return !e.equals(this)&&e.isActive()&&!e.isDead()&&!e.isObject()&&!e.isParticle()&&!e.isProjectile();
	}
	protected boolean canDamage(Entity e){
		return !e.equals(this)&&!e.isInvulnerable();
	}
	protected boolean canCollideWith(Entity e){
		if(e.equals(this)||e.isParticle()||e.isItem()||e.isGhost())
			return false;
		//if(this.isGhost())
		//	return e.isGhost();
		//if(!this.isGhost())
		//	return !e.isGhost();
		return true;
	}
	public boolean entityCollision(double xOff,double yOff){
		for(Entity e:game.$currentWorld().$entityManager().$entities()){
			if(!canCollideWith(e))
				continue;
			if(e.$collisionBounds(0,0).intersects($collisionBounds(xOff,yOff)))
				return true;
		}
		return false;
	}
	protected Entity entityCollidedWith(double xOff,double yOff){
		for(Entity e:game.$currentWorld().$entityManager().$entities()){
			if(!canCollideWith(e))
				continue;
			if(e.$collisionBounds(0,0).intersects($collisionBounds(xOff,yOff)))
				return e;
		}
		return null;
	}
	
	//Default AI
	//should always resolve in this order:
	//resolve effects
	//if isIdle
		//resolveCurrentTarget
		//decideNextAction
	//finish current action
	
	//resolving target
	protected boolean canSee(Entity e){
		return new Rectangle((int)(this.x-224),(int)(this.y-224),480,480).intersects(e.$collisionBounds(0,0));
	}
	protected boolean doesNotExist(Entity e){
		return e==null||!e.isActive()||e.isDead();
	}
	protected void resolveCurrentTarget(){
		if(!doesNotExist(swornTarget)&&!faction.canAttack(swornTarget.$faction())){
			swornTarget=null;
		}
		if(!doesNotExist(swornTarget)&&canSee(swornTarget)){
			currentTarget=swornTarget;
			return;
		}
		if(doesNotExist(currentTarget)||!canSee(currentTarget)){
			currentTarget=null;
			if(doesNotExist(swornTarget)){
				findNextTarget();
			}else{
				if(canSee(swornTarget)){
					currentTarget=swornTarget;
				}else{
					findNextTarget();
				}
			}
		}
	}
	private void findNextTarget(){
		//if(isAggressive()&&!doesNotExist(game.$player())&&doesNotExist(swornTarget)){
		//	swornTarget=game.$player();
		//}
		for(Entity e:game.$currentWorld().$entityManager().$entities()){
			if(canSee(e)&&canTarget(e)&&faction.isEnemy(e.$faction())){
				if(currentTarget==null)
					currentTarget=e;
				else if(distanceTo(currentTarget)>distanceTo(e))
					currentTarget=e;
			}
		}
	}
	
	//deciding & resolving actions
	protected void decideNextAction(double dt){
		if(!doesNotExist(currentTarget)){
			if(!attemptToAttackTarget()){
				if(isAggressive()){
					//move towards target
					dir=$directionTowards(currentTarget);
					moveT=ACTION_LENGTH;
				}else{
					doNextRandomAction(dt);
				}
			}
		}else{
			doNextRandomAction(dt);
		}
	}
	protected void resolveAction(double dt){
		nextAttackWithProjectile+=dt;
		if(this.isKnockedBack()){
			getKnockedBack(dt);
			return;
		}
		if(this.isAttacking()){
			attacking(dt);
			return;
		}
		if(this.isMoving()){
			if(!doesNotExist(currentTarget)&&isAggressive()){
				moveTowardsTarget(dt);
			}else{
				move(dt);
			}
			return;
		}
		if(this.isDoingNothing()){
			doNothing(dt);
			return;
		}
	}
	
	//attacking
	protected boolean attemptToAttackTarget(){
		Direction toTarget=$actualDirection($directionTowards(currentTarget));
		if(currentTarget.$collisionBounds(0,0).intersects(weapon.$hitBounds($collisionBounds(0, 0), toTarget))){
			attackT=aDown.$speed();
			attackWithProjectile=false;
			dir=toTarget;
			attacked=false;
			aDown.reset();
			aUp.reset();
			aRight.reset();
			aLeft.reset();
			return true;
		}
		if(canAttackWithProjectile&&nextAttackWithProjectile>nextAttackWithProjectileThres){
			Rectangle ar;
			if(toTarget==Direction.DOWN){
				ar=new Rectangle(32,100);
				ar.x=(int)x;
				ar.y=(int)y+height+60;
			}else if(toTarget==Direction.RIGHT){
				ar=new Rectangle(100,32);
				ar.x=(int)x+width+60;
				ar.y=(int)y;
			}else if(toTarget==Direction.UP){
				ar=new Rectangle(32,100);
				ar.x=(int)x;
				ar.y=(int)y-160;
			}else{ //left
				ar=new Rectangle(100,32);
				ar.x=(int)x-160;
				ar.y=(int)y;
			}
			if(currentTarget.$collisionBounds(0,0).intersects(ar)){
				nextAttackWithProjectile=0;
				nextAttackWithProjectileThres=game.$randomDouble(0.125, 1.5);
				attackT=aDown.$speed();
				attackWithProjectile=true;
				dir=toTarget;
				attacked=false;
				aDown.reset();
				aUp.reset();
				aRight.reset();
				aLeft.reset();
				return true;
			}
		}
		return false;
	}
	
	protected void attacking(double dt){
		attackT-=dt;
		aDown.update(dt);
		aUp.update(dt);
		aRight.update(dt);
		aLeft.update(dt);
		if(aDown.$currentIndex()>=aDown.$indexOfLastFrame()-1){
			if(!attacked){
				if(!attackWithProjectile)
					attack();
				else
					attackWithProjectile();
				attacked=true;
			}
		}
		if(attackT<0){
			nothingT=ACTION_LENGTH*game.$randomDouble(1, 4);
		}
	}
	protected void attack(){
		for(Entity e:game.$currentWorld().$entityManager().$entities()){
			if(!canCollideWith(e))
				continue;
			if(e.$collisionBounds(0,0).intersects(weapon.$hitBounds($collisionBounds(0, 0), this.dir))){
				damage(e);
			}
		}
	}
	protected void attackWithProjectile(){
	}
	
	protected void damage(Entity e){
		damage(e, this);
	}
	protected void damage(Entity e, Entity source){
		double effectiveArmor = e.$armor().$AC() * ((1000.0 - weapon.$AP()) / 1000.0);
		double damageMod = ((1000.0 - effectiveArmor) / 1000.0);
		if (weapon.$AP() < 0 && e.$armor().$AC() <= 0)
			damageMod = ((1000.0 + weapon.$AP()) / 1000.0);
		else if (weapon.$AP() > 0 && e.$armor().$AC() <= 0)
			damageMod = ((1000.0 - weapon.$AP()) / 1000.0);
		double damage = (strength + (weapon.$maxDamage() * 0.5) + (weapon.$maxDamage() * game.$randomDouble(0, 0.5))) * damageMod;
		System.out.println(source+" hit "+e+" for "+(int)damage+" points of damage.");
		e.makeSwornTarget(source);
		e.reduceHealth((int)damage);
		e.knockback($actualDirection($directionTowards(e)));
	}
	
	//moving
	protected Direction $directionTowards(Entity e){
		int tx=(int)this.$x();
		int ty=(int)this.$y();
		int ex=(int)e.$x();
		int ey=(int)e.$y();
		if(ex==tx){
			if(ey<ty)
				return Direction.UP;
			if(ey>ty)
				return Direction.DOWN;
		}
		if(ex<tx){
			if(ey<ty)
				return Direction.UPLEFT;
			if(ey>ty)
				return Direction.DOWNLEFT;
		}
		if(ex>tx){
			if(ey<ty)
				return Direction.UPRIGHT;
			if(ey>ty)
				return Direction.DOWNRIGHT;
		}
		if(ey==ty){
			if(ex<tx)
				return Direction.LEFT;
			if(ex>tx)
				return Direction.RIGHT;
		}
		return Direction.DOWN;
	}
	protected boolean move(double dt){
		moveT-=dt;
		double dx=dir.$dx()*speed*dt;
		double dy=dir.$dy()*speed*dt;
		return move(dx, dy);
	}
	protected boolean moveTowardsTarget(double dt){
		moveT-=dt;
		double dx=dir.$dx()*speed*dt;
		double dy=dir.$dy()*speed*dt;
		int tx=(int)this.$x();
		int ty=(int)this.$y();
		int ex=(int)currentTarget.$x();
		int ey=(int)currentTarget.$y();
		if(tx<ex){
			if(this.$x()+dx>currentTarget.$x()){
				dx=currentTarget.$x()-this.$x();
			}
		}else if(tx>ex){
			if(this.$x()+dx<currentTarget.$x()){
				dx=currentTarget.$x()-this.$x();
			}
		}else if(tx==ex){
			dx=0;
		}
		if(ty<ey){
			if(this.$y()+dy>currentTarget.$y()){
				dy=currentTarget.$y()-this.$y();
			}
		}else if(ty>ey){
			if(this.$y()+dy<currentTarget.$y()){
				dy=currentTarget.$y()-this.$y();
			}
		}else if(ty==ey){
			dy=0;
		}
		return move(dx, dy);
	}
	protected boolean move(double dx, double dy) {
		boolean success=moveEX(dx);
		if(success)
			moveEY(dy);
		else
			success=moveEY(dy);
		return success;
	}
	private boolean moveEX(double dx){
		double idx=dx;
		Entity ex=entityCollidedWith(dx,0);
		if(ex!=null){
			if(dx>0){
				dx=((ex.$collisionBounds(0,0).x)-(bounds.x+bounds.width))-x;
			}else if(dx<0){
				dx=((ex.$collisionBounds(0,0).x+ex.$collisionBounds(0,0).width)-(bounds.x))-x;
			}
		}
		if(Math.abs(dx)>Math.abs(idx)){
			return false;
		}
		return moveX(dx);
	}
	private boolean moveEY(double dy){
		double idy=dy;
		Entity ey=entityCollidedWith(0,dy);
		if(ey!=null){
			if(dy>0){
				dy=((ey.$collisionBounds(0,0).y)-(bounds.y+bounds.height))-y;
			}else if(dy<0){
				dy=((ey.$collisionBounds(0,0).y+ey.$collisionBounds(0,0).height)-(bounds.y))-y;
			}
		}
		if(Math.abs(dy)>Math.abs(idy)){
			return false;
		}
		return moveY(dy);
	}
	private boolean moveX(double dx){
		boolean success=true;
		if(dx>0){
			int tx=(int)(x+dx+bounds.x+bounds.width)/Tile.TILE_WIDTH;
			if(!collision(tx,(int)((y+bounds.y)/Tile.TILE_HEIGHT))&&!collision(tx,(int)((y+bounds.y+bounds.height)/Tile.TILE_HEIGHT))){
				x+=dx;
			}else{
				x=tx*Tile.TILE_WIDTH-bounds.x-bounds.width-1;
				success=false;
			}
		}else if(dx < 0){
			int tx=(int)(x+dx+bounds.x)/Tile.TILE_WIDTH;
			if(!collision(tx,(int)((y+bounds.y)/Tile.TILE_HEIGHT))&&!collision(tx,(int)((y+bounds.y+bounds.height)/Tile.TILE_HEIGHT))){
				x+=dx;
			}else{
				x=tx*Tile.TILE_WIDTH+Tile.TILE_WIDTH-bounds.x;
				success=false;
			}
		}
		return success;
	}
	private boolean moveY(double dy){
		boolean success=true;
		if(dy<0){
			int ty=(int)(y+dy+bounds.y)/Tile.TILE_HEIGHT;
			if(!collision((int)(x+bounds.x)/Tile.TILE_WIDTH,ty)&&!collision((int)(x+bounds.x+bounds.width)/Tile.TILE_WIDTH,ty)){
				y+=dy;
			}else{
				y=ty*Tile.TILE_HEIGHT+Tile.TILE_HEIGHT-bounds.y;
				success=false;
			}
		}else if(dy>0){
			int ty=(int)(y+dy+bounds.y+bounds.height)/Tile.TILE_HEIGHT;
			if(!collision((int)(x+bounds.x)/Tile.TILE_WIDTH,ty)&&!collision((int)(x+bounds.x+bounds.width)/Tile.TILE_WIDTH,ty)){
				y+=dy;
			} else {
				y=ty*Tile.TILE_HEIGHT-bounds.y-bounds.height-1;
				success=false;
			}
		}
		return success;
	}
	
	//knocked back
	public void knockback(Direction kbdir){
		if(spawning)
			return;
		knockbackT=0.1;
		knockbackAmt=5;
		this.kbdir=kbdir;
		stopActions();
	}
	public void knockback(Direction kbdir, int amt){
		knockback(kbdir);
		knockbackAmt=amt;
	}
	protected boolean getKnockedBack(double dt){
		knockbackT=-1;
		double dx=kbdir.$dx()*knockbackAmt;//speed*dt*2;
		double dy=kbdir.$dy()*knockbackAmt;//speed*dt*2;
		nothingT=ACTION_LENGTH*game.$randomDouble(1, 4);
		knockbackAmt=5;
		return move(dx, dy);
	}
	
	//do nothing
	protected void doNothing(double dt){
		nothingT-=dt;
	}
	
	//render
	public Direction $randomDirection(){
		int dir=game.$randomInt(0, 8);
		if(dir==0)
			return Direction.DOWN;
		else if(dir==1)
			return Direction.DOWNRIGHT;
		else if(dir==2)
			return Direction.RIGHT;
		else if(dir==3)
			return Direction.UPRIGHT;
		else if(dir==4)
			return Direction.UP;
		else if(dir==5)
			return Direction.UPLEFT;
		else if(dir==6)
			return Direction.LEFT;
		else
			return Direction.DOWNLEFT;
	}
	public Direction $actualDirection(Direction dir){
		if(dir==Direction.LEFT || dir==Direction.DOWNLEFT){
			return Direction.LEFT;
		}else if(dir==Direction.RIGHT || dir==Direction.UPRIGHT){
			return Direction.RIGHT;
		}else if(dir==Direction.UP || dir==Direction.UPLEFT){
			return Direction.UP;
		}else{
			return Direction.DOWN;
		}
	}
	protected void updateMoveAnimations(double dt){
		up.update(dt);
		down.update(dt);
		left.update(dt);
		right.update(dt);
		iUp.update(dt);
		iDown.update(dt);
		iLeft.update(dt);
		iRight.update(dt);
	}
	protected void updateCurrentImg(){
		Direction actualDir=$actualDirection(dir);
		if(isAttacking()){
			if(actualDir==Direction.LEFT){
				img=aLeft.$currentFrame();
			}else if(actualDir==Direction.RIGHT){
				img=aRight.$currentFrame();
			}else if(actualDir==Direction.UP){
				img=aUp.$currentFrame();
			}else{
				img=aDown.$currentFrame();
			}
		}else if(isDoingNothing()){
			if(actualDir==Direction.LEFT){
				img=iLeft.$currentFrame();
			}else if(actualDir==Direction.RIGHT){
				img=iRight.$currentFrame();
			}else if(actualDir==Direction.UP){
				img=iUp.$currentFrame();
			}else{
				img=iDown.$currentFrame();
			}
		}else if(isMoving()||isKnockedBack()){
			if(actualDir==Direction.LEFT){
				img=left.$currentFrame();
			}else if(actualDir==Direction.RIGHT){
				img=right.$currentFrame();
			}else if(actualDir==Direction.UP){
				img=up.$currentFrame();
			}else{
				img=down.$currentFrame();
			}
		}
	}
	
	public abstract void update(double dt);
	public abstract void render(Graphics g);
	
	protected abstract void die();
	
	public double $x(){
		return x;
	}
	public double $y(){
		return y;
	}
	public void setXY(double x, double y){
		this.x=x;
		this.y=y;
	}
	public int $width(){
		return width;
	}
	public int $height(){
		return height;
	}
	public Armor $armor(){
		return armor;
	}
	public Faction $faction(){
		return faction;
	}

	public boolean isActive() {
		return active;
	}
	public boolean isDead() {
		return dead;
	}
	public boolean isParticle() {
		return particle;
	}
	public boolean isObject() {
		return object;
	}
	public boolean isProjectile() {
		return projectile;
	}
	public boolean isItem() {
		return item;
	}
	public boolean isFleshy() {
		return fleshy;
	}
	public boolean isGhost() {
		return ghost;
	}
	public boolean isInvulnerable() {
		return invulnerable;
	}
	public boolean isAggressive() {
		return aggressive;
	}
	
	public void makeSwornTarget(Entity e){
		if(!e.equals(this)&&faction.canAttack(e.$faction())){
			System.out.println(e+" angered "+this+".");
			swornTarget=e;
		}
	}
	public void reduceHealth(int amount){
		if(!invulnerable)
			health-=amount;
		if(fleshy){
			game.$currentWorld().$entityManager().addEntity(new HurtEffect(game,x,y));
		}
	}
	
	protected void renderOverlay(Graphics g){
		
	}
	protected void renderHP(Graphics g){
		if(health<maxHealth){
			g.setColor(Color.BLACK);
			g.fillRect((int)(x-game.$camera().$xOffset()+4),(int)(y-game.$camera().$yOffset()),24,2);
			g.setColor(Color.GREEN);
			if(health<=maxHealth*2/3)
				g.setColor(Color.YELLOW);
			if(health<=maxHealth/3)
				g.setColor(Color.RED);
			if(health>0){
				int hpbar=(int)(((float)(health)/(float)(maxHealth))*24);
				g.fillRect((int)(x-game.$camera().$xOffset()+4),(int)(y-game.$camera().$yOffset()),hpbar<=0?1:hpbar,2);
			}
		}
	}
	protected void renderWeaponBounds(Graphics g){
		g.setColor(Color.RED);
		Rectangle b=weapon.$hitBounds(this.$collisionBounds(0,0),Direction.UP);
		g.drawRect((int)(b.x-game.$camera().$xOffset()),(int)(b.y-game.$camera().$yOffset()),b.width,b.height);
		b=weapon.$hitBounds(this.$collisionBounds(0,0),Direction.DOWN);
		g.drawRect((int)(b.x-game.$camera().$xOffset()),(int)(b.y-game.$camera().$yOffset()),b.width,b.height);
		b=weapon.$hitBounds(this.$collisionBounds(0,0),Direction.RIGHT);
		g.drawRect((int)(b.x-game.$camera().$xOffset()),(int)(b.y-game.$camera().$yOffset()),b.width,b.height);
		b=weapon.$hitBounds(this.$collisionBounds(0,0),Direction.LEFT);
		g.drawRect((int)(b.x-game.$camera().$xOffset()),(int)(b.y-game.$camera().$yOffset()),b.width,b.height);
	}
	protected void renderDebug(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(game.$defaultFont());
		g.drawString(swornTarget+" "+currentTarget,
				(int)(this.x-game.$camera().$xOffset()),
				(int)(this.y-game.$camera().$yOffset())-22);
		g.drawString((int)((double)health*100/(double)maxHealth)+" "+(int)x+" "+(int)y+" "+
				(isIdle()?"1":"0")+(isMoving()?"1":"0")+(isAttacking()?"1":"0")+(isKnockedBack()?"1":"0")+" "+dir,
				(int)(this.x-game.$camera().$xOffset()),
				(int)(this.y-game.$camera().$yOffset())-12);
		g.drawString(game.$currentWorld().$entityManager().$size()+" "+game.$FPS(),
				(int)(this.x-game.$camera().$xOffset()),
				(int)(this.y-game.$camera().$yOffset())-2);
	}
	public void renderShadow(Graphics g){
		g.setColor(Color.BLACK);
		g.fillOval(
				(int)((x+((width-bounds.width)/2))-game.$camera().$xOffset()),
				(int)((y+(height*5/6))-game.$camera().$yOffset()),
				bounds.width+1,
				(height*1/4));
	}
	
	public String toString(){
		return name;
	}

}
