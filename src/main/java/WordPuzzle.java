import java.util.ArrayList;
import java.util.Arrays;

public class WordPuzzle{

  public String makePuzzle(String phrase) {
    String vowels = "aeiouAEIOU";
    char[] toPuzzle = phrase.toCharArray();

    for (int index = 0; index < phrase.length(); index++) {
      if (vowels.indexOf(toPuzzle[index]) >= 0) {
        toPuzzle[index] = '-';
      }
    }

    String puzzle = new String(toPuzzle);
    return puzzle;
  }

  public String giveHint(String answer, String puzzle) {
    char[] answerChars = answer.toCharArray();
    char[] puzzleChars = puzzle.toCharArray();

    for (int index = 0; index < answer.length(); index++) {
      if (puzzleChars[index] != answerChars[index]) {
        puzzleChars[index] = answerChars[index];
        break;
      }
    }
    puzzle = new String(puzzleChars);
    return puzzle;
  }

  public String answerAttempt(String answer, String userAnswer) {
    String answerResult = "Try again";
    if (answer.toLowerCase().equals(userAnswer.toLowerCase())){
      answerResult = "You are correct";
    }
    return answerResult;
  }
}
