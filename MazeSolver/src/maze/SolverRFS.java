package maze;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolverRFS extends Solver{

	public SolverRFS(BufferedImage maze, Node start, Node end) {
		super(maze, start, end);
	}
	
	protected int checkAdjacent(Node n, int x, int y, double dx){
		Node e=new Node(x, y);
		n.adjacent(e.$key(), dx);
		if(nodes.getOrDefault(e.$key(), null)==null){
			//queue.add(e.$key());
			nodes.put(e.$key(), e);
		}
		return e.$key();
	}
	
	public boolean tick(){
		if(stop || queue.isEmpty()){
			return false;
		}
		
		Node n=nodes.get(queue.remove(queue.size()-1));
		
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
		for(Map.Entry<Integer, Double> entry:n.$adjacent().entrySet()){
			Node a=nodes.get(entry.getKey());
			if(a.isMarked()){
				if(DEBUG)System.out.println("Skip "+a.$key());
				continue;
			}
			if(DEBUG)System.out.println("Adjust "+a.$key()+": "+a.$last()+" -> "+n.$key()+", "+a.$weight()+" -> "+(n.$weight()+entry.getValue()));
			a.adjust(n, n.$weight()+entry.getValue());
			if(!queue.contains(a.$key())){
				choices.add(a.$key());
			}
			if(a.equals(end)){
				if(DEBUG)System.out.println("Stop "+a.$key());
				stop=true;
			}
			img.setRGB(a.$x(), a.$y(), -4144960);
		}
		shuffle(choices);
		for(int c:choices){
			queue.add(c);
		}
		
		currentpath(n);
		n.mark();
		img.setRGB(n.$x(), n.$y(), -8355712);
		return true;
	}
}
