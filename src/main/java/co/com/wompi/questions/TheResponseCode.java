package co.com.wompi.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheResponseCode implements Question<Integer> {

  @Override
  public Integer answeredBy(Actor actor) {
    return SerenityRest.lastResponse().getStatusCode();
  }

  public static Question<Integer> is() {
    return new TheResponseCode();
  }
}
