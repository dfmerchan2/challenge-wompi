package co.com.wompi.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EndPoints {
  URL_BASE("https://api-sandbox.co.uat.wompi.dev"),
  TRANSACTIONS("/v1/transactions"),
  MERCHANTS("/v1/merchants/");

  private final String value;
}
