package Test;

import ChromeManager.ChromeManager;
import Page.HomePage;
import Page.LoginPage;
import Page.ProductPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import java.io.IOException;

public class SelectPhonesAndGetData extends ChromeManager {

    @Test
    public void selectAndGetData() throws IOException, InvalidFormatException {
        HomePage homePage = new HomePage(driver,wait);

        homePage.selectLoginButton();

        LoginPage loginPage = new LoginPage(driver,wait);

        loginPage.login("asd","asd", "correcto");

        homePage.selectProduct("Nexus 6");

        ProductPage productPage = new ProductPage(driver,wait);

        String price = productPage.getProductPrice();
        String product = productPage.getProductName();

        productPage.addToCart();

        System.out.println("El precio es " + price + " y el producto es " + product);
    }
}
