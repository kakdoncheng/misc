package maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SolverNaive extends Solver{

	//private long dt=System.currentTimeMillis();
	
	public SolverNaive(BufferedImage maze, Node start, Node end) {
		super(maze, start, end);
		end.adjust(null, -1);
	}
	
	protected int checkAdjacent(Node n, int x, int y, double dx){
		Node e=new Node(x, y);
		e.adjust(null, 0);
		n.adjacent(e.$key(), dx);
		if(nodes.getOrDefault(e.$key(), null)==null){
			nodes.put(e.$key(), e);
		}
		return e.$key();
	}
	
	private Comparator<Integer> avoidlast=new Comparator<Integer>(){
		public int compare(Integer a, Integer b) {
			Node aa=nodes.get(a);
			Node bb=nodes.get(b);
			if(aa.$weight()<bb.$weight()){
				return -1;
			}
			if(aa.$weight()>bb.$weight()){
				return 1;
			}
			return 0;
		}
	};
	
	public boolean tick(){
		if(stop || queue.isEmpty()){
			return false;
		}
		
		Node n=nodes.get(queue.remove(0));
		
		if(DEBUG)System.out.println("For "+n+":");
		if(n.$x()-1>-1&&(img.getRGB(n.$x()-1,n.$y())&0xff)!=0){
			int a=checkAdjacent(n,n.$x()-1,n.$y(),1.0);
			if(DEBUG)System.out.println("Left x-1 "+a);
		}
		if(n.$y()-1>-1&&(img.getRGB(n.$x(),n.$y()-1)&0xff)!=0){
			int a=checkAdjacent(n,n.$x(),n.$y()-1,1.0);
			if(DEBUG)System.out.println("Up y-1 "+a);
		}
		if(n.$x()+1<img.getWidth()&&(img.getRGB(n.$x()+1,n.$y())&0xff)!=0){
			int a=checkAdjacent(n,n.$x()+1,n.$y(),1.0);
			if(DEBUG)System.out.println("Right x+1 "+a);
		}
		if(n.$y()+1<img.getHeight()&&(img.getRGB(n.$x(),n.$y()+1)&0xff)!=0){
			int a=checkAdjacent(n,n.$x(),n.$y()+1,1.0);
			if(DEBUG)System.out.println("Down y+1 "+a);
		}
		List<Integer> choices=new ArrayList<Integer>();
		for(int k:n.$adjacent().keySet()){
			choices.add(k);
		}
		shuffle(choices);
		choices.sort(avoidlast);
		queue.add(choices.get(0));
		
		if(n.equals(end)){
			stop=true;
		}
		//n.mark();
		n.adjust(null, n.$weight()+1);
		currentpath(n);
		
		//for(Node m:nodes.values()){
		//	if(System.currentTimeMillis()-m.$weight()>10){
		//		nodes.remove(m);
		//	}
		//}
		
		img.setRGB(n.$x(), n.$y(), -8355712);
		return false;
	}
	
	public void endpath(){
		img.setRGB(end.$x(), end.$y(), -8355712); //-16711936
		Graphics2D g2d=img.createGraphics();
		g2d.setColor(new Color(0,255,0));
		g2d.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
		g2d.dispose();
	}
	
	public void currentpath(Node path){
		Color c=new Color(255,0,0);
		overlay=new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		overlay.setRGB(path.$x(), path.$y(), c.getRGB());
		Graphics2D g=overlay.createGraphics();
		g.setColor(c);
		g.fillRect(path.$x()-1, path.$y()-1, 3, 3);
		g.dispose();
	}
}
