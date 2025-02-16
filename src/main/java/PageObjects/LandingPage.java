package PageObjects;

import Utility.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

    WebDriver driver;


    /*Here there is no driver initialised and this driver we need to pass from our mail test clas as we initialised there

    When we difine constructor whenever this class is called first constructor is executed so we need to initialize driver here
    by creating object in mail class

    PageObject will only focus on elements and actionMethods only
     */
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this); //this will initialise all elements once it is called
    }

    //WebElement userMail=driver.findElement(By.id("userEmail"));

    /*Another way of defining locators is @FndBy
    this we call it as PageFactory
    however @FindBy should aware driver to construct locators....this will be done using pageFactory initiElements

     */
    @FindBy(id="userEmail")
    WebElement userMail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement submit;


    @FindBy(css="[class*='toast-message']")
    WebElement errorMsg;

    public void openURL(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCatalogue loginMethod(String email, String password){
        userMail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }
    public String getLoginErrorMsg(){
        waitUntilVisibleWebelement(errorMsg);

        return errorMsg.getText();
    }
}
