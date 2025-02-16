package PageObjects;

import Utility.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponents {

    WebDriver driver;


    /*Here there is no driver initialised and this driver we need to pass from our mail test clas as we initialised there

    When we difine constructor whenever this class is called first constructor is executed so we need to initialize driver here
    by creating object in mail class

    PageObject will only focus on elements and actionMethods only
     */
    public OrderPage(WebDriver driver){
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


    @FindBy(css="tr[class='ng-star-inserted'] td:nth-of-type(2)")
    List<WebElement> ordersData;





  public boolean verifyOrderDisplay(String orderedProduct){
      boolean match=ordersData.stream().anyMatch(s->s.getText().equalsIgnoreCase(orderedProduct));
      return match;

  }

}
