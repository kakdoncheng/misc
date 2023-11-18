package havocpixel.entities;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.states.State;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Character extends Entity{
	private int team;
	private int gravity=0,gravityLim=-8, dx=0, dy=0, a=0, cd=0, b=0;
	private boolean jumping=true,moving=false, hurt=false, attacking=false,blocking=false,counter=false;
	private Rectangle fist;
	
	private Animation move,punch,idle,move1,punch1,idle1,death0,death1;
	private boolean attackA,dead=false;
	private int hurtTick=0,nothing=0;
	
	public boolean isDead(){
		return dead;
	}
	
	public Character(Handler hdlr,int team,int x,int y){
		super(hdlr,x,y, 100, 200, 1000);
		this.team=team;
		this.label="Character";
		this.speed=3;
		
		move=new Animation(hdlr, 80, Assets.move0);
		punch=new Animation(hdlr, 40, Assets.punch0);
		idle=new Animation(hdlr, 200, Assets.idle0);
		move1=new Animation(hdlr, 80, Assets.move1);
		punch1=new Animation(hdlr, 40, Assets.punch1);
		idle1=new Animation(hdlr, 200, Assets.idle1);
		death0=new Animation(hdlr, 150, Assets.death0);
		death1=new Animation(hdlr, 150, Assets.death1);
	}
	public void tick(){
		if(dead){
			if(death0.$index()!=5)
				death0.tick();
			if(death1.$index()!=5)
				death1.tick();
		}
		if(nothing>0){
			nothing--;
		}
		move.tick();
		idle.tick();
		move1.tick();
		idle1.tick();
		if(this.y>hdlr.$game().$height()){
			health=0;
		}
		if(cd>0)
			cd--;
		if(gravity>gravityLim&&hdlr.$game().$tick()%6==0){
			gravity--;
		}
		dy=gravity;
		if((this.width+this.x)<State.$State().s.x || this.x>(State.$State().s.width+State.$State().s.x))
			jumping=true;
		if(jumping){
			if(!this.$collisionBounds(0,1).intersects(State.$State().s.$collisionBounds(0,0))){
				y-=dy;
			}else{
				y=State.$State().s.$y()-this.height-1;
				jumping=false;
			}
		}
		if(blocking){
			if(b>0)
				b--;
			else
				blocking=false;
		}
		if(hurt&&!counter&&!blocking){
			hurtTick=20;
			gravity=3;
			jumping=true;
			moving=true;
			attacking=false;
			fist=null;
			a=0;
			if(team==1){
				dx=2;
			}else{
				dx=-2;
			}
		}
		if(attacking&&!hurt){
			attackA=true;
			fist=new Rectangle(((team==0)?(int)x:(int)(x-50))+a+(this.width/2),(int)y+(this.height/2)-25,50,50);
			if(team==0){
				if(State.$State().em.$char(2).$collisionBounds(0,0).intersects(fist)){
					State.$State().em.$char(2).damage(counter?(int)((Math.random()*20)+15):(int)((Math.random()*15)+10));
				}
			}else{
				if(State.$State().em.$char(1).$collisionBounds(0,0).intersects(fist)){
					State.$State().em.$char(1).damage(counter?(int)((Math.random()*20)+15):(int)((Math.random()*15)+10));
				}
			}
			if(a>90||a<-90){
				attacking=false;
				counter=false;
				fist=null;
				a=0;
			}else{
				if(team==0)
					a+=9;
				else
					a-=9;
			}
		}
		if(moving){
			boolean hit=false;
			if(dx<0){
				for(Entity e : State.$State().em.$entities()){
					if(e.equals(this))
						continue;
					if(this.$collisionBounds(-3,0).intersects(e.$collisionBounds(0,0))){
						hit=true;
					}
				}
				if(!hit){
					x+=dx;
					if(hdlr.$game().$tick()%7==0&&!jumping)
						dx++;
				}else{
					dx=0;
				}
			}else if(dx>0){
				for(Entity e : State.$State().em.$entities()){
					if(e.equals(this))
						continue;
					if(this.$collisionBounds(3,0).intersects(e.$collisionBounds(0,0))){
						hit=true;
					}
				}
				if(!hit){
					x+=dx;
					if(hdlr.$game().$tick()%8==0&&!jumping)
						dx--;
				}else{
					dx=0;
				}
			}
			if(dx==0){
				moving=false;
			}
		}
		hurt=false;
		if(health<1)
			dead=true;
	}
	public void render(Graphics g){
		if(team==0){
			g.setColor(Color.RED);
			g.fillRect(10, 15, (hdlr.$game().$width()/2)-60, 25);
			g.setColor(Color.GREEN);
			g.fillRect(10, 15, (int)(((hdlr.$game().$width()/2)-60)*((health*1.0)/1000.0)), 25);
			g.setColor(Color.BLACK);
			g.drawRect(10, 15, (hdlr.$game().$width()/2)-60, 25);
			g.drawRect(10+1, 15+1, (hdlr.$game().$width()/2)-60-2, 25-2);
			g.setColor(Color.YELLOW);
			g.setFont(new Font("San Serif",Font.BOLD,16));
			Utils.drawStringWithOutline(g,"LEFT PATRICK", 18, 34,Color.YELLOW);
			
			g.drawImage($currentFrame(),(int)x, (int)y, null);
			g.setColor(Color.RED);
		}else{
			g.setColor(Color.RED);
			g.fillRect((hdlr.$game().$width()/2)+45, 15, (hdlr.$game().$width()/2)-60, 25);
			g.setColor(Color.GREEN);
			int d=(int)(((hdlr.$game().$width()/2)-60)*((health*1.0)/1000.0));
			g.fillRect((hdlr.$game().$width()/2)+45+(((hdlr.$game().$width()/2)-60)-d), 15, d, 25);
			g.setColor(Color.BLACK);
			g.drawRect((hdlr.$game().$width()/2)+45, 15, (hdlr.$game().$width()/2)-60, 25);
			g.drawRect((hdlr.$game().$width()/2)+45+1, 15+1, (hdlr.$game().$width()/2)-60-2, 25-2);
			g.setColor(Color.YELLOW);
			g.setFont(new Font("San Serif",Font.BOLD,16));
			Utils.drawStringWithOutline(g, "RIGHT PATRICK", hdlr.$game().$width()-46-103, 34, Color.YELLOW);
			g.drawImage($currentFrame(),(int)x-(($currentFrame().getWidth()>175)?($currentFrame().getWidth()/2):0), (int)y, null);
			//if(attackA)
			//	g.drawImage($currentFrame(),(int)x-(($currentFrame().getWidth()>175)?($currentFrame().getWidth()/2):0), (int)y, null);
			//else
			//	g.drawImage($currentFrame(),(int)x, (int)y, null);
			g.setColor(Color.BLUE);
		}
		if(punch.$index()==5){
			attackA=false;
		}
		if(punch1.$index()==5){
			attackA=false;
		}
		//g.drawRect((int)x, (int)y, width, height);
		//if(fist!=null)
			//g.drawRect(fist.x, fist.y, fist.width, fist.height);
	}
	
	public void shittyMove(int dir){
		if(dir==0){
			y-=speed;
		}else if(dir==1){
			x+=speed;
		}else if(dir==2){
			y+=speed;
		}else if(dir==3){
			x-=speed;
		}
	}
	public void shittyHurt(){
		hurt=true;
	}
	public void attack(){
		if(nothing>0){
			return;
		}
		if(dead)
			return;
		if(cd>0)
			return;
		attacking=true;
		cd=30;
	}
	public void block(){
		if(nothing>0){
			return;
		}
		if(dead)
			return;
		if(cd>0)
			return;
		b=20;
		blocking=true;
		cd=40;
		
	}

	public void jump(){
		if(nothing>0){
			return;
		}
		if(dead)
			return;
		if(jumping||attacking||hurt){
			return;
		}
		//System.out.println("jump");
		gravity=6;
		jumping=true;
	}
	public void damage(int dmg){
		if(nothing>0){
			return;
		}
		if(dead)
			return;
		if(blocking){
			counter=true;
			attacking=true;
			return;
		}
		hurt=true;
		health-=dmg;
	}
	public void doNothing(){
		nothing=20;
	}
	public void move(int dir){
		if(nothing>0){
			return;
		}
		if(dead)
			return;
		//if(moving)
			//return;
		if(jumping||attacking||hurt){
			return;
		}
		if(dir==0){
			dx=(int)speed;
		}else{
			dx=-(int)speed;
		}
		moving=true;
	}
	
	public BufferedImage $currentFrame(){
		if(dead){
			if(team==0)
				return death0.$currentFrame();
			else
				return death1.$currentFrame();
		}
		if(nothing>0){
			if(team==0)
				return Assets.pin0;
			else
				return Assets.pin1;
		}
		if(team==0){
			if(State.$State().em.$char(2).isDead())
				return Assets.win0;
			if(blocking){
				return Assets.block0;
			}
			if(attackA){
				punch.tick();
				return punch.$currentFrame();
			}
			if(hurtTick>0){
				punch.reset();
				hurtTick--;
				return Assets.hurt0;
			}
			
			if(moving || jumping){
				return move.$currentFrame();
			}
			return idle.$currentFrame();
		}else{
			if(State.$State().em.$char(1).isDead())
				return Assets.win1;
			if(blocking){
				return Assets.block1;
			}
			if(attackA){
				punch1.tick();
				return punch1.$currentFrame();
			}
			if(hurtTick>0){
				punch1.reset();
				hurtTick--;
				return Assets.hurt1;
			}
			
			if(moving || jumping){
				return move1.$currentFrame();
			}
			return idle1.$currentFrame();
		}
		
	}
	
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
