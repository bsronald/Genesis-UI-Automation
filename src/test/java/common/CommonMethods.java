package common;

import ui.PageTransporter;
import ui.pages.LoginPage;
import ui.pages.MainPage;

/**
 * User: RonaldButron
 * Date: 11/20/15
 */
public class CommonMethods {

    public static LoginPage login;

    public static void logOut(){
        MainPage mainPage = new MainPage();
        mainPage.logOut();
    }

    public static void navigateLogIn(){

       login = PageTransporter.getInstance().toLoginPage();
    }
}
