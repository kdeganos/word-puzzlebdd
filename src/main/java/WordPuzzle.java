import java.util.ArrayList;
import java.util.Arrays;

public class WordPuzzle{

  public String makePuzzle (String phrase) {
    String vowels = "aeiouAEIOU";
    char[] toPuzzle = phrase.toCharArray();

    for (int index = 0; index < phrase.length(); index++){
      if (vowels.indexOf(toPuzzle[index]) >= 0) {
        toPuzzle[index] = '-';
      }
    }

    String puzzled = new String(toPuzzle);
    return puzzled;
   }
}
