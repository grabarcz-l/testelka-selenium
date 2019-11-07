package testy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Java_script {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void LaunchSettings() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void Close() {
        driver.close();
        driver.quit();
    }

    @Test
    public void Alert() {
        driver.get("https://fakestore.testelka.pl/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("console.log('taki o to tekst');");
        String domain = (String)js.executeScript("return document.domain;");
    }

    @Test
    public void aSync() {
        Long start = System.currentTimeMillis();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length -1],500);");
        Long elapsedtime = System.currentTimeMillis()-start;
        System.out.println(elapsedtime);
    }

    @Test
    public void aSync2() {
        Long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length -1],500);");
        Long elapsedtime2 = System.currentTimeMillis()-start;
        System.out.println(elapsedtime2);
    }

    @Test
    public void scrollingView() {
        driver.get("https://fakestore.testelka.pl/product/zmien-swoja-sylwetke-yoga-na-malcie/");
        WebElement opis = driver.findElement(By.cssSelector("div#tab-description"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()",opis);
        List<WebElement> lista = driver.findElements(By.cssSelector("section.storefront-sticky-add-to-cart--slideInDown"));
        Assertions.assertTrue(lista.size()==1, "\ncoś nie pykło");
        System.out.println(lista.size());
    }




}
