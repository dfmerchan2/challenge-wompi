package co.com.wompi.models.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Messages {
  @JsonProperty("public_key")
  private ArrayList<String> publicKey;

  @JsonProperty("acceptance_token")
  private ArrayList<String> acceptanceToken;

  @JsonProperty("valid_amount_in_cents")
  private ArrayList<String> amount;

  private ArrayList<String> currency;

  @JsonProperty("customer_email")
  private ArrayList<String> customerEmail;

  @JsonProperty("payment_method")
  private PaymentMethod paymentMethod;

  @JsonProperty("user_type")
  private ArrayList<String> userType;

  @JsonProperty("user_legal_id_type")
  private ArrayList<String> userLegalIdType;

  private ArrayList<String> signature;
}
