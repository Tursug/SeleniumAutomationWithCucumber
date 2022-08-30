package Base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {
    public WebDriver webDriver;
    public WebDriverWait wait;
    public WebElement element;

    JavascriptExecutor executor;

    public Base(){
        System.setProperty("webdriver.chrome.driver","src/test/java/Driver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        options.addArguments("disable-notifications");

        webDriver = new ChromeDriver(options);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(TimeOut.MIDDLE.value));

        executor = (JavascriptExecutor) webDriver;
    }

    public enum Type{
        id,
        className,
        name,
        xPath,
        cssSelector,
        linkText,
        tagName
    }

    public enum TimeOut{
        LOW(5),
        MIDDLE(10),
        HIGH(15),
        CUSTOM_MAX(60);

        private final int value;

        public int getValue(){
            return value;
        }

        private TimeOut(int value){
            this.value = value;
        }

    }

    public void getUrl(){
        webDriver.get("https://www.zingat.com");
    }

    public void waitElement(WebElement element, TimeOut timeOut){
        try{
            wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOut.value));
            wait.until(ExpectedConditions.invisibilityOf(element));
        }catch (Exception ignored){

        }
    }

    public void findElementAndClick (String value, Type type, TimeOut timeOut){
        try{
            wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOut.value));
            switch (type){
                case className -> wait.until(ExpectedConditions.elementToBeClickable(By.className(value))).click();
                case id -> wait.until(ExpectedConditions.elementToBeClickable(By.id(value))).click();
                case name -> wait.until(ExpectedConditions.elementToBeClickable(By.name(value))).click();
                case xPath -> wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value))).click();
                case cssSelector -> wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(value))).click();
                case linkText -> wait.until(ExpectedConditions.elementToBeClickable(By.linkText(value))).click();
                case tagName -> wait.until(ExpectedConditions.elementToBeClickable(By.tagName(value))).click();
                default -> throw new NotFoundException();
            }
        }catch (Exception ignored){

        }
    }

    public WebElement findElement(String path, Type type, TimeOut timeOut){
        try{
            wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOut.value));
            element = null;

            switch (type){
                case className -> element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(path)));
                case id -> element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(path)));
                case name -> element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(path)));
                case xPath -> element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
                case cssSelector -> element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(path)));
                case linkText -> element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(path)));
                case tagName -> element = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(path)));
                default -> throw new NotFoundException();
            }
        }catch (Exception ignored){

        }
        return element;
    }

    public void sendKeys(String value, String path, Type type, TimeOut timeOut){
        WebElement element= findElement(path,type,timeOut);
        element.sendKeys(value);
    }

    public String getTitle(){
        return webDriver.getTitle();
    }

    public void scrollDown(){
        executor.executeScript("window.scrollBy(0,300)", "");
    }

    public void scrollUp(){
        executor.executeScript("window.scrollBy(0,-300","");
    }

    public void quit(){
        webDriver.quit();
    }


}
