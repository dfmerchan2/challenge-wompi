package co.com.wompi.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static co.com.wompi.utils.enums.EndPoints.URL_BASE;

public class Hook {
  @Before
  public void setStage() {
    OnStage.setTheStage(OnlineCast.whereEveryoneCan(CallAnApi.at(URL_BASE.getValue())));
  }
}
