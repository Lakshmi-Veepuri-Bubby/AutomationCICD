package FrameworkCodes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StandAloneTestMain {
    public static void main(){
        WebDriver driver=new ChromeDriver();
        driver.get("www.google.com");
    }
}
