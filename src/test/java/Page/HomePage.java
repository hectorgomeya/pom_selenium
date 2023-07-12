package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {

    @FindBy(xpath = "//a[text()=\"Phones\"]")
    WebElement categoryPhones;

    @FindBy(xpath = "//a[text()=\"Monitors\"]")
    WebElement categoryMonitors;

    @FindBy(xpath = "//a[text()=\"Laptops\"]")
    WebElement categoryLaptops;

    @FindBy(id = "login2")
    WebElement loginButton;


    @FindBy(id = "cartur")
    WebElement cartButton;
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void selectCategory(String category){
        switch (category){
            case "monitors":
                wait.until(ExpectedConditions.visibilityOf(categoryMonitors)).click();
                break;
            case "laptops":
                wait.until(ExpectedConditions.visibilityOf(categoryLaptops)).click();
                break;
            case "phones":
                wait.until(ExpectedConditions.visibilityOf(categoryPhones)).click();
                break;
        }
    }

    public void selectLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void selectProduct(String product){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"" + product + "\"]"))).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("prod"));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name"))).getText().equals(product));
    }

    public void goToCart(){
        wait.until(ExpectedConditions.visibilityOf(cartButton)).click();
    }
}
