package fairytale.states;

import havocpixel.Game;
import havocpixel.State;
import havocpixel.entities.basic.DebugPlayer;

import java.awt.Graphics;

public class DemoWorldState extends State{

	public DemoWorldState(Game game) {
		super(game);
		this.useCompressedRendering=true;
	}
	
	private boolean spawned=false;
	
	@Override
	public void update(double dt) {
		game.$currentWorld().update(dt);
		if(!spawned){
			//game.$currentWorld().spawnEntityInWorld(new Jayde(game,0,0));
			game.$currentWorld().spawnEntityInWorld(new DebugPlayer(game,0,0));
			spawned=true;
		}
		
	}

	@Override
	public void render(Graphics g) {
		game.$currentWorld().render(g);
	}

}
