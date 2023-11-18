package scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PathNode{
	public static HashMap<String, PathNode> nodes=new HashMap<String, PathNode>();
	public int weight;
	public String prev;
	public Map<String, Integer> adjacent;
	public PathNode(String name){
		this.weight=Integer.MAX_VALUE;
		this.prev=null;
		this.adjacent=new HashMap<String, Integer>();
		nodes.put(name, this);
	}
	public void addAdjacent(String node, int distanceTo){
		adjacent.put(node, distanceTo);
	}
}

public class Dijkstra {
	
	public static void findShortestPath(String start, String end){
		List<String> marked=new ArrayList<String>();
		List<String> queue=new ArrayList<String>();
		
		PathNode.nodes.get(start).weight=0;
		queue.add(start);
		
		boolean found=false;
		while(!queue.isEmpty()&&!found){
			
			// pick node from queue with min weight
			String n=queue.get(0);
			for(int i=1;i<queue.size();i++){
				if(PathNode.nodes.get(n).weight>PathNode.nodes.get(queue.get(i)).weight){
					n=queue.get(i);
				}
			}
			System.out.println("For "+n+" ("+PathNode.nodes.get(n).prev+", "+PathNode.nodes.get(n).weight+"):");
			
			// process node
			for(Map.Entry<String, Integer> adj:PathNode.nodes.get(n).adjacent.entrySet()){
				// skip marked nodes
				boolean skip=false;
				for(String m:marked){
					if(adj.getKey().equals(m)){
						skip=true;
						break;
					}
				}
				if(skip){
					System.out.println("Skip "+adj.getKey());
					continue;
				}
				
				// add to queue if not already added
				boolean add=true;
				for(String m:queue){
					if(adj.getKey().equals(m)){
						add=false;
						break;
					}
				}
				if(add){
					System.out.println("Add "+adj.getKey());
					queue.add(adj.getKey());
				}
				
				if(PathNode.nodes.get(n).weight+adj.getValue()<PathNode.nodes.get(adj.getKey()).weight){
					System.out.println("Adjust "+adj.getKey()+": "+PathNode.nodes.get(adj.getKey()).prev+" -> "+n+", "+(PathNode.nodes.get(adj.getKey()).weight==Integer.MAX_VALUE?"∞":PathNode.nodes.get(adj.getKey()).weight)+" -> "+(PathNode.nodes.get(n).weight+adj.getValue()));
					PathNode.nodes.get(adj.getKey()).weight=PathNode.nodes.get(n).weight+adj.getValue();
					PathNode.nodes.get(adj.getKey()).prev=n;
				}else{
					System.out.println("Check "+adj.getKey()+": "+PathNode.nodes.get(adj.getKey()).prev+" -× "+n+", "+(PathNode.nodes.get(adj.getKey()).weight==Integer.MAX_VALUE?"∞":PathNode.nodes.get(adj.getKey()).weight)+" -× "+(PathNode.nodes.get(n).weight+adj.getValue()));
				}
				if(adj.getKey().equals(end)){
					System.out.println("Found "+end);
					found=true;
				}
			}
			
			// mark node to avoid processing it again
			System.out.println("Done "+n+"\n");
			queue.remove(n);
			marked.add(n);
		}
		
		String path=end;
		if(PathNode.nodes.get(end).prev==null){
			System.out.println("No path found.");
			return;
		}
		String ln=end;
		while(PathNode.nodes.get(ln).prev!=null){
			ln=PathNode.nodes.get(ln).prev;
			path+=" <- "+ln;
		}
		System.out.println(path);
	}
	
	public static void main(String[] args){
		new PathNode("S");
		PathNode.nodes.get("S").addAdjacent("A", 7);
		PathNode.nodes.get("S").addAdjacent("B", 2);
		PathNode.nodes.get("S").addAdjacent("C", 3);
		
		new PathNode("A");
		PathNode.nodes.get("A").addAdjacent("S", 7);
		PathNode.nodes.get("A").addAdjacent("B", 3);
		PathNode.nodes.get("A").addAdjacent("D", 4);
		
		new PathNode("B");
		PathNode.nodes.get("B").addAdjacent("S", 2);
		PathNode.nodes.get("B").addAdjacent("A", 3);
		PathNode.nodes.get("B").addAdjacent("D", 4);
		PathNode.nodes.get("B").addAdjacent("H", 1);
		
		new PathNode("C");
		PathNode.nodes.get("C").addAdjacent("S", 3);
		PathNode.nodes.get("C").addAdjacent("L", 2);
		
		new PathNode("D");
		PathNode.nodes.get("D").addAdjacent("A", 4);
		PathNode.nodes.get("D").addAdjacent("B", 4);
		PathNode.nodes.get("D").addAdjacent("F", 5);
		
		new PathNode("F");
		PathNode.nodes.get("F").addAdjacent("D", 5);
		PathNode.nodes.get("F").addAdjacent("H", 3);
		
		new PathNode("G");
		PathNode.nodes.get("G").addAdjacent("H", 2);
		PathNode.nodes.get("G").addAdjacent("E", 2);
		
		new PathNode("H");
		PathNode.nodes.get("H").addAdjacent("B", 1);
		PathNode.nodes.get("H").addAdjacent("F", 3);
		PathNode.nodes.get("H").addAdjacent("G", 2);
		
		new PathNode("I");
		PathNode.nodes.get("I").addAdjacent("L", 4);
		PathNode.nodes.get("I").addAdjacent("J", 6);
		PathNode.nodes.get("I").addAdjacent("K", 4);
		
		new PathNode("J");
		PathNode.nodes.get("J").addAdjacent("I", 6);
		PathNode.nodes.get("J").addAdjacent("L", 4);
		PathNode.nodes.get("J").addAdjacent("K", 4);
		
		new PathNode("K");
		PathNode.nodes.get("K").addAdjacent("I", 4);
		PathNode.nodes.get("K").addAdjacent("J", 4);
		PathNode.nodes.get("K").addAdjacent("E", 5);
		
		new PathNode("L");
		PathNode.nodes.get("L").addAdjacent("C", 2);
		PathNode.nodes.get("L").addAdjacent("I", 4);
		PathNode.nodes.get("L").addAdjacent("J", 4);
		
		new PathNode("E");
		PathNode.nodes.get("E").addAdjacent("G", 2);
		PathNode.nodes.get("E").addAdjacent("K", 5);
		
		findShortestPath("S", "E");
	}
}
