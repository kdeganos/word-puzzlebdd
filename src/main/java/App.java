import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");

    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/output", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/output.vtl");

      WordPuzzle userPuzzle = new WordPuzzle();

      String userInputString = request.queryParams("phrase");

      String puzzle = userPuzzle.makePuzzle(userInputString);

      model.put("puzzle", puzzle);
      model.put("answer", userInputString);
      model.put("answerResult", "");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/hint", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/output.vtl");

      WordPuzzle userPuzzle = new WordPuzzle();

      String answer = request.queryParams("answer");
      String puzzle = request.queryParams("puzzle");

      puzzle = userPuzzle.giveHint(answer, puzzle);

      model.put("puzzle", puzzle);
      model.put("answer", answer);
      model.put("answerResult", "");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/userAttempt", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/output.vtl");

      WordPuzzle userPuzzle = new WordPuzzle();

      String puzzle = request.queryParams("puzzle");
      String answer = request.queryParams("answer");
      String userAnswer = request.queryParams("userAnswer");

      String answerResult = userPuzzle.answerAttempt(answer, userAnswer);

      model.put("puzzle", puzzle);
      model.put("answer", answer);
      model.put("answerResult", answerResult);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
