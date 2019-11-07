package testy;

import org.junit.jupiter.api.AfterEach;
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

public class Listy {
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
        driver.get("https://allegro.pl/");
        driver.manage().addCookie(new Cookie("gdpr_permission_given","1"));
        driver.navigate().refresh();
    }

    @AfterEach
    public void Close() {
        driver.close();
        driver.quit();
    }

    @Test
    public void selectElelemnt() {
        WebElement list = driver.findElement(By.cssSelector("[data-role='filters-dropdown-toggle']"));
        Select categorylist = new Select(list);
        categorylist.selectByIndex(3);
        Boolean isMultiple = categorylist.isMultiple();
        List<WebElement> dropdownList = categorylist.getOptions();
        WebElement first = categorylist.getFirstSelectedOption();
    }

}
