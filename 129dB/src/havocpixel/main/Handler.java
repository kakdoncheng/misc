package havocpixel.main;

import havocpixel.gfx.Camera;
import havocpixel.input.KeyManager;
import havocpixel.states.StateManager;


public class Handler {
	private Game game;
	public boolean showInv=false;
	public Handler(Game game) {
		this.game = game;
	}
	public Game $game() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public int $width() {
		return game.$width();
	}
	public int $height() {
		return game.$height();
	}

	public Camera $camera() {
		return game.$camera();
	}
	
	public KeyManager $km() {
		return game.$keyManager();
	}
	public StateManager $sm() {
		return game.$stateManager();
	}
	
}
