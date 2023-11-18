package markov;


public class WordCount {

	private String word;
	private int count;

	public WordCount(String w) {
		word = w;
		count = 1;
	}

	public String getWord() {
		return word;
	}

	public int getCount() {
		return count;
	}

	public void increment() {
		count++;
	}

	public void setCount(int c) {
		count = c;
	}

	public String toString() {
		return word + " : " + count;
	}
}
