package testy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Listy_zadanie {
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
        driver.get("https://fakestore.testelka.pl/product-category/windsurfing/");
    }

    @AfterEach
    public void Close() {
        driver.close();
        driver.quit();
    }

    @Test
    public void sortAscending() {
        String expectedLowest = "2 900,00 zł";
        WebElement list = driver.findElement(By.cssSelector("[name='orderby']"));
        Select categorylist = new Select(list);
        categorylist.selectByIndex(4);
        WebElement lowest = driver.findElement(By.xpath("//*[@id='main']/ul/li[1]/a[1]/span/span"));
        System.out.println(lowest.getText());
        Assertions.assertEquals(lowest.getText(),expectedLowest,"This is not the lowest price");
    }

    @Test
    public void sortDescending() {
        String expectedHighest = "5 399,00 zł";
        WebElement list = driver.findElement(By.cssSelector("[name='orderby']"));
        Select categorylist = new Select(list);
        categorylist.selectByIndex(5);
        WebElement highest = driver.findElement(By.xpath("//*[@id='main']/ul/li[1]/a[1]/span/span"));
        System.out.println(highest.getText());
        Assertions.assertEquals(highest.getText(),expectedHighest,"This is not the highest price");
    }

}
