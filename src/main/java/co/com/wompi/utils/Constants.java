package co.com.wompi.utils;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Locale;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  public static final Faker DATA_RANDOM =
      new Faker(new Locale.Builder().setLanguage("es").setRegion("MX").build());

  public static final String SAVE_TRANSACTIONS_REQUEST = "transactions request";
  public static final String FORMAT_DATE = "yyyy-MM-dd";
  public static final String KEY_AUTHORIZATION = "authorization";
}
