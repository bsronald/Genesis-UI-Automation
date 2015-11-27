package common;

import framework.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.PageTransporter;
import ui.pages.LoginPage;
import ui.pages.MainPages;
import ui.pages.TopMenu;
import utils.ConfigFileReader;

/**
 * User: RonaldButron
 * Date: 11/20/15
 */
public class CommonMethods {


    private static WebDriver driver = DriverManager.getInstance().getWebDriver();

    public static void logOut(){

        if (!isItInTheLoginPage()){
            MainPages mainPages = new MainPages();
            TopMenu topMenu = mainPages.getTopMenu();
            topMenu.logOut();
        }

    }

    public static void navigateLogIn(){

       PageTransporter.getInstance().toLoginPage();
    }

    public static Boolean isItInTheLoginPage(){

        ConfigFileReader reader = new ConfigFileReader();
        String URLLogin = reader.getPropertiesValues("URLLogin");
        return driver.getCurrentUrl().equalsIgnoreCase(URLLogin);
    }

    public static void elementHighlight(WebElement element) {

        WebDriver driver = DriverManager.getInstance().getWebDriver();
        for (int i = 0; i < 3; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "color: red; border: 4px solid red;");
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "");
        }
    }


}
