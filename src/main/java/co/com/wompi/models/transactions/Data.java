package co.com.wompi.models.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
  private String id;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("finalized_at")
  private Object finalizedAt;

  @JsonProperty("amount_in_cents")
  private Integer amountInCents;

  private String reference;

  @JsonProperty("customer_email")
  private String customerEmail;

  private String currency;

  @JsonProperty("payment_method_type")
  private String paymentMethodType;

  @JsonProperty("payment_method")
  private PaymentMethod paymentMethod;

  private String status;

  @JsonProperty("status_message")
  private String statusMessage;

  @JsonProperty("billing_data")
  private String billingData;

  @JsonProperty("shipping_address")
  private String shippingAddress;

  @JsonProperty("redirect_url")
  private String redirectUrl;

  @JsonProperty("payment_source_id")
  private String paymentSourceId;

  @JsonProperty("payment_link_id")
  private String paymentLinkId;

  @JsonProperty("customer_data")
  private String customerData;

  @JsonProperty("bill_id")
  private String billId;

  private List<String> taxes;

  @JsonProperty("tip_in_cents")
  private String tipInCents;
}
