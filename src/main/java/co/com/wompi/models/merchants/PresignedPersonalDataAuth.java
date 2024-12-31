package co.com.wompi.models.merchants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PresignedPersonalDataAuth {
  @JsonProperty("acceptance_token")
  private String acceptanceToken;

  private String permalink;
  private String type;
}
