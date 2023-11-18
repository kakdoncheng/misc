package katz;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class TrigramModel {
	
	public static final String START="«START»",END="«END»";
	
	private ArrayList<String> possibleWords;
	private WordFrequencies unigram;
	private HashMap<String, WordFrequencies> bigram, trigram;

	public TrigramModel() {
		unigram=new WordFrequencies();
		bigram=new HashMap<String, WordFrequencies>();
		trigram=new HashMap<String, WordFrequencies>();
	}
	
	private String reformatParse(String in){
		String chars="`~!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?¿¡";
		String out=in.replace("»", "").replace("«", "").replace("\n", " ").replace("\r", " ").replace("\t", " ");//.toLowerCase();
		for(int i=0;i<chars.length();i++){
			System.out.print(chars.charAt(i));
			out=out.replace(chars.charAt(i)+" ", " «"+i+"»» ");
			out=out.replace(" "+chars.charAt(i), " ««"+i+"» ");
			out=out.replace(chars.charAt(i)+"", " «"+i+"» ");
		}
		return out;
	}
	
	private String reverseReformat(String in){
		String chars="`~!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?¿¡";
		for(int i=0;i<chars.length();i++){
			//in=in.replace(" «"+i+"»» ",chars.charAt(i)+" ");
			//in=in.replace(" ««"+i+"» "," "+chars.charAt(i));
			//in=in.replace(" «"+i+"» ",chars.charAt(i)+"");
			in=in.replace("«"+i+"»»",chars.charAt(i)+" ");
			in=in.replace("««"+i+"»"," "+chars.charAt(i));
			in=in.replace("«"+i+"»",chars.charAt(i)+"");
		}
		return in;
	}
	
	public void parseFromText(String filename){
		System.out.print("Parsing Data... ");
		String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        content = reformatParse(content);
        String[] contentArray = content.split("\\s+", 0);
        ArrayList<String> words=new ArrayList<String>();
        for(int i=0; i<contentArray.length; i++){
        	words.add(contentArray[i]);
        }
        System.out.println("Done.");
        trainWordModel(words);
        addOneSmoothing();
	}
	
	public void trainWordModel(ArrayList<String> words){
		System.out.print("Training from data... ");
		for(int i=0; i<words.size()-1; i++){
			String prefix=words.get(i);
			String word=words.get(i+1);
			if(i>0&&trigram.containsKey(words.get(i-1)+" "+prefix)){
				trigram.get(words.get(i-1)+" "+prefix).addToNext(word);
			}else if(bigram.containsKey(prefix)){
				bigram.get(prefix).addToNext(word);
				WordFrequencies w=new WordFrequencies();
				w.addToNext(word);
				if(i>0)
					trigram.put(words.get(i-1)+" "+prefix, w);
			}else{
				unigram.addToNext(word);
				WordFrequencies w=new WordFrequencies();
				w.addToNext(word);
				WordFrequencies wt=new WordFrequencies();
				wt.addToNext(word);
				bigram.put(prefix, w);
				if(i>0)
					trigram.put(words.get(i-1)+" "+prefix, wt);
			}
		}
		possibleWords=unigram.$words();
		System.out.println("Done.");
	}
	
	public void addOneSmoothing(){
		System.out.print("Refining (+1)... ");
		unigram.incrementAll();
		for (HashMap.Entry<String, WordFrequencies> entry : bigram.entrySet()) {
		    if(entry.getValue().$wordAmount()>1){
		    	entry.getValue().incrementAll();
		    }
		}
		//for (HashMap.Entry<String, WordFrequencies> entry : trigram.entrySet()) {
		//	if(entry.getValue().$wordAmount()>1){
		//    	entry.getValue().incrementAll();
		//    }
		//}
		System.out.println("Done.");
	}
	
	public double probabilityOfNextWordBeing(String word, String[] prefix){
		if(trigram.containsKey(prefix[0]+" "+prefix[1])){
			double p=trigram.get(prefix[0]+" "+prefix[1]).probabilityOf(word);
			if(p>0){
				return p;
			}
		}
		if(bigram.containsKey(prefix[1])){
			double p=bigram.get(prefix[1]).probabilityOf(word);
			if(p>0){
				return p*0.5;
			}
		}
		return unigram.probabilityOf(word)*0.25;
	}
	
	private class WordP{
		private String word;
		private double p;
		public WordP(String word, double p){
			this.p=p;
			this.word=word;
		}
		public String $word(){
			return word;
		}
		public double $p(){
			return p;
		}
	}
	
	public String generateNextWord(String[] prefix){
		ArrayList<WordP> list=new ArrayList<WordP>();
		for(String word:possibleWords){
			list.add(new WordP(word, probabilityOfNextWordBeing(word, prefix)));
		}
		double tp=0;
		for(WordP wp:list){
			tp+=wp.$p();
		}
		double guess=Math.random()*tp,weight=0;
		for(WordP wp:list){
			weight+=wp.$p();
			if(weight>guess){
				return wp.$word();
			}
		}
		return null;
	}
	
	public String generateNextNWords(int n){
		System.out.println("\nGenerating text... \n");
		String[] prefix=new String[2];
		prefix[0]="«28»»";
		prefix[1]="«28»»";
		String output=" ";
		for(int i=0;i<n;i++){
			String word=generateNextWord(prefix);
			System.out.print(word+" ");
			output+=word+" ";
			prefix[0]=prefix[1];
			prefix[1]=word;
		}
		output=reverseReformat(output.replace("» ", "»").replace(" «", "«"));
		System.out.println("\n\nOutput: \n\n"+output+"\n\n");
		return output;
		//System.out.println("\n\nOutput: \n\n"+reverseReformat(output)+"\n\n");
		//return reverseReformat(output).replace("  "," ");
	}
	
	public void printModel(){
		System.out.println("No prefix. -> "+unigram);
		for (HashMap.Entry<String, WordFrequencies> entry : bigram.entrySet()) {
		    System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
		for (HashMap.Entry<String, WordFrequencies> entry : trigram.entrySet()) {
		    System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
	}
	
	public static void main(String[] args){
		TrigramModel b=new TrigramModel();
		b.parseFromText("GrimmsTales.txt");
		b.printModel();
		b.generateNextNWords(500);
	}

}
