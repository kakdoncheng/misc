package havocpixel.states;

import havocpixel.Timer;
import havocpixel.main.Handler;

public class StateManager {
	private Handler hdlr;
	private State defaultState,pause,menu,portal,gameover,controls,mainportal;
	public StateManager(Handler hdlr){
		this.hdlr=hdlr;
	}
	public void loadStates(){
		defaultState=new DefaultWorldState(hdlr);
		pause=new Pause(hdlr);
		menu=new Menu(hdlr);
		portal=new DemoPortalScreen(hdlr);
		mainportal=new PortalScreen(hdlr);
		gameover=new GameOverScreen(hdlr);
		controls=new ControlInfo(hdlr);
	}
	
	public State $defWorld() {
		System.out.print("["+Timer.time()+"] Loading Default State;\n");
		return defaultState;
	}
	public State $controls() {
		System.out.print("["+Timer.time()+"] Loading Control Info;\n");
		return controls;
	}
	public State $death() {
		System.out.print("["+Timer.time()+"] YOU DIED;\n");
		return gameover;
	}
	public State $menu() {
		System.out.print("["+Timer.time()+"] Loading Menu;\n");
		return menu;
	}
	public State $portal() {
		System.out.print("["+Timer.time()+"] Loading Demo Portal Screen;\n");
		return portal;
	}
	public State $mainPortal() {
		System.out.print("["+Timer.time()+"] Loading Main Portal Screen;\n");
		return mainportal;
	}
	public State $pause() {
		System.out.print("["+Timer.time()+"] Game Paused;\n");
		return pause;
	}
	public void refreshGameStates(){
	}
}
