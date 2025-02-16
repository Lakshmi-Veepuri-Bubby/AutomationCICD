package FrameworkTests.TestComponents;

import PageObjects.CartVerification;
import PageObjects.CheckoutInfo;
import PageObjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LoginErrorMessage extends BaseTest {
    @Test(groups = {"errorHandlingMethod"})
    public void errorLogIn() {
        String product = "IPHONE 13 PRO";

        landingPage.loginMethod("lakshmibhavaiveepuri403@gmail.com", "Bubby@1234");
        landingPage.getLoginErrorMsg();
        Assert.assertEquals(landingPage.getLoginErrorMsg(), "Incorrect email or password.");
        //tearDown();
    }
   // @Test(retryAnalyzer = Retry.class)
    public void productDisplay() throws IOException {
                String product="IPHONE 13 PRO 13";
//        driver=launchDriver();
//
//        landingPage = new LandingPage(driver);
//        landingPage.openURL();
//        landingPage.loginMethod("lakshmibhavaiveepuri403@gmail.com", "Bubby@1234");
                ProductCatalogue productCatalogue=landingPage.loginMethod("lakshmiveepuri95@gmail.com","Bubby@123");
                WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
                List<WebElement> requiredItems = productCatalogue.getProductName(product);
                CartVerification cartVerification=productCatalogue.addProductToCart(requiredItems);
                CheckoutInfo checkoutInfo=cartVerification.cartItems(product);
                tearDown();

            }


}


