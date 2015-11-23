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

    public static boolean waitElementIsRemoved(By element){
        Boolean elementFind;

        do{
           elementFind = isElementPresent(element);
        }while (elementFind);


      return elementFind;
    }

    public static boolean waitElementIsNotPresent(int MaxCount, By element){
        boolean result = true;
        int count = 1;
        try {
            while (result && count <= MaxCount){
                Thread.sleep(50);
                System.out.print("++++++++++Element not Present++++++++++"+result);
                result = isElementPresent(element);
                count++;

            }

        } catch (InterruptedException e){

           log.error("Exception Found Element is not Present");
        }
        System.out.print("++++++++++Element not Present Out of While++++++++++"+result);
        return result;
    }

    public static boolean waitElementIsPresent(int MaxCount, By element){

        boolean result = false;
        int count = 1;
        while (!result && count <= MaxCount){
            System.out.print("++++++++++Present Element++++++++++"+result);
            result = isElementPresent(element);
            count++;
        }

        System.out.print("++++++++++Element Present Out of While++++++++++"+result);

        return result;
    }
}
