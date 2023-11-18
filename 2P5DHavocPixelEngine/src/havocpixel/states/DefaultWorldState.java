package havocpixel.states;

import havocpixel.main.Handler;
import havocpixel.worlds.WorldManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DefaultWorldState extends State {
	private WorldManager wm;
	
	public DefaultWorldState(Handler hdlr) {
		super(hdlr);
		wm=new WorldManager(hdlr);
		hdlr.setWorldManager(wm);
		
	}
	public void tick() {
		wm.tick();
	}
	public void render(Graphics g) {
		wm.render(g);
	}

}
