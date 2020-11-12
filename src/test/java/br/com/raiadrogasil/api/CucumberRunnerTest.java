package br.com.raiadrogasil.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "json:target/cucumber.json" }, features = "classpath:features")
public class CucumberRunnerTest {
}
