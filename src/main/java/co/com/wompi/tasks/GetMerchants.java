package co.com.wompi.tasks;

import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static co.com.wompi.utils.enums.EndPoints.MERCHANTS;
import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class GetMerchants implements Task {

  private final String key;

  @Step("{0} get authorized merchants with the key: #key")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Get.resource(MERCHANTS.getValue() + key)
            .with(request -> request.contentType(ContentType.JSON).log().all()));
    SerenityRest.lastResponse().print();
  }

  public static Performable withTheKey(String key) {
    return instrumented(GetMerchants.class, key);
  }
}
