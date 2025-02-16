package PageObjects;

import Utility.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;


    /*Here there is no driver initialised and this driver we need to pass from our mail test clas as we initialised there

    When we difine constructor whenever this class is called first constructor is executed so we need to initialize driver here
    by creating object in mail class

    PageObject will only focus on elements and actionMethods only
     */
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this); //this will initialise all elements once it is called
    }

    //WebElement userMail=driver.findElement(By.id("userEmail"));

    /*Another way of defining locators is @FndBy
    this we call it as PageFactory
    however @FindBy should aware driver to construct locators....this will be done using pageFactory initiElements

     */
    // List<WebElement> products=driver.findElements(By.xpath("//div[@class='card-body']/h5"));
    @FindBy(xpath ="//div[@class='card-body']/h5")
    List<WebElement> products;
    @FindBy(id="toast-container")
     WebElement invisibleToastMessage;




    By elementVisibility = By.xpath("//div[@class='card-body']/h5");
   By addProductToCartPath = By.xpath("parent::div/button[2]");
   By toastMessage = By.cssSelector("#toast-container");



   public List<WebElement> getProductsList(){
       explicitWait(elementVisibility);
       return products;
   }

   public List<WebElement> getProductName(String productName) {
       return getProductsList().stream()
               .filter(s -> s.getText().equalsIgnoreCase(productName)).collect(Collectors.toList());
   }

   public CartVerification addProductToCart(List<WebElement> requiredItems){
       requiredItems.stream().forEach(s->s.findElement(addProductToCartPath).click());
       explicitWait(toastMessage);
       invisibilityWait(invisibleToastMessage);

       CartVerification cartVerification = new CartVerification(driver); //creating object in page objects only instead of Test class

       return cartVerification;

   }





}
