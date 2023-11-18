package havocpixel;

import java.util.ArrayList;

import fairytale.worlds.Arena;
import fairytale.worlds.DemoForestWorld;

public class WorldManager {
	private int currentState;
	private ArrayList<World> worlds;
	private Game game;
	
	public WorldManager(Game game) {
		this.game=game;
		worlds=new ArrayList<World>();
		currentState=0;
	}
	
	public void loadWorlds(){
		System.out.print("havocpixel.WorldManager:INFO: Loading Worlds.\n");
		worlds.add(new EmptyWorld(game));
		worlds.add(new Arena(game));
		worlds.add(new DemoForestWorld(game));
		for(int i=0; i<worlds.size(); i++){
			currentState=i;
			worlds.get(i).load();
		}
	}
	public void teleportTo(int x, int y, int index){
		if(index>worlds.size()-1||index<0){
			System.out.print("havocpixel.WorldManager:WARNING: Attempted to teleport to an invalid world "+index+".\n");
			currentState=0;
		} else {
			currentState=index;
		}
	}

	public World $currentWorld() {
		if(worlds.get(currentState)==null){
			System.out.print("havocpixel.StateManager:WARNING: Attempted to load a null world "+currentState+".\n");
			currentState=0;
		}
		return worlds.get(currentState);
	}

}
