package co.com.wompi.tasks;

import co.com.wompi.models.transactions.TransactionsRequest;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static co.com.wompi.utils.enums.EndPoints.TRANSACTIONS;
import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class PostTransactions implements Task {
  private final String authKey;
  private final TransactionsRequest transactionsRequest;

  @Step("{0} send the payment request")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Post.to(TRANSACTIONS.getValue())
            .with(
                request ->
                    request
                        .contentType(ContentType.JSON)
                        .header("authorization", String.format("Bearer %s", authKey))
                        .body(transactionsRequest)
                        .log()
                        .all()));
    SerenityRest.lastResponse().print();
  }

  public static Performable withTheInformation(String authKey, TransactionsRequest request) {
    return instrumented(PostTransactions.class, authKey, request);
  }
}
