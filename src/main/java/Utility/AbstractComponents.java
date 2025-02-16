package Utility;

import PageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css="[routerlink*='myorders']:nth-of-type(1)")
    WebElement ordersTab;



    public AbstractComponents(WebDriver driver) {
        this.driver=driver;
        //PageFactory.initElements(driver,this);
         this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void explicitWait(By FindBy){


        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
    }
    public void invisibilityWait(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void waitUntilVisibleWebelement(WebElement FindBy){
        wait.until(ExpectedConditions.visibilityOf(FindBy));
    }
    public void closeDriver(){
        driver.close();
    }

    public OrderPage ordersInfo(){
        ordersTab.click();
        OrderPage orderPage= new OrderPage(driver);
        return orderPage;
        //ordersData.stream().anyMatch(s->s.getText().equalsIgnoreCase())
    }
}
