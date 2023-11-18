package havocpixel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int height, width;
	
	GraphicsDevice gd;
	private int Mwidth;
	private int Mheight;
	private boolean isFullscreen;
	
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
		return isFullscreen;
	}
	
	public Display(int width, int height, String title, boolean isFullScreen) {
		this.width = width;
		this.height = height;
		this.title = title;
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		Mwidth = gd.getDisplayMode().getWidth();
		Mheight = gd.getDisplayMode().getHeight();
		isFullscreen=isFullScreen;
		initFrame();	
	}
	
	
	private void initFrame() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIgnoreRepaint(true);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//frame.setIconImage(ImageLoader.loadImage("/txr/icon.png"));
		
		canvas = new Canvas();
		canvas.setFocusable(false);
		canvas.setIgnoreRepaint(true);
		canvas.setBackground(Color.BLACK);
		if(isFullscreen){
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
		frame.toFront();
		frame.requestFocus();
		//frame.setAlwaysOnTop(true);
		
		//hide cursor
		frame.setCursor(frame.getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new Point(),
                null));
		System.out.print("havocpixel.Display:INFO: Frame Initialized, Fullscreen is "+isFullscreen+".\n");
		
	}
	
}