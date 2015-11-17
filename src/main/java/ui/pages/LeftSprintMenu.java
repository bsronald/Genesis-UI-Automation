package ui.pages;

import org.openqa.selenium.support.PageFactory;
import ui.BasePageObject;

/**
 * User: RonaldButron
 * Date: 11/17/15
 */
public class LeftSprintMenu extends BasePageObject {

    @Override
    public void waitUntilPageObjectIsLoaded() {

    }

    public LeftSprintMenu(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    public void addNewSPrint(){

    }


    public void setNameSprint(String nameSprint){

    }

    public void setCapacityInHours(String hours){

    }

    public void setStartDate(String startDate){

    }

    public void setFinishDate(String finishDate){

    }

    public void saveNewSprint(){

    }

    public void sprintIsDisplayed(){

    }

    public void selectSprint(){

    }
}
