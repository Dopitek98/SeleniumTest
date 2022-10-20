import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CssSelectorsTest {

    @Test
    public void FindDataWithSelector(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver();

        chromeDriver.get("http://automationpractice.com/index.php");

        By cssId = By.cssSelector("#block_top_menu");
        chromeDriver.findElement(cssId);

        By cssClass = By.cssSelector(".button-container");
        chromeDriver.findElement(cssClass);

        By cssTagName = By.cssSelector("input");
        chromeDriver.findElement(cssTagName);

        By cssName = By.cssSelector("[name='controller']");
        chromeDriver.findElement(cssName);

        By cssClass2 = By.cssSelector("[class='button-container']");
        chromeDriver.findElement(cssClass2);

        By cssAll = By.cssSelector("*");
        Integer size = chromeDriver.findElements(cssAll).size();
        System.out.println(size);

        By doubleSelector = By.cssSelector("li div");
        chromeDriver.findElement(doubleSelector);


    }
}
