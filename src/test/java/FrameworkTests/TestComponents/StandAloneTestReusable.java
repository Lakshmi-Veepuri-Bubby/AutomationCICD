package FrameworkTests.TestComponents;

import PageObjects.*;
import inputFiles.DataReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class StandAloneTestReusable extends BaseTest {
    String[] addPproduct={"IPHONE 13 PRO","Banarsi Saree"};

@Test(dataProvider = "getData",groups = "daraReader")
//    public void testCart(String email,String password,String product) throws IOException {
    public void testCart(HashMap<String,String> input) throws IOException {
        //instead of System.setProperty we can add WebDriverMaager Dependency for this to setup


//        WebDriverManager.chromedriver().setup();
//        WebDriver driver=new ChromeDriver();
//        driver.manage().window().maximize();
//       // System.out.println("hello");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       // driver.get("https://rahulshettyacademy.com/client");
        //launchDriver();

        //LandingPage landingPage = new LandingPage(driver);
//        driver.findElement(By.id("userEmail")).sendKeys("lakshmibhavaiveepuri403@gmail.com");
//        driver.findElement(By.id("userPassword")).sendKeys("Bubby@123");
//        driver.findElement(By.id("login")).click();
        //LandingPage landingPage=launchApplication();-->as introduced @BeforeMethod in BaseTest class commented
        /*
        //creating object in page objects method only instead of Test class and returning that object
         */
//        ProductCatalogue productCatalogue=landingPage.loginMethod("lakshmibhavaiveepuri403@gmail.com","Bubby@123");

//    ProductCatalogue productCatalogue=landingPage.loginMethod(email,password);
    ProductCatalogue productCatalogue=landingPage.loginMethod(input.get("email"),input.get("password"));



       WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/h5")));
//        List<WebElement> products=driver.findElements(By.xpath("//div[@class='card-body']/h5"));
       // ProductCatalogue productCatalogue= new ProductCatalogue(driver);
       List<WebElement> products= productCatalogue.getProductsList();

    System.out.println(input.get("product"));

//        List<WebElement> requiredItems=products.stream()
//                .filter(s->s.getText().equalsIgnoreCase("IPHONE 13 PRO")).collect(Collectors.toList());

//        List<WebElement> requiredItems = productCatalogue.getProductName(product);
    List<WebElement> requiredItems = productCatalogue.getProductName(input.get("product"));
        CartVerification cartVerification=productCatalogue.addProductToCart(requiredItems);

//        requiredItems.stream().forEach(s->s.findElement(By.xpath("parent::div/button[2]")).click());
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']"))).click();


//        driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
//        List<WebElement> cartItems=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
//        boolean flag=cartItems.stream().anyMatch(s->s.getText().equals("IPHONE 13 PRO"));
//        Assert.assertTrue(flag);
//        driver.findElement(By.cssSelector(".totalRow button")).click();
       // CartVerification cartVerification = new CartVerification(driver);
//        CheckoutInfo checkoutInfo=cartVerification.cartItems(product);
    CheckoutInfo checkoutInfo=cartVerification.cartItems(input.get("product"));

        String country="india";
        //CheckoutInfo checkoutInfo = new CheckoutInfo(driver);
//       PlacedOrderedInfo placedOrderedInfo= checkoutInfo.checkoutDetails(product,country);
    PlacedOrderedInfo placedOrderedInfo= checkoutInfo.checkoutDetails(input.get("product"),country);

        //driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
        //another way to use send keys
//        Actions a=new Actions(driver);
//        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//        //Thread.sleep(3000);
//        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//        a.keyDown(Keys.ARROW_DOWN).build().perform();
//        a.keyDown(Keys.ARROW_DOWN).click().build().perform();
        String ordermsg = "Thankyou for the order.";

       // driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();

        //PlacedOrderedInfo placedOrderedInfo = new PlacedOrderedInfo(driver);
        Boolean flag=placedOrderedInfo.orderVerification(ordermsg);

        Assert.assertTrue(flag);
        //driver.close();
//        boolean orderMsg=driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order.");
//        Assert.assertTrue(orderMsg);
     //driver.close();


    }

    @Test(dependsOnMethods = "testCart")
    public void orderPageVerification(){
        ProductCatalogue productCatalogue=landingPage.loginMethod("lakshmibhavaiveepuri403@gmail.com","Bubby@123");
        OrderPage orderPage= productCatalogue.ordersInfo();
        boolean match=orderPage.verifyOrderDisplay(addPproduct[0]);
        Assert.assertTrue(match);

    }

    @DataProvider
    public Object[][] getData() throws IOException {
//    Object[][] data = new Object[][] {{"lakshmibhavaiveepuri403@gmail.com","Bubby@123",addPproduct[0]},{"lakshmiveepuri95@gmail.com","Bubby@123",addPproduct[1]}};
//    return data;

        //AnotherWay using HashMap
//        HashMap<String,String> data1 = new HashMap<String,String>();
//        data1.put("email","lakshmibhavaiveepuri403@gmail.com");
//        data1.put("password","Bubby@123");
//        data1.put("product",addPproduct[0]);
//
//        HashMap<String,String> data2 = new HashMap<String,String>();
//        data2.put("email","lakshmiveepuri95@gmail.com");
//        data2.put("password","Bubby@123");
//        data2.put("product",addPproduct[1]);
//
//        return new Object[][] {{data1},{data2}};

        DataReader dataReader = new DataReader();
        List<HashMap<String,String>> data=dataReader.getJsonDataToMap(System.getProperty("user.dir")+"//src//main//java//inputFiles//dataReader.json");
        System.out.println(data);
        //Object[][] finalData = data;
        return new Object[][] {{data.get(0)},{data.get(1)}};


    }
}
