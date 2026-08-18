package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WaitUtil {
	protected Page page;
	

	public WaitUtil(Page page) {
		this.page=page;
	}
	
	public void waitForPageLoad() {
		page.waitForLoadState(LoadState.NETWORKIDLE);
	}
	
	public void waitForUrl(String URL) {
		page.waitForURL(URL);
	}
}
