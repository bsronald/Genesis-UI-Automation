package ui;

import framework.DriverManager;
import org.openqa.selenium.WebDriver;
import ui.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import utils.ConfigFileReader;

/**
  * User: RonaldButron
 * Date: 11/11/15
 */
public class PageTransporter {

    private WebDriver driver = DriverManager.getInstance().getWebDriver();
    private ConfigFileReader reader = new ConfigFileReader();
    private  String URLLogin = reader.getPropertiesValues("URLLogin");
    private static PageTransporter instance;

    protected PageTransporter(){
            init();
    }

    public static PageTransporter getInstance(){
                if(instance == null){
                    instance = new PageTransporter();
                }
        return instance;
    }

    private void init(){

    }

    private void goToURL(String URL){
        driver.navigate().to(URL);
    }
    public String getCurrentURL(){

        return driver.getCurrentUrl();

    }

    public LoginPage toLoginPage(){

        goToURL(URLLogin);
        return new LoginPage();



    }

}
