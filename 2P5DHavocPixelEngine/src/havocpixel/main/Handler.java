package havocpixel.main;

import havocpixel.entities.creatures.Player;
import havocpixel.entities.items.Inventory;
import havocpixel.gfx.Camera;
import havocpixel.input.KeyManager;
import havocpixel.input.MouseManager;
import havocpixel.states.StateManager;
import havocpixel.worlds.World;
import havocpixel.worlds.WorldManager;


public class Handler {
	private Game game;
	private Inventory inv;
	private Player player;
	public boolean showInv=false;
	public Handler(Game game) {
		this.game = game;
	}
	public Inventory $inv(){
		return inv;
	}
	public Player $player(){
		return player;
	}
	
	public void setInv(Inventory inv) {
		this.inv = inv;
	}
	public void setPlayer(Player player) {
		this.player = player;
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
	public StateManager $sm() {
		return game.$stateManager();
	}
	
	private WorldManager world;
	public World $currentWorld() {
		return world.$currentWorld();
	}
	public WorldManager $wm() {
		return world;
	}
	public void setWorldManager(WorldManager world) {
		this.world = world;
	}
}
