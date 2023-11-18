package markov;

/**
 * MarkovModel can
 * @author Shannon Chu slc027@ucsd.edu
 * 3/15/2019
 * Reference PA7 writeup
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class defines methods we can call on a MarkovModel object. It can
 * can generate text that looks similar to the original body of text.
 * */
public class MarkovModel {

    protected HashMap<String, WordCountList> predictionMap;

    protected int degree;
    protected Random random;
    protected boolean isWordModel;

    protected final static char DELIMITER = '\u0000';
    protected final static char NEW_LINE = '\n';
    protected final static char SPACE_CHAR = ' ';
    /* comment ALL methods in this file */

    /*
     * @param content -
     *
     */
    public MarkovModel (int degree, boolean isWordModel) {
      this.degree = degree;
      this.isWordModel = isWordModel;

      random = new Random();
      predictionMap = new HashMap<String, WordCountList>();
    }

    /*
     * @param filename -
     * @return void
     *
     */
    public void trainFromText(String filename) {

        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // `content` contains everything from the file, in one single string
        // TODO Wrap the training text and train the word or char model.
        String content1 = content.replace("\n"," ").replace("\r"," ");
        content1 = content1.toLowerCase();
        String[] contentArray = content.split("\\s+", 0);
        StringBuilder wrap = new StringBuilder("");

        if (isWordModel) {
          for (int index = 0; index < degree; index++) {
            wrap.append(SPACE_CHAR);
            wrap.append(contentArray[index]);
            //wrap.append(SPACE_CHAR);
          }
        }
        else if (isWordModel == false) {
          for (int index = 0; index < degree; index++){
            wrap.append(content.charAt(index));
          }
        }
        content1 += wrap.toString();

        if (isWordModel) {
          trainWordModel(content1);
        }else{
          trainCharacterModel(content1);
        }
    }

    /*
     * @param content -
     * @return void
     *
     */
     private void trainWordModel(String content){
           String[] contentArray = content.split("\\s+");
           StringBuilder prefix = new StringBuilder("");
           for(int i = 0; i < contentArray.length - degree; i++){
             prefix = new StringBuilder("");
             for (int index = 0; index < degree; index++) {
               prefix.append(contentArray[i + index]);
               prefix.append(DELIMITER);
             }
             String prefixString = prefix.toString();
             prefixString = prefixString.toLowerCase();
             String prediction = contentArray[i + degree];
             prediction = prediction.toLowerCase();

             if (predictionMap.containsKey(prefixString) == false) {
               predictionMap.put(prefixString, new WordCountList());
             }
             if (predictionMap.containsKey(prefixString)) {
               predictionMap.get(prefixString).add(prediction);
             }
             predictionMap.remove(NEW_LINE);
           }
         }

    /*
     * @param content -
     * @return void
     *
     */
    private void trainCharacterModel(String content){
      StringBuilder prefix = new StringBuilder("");
      for (int i = 0; i < content.length() - degree; i++) {
        prefix = new StringBuilder("");
        for (int index = 0; index < degree; index++){
          prefix.append(content.charAt(i + index));
          prefix.append(DELIMITER);
        }
        String prefixChars = prefix.toString();
        prefixChars = prefixChars.toLowerCase();
        String prediction = Character.toString(content.charAt(i + degree));
        prediction = prediction.toLowerCase();

        if (predictionMap.containsKey(prefixChars) == false) {
          predictionMap.put(prefixChars, new WordCountList());
        }
        if (predictionMap.containsKey(prefixChars)) {
          predictionMap.get(prefixChars).add(prediction);
        }
      }
    }

    /*
     * @param prefix -
     * @return ArrayList<String>
     *
     */
    public ArrayList<String> getFlattenedList(String prefix){
        // TODO Create a "flattened list" of predictions
        ArrayList<String> flatList = new ArrayList<String>();

        WordCountList wcl = predictionMap.get(prefix);
        ArrayList<WordCount> wordList = wcl.getList();

        String prediction = "";
        int predictionCount = 0;

        for (int index = 0; index < wordList.size(); index++) {
          prediction = wordList.get(index).getWord().toLowerCase();
          predictionCount = wordList.get(index).getCount();
          for (int i = 0; i < predictionCount; i++) {
            flatList.add(prediction);
          }
        }

        return flatList;
    }


    public String generateNext(String prefix) {
        // TODO
        ArrayList<String> prefixFlattenedList = getFlattenedList(prefix);
        int randomNumber = random.nextInt(prefixFlattenedList.size());
        return prefixFlattenedList.get(randomNumber);
    }

    public String generate(int count) {
        // TODO
        ArrayList<String> keys = new ArrayList<String>(predictionMap.keySet());

        String generatedString = keys.get(random.nextInt(keys.size()));
        String[] words = generatedString.split(DELIMITER+"");
        while(words.length < count) {
          String prefix = "";
          for(int amount = degree; amount > 0; amount--){
            prefix+=words[words.length-amount]+DELIMITER;
          }
          generatedString+=generateNext(prefix)+DELIMITER;
          words = generatedString.split(DELIMITER+"");
        }
        return generatedString.replaceAll(DELIMITER+"",isWordModel?" ":"");
    }


    @Override
    public String toString(){
        // TODO
        try{
            StringBuilder string = new StringBuilder();
            for (String key: predictionMap.keySet()) {
                string.append(key);
                string.append(": ");
                string.append(predictionMap.get(key).toString());
                string.append(NEW_LINE);
            }
            for (int index = 0; index < string.length(); index++) {
              if (string.charAt(index) == DELIMITER) {
                string.setCharAt(index, SPACE_CHAR);
              }
            }
            return string.toString();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
