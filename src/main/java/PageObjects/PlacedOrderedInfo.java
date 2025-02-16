package PageObjects;

import Utility.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlacedOrderedInfo extends AbstractComponents {

    WebDriver driver;


    /*Here there is no driver initialised and this driver we need to pass from our mail test clas as we initialised there

    When we difine constructor whenever this class is called first constructor is executed so we need to initialize driver here
    by creating object in mail class

    PageObject will only focus on elements and actionMethods only
     */
    public PlacedOrderedInfo(WebDriver driver){
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
//    @FindBy(xpath ="(//button[@class='btn btn-custom'])[3]")
//    WebElement proceed;
//    @FindBy(xpath="//div[@class='cartSection']/h3")
//     List<WebElement> addedCartItems;
//
//    @FindBy(css=".totalRow button")
//    WebElement checkout;
//
//
//    @FindBy(css=".ta-item:nth-of-type(2)")
//    WebElement checkoutTextBox;
//
//    @FindBy(css="input[placeholder='Select Country']")
//    WebElement enterText;

    @FindBy(css=".hero-primary")
    WebElement orderVerificationMsg;






  public boolean orderVerification(String orderMsgo) {

      //driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
      boolean orderMsg=orderVerificationMsg.getText().equalsIgnoreCase(orderMsgo);
      return orderMsg;
  }


}
