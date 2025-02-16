package org.example;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        //instead of System.setProperty we can add WebDriverMaager Dependency for this to setup
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
       // System.out.println("hello");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage constructcall = new LandingPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("lakshmibhavaiveepuri403@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Bubby@123");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/h5")));
        List<WebElement> products=driver.findElements(By.xpath("//div[@class='card-body']/h5"));
        List<WebElement> requiredItems=products.stream()
                .filter(s->s.getText().equalsIgnoreCase("IPHONE 13 PRO")).collect(Collectors.toList());
        requiredItems.stream().forEach(s->s.findElement(By.xpath("parent::div/button[2]")).click());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']"))).click();


        driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
        List<WebElement> cartItems=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        boolean flag=cartItems.stream().anyMatch(s->s.getText().equals("IPHONE 13 PRO"));
        Assert.assertTrue(flag);
        driver.findElement(By.cssSelector(".totalRow button")).click();

        //driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
        //another way to use send keys
        Actions a=new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        //Thread.sleep(3000);
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//        a.keyDown(Keys.ARROW_DOWN).build().perform();
//        a.keyDown(Keys.ARROW_DOWN).click().build().perform();
        driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
        boolean orderMsg=driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order.");
        Assert.assertTrue(orderMsg);
        driver.close();

    }
}
