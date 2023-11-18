package maze;

import java.util.HashMap;
import java.util.Map;

public class Node {
	private boolean m;
	private int x;
	private int y;
	private double w;
	private Node l;
	private Map<Integer, Double> a;
	
	public Node(int x, int y){
		this.m=false;
		this.x=x;
		this.y=y;
		this.w=Integer.MAX_VALUE;
		this.l=null;
		this.a=new HashMap<Integer, Double>();
	}
	
	public static int keyOf(int x, int y){
		return (y<<16)|x;
	}
	public double distanceTo(Node n){
		double dx=x-n.$x(), dy=y-n.$y();
		return Math.sqrt((dx*dx)+(dy*dy));
	}
	
	public int $key(){
		return keyOf(x, y);
	}
	public int $x(){
		return x;
	}
	public int $y(){
		return y;
	}
	public double $weight(){
		return w;
	}
	public boolean isMarked(){
		return m;
	}
	public Node $last(){
		return l;
	}
	public Map<Integer, Double> $adjacent(){
		return a;
	}
	
	public void adjust(Node last, double weight){
		l=last;
		w=weight;
	}
	public void mark(){
		m=true;
	}
	public void adjacent(int n, double dx){
		a.put(n, dx);
	}
	public String toString(){
		return $key()+" ("+x+", "+y+", "+w+")";
	}
	
}
