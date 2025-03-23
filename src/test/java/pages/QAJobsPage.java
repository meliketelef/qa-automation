package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class QAJobsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public QAJobsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(css = "a[href*='open-positions/?department=qualityassurance']")
    private WebElement seeAllQAJobsButton;

    @FindBy(css = "span.select2-selection.select2-selection--single[aria-labelledby='select2-filter-by-location-container']")
    private WebElement locationDropdown;

    @FindBy(xpath = "//li[contains(text(),'Istanbul, Turkiye')]")
    private WebElement istanbulOption;

    @FindBy(css = "span.select2-selection--single[aria-labelledby='select2-filter-by-department-container']")
    private WebElement departmentDropdown;

    @FindBy(xpath = "//li[contains(text(),'Quality Assurance')]")
    private WebElement qualityAssuranceOption;

    @FindBy(css = ".job-listing")
    private List<WebElement> jobListings;

    @FindBy(css = ".job-listing a[target='_blank']")
    private WebElement viewRoleButton;

    public void clickSeeAllQAJobs() {
        wait.until(ExpectedConditions.elementToBeClickable(seeAllQAJobsButton)).click();
    }

    public void filterByLocationAndDepartment() {
        wait.until(ExpectedConditions.elementToBeClickable(locationDropdown)).click();
        sleep(500);
        scrollIntoViewAndClick(istanbulOption);

        wait.until(ExpectedConditions.elementToBeClickable(departmentDropdown)).click();
        sleep(500);
        scrollIntoViewAndClick(qualityAssuranceOption);
    }

    public boolean areJobsDisplayed() {
        return !jobListings.isEmpty();
    }

    public void clickFirstViewRoleAndSwitchToLever() {
        wait.until(ExpectedConditions.visibilityOf(viewRoleButton)).click();

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains("lever.co")) {
                break;
            }
        }
    }

    public boolean isOnLeverSite() {
        return driver.getCurrentUrl().contains("lever.co");
    }

    private void scrollIntoViewAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ block: 'center' });", element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
