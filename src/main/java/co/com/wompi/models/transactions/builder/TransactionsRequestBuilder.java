package co.com.wompi.models.transactions.builder;

import co.com.wompi.models.transactions.PaymentMethod;
import co.com.wompi.models.transactions.TransactionsData;
import co.com.wompi.models.transactions.TransactionsRequest;

import static co.com.wompi.utils.Constants.DATA_RANDOM;
import static co.com.wompi.utils.DataManagement.*;

public class TransactionsRequestBuilder {

  private static final String DESCRIPTION = "Pago a Tienda Wompi";

  public static TransactionsRequest transactionsRequest(TransactionsData data) {

    String reference = DATA_RANDOM.number().digits(10);
    String signature =
        getSignature(
            reference + data.getAmount() + data.getCurrency() + getSecretKey(),
            data.getSignature());

    return TransactionsRequest.builder()
        .acceptanceToken(getAccessToke(data.getAccessToken()))
        .acceptPersonalAuth(getAcceptPersonalAuth())
        .amountInCents(Integer.valueOf(data.getAmount()))
        .currency(data.getCurrency())
        .signature(signature)
        .customerEmail(data.getEmail())
        .reference(reference)
        .paymentMethod(paymentMethod(data))
        .build();
  }

  public static PaymentMethod paymentMethod(TransactionsData data) {
    return PaymentMethod.builder()
        .type(data.getPaymentMethod())
        .userType(Integer.valueOf(data.getUserType()))
        .userLegalIdType(data.getIdType())
        .userLegalId(data.getIdNumber())
        .financialInstitutionCode(data.getIdFinancial())
        .paymentDescription(DESCRIPTION)
        .build();
  }
}
