package co.com.wompi.questions;

import co.com.wompi.models.merchants.MerchantsResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheResponseOfMerchants implements Question<MerchantsResponse> {

  @Override
  public MerchantsResponse answeredBy(Actor actor) {
    return SerenityRest.lastResponse().as(MerchantsResponse.class);
  }

  public static TheResponseOfMerchants is() {
    return new TheResponseOfMerchants();
  }
}
