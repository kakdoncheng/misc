package markov;

/**
 * WordCountList is an ArrayList of WordCount objects.
 * @author Shannon Chu slc027@ucsd.edu
 * 3/15/2019
 * Reference PA7 writeup
 */

import java.util.ArrayList;

/**
 * This class defines methods we can call on a WordCountList object. For
 * example, we can use the method add(). WordCountList is an ArrayList of
 * WordCount objects, which consist of a String and an integer.
 * */
public class WordCountList {

    final static int NOT_FOUND = -1;
    ArrayList<WordCount> list;

    /* Do not forget method headers for all TODO methods*/

    /*
     * @param null
     * Constructs a WordCountList object, taking no parameters
     */
    public WordCountList() {
        //TODO
        list = new ArrayList<WordCount>();
    }

    /*
     * @param null
     * @return WordCount - ArrayList of WordCount objects to return
     * returns the list instance variable of a WordCountList
     */
    public ArrayList<WordCount> getList(){
        //TODO
        return list;
    }

    /*
     * @param word - string that we want to check for in the list
     * @return int - index at which we find word in list.
     *               returns -1 if not found
     * checks whether or not word exists in list or not. ignores case
     */
    private int check(String word) {
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).getWord().toLowerCase().equals(
                        word.toLowerCase())) {
                return index;
            }
        }
        return NOT_FOUND;
    }

    /*
     * @param word - string that we want to check for in the list
     * @return void
     * adds a word to the list instance variable in a WordCountList object.
     * ignores case. If word does not exist in the WordCountList object,
     * create a new WordCount object and new ArrayList list. If word
     * does exist, increment the count
     */
    public void add(String word) {
        //TODO
        if (word == null) {
            return;
        }
        int checkForWord = check(word.toLowerCase());
        if (checkForWord != NOT_FOUND) {
            list.get(checkForWord).increment();
        }
        else {
            list.add(new WordCount(word.toLowerCase()));
        }
    }

    /*
     * @param null
     * @return String - list of words and their counts from each
     * WordCount in the list
     * prints out, from each WordCount in the list, word (count)
     */
    public String toString() {
        //TODO
        try{
            StringBuilder string = new StringBuilder();
            for (int index = 0; index < list.size(); index++) {
                string.append(list.get(index).getWord().toLowerCase());
                string.append("(");
                string.append(list.get(index).getCount());
                string.append(")");
                string.append(" ");
            }
            if (string.length() > 0) {
                string.setLength(string.length() - 1);
            }
            return string.toString();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
