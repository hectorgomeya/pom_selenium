package Page;

import ChromeManager.UtilsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ValidateMessagePage {

    @FindBy(xpath = "//p[@class=\"lead text-muted \"]")
    WebElement tableMessage;
    private WebDriver driver;
    private WebDriverWait wait;

    private UtilsManager utilsManager;

    public ValidateMessagePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        utilsManager = new UtilsManager(driver, wait);
    }

    public void validateMessageOk(){
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()=\"Thank you for your purchase!\"]"))).isDisplayed());
        System.out.println(wait.until(ExpectedConditions.visibilityOf(tableMessage)).getText());
    }
}
