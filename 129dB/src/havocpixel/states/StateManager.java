package havocpixel.states;

import havocpixel.Timer;
import havocpixel.main.Handler;

public class StateManager {
	private Handler hdlr;
	private State single,arena,menu,portal,gameover,controls;
	public StateManager(Handler hdlr){
		this.hdlr=hdlr;
	}
	public void loadStates(){
		menu=new Menu(hdlr);
		arena=new Arena(hdlr);
		single=new SingleArena(hdlr);
		controls=new ControlInfo(hdlr);
		portal=new Client(hdlr);
	}
	
	public State $singleArena() {
		System.out.print("["+Timer.time()+"] Loading Single Arena;\n");
		return single;
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
	public State $client() {
		System.out.print("["+Timer.time()+"] Loading Client;\n");
		return portal;
	}
	public Arena $arena() {
		System.out.print("["+Timer.time()+"] Switching to Arena;\n");
		return (Arena)arena;
	}
	public void refreshGameStates(){
	}
}
