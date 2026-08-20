package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;

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
	
	public void waitUntilContentLoad() {
		page.reload(new Page.ReloadOptions()
		        .setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
	}
}
