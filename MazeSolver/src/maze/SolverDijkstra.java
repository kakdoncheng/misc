package maze;

import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Map;

public class SolverDijkstra extends Solver{

	public SolverDijkstra(BufferedImage maze, Node start, Node end) {
		super(maze, start, end);
	}
	
	private Comparator<Integer> dijkstra=new Comparator<Integer>(){
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
		
		queue.sort(dijkstra);
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
		for(Map.Entry<Integer, Double> entry:n.$adjacent().entrySet()){
			Node a=nodes.get(entry.getKey());
			if(a.isMarked()){
				if(DEBUG)System.out.println("Skip "+a.$key());
				continue;
			}
			double nw=n.$weight()+entry.getValue();
			if(nw<a.$weight()){
				if(DEBUG)System.out.println("Adjust "+a.$key()+": "+a.$last()+" -> "+n.$key()+", "+a.$weight()+" -> "+(n.$weight()+entry.getValue()));
				a.adjust(n, nw);
			}else{
				if(DEBUG)System.out.println("Check "+a.$key()+": "+a.$last()+" -× "+n.$key()+", "+a.$weight()+" -× "+(n.$weight()+entry.getValue()));
			}
			if(a.equals(end)){
				if(DEBUG)System.out.println("Stop "+a.$key());
				stop=true;
			}
			img.setRGB(a.$x(), a.$y(), -4144960);
		}
		
		currentpath(n);
		n.mark();
		img.setRGB(n.$x(), n.$y(), -8355712);
		return true;
	}

}
