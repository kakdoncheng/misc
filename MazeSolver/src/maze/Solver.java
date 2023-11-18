package maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public abstract class Solver {
	
	public static boolean DEBUG=false;
	protected static void swap(List<Integer> list, int a, int b){
		int t=list.get(a);
		list.set(a, list.get(b));
		list.set(b, t);
	}
	protected static void shuffle(List<Integer> list){
		for(int i=0;i<list.size();i++){
			int s=(int)(Math.random()*list.size());
			if(i!=s){
				swap(list, i, s);
			}
		}
	}
	
	protected boolean stop;
	protected Map<Integer, Node> nodes;
	protected List<Integer> queue;
	protected Node start;
	protected Node end;
	protected BufferedImage img;
	protected BufferedImage overlay;
	
	public Solver(String path){
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		overlay=null;
		stop=false;
		nodes=new HashMap<Integer, Node>();
		queue=new ArrayList<Integer>();
		start=new Node(0, 1);
		end=new Node(img.getWidth()-1, img.getHeight()-2);
		start.adjust(null, 0);
		
		nodes.put(start.$key(), start);
		nodes.put(end.$key(), end);
		queue.add(start.$key());
	}
	
	public Solver(BufferedImage maze, Node start, Node end){
		img=maze;
		overlay=null;
		stop=false;
		nodes=new HashMap<Integer, Node>();
		queue=new ArrayList<Integer>();
		this.start=start;
		this.end=end;
		this.start.adjust(null, 0);
		
		nodes.put(start.$key(), start);
		nodes.put(end.$key(), end);
		queue.add(start.$key());
	}
	
	protected int checkAdjacent(Node n, int x, int y, double dx){
		Node e=new Node(x, y);
		n.adjacent(e.$key(), dx);
		if(nodes.getOrDefault(e.$key(), null)==null){
			queue.add(e.$key());
			nodes.put(e.$key(), e);
		}
		return e.$key();
	}
	
	public abstract boolean tick();
	
	public void endpath(){
		Node path=end;
		if(end.$last()==null){
			Graphics2D g2d=img.createGraphics();
			g2d.setColor(new Color(255,0,0));
			g2d.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
			g2d.dispose();
			return;
		}
		while(path!=null){
			img.setRGB(path.$x(), path.$y(), -16711936);//-65536
			path=path.$last();
		}
	}
	
	public void currentpath(Node n){
		overlay=new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Node path=n;
		while(path!=null){
			overlay.setRGB(path.$x(), path.$y(), -65536);//-256, -16711936, -8454144
			path=path.$last();
		}
	}
	
	public void save(String path){
		try {
			ImageIO.write(img, "png", new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isFinished(){
		return stop;
	}
	public BufferedImage $maze(){
		return img;
	}
	public BufferedImage $currentPath(){
		return overlay;
	}
	
}
