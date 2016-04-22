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

 // @Test
 // public void rootTest() {
 //   goTo("http://localhost:4567/");
 //    fill("#???").with("?");
 //    submit(".btn");
 //    assertThat(pageSource()).contains("what you want to be found related to your method");
 // }

 @Test
 public void WordPuzzle_returnsAStringAsIsWhenNoVowelsArePresent_NoChange() {
   WordPuzzle testWordPuzzle = new WordPuzzle();
   assertEquals("znmrp", testWordPuzzle.makePuzzle("znmrp"));
 }

  @Test
  public void WordPuzzle_replacesVowelsWithDashes_StringWithDashesForVowels() {
    WordPuzzle testWordPuzzle = new WordPuzzle();
    assertEquals("B-l--v- y-- c-n -nd y--'r- h-lfw-y th-r-. Th--d-r- R--s-v-lt", testWordPuzzle.makePuzzle("Believe you can and you're halfway there. Theodore Roosevelt"));
  }
  @Test
  public void WordPuzzle_replacesVowelsWithDashesCaseInsensitive_StringWithDashesForVowels() {
    WordPuzzle testWordPuzzle = new WordPuzzle();
    assertEquals("-ny -m-rg-ncy", testWordPuzzle.makePuzzle("Any emErgency"));
  }
}
