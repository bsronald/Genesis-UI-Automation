package framework;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by RonaldButron on 11/10/2015.
 */
public class DriverManager {

    private static DriverManager instance = null;
    private WebDriver driver;
    private WebDriverWait wait;

    protected DriverManager(){
        init();
    }

    public static DriverManager getInstance(){
        if (instance == null){
            instance = new DriverManager();
        }

        return instance;
    }

    private void init(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30, 100);

    }

    public WebDriver getWebDriver(){
        return driver;
    }

    public WebDriverWait getWait(){
        return wait;
    }
}
