package katz;
import java.util.ArrayList;

public class WordFrequencies {
	private ArrayList<WordFrequency> nextWords;
	private int total;
	
	public class WordFrequency {
		private String word;
		private int count;

		public WordFrequency(String word) {
			this.word=word;
			this.count=1;
		}
		public void increment(){
			count++;
		}
		public String $word(){
			return word;
		}
		public int $count(){
			return count;
		}
		public String toString(){
			return $word()+"("+$count()+")";
		}

	}

	public WordFrequencies() {
		nextWords=new ArrayList<WordFrequency>();
		total=0;
	}
	
	public boolean addToNext(String word){
		for(WordFrequency u : nextWords){
			if(u.$word().equals(word)){
				u.increment();
				total++;
				return false;
			}
		}
		nextWords.add(new WordFrequency(word));
		total++;
		return true;
	}
	
	public double probabilityOf(String word){
		for(WordFrequency u : nextWords){
			if(u.$word().equals(word)){
				return 1.0*u.$count()/total;
			}
		}
		return -1;
	}
	
	public void incrementAll(){
		for(WordFrequency u : nextWords){
			u.increment();
			total++;
		}
	}
	
	public ArrayList<String> $words(){
		ArrayList<String> words=new ArrayList<String>();
		for(WordFrequency u : nextWords){
			words.add(u.$word());
		}
		return words;
	}
	
	public int $wordAmount(){
		return nextWords.size();
	}
	
	public String toString(){
		String next="";
		for(WordFrequency u:nextWords){
			next+=u+" ";
		}
		return next;
	}

}
