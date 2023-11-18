package havocpixel.input;

//import havocpixel.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	public boolean[] keys;
	public boolean up,down,left,right,space,suicide,qu0,qu1,I,Q,W,T,ESC,ENTER,ALT,SHIFT,d0,d2,d3,d4,d5,d6,d7,d8,d9;
	private boolean anyKeyPressed;
	public KeyManager() {
		keys=new boolean[256];
	}
	
	public void tick() {
		anyKeyPressed=false;
		for(boolean u:keys)
			if(u)
				anyKeyPressed=true;
		up=keys[KeyEvent.VK_UP];
		left=keys[KeyEvent.VK_LEFT];
		down=keys[KeyEvent.VK_DOWN];
		right=keys[KeyEvent.VK_RIGHT];
		space=keys[KeyEvent.VK_SPACE];
		suicide=keys[KeyEvent.VK_1];
		qu0=keys[KeyEvent.VK_R];
		qu1=keys[KeyEvent.VK_E];
		I=keys[KeyEvent.VK_I];
		Q=keys[KeyEvent.VK_Q];
		W=keys[KeyEvent.VK_W];
		T=keys[KeyEvent.VK_T];
		ESC=keys[KeyEvent.VK_ESCAPE];
		ENTER=keys[KeyEvent.VK_ENTER];
		ALT=keys[KeyEvent.VK_ALT];
		SHIFT=keys[KeyEvent.VK_SHIFT];
		d0=keys[KeyEvent.VK_0];
		d2=keys[KeyEvent.VK_2];
		d3=keys[KeyEvent.VK_3];
		d4=keys[KeyEvent.VK_4];
		d5=keys[KeyEvent.VK_5];
		d6=keys[KeyEvent.VK_6];
		d7=keys[KeyEvent.VK_7];
		d8=keys[KeyEvent.VK_8];
		d9=keys[KeyEvent.VK_9];
	}
	
	public boolean isAnyKeyPressed(){
		return anyKeyPressed;
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]=true;
		//System.out.print("["+Timer.time()+"] Key Pressed "+e.getKeyCode()+";\n");
	}
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]=false;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
}
