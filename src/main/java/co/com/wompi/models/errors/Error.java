package co.com.wompi.models.errors;

import lombok.Data;

@Data
public class Error {
  private String type;
  private String reason;
  private Messages messages;
}
