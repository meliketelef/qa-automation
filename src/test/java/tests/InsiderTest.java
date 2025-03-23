package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CareerPage;
import pages.QAJobsPage;

public class InsiderTest extends BaseTest {

    @Test
    public void testInsiderCareerPage() {
        HomePage homePage = new HomePage(driver);
        homePage.goToHomepage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened!");

        homePage.clickCompanyMenu();
        homePage.clickCareersLink();

        CareerPage careerPage = new CareerPage(driver);
        Assert.assertTrue(careerPage.isCareersPageOpened(), "Careers page is not opened!");
        Assert.assertTrue(careerPage.isLocationsSectionVisible(), "Locations section is not visible!");
        Assert.assertTrue(careerPage.isLifeAtInsiderVisible(), "Life at Insider section is not visible!");
        careerPage.acceptCookiesIfVisible();

        careerPage.goToQAJobs();

        QAJobsPage qaJobsPage = new QAJobsPage(driver);
        qaJobsPage.clickSeeAllQAJobs();
        qaJobsPage.filterByLocationAndDepartment();
        Assert.assertTrue(qaJobsPage.areJobsDisplayed(), "No QA jobs found!");

    }
}
