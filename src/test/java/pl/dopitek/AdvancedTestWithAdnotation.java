package pl.dopitek;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.dopitek.BaseTest;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

@Listeners(value = {SampleTestListener.class})
public class AdvancedTestWithAdnotation extends BaseTest{

    WebDriver driver;

    @Test
    public void elementExists(){
        driver = DriverFactory.getDriver();

        driver.get("https://www.google.pl/");
        clickCookieBanner();
        System.out.println(isElementExist(By.tagName("div")));

        driver.findElement(By.tagName("div")).isDisplayed();

    }

    @Test
    public void elementNotExists(){
        driver = DriverFactory.getDriver();

        driver.get("https://www.google.pl/");

        waitForElementToExistLambda(By.tagName("div"));
    }

    @Test @Ignore
    public void elementNotExistsAndIgnoredWithFluentWaitAndAfter10sThrowNoSuchElementException(){
        driver = DriverFactory.getDriver();

        driver.get("https://www.google.pl/");

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.ignoring(NoSuchElementException.class);
        fluentWait.withTimeout(Duration.ofSeconds(10));
        fluentWait.pollingEvery(Duration.ofSeconds(2));
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));

    }

    @Test
    public void elementExistsFurtherTest(){
        driver = DriverFactory.getDriver();

        driver.get("https://www.google.pl/");

        System.out.println(isElementExist(By.tagName("div")));

        driver.findElement(By.tagName("div")).isDisplayed();
        driver.findElement(By.tagName("div")).isEnabled();

        WebElement image = driver.findElement(By.xpath("(//img[@alt='Google'])[1]"));

        String imageHeight = image.getAttribute("naturalHeight");
        Assert.assertNotEquals(imageHeight,0);
    }


    @Test
    public void searchWithGoogleWithAssert() {
        driver = DriverFactory.getDriver();

        driver.get("https://www.google.pl/");

        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("Michał");
        search.clear();
        search.sendKeys("Kot");
        search.sendKeys(Keys.ENTER);

        Assert.assertEquals(driver.getTitle(),"Kot - Szukaj w Google");
        Assert.assertEquals(driver.getTitle(),"Kot - Nie Szukaj w Google");

    }

    @Test
    public void searchWithGoogleWithSoftAssert() {
        driver = DriverFactory.getDriver();

        driver.get("https://www.google.pl/");

        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("Michał");
        search.clear();
        search.sendKeys("Kot");
        search.sendKeys(Keys.ENTER);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(),"Kot - Szukaj w Google");
        softAssert.assertEquals(driver.getTitle(),"Kot - Nie Szukaj w Google");
        softAssert.assertEquals(driver.getTitle(),"Kot - Znajdź w Google");

        softAssert.assertAll();
    }


    public void waitForElementToExist(By locator){
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.ignoring(NoSuchElementException.class);
        fluentWait.withTimeout(Duration.ofSeconds(10));
        fluentWait.pollingEvery(Duration.ofSeconds(2));

        fluentWait.until(new Function<WebDriver, Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                List<WebElement> elements = driver.findElements(locator);
                if (elements.size()>0) {
                    System.out.println("element jest na stronie");
                    return true;
                } else {
                    System.out.println("elementu nie ma na stronie");
                    return false;
                }
            }
        });
    }

    public void waitForElementToExistLambda(By locator){
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.ignoring(NoSuchElementException.class);
        fluentWait.withTimeout(Duration.ofSeconds(10));
        fluentWait.pollingEvery(Duration.ofSeconds(2));

        fluentWait.until((driver) -> {
            List<WebElement> elements = driver.findElements(locator);
            if (elements.size()>0) {
                System.out.println("element jest na stronie");
                return true;
            } else {
                System.out.println("elementu nie ma na stronie");
                return false;
            }
        });

    }

    public boolean isElementExist(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    public void clickCookieBanner(){
        driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']")).click();
    }

}
