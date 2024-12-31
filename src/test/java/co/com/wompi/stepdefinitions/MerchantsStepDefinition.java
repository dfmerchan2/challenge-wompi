package co.com.wompi.stepdefinitions;

import co.com.wompi.models.errors.ErrorResponse;
import co.com.wompi.models.merchants.MerchantsResponse;
import co.com.wompi.questions.TheResponseCode;
import co.com.wompi.questions.TheResponseError;
import co.com.wompi.questions.TheResponseOfMerchants;
import co.com.wompi.tasks.GetMerchants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.wompi.utils.DataManagement.getDataAuthKey;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class MerchantsStepDefinition {
  @When("that {string} requests a list of merchants using the {string} authentication key")
  public void thatConsultsTheMerchants(String actor, String authKey) {
    theActorCalled(actor).attemptsTo(GetMerchants.withTheKey(getDataAuthKey(authKey)));
  }

  @Then("the status code should be {int}")
  public void heGetsAResponseCode(Integer statusCode) {
    theActorInTheSpotlight()
        .should(seeThat("the response code", TheResponseCode.is(), equalTo(statusCode)));
  }

  @And("the response should include type of error {string} and reason {string}")
  public void theResponseShouldIncludeTypeOfErrorAndReason(String type, String reason) {
    ErrorResponse response = TheResponseError.is().answeredBy(theActorInTheSpotlight());

    theActorInTheSpotlight()
        .should(
            seeThat("the type error", act -> response.getError().getType(), equalTo(type)),
            seeThat("the type reason", act -> response.getError().getReason(), equalTo(reason)));
  }

  @And("the response should include type of error {string} and message {string}")
  public void theResponseShouldIncludeTypeOfErrorAndMessage(String type, String message) {
    ErrorResponse response = TheResponseError.is().answeredBy(theActorInTheSpotlight());

    theActorInTheSpotlight()
        .should(
            seeThat("the type error", act -> response.getError().getType(), equalTo(type)),
            seeThat(
                "the type reason",
                act -> response.getError().getMessages().getPublicKey().getFirst(),
                equalTo(message)));
  }

  @And("verify that the correct information is returned")
  public void verifyThatTheCorrectInformationIsReturned() {
    MerchantsResponse response = TheResponseOfMerchants.is().answeredBy(theActorInTheSpotlight());

    theActorInTheSpotlight()
        .should(
            seeThat("the id", act -> response.getData().getId(), notNullValue()),
            seeThat("the name", act -> response.getData().getName(), notNullValue()),
            seeThat("the email", act -> response.getData().getEmail(), notNullValue()),
            seeThat("the contact name", act -> response.getData().getContactName(), notNullValue()),
            seeThat("the phone number", act -> response.getData().getPhoneNumber(), notNullValue()),
            seeThat("the active", act -> response.getData().getActive(), notNullValue()),
            seeThat("the legal name", act -> response.getData().getLegalName(), notNullValue()),
            seeThat(
                "the legal id type", act -> response.getData().getLegalIdType(), notNullValue()),
            seeThat("the legal id", act -> response.getData().getLegalId(), notNullValue()),
            seeThat("the public key", act -> response.getData().getPublicKey(), notNullValue()),
            seeThat(
                "the accepted currencies",
                act -> response.getData().getAcceptedCurrencies(),
                notNullValue()),
            seeThat(
                "the accepted payment methods",
                act -> response.getData().getAcceptedPaymentMethods(),
                notNullValue()),
            seeThat(
                "the payment methods",
                act -> response.getData().getPaymentMethods(),
                notNullValue()),
            seeThat(
                "the acceptance token",
                act -> response.getData().getPresignedAcceptance().getAcceptanceToken(),
                notNullValue()),
            seeThat(
                "the personal data auth token",
                act -> response.getData().getPresignedPersonalDataAuth().getAcceptanceToken(),
                notNullValue()));
  }
}
