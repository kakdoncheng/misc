package scratch;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Sorting {
	
	public static ArrayList<Integer> getRandomList(int length, int min, int max){
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i=0;i<length;i++){
			a.add((int)(Math.random()*(max-min)+min));
		}
		return a;
	}
	public static void print(List<Integer> list){
		for(int i:list){
			System.out.print(i+",");
		}
		System.out.println();
	}
	public static void swap(List<Integer> list, int a, int b){
		int t=list.get(a);
		list.set(a, list.get(b));
		list.set(b, t);
	}
	public static boolean validate(List<Integer> list){
		for(int i=0;i<list.size()-1;i++){
			if(list.get(i)>list.get(i+1)){
				return false;
			}
		}
		return true;
	}
	
	// insertion sort
	public static void insertionsort(List<Integer> list){
		for(int i=1;i<list.size();i++){
			int a=i;
			while(a>0 && list.get(a)<list.get(a-1)){
				swap(list, a, a-1);
				a--;
			}
		}
	}
	
	// heapsort
	// in a heap, for node n
	// children: 2n+1, 2n+2
	// parent: (n-1)/2
	public static void sink(List<Integer> list, int i, int e){
		int a=i; // current node
		while(a<e){
			int b=(2*a)+1; // child 1
			int c=(2*a)+2; // child 2
			int d=a; // max
			if(b<e && list.get(d)<list.get(b)){
				d=b;
			}
			if(c<e && list.get(d)<list.get(c)){
				d=c;
			}
			if(a==d){
				break;
			}
			swap(list, a, d);
			a=d;
		}
	}
	public static void heapsort(List<Integer> list){
		// cap
		int e=list.size()-1;
		// reorganize to valid max heap
		for(int i=e;i>-1;i--){
			sink(list, i, e);
		}
		// swap 0 & e, sink down until e
		while(e>0){
			swap(list, 0, e);
			sink(list, 0, e);
			e--;
		}
	}
	
	// mergesort
	public static List<Integer> mergeSortedLists(List<Integer> a, List<Integer> b){
		List<Integer> list=new ArrayList<Integer>();
		int c=0; // a index
		int d=0; // b index
		while(c<a.size() || d<b.size()){
			if(c<a.size() && d<b.size()){
				if(a.get(c)<=b.get(d)){
					list.add(a.get(c));
					c++;
				}else{
					list.add(b.get(d));
					d++;
				}
			}else{
				if(d>=b.size()){
					list.add(a.get(c));
					c++;
				}else{
					list.add(b.get(d));
					d++;
				}
			}
		}
		return list;
	}
	public static List<Integer> mergesort(List<Integer> list){
		// list of size 1 or 0 is already sorted
		if(list.size()<2){
			return list;
		}
		// split list in two
		List<Integer> a=mergesort(list.subList(0, list.size()/2));
		List<Integer> b=mergesort(list.subList(list.size()/2, list.size()));
		// merge lists together
		return mergeSortedLists(a, b);
	}
	
	// quicksort
	public static void quicksort(List<Integer> list, int start, int end){
		int a=start;
		int b=end;
		if(end-start<1){
			return;
		}
		while(a<b){
			if(list.get(a)>list.get(b)){
				if(b-a!=1){
					swap(list, b, b-1);
				}
				swap(list, a, b);
				b--;
			}else{
				a++;
			}
			
		}
		quicksort(list, start, b-1);
		quicksort(list, b, end);
	}
	public static void quicksort(List<Integer> list){
		quicksort(list, 0, list.size()-1);
	}
	
	public static void main(String[] args){
		System.out.println(new Color(0,255,0).getRGB());
		for(int n=100;n<1000000;n*=2){
			List<Integer> list=getRandomList(n,-10000,10000);
			//list=new ArrayList<Integer>(Arrays.asList(3,7,8,5,2,1,9,5,4));
			//print(list);
			long t=System.currentTimeMillis();
			//insertionsort(list);
			heapsort(list);
			//quicksort(list);
			//list=mergesort(list);
			t=System.currentTimeMillis()-t;
			//print(list);
			System.out.println(n+": "+t+" ms, "+(validate(list)&&list.size()==n));
		}
	}
}
