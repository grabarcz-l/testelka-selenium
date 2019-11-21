package testy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Gesty_zadanie2 {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    WebElement draggable;
    WebElement droppable;
    WebElement afterdropping;
    String expectedMessage;

    @BeforeEach
    public void LaunchSettings() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
               driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://fakestore.testelka.pl/actions");/*URL only for excercises !*/

        draggable = driver.findElement(By.cssSelector("#draggable"));
        droppable = driver.findElement(By.cssSelector("#droppable"));
        expectedMessage = "Dropped!";
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

/*
Napisz poniższe testy. Skorzystaj z przykładu pierwszego (drag and drop) na stronie https://fakestore.testelka.pl/actions.

1.Sprawdź czy przesunięcie żółtego kwadratu na różowy działa i czy widoczna jest informacja, że kwadrat został upuszczony poprawnie.
2.Sprawdź czy przesunięcie żółtego kwadratu tak, by jego środek znalazł się (mniej więcej) w prawym dolnym rogu różowego działa i czy widoczna jest informacja, że kwadrat został upuszczony poprawnie.
3.Sprawdź czy przesunięcie żółtego kwadratu o 160 pikseli w prawo i o 40 pikseli w dół spowoduje upuszczenie na różowym kwadracie.
*/
    @Test
    public void test1() {
//        actions.clickAndHold(draggable).moveToElement(droppable).release().build().perform();
        actions.dragAndDrop(draggable, droppable).build().perform();
        Assertions.assertEquals(expectedMessage, afterdropping.getText(),"element nie zostal upuszczony");
    }

    @Test
    public void test2() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", draggable);
//        actions.dragAndDropBy(draggable,218,93).release().build().perform();
//        actions.clickAndHold(draggable).moveByOffset(218,93).release().build().perform();
      actions.clickAndHold(draggable).moveToElement(droppable,74,74).release().build().perform();
        Assertions.assertEquals(expectedMessage, droppable.getText(), "Moved element isn't within pink Box !");
    }

    @Test
    public void test3() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", draggable);
        actions.dragAndDropBy(draggable,160,40).release().build().perform();
        Assertions.assertEquals(expectedMessage,droppable.getText(),"Box nie zostal upuszczony poprawnie !");
    }

}
