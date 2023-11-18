package havocpixel.input;

import havocpixel.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	private boolean leftPressed,rightPressed;
	private int x,y;
	private UIManager uim;
	
	public MouseManager() {
		
	}
	
	public void setUIManager(UIManager uim){
		this.uim=uim;
	}
	
	public boolean isLeftPressed(){
		return leftPressed;
	}
	public boolean isRightPressed(){
		return rightPressed;
	}
	public int $x(){
		return x;
	}
	public int $y(){
		return y;
	}
	
	public void tick(){
		
	}
	public void mouseDragged(MouseEvent e) {
		
	}
	public void mouseMoved(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		//System.out.print("["+Timer.time()+"] Hover;\n");
		if(uim!=null)
			uim.onMouseMove(e);
	}
	public void mouseClicked(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){
			leftPressed=true;
		}else if(e.getButton()==MouseEvent.BUTTON3){
			rightPressed=true;
		}
		
	}
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){
			leftPressed=false;
		}else if(e.getButton()==MouseEvent.BUTTON3){
			rightPressed=false;
		}
		if(uim!=null)
			uim.onMouseReleased(e);
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}

}
