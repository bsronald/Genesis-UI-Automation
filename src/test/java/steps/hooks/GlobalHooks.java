package steps.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import framework.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * User: RonaldButron
 * Date: 11/25/15
 */
public class GlobalHooks {
    WebDriver driver = DriverManager.getInstance().getWebDriver();

    @After
    public void tearDown(Scenario scenario){

        if (scenario.isFailed()){

            final byte[] screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot, "failed.png");
        }

    }
}
