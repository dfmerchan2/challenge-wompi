package co.com.wompi.models.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMethod {

  private String type;
  private Extra extra;

  @JsonProperty("user_type")
  private Integer userType;

  @JsonProperty("user_legal_id")
  private String userLegalId;

  @JsonProperty("user_legal_id_type")
  private String userLegalIdType;

  @JsonProperty("payment_description")
  private String paymentDescription;

  @JsonProperty("financial_institution_code")
  private String financialInstitutionCode;
}
