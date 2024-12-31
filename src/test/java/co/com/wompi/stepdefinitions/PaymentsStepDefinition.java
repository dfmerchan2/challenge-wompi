package co.com.wompi.stepdefinitions;

import co.com.wompi.models.transactions.TransactionsData;
import co.com.wompi.models.transactions.TransactionsRequest;
import co.com.wompi.models.transactions.TransactionsResponse;
import co.com.wompi.questions.TheResponseCode;
import co.com.wompi.questions.TheResponseOfTransactions;
import co.com.wompi.tasks.GetMerchants;
import co.com.wompi.tasks.PostTransactions;
import co.com.wompi.utils.enums.FieldError;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.com.wompi.models.transactions.builder.TransactionsRequestBuilder.transactionsRequest;
import static co.com.wompi.utils.Constants.KEY_AUTHORIZATION;
import static co.com.wompi.utils.Constants.SAVE_TRANSACTIONS_REQUEST;
import static co.com.wompi.utils.CsvUtilities.getDataTransaction;
import static co.com.wompi.utils.DataManagement.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;

public class PaymentsStepDefinition {

  @Given("that {string} has valid access tokens with status code {int}")
  public void thatHasValidAccessTokens(String actor, Integer statusCode) {
    theActorCalled(actor).attemptsTo(GetMerchants.withTheKey(getAuthKey()));
    theActorInTheSpotlight()
        .should(seeThat("the response code", TheResponseCode.is(), equalTo(statusCode)));
  }

  @When("sending a payment request using {string} information")
  public void sendingAPaymentRequestUsingInformation(String filter) {
    TransactionsData data = getDataTransaction(filter);
    TransactionsRequest transactionsRequest = transactionsRequest(data);

    theActorInTheSpotlight()
        .attemptsTo(
            PostTransactions.withTheInformation(
                getDataAuthKey(data.getAuthorization()), transactionsRequest));

    theActorInTheSpotlight().remember(SAVE_TRANSACTIONS_REQUEST, transactionsRequest);
  }

  @When("send a payment request using the following information")
  public void sendPaymentRequestUsingTheFollowingInformation(List<Map<String, String>> data) {

    TransactionsRequest transactionsRequest = transactionsRequest(getTransactionsData(data));

    theActorInTheSpotlight()
        .attemptsTo(
            PostTransactions.withTheInformation(
                getDataAuthKey(data.getFirst().get(KEY_AUTHORIZATION)), transactionsRequest));

    theActorInTheSpotlight().remember(SAVE_TRANSACTIONS_REQUEST, transactionsRequest);
  }

  @Then("should see the status code {int}")
  public void shouldSeeTheStatusCode(Integer statusCode) {
    theActorInTheSpotlight()
        .should(seeThat("the response code", TheResponseCode.is(), equalTo(statusCode)));
  }

  @Then("should see an error message {string} indicating that the value of {string} is not valid")
  public void shouldSeeAnErrorMessageIndicatingThatTheValueOfIsNotValid(
      String message, String field) {

    theActorInTheSpotlight()
        .should(
            seeThat(
                "the reason", atc -> getMessageError(FieldError.valueOf(field)), equalTo(message)));
  }

  @And("the transaction status is {string}")
  public void theTransactionStatusIs(String status) {
    TransactionsResponse response =
        TheResponseOfTransactions.is().answeredBy(theActorInTheSpotlight());

    TransactionsRequest request = theActorInTheSpotlight().recall(SAVE_TRANSACTIONS_REQUEST);

    theActorInTheSpotlight()
        .should(
            seeThat("the id", act -> response.getData().getId(), notNullValue()),
            seeThat(
                "the amount",
                act -> response.getData().getAmountInCents(),
                equalTo(request.getAmountInCents())),
            seeThat(
                "the reference",
                act -> response.getData().getReference(),
                equalTo(request.getReference())),
            seeThat(
                "the customer email",
                act -> response.getData().getCustomerEmail(),
                equalTo(request.getCustomerEmail())),
            seeThat(
                "the currency",
                act -> response.getData().getCurrency(),
                equalTo(request.getCurrency())),
            seeThat(
                "the payment method type",
                act -> response.getData().getPaymentMethodType(),
                equalTo(request.getPaymentMethod().getType())),
            seeThat(
                "the payment type",
                act -> response.getData().getPaymentMethod().getType(),
                equalTo(request.getPaymentMethod().getType())),
            seeThat(
                "the extra",
                act -> response.getData().getPaymentMethod().getExtra(),
                notNullValue()),
            seeThat(
                "the user type",
                act -> response.getData().getPaymentMethod().getUserType(),
                equalTo(request.getPaymentMethod().getUserType())),
            seeThat(
                "the user legal id",
                act -> response.getData().getPaymentMethod().getUserLegalId(),
                equalTo(request.getPaymentMethod().getUserLegalId())),
            seeThat(
                "the user legalId type",
                act -> response.getData().getPaymentMethod().getUserLegalIdType(),
                equalTo(request.getPaymentMethod().getUserLegalIdType())),
            seeThat(
                "the payment description",
                act -> response.getData().getPaymentMethod().getPaymentDescription(),
                equalTo(request.getPaymentMethod().getPaymentDescription())),
            seeThat(
                "the financial institution code",
                act -> response.getData().getPaymentMethod().getFinancialInstitutionCode(),
                equalTo(request.getPaymentMethod().getFinancialInstitutionCode())),
            seeThat("the status", act -> response.getData().getStatus(), equalTo(status)));
  }
}
