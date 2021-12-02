package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Log4jManager;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    String baseURL = "https://useinsider.com/";

    @FindBy(xpath = "//a[@class='nav-link dropdown-toggle']//span[contains(text(),'More')]")
    public WebElement btnNavMore;
    @FindBy(xpath = "//h5[contains(text(),'Careers')]")
    public WebElement btnSubCareers;

    public void goToInsider(){
        try {
            driver.get(baseURL);
            softAssertText(driver.getCurrentUrl(), baseURL);
            Log4jManager.info("Webpage Opened");
        } catch (Exception e) {
            Log4jManager.error("Failed to open Webpage",null);
            throw(e);
        }
    }
    public void clickCareers(){
        try {
            click(btnNavMore);
            click(btnSubCareers);
            softAssertText(driver.getCurrentUrl(),"https://useinsider.com/careers/");
            Log4jManager.info("Opened Careers Page");
        } catch (Exception e) {
            Log4jManager.error("Failed to open career page",e);
            throw(e);
        }
    }
}
