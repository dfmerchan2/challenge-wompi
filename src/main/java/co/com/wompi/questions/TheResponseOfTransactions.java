package co.com.wompi.questions;

import co.com.wompi.models.transactions.TransactionsResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheResponseOfTransactions implements Question<TransactionsResponse> {

  @Override
  public TransactionsResponse answeredBy(Actor actor) {
    return SerenityRest.lastResponse().as(TransactionsResponse.class);
  }

  public static TheResponseOfTransactions is() {
    return new TheResponseOfTransactions();
  }
}
