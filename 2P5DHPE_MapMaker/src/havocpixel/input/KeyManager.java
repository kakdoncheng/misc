package havocpixel.input;

//import havocpixel.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	private boolean[] keys;
	public boolean up,down,left,right,space,suicide,qu0,qu1,I,Q,W,ESC,ENTER,ALT,SHIFT,d0;
	public KeyManager() {
		keys=new boolean[256];
	}
	
	public void tick() {
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
		ESC=keys[KeyEvent.VK_ESCAPE];
		ENTER=keys[KeyEvent.VK_ENTER];
		ALT=keys[KeyEvent.VK_ALT];
		SHIFT=keys[KeyEvent.VK_SHIFT];
		d0=keys[KeyEvent.VK_0];
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
