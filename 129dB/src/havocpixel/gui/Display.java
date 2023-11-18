package havocpixel.gui;

import havocpixel.Timer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int height, width;
	
	GraphicsDevice gd;
	private int Mwidth;
	private int Mheight;
	private boolean full;
	
	public int $Mwidth(){
		return Mwidth;
	}
	public int $Mheight(){
		return Mheight;
	}
	
	public Canvas $canvas() {
		return canvas;
	}
	public JFrame $frame() {
		return frame;
	}
	public boolean isFullScreen(){
		return full;
	}
	
	public Display(int width, int height, String title, boolean isFullScreen) {
		this.width = width;
		this.height = height;
		this.title = title;
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		Mwidth = gd.getDisplayMode().getWidth();
		Mheight = gd.getDisplayMode().getHeight();
		full=isFullScreen;
		initFrame();	
	}
	
	
	private void initFrame() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//frame.setIconImage(ImageLoader.loadImage("/txr/icon.png"));
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
		canvas.setFocusable(false);
		canvas.setBackground(Color.BLACK);
		if(full){
			frame.setExtendedState(0);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setResizable(true);
			canvas.setPreferredSize(new Dimension(Mwidth, Mheight));
			canvas.setMaximumSize(new Dimension(Mwidth, Mheight));
			canvas.setMinimumSize(new Dimension(Mwidth, Mheight));
		}else{
			frame.setExtendedState(0);
			frame.setExtendedState(JFrame.NORMAL); 
			frame.setUndecorated(false);
			frame.setVisible(true);
			frame.setResizable(false);
			canvas.setPreferredSize(new Dimension(width, height));
			canvas.setMaximumSize(new Dimension(width, height));
			canvas.setMinimumSize(new Dimension(width, height));
		}
		frame.add(canvas);
		frame.pack();
		System.out.print("["+Timer.time()+"] Frame Initialized;\n");
		
	}
	
}