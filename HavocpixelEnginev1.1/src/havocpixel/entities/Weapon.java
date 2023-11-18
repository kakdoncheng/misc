package havocpixel.entities;

import java.awt.Rectangle;

public enum Weapon {
	UNARMED(0, 0, 0.0),
	DEMON_CLAWS(175, 0, 0.1),
	DAMAGED_CLEAVER(150, -100, 0.05),
	MORNINGSTAR(300, -150, 0.05),
	DAMAGED_SWORD(125, 0, 0.05),
	STEEL_SWORD(150, 100, 0.2),
	DAMAGED_AXE(350, 150, 0.0),
	ZWEIHANDER(1000, 0, 0.3, 40),
	THROWING_KNIFE(75, 0, 0.25),
	ARROW(125, 500, 0.1),
	FIREBALL(250, 0, 0),
	BULLET(500, 750, 0.8),
	BUCKSHOT(150, -200, 0.5),
	EXPLOSION(500, 0, 0.01);
	
	private int maxDamage;
	private int AP;
	private double critChance;
	private int range;
	
	private Weapon(int maxDamage, int AP, double critChance){
		this.maxDamage=maxDamage;
		this.AP=AP;
		this.critChance=critChance;
		this.range=20;
	}
	private Weapon(int maxDamage, int AP, double critChance, int range){
		this.maxDamage=maxDamage;
		this.AP=AP;
		this.critChance=critChance;
		this.range=range;
	}
	
	public int $maxDamage(){
		return maxDamage;
	}
	public int $AP(){
		return AP;
	}
	public double $critChance(){
		return critChance;
	}
	public Rectangle $hitBounds(Rectangle enitityBounds, Direction direction){
		Rectangle hitBounds=new Rectangle(range,range);
		if(direction==Direction.DOWN){
			//down
			hitBounds.x=enitityBounds.x+(enitityBounds.width/2)-(range/2);
			hitBounds.y=enitityBounds.y+enitityBounds.height;
		}else if(direction==Direction.RIGHT){
			//right
			hitBounds.x=enitityBounds.x+enitityBounds.width;
			hitBounds.y=enitityBounds.y+(enitityBounds.height/2)-(range/2);
		}else if(direction==Direction.UP){
			//up
			hitBounds.x=enitityBounds.x+(enitityBounds.width/2)-(range/2);
			hitBounds.y=enitityBounds.y-range;
		}else if(direction==Direction.LEFT){
			//left
			hitBounds.x=enitityBounds.x-range;
			hitBounds.y=enitityBounds.y+(enitityBounds.height/2)-(range/2);
		}
		return hitBounds;
	}

}
