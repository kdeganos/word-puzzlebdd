import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class WordPuzzleTest extends FluentTest{
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
   public WebDriver getDefaultDriver() {
     return webDriver;
   }

  @ClassRule
  public static ServerRule server = new ServerRule();

 @Test
 public void rootTest() {
   goTo("http://localhost:4567/");
   assertThat(pageSource()).contains("will take a word or phrase");
 }

 @Test
 public void submitTest() {
   goTo("http://localhost:4567/");
    fill("#phrase").with("Believe you can and you're halfway there. Theodore Roosevelt");
    submit(".btn");
    assertThat(pageSource()).contains("B-l--v- y-- c-n -nd y--'r- h-lfw-y th-r-. Th--d-r- R--s-v-lt");
  }

 @Test
 public void hintTest() {
   goTo("http://localhost:4567/");
    fill("#phrase").with("Believe you can and you're halfway there. Theodore Roosevelt");
    submit(".btn");
    submit("#hintButton");
    assertThat(pageSource()).contains("Bel--v- y-- c-n -nd y--'r- h-lfw-y th-r-. Th--d-r- R--s-v-lt");
 }

 @Test
 public void answerTest() {
   goTo("http://localhost:4567/");
    fill("#phrase").with("Believe you can and you're halfway there. Theodore Roosevelt");
    submit(".btn");
    fill("#userAnswer").with("Believe you can and you're halfway there. Theodore Roosevelt");
    submit("#answerButton");
    assertThat(pageSource()).contains("You are correct");
 }

 @Test
 public void makePuzzle_returnsAStringAsIsWhenNoVowelsArePresent_NoChange() {
   WordPuzzle testWordPuzzle = new WordPuzzle();
   assertEquals("znmrp", testWordPuzzle.makePuzzle("znmrp"));
 }

  @Test
  public void makePuzzle_replacesVowelsWithDashes_StringWithDashesForVowels() {
    WordPuzzle testWordPuzzle = new WordPuzzle();
    assertEquals("B-l--v- y-- c-n -nd y--'r- h-lfw-y th-r-. Th--d-r- R--s-v-lt", testWordPuzzle.makePuzzle("Believe you can and you're halfway there. Theodore Roosevelt"));
  }

  @Test
  public void makePuzzle_replacesVowelsWithDashesCaseInsensitive_StringWithDashesForVowels() {
    WordPuzzle testWordPuzzle = new WordPuzzle();
    assertEquals("-ny v-w-l -s r-pl-c-d", testWordPuzzle.makePuzzle("Any vOwEl Is replaced"));
  }

  @Test
  public void giveHint_replacesASingleDashWithTheCorrectCharacter_OneVowelShows() {
    WordPuzzle testWordPuzzle = new WordPuzzle();
    assertEquals("Hell-", testWordPuzzle.giveHint("Hello", "H-ll-"));
  }

  @Test
  public void answerAttempt_comparesUserAnswerWithAnswer_YouAreCorrect() {
    WordPuzzle testWordPuzzle = new WordPuzzle();
    assertEquals("You are correct", testWordPuzzle.answerAttempt("Hello", "Hello"));
  }
}
