package co.com.wompi.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features/payments.feature",
    plugin = {"pretty", "summary"},
    glue = "co.com.wompi.stepdefinitions",
    snippets = CucumberOptions.SnippetType.CAMELCASE)
public class PaymentsRunner {}
