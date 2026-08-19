package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Page;

public class ScreenshotUtil {
	
	public static String takeScreenshot(Page page, String testName) {
		
		try {
			
			String folder = "test-output/screenshots/";
			
			Files.createDirectories(
					Paths.get(folder)
					);
			
			String filename = 
					testName+"_"+System.currentTimeMillis()+".png";
			
			Path path = Paths.get(folder + filename);
			
			page.screenshot( 
					new Page.ScreenshotOptions()
					.setPath(path)
					.setFullPage(true)
					);
			
			return path.toString();
		}
		
		catch(Exception e) {
			 throw new RuntimeException(
	                    "Unable to capture screenshot",e
	            );
		}
		
	}

}
