package havocpixel;

import java.util.ArrayList;

import fairytale.states.BeginDemoWorldState;
import fairytale.states.DemoWorldState;
import fairytale.states.MenuState;
import fairytale.states.PortalScreen;

public class StateManager {
	private boolean isBackgroundAudioPlaying;
	private int currentState;
	private ArrayList<State> states;
	private Game game;
	
	public StateManager(Game game) {
		this.game=game;
		isBackgroundAudioPlaying=false;
		currentState=0;
		states=new ArrayList<State>();
	}
	
	public void loadStates(){
		System.out.print("havocpixel.StateManager:INFO: Loading States.\n");
		states.add(new EmptyState(game));
		states.add(new PortalScreen(game));
		states.add(new MenuState(game));
		states.add(new BeginDemoWorldState(game));
		states.add(new DemoWorldState(game));
	}
	public void setCurrentState(int index){
		if(index>states.size()-1||index<0){
			System.out.print("havocpixel.StateManager:WARNING: Attempted to change to an invalid state "+index+".\n");
			currentState=0;
		} else {
			currentState=index;
		}
	}

	public boolean isBackgroundAudioPlaying() {
		return isBackgroundAudioPlaying;
	}
	public State $currentState() {
		if(states.get(currentState)==null){
			System.out.print("havocpixel.StateManager:WARNING: Attempted to load a null state "+currentState+".\n");
			currentState=0;
		}
		return states.get(currentState);
	}
	
	

}
