package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/resources/Flipkart.feature",
tags = "@functional",
glue={"steps"},
plugin = {"json:target/cucumber.json"})
public class testRunner {

}