package pl.dopitek;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
static WebDriver driver;
    @BeforeTest
    public void beforeTest(){
        System.out.println("I am running before test");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("I am running after test");
    }


    @BeforeMethod
    public void beforeMethod(){
        System.out.println("I am running before method");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("I am running after method");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before suite");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before class");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After suite");
        driver = DriverFactory.getDriver();
        driver.quit();
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After class");
    }

}
