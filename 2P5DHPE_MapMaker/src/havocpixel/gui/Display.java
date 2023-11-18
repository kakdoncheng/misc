package havocpixel.gui;

import havocpixel.Timer;
import havocpixel.util.Utils;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int height, width;
	
	public Canvas $canvas() {
		return canvas;
	}
	public JFrame $frame() {
		return frame;
	}
	
	public Display(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		initFrame();	
	}
	private void initFrame() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowListener(){
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				//Utils.newProcess("");
				System.out.print("["+Timer.time()+"] [WARNING] Thread shutdown; Main game loop stopped;\n");
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBackground(Color.BLACK);
		
		frame.add(canvas);
		frame.pack();
		System.out.print("["+Timer.time()+"] Frame Initialized;\n");
		
	}
	
}