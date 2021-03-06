package framework;

import framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;


/**
 * User: RonaldButron
 * Date: 11/16/15
 */
public class UIMethods {

    public static WebDriver driver =  DriverManager.getInstance().getWebDriver();
    private static Logger log = Logger.getLogger("UIMethods");
    public UIMethods(){

    }
    public static boolean isElementPresent(By element){
          try{
              driver.findElement(element);
              return true;
          } catch(NoSuchElementException e){
              return  false;
          }
     }

    public static boolean waitElementIsNotPresent(int MaxCount, By element){
        boolean result = true;
        int count = 1;
        try {
            while (result && count <= MaxCount){
                Thread.sleep(50);
                result = isElementPresent(element);
                count++;

            }

        } catch (InterruptedException e){

           log.error("Exception Found Element is not Present");
        }
        return result;
    }

    public static boolean waitElementIsPresent(int MaxCount, By element){

        boolean result = false;
        int count = 1;
        while (!result && count <= MaxCount){
            result = isElementPresent(element);
            count++;
        }

        return result;
    }
}
