package sampleTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.dopitek.BaseTest;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class AdvancedTest extends BaseTest {

    WebDriver driver;
    @Test
    public void elementExists(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.google.pl/");
        WebElement cookieBanner = driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
        cookieBanner.click();

        System.out.println(isElementExist(By.tagName("div")));

        driver.findElement(By.tagName("div")).isDisplayed();
    }

    @Test
    public void elementNotExists(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.google.pl/");
        WebElement cookieBanner = driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
        cookieBanner.click();

        waitForElementToExistLambda(By.tagName("p"));
    }

    @Test
    public void elementNotExistsAndIgnoredWithFluentWaitAndAfter10sThrowNoSuchElementException(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.google.pl/");
        WebElement cookieBanner = driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
        cookieBanner.click();

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.ignoring(NoSuchElementException.class);
        fluentWait.withTimeout(Duration.ofSeconds(10));
        fluentWait.pollingEvery(Duration.ofSeconds(2));
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));

    }

    @Test
    public void elementExistsFurtherTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.google.pl/");
        WebElement cookieBanner = driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
        cookieBanner.click();

        System.out.println(isElementExist(By.tagName("div")));

        driver.findElement(By.tagName("div")).isDisplayed();
        driver.findElement(By.tagName("div")).isEnabled();

        WebElement image = driver.findElement(By.xpath("(//img[@alt='Google'])[1]"));

        String imageHeight = image.getAttribute("naturalHeight");
        Assert.assertNotEquals(imageHeight,0);


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


}
