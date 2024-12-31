package co.com.wompi.utils;

import co.com.wompi.models.errors.ErrorResponse;
import co.com.wompi.models.transactions.TransactionsData;
import co.com.wompi.questions.TheResponseError;
import co.com.wompi.questions.TheResponseOfMerchants;
import co.com.wompi.utils.enums.FieldError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.DatatypeConverter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static co.com.wompi.utils.Constants.DATA_RANDOM;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class DataManagement {

  public static String getAuthKey() {
    return System.getenv("AUTH_KEY");
  }

  public static String getSecretKey() {
    return System.getenv("SECRET_KEY");
  }

  public static String getDataAuthKey(String option) {
    return switch (option) {
      case "valid" -> getAuthKey();
      case "invalid" -> getAuthKey() + DATA_RANDOM.number().digits(2);
      case "invalid format" -> DATA_RANDOM.number().digits(20);
      case "empty" -> "";
      default -> throw new IllegalStateException(String.format("Unexpected value: %s", option));
    };
  }

  public static String getSignature(String value, String option) {
    return switch (option) {
      case "valid" -> getSHA256Hash(value);
      case "invalid" -> DATA_RANDOM.number().digits(30);
      case "empty" -> "";
      default -> throw new IllegalStateException(String.format("Unexpected value: %s", option));
    };
  }

  public static String getAccessToke(String option) {
    String accessToken =
        TheResponseOfMerchants.is()
            .answeredBy(theActorInTheSpotlight())
            .getData()
            .getPresignedAcceptance()
            .getAcceptanceToken();
    return switch (option) {
      case "invalid" -> accessToken + DATA_RANDOM.number().digits(2);
      case "empty" -> "";
      case "valid" -> accessToken;
      default -> throw new IllegalStateException(String.format("Unexpected value: %s", option));
    };
  }

  public static String getAcceptPersonalAuth() {
    return TheResponseOfMerchants.is()
        .answeredBy(theActorInTheSpotlight())
        .getData()
        .getPresignedPersonalDataAuth()
        .getAcceptanceToken();
  }

  public static String getSHA256Hash(String data) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] digest = md.digest(data.getBytes(StandardCharsets.UTF_8));
      return DatatypeConverter.printHexBinary(digest).toLowerCase();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("SHA-256 algorithm not found.", e);
    }
  }

  public static String getCurrentDate(String format) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    return dateTimeFormatter.format(LocalDateTime.now());
  }

  public static TransactionsData getTransactionsData(List<Map<String, String>> data) {
    return new ObjectMapper().convertValue(data.getFirst(), TransactionsData.class);
  }

  public static String getMessageError(FieldError option) {
    ErrorResponse response = TheResponseError.is().answeredBy(theActorInTheSpotlight());
    return switch (option) {
      case AUTHORIZATION -> response.getError().getReason();
      case ACCEPTANCE_TOKEN -> response.getError().getMessages().getAcceptanceToken().getFirst();
      case AMOUNT -> response.getError().getMessages().getAmount().getFirst();
      case CURRENCY -> response.getError().getMessages().getCurrency().getFirst();
      case CUSTOMER_EMAIL -> response.getError().getMessages().getCustomerEmail().getFirst();
      case USER_TYPE -> response.getError().getMessages().getPaymentMethod().getMessages().getUserType().getFirst();
      case TYPE_DOCUMENT -> response.getError().getMessages().getPaymentMethod().getMessages().getUserLegalIdType().getFirst();
      case NUMBER_DOCUMENT -> response.getError().getMessages().getPaymentMethod().getUserLegalId().getFirst();
      case SIGNATURE -> response.getError().getMessages().getSignature().getFirst();
    };
  }
}
