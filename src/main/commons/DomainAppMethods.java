package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


/**
 * User: RonaldButron
 * Date: 11/16/15
 */
public class DomainAppMethods {

    public DomainAppMethods(){

    }
     public static Boolean isElementPresent(By element, WebDriver driver){
          try{
              driver.findElement(element);
              return true;
          } catch(NoSuchElementException e){
              return  false;
          }
     }
}
