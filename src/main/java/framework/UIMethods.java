package framework;

import framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


/**
 * User: RonaldButron
 * Date: 11/16/15
 */
public class UIMethods {

    public static WebDriver driver =  DriverManager.getInstance().getWebDriver();
    public UIMethods(){

    }
    public static Boolean isElementPresent(By element){
          try{
              driver.findElement(element);
              return true;
          } catch(NoSuchElementException e){
              return  false;
          }
     }

    public static Boolean waitElementIsRemoved(By element){
        Boolean elementFind;

        do{
           elementFind = isElementPresent(element);
        }while (elementFind == Boolean.TRUE);


      return elementFind;
    }
}
