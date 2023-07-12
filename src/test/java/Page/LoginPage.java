package Page;

import ChromeManager.UtilsManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//button[text()=\"Log in\"]")
    WebElement buttonLogIn;

    @FindBy(id = "loginusername")
    WebElement username;

    @FindBy(id = "loginpassword")
    WebElement password;

    private UtilsManager utilsManager;
    public LoginPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        utilsManager = new UtilsManager(driver, wait);
    }

    public void login(String username, String password, String flag) throws IOException, InvalidFormatException {

        switch (flag){
            case "correcto":
                wait.until(ExpectedConditions.elementToBeClickable(this.username)).sendKeys(username);
                wait.until(ExpectedConditions.elementToBeClickable(this.password)).sendKeys(password);
                wait.until(ExpectedConditions.elementToBeClickable(buttonLogIn)).click();
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))).getText().contains(username));
                //utilsManager.takeScreenShot("login_correcto");
                //utilsManager.createFileWithEvidenceScreenShot("login_correcto","Evidencia_login", "LOGIN CORRECTO");
                break;
            case "incorrecto":
                wait.until(ExpectedConditions.visibilityOf(this.username)).sendKeys(username);
                wait.until(ExpectedConditions.visibilityOf(this.password)).sendKeys(password);
                wait.until(ExpectedConditions.elementToBeClickable(buttonLogIn)).click();
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                //utilsManager.takeScreenShot("login_incorrecto");
                //utilsManager.createFileWithEvidenceScreenShot("login_incorrecto","Evidencia_login", "LOGIN CORRECTO");
                break;
        }
    }
}
