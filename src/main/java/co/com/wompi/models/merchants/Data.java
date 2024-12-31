package co.com.wompi.models.merchants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
  private Integer id;
  private String name;
  private String email;

  @JsonProperty("contact_name")
  private String contactName;

  @JsonProperty("phone_number")
  private String phoneNumber;

  private Boolean active;

  @JsonProperty("logo_url")
  private Object logoUrl;

  @JsonProperty("legal_name")
  private String legalName;

  @JsonProperty("legal_id_type")
  private String legalIdType;

  @JsonProperty("legal_id")
  private String legalId;

  @JsonProperty("public_key")
  private String publicKey;

  @JsonProperty("accepted_currencies")
  private List<String> acceptedCurrencies;

  @JsonProperty("fraud_javascript_key")
  private Object fraudJavascriptKey;

  @JsonProperty("fraud_groups")
  private List<String> fraudGroups;

  @JsonProperty("accepted_payment_methods")
  private List<String> acceptedPaymentMethods;

  @JsonProperty("payment_methods")
  private List<PaymentMethod> paymentMethods;

  @JsonProperty("presigned_acceptance")
  private PresignedAcceptance presignedAcceptance;

  @JsonProperty("presigned_personal_data_auth")
  private PresignedPersonalDataAuth presignedPersonalDataAuth;
}
