package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Log4jManager;

import java.util.List;

public class JobDetailsPage extends BasePage{
    public JobDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='posting-headline']//h2")
    public WebElement jobTitle;
    @FindBy(xpath = "//a[@class='postings-btn template-btn-submit shamrock']")
    List<WebElement> btnApply;
    @FindBy(xpath = "//*[@class='posting-requirements plain-list']")
    public WebElement jobReqs;


    public void checkSelectedTrueJob(String str) {
        try {
            assertText(jobTitle.getText(), str);
            assertTrue(btnApply.get(0));
            assertTrue(jobReqs);
        } catch (Exception e) {
            Log4jManager.error("Failed checkSelectedTrueJob",e);
            throw(e);
        }
    }
    public void checkLever(){
        click(btnApply.get(0));
        String[] url = driver.getCurrentUrl().split("(?<=.co/)");
        assertText(url[0],"https://jobs.lever.co/");
    }
}
