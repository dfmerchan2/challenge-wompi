package co.com.wompi.models.merchants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMethod {
  private String name;

  @JsonProperty("payment_processors")
  private List<PaymentProcessor> paymentProcessors;
}
