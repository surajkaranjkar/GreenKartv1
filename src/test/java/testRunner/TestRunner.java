package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		features = ".//features", 
		plugin={"pretty","html:test-output/report.html"}, 
		monochrome=true,
		glue="stepDefinations",
		dryRun=true,
		tags="@Smoke"
		)

public class TestRunner{
	
	
}
