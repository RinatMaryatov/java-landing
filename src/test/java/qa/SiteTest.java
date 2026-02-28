package qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteTest {

    @Test
    void openSiteTest() {

System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

ChromeOptions options = new ChromeOptions();
options.setBinary("/usr/bin/chromium");
options.addArguments("--headless=new");
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--remote-allow-origins=*");
options.addArguments("--disable-gpu");

WebDriver driver = new ChromeDriver(options);

        driver.get("https://java-landing-521w.onrender.com/");
        WebElement paragraph = driver.findElement(By.xpath("/html/body/div/p"));

        String actualText = paragraph.getText();

        Assertions.assertEquals("Ринат молодец", actualText);


        driver.quit();
    }
}
