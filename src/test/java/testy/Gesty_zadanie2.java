package testy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Gesty_zadanie2 {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeEach
    public void LaunchSettings() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void Exit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void offsetExample() {
        driver.navigate().to("https://marcojakob.github.io/dart-dnd/detection_only/");
        WebElement draggable = driver.findElement(By.cssSelector(".draggable"));
        actions.dragAndDropBy(draggable, 20, 20).build().perform();
        actions.clickAndHold(draggable).moveByOffset(20,20).release().build().perform();
        actions.moveToElement(draggable).clickAndHold().moveByOffset(20,20).release().build().perform();
    }

    @Test
    public void toElementExample() {
        driver.navigate().to("http://marcojakob.github.io/dart-dnd/nested_dropzones/");
        WebElement draggable = driver.findElement(By.cssSelector(".draggable"));
        WebElement dropElement = driver.findElement(By.cssSelector(".dropzone-inner"));
        WebElement dragElement = driver.findElement(By.cssSelector(".dropzone-outer"));
        //actions.dragAndDrop(draggable,dropElement).build().perform();
        //actions.clickAndHold(draggable).moveToElement(dropElement).release().build().perform();
        //actions.clickAndHold(dragElement).release(dropElement).build().perform();
        actions.clickAndHold(draggable).moveToElement(dropElement,2,2).release().build().perform();
    }

    @Test
    public void test1() {
        driver.navigate().to("https://fakestore.testelka.pl/actions");
    }

}
