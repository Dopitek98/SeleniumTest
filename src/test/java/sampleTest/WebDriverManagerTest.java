package sampleTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class WebDriverManagerTest {
   @Test
   public void openBrowser() throws IOException {
      WebDriverManager.chromedriver().setup();
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setHeadless(false);
      chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

      WebDriver driver = new ChromeDriver(chromeOptions);
      JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
      javascriptExecutor.executeScript("alert('hello')");
      driver.get("https://www.google.pl/");

      TakesScreenshot screenshot = (TakesScreenshot) driver;
      File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(srcFile , new File("src/test/resources/Screenshots/test1.png"));
      driver.quit();
   }

   @Test
   public void openBrowserAndTakeScreenshotWithUniqName() throws IOException {
      WebDriverManager.chromedriver().setup();
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setHeadless(false);
      chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

      int randNumber = (int) (Math.random()*1000);
      String fileName = "openBrowserAndTakeScreenshotWithUniqName" + randNumber + ".png";
      WebDriver driver = new ChromeDriver(chromeOptions);
      JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
      javascriptExecutor.executeScript("alert('hello')");
      driver.get("https://www.google.pl/");

      TakesScreenshot screenshot = (TakesScreenshot) driver;
      File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(srcFile , new File("src/test/resources/Screenshots/" + fileName));
      driver.quit();
   }



   @Test
   public void firstTestWithManager(){
      WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();

      driver.get("http://automationpractice.com/index.php");
      By buttonId = By.id("block_top_menu");
      WebElement menu = driver.findElement(buttonId);

      By tagInput = By.tagName("input");
      WebElement input = driver.findElement(tagInput);

      List<WebElement> inputList = driver.findElements(tagInput);
      inputList.stream().forEach(System.out::println);
      System.out.println(inputList.size());


      By linkText = By.linkText("Best Sellers");
      WebElement linkTextElements = driver.findElement(linkText);
   }

   @Test
   public void FindDataWithSelector(){
      WebDriverManager.chromedriver().setup();
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get("https://www.google.pl/");
      WebElement cookieBanner = chromeDriver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
      cookieBanner.click();

      WebElement search = chromeDriver.findElement(By.name("q"));
      search.sendKeys("Michał");
      search.clear();
      search.sendKeys("Kot");
      search.sendKeys(Keys.ENTER);

   }

   @Test
   public void FindDataWithSelectorAndClickWebElementWithMouse(){
      WebDriverManager.chromedriver().setup();
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get("https://www.google.pl/");
      WebElement cookieBanner = chromeDriver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
      cookieBanner.click();

      WebElement search = chromeDriver.findElement(By.name("q"));
      search.sendKeys("Michał");
      search.clear();
      search.sendKeys("Kot");

      Actions actions = new Actions(chromeDriver);
      actions.contextClick(chromeDriver.findElement(By.xpath("(//input[@value='Szukaj w Google'])[2]"))).perform();

   }

   @Test
   public void FindDataWithSelectorAndClickWebElementWithMouseTwoTimes(){
      WebDriverManager.chromedriver().setup();
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get("https://www.google.pl/");
      WebElement cookieBanner = chromeDriver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
      cookieBanner.click();

      WebElement search = chromeDriver.findElement(By.name("q"));
      search.sendKeys("Michał");
      search.clear();
      search.sendKeys("Kot");

      Actions actions = new Actions(chromeDriver);
      actions.doubleClick(chromeDriver.findElement(By.xpath("(//input[@value='Szukaj w Google'])[2]"))).perform();

   }

   @Test
   public void FindDataWithSelectorAndClickWebElementWithMouseTwoTimesAndGetPageInfo(){
      WebDriverManager.chromedriver().setup();
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get("https://www.google.pl/");
      WebElement cookieBanner = chromeDriver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
      cookieBanner.click();

      WebElement search = chromeDriver.findElement(By.name("q"));
      search.sendKeys("Michał");
      search.clear();
      search.sendKeys("Kot");

      Actions actions = new Actions(chromeDriver);
      actions.doubleClick(chromeDriver.findElement(By.xpath("(//input[@value='Szukaj w Google'])[2]"))).perform();

      String pageName = chromeDriver.getTitle();
      System.out.println(pageName);
   }

   @Test
   public void getDataFromTextField(){
      WebDriverManager.chromedriver().setup();
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get("https://www.google.pl/");
      WebElement cookieBanner = chromeDriver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
      cookieBanner.click();

      WebElement search = chromeDriver.findElement(By.name("q"));
      search.sendKeys("Michał");
      search.clear();
      search.sendKeys("Kot");
      System.out.println(search.getAttribute("value"));

      chromeDriver.quit();
   }

   @Test
   public void getDataFromTextFieldAndUnexpectedAlert(){
      WebDriverManager.chromedriver().setup();
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get("https://www.google.pl/");
      WebElement cookieBanner = chromeDriver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
      cookieBanner.click();

      WebElement search = chromeDriver.findElement(By.name("q"));
      search.sendKeys("Michał");
      search.clear();
      search.sendKeys("Kot");
      JavascriptExecutor javascriptExecutor = (JavascriptExecutor) chromeDriver;
      javascriptExecutor.executeScript("alert('hello')");

      Alert alert = chromeDriver.switchTo().alert();
      alert.getText();
      alert.accept();

     // search.sendKeys(Keys.ENTER);
      chromeDriver.quit();
   }

   @Test
   public void getDataFromTextFieldAndPressSearchUsingJavaScriptExecutor(){
      WebDriverManager.chromedriver().setup();
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get("https://www.google.pl/");
      WebElement cookieBanner = chromeDriver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
      cookieBanner.click();

      WebElement search = chromeDriver.findElement(By.name("q"));
      search.sendKeys("Michał");
      search.clear();
      search.sendKeys("Kot");

      WebElement pressSearch = chromeDriver.findElement(By.xpath("(//input[@value='Szukaj w Google'])[2]"));
      JavascriptExecutor javascriptExecutor = (JavascriptExecutor) chromeDriver;
      javascriptExecutor.executeScript("arguments[0].click();" , pressSearch);
      // search.sendKeys(Keys.ENTER);
      chromeDriver.quit();
   }
   @Test

   public void getDataFromTextFieldAndSearchUsingJavaScriptExecutor(){
      WebDriverManager.chromedriver().setup();
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get("https://www.google.pl/");
      WebElement cookieBanner = chromeDriver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
      cookieBanner.click();

      WebElement search = chromeDriver.findElement(By.name("q"));

      JavascriptExecutor javascriptExecutor = (JavascriptExecutor) chromeDriver;
      javascriptExecutor.executeScript("arguments[0].setAttribute('value','Michał');",search);

      WebElement pressSearch = chromeDriver.findElement(By.xpath("(//input[@value='Szukaj w Google'])[2]"));
      //JavascriptExecutor javascriptExecutor = (JavascriptExecutor) chromeDriver;
      javascriptExecutor.executeScript("arguments[0].click();" , pressSearch);
      // search.sendKeys(Keys.ENTER);
      chromeDriver.quit();
   }


   @Test
   public void getDataFromTextFieldAndUnexpectedNewPopup(){
      WebDriverManager.chromedriver().setup();
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get("https://www.google.pl/");
      WebElement cookieBanner = chromeDriver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
      cookieBanner.click();

      WebElement search = chromeDriver.findElement(By.name("q"));
      search.sendKeys("Michał");
      search.clear();
      search.sendKeys("Kot");

      String currentWindow = chromeDriver.getWindowHandle();

      JavascriptExecutor javascriptExecutor = (JavascriptExecutor) chromeDriver;
      javascriptExecutor.executeScript(" window.open('http://automationpractice.com/index.php','_blank')");

      Set<String> windowNames = chromeDriver.getWindowHandles();
      for (String window : windowNames){
         if(!window.equals(currentWindow))
            chromeDriver.switchTo().window(window);
      }

      chromeDriver.switchTo().window(currentWindow);

      search.sendKeys(Keys.ENTER);
    //  chromeDriver.quit();
   }

   @Test
   public void firstTestWithSelect() throws InterruptedException {
      WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();

      driver.get("http://automationpractice.com/index.php");

      WebElement element = driver.findElement(By.cssSelector("[title='Printed Dress']"));
      element.click();

      Thread.sleep(10000);
      driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='fancybox-iframe']")));
      WebElement select = driver.findElement(By.xpath("//select[@name='group_1']"));
      Select select1 = new Select(select);
      select1.selectByIndex(2);

      List<WebElement> options = select1.getOptions();
      for(WebElement s:options){
         System.out.println(s.getText());
      }
   }

   @Test
   public void firstTestWithSelectAndMethod() throws InterruptedException {
      WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();

      driver.get("http://automationpractice.com/index.php");

      WebElement element = driver.findElement(By.cssSelector("[title='Printed Dress']"));
      element.click();

      Thread.sleep(10000);
      driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='fancybox-iframe']")));
      WebElement select = driver.findElement(By.xpath("//select[@name='group_1']"));
      Assert.assertFalse(isSelectOption("XL", select));
   }
   public boolean isSelectOption(String parameter, WebElement element){
      Select select = new Select(element);
      List<WebElement> options = select.getOptions();

      for(WebElement s: options){
         if(s.getText().equals(parameter))
            return true;
      }
      return false;
   }
}
