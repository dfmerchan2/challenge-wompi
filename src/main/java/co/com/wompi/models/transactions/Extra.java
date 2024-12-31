package co.com.wompi.models.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Extra {
  @JsonProperty("is_three_ds")
  private Boolean isThreeDs;

  @JsonProperty("three_ds_auth_type")
  private String threeDsAuthType;
}
