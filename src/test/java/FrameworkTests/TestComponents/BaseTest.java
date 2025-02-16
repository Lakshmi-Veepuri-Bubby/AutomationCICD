package FrameworkTests.TestComponents;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
     public WebDriver driver;
     public  LandingPage landingPage;


    public WebDriver launchDriver() throws IOException {

        Properties properties= new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalVariable.properties");
        properties.load(fileInputStream);
        String browserName=System.getProperty("browser")!=null? System.getProperty("browser"): properties.getProperty("browser");

        if(browserName.contains("chrome")){
            ChromeOptions options =new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
             driver=new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        }
        else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
             driver=new EdgeDriver();
        }
        else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
             driver=new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }
@BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        driver=launchDriver();

         landingPage = new LandingPage(driver);
        landingPage.openURL();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png"));
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }
}
