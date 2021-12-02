package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BasePage extends PageGenerator {
    public BasePage (WebDriver driver){
        super(driver);
    }
    WebDriverWait wait = new WebDriverWait(this.driver,20);
    SoftAssert sa = new SoftAssert();

    public void click(WebElement clickElement){
        wait.until(ExpectedConditions.elementToBeClickable(clickElement));
        clickElement.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void softAssertText(String actual, String expectedText){

        sa.assertEquals(actual, expectedText);
    }
    public void softAssertAll(){
        sa.assertAll();
    }


    public String readText(WebElement getTextElement){
        wait.until(ExpectedConditions.visibilityOf(getTextElement));
        return getTextElement.getText();
    }


    public  void assertText(String actual, String expectedText){
        Assert.assertEquals(actual, expectedText);
    }
    public  void assertTrue(WebElement actual){
        wait.until(ExpectedConditions.visibilityOf(actual));
        Assert.assertTrue(actual.isDisplayed());
    }

    
}
