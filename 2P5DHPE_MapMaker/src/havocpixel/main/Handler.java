package havocpixel.main;

import havocpixel.gfx.Camera;
import havocpixel.input.KeyManager;
import havocpixel.input.MouseManager;


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
	public MouseManager $mm() {
		return game.$mouseManager();
	}
}
