package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.PageGenerator;

import java.util.concurrent.TimeUnit;



public class BaseTest {
    public static  WebDriver driver;
    public WebDriverWait wait;
    public SoftAssert sa;
    public PageGenerator page;

    String browser = "chrome";
    @BeforeMethod
    public void setUp() {
        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 15);
            sa = new SoftAssert();
            page = new PageGenerator(driver);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, 15);
            sa = new SoftAssert();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 15);
            sa = new SoftAssert();
            page = new PageGenerator(driver);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}


