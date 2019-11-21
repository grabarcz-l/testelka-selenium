package testy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebStorage {
    ChromeDriver driver;



    @BeforeEach
    public void LaunchSettings() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void closing() {
        driver.close();
        driver.quit();
    }

    @Test
    public void LocalStorageExample() {
        driver.navigate().to("https://airly.eu/map/pl/#51.78181,19.45962");
        LocalStorage local = driver.getLocalStorage();
        String value = local.getItem("persist:nps");
        int size = local.size();
        Set<String> keys = local.keySet();
        local.setItem("newONE", "Yahoo");
        String removed = local.removeItem("persist:nps");
    }

    @Test
    public void SessionStorageExample() {
        driver.navigate().to("https://www.youtube.com/watch?v=h6A6vlv0mU4");
        SessionStorage session = driver.getSessionStorage();
        String value = session.getItem("yt-remote-session-app");
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(a->session.size()==5);
        int size = session.size();
        Set<String> keys = session.keySet();
        session.setItem("newONE", "Yahoo");
        String removed = session.removeItem("yt-remote-session-app");
        Set<String> newkeys = session.keySet();
        session.clear();
    }
}
