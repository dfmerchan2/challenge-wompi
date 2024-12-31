package co.com.wompi.models.transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsData {
  private String id;
  private String filterId;
  private String amount;
  private String currency;
  private String paymentMethod;
  private String email;
  private String userType;
  private String idType;
  private String idNumber;
  private String idFinancial;
  private String signature;
  private String accessToken;
  private String authorization;
}
