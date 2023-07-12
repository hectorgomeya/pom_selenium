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

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private UtilsManager utilsManager;

    @FindBy(xpath = "//button[text()=\"Place Order\"]")
    WebElement placeOrder;

    public CartPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        utilsManager = new UtilsManager(driver, wait);
    }

    public void validatePriceAndProductAndDelete(String product, String price){
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()=\""+ product + "\"]"))).getText(), product);
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()=\""+ price + "\"]"))).getText(), price);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"Delete\"]"))).click();
    }

    public void validatePriceAndProductAndSubmit(String product, String price){
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()=\""+ product + "\"]"))).getText(), product);
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()=\""+ price + "\"]"))).getText(), price);
        wait.until(ExpectedConditions.visibilityOf(placeOrder)).click();
    }

    public void validatePriceAndProduct(String product, String price){
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()=\""+ product + "\"]"))).getText(), product);
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()=\""+ price + "\"]"))).getText(), price);
    }
}
