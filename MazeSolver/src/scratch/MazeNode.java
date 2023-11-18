package scratch;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

public class MazeNode {
	// xy pos
	public int x;
	public int y;
	// weight
	public double w;
	public double h;
	// trail
	public int l;
	public Map<Integer, Double> adj;
	
	public MazeNode(int x, int y){
		adj=new HashMap<Integer, Double>();
		this.x=x;
		this.y=y;
		this.w=Integer.MAX_VALUE;
		this.h=Integer.MAX_VALUE;
		this.l=-1;
	}
	
	private double distanceFromTo(double x1, double y1, double x2, double y2){
		double dx=x1-x2, dy=y1-y2;
		return Math.sqrt((dx*dx)+(dy*dy));
	}
	
	public int $key(){
		return (y<<16)|x;
	}
	public double distanceTo(MazeNode n){
		return distanceFromTo(x, y, n.x, n.y); 
	}
	
	private static int addAdjacentNode(Map<Integer, MazeNode> nodes, int n, int x, int y, double w){
		MazeNode e=new MazeNode(x, y);
		nodes.get(n).adj.put(e.$key(), w);
		if(nodes.getOrDefault(e.$key(), null)==null){
			nodes.put(e.$key(), e);
		}
		return e.$key();
	}
	
	public static void solve(){
		BufferedImage img;
		try {
			img = ImageIO.read(new File("C:\\Users\\Ben\\Desktop\\maze.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		long dt=System.currentTimeMillis();
		MazeNode start=new MazeNode(0,1);
		MazeNode end=new MazeNode(img.getWidth()-1,img.getHeight()-2);
		
		Map<Integer, MazeNode> nodes=new HashMap<Integer, MazeNode>();
		Set<Integer> marked=new HashSet<Integer>();
		List<Integer> queue=new ArrayList<Integer>();
		
		boolean found=false;
		start.w=0;
		nodes.put(start.$key(), start);
		nodes.put(end.$key(), end);
		queue.add(start.$key());
		
		while(!found&&!queue.isEmpty()){
			// turn queue into min-heap?
			/*
			int n=queue.get(0);
			for(int i=0;i<queue.size();i++){
				if(nodes.get(n).w>nodes.get(queue.get(i)).w){
					n=queue.get(i);
				}
			}
			//*/
			///*
			queue.sort(new Comparator<Integer>(){
				public int compare(Integer a, Integer b) {
					//dijkstra
					/*
					if(nodes.get(a).w<nodes.get(b).w){
						return -1;
					}
					if(nodes.get(a).w>nodes.get(b).w){
						return 1;
					}
					return 0;
					//*/
					
					// A*
					///*
					double ad=nodes.get(a).distanceTo(end);
					double ae=nodes.get(a).w+ad;
					double bd=nodes.get(b).distanceTo(end);
					double be=nodes.get(b).w+bd;
					if(ae<be){
						return -1;
					}else if(ae>be){
						return 1;
					}else{
						if(ad<bd){
							return -1;
						}else if(ad>bd){
							return 1;
						}else{
							if(nodes.get(a).w<nodes.get(b).w){
								return -1;
							}
							if(nodes.get(a).w>nodes.get(b).w){
								return 1;
							}
							return 0;
						}
					}
					//*/
					
				}
			});
			
			//for(int q:queue){
			//	System.out.println(q+" ("+nodes.get(q).x+", "+nodes.get(q).y+", "+nodes.get(q).l+", "+nodes.get(q).w+")");
			//}
			
			int n=queue.get(0);
			//*/
			System.out.println("For "+n+" ("+nodes.get(n).x+", "+nodes.get(n).y+", "+nodes.get(n).l+", "+nodes.get(n).w+"):");//, "+(nodes.get(n).w+nodes.get(n).distanceTo(end))+"):");
			
			if(nodes.get(n).x-1>-1&&(img.getRGB(nodes.get(n).x-1,nodes.get(n).y)&0xff)!=0){
				System.out.println("Left x-1 "+addAdjacentNode(nodes,n,nodes.get(n).x-1,nodes.get(n).y,1.0));
			}
			if(nodes.get(n).y-1>-1&&(img.getRGB(nodes.get(n).x,nodes.get(n).y-1)&0xff)!=0){
				System.out.println("Up y-1 "+addAdjacentNode(nodes,n,nodes.get(n).x,nodes.get(n).y-1,1.0));
			}
			if(nodes.get(n).x+1<img.getWidth()&&(img.getRGB(nodes.get(n).x+1,nodes.get(n).y)&0xff)!=0){
				System.out.println("Right x+1 "+addAdjacentNode(nodes,n,nodes.get(n).x+1,nodes.get(n).y,1.0));
			}
			if(nodes.get(n).y+1<img.getHeight()&&(img.getRGB(nodes.get(n).x,nodes.get(n).y+1)&0xff)!=0){
				System.out.println("Down y+1 "+addAdjacentNode(nodes,n,nodes.get(n).x,nodes.get(n).y+1,1.0));
			}
			
			// diagonals
			/*
			if(nodes.get(n).x-1>-1&&nodes.get(n).y-1>-1&&(img.getRGB(nodes.get(n).x-1,nodes.get(n).y-1)&0xff)!=0){
				System.out.println("Upleft x-1 y-1 "+addAdjacentNode(nodes,n,nodes.get(n).x-1,nodes.get(n).y-1,1.414));
			}
			if(nodes.get(n).x+1<img.getWidth()&&nodes.get(n).y-1>-1&&(img.getRGB(nodes.get(n).x+1,nodes.get(n).y-1)&0xff)!=0){
				System.out.println("Upright x+1 y-1 "+addAdjacentNode(nodes,n,nodes.get(n).x+1,nodes.get(n).y-1,1.414));
			}
			if(nodes.get(n).x+1<img.getWidth()&&nodes.get(n).y+1<img.getHeight()&&(img.getRGB(nodes.get(n).x+1,nodes.get(n).y+1)&0xff)!=0){
				System.out.println("Downright x+1 y+1 "+addAdjacentNode(nodes,n,nodes.get(n).x+1,nodes.get(n).y+1,1.414));
			}
			if(nodes.get(n).x-1>-1&&nodes.get(n).y+1<img.getHeight()&&(img.getRGB(nodes.get(n).x-1,nodes.get(n).y+1)&0xff)!=0){
				System.out.println("Downleft x-1 y+1 "+addAdjacentNode(nodes,n,nodes.get(n).x-1,nodes.get(n).y+1,1.414));
			}
			//*/
			
			for(Map.Entry<Integer, Double> a:nodes.get(n).adj.entrySet()){
				if(marked.contains(a.getKey())){
					System.out.println("Skip "+a.getKey());
					continue;
				}
				///*
				boolean add=true;
				for(int m:queue){
					if(a.getKey()==m){
						add=false;
						break;
					}
				}
				if(add){
					System.out.println("Add "+a.getKey());
					queue.add(a.getKey());
				}
				//*/
				if(nodes.get(n).w+a.getValue()<nodes.get(a.getKey()).w){
					System.out.println("Adjust "+a.getKey()+": "+nodes.get(a.getKey()).l+" -> "+n+", "+nodes.get(a.getKey()).w+" -> "+(nodes.get(n).w+a.getValue()));
					nodes.get(a.getKey()).w=nodes.get(n).w+a.getValue();
					nodes.get(a.getKey()).l=n;
				}else{
					System.out.println("Check "+a.getKey()+": "+nodes.get(a.getKey()).l+" -× "+n+", "+nodes.get(a.getKey()).w+" -× "+(nodes.get(n).w+a.getValue()));
				}
				if(a.getKey().equals(end.$key())){
					System.out.println("Stop "+end.$key());
					found=true;
				}
			}
			
			System.out.println("Done "+n+"\n");
			marked.add(n);
			queue.remove((Integer)n);
			
		}
		
		System.out.println((System.currentTimeMillis()-dt)+" ms");
		
		Color c=new Color(255,255,0);
		System.out.println(c.getRGB());
		for(MazeNode mn:nodes.values()){
			img.setRGB(mn.x, mn.y, c.getRGB());
		}
		c=new Color(128,128,128);
		System.out.println(c.getRGB());
		for(int mn:marked){
			img.setRGB(nodes.get(mn).x, nodes.get(mn).y, c.getRGB());
		}
		c=new Color(255,0,0);
		System.out.println(c.getRGB());
		int dxy=end.$key();
		/*
		double dw=256/(end.w*0.5);
		double cr=255;
		double cg=0;
		//*/
		while(dxy!=-1){
			/*
			if(cg<255){
				cg+=dw;
			}else{
				cr-=dw;
			}
			if(cr<0){
				cr=0;
			}
			if(cg>255){
				cg=255;
			}
			img.setRGB(nodes.get(dxy).x, nodes.get(dxy).y, new Color(0, (int)cr, (int)cg).getRGB());
			dxy=nodes.get(dxy).l;
			//*/
			img.setRGB(nodes.get(dxy).x, nodes.get(dxy).y, c.getRGB());
			dxy=nodes.get(dxy).l;
		}
		try {
			ImageIO.write(img, "png", new File("C:\\Users\\Ben\\Desktop\\solved.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		solve();
	}
}
