package common;

import framework.DriverManager;
import org.openqa.selenium.WebDriver;
import ui.PageTransporter;
import ui.pages.LoginPage;
import ui.pages.MainPages;
import ui.pages.TopMenu;

/**
 * User: RonaldButron
 * Date: 11/20/15
 */
public class CommonMethods {

    private static String LoginUrl = "https://genesis-planner.com/login";
    public static LoginPage login;
    private static WebDriver driver = DriverManager.getInstance().getWebDriver();


    public static void logOut(){
        MainPages mainPages = new MainPages();
        TopMenu topMenu = mainPages.getTopMenu();
        topMenu.logOut();
    }

    public static void navigateLogIn(){

       PageTransporter.getInstance().toLoginPage();
    }

    public static Boolean isItInTheLoginPage(){

        if (driver.getCurrentUrl().equalsIgnoreCase(LoginUrl)) {

            return true;
        } else{

            return false;
        }
    }
}
