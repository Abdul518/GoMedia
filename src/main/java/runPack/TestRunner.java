package runPack;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",glue = "stepDef", tags = "@e2e",monochrome = true)
public class TestRunner
{
}
