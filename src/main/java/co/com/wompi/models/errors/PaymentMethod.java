package co.com.wompi.models.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PaymentMethod {
  private String type;
  private Messages messages;

  @JsonProperty("user_legal_id")
  private ArrayList<String> userLegalId;
}
