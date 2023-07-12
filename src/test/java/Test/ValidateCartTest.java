package Test;

import ChromeManager.ChromeManager;
import Page.CartPage;
import Page.HomePage;
import Page.LoginPage;
import Page.ProductPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import java.io.IOException;

public class ValidateCartTest extends ChromeManager {

    @Test
    public void validateCart() throws IOException, InvalidFormatException {
        HomePage homePage = new HomePage(driver,wait);

        homePage.selectLoginButton();

        LoginPage loginPage = new LoginPage(driver,wait);

        loginPage.login("asd","asd", "correcto");

        homePage.selectProduct("Nexus 6");

        ProductPage productPage = new ProductPage(driver,wait);

        String price = productPage.getProductPrice();
        String product = productPage.getProductName();

        productPage.addToCart();

        homePage.goToCart();

        CartPage cartPage = new CartPage(driver, wait);

        cartPage.validatePriceAndProduct(product, price);

    }
}
