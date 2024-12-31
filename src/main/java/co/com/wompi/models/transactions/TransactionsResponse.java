package co.com.wompi.models.transactions;

import co.com.wompi.models.merchants.Meta;
import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionsResponse {
  private Data data;
  private Meta meta;
}
