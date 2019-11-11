package Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;


	@RunWith(Cucumber.class)
	@CucumberOptions(features="FeatureFiles",glue="stepDefinitions",plugin={"com.cucumber.listener.ExtentCucumberFormatter:Reports/report.html","html:target/cucumber-reports"})
	public class Runner extends AbstractTestNGCucumberTests
	{
		
    }
