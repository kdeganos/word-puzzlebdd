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

      model.put("result", puzzle);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
