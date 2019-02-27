package com.sapient.learning.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "json:target/cucumber-reports/integ-test-report.json" }, 
		features = "classpath:features", 
		monochrome = true)
public class RunCucumberTest {
	
}