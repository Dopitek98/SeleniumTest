package sampleTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
    }

    @Test
    public void test(){
        System.out.println("hello");
    }

    @Test
    public void openGoogleOnChrome(){
        WebDriver driver = getDriver("Chrome");
        driver.manage().window().maximize();
        driver.get("https://www.google.pl/");
        driver.quit();
    }

    @Test
    public void openGoogleOnChromeWithSelectedDimension(){
        WebDriver driver = getDriver("Chrome");
        Dimension dimension = new Dimension(200,400);
        driver.manage().window().setSize(dimension);
        driver.get("https://www.google.pl/");
        driver.quit();
    }

    @Test
    public void FirstOnChromeTest(){
        WebDriver driver = getDriver("Chrome");
        driver.manage().window().maximize();
        driver.get("https://www.google.pl/");

        //driver.switchTo().frame(0);

        WebElement cookieButton = driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
        cookieButton.click();
        driver.switchTo().defaultContent();

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("MERIDA");
        searchField.sendKeys(Keys.ENTER);

        WebElement searchresultField = driver.findElement(By.xpath("//h3[text()='MERIDA BIKES Poland']"));
        Assert.assertTrue(searchresultField.isDisplayed());
        searchresultField.click();

        driver.quit();
    }


    @Test
    public void openGoogleOnEdge(){
        WebDriver driver = getDriver("edge");
        driver.get("https://www.google.pl/");
        driver.quit();
    }

    public WebDriver getDriver(String driver){
        switch (driver.toLowerCase()){
            case "chrome":
              //  System.setProperty("webdriver.chrome.driver","C:\\Users\\szyme\\Desktop\\tutorialselenium\\src\\main\\resources\\chromedriver.exe");
              // not neccessary after adding system variable with path
                return new ChromeDriver();
            case "edge":
               // System.setProperty("webdriver.edge.driver","C:\\Users\\szyme\\Desktop\\tutorialselenium\\src\\main\\resources\\msedgedriver.exe");
                return new EdgeDriver();
            default:
                throw new IllegalArgumentException("Not supported webdriver");
        }
    }
}

