package co.com.wompi.questions;

import co.com.wompi.models.errors.ErrorResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheResponseError implements Question<ErrorResponse> {

  @Override
  public ErrorResponse answeredBy(Actor actor) {
    return SerenityRest.lastResponse().as(ErrorResponse.class);
  }

  public static TheResponseError is() {
    return new TheResponseError();
  }
}
