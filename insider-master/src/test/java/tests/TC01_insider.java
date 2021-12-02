package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;

@Listeners(utilities.Listeners.class)
public class TC01_insider extends BaseTest{

    @Test (priority = 0)
    public void checkJobDetails() throws InterruptedException {

        page.GetInstance(HomePage.class).goToInsider();
        page.GetInstance(HomePage.class).clickCareers();
        page.GetInstance(CareersPage.class).clickFindYourDreamJob();
        page.GetInstance(CareersPage.class).filterJobOptions("Istanbul, Turkey", "Quality Assurance");
        page.GetInstance(CareersPage.class).checkJobResults("Istanbul, Turkey", "Quality Assurance");
        String jobTitle = page.GetInstance(CareersPage.class).clickRandomJob();
        page.GetInstance(JobDetailsPage.class).checkSelectedTrueJob(jobTitle);
        page.GetInstance(JobDetailsPage.class).checkLever();
        page.GetInstance(BasePage.class).softAssertAll();

    }
}
