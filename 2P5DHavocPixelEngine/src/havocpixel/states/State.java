package havocpixel.states;

import havocpixel.main.Handler;

import java.awt.Graphics;

public abstract class State {
	protected Handler hdlr;
	protected boolean playingAudio=false;
	public State(Handler hdlr) {
		this.hdlr=hdlr;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	
	private static State currentState=null;
	public static void setState(State state) {
		currentState=state;
	}
	public static State $State() {
		return currentState;
	}
}
