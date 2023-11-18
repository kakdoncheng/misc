package havocpixel.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import havocpixel.gfx.ImageLoader;
import havocpixel.gfx.Textures;
import havocpixel.main.Handler;

public class Stage extends Entity{

	BufferedImage back=new Textures(ImageLoader.loadImage("/txr/stage.png")).crop();
	public Stage(Handler hdlr) {
		super(hdlr, 120, hdlr.$game().$height()-80, hdlr.$game().$width()-240-80, 50, 1);
		// TODO Auto-generated constructor stub
		label="s";
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stubg.setColor(Color.red);
		g.setColor(Color.GREEN);
		g.drawImage(back,(int)x-20, (int)y-40-height*2, width+30+80, 20+height*4,null);
		//g.drawRect((int)x, (int)y, width, height);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
