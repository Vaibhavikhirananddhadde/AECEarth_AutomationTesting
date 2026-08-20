package base;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.ConfigReader;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class BaseClass{
	
	protected Playwright playwright;
	protected Browser browser;
	protected Page page;
	protected ExtentTest test;
	protected ExtentReports extent;
	protected BrowserContext context;
	
	@BeforeMethod
	public void setup(Method method) {
		extent = ExtentManager.getInstance();
		test = extent.createTest(method.getName());
		
		playwright = Playwright.create();
		
		String browserName =
		        ConfigReader.getProperty("browser"); 
		
		boolean headless =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("headless")
                );
		
		switch(browserName.toLowerCase()) {
		case "chromium":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
			break;
			
		case "chrome":
			browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
			break;
			
		case "safari":
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless).setArgs(Arrays.asList("--start-maximized")));
			break;
			
		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless).setArgs(Arrays.asList("--start-maximized")));
			break;
			
		 default:
             throw new RuntimeException(
                     "Invalid browser: " + browserName
             );
		}
		
		context = browser.newContext();
		page = context.newPage();
		page.navigate(ConfigReader.getProperty("baseUrl"));
		page.evaluate("document.body.style.zoom='80%'");
			
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		try {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			
			String screenshot = ScreenshotUtil.takeScreenshot(page, result.getName());
			
			test.fail(result.getThrowable());
			
			test.addScreenCaptureFromPath(screenshot);			
		}
		
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test passed");
		}
		
		else {
			test.skip("Test skipped");
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				
		finally {

            extent.flush();

            if (context != null)
                context.close();

            if (browser != null)
                browser.close();

            if (playwright != null)
                playwright.close();
        }
		
		}
	

}
