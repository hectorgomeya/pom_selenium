package ChromeManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class ChromeManager {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");              // Maximize the browser window
        chromeOptions.addArguments("--disable-infobars");             // Disable the "Chrome is being controlled by automated test software" infobar
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-cache");// Disable browser extensions
        chromeOptions.addArguments("--disable-popup-blocking");       // Disable popup blocking
        chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.demoblaze.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @AfterSuite
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }
}
