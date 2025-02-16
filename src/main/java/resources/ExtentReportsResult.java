package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsResult {

    public static ExtentReports getReportsObject(){
        String path = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("HTML Reports");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Lakshmi");
        //the continution code that letting every test knows to create report for that test execution is present in Listners class

        return extent;



    }
}
