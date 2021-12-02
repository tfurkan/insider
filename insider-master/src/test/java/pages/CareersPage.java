package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.Log4jManager;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class CareersPage extends BasePage{
    public CareersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='btn btn-info rounded mr-0 mr-md-4 py-3']")
    public WebElement btnFindYourDreamJob;
    @FindBy(xpath = "//span[@class='select2-selection__rendered'and @id='select2-filter-by-location-container']")
    public WebElement locationList;
    @FindBy(xpath = "//ul[@class='select2-results__options']//li")
    List<WebElement> allLocations;
    @FindBy(xpath = "//span[@class='select2-selection__rendered'and @id='select2-filter-by-department-container']")
    public WebElement departmentList;
    @FindBy(xpath = "//ul[@class='select2-results__options']//li")
    List<WebElement> allDepartments;
    @FindBy(xpath = "//*[@class='position-list col-12 d-flex flex-wrap mt-5 pl-2 pr-2 pl-lg-0 pr-lg-0 pt-4']//div[@data-animated='true']")
    List<WebElement> openPositions;
    By openPositionsLocation = By.xpath("//*[@class='position-location text-large']");
    By getOpenPositionsDepartment = By.xpath("//*[@class='position-department text-large font-weight-600 text-primary']");
    By btnApplyNow = By.xpath("//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5']");
    By jobTitle = By.xpath("//*[@class='position-title font-weight-bold']");


    public void clickFindYourDreamJob(){
        try {
            click(btnFindYourDreamJob);
            softAssertText(driver.getCurrentUrl(), "https://useinsider.com/careers/open-positions/");
            Log4jManager.info("Clicked find your job button and checked URL");
        } catch (Exception e) {
            Log4jManager.error("Failed to clicked find your job button",e);
            throw(e);
        }
    }
    public void filterJobOptions(String location, String department){
        try {
            click(locationList);
            for (int i = 0; i < allLocations.size(); i++){
                if(allLocations.get(i).getText().equals(location)){
                    click(allLocations.get(i));
                    break;
                }
            }
            Log4jManager.info("Location List filtered");
        } catch (Exception e) {
            Log4jManager.error("Fail to Location List filter",e);
            throw(e);
        }
        try {
            click(departmentList);
            for (int i = 0; i < allDepartments.size(); i++){
                if(allLocations.get(i).getText().equals(department)){
                    click(allLocations.get(i));
                    break;
                }
            }
            Log4jManager.info("Department List filtered");
        } catch (Exception e) {
            Log4jManager.error("Fail to Department list filter",e);
            throw(e);
        }
    }
    public void checkJobResults(String location, String department) throws InterruptedException {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,750)","");
            Thread.sleep(2000);
            for(int i = 0; i < openPositions.size(); i++){
                assertText(openPositions.get(i).findElements(openPositionsLocation).get(i).getText(), location);
                assertText(openPositions.get(i).findElements(getOpenPositionsDepartment).get(i).getText(), department);
            }
            Log4jManager.info("Job List True");
        } catch (InterruptedException e) {
            Log4jManager.error("Job List False",e);
            throw(e);
        }
    }
    public String clickRandomJob(){
        String title = null;
        try {
            Random r = new Random();
            int randomValue = r.nextInt(openPositions.size());
            Actions actions = new Actions(driver);
            actions.moveToElement(openPositions.get(randomValue));
            actions.perform();
            title = readText(openPositions.get(randomValue).findElements(jobTitle).get(randomValue));
            click(openPositions.get(randomValue).findElements(btnApplyNow).get(randomValue));
            Log4jManager.info("Clicked Apply Now");
        } catch (Exception e) {
            Log4jManager.error("Failed to click Apply Now",e);
            throw(e);
        }
        Set<String> handles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();
        for (String handle : handles) {

            if (!handle .equals(currentHandle))
            {
                driver.switchTo().window(handle);
            }
        }
        String[] url = driver.getCurrentUrl().split("(?<=.co/)");
        softAssertText(url[0],"https://jobs.lever.co/");
        return title;
    }


}
