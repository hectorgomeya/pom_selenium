package Page;

import ChromeManager.UtilsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    @FindBy(className = "name")
    WebElement productName;

    @FindBy(className = "price-container")
    WebElement priceContainer;

    @FindBy(xpath = "//a[text()=\"Add to cart\"]")
    WebElement buttonAddToCart;

    private WebDriver driver;
    private WebDriverWait wait;

    private UtilsManager utilsManager;

    public ProductPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        utilsManager = new UtilsManager(driver, wait);
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public String getProductPrice(){
        return wait.until(ExpectedConditions.visibilityOf(priceContainer)).getText().split(" ")[0].split("\\$")[1];
    }

    //public void addToCart(String price, String product){
    public void addToCart(){
        //price = getProductPrice();
        //product = getProductName();
        buttonAddToCart.click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
}
