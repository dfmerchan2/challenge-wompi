package co.com.wompi.models.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionsRequest {
  @JsonProperty("acceptance_token")
  private String acceptanceToken;

  @JsonProperty("accept_personal_auth")
  private String acceptPersonalAuth;

  @JsonProperty("amount_in_cents")
  private Integer amountInCents;

  private String currency;
  private String signature;

  @JsonProperty("customer_email")
  private String customerEmail;

  private final String reference;

  @JsonProperty("payment_method")
  private PaymentMethod paymentMethod;
}
