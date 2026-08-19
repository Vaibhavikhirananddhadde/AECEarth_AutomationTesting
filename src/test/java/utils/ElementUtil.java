package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ElementUtil {
	
	private Page page;

    public ElementUtil(Page page) {
        this.page = page;
    }

    public void click(String selector) {
        page.locator(selector).click();
    }

    public void fill(
            String selector,
            String value) {

        page.locator(selector).fill(value);
    }

    public String getText(String selector) {

        return page.locator(selector)
                .textContent();
    }

    public boolean isVisible(String selector) {

        return page.locator(selector)
                .isVisible();
	
    }
    
    public void scrollIfNeeded(Locator element) {
    	element.scrollIntoViewIfNeeded();
    }
    
    public void scrollDownJS() {
    	page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
    }

}
