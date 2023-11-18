package havocpixel;

import java.awt.Graphics;

public abstract class State {
	protected Game game;
	protected boolean playingAmb=false;
	protected boolean useCompressedRendering=false;
	public boolean isUsingCompressedRendering(){
		return this.useCompressedRendering;
	}
	public State(Game game) {
		this.game=game;
	}
	
	public abstract void update(double dt);
	public abstract void render(Graphics g);
	
}
