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

import java.util.List;
import java.util.concurrent.TimeUnit;

/*POLECNIA do ZADAN !!!

Przejdź na stronę https://fakestore.testelka.pl/actions/ i wykonaj następujące testy (do każdego wykonaj asercję).

1. Potwierdź, że w punkcie numer 2, wybór opcji „Koszyk” z menu kontekstowego przenosi nas na stronę koszyka.
2. Potwierdź, że w punkcie numer 3, podwójny klik na prostokącie zmieni jego kolor na #f55d7a (do konwersji z hex na rgba możesz użyć tego narzędzia).
3. Potwierdź, że w punkcie numer 5, po wprowadzeniu i zatwierdzeniu tekstu poniżej przycisku „Zatwierdź” zostanie wyświetlona informacja „Wprowadzony tekst: ” plus Twój tekst.
4. Potwierdź, że w punkcie numer 6, zaznaczanie kwadratów z liczbami ze wciśniętym przyciskiem CTRL nie powoduje odznaczania już zaznaczonych (czyli jeżeli przytrzymamy CTRL i wybierzemy 1, 5 i 7 to wszystkie trzy kafelki będą zaznaczone).
*/

public class Gesty_zadanie1 {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeEach
    public void LaunchSettings() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://fakestore.testelka.pl/actions/");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void Exit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void test1() {
        WebElement menu = driver.findElement(By.cssSelector("#menu-link"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menu);
        actions.contextClick(menu).build().perform();
        WebElement menu_cart = driver.findElement(By.cssSelector("li.menu-cart"));
        actions.click(menu_cart).build().perform();
        String expected_cart_url = "https://fakestore.testelka.pl/koszyk/";
        Assertions.assertEquals(expected_cart_url, driver.getCurrentUrl(), "URL is wrong,  may be you're in wrong place ?");
    }

    @Test
    public void test2() {
        WebElement box = driver.findElement(By.cssSelector("#double-click"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", box);
        actions.doubleClick(box).build().perform();
        Assertions.assertEquals("rgba(245, 93, 122, 1)", box.getCssValue("background-color"), "doubleclick doesn't work");
    }

    @Test
    public void test3() {
        WebElement field = driver.findElement(By.cssSelector("#input"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", field);
        WebElement textCommit = driver.findElement(By.cssSelector("div > p:nth-child(9) > button"));
        actions.click(field).sendKeys("mojeTestowanie").build().perform();
        actions.click(textCommit).build().perform();
        String textUploaded = "Wprowadzony tekst: mojeTestowanie\n" +
                "\n";
        WebElement textOutput = driver.findElement(By.cssSelector("#output"));
        Assertions.assertEquals("Wprowadzony tekst: mojeTestowanie", textOutput.getText(),"Wartosci nie sa rowne");
    }

    @Test
    public void test4() {
        WebElement macierz = driver.findElement(By.className("ui-selectable"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",macierz);
        List<WebElement> buttons = driver.findElements(By.xpath("//*[@id='selectable']/li"));
        actions.keyDown(Keys.CONTROL).click(buttons.get(2)).click(buttons.get(4)).click(buttons.get(6)).build().perform();
        Assertions.assertAll(
                ()-> Assertions.assertTrue(buttons.get(2).getAttribute("class").contains("ui-selected"),"Przycisk o numerze 3 nie został wciśnięty"),
                ()-> Assertions.assertTrue(buttons.get(4).getAttribute("class").contains("ui-selected"),"Przycisk o numerze 4 nie został wciśnięty"),
                ()-> Assertions.assertTrue(buttons.get(6).getAttribute("class").contains("ui-selected"),"Przycisk o numerze 6 nie został wciśnięty")
        );
    }

}
