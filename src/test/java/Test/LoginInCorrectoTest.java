package Test;

import ChromeManager.ChromeManager;
import Page.HomePage;
import Page.LoginPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginInCorrectoTest extends ChromeManager {

    @Test
    public void LoginCorrecto() throws IOException, InvalidFormatException {
        HomePage homePage = new HomePage(driver, wait);

        homePage.selectLoginButton();

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.login("asd", "123", "incorrecto");
    }
}
