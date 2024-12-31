package co.com.wompi.models.merchants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchantsResponse {
  private Data data;

  @JsonProperty("meta")
  private Meta meta;
}
