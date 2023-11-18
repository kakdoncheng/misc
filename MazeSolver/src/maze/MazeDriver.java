package maze;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class MazeDriver {
	
	private static BufferedImage deepcopy(BufferedImage bi) {
	    ColorModel cm = bi.getColorModel();
	    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
	    WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
	    return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	private static List<Solver> getNextDemo(int size, double rchance){
		int[][] raw;
		try {
			raw=new MazeGenerator(size, size, true).generate(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		
		BufferedImage maze=new BufferedImage(raw.length, raw[0].length, BufferedImage.TYPE_INT_ARGB);
		Node a=null;
		Node b=null;
		for(int y=0;y<raw.length;y++){
			for(int x=0;x<raw[y].length;x++){
				if(raw[y][x]<3){
					maze.setRGB(x, raw.length-1-y, -1);
					if(raw[y][x]==1){
						a=new Node(x, raw.length-1-y);
					}
					if(raw[y][x]==2){
						b=new Node(x, raw.length-1-y);
					}
				}else{
					maze.setRGB(x, raw.length-1-y, -16777216);
					if(Math.random()<rchance&&x>0&&x<raw[y].length-1&&y>0&&y<raw.length-1&&
							!(raw[y][x+1]>2&&raw[y+1][x]>2&&raw[y-1][x]>2&&raw[y][x-1]>2)){
							maze.setRGB(x, raw.length-1-y, -1);
					}
					
				}
				
			}
		}
		List<Solver> out=new ArrayList<Solver>();
		out.add(new SolverNaive(deepcopy(maze), new Node(a.$x(), a.$y()), new Node(b.$x(), b.$y())));
		out.add(new SolverDFS(deepcopy(maze), new Node(a.$x(), a.$y()), new Node(b.$x(), b.$y())));
		out.add(new SolverRFS(deepcopy(maze), new Node(a.$x(), a.$y()), new Node(b.$x(), b.$y())));
		out.add(new SolverA(deepcopy(maze), new Node(a.$x(), a.$y()), new Node(b.$x(), b.$y())));
		return out;
	}
	
	public static void main(String[] args){
		int size=100;
		double rchance=0.025;
		
		//Solver s=new Solver("C:\\Users\\Ben\\Desktop\\maze.png");
		List<Solver> s=getNextDemo(size, rchance);
		
		double z=4.0;
		int fw=(int)(s.get(0).$maze().getWidth()*z);
		int fh=(int)(s.get(0).$maze().getHeight()*z);
		
		JFrame frame=new JFrame();
		frame.setTitle("Solver");
		frame.setSize(fw*2, fh*2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIgnoreRepaint(true);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//frame.setIconImage(ImageLoader.loadImage("/txr/icon.png"));
		
		Canvas canvas = new Canvas();
		canvas.setFocusable(false);
		canvas.setIgnoreRepaint(true);
		canvas.setBackground(Color.BLACK);
		frame.setExtendedState(0);
		frame.setExtendedState(JFrame.NORMAL); 
		frame.setUndecorated(false);
		frame.setVisible(true);
		canvas.setPreferredSize(new Dimension(fw*2, fh*2));
		canvas.setMaximumSize(new Dimension(fw*2, fh*2));
		canvas.setMinimumSize(new Dimension(fw*2, fh*2));
		frame.add(canvas);
		frame.pack();
		frame.toFront();
		frame.requestFocus();
		
		canvas.createBufferStrategy(2);
		
		while(true){
			long l=System.currentTimeMillis();
			long lt=System.nanoTime();
			while(true){
				boolean stop=true;
				if(System.nanoTime()-lt>100000){
					lt=System.nanoTime();
					for(Solver a:s){
						if(a.tick()){
							stop=false;
						}
						if(a.isFinished()){
							a.endpath();
						}
					}
				}else{
					stop=false;
				}
				if(System.currentTimeMillis()-l<0){
					continue;
				}
				l=System.currentTimeMillis();
				Graphics2D g2=(Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			    g2.drawImage(s.get(0).$maze(), 0, 0, fw, fh, null);
			    if(!s.get(0).isFinished()){
			    	g2.drawImage(s.get(0).$currentPath(), 0, 0, fw, fh, null);
			    }
			    g2.drawImage(s.get(1).$maze(), fw, 0, fw, fh, null);
			    if(!s.get(1).isFinished()){
			    	g2.drawImage(s.get(1).$currentPath(), fw, 0, fw, fh, null);
			    }
			    g2.drawImage(s.get(2).$maze(), 0, fh, fw, fh, null);
			    if(!s.get(2).isFinished()){
			    	g2.drawImage(s.get(2).$currentPath(), 0, fh, fw, fh, null);
			    }
			    g2.drawImage(s.get(3).$maze(), fw, fh, fw, fh, null);
			    if(!s.get(3).isFinished()){
			    	g2.drawImage(s.get(3).$currentPath(), fw, fh, fw, fh, null);
			    }
			    canvas.getBufferStrategy().show();
			    g2.dispose();
			    if(stop){
			    	break;
			    }
				//try {
					//Thread.sleep(1);
				//} catch (Exception e) {
				//	e.printStackTrace();
				//}
			}
			long delay=System.currentTimeMillis();
			while(System.currentTimeMillis()-delay<2000){
				s.get(0).tick();
				if(s.get(0).isFinished()){
					s.get(0).endpath();
				}
				Graphics2D g2=(Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			    g2.drawImage(s.get(0).$maze(), 0, 0, fw, fh, null);
			    if(!s.get(0).isFinished()){
			    	g2.drawImage(s.get(0).$currentPath(), 0, 0, fw, fh, null);
			    }
			    canvas.getBufferStrategy().show();
			    g2.dispose();
			}
		    l=System.currentTimeMillis();
			//s.save("C:\\Users\\Ben\\Desktop\\solved.png");
			s=getNextDemo(size, rchance);
		}
	}

}
