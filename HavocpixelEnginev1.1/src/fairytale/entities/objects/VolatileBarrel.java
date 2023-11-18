package fairytale.entities.objects;

import havocpixel.Game;
import havocpixel.entities.Armor;
import havocpixel.entities.MoveableObject;
import havocpixel.entities.basic.Explosion;
import havocpixel.gfx.CoreAssets;

import java.awt.Graphics;

public class VolatileBarrel extends MoveableObject{

	public VolatileBarrel(Game game, double x, double y) {
		super(game, x, y);
		maxHealth=500;
		health=maxHealth;
		name="Volatile Barrel";
		//speed=120;
		armor=Armor.WROUGHT_IRON_PLATE;
		invulnerable=false;
		img=CoreAssets.steelBarrel;
	}
	
	public void render(Graphics g){
		g.drawImage(img, 
				(int)(x-game.$camera().$xOffset()),
				(int)(y-game.$camera().$yOffset()),
				this.width,
				this.height,
				null);
		//g.setColor(Color.RED);
		//g.setFont(game.$defaultFont());
		//g.drawString(game.$currentWorld().entityIsInValidSpace(this)+"", (int)(x-game.$camera().$xOffset()),
		//		(int)(y-game.$camera().$yOffset()));
	}
	
	public void die(){
		super.die();
		game.$currentWorld().$entityManager().addEntity(new Explosion(game, x+16, y+8, this));
	}

}
