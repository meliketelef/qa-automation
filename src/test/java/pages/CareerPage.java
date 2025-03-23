package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CareerPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[contains(@class,'location-info')]")
    private WebElement locationInfo;

    @FindBy(css = "a.loadmore")
    private WebElement seeAllTeamsButton;

    @FindBy(xpath = "//h2[contains(text(), 'Life at Insider')]")
    private WebElement lifeAtInsiderSection;

    @FindBy(xpath = "//a[h3[text()='Quality Assurance']]")
    private WebElement qaJobsLink;

    @FindBy(id = "wt-cli-accept-all-btn")
    private WebElement acceptCookiesButton;

    public CareerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookiesIfVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(acceptCookiesButton))
                    .click();
        } catch (Exception ignored) {}
    }

    public void scrollToElementWithWait(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isCareersPageOpened() {
        return driver.getTitle().contains("Careers");
    }

    public void clickSeeAllTeamsButton() {
        scrollToElementWithWait(seeAllTeamsButton);
        try {
            seeAllTeamsButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", seeAllTeamsButton);
        }
    }

    public boolean isLocationsSectionVisible() {
        scrollToElementWithWait(locationInfo);
        return locationInfo.isDisplayed();
    }

    public boolean isLifeAtInsiderVisible() {
        clickSeeAllTeamsButton();
        scrollToElementWithWait(lifeAtInsiderSection);
        return lifeAtInsiderSection.isDisplayed();
    }

    public void goToQAJobs() {
        scrollToElementWithWait(qaJobsLink);
        try {
            qaJobsLink.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", qaJobsLink);
        }
    }
}
