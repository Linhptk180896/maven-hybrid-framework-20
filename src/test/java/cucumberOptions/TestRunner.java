package cucumberOptions;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        //path of feature file
        features = "src/test/java/features",
        //path of step definition file
        glue = "stepDefinitions",
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        plugin = {"pretty" ,"html:target/site/cucumber-report-default" ,
                "json:target/site/cucumber.json"
        },
        tags = {"@datatable"}
)

public class TestRunner {

}
